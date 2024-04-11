/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.pseudocontroller;

import dao.StoreCheckDAO;
import hibernateModel.Categories;
import hibernateModel.Orders;
import hibernateModel.Store;
import hibernateModel.Storeitems;
import hibernateModel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class PSEUDO_StoreController extends HttpServlet {

 
    public int doPost(String ReqURL)  
                throws ServletException, IOException {  
              
            String URL = ReqURL;
            
            if(URL.equals("/Store")){
            
                
                
                
                    return 1;
                
                
                
            }
            else if(URL.equals("/CreateStore")){
              
                    return 2;
                    
                
            
            }
            else if(URL.equals("/Items")){
                
                
                return 3;
            
            }
            else if(URL.equals("/AddItemConf")){
               
                return 4;
            
 
            } 
            else if(URL.equals("/AddItem")){
                return 5;
            
 
            }
            else if(URL.equals("/EditItem")){

                return 6;
            } 
            else if(URL.equals("/UpdateItem")){
                return 7;
            }
            
            else if(URL.equals("/RemoveItemQ")){
                return 8;
            }
            else if(URL.equals("/RemoveItem")){
                return 9;
            }
            else if(URL.equals("/CancelRemove")){
                return 10;
            }
            else if(URL.equals("/Sales")){
               return 11;
            }
            
            else if(URL.equals("/GotoHomepage")){
                
               return 12;
            
            }
            
       return 0;     
    }

}
