/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.pseudocontroller;

import dao.CatalogueDAO;
import dao.StoreCheckDAO;
import hibernateModel.Categories;
import hibernateModel.Storeitems;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "Catalogue", urlPatterns = {"/Catalogue","/Showcat"})
public class PSEUDO_CatalogueController extends HttpServlet {

   public int doPost(String ReqURL)  
                throws ServletException, IOException {  
                    
                    String URL = ReqURL;
                    
                    if(URL.equals("/Catalogue")){
                        
                        return 1;
                        
                    }
                    else if(URL.equals("/Showcat")){
                    
                        return 2;
                        
                    }
                    
                    return 0;
            }
 
             
  

}
