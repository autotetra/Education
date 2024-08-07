/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.pseudocontroller.PSEUDO_LoginController;
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
public class LoginControllerTest {
    
    public LoginControllerTest() {
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

     @Test
    public void testDoPost1() throws Exception {
        PSEUDO_LoginController instance = new PSEUDO_LoginController();
        int result = instance.doPost("test","pass");
        int expResult = 1;
        assertEquals(expResult, result);
    }
    
     @Test
    public void testDoPost2() throws Exception {
        PSEUDO_LoginController instance = new PSEUDO_LoginController();
        int result = instance.doPost("test","pass2");
        int expResult = 2;
        assertEquals(expResult, result);
    }
    
     @Test
    public void testDoPost3() throws Exception {
        PSEUDO_LoginController instance = new PSEUDO_LoginController();
        int result = instance.doPost("","pass2");
        int expResult = 3;
        assertEquals(expResult, result);
    }
}
