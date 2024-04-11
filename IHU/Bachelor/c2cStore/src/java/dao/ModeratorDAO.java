package dao;
import hibernateModel.User;
import hibernateModel.Categories;
import hibernateModel.Store;
import hibernateUtil.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ModeratorDAO {
     public List getAllUsers(){
        List<User> userlist = null;
        HibernateUtil LU = new HibernateUtil();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query q = session.createQuery ("from User as user where user.id > 1 " );
        Query q2 = session.createQuery ("from User as user where user.rank='user' " );
        userlist = (List<User>) q.list();
        userlist =  q.list();
        userlist = (List<User>) q2.list();
        userlist =  q2.list();
        
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
      public Store getStoreById(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
         Store store = (Store)session.createQuery("from Store as store where store.id = '"+id+"'").uniqueResult();
        tx.commit();
        session.close(); 
        return store;
     
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
     public List getAllStores(){
        List<Store> storelist = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query qr = session.createQuery ("from Store as store where store.id > 0 " );
        storelist = (List<Store>) qr.list();
        storelist =  qr.list();
         return storelist;
     }
    
    public List getAllCategries(){
        List<Categories> ViewList = null;
       Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query q = session.createQuery ("from Categories as categories where categories.id > 0 " );
        ViewList = (List<Categories>) q.list();
        ViewList =  q.list();
         return ViewList;
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
   public void deleteCatById(int catId) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Categories cat = (Categories) session.load(Categories.class, new Integer(catId));
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
}
