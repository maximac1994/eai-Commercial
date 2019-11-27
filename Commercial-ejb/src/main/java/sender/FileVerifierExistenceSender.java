/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import MessagesTypes.CodeFormationMessage;
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
 *
 * @author 33785
 */
public class FileVerifierExistenceSender {

    Context context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "jms/__defaultConnectionFactory";
    String destName = "FILE_VERIF_EXISTENCE";
    Destination dest = null;
    int count = 100;
    Session session = null;
    MessageProducer sender = null;

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

    public void publish(DemandeFormationMessage demandeFormation) throws JMSException, InterruptedException {
        Logger.getLogger(FileVerifierExistenceSender.class.getName()).log(Level.INFO, "[APPLI COMMERCIAL] FileVerifierExistenceSender - publish() : " + demandeFormation.toString());
        if (context == null) {
            this.createContext();
        }
        this.connect();
        System.out.println("publish : " + demandeFormation);
        ObjectMessage message = session.createObjectMessage(demandeFormation);
        //message.setJMSType();
        sender.send(message);
        this.close();
    }

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
