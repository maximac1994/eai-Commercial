/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import MessagesTypes.DemandeFormationMessage;
import MessagesTypes.EvenementFormation;
import MessagesTypes.EvenementFormationAnnulation;
import MessagesTypes.EvenementFormationValidation;
import MessagesTypes.ReponseExistenceFormation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import listener.TopicFormationListener;
import resources.DemandeFormation;
import sender.FileFormationSender;
import sender.FileVerifierExistenceSender;

/**
 *
 * @author 33785
 */
@Stateless
public class GestionCommercial implements GestionCommercialLocal {

    FileVerifierExistenceSender fileVerifExistence;
    FileFormationSender fileFormation;

    /**
     * 
     */
    public GestionCommercial() {
        this.fileVerifExistence = new FileVerifierExistenceSender();
        this.fileFormation = new FileFormationSender();
    }

    /**
     *
     * @param demandeF
     */
    @Override
    public void creerDemandeFormation(DemandeFormation demandeF) {
        Logger.getLogger(GestionCommercial.class.getName()).log(Level.INFO, "[APPLI COMMERCIAL] GestionCommercial - creerDemandeFormation() " + demandeF.toString());
        // Vérifier existance formation
        try {
            DemandeFormationMessage demandeFormationMessage = this.creerDemandeFormationMessage(demandeF);
            this.fileVerifExistence.publish(demandeFormationMessage);
        } catch (JMSException ex) {
            Logger.getLogger(GestionCommercial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GestionCommercial.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Traiter la demande
    }

    private DemandeFormationMessage creerDemandeFormationMessage(DemandeFormation demandeF) {
        DemandeFormationMessage demandeFormationMessage = new DemandeFormationMessage();
        demandeFormationMessage.setNbParticipants(demandeF.getNbParticipants());
        demandeFormationMessage.setCodeEntreprise(demandeF.getCodeClient());
        demandeFormationMessage.setNom(demandeF.getNomEntreprise());
        demandeFormationMessage.setCodeFormation(demandeF.getCodeCatalogue());
        demandeFormationMessage.setLibelleFormation(demandeF.getIntituleFormation());
        return demandeFormationMessage;
    }

    /**
     *
     * @param reponseExistence
     */
    @Override
    public void traiterDemandeFormation(ReponseExistenceFormation reponseExistence) {
        Logger.getLogger(FileVerifierExistenceSender.class.getName()).log(Level.INFO, "[APPLI COMMERCIAL] GestionCommercial - traiterDemandeFormation() : " + reponseExistence.toString());
        if (reponseExistence.isFormationExists()) {
            try {
                this.fileFormation.publish(reponseExistence.getDemandeFormationMessage());
            } catch (JMSException ex) {
                Logger.getLogger(GestionCommercial.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(GestionCommercial.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("!!! LA FORMATION N'EXISTE PAS !!!");
        }
    }

    /**
     *
     * @param evenement
     */
    @Override
    public void genererReponsePositive(EvenementFormationValidation evenement) {
        Logger.getLogger(GestionCommercial.class.getName()).log(Level.INFO, "Validation : " + evenement.toString());
        System.out.println("-> TODO : générer réponse");
    }

    /**
     *
     * @param evenement
     */
    @Override
    public void genererReponseNegative(EvenementFormationAnnulation evenement) {
        Logger.getLogger(GestionCommercial.class.getName()).log(Level.INFO, "[Commercial][GestionCommercial] GenererReponseNegative : " + evenement.toString());
        System.out.println("-> TODO : générer réponse");
    }

}
