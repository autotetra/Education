package dao;

import hibernateModel.Storeitems;
import hibernateUtil.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SearchDAO
{
    public List getItems(String givenSearchKey)
    {
        List<Storeitems> itemList = null;
        HibernateUtil LU = new HibernateUtil();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query q = session.createQuery("FROM Storeitems AS storeitems WHERE storeitems.description LIKE '%" + givenSearchKey + "%'"  );
        itemList = (List<Storeitems>) q.list();
        itemList =  q.list();
        return itemList;
    }
}
