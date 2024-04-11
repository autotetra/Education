/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernateModel.Categories;
import hibernateModel.Orders;
import hibernateModel.Store;
import hibernateModel.Storeitems;
import hibernateModel.User;
import hibernateUtil.HibernateUtil;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class StoreCheckDAO{
    
    
    public Store checkForStore(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Store store =  (Store) session.createQuery("from Store as store where store.user = "+"'"+user.getId()+"'").uniqueResult();
        tx.commit();
        session.close(); 
        return store;
        
     
    }
    
   
    public boolean checkForStoreName(String storeName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Store store =  (Store) session.createQuery("from Store as store where store.storename = "+"'"+storeName+"'").uniqueResult();
        tx.commit();
        session.close(); 
        if(store == null){
            return false;
        }
        else{
            return true;
        }
        
     
    }
     
    
    public Store createStore(String storeName, User ur){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Store store = new Store();
        store.setStorename(storeName);
        store.setUser(ur);
        session.save(store);
        tx.commit();
        session.close(); 
        return store;
        
    }
     
   
    public List getItems(Store store){
        List<Storeitems> itemList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query q = session.createQuery ("from Storeitems as storeitems where storeitems.store = " + store.getId() );
        itemList = (List<Storeitems>) q.list();
        itemList =  q.list();
       

        return itemList;
    }
    
   
    public void addItem(String desc,String quantity, Store store, String price, String category){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        
        //Store store =  (Store) session.createQuery("from Store as store where store.storename = "+"'"+storeName+"'").uniqueResult();
        Storeitems item = new Storeitems();
        item.setDescription(desc);
        item.setPrice(price);
        item.setPhoto1("1");
        item.setPhoto2("2");
        item.setPhoto3("3");
        item.setQuantity(Integer.parseInt(quantity));
        Categories cat = (Categories) session.createQuery("from Categories as categories where categories.categoriename ='"+category+"'").uniqueResult();
        item.setStore(store);
        item.setCategories(cat);
        session.save(item);
        tx.commit();
        session.close();   
        
    }
    
   
    public List getCategories(){
        List<Categories> categories = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query q = session.createQuery ("from Categories as categories where categories.id > 0"  );
        categories = (List<Categories>) q.list();
        categories =  q.list();
       

        return categories;
    }
        public void updateItem(String id, String price, String desc, String quan){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
   

                
//        String hql = "update Storeitems set Price = "+price+", Description = '"+desc+"', Quantity = '"+quan+"' where ID = '"+id+"'";
//        Query query = session.createQuery(hql);
//        int result = query.executeUpdate();
//        String hql2 = "UPDATE Storeitems set Description = "+desc+" WHERE ID = '"+id+"'";
//        Query query2 = session.createQuery(hql2);
//        int result2 = query.executeUpdate();
//        String hql3 = "UPDATE Storeitems set Quantity = "+quan+" WHERE ID = '"+id+"'";
//        Query query3 = session.createQuery(hql3);
//        int result3 = query.executeUpdate();
//        session.getTransaction().commit();
        
        try{
            session.getTransaction().begin();
            Query query = session.createSQLQuery("update Storeitems set Price = "+price+", Description = '"+desc+"', Quantity = '"+quan+"' where ID = '"+id+"'");
            int result = query.executeUpdate();
            session.getTransaction().commit();  
        }catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
        session.close();
        }
        }    
        public void delItem(String id){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
   

                
//        String hql = "update Storeitems set Price = "+price+", Description = '"+desc+"', Quantity = '"+quan+"' where ID = '"+id+"'";
//        Query query = session.createQuery(hql);
//        int result = query.executeUpdate();
//        String hql2 = "UPDATE Storeitems set Description = "+desc+" WHERE ID = '"+id+"'";
//        Query query2 = session.createQuery(hql2);
//        int result2 = query.executeUpdate();
//        String hql3 = "UPDATE Storeitems set Quantity = "+quan+" WHERE ID = '"+id+"'";
//        Query query3 = session.createQuery(hql3);
//        int result3 = query.executeUpdate();
//        session.getTransaction().commit();
        
        try{
            session.getTransaction().begin();
            Query query = session.createSQLQuery("delete from Storeitems  where ID = '"+id+"'");
            int result = query.executeUpdate();
            session.getTransaction().commit();  
        }catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
        session.close();
        }


    }
        
    public List<Orders> getStoreSales(int storeid){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Orders> sales = new ArrayList<Orders>();
        try{
            transaction = session.beginTransaction();
            sales = (List<Orders>) session.createQuery ("from Orders as orders where orders.storeId = '"+storeid+"' ").list();
            transaction.commit();
            
        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
        session.close();
        }
        out.print("PRINEW#!@@!!DSFE!!!!!"+sales.get(0).getStoreId());
        return sales;
    }
    
        public Storeitems getItem(int itemid){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query q = session.createQuery ("from Storeitems as storeitems where storeitems.id = '" + itemid+"'" );
        Storeitems item = (Storeitems) q.uniqueResult();
        out.print("EROREOREOEROEEOROERORE DESC: "+item.getDescription());

        return item;
    }

}
