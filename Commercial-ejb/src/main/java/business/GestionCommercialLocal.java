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
import javax.ejb.Local;
import resources.DemandeFormation;

/**
 *
 * @author 33785
 */
@Local
public interface GestionCommercialLocal {
    
    /**
     * crée une demande formation
     * @param demandeF
     */
    public void creerDemandeFormation(DemandeFormation demandeF);
    
    /**
     * traite une demande de formation apres reception d'une confirmation d'existance
     * @param reponseExistence
     */
    public void traiterDemandeFormation(ReponseExistenceFormation reponseExistence);
    
    /**
     * genere une reponse positive apres validation
     * @param evenement
     */
    public void genererReponsePositive(EvenementFormationValidation evenement);
    
    /**
     * génere une réponse positive apres annulation
     * @param evenement
     */
    public void genererReponseNegative(EvenementFormationAnnulation evenement);
    
}
