/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import resources.DemandeFormation;
import services.DemandeFormationServiceLocal;

/**
 *
 * @author 33785
 */
@Path("demandeFormation")
public class CommercialRest {

    // Accès BackOffice
    DemandeFormationServiceLocal dfsl;

    @Context
    private UriInfo context;

    // Convertisseur JSON
    private Gson gson;

    /**
     * Constructeur Ressource
     */
    public CommercialRest() {
        this.gson = new Gson();
        this.dfsl = lookupDemandeFormationServiceLocal();
    }

    /**
     * Création d'une demande de formation. Pour appeler cette méthode on doit utiliser l'URL :
     * http://localhost:8080/TecgnicoCommercial-web/webresources/competences
     *
     * @param demandeFormationString
     * @return liste des compétences
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCompetences(String demandeFormationString) {
        System.out.println(demandeFormationString);
        DemandeFormation demandeFormation = gson.fromJson(demandeFormationString, DemandeFormation.class);
        this.dfsl.creerDemandeFormation(demandeFormation);
        return Response.status(Status.OK).entity("Demande de formation prise en compte").build();
    }

    /**
     * Recherche JNDI BackOffice
     *
     * @return la référence vers le BackOffice
     */
    private DemandeFormationServiceLocal lookupDemandeFormationServiceLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (DemandeFormationServiceLocal) c.lookup("java:global/Commercial-ear/Commercial-ejb-1.0-SNAPSHOT/DemandeFormationService!services.DemandeFormationServiceLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
