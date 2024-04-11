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
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author a
 */
public class LoginDAO{
    User ur = new User();
    
 
    public User getDBUser(String ungiven){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        ur =  (User) session.createQuery("from User as user where user.username = '"+ungiven+"'").uniqueResult();
        tx.commit();
        session.close(); 
        return ur;
     
    }

   
    
    
}
