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

/**
 *
 * @author Chris
 */
public class SettingsControllerTest {
    
    public SettingsControllerTest() {
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
     * Test of doPost method, of class SettingsController.
     */
    @Test
    public void testDoPost1() throws Exception {
        PSEUDO_SettingsController instance = new PSEUDO_SettingsController();
        int result = instance.doPost("/Settings", 0, 0, 0);
        int expResult = 1;
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of doPost method, of class SettingsController.
     */
    @Test
    public void testDoPost2() throws Exception {
        PSEUDO_SettingsController instance = new PSEUDO_SettingsController();
        int result = instance.doPost("/EditProfile", 0, 0, 0);
        int expResult = 2;
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of doPost method, of class SettingsController.
     */
    @Test
    public void testDoPost3() throws Exception {
        PSEUDO_SettingsController instance = new PSEUDO_SettingsController();
        int result = instance.doPost("/SaveProfile", 0, 0, 0);
        int expResult = 3;
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of doPost method, of class SettingsController.
     */
    @Test
    public void testDoPost4() throws Exception {
        PSEUDO_SettingsController instance = new PSEUDO_SettingsController();
        int result = instance.doPost("/DeleteProfileSure", 1, 1, 0);
        int expResult = 4;
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of doPost method, of class SettingsController.
     */
    @Test
    public void testDoPost5() throws Exception {
        PSEUDO_SettingsController instance = new PSEUDO_SettingsController();
        int result = instance.doPost("/DeleteProfileSure", 1, 1, 1);
        int expResult = 5;
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of doPost method, of class SettingsController.
     */
    @Test
    public void testDoPost6() throws Exception {
        PSEUDO_SettingsController instance = new PSEUDO_SettingsController();
        int result = instance.doPost("/DeleteProfileSure", 1, 0, 0);
        int expResult = 6;
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of doPost method, of class SettingsController.
     */
    @Test
    public void testDoPost7() throws Exception {
        PSEUDO_SettingsController instance = new PSEUDO_SettingsController();
        int result = instance.doPost("/DeleteProfile", 0, 0, 0);
        int expResult = 7;
        assertEquals(expResult, result);
        
    }

    
    
}
