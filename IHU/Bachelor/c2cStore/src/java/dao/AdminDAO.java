package dao;
import hibernateModel.User;
import hibernateModel.Categories;
import hibernateModel.Orders;
import hibernateModel.Store;
import hibernateModel.Storeitems;
import hibernateUtil.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Denis
 */

public class AdminDAO {
   //============================USER CRUD================================================
    public List getAllUsers(){
        List<User> userlist = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query q = session.createQuery ("from User as user where user.id > 0 " );
        userlist = (List<User>) q.list();
        userlist =  q.list();
         return userlist;
     }
    public List getAllModerators(){
        List<User> userlist = null;
        HibernateUtil LU = new HibernateUtil();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query q = session.createQuery ("from User as user where user.rank='moderator' " );
        userlist = (List<User>) q.list();
        userlist =  q.list();
         return userlist;
     }
    public User getUserById(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        User user = (User)session.createQuery("from User as user where user.id = '"+id+"'").uniqueResult();
        tx.commit();
        session.close(); 
        return user;
    }
    public void deleteUserById(int userid) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            User user = (User) session.load(User.class, new Integer(userid));
            session.delete(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
                e.printStackTrace();
            }
        } finally {
            session.flush();
            session.close();
        }
    }
    public void updateUser(User user) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
  //============================END OF USER CRUD================================================ 
    
    //============================CATEGORY CRUD================================================
    public List getAllCategries(){
        List<Categories> catList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query q = session.createQuery ("from Categories as categories where categories.id > 0 " );
        catList = (List<Categories>) q.list();
        catList =  q.list();
         return catList;
     }
    public void createCategory(Categories cat){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(cat);
            transaction.commit();
           }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
        session.close();
        }
    }
     public Categories getCatById(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Categories cats = (Categories)session.createQuery("from Categories as categories where categories.id = '"+id+"'").uniqueResult();
        tx.commit();
        session.close(); 
        return cats;
    }
    public void updateCat(Categories cat) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(cat);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
   public void deleteCatById(int catId) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Categories cat = (Categories)session.load(Categories.class, new Integer(catId));
            session.delete(cat);
            session.getTransaction().commit();
            } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
                e.printStackTrace();
            }
        } finally {
            session.flush();
            session.close();
        } 
}
   //============================END OF CATEGORY CRUD================================================
   
   //===========================ORDER MANAGMENT =================================================
   public List getAllOrders(){
        List<Orders> orderList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query q = session.createQuery ("from Orders as orders where orders.id > 0 " );
        orderList = (List<Orders>) q.list();
        orderList =  q.list();
         return orderList;
     }
   
   //===========================END OF ORDER MANAGMENT============================================
   //===========================STORE MANGMENT====================================================
    public List getAllStores(){
        List<Store> storelist = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query q = session.createQuery ("from Store as store where store.id > 0 " );
        storelist = (List<Store>) q.list();
        storelist =  q.list();
         return storelist;
     }
     public void deleteStoreById(int storeId) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Store store = (Store)session.load(Store.class, new Integer(storeId));
            session.delete(store);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
                e.printStackTrace();
            }
        } finally {
            session.flush();
            session.close();
        }
    }
   //===========================END OF STORE MANAGMENT============================================
    
//===================================ITEM MANAGMENT===============================================
   public List getAllItems(){
        List<Storeitems> itemList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query q = session.createQuery ("from Storeitems as storeitems where storeitems.id > 0"  );
        itemList = (List<Storeitems>) q.list();
        itemList =  q.list();
       

        return itemList;
    }
   
   public void deleteItemById(int itemId) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Storeitems item = (Storeitems)session.load(Storeitems.class, new Integer(itemId));
            session.delete(item);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
                e.printStackTrace();
            }
        } finally {
            session.flush();
            session.close();
        }
    }
   public Storeitems getItemById(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Storeitems item = (Storeitems)session.createQuery("from Storeitems as storeitems where storeitems.id = '"+id+"'").uniqueResult();
        tx.commit();
        session.close(); 
        return item;
    }
    public void updateItem(Storeitems item) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
 //===================================END OF ITEM MANAGMENT========================================  
}//end of DAO