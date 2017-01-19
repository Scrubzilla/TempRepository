/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Pojo.GenericHelper;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jsrpg.Account;
import jsrpg.Character;
import jsrpg.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class AccountHelper {

    public String createAccount(String email, String username, String password, String securityQuest, String securityAns) {
        GenericHelper genHelp = new GenericHelper();
        int id = genHelp.getCount("Id", "Account") + 1;

        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            Account account = new Account();
            account.setId(id);
            account.setRole(0);
            account.setUsername(username);
            account.setEmail(email);
            account.setPassword(password);
            account.setSecurityquestion(securityQuest);
            account.setSecurityquestionans(securityAns);

            session.save(account);
            session.getTransaction().commit();

            session.close();

        } catch (Exception e) {

            e.printStackTrace();
            tx.rollback();
            return "Error during creation of account";
        }
        return "Account was created successfully!";
    }

    public Account getAccount(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account> accountList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            Query q = session.createQuery("from Account A where A.username = :Username");
            q.setParameter("Username", username);
            accountList = (List<Account>) q.list();
            System.out.println("ACCOUNT TEST:     " + accountList.get(0));
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        return accountList.get(0);
    }

    public String checkUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account> accountList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            Query q = session.createQuery("from Account");
            accountList = (List<Account>) q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        String message = "0";
        for (Account account : accountList) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                message = "1";
            }
        }
        return message;
    }

    public String checkEmail(String eMail) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(eMail);

        if (matcher.matches() == false) {
            return "1";
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account> accountList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            Query q = session.createQuery("from Account");
            accountList = (List<Account>) q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        String message = "0";
        for (Account account : accountList) {
            if (account.getEmail().equalsIgnoreCase(eMail)) {
                message = "1";
            }
        }
        return message;
    }

    public String changePassword(String accName, String newPassword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            String hql = "UPDATE Account SET Password =:password WHERE username =:Username";
            Query query = session.createSQLQuery(hql);
            query.setParameter("Username", accName);
            query.setParameter("password", newPassword);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Rows Affected: " + result);
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
            return "Not successfull";
        }
        return "Successfull";
    }

    public String checkLogInCred(String accUser, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account> accountList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            Query q = session.createQuery("from Account where username =:Username");
            q.setParameter("Username", accUser);
            accountList = (List<Account>) q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        String message = "";
        if (accountList.size() == 0) {
            message = "0";
        } else if (accountList.get(0).getUsername().equalsIgnoreCase(accUser) && accountList.get(0).getPassword().equals(password)) {
            message = accountList.get(0).getUsername();
        } else {
            message = "0";
        }

        return message;
    }

    public String getSecurityQuestion(String username) {
        String message = "";
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account> accountList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            Query q = session.createQuery("from Account where Username =:username");
            q.setParameter("username", username);
            accountList = (List<Account>) q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        message = accountList.get(0).getSecurityquestion();

        return message;
    }

    public String checkSecurityAnswer(String username, String answer) {
        String message = "0";

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account> accountList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            Query q = session.createQuery("from Account where Username =:username");
            q.setParameter("username", username);
            accountList = (List<Account>) q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        if (accountList.get(0).getSecurityquestionans().equalsIgnoreCase(answer)) {
            message = "1";
        }
        return message;
    }

    public String addCharacterToAccount(String username, Character chara) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            String hql = "UPDATE Account SET Characters =:chara WHERE username =:Username";
            Query query = session.createSQLQuery(hql);
            query.setParameter("Username", username);
            query.setParameter("chara", chara);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Rows Affected: " + result);
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
            return "Not successfull";
        }

        return "";
    }

    public boolean checkIfAccHasChar(String username) {
        boolean hasChar = false;

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account> accountList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            Query q = session.createQuery("select characters from Account where Username =:username");
            q.setParameter("username", username);
            accountList = (List<Account>) q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        if (accountList.size() == 0) {
            hasChar = false;
        } else {
            hasChar = true;
        }

        return hasChar;
    }

    public String checkRole(String username) {
        String response = "0";

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account> accountList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            Query q = session.createQuery("from Account where Username =:username");
            q.setParameter("username", username);
            accountList = (List<Account>) q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        response = accountList.get(0).getRole() + "";

        return response;
    }

    public String forgotPasswordStuff(String email) {
        String response = "";

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account> accountList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            Query q = session.createQuery("from Account where Email =:email");
            q.setParameter("email", email);
            accountList = (List<Account>) q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        if (accountList.size() == 0) {
            response = "0";
            return response;
        } else {
            response = accountList.get(0).getUsername();
            return response;
        }
    }

    public String changeEmail(String username, String newEmail, String oldEmail, String securityAnsw) {
        if (checkEmail(oldEmail).equals("0")) {
            return "There is no such email";
        }

        if (checkSecurityAnswer(username, securityAnsw).equalsIgnoreCase("0")) {
            return "The answer is not correct";
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            String hql = "UPDATE Account SET Email =:email WHERE username =:Username";
            Query query = session.createSQLQuery(hql);
            query.setParameter("Username", username);
            query.setParameter("email", newEmail);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Rows Affected: " + result);
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
            return "Not successfull";
        }

        return "Successfully changed the email";
    }

    public String updateRole(String username, String role) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            String hql = "UPDATE Account SET role =:Role WHERE username =:Username";
            Query query = session.createSQLQuery(hql);
            query.setParameter("Username", username);
            query.setParameter("Role", Integer.parseInt(role));
            int result = query.executeUpdate();
            session.getTransaction().commit();
            System.out.println("Rows Affected: " + result);
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
            return "Not successfull";
        }
        return "Successfull";
    }
}
