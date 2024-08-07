/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MailModel;
import model.SendMailModel;

/**
 *
 * @author Chris
 */
@WebServlet(name = "ContactController", urlPatterns = {"/sendTheMail", "/aboutFrm"})
public class ContactController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws IOException, ServletException
    {
        String userPath = request.getServletPath();
        if(userPath.equals("/aboutFrm")){
        RequestDispatcher rd = request.getRequestDispatcher("/Pages/AboutForm/about.jsp"); //URL To Send
        rd.forward(request, response); //Forward Request
        }
        else{
            RequestDispatcher rd = request.getRequestDispatcher("/Pages/AboutForm/about.jsp");
            try
        {
            String mailMessage = request.getParameter("mailMsg");
            MailModel mMail = new MailModel();
            SendMailModel sMail = new SendMailModel(mMail);
            sMail.setModelMsg(mailMessage);
            sMail.setModelSubj(request.getParameter("mailSubj"));
            sMail.sendMailTo("chrisnmr72@gmail.com");
        }
        catch (MessagingException ex)
        {
            Logger.getLogger(ContactController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rd.forward(request, response);
        }
    }
    
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse res)  
    throws ServletException, IOException 
    {
        doPost(req, res);
    }

}
