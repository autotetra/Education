
package controller.pseudocontroller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.RegisterModel;

/**
 *
 * @author Chris
 */
@WebServlet(name = "RegisterControllerServlet", urlPatterns = {"/Register", "/RegisterValidate"})
public class PSEUDO_RegisterController extends HttpServlet {

    public int doPost( String name, String surname, String username, String email, String password, String confirmpassword, 
            String sequrityquestion, String securityanswer, String country, String state, String city, String address, String postalcode, String phone, String userPath)
            throws ServletException, IOException {
//               response.setContentType("text/html");  
//               PrintWriter out=response.getWriter();  
             
        
        int toreturned = 0;
        
        if (userPath.equals("/Register")) {
            //RequestDispatcher rd=request.getRequestDispatcher("/Pages/SignUp/Register.jsp");  
            //rd.forward(request, response);
            toreturned = 1; //Press Register Button
        }else if (userPath.equals("/RegisterValidate")) {
            
            RegisterModel rm = new RegisterModel();

            rm.setName(name);
            rm.setSurname(surname);
            rm.setUsername(username);
            rm.setEmail(email);
            rm.setPassword(password);
            rm.setConfirmpassword(confirmpassword);
            rm.setSequrityquestion(sequrityquestion);
            rm.setSecurityanswer(securityanswer);
            rm.setCountry(country);
            rm.setCity(city);
            rm.setState(state);
            rm.setAddress(address);
            rm.setPostalcode(postalcode);
            rm.setPhone(phone);

            rm.validateUsername();
            rm.validateEmail();
            rm.validatePassword();
            boolean status = rm.validate();

            if(status){

//                User user = new User(rm.getName(), rm.getSurname(), rm.getUsername(), rm.getEmail(), rm.getSequrityquestion(),
//                        rm.getSecurityanswer(), rm.getPassword(), rm.getCity(), rm.getCountry(), rm.getState(), rm.getAddress(), rm.getPostalcode(), rm.getPhone());
//                UserDAO userDAO = new UserDAO();
//                userDAO.addUser(user);
//
//                RequestDispatcher rd = request.getRequestDispatcher("/Pages/Dashboards/UserLoginSuccess.jsp");
//                rd.forward(request, response);
                  toreturned = 2; //Success Register
            }
            else{
                if((((rm.isUsernameCheck() == false) && (rm.isEmailCheck() == true)) && (rm.isPasswordCheck() == true))) {
//                    RequestDispatcher rd = request.getRequestDispatcher("/Pages/SignUp/WrongUsername.jsp");
//                    rd.forward(request, response);
                      toreturned = 3; //WrongUsername
                }
                else if((((rm.isUsernameCheck() == false) && (rm.isEmailCheck() == false)) && (rm.isPasswordCheck() == true))) {
//                    RequestDispatcher rd = request.getRequestDispatcher("/Pages/SignUp/WrongUsernameAndEmail.jsp");
//                    rd.forward(request, response);
                      toreturned = 4; //Wrong Username & Email
                } 
                else if((((rm.isUsernameCheck() == false) && (rm.isEmailCheck() == false)) && (rm.isPasswordCheck() == false))) {
//                    RequestDispatcher rd = request.getRequestDispatcher("/Pages/SignUp/WrongUsernameAndEmailAndPass.jsp");
//                    rd.forward(request, response);
                      toreturned = 5; //Wrong Username & Email & Pass
                } 
                else if((((rm.isUsernameCheck() == true) && (rm.isEmailCheck() == false)) && (rm.isPasswordCheck() == true))) {
//                    RequestDispatcher rd = request.getRequestDispatcher("/Pages/SignUp/WrongEmail.jsp");
//                    rd.forward(request, response);
                      toreturned = 6; //Wrong Email
                } 
                else if((((rm.isUsernameCheck() == true) && (rm.isEmailCheck() == false)) && (rm.isPasswordCheck() == false))) {
//                    RequestDispatcher rd = request.getRequestDispatcher("/Pages/SignUp/WrongEmailAndPass.jsp");
//                    rd.forward(request, response);
                      toreturned = 7; //Wrong Email & Pass
                } 
                else if((((rm.isUsernameCheck() == true) && (rm.isEmailCheck() == true)) && (rm.isPasswordCheck() == false))) {
//                    RequestDispatcher rd = request.getRequestDispatcher("/Pages/SignUp/WrongPass.jsp");
//                    rd.forward(request, response);
                      toreturned = 8; //Wrong Pass
                } 
                else if ((((rm.isUsernameCheck() == false) && (rm.isEmailCheck() == true)) && (rm.isPasswordCheck() == false))) {
//                    RequestDispatcher rd = request.getRequestDispatcher("/Pages/SignUp/WrongUsernameAndPass.jsp");
//                    rd.forward(request, response);
                      toreturned = 9; //Wrong Username & Pass
                }
            }
        }
        return toreturned;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }
}
