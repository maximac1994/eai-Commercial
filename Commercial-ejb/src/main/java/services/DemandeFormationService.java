/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import business.GestionCommercialLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import resources.DemandeFormation;

/**
 *
 * @author 33785
 */
@Stateless
public class DemandeFormationService implements DemandeFormationServiceLocal {
    
    @EJB
    GestionCommercialLocal gcl;

    @Override
    public void creerDemandeFormation(DemandeFormation demandeF) {
        this.gcl.creerDemandeFormation(demandeF);
    }
    
}
