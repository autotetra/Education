/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernateModel.Orders;
import hibernateModel.Storeitems;
import hibernateUtil.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Chris
 */
public class CheckoutDAO{

    
    public void addOrder(Orders order){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            
            
        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
        session.close();
        }
    }
    
    public Orders getOrder(int id){
        Orders order = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        order = (Orders) session.createQuery ("from Orders as orders where orders.id = '"+id+"'").uniqueResult();    

        return order;
    }
    
    public void updateItem(int itemID, int qua){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            session.getTransaction().begin();
            Query query = session.createSQLQuery("update Storeitems set Quantity = :qua" + " where Id = :itemID");
            query.setParameter("qua", qua);
            query.setParameter("itemID", itemID);
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
    
    public Storeitems getItem(int id){
        Storeitems item = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        item = (Storeitems) session.createQuery ("from Storeitems as storeitems where storeitems.id = '"+id+"'").uniqueResult();    

        return item;
    }
    
    
}
