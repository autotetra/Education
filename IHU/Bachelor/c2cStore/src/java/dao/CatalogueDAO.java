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
import static java.lang.System.out;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class CatalogueDAO{
     
    
    public List getItems(){
        List<Storeitems> itemList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query q = session.createQuery ("from Storeitems as storeitems where storeitems.id > 0"  );
        //itemList = (List<Storeitems>) q.list();
        itemList =  q.list();
       

        return itemList;
    }
    
    public List getItemsbyCat(String cid){
        List<Storeitems> itemList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query q = session.createQuery ("from Storeitems as storeitems where storeitems.categories = '"+cid+"'"  );
        itemList =  q.list();

        return itemList;
    }

   
    
}
