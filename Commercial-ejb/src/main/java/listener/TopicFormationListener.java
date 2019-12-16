/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import MessagesTypes.EvenementFormation;
import MessagesTypes.EvenementFormationAnnulation;
import MessagesTypes.EvenementFormationValidation;
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

/**
 * topic pour gestion validation ou annulation de formation
 * @author 33785
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "TOPIC_FORMATION_COM")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "TOPIC_FORMATION")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "TOPIC_FORMATION")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class TopicFormationListener implements MessageListener {

    @EJB
    GestionCommercialLocal gcl;

    /**
     *
     */
    public TopicFormationListener() {
    }

    /**
     * à chaque reception de message verification de l'entete JMS pour savoir si annulation ou validation
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage objet = (ObjectMessage) message;
                Object pqObj = objet.getObject();
                if (pqObj instanceof EvenementFormation) {
                    if ("annulation".equals(message.getJMSType())) {
                        EvenementFormationAnnulation evenementFormation = (EvenementFormationAnnulation) pqObj;
                        this.gcl.genererReponseNegative(evenementFormation);
                    } else if ("validation".equals(message.getJMSType())) {
                        EvenementFormationValidation evenementFormation = (EvenementFormationValidation) pqObj;
                        this.gcl.genererReponsePositive(evenementFormation);
                    }
                }
            } else if (message != null) {
                System.out.println("Received non text message");
            }
        } catch (JMSException ex) {
            Logger.getLogger(FileConfirmerExistenceListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
