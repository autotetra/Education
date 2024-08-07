/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.pseudocontroller.PSEUDO_ItemViewController;
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
public class ItemViewControllerTest {
    
    public ItemViewControllerTest() {
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
        PSEUDO_ItemViewController instance = new PSEUDO_ItemViewController();
        int result = instance.doPost("/Viewitem");
        int expResult = 1;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDoPost0() throws Exception {
        PSEUDO_ItemViewController instance = new PSEUDO_ItemViewController();
        int result = instance.doPost("");
        int expResult = 0;
        assertEquals(expResult, result);
    }
}
