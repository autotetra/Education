/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernateModel.Categories;
import hibernateModel.Store;
import hibernateModel.Storeitems;
import hibernateModel.User;
import hibernateUtil.HibernateUtil;
import static java.lang.System.out;
import java.util.List;
import model.ItemViewModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class ItemViewDAO{
     
    
    public ItemViewModel getItemInfo(String id){
        Storeitems item = null;
        Categories cat = new Categories();
        User user = new User();
        Store store = new Store();
        ItemViewModel ivm = new ItemViewModel();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        item = (Storeitems) session.createQuery ("from Storeitems as storeitems where storeitems.id = '"+id+"'").uniqueResult();    
        ivm.setItem(item);
//        out.print("PRINTING PRINTING "+item.getDescription()+item.getStore().getId());
        store = (Store) session.createQuery ("from Store as store where store.id = "+item.getStore().getId()).uniqueResult();
//        out.print("PRINTING PRINTING "+store.getStorename());
        ivm.setStore(store);
        user = (User) session.createQuery ("from User as user where user.id = '"+store.getUser().getId()+"'").uniqueResult();
        ivm.setUser(user);
        cat = (Categories) session.createQuery ("from Categories as categories where categories.id = '"+item.getCategories().getId()+"'").uniqueResult();
        ivm.setCat(cat);
        
//        out.print(cat.getCategoriename()+item.getDescription()+store.getStorename()+user.getLastname());
        
        return ivm;
    }

 
}
