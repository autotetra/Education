/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernateModel.Storeitems;
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
public class UserPanelDAOTest {
    
    public UserPanelDAOTest() {
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
     * Test of retrieveItems method, of class UserPanelDAO.
     */
    @Test (expected = java.lang.ExceptionInInitializerError.class)
    public void testRetrieveItems() {
        System.out.println("retrieveItems");
        UserPanelDAO instance = new UserPanelDAO();
        int expResult = 3;
        List<Storeitems> storeItems = instance.retrieveItems();
        int result = storeItems.size();
        assertEquals(expResult, result);
        
    }
    
}
