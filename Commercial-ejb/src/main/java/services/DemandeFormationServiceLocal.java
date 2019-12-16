/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Local;
import resources.DemandeFormation;

/**
 * exposition des traitements du service COMMERCIAL en REST
 * @author 33785
 */
@Local
public interface DemandeFormationServiceLocal {
    
    /**
     * creation d'une demande de formation
     * @param demandeF
     */
    public void creerDemandeFormation(DemandeFormation demandeF);
    
}
