/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Pojo.GenericHelper;
import java.util.Date;
import java.util.List;
import jsrpg.Character;
import jsrpg.CharacterId;
import jsrpg.Account;
import jsrpg.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class CharacterHelper {

    /*
     CharacterId id, Account account, String name, int portrait, Date lastloggedin, Integer level, Integer xp, 
     Integer location, Boolean isonline, Integer money, Integer strength, Integer dexterity, Integer vitality, Integer intelligence, 
     Integer wisdom, Integer charisma, Integer equiphelm, Integer equiparmor, Integer equipweapon, Integer equipoffhand, Integer struse, 
     Integer dexuse, Integer fureuse, Integer iceuse, Integer arcaneuse, Integer lightuse, Integer darkuse, Integer magoffuse, 
     Integer magdefuse
    
    
     */
    public String createCharacter(Account account, String name, int portrait, int str, int dex, int vit, int intell, int wis, int charisma) {
        GenericHelper genHelp = new GenericHelper();
        int id = genHelp.getCount("Id", "Character") + 1;

        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            Character character = new Character();
            CharacterId charId = new CharacterId();
            charId.setId(id);
            charId.setAccountId(account.getId());

            character.setId(charId);
            character.setAccount(account);
            character.setName(name);
            character.setPortrait(portrait);
            //character.setLastloggedin();
            character.setLevel(0);
            character.setXp(0);
            character.setLocation(1);
            character.setIsonline(true);
            character.setMoney(0);
            character.setStrength(str);
            character.setDexterity(dex);
            character.setVitality(vit);
            character.setIntelligence(intell);
            character.setWisdom(wis);
            character.setCharisma(charisma);
            
            AccountHelper accHelp = new AccountHelper();
            accHelp.addCharacterToAccount(account.getUsername(), character);

            session.save(character);
            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
            return "Failed to create character";
        }
        return "Character was created successfully!";

    }

    public String checkName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Character> charList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            Query q = session.createQuery("from Character");
            charList = (List<Character>) q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        String message = "0";
        for (Character chara : charList) {
            if (chara.getName().equalsIgnoreCase(name)) {
                message = "1";
            }
        }
        return message;
    }

    public String changeLocationOfChar(String charName, String location) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            String hql = "UPDATE `character` SET location = '" + location + "' WHERE name ='" + charName + "'";
            Query query = session.createSQLQuery(hql);
            //query.setParameter("Name", charName);
            //query.setParameter("Location", Integer.parseInt(location));
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

        return "successfuly added new location";
    }

    public Character getCharacterFromAccountName(String accName) {
        Character character = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Character> charList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            Query q = session.createQuery("from Character");
            charList = (List<Character>) q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            session.close();
        }

        for (Character chara : charList) {
            if (chara.getAccount().getUsername().equalsIgnoreCase(accName)) {
                character = chara;
            }
        }

        return character;
    }

    public String updateXP(int xp, String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            String hql = "UPDATE `character` SET xp =:XP WHERE name =:Name";
            Query query = session.createSQLQuery(hql);
            query.setParameter("Name", name);
            query.setParameter("Location", xp);
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

        return "Successfully updated XP";
    }
    
    public String getCharLocation(String username){
        Character character = null;
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Character> charList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            Query q = session.createQuery("from Character");
            charList = (List<Character>) q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        
        for (Character chara : charList) {
            if (chara.getAccount().getUsername().equalsIgnoreCase(username)) {
                character = chara;
            }
        }
        return character.getLocation() + "";
    }
    
    public String getCharacterName(String username){
        Character character = null;
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Character> charList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            Query q = session.createQuery("from Character");
            charList = (List<Character>) q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        
        for (Character chara : charList) {
            if (chara.getAccount().getUsername().equalsIgnoreCase(username)) {
                character = chara;
            }
        }
        return character.getName() + "";
    }
    
    public Character getCharacter(String charName){
      
        Character character = null;
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Character> charList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

           Query q = session.createQuery("from Character Where name =:Name");
            q.setParameter("Name", charName);
            charList = (List<Character>) q.list();
            session.close();

       } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        
        character = charList.get(0);
        
        return character;
    }
    
   
}
