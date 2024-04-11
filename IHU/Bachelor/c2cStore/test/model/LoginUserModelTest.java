/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import hibernateModel.User;
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
public class LoginUserModelTest {
    
    public LoginUserModelTest() {
    }
    LoginUserModel instance = new LoginUserModel();
    User user = new User();
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
         
    }
    
    @Before
    public void setUp() {
        instance.setName("test");
        instance.setPassword("pass");

        
       
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class LoginUserModel.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "test";
        String result = instance.getName();
        assertEquals(expResult, result);
        
        
    }

   
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String expResult = "pass";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        
        
    }

   

   
    @Test
    public void testGetRank() {
        System.out.println("getRank");
        String expResult = null;
        String result = instance.getRank();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

   
    @Test
    public void testSetRank() {
        System.out.println("setRank");
        String rank = "";
        LoginUserModel instance = new LoginUserModel();
        instance.setRank(rank);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        LoginUserModel instance = new LoginUserModel();
        User expResult = null;
        User result = instance.getUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        User user = null;
        LoginUserModel instance = new LoginUserModel();
        instance.setUser(user);
       
    }

   
    @Test
    public void testGetRDFoward1() {
        System.out.println("getRDFoward1");
        String expResult = "/Pages/Dashboards/UserDashboard.jsp";
        user.setRank("user");
        user.setPassword("pass");
        instance.setUser(user);
        String result = instance.getRDFoward();
        assertEquals(expResult, result);
        
        
    }
    
    
    @Test
    public void testGetRDFoward2() {
        System.out.println("getRDFoward2");
        String expResult = "/Pages/Login/WrongPass.jsp";
        user.setRank("user");
        user.setPassword("pass2");
        instance.setUser(user);
        String result = instance.getRDFoward();
        assertEquals(expResult, result);
        
        
    }
    
        @Test
    public void testGetRDFoward3() {
        System.out.println("getRDFoward3");
        String expResult = "/Pages/Dashboards/ModeratorDashboard.jsp";
        user.setRank("moderator");
        user.setPassword("pass");
        instance.setUser(user);
        String result = instance.getRDFoward();
        assertEquals(expResult, result);
        
        
    }
    
        @Test
    public void testGetRDFoward4() {
        System.out.println("getRDFoward4");
        String expResult = "/Pages/Dashboards/SuperUserDashboard.jsp";
        user.setRank("empty");
        user.setPassword("pass");
        instance.setUser(user);
        String result = instance.getRDFoward();
        assertEquals(expResult, result);
    }
    
}
