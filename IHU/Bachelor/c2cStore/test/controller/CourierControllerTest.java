/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.pseudocontroller.PSEUDO_CourierController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class CourierControllerTest {
    
    public CourierControllerTest() {
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
     * Search orders.
     */
    @Test
    public void testDoPost1() throws Exception {
        PSEUDO_CourierController instance = new PSEUDO_CourierController();
        int result = instance.doPost("/SearchOrders");
        int expResult = 1;
        assertEquals(expResult, result);
    }

    /**
     * close orders.
     */
    @Test
    public void testDoPost2() throws Exception {
        PSEUDO_CourierController instance = new PSEUDO_CourierController();
        int result = instance.doPost("/CloseOrder");
        int expResult = 2;
        assertEquals(expResult, result);
    }
    
}
