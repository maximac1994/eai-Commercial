/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import MessagesTypes.DemandeFormationMessage;
import business.GestionCommercial;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Sender vers File de l'appli formation pour traitement de demande
 * @author 33785
 */
public class FileFormationSender {

    Context context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "jms/__defaultConnectionFactory";
    String destName = "FILE_FORMATION";
    Destination dest = null;
    Session session = null;
    MessageProducer sender = null;

    /**
     * creation du contexte
     */
    public void createContext() {
        try {
            // create the JNDI initial context.
            context = new InitialContext();
            // look up the ConnectionFactory
            factory = (ConnectionFactory) context.lookup(factoryName);
            // look up the Destination
            dest = (Destination) context.lookup(destName);
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {

        }
    }

    /**
     * connection JMS
     * @throws JMSException
     */
    public void connect() throws JMSException {
        // create the connection
        connection = factory.createConnection();
        // create the session
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // create the sender
        sender = session.createProducer(dest);
        // start the connection, to enable message sends
        connection.start();
    }

    /**
     * envoi un message sur la FILE FORMATION pour traiter une demande
     * @param demandeFormation
     * @throws JMSException
     * @throws InterruptedException
     */
    public void publish(DemandeFormationMessage demandeFormation) throws JMSException, InterruptedException {
        Logger.getLogger(FileFormationSender.class.getName()).log(Level.INFO, "[APPLI COMMERCIAL] FileFormationSender - publish() : " + demandeFormation.toString());
        if (context == null) {
            this.createContext();
        }
        this.connect();
        ObjectMessage message = session.createObjectMessage(demandeFormation);
        //message.setJMSType();
        sender.send(message);
        this.close();
    }

    /**
     * fermeture de la connection
     */
    public void close() {
        // close the context
        if (context != null) {
            try {
                context.close();
            } catch (NamingException exception) {
                exception.printStackTrace();
            }
        }

        // close the connection
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException exception) {
                exception.printStackTrace();
            }
        }
    }

}
