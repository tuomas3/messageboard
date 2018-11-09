package com.buutcamp.database;

import com.buutcamp.config.HibernateConfig;
import com.buutcamp.main.Utils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.hibernate.id.enhanced.HiLoOptimizer;
import sun.plugin2.message.Message;

import java.util.ArrayList;
import java.util.List;

public class HibernateMethods {

    private static SessionFactory factory;

    private static Session getCurrentSession(){
        if(factory == null){
            Configuration configuration = (new HibernateConfig()).getConfiguration();
            factory = configuration.addAnnotatedClass(MessageData.class).buildSessionFactory();
        }
        return factory.getCurrentSession();
    }

    public static List<MessageData> getAllData() {
        Session session = getCurrentSession();
        List<MessageData> list = new ArrayList<MessageData>();
        try {
            session.beginTransaction();
            list = session.createQuery("from MessageData").getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return list;
    }

    public static List<MessageData> getNLastEntries(int numberOfEntries) {
        Session session = getCurrentSession();
        List<MessageData> list = new ArrayList<MessageData>();
        try {
            session.beginTransaction();
            int total = getTotalCount(session);
            Criteria criteria = session.createCriteria(MessageData.class);
            criteria.setFirstResult(total-numberOfEntries);
            criteria.setMaxResults(numberOfEntries);
            list = criteria.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return list;
    }

    private static int getTotalCount(Session session){
        Criteria count = session.createCriteria(MessageData.class);
        count.setProjection(Projections.rowCount());
        Long total = (Long) count.uniqueResult();
        return total.intValue();
    }

    public static void saveMessage(MessageData messageData){
        Session session = getCurrentSession();
        try {
            session.beginTransaction();
            session.save(messageData);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        session.close();
    }

    public static void deleteMessage(int id){
        Session session = getCurrentSession();
        try {
            session.beginTransaction();
            MessageData messageData = session.get(MessageData.class, id);
            session.delete(messageData);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        session.close();
    }

    public static void deleteMessages(ArrayList<Integer> ids){
        Session session = getCurrentSession();
        try {
            session.beginTransaction();
            for(int id : ids) {
                MessageData messageData = session.get(MessageData.class, id);
                session.delete(messageData);
            }
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        session.close();
    }

    public static List<MessageData> searchMessages(String searchVal, boolean usernameField, boolean dateField, boolean messageField) {
        List<MessageData> list = new ArrayList<MessageData>();
        if(!Utils.stringIsNullOrEmpty(searchVal) && (usernameField || dateField || messageField)) {
            Session session = getCurrentSession();
            try {
                session.beginTransaction();
                Query query;
                String queryStr = "from MessageData where ";
                if(usernameField){
                    queryStr += "lower(username) like :searchValue";
                }
                if(dateField){
                    if(usernameField) queryStr += " or ";
                    queryStr += "lower(date) like :searchValue";
                }
                if(messageField){
                    if(usernameField || dateField) queryStr += " or ";
                    queryStr += "lower(message) like :searchValue";
                }
                query = session.createQuery(queryStr, MessageData.class);
                query.setParameter("searchValue", "%" + searchVal.toLowerCase() + "%");
                list = query.getResultList();
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            session.close();
        }
        return list;
    }

    public static void deleteRange(Integer low, Integer high) {
        Session session = getCurrentSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("delete MessageData where id between :low and :high");
            query.setParameter("low", low);
            query.setParameter("high", high);

            int result = query.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        session.close();
    }
}
