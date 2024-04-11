/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.StoreCheckDAO;
import hibernateModel.Store;
import hibernateModel.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author a
 */
public class StoreCheckDAOIT {
    
    public StoreCheckDAOIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkForStore method, of class StoreCheckDAO.
     */
    @Test
    public void testCheckForStore() {
        System.out.println("checkForStore");
        User user = new User();
        user.setId(1);
        StoreCheckDAO instance = new StoreCheckDAO();
        Store result = instance.checkForStore(user);
        assertNotNull(result);
    }

    /**
     * Test of checkForStoreName method, of class StoreCheckDAO.
     */
    @Test
    public void testCheckForStoreName() {
        System.out.println("checkForStoreName");
        String storeName = "store";
        StoreCheckDAO instance = new StoreCheckDAO();
        boolean expResult = true;
        boolean result = instance.checkForStoreName(storeName);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckForStoreName2() {
        System.out.println("checkForStoreName");
        String storeName = "";
        StoreCheckDAO instance = new StoreCheckDAO();
        boolean expResult = false;
        boolean result = instance.checkForStoreName(storeName);
        assertEquals(expResult, result);
    }

    /**
     * Test of createStore method, of class StoreCheckDAO.
     */
    @Test(expected=org.hibernate.exception.ConstraintViolationException.class)
    public void testCreateStore() {
        System.out.println("createStore");
        String storeName = "TESTSTORE";
        User ur = new User();
        ur.setId(200);
        StoreCheckDAO instance = new StoreCheckDAO();
        Store result = instance.createStore(storeName, ur);
        assertNotNull(result);
    }

    /**
     * Test of getItems method, of class StoreCheckDAO.
     */
    @Test(expected=java.lang.AssertionError.class)
    public void testGetItems() {
        System.out.println("getItems");
        Store store = new Store();
        store.setId(200);
        StoreCheckDAO instance = new StoreCheckDAO();
        List result = instance.getItems(store);
        assertNull(result);
    }

    /**
     * Test of addItem method, of class StoreCheckDAO.
     */
    @Test(expected=java.lang.NumberFormatException.class)
    public void testAddItem() {
        System.out.println("addItem");
        String desc = "";
        String quantity = "";
        Store store = null;
        String price = "";
        String category = "";
        StoreCheckDAO instance = new StoreCheckDAO();
        instance.addItem(desc, quantity, store, price, category);
    }

    /**
     * Test of getCategories method, of class StoreCheckDAO.
     */
    @Test
    public void testGetCategories() {
        System.out.println("getCategories");
        StoreCheckDAO instance = new StoreCheckDAO();
        List result = instance.getCategories();
        assertNotNull(result);
    }
    
}
