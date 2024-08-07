/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import hibernateModel.Store;
import hibernateModel.Storeitems;
import hibernateModel.User;
import hibernateUtil.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Chris
 */
public class RegisterDAO{
    
    User query = new User();
    
    
    public void addUser(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            
            
        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
        session.close();
        }
    }
    
    
    public User checkUsername(String username){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            query = (User) session.createQuery("from User as user where user.username = '"+username+"'").uniqueResult();
            transaction.commit();
            
            
        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
        session.close();
        }
        return query;
    }
    
    
    public User checkEmail(String email){
        
        Session session = HibernateUtil.getSessionFactory().openSession();;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            query = (User) session.createQuery("from User as user where user.email = '"+email+"'").uniqueResult();
            transaction.commit();
            
            
        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
        session.close();
        }
        return query;
    }

    

    
    
}
