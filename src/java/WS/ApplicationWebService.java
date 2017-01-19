/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import Helpers.AccountHelper;
import Helpers.CharacterHelper;
import Pojo.MailHandler;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author User
 */
@WebService(serviceName = "ApplicationWebService")
public class ApplicationWebService {

    /**
     * This is a sample web service operation
     */
    /**
     * Web service operation
     */
    @WebMethod(operationName = "addCharacter")
    public String addCharacter(@WebParam(name = "username") String username, @WebParam(name = "name") String name, @WebParam(name = "portrait") int portrait, @WebParam(name = "str") int str, @WebParam(name = "dex") int dex, @WebParam(name = "vit") int vit, @WebParam(name = "intell") int intell, @WebParam(name = "wis") int wis, @WebParam(name = "chr") int chr) {
        AccountHelper accHelp = new AccountHelper();

        CharacterHelper charHelp = new CharacterHelper();
        String response = charHelp.createCharacter(accHelp.getAccount(username), name, portrait, str, dex, vit, intell, wis, chr);

        return response;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addAccount")
    public String addAccount(@WebParam(name = "username") String username, @WebParam(name = "password") String password, @WebParam(name = "eMail") String eMail, @WebParam(name = "securityQ") String securityQ, @WebParam(name = "securityQans") String securityQans) {
        AccountHelper accHelp = new AccountHelper();

        if (accHelp.checkUsername(username).equals("1") || accHelp.checkEmail(eMail).equals("1")) {
            return "The email or the username is already in user";
        }

        String response = accHelp.createAccount(eMail, username, password, securityQ, securityQans);

        MailHandler mailHa = new MailHandler();
        mailHa.sendRegisterConfirmMail(eMail, username);

        return response;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "changePassword")
    public String changePassword(@WebParam(name = "username") String username, @WebParam(name = "newPassword") String newPassword, @WebParam(name = "oldPassword") String oldPassword) {
        String response = "The old password does not match";

        AccountHelper accHelp = new AccountHelper();

        if (accHelp.getAccount(username).getPassword().equals(oldPassword)) {
            response = accHelp.changePassword(username, newPassword);
        }

        return response;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "logInCredentials")
    public String logInCredentials(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        AccountHelper accHelp = new AccountHelper();
        String response = accHelp.checkLogInCred(username, password);
        return response;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getSecurityQuestion")
    public String getSecurityQuestion(@WebParam(name = "username") String username) {
        AccountHelper accHelp = new AccountHelper();
        String response = accHelp.getSecurityQuestion(username);
        return response;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkSecurityAnswer")
    public String checkSecurityAnswer(@WebParam(name = "username") String username, @WebParam(name = "answer") String answer) {
        AccountHelper accHelp = new AccountHelper();
        String response = accHelp.checkSecurityAnswer(username, answer);
        return response;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkCharacterFromUsername")
    public boolean checkCharacterFromUsername(@WebParam(name = "username") String username) {
        AccountHelper accHelp = new AccountHelper();

        return accHelp.checkIfAccHasChar(username);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getRoleFromAccount")
    public String getRoleFromAccount(@WebParam(name = "username") String username) {
        AccountHelper accHelp = new AccountHelper();
        String response = accHelp.checkRole(username);
        return response;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "forgotPassword")
    public boolean forgotPassword(@WebParam(name = "eMail") String eMail) {
        AccountHelper accHelp = new AccountHelper();
        String response = accHelp.forgotPasswordStuff(eMail);

        if (response.equals("0")) {
            return false;
        }
        MailHandler mailHa = new MailHandler();
        mailHa.sendPasswordResetMail(eMail, response);

        return true;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "changeEmail")
    public String changeEmail(@WebParam(name = "username") String username, @WebParam(name = "newEmail") String newEmail, @WebParam(name = "oldEmail") String oldEmail, @WebParam(name = "securityAns") String securityAns) {
        AccountHelper accHelp = new AccountHelper();
        String response = accHelp.changeEmail(username, newEmail, oldEmail, securityAns);
        return response;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "resetPassword")
    public String resetPassword(@WebParam(name = "username") String username, @WebParam(name = "newPassword") String newPassword) {
        AccountHelper accHelp = new AccountHelper();
        String response = accHelp.changePassword(username, newPassword);
        return response;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCharLocation")
    public String getCharLocation(@WebParam(name = "username") String username) {
        CharacterHelper charHelp = new CharacterHelper();
        String response = charHelp.getCharLocation(username);
        return response;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCharacterName")
    public String getCharacterName(@WebParam(name = "username") String username) {
        CharacterHelper charHelp = new CharacterHelper();
        String response = charHelp.getCharacterName(username);
        return response;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateRole")
    public String updateRole(@WebParam(name = "username") String username, @WebParam(name = "role") String role) {
        AccountHelper accHelp = new AccountHelper();
        String response = accHelp.updateRole(username, role);
        return response;
    }

}
