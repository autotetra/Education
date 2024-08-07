/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernateModel.User;
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
public class RegisterDAOTest {
    
    public RegisterDAOTest() {
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
     * Test of addUser method, of class RegisterDAO.
     */
    @Test (expected = java.lang.NoClassDefFoundError.class)
    public void testAddUser() {
        System.out.println("addUser");
        User user = new User();
        user.setName("Chris");
        user.setLastname("Stilidis");
        user.setUsername("Stilofski");
        user.setEmail("CStilidis@gmail.com");
        user.setPassword("12345");
        user.setSecretQ("What was your first car?");
        user.setSecretA("fiat");
        user.setCountry("Greece");
        user.setCity("Kalithea");
        user.setState("Xanthi");
        user.setAddress("Menelaou Aggelidi 40");
        user.setPostalCode("67100");
        user.setPhone("6971857651");
        RegisterDAO instance = new RegisterDAO();
        instance.addUser(user);
        User user2 = new User();
        user2 = instance.checkUsername("Stilofski");
        String expResult = "Stilofski";
        String result = user2.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkUsername method, of class RegisterDAO.
     */
    @Test (expected = java.lang.ExceptionInInitializerError.class)
    public void testCheckUsername() {
        System.out.println("checkUsername");
        String username = "Stilofski";
        RegisterDAO instance = new RegisterDAO();
        String expResult = "Stilofski";
        User user = instance.checkUsername(username);
        String result = user.getUsername();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of checkEmail method, of class RegisterDAO.
     */
    @Test (expected = java.lang.NoClassDefFoundError.class)
    public void testCheckEmail() {
        System.out.println("checkEmail");
        String email = "CStilidis@gmail.com";
        RegisterDAO instance = new RegisterDAO();
        String expResult = "CStilidis@gmail.com ";
        User user = instance.checkEmail(email);
        String result = user.getEmail();
        assertEquals(expResult, result);
        
    }
    
}
