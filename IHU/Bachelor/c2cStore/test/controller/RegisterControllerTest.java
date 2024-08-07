
package controller;

import controller.pseudocontroller.PSEUDO_RegisterController;
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
public class RegisterControllerTest {
    
    public RegisterControllerTest() {
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
     * Choice Register Button from menu
     */
    @Test
    public void testDoPost1() throws Exception {
        PSEUDO_RegisterController instance = new PSEUDO_RegisterController();
        int result = instance.doPost("Chris", "Stilidis", "Stilofski", "CStilidis@gmail.com", "12345", "12345", 
                "What was your first car?", "fiat", "Greece", "Xanthi", "Eumoiro", "Xondrou 1", "67100", "6971857651", "/Register");
        int expResult = 1;
        assertEquals(expResult, result);
    }

    /**
     * Confirm Register
     */
    @Test
    public void testDoPost2() throws Exception {
        PSEUDO_RegisterController instance = new PSEUDO_RegisterController();
        int result = instance.doPost("Chris", "Stilidis", "Stilofski", "CStilidis@gmail.com", "12345", "12345", 
                "What was your first car?", "fiat", "Greece", "Xanthi", "Eumoiro", "Xondrou 1", "67100", "6971857651", "/RegisterValidate");
        int expResult = 2;
        assertEquals(expResult, result);
    }
    
    /**
     * WrongUsername
     */
    @Test
    public void testDoPost3() throws Exception {
        PSEUDO_RegisterController instance = new PSEUDO_RegisterController();
        int result = instance.doPost("Chris", "Stilidis", "St", "CStilidis@gmail.com", "12345", "12345", 
                "What was your first car?", "fiat", "Greece", "Xanthi", "Eumoiro", "Xondrou 1", "67100", "6971857651", "/RegisterValidate");
        int expResult = 3;
        assertEquals(expResult, result);
    }
    
    /**
     * Wrong Username & Email
     */
    @Test
    public void testDoPost4() throws Exception {
        PSEUDO_RegisterController instance = new PSEUDO_RegisterController();
        int result = instance.doPost("Chris", "Stilidis", "St", "CStilidis@gmailcom", "12345", "12345", 
                "What was your first car?", "fiat", "Greece", "Xanthi", "Eumoiro", "Xondrou 1", "67100", "6971857651", "/RegisterValidate");
        int expResult = 4;
        assertEquals(expResult, result);
    }
    
    /**
     * Wrong Username & Email & Pass
     */
    @Test
    public void testDoPost5() throws Exception {
        PSEUDO_RegisterController instance = new PSEUDO_RegisterController();
        int result = instance.doPost("Chris", "Stilidis", "St", "CStilidis@gmailcom", "12345", "54321", 
                "What was your first car?", "fiat", "Greece", "Xanthi", "Eumoiro", "Xondrou 1", "67100", "6971857651", "/RegisterValidate");
        int expResult = 5;
        assertEquals(expResult, result);
    }
    
    /**
     * Wrong Email
     */
    @Test
    public void testDoPost6() throws Exception {
        PSEUDO_RegisterController instance = new PSEUDO_RegisterController();
        int result = instance.doPost("Chris", "Stilidis", "Stilofski", "CStilidis@gmailcom", "12345", "12345", 
                "What was your first car?", "fiat", "Greece", "Xanthi", "Eumoiro", "Xondrou 1", "67100", "6971857651", "/RegisterValidate");
        int expResult = 6;
        assertEquals(expResult, result);
    }
    
    /**
     * Wrong Email & Pass
     */
    @Test
    public void testDoPost7() throws Exception {
        PSEUDO_RegisterController instance = new PSEUDO_RegisterController();
        int result = instance.doPost("Chris", "Stilidis", "Stilofski", "CStilidis@gmailcom", "12345", "54321", 
                "What was your first car?", "fiat", "Greece", "Xanthi", "Eumoiro", "Xondrou 1", "67100", "6971857651", "/RegisterValidate");
        int expResult = 7;
        assertEquals(expResult, result);
    }
    
    /**
     * Wrong Pass
     */
    @Test
    public void testDoPost8() throws Exception {
        PSEUDO_RegisterController instance = new PSEUDO_RegisterController();
        int result = instance.doPost("Chris", "Stilidis", "Stilofski", "CStilidis@gmail.com", "12345", "54321", 
                "What was your first car?", "fiat", "Greece", "Xanthi", "Eumoiro", "Xondrou 1", "67100", "6971857651", "/RegisterValidate");
        int expResult = 8;
        assertEquals(expResult, result);
    }
    
    /**
     * Wrong Username & Pass
     */
    @Test
    public void testDoPost9() throws Exception {
        PSEUDO_RegisterController instance = new PSEUDO_RegisterController();
        int result = instance.doPost("Chris", "Stilidis", "St", "CStilidis@gmail.com", "12345", "54321", 
                "What was your first car?", "fiat", "Greece", "Xanthi", "Eumoiro", "Xondrou 1", "67100", "6971857651", "/RegisterValidate");
        int expResult = 9;
        assertEquals(expResult, result);
    }
    
}
