/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

import java.util.List;
import jsrpg.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class GenericHelper {

    public int getCount(String column, String table) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        List results = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {
            String hql = "select count(" + column + ") from " + table;
            Query query = session.createQuery(hql); 
            results = query.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        return Integer.parseInt(results.get(0).toString());
    }
}
