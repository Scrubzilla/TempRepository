/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.util.ArrayList;
import java.util.List;
import jsrpg.Creature;
import jsrpg.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class AreaHelper {

    public List<Creature> getCreaturesFromArea(String zoneId, String areaId) {
        List<Creature> creatList = checkZoneArea(zoneId, areaId);

        return creatList;
    }

    public List<Creature> checkZoneArea(String zoneId, String areaId) {
        List<Creature> areaCreatureList = new ArrayList<>();

        List<Creature> creatueList = getAllCreaturesFromDB();

        if (zoneId.equalsIgnoreCase("1")) {
            if (areaId.equalsIgnoreCase("1")) {
                areaCreatureList.add(creatueList.get(1));
                areaCreatureList.add(creatueList.get(1));

            } else if (areaId.equalsIgnoreCase("2")) {
                areaCreatureList.add(creatueList.get(2));

            } else if (areaId.equalsIgnoreCase("3")) {
                areaCreatureList.add(creatueList.get(1));
                areaCreatureList.add(creatueList.get(2));

            }
        } else if (zoneId.equalsIgnoreCase("2")) {
            if (areaId.equalsIgnoreCase("1")) {
                areaCreatureList.add(creatueList.get(1));
                areaCreatureList.add(creatueList.get(1));
                areaCreatureList.add(creatueList.get(1));
                areaCreatureList.add(creatueList.get(1));

            } else if (areaId.equalsIgnoreCase("2")) {
                areaCreatureList.add(creatueList.get(2));
                areaCreatureList.add(creatueList.get(2));

            } else if (areaId.equalsIgnoreCase("3")) {
                areaCreatureList.add(creatueList.get(0));
                areaCreatureList.add(creatueList.get(2));
            }
        } else if (zoneId.equalsIgnoreCase("3")) {
            if (areaId.equalsIgnoreCase("1")) {
                areaCreatureList.add(creatueList.get(1));
                areaCreatureList.add(creatueList.get(2));
                areaCreatureList.add(creatueList.get(0));

            } else if (areaId.equalsIgnoreCase("2")) {
                areaCreatureList.add(creatueList.get(2));
                areaCreatureList.add(creatueList.get(0));
                areaCreatureList.add(creatueList.get(0));

            } else if (areaId.equalsIgnoreCase("3")) {
                areaCreatureList.add(creatueList.get(0));
                areaCreatureList.add(creatueList.get(0));
                areaCreatureList.add(creatueList.get(0));

            }
        }

        return areaCreatureList;
    }

    private List<Creature> getAllCreaturesFromDB() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Creature> creatList = null;
        org.hibernate.Transaction tx = session.beginTransaction();
        try {

            Query q = session.createQuery("from Creature");
            creatList = (List<Creature>) q.list();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

        return creatList;
    }
}
