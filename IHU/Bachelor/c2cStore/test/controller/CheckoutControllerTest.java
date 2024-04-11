/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.pseudocontroller.PSEUDO_CheckoutController;
import hibernateModel.User;
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
public class CheckoutControllerTest {
    
    public CheckoutControllerTest() {
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
     * Checkout, User not logged In.
     */
    @Test
    public void testDoPost1() throws Exception {
        PSEUDO_CheckoutController instance = new PSEUDO_CheckoutController();
        int result = instance.doPost("sessionFalse", "/Checkout");
        int expResult = 1;
        assertEquals(expResult, result);
        
    }

    /**
     * Checkout with logged user.
     */
    @Test
    public void testDoPost2() throws Exception {
        PSEUDO_CheckoutController instance = new PSEUDO_CheckoutController();
        int result = instance.doPost("sessionTrue", "/Checkout");
        int expResult = 2;
        assertEquals(expResult, result);
        
    }
    
    /**
     * User logged in, send order to another address.
     */
    @Test
    public void testDoPost3() throws Exception {
        PSEUDO_CheckoutController instance = new PSEUDO_CheckoutController();
        int result = instance.doPost("sessionTrue", "/AnotherAddress");
        int expResult = 3;
        assertEquals(expResult, result);
    }
    
    /**
     * Complete order, not user logged in.
     */
    @Test
    public void testDoPost4() throws Exception {
        PSEUDO_CheckoutController instance = new PSEUDO_CheckoutController();
        int result = instance.doPost("sessionFalse", "/CompleteOrder");
        int expResult = 4;
        assertEquals(expResult, result);
    }
    
    /**
     * Complete order, user logged in.
     */
    @Test
    public void testDoPost5() throws Exception {
        PSEUDO_CheckoutController instance = new PSEUDO_CheckoutController();
        int result = instance.doPost("sessionTrue", "/CompleteOrder");
        int expResult = 5;
        assertEquals(expResult, result);
    }
    
    /**
     * Return to home page, user logged in.
     */
    @Test
    public void testDoPost6() throws Exception {
        PSEUDO_CheckoutController instance = new PSEUDO_CheckoutController();
        int result = instance.doPost("sessionFalse", "/Homepage");
        int expResult = 6;
        assertEquals(expResult, result);
    }
    
    /**
     * Return to home page, user not logged in.
     */
    @Test
    public void testDoPost7() throws Exception {
        PSEUDO_CheckoutController instance = new PSEUDO_CheckoutController();
        int result = instance.doPost("sessionTrue", "/Homepage");
        int expResult = 7;
        assertEquals(expResult, result);
    }
}
