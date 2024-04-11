/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernateModel.Orders;
import hibernateModel.Storeitems;
import hibernateUtil.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Chris
 */
public class CourierDAO {
    
    public List<Orders> retrieveOrders(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Orders> order = new ArrayList<Orders>();
        try{
            transaction = session.beginTransaction();
            order = (List<Orders>) session.createQuery ("from Orders as orders where orders.delivered = '0'").list();
            transaction.commit();
            
        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
        session.close();
        }
        return order;
    }
    
    public List<Orders> getOrder(int ordercode){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Orders> order = new ArrayList<Orders>();
        try{
            transaction = session.beginTransaction();
            order = (List<Orders>) session.createQuery ("from Orders as orders where orders.orderCode = '"+ordercode+"' ").list();
            transaction.commit();
            
        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
        session.close();
        }
        return order;
    }
    
    public void updateOrder(int ordcode, boolean delivered){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            session.getTransaction().begin();
            Query query = session.createSQLQuery("update Orders set delivered = :delivered" + " where OrderCode = :ordercode");
            query.setParameter("ordercode", ordcode);
            query.setParameter("delivered", delivered);
            int result = query.executeUpdate();
            session.getTransaction().commit();
            
            
        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
        session.close();
        }
    }
    
}
