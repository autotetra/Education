
package model;

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
public class RegisterModelTest {
    
    public RegisterModelTest() {
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
     * Test of validateUsername method, of class RegisterModel.
     */
    @Test
    public void testValidateUsername1() {
        String validUserNames[] = {"Chris34", "Chris_2002","Chris-2002" ,"Ch3-4_ris"};
        RegisterModel instance = new RegisterModel();
        Boolean expResult = true;
        for(int i=0;i<validUserNames.length;i++){
            instance.setUsername(validUserNames[i]);
            instance.validateUsername();
            boolean result = instance.isUsernameCheck();
            assertEquals(expResult, result);
        }
    }
    /**
     * Test of validateUsername method, of class RegisterModel.
     */
    @Test
    public void testValidateUsername2() {
        String notValidUserNames[] = {"Ch", "Ch@chris","Chris123456789-_"};
        RegisterModel instance = new RegisterModel();
        boolean expResult = false;
        for(int i=0;i<notValidUserNames.length;i++){
            instance.setUsername(notValidUserNames[i]);
            instance.validateUsername();
            boolean result = instance.isUsernameCheck();
            assertEquals(expResult, result);
        }
    }
    /**
     * Test of validateEmail method, of class RegisterModel.
     */
    @Test
    public void testValidateEmail1() {
        String validEmails[] = {"ch@gmail.com", "ch@ch.co.uk", "ch@yahoo.com", 
                                "ch-100@ch.com", "ch.100@ch.com", "ch-100@ch.net",
                                "ch.100@ch.com.au", "ch@1.com", "ch+100@gmail.com",
                                "ch-100@yahoo-test.com"};
        RegisterModel instance = new RegisterModel();
        boolean expResult = true;
        for(int i=0;i<validEmails.length;i++){
            instance.setEmail(validEmails[i]);
            instance.validateEmail();
            boolean result = instance.isEmailCheck();
            assertEquals(expResult, result);
        }
    }
    /**
     * Test of validateEmail method, of class RegisterModel.
     */
    @Test
    public void testValidateEmail2() {
        String notValidEmails[] = {"ch@.com.my", "ch123@.com", "ch123@.com.com", 
                                   "ch@%*.com", "ch@ch@gmail.com", "ch@gmail.com.1a"};
        RegisterModel instance = new RegisterModel();
        boolean expResult = false;
        for(int i=0;i<notValidEmails.length;i++){
            instance.setEmail(notValidEmails[i]);
            instance.validateEmail();
            boolean result = instance.isEmailCheck();
            assertEquals(expResult, result);
        }
    }
    

    /**
     * Test of validatePassword method, of class RegisterModel.
     */
    @Test
    public void testValidatePassword1() {
        String password = "123";
        String confirmPassword = "123";
        RegisterModel instance = new RegisterModel();
        instance.setPassword(password);
        instance.setConfirmpassword(confirmPassword);
        instance.validatePassword();
        boolean expResult = true;
        boolean result = instance.isPasswordCheck();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validatePassword method, of class RegisterModel.
     */
    @Test
    public void testValidatePassword2() {
        String password = "123";
        String confirmPassword = "321";
        RegisterModel instance = new RegisterModel();
        instance.setPassword(password);
        instance.setConfirmpassword(confirmPassword);
        instance.validatePassword();
        boolean expResult = false;
        boolean result = instance.isPasswordCheck();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of validate method, of class RegisterModel.
     */
    @Test
    public void testValidate1(){
        
       RegisterModel instance = new RegisterModel();
       boolean result,expResult = true;
       instance.setUsername("Chris123");
       instance.setEmail("CStilidis@gmail.com");
       instance.setPassword("12345");
       instance.setConfirmpassword("12345");
       instance.validateUsername();
       instance.validateEmail();
       instance.validatePassword();
       if(instance.isUsernameCheck()==true && instance.isEmailCheck() && instance.isPasswordCheck()){
           result = true;
       }
       else{
           result = false;
       }
       assertEquals(expResult, result);
        
    }
    /**
     * Test of validate method, of class RegisterModel.
     */
    @Test
    public void testValidate2(){
       RegisterModel instance = new RegisterModel();
       boolean result,expResult = false;
       instance.setUsername("Ch");
       instance.setEmail("CStilidis@gmail.com");
       instance.setPassword("12345");
       instance.setConfirmpassword("12345");
       instance.validateUsername();
       instance.validateEmail();
       instance.validatePassword();
       if(instance.isUsernameCheck()==true && instance.isEmailCheck() && instance.isPasswordCheck()){
           result = true;
       }
       else{
           result = false;
       }
       assertEquals(expResult, result);
        
    }
    /**
     * Test of validate method, of class RegisterModel.
     */
    @Test
    public void testValidate3(){
        
       RegisterModel instance = new RegisterModel();
       boolean result,expResult = false;
       instance.setUsername("Chris123");
       instance.setEmail("ch@ch@gmail.com");
       instance.setPassword("12345");
       instance.setConfirmpassword("12345");
       instance.validateUsername();
       instance.validateEmail();
       instance.validatePassword();
       if(instance.isUsernameCheck()==true && instance.isEmailCheck() && instance.isPasswordCheck()){
           result = true;
       }
       else{
           result = false;
       }
       assertEquals(expResult, result);
        
    }
    /**
     * Test of validate method, of class RegisterModel.
     */
    @Test
    public void testValidate4(){
       
       RegisterModel instance = new RegisterModel();
       boolean result,expResult = false;
       instance.setUsername("Chris123");
       instance.setEmail("CStilidis@gmail.com");
       instance.setPassword("12345");
       instance.setConfirmpassword("54321");
       instance.validateUsername();
       instance.validateEmail();
       instance.validatePassword();
       if(instance.isUsernameCheck()==true && instance.isEmailCheck() && instance.isPasswordCheck()){
           result = true;
       }
       else{
           result = false;
       }
       assertEquals(expResult, result);
        
    }
    
}
