/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import MessagesTypes.ReponseExistenceFormation;
import business.GestionCommercialLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import sender.FileVerifierExistenceSender;

/**
 *
 * @author 33785
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "FILE_CONFIRM_EXISTENCE")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class FileConfirmerExistenceListener implements MessageListener {

    @EJB
    GestionCommercialLocal gcl;

    public FileConfirmerExistenceListener() {
    }

    @Override
    public void onMessage(Message message) {
        Logger.getLogger(FileConfirmerExistenceListener.class.getName()).log(Level.INFO, "[APPLI COMMERCIAL] FileConfirmerExistenceListener - onMessage()");
        if (message instanceof ObjectMessage) {
            try {
                Object pqObj = ((ObjectMessage) message).getObject();
                if (pqObj instanceof ReponseExistenceFormation) {
                    ReponseExistenceFormation reponseExistence = (ReponseExistenceFormation) pqObj;
                    this.gcl.traiterDemandeFormation(reponseExistence);
                }
            } catch (JMSException ex) {
                Logger.getLogger(FileConfirmerExistenceListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (message != null) {
            System.out.println("Received non text message");
        }
    }

}
