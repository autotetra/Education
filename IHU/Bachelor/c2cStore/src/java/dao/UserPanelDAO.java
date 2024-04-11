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
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Chris
 */
public class UserPanelDAO{
    
    
    public List<Storeitems> retrieveItems(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Storeitems> item = new ArrayList<Storeitems>();
        try{
            transaction = session.beginTransaction();
            item = (List<Storeitems>) session.createQuery ("from Storeitems as storeitems order by rand()").setMaxResults(3).list();
            transaction.commit();
            
        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
        session.close();
        }
        return item;
    }

    
    
}
