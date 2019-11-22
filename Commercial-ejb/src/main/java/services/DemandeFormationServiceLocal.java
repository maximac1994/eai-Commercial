/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ejb.Local;
import resources.DemandeFormation;

/**
 *
 * @author 33785
 */
@Local
public interface DemandeFormationServiceLocal {
    
    public void creerDemandeFormation(DemandeFormation demandeF);
    
}
