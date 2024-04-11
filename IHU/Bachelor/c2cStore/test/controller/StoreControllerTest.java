/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.pseudocontroller.PSEUDO_StoreController;
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
public class StoreControllerTest {
    
    public StoreControllerTest() {
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
        PSEUDO_StoreController instance = new PSEUDO_StoreController();
        int result = instance.doPost("/Store");
        int expResult = 1;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDoPost2() throws Exception {
        PSEUDO_StoreController instance = new PSEUDO_StoreController();
        int result = instance.doPost("/CreateStore");
        int expResult = 2;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDoPost3() throws Exception {
        PSEUDO_StoreController instance = new PSEUDO_StoreController();
        int result = instance.doPost("/Items");
        int expResult = 3;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDoPost4() throws Exception {
        PSEUDO_StoreController instance = new PSEUDO_StoreController();
        int result = instance.doPost("/AddItemConf");
        int expResult = 4;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDoPost5() throws Exception {
        PSEUDO_StoreController instance = new PSEUDO_StoreController();
        int result = instance.doPost("/AddItem");
        int expResult = 5;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDoPost6() throws Exception {
        PSEUDO_StoreController instance = new PSEUDO_StoreController();
        int result = instance.doPost("/EditItem");
        int expResult = 6;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDoPost7() throws Exception {
        PSEUDO_StoreController instance = new PSEUDO_StoreController();
        int result = instance.doPost("/UpdateItem");
        int expResult = 7;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDoPost8() throws Exception {
        PSEUDO_StoreController instance = new PSEUDO_StoreController();
        int result = instance.doPost("/RemoveItemQ");
        int expResult = 8;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDoPost9() throws Exception {
        PSEUDO_StoreController instance = new PSEUDO_StoreController();
        int result = instance.doPost("/RemoveItem");
        int expResult = 9;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDoPost10() throws Exception {
        PSEUDO_StoreController instance = new PSEUDO_StoreController();
        int result = instance.doPost("/CancelRemove");
        int expResult = 10;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDoPost11() throws Exception {
        PSEUDO_StoreController instance = new PSEUDO_StoreController();
        int result = instance.doPost("/Sales");
        int expResult = 11;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDoPost12() throws Exception {
        PSEUDO_StoreController instance = new PSEUDO_StoreController();
        int result = instance.doPost("/GotoHomepage");
        int expResult = 12;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDoPost0() throws Exception {
        PSEUDO_StoreController instance = new PSEUDO_StoreController();
        int result = instance.doPost("sweetroll");
        int expResult = 0;
        assertEquals(expResult, result);
    }
}
