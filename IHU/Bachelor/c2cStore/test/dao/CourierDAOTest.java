/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernateModel.Orders;
import java.util.ArrayList;
import java.util.List;
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
public class CourierDAOTest {
    
    public CourierDAOTest() {
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
     * Test of retrieveOrders method, of class CourierDAO.
     */
    @Test (expected = java.lang.NoClassDefFoundError.class)
    public void testRetrieveOrders() {
        System.out.println("retrieveOrders");
        CourierDAO instance = new CourierDAO();
        int expResult = 0;
        List<Orders> result = instance.retrieveOrders();
        for(int i=0;i<result.size();i++){
            assertEquals(expResult, result.get(i).isDelivered());
        }
        
    }

    /**
     * Test of getOrder method, of class CourierDAO.
     */
    @Test (expected = java.lang.NoClassDefFoundError.class)
    public void testGetOrder() {
        System.out.println("getOrder");
        int ordercode = 1;
        CourierDAO instance = new CourierDAO();
        int expResult = 1;
        List<Orders> result = instance.getOrder(ordercode);
        for(int i=0;i<result.size();i++){
            assertEquals(expResult, result.get(i).getOrderCode());
        }
    }

    /**
     * Test of updateOrder method, of class CourierDAO.
     */
    @Test (expected = java.lang.ExceptionInInitializerError.class)
    public void testUpdateOrder() {
        System.out.println("updateOrder");
        int ordcode = 1;
        boolean delivered = true;
        CourierDAO instance = new CourierDAO();
        instance.updateOrder(ordcode, delivered);
        List<Orders> result = instance.getOrder(ordcode);
        int expResult = 1;
        for(int i=0;i<result.size();i++){
            assertEquals(expResult, result.get(i).getOrderCode());
        }
    }
    
    
    
}
