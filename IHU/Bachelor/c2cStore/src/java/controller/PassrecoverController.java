

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PasswordRecoveryDAO;
import hibernateModel.User;
import java.io.IOException;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.PassrecoverModel;

/**
 *
 * @author kiriakos
 */
@WebServlet(name = "PassrecoverController", urlPatterns = {"/passrecover", "/passwordRecoveryProccess","/getUserForPassRec"})
public class PassrecoverController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                   throws ServletException, IOException {  
               response.setContentType("text/html");  
               
                RequestDispatcher rd;
            
            String path = request.getServletPath();
            if(path.equals("/passrecover"))
            {   
                
                rd=request.getRequestDispatcher("/Pages/PassRecover/Passrecoverform.jsp");  
                rd.forward(request, response); 
            }
            if(path.equals("/getUserForPassRec")){
            
                PasswordRecoveryDAO prd = new PasswordRecoveryDAO();
                User ur= (User)prd.getDBUser(request.getParameter("username"));
                if(ur == null){
                
                    rd=request.getRequestDispatcher("/Pages/PassRecover/NoUser.jsp");  
                    rd.forward(request, response);
                
                }
                else{
            
                    request.getSession(true).setAttribute("userwhoforgotpass", ur);
                    rd=request.getRequestDispatcher("/Pages/PassRecover/Passrecoverform2.jsp");  
                    rd.forward(request, response);
                }
            
            }
            
            if(path.equals("/passwordRecoveryProccess")){
            
                PassrecoverModel prm = new PassrecoverModel();
                User ur =(User) request.getSession().getAttribute("userwhoforgotpass");
                if(prm.checkSecretQ(ur.getSecretA(), request.getParameter("secretquestion"))){
                    String newpass = prm.generatepass();
                    PasswordRecoveryDAO prd = new PasswordRecoveryDAO();
                    prd.UpdatePassword(ur.getId().toString(), newpass);
                    try {
                        prm.sendPassRecoverEmail(ur.getEmail(), newpass);
                    } catch (MessagingException ex) {
                        Logger.getLogger(PassrecoverController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    rd=request.getRequestDispatcher("/Pages/PassRecover/PasswordRecovered.jsp");  
                    rd.forward(request, response);
                }
                else{
                    rd=request.getRequestDispatcher("/Pages/PassRecover/WrongSecretQ.jsp");  
                    rd.forward(request, response);
                }
            
            }
    }
      
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        doPost(req, resp);  
    }

}

