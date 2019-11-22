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
    
    public void creerDemandeFormation(DemandeFormation demandeF);
    
    public void traiterDemandeFormation(ReponseExistenceFormation reponseExistence);
    
    public void genererReponsePositive(EvenementFormationValidation evenement);
    
    public void genererReponseNegative(EvenementFormationAnnulation evenement);
    
}
