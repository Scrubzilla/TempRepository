/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author User
 */
public class MailHandler {

    public boolean sendRegisterConfirmMail(String recipient, String username) {
        System.out.println("Sent confirmation email!");
        return mailSender(recipient, "Registration was successfull for the account " + username + ".\n\nYou can now login on the website http://localhost:8080/JspRPG_WebClient/Login.jsp \n\nPlease enjoy your time in the world of JspRPG!", "JspRPG Registration complete!") == true;
    }

    public void sendPasswordResetMail(String recipient, String username) {

        System.out.println("Sent reset email!");
        mailSender(recipient, "Hi there!\n\nSomeone(hopefully you) have requested a password retrieval for the account: " + username + ".\n\nIf this was intentional, please visit the link: http://localhost:8080/JspRPG_WebClient/PasswordReset.jsp?user=" + username + " \n\nThere you will be able to reset it.", "JspRPG Forgotten password");

    }

    private boolean mailSender(String recipient, String emailMessage, String emailSubject) {
        try {
            String username = "scruburuzilla@gmail.com";
            String password = "Lila_bil123";

            Properties props = new Properties();

            //This properties are valid for gmail.  You need to check for other mail providers/servers/agents.
            props.setProperty("mail.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            System.out.println("So far so good -1 setting up session parameters");

            //Authentication is performed here.  
            SMTPAuthenticator auth = new SMTPAuthenticator(username, password);
            System.out.println("So far so good-2 authenticator called....");

            //The mail session is instantiated
            //The rest is copied from Java Mail documentation.
            Session mailConnection = Session.getInstance(props, auth);
            javax.mail.Message msg = new MimeMessage(mailConnection);
            System.out.println("So far so good-3 creating MIME message.....");
            Address sender = new InternetAddress(username, "JspRPG");
            Address receiver = new InternetAddress(recipient);
            Session session = Session.getInstance(props);
            msg.setContent(emailMessage, "text/plain");
            msg.setFrom(sender);
            msg.setRecipient(javax.mail.Message.RecipientType.TO, receiver);
            msg.setSubject(emailSubject);
            System.out.println("So far so good-4 ... finishing mail setup");

            Transport transport = session.getTransport("smtp");
            transport.connect(username, password);
            System.out.println("So far so good-5  connecting to server.....");

            Transport.send(msg);
            System.out.println("Message successfully sent");
            return true;        //Time to celebrate IF everything went fine upto this point.
        } catch (MessagingException e) {
            System.out.printf("Messaging Exception: " + e.getMessage());
//          throw new RuntimeException(e);
        } catch (Exception ex) {
            System.out.printf("General Exception: ");
            ex.printStackTrace();
        }
        return false;       //OOPS! something went terribly wrong.  Check the above exception messages!!
    }

}
