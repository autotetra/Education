/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import controller.pseudocontroller.PSEUDO_CatalogueController;
/**
 *
 * @author a
 */
public class CatalogueControllerTest {
    
    public CatalogueControllerTest() {
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
        PSEUDO_CatalogueController instance = new PSEUDO_CatalogueController();
        int result = instance.doPost("/Catalogue");
        int expResult = 1;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDoPost2() throws Exception {
        PSEUDO_CatalogueController instance = new PSEUDO_CatalogueController();
        int result = instance.doPost("/Showcat");
        int expResult = 2;
        assertEquals(expResult, result);
    }
    
        @Test
    public void testDoPost0() throws Exception {
        PSEUDO_CatalogueController instance = new PSEUDO_CatalogueController();
        int result = instance.doPost("");
        int expResult = 0;
        assertEquals(expResult, result);
    }
}
