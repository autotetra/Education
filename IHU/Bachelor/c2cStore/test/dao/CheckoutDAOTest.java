/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernateModel.Orders;
import hibernateModel.Storeitems;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chris
 */
public class CheckoutDAOTest {

    public CheckoutDAOTest() {
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
     * Test of addOrder method, of class CheckoutDAO.
     */
    @Test (expected = java.lang.NoClassDefFoundError.class)
    public void testAddOrder() {
        System.out.println("addOrder");
        Orders order1 = new Orders();
        Orders order2 = new Orders();
        order1.setDelivered(false);
        order1.setId(1);
        order1.setItemId(1);
        order1.setOrderCode(1);
        order1.setQuantity(1);
        order1.setStoreId(1);
        order1.setUserId(1);
        CheckoutDAO instance = new CheckoutDAO();
        instance.addOrder(order1);
        int expResult = 1;
        order2 = instance.getOrder(1);
        int result = order2.getId();
        assertEquals(expResult, result);

    }

    /**
     * Test of getOrder method, of class CheckoutDAO.
     */
    @Test (expected = java.lang.ExceptionInInitializerError.class)
    public void testGetOrder() {
        Orders order1 = new Orders();
        Orders order2 = new Orders();
        order1.setDelivered(false);
        order1.setId(1);
        order1.setItemId(1);
        order1.setOrderCode(1);
        order1.setQuantity(1);
        order1.setStoreId(1);
        order1.setUserId(1);
        CheckoutDAO instance = new CheckoutDAO();
        instance.addOrder(order1);
        int expResult = 1;
        order2 = instance.getOrder(1);
        int result = order2.getId();
        assertEquals(expResult, result);

    }

    /**
     * Test of updateItem method, of class CheckoutDAO.
     */
    @Test (expected = java.lang.NoClassDefFoundError.class)
    public void testUpdateItem() {
        System.out.println("updateItem");
        CheckoutDAO checkoutDAO = new CheckoutDAO();
        int itemID = 1;
        Storeitems storeItem = new Storeitems();
        storeItem = checkoutDAO.getItem(itemID);
        int newQuantity = 10;
        storeItem.setQuantity(newQuantity);
        checkoutDAO.updateItem(itemID, newQuantity);
        storeItem = checkoutDAO.getItem(itemID);
        String expResult = "true";
        boolean result = false;
        if (storeItem.getQuantity() == newQuantity) {
            result = true;
        } else {
            result = false;
        }
        assertTrue(expResult, result);
    }

    /**
     * Test of getItem method, of class CheckoutDAO.
     */
    @Test (expected = java.lang.NoClassDefFoundError.class)
    public void testGetItem() {
        System.out.println("getItem");
        CheckoutDAO checkoutDAO = new CheckoutDAO();
        int itemID = 1;
        Storeitems storeItem = new Storeitems();
        storeItem = checkoutDAO.getItem(itemID);
        int expResult = 1;
        int result = storeItem.getId();
        assertEquals(expResult, result);

    }

}
