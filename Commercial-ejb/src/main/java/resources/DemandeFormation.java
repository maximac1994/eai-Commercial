/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

/**
 *
 * @author 33785
 */
public class DemandeFormation {
    
    private String codeClient;
    private String nomEntreprise;
    private String codeCatalogue;
    private String intituleFormation;
    private int nbParticipants;    

    public DemandeFormation(String codeClient, String nomEntreprise, String codeCatalogue, String intituleFormation, int nbParticipants) {
        this.codeClient = codeClient;
        this.nomEntreprise = nomEntreprise;
        this.codeCatalogue = codeCatalogue;
        this.intituleFormation = intituleFormation;
        this.nbParticipants = nbParticipants;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getCodeCatalogue() {
        return codeCatalogue;
    }

    public void setCodeCatalogue(String codeCatalogue) {
        this.codeCatalogue = codeCatalogue;
    }

    public String getIntituleFormation() {
        return intituleFormation;
    }

    public void setIntituleFormation(String intituleFormation) {
        this.intituleFormation = intituleFormation;
    }

    public int getNbParticipants() {
        return nbParticipants;
    }

    public void setNbParticipants(int nbParticipants) {
        this.nbParticipants = nbParticipants;
    }

    @Override
    public String toString() {
        return "DemandeFormation{" + "codeClient=" + codeClient + ", nomEntreprise=" + nomEntreprise + ", codeCatalogue=" + codeCatalogue + ", intituleFormation=" + intituleFormation + ", nbParticipants=" + nbParticipants + '}';
    }
    
}
