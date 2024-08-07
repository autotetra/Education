/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.LoginDAO;
import hibernateModel.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.NullPointerException;
import org.junit.rules.ExpectedException;

/**
 *
 * @author a
 */
public class LoginDAOTest {
    
    public LoginDAOTest() {
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
     * Test of getDBUser method, of class LoginDAO.
     */
    @Test
    public void testGetDBUser() {
        System.out.println("getDBUser");
        String ungiven = "test";
        LoginDAO instance = new LoginDAO();
        User resultuser = instance.getDBUser(ungiven);
        String expResult = "1";
        String result = resultuser.getId().toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
        @Test(expected= NullPointerException.class)
    public void testGetDBUser2() {
        System.out.println("getDBUser");
        String ungiven = "";
        LoginDAO instance = new LoginDAO();
        User resultuser = instance.getDBUser(ungiven);
        String expResult = "1";
        String result = resultuser.getId().toString();
        assertEquals(expResult, result);
        
       
    }
    
}
