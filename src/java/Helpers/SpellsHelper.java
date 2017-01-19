/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.util.List;
import jsrpg.HibernateUtil;
import jsrpg.Spells;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class SpellsHelper {

    public List<Spells> getAllSpells() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Spells> spellList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            Query q = session.createQuery("from Spells");
            spellList = (List<Spells>) q.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        
        return spellList;
    }

}
