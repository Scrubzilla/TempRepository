/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *
 * @author User
 */
public class SMTPAuthenticator extends Authenticator{
    private PasswordAuthentication authentication;

    public SMTPAuthenticator(String login, String password) {
        authentication = new PasswordAuthentication(login, password);
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return authentication;
    }
}
