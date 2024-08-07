/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CartDAO;
import dao.CatalogueDAO;
import hibernateModel.Storeitems;
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
import model.CartItemModel;
/**
 *
 * @author Admin
 */
@WebServlet(name = "CartController", urlPatterns = {"/Cart","/Additem"})
public class CartController extends HttpServlet {
protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                throws ServletException, IOException {  
                    response.setContentType("text/html");  
                    PrintWriter out=response.getWriter();  
                    RequestDispatcher rd;
                    String URL = request.getServletPath();
                    
                    if(URL.equals("/Additem")){
                    
                        CartDAO itemDB = new CartDAO();
                        Storeitems item = itemDB.getItem(request.getParameter("itemid"));
                        int quan = Integer.parseInt(request.getParameter("quantity"));
                        List<CartItemModel> cartlist = (List<CartItemModel>)request.getSession().getAttribute("cartlist");
                        
                       
//                out.println("<!DOCTYPE html>");
//                out.println("<html>");
//                out.println("<head>");
//                out.println("<title>Servlet NewServlet</title>");            
//                out.println("</head>");
//                out.println("<body>");
//                out.println("<h1>Servlet NewServlet at " + item.getPrice() + "</h1>");
//                out.println("</body>");
//                out.println("</html>");
                        if(cartlist==null){
                            cartlist = new ArrayList<CartItemModel>();
                        }
                        CartItemModel newItem = new CartItemModel(item, quan);
                        cartlist.add(newItem);
                        request.getSession().setAttribute("cartlist", cartlist);
                        rd=request.getRequestDispatcher("/Pages/Catalogue/Catalogue.jsp");
                        rd.forward(request, response);
//                        out.println("<!DOCTYPE html>");
//                        out.println("<html>");
//                        out.println("<head>");
//                        out.println("<title>Servlet NewServlet</title>");            
//                        out.println("</head>");
//                        out.println("<body>");
//                        out.println("<h1>Servlet NewServlet at " + item.getDescription() + "</h1>");
//                        out.println("</body>");
//                        out.println("</html>");
                    }
                    else if(URL.equals("/Cart")){
                    
                        rd=request.getRequestDispatcher("/Pages/Cart/Cart.jsp");
                        rd.forward(request, response);
                        
                    }
            }
}
