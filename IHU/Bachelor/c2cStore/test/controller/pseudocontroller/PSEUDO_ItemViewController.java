/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.pseudocontroller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


/**
 *
 * @author a
 */
@WebServlet(name = "ItemView", urlPatterns = {"/Viewitem"})
public class PSEUDO_ItemViewController extends HttpServlet {
    public int doPost(String ReqURL)  
                            throws ServletException, IOException {  
        
        String URL = ReqURL;

        if(URL.equals("/Viewitem")){
            return 1;
        }
        
        return 0;
    }
}

