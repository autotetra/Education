/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
public class CatalogueController extends HttpServlet {

   protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                throws ServletException, IOException {  
                    response.setContentType("text/html");  
                    PrintWriter out=response.getWriter();  
                    RequestDispatcher rd;
                    String URL = request.getServletPath();
                    
                    if(URL.equals("/Catalogue")){
                        
                        CatalogueDAO catalogue = new CatalogueDAO();
                        List<Storeitems> itemList = null;
                        itemList =(List<Storeitems>) catalogue.getItems();
                        StoreCheckDAO SC = new StoreCheckDAO();
                        List<Categories> categories = null;
                        categories = (List<Categories>)SC.getCategories();
                        request.getSession().setAttribute("categories", categories);
                        request.getSession().setAttribute("catalogueitemlist", itemList);
                        rd=request.getRequestDispatcher("/Pages/Catalogue/Catalogue.jsp");  
                        rd.forward(request, response);
                        
                    }
                    else if(URL.equals("/Showcat")){
                    
                        CatalogueDAO cd = new CatalogueDAO();
                        List<Storeitems> catlist = (List<Storeitems>)cd.getItemsbyCat(request.getParameter("catid"));
                        request.getSession().setAttribute("catlistitems", catlist);
                        rd=request.getRequestDispatcher("/Pages/Catalogue/ViewbyCat.jsp");  
                        rd.forward(request, response);
                        
                    }
            }
 
             
  

}
