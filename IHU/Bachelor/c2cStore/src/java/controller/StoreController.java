/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
@WebServlet(name = "Store", urlPatterns = {"/Store", "/CreateStore","/Items", "/AddItemConf","/AddItem","/EditItem","/UpdateItem","/RemoveItemQ","/RemoveItem","/CancelRemove","/Sales","/GotoHomepage"})
public class StoreController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                throws ServletException, IOException {  
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
              
            User ur = (User) request.getSession().getAttribute("user");
            String URL = request.getServletPath();
            
            if(URL.equals("/Store")){
            
                StoreCheckDAO SC = new StoreCheckDAO();
                RequestDispatcher rd;
                Store store = SC.checkForStore(ur);
                if(store !=null){
                    request.getSession().setAttribute("store", store);
                    rd=request.getRequestDispatcher("/Pages/Store/Store.jsp");
                    rd.forward(request, response);
                }
                else{
                
                    rd=request.getRequestDispatcher("/Pages/Store/CreateStore.jsp");
                    rd.forward(request, response);
                
                }
                
            }
            else if(URL.equals("/CreateStore")){
            
                StoreCheckDAO SC = new StoreCheckDAO();
                if(SC.checkForStoreName(request.getParameter("storename"))){
                    
                    RequestDispatcher rd;
                    rd=request.getRequestDispatcher("/Pages/Store/Storenameexist.jsp");
                    rd.forward(request, response);
                    
                }
                else{
                
                    
                    request.getSession().setAttribute("store", SC.createStore(request.getParameter("storename"), ur));
                    
                    RequestDispatcher rd;
                    rd=request.getRequestDispatcher("/Pages/Store/Store.jsp");
                    rd.forward(request, response);
                    
                }
            
            }
            else if(URL.equals("/Items")){
                
                StoreCheckDAO SC = new StoreCheckDAO();
                List<Storeitems> si = null;
                //Storeitems item = new Storeitems();
                si=(List<Storeitems>)SC.getItems((Store)request.getSession().getAttribute("store")); 
                request.getSession().setAttribute("items", si); 
                RequestDispatcher rd;
                rd=request.getRequestDispatcher("/Pages/Store/Itemlist.jsp");
                rd.forward(request, response);
//                out.println("<!DOCTYPE html>");
//                out.println("<html>");
//                out.println("<head>");
//                out.println("<title>Servlet NewServlet</title>");            
//                out.println("</head>");
//                out.println("<body>");
//                out.println("<h1>Servlet NewServlet at " + si.get(1).getPrice() + "</h1>");
//                out.println("</body>");
//                out.println("</html>");
            
            }
            else if(URL.equals("/AddItemConf")){
                StoreCheckDAO SC = new StoreCheckDAO();
                List<Categories> categories = null;
                categories = (List<Categories>)SC.getCategories();
                request.getSession().setAttribute("categories", categories);
                RequestDispatcher rd;
                rd=request.getRequestDispatcher("/Pages/Store/AddItem.jsp");
                
                rd.forward(request, response);
            
 
            } 
            else if(URL.equals("/AddItem")){
                StoreCheckDAO SC = new StoreCheckDAO();
                SC.addItem(request.getParameter("desc"), request.getParameter("qua"),(Store)request.getSession().getAttribute("store"),request.getParameter("price"), request.getParameter("category"));
                RequestDispatcher rd;
                rd=request.getRequestDispatcher("/Pages/Store/Store.jsp");
                rd.forward(request, response);
//                out.println("<!DOCTYPE html>");
//                out.println("<html>");
//                out.println("<head>");
//                out.println("<title>Servlet NewServlet</title>");            
//                out.println("</head>");
//                out.println("<body>");
//                out.println("<h1>Servlet NewServlet at " + item.getPrice()+item.getCategories()+item.getQuantity()+item.getDescription()+item.getStore().getStorename() + "</h1>");
//                out.println("</body>");
//                out.println("</html>");
            
 
            }
            else if(URL.equals("/EditItem")){

                request.getSession().setAttribute("itemlistiteration", request.getParameter("itemlistid"));
                RequestDispatcher rd;
                rd=request.getRequestDispatcher("/Pages/Store/EditItem.jsp");
                rd.forward(request, response);
            } 
            else if(URL.equals("/UpdateItem")){
                StoreCheckDAO SC = new StoreCheckDAO();
                SC.updateItem((String)request.getParameter("upitemid"), (String)request.getParameter("newPrice"), (String)request.getParameter("newDesc"), (String)request.getParameter("newQuan"));
                RequestDispatcher rd;
                rd=request.getRequestDispatcher("/Pages/Store/Store.jsp");
                rd.forward(request, response);
            }
            
            else if(URL.equals("/RemoveItemQ")){
                request.getSession().setAttribute("itemlistiteration", request.getParameter("idforemitem"));
                RequestDispatcher rd;
                rd=request.getRequestDispatcher("/Pages/Store/RemoveItemQuestion.jsp");
                rd.forward(request, response);
            }
            else if(URL.equals("/RemoveItem")){
                StoreCheckDAO SC = new StoreCheckDAO();
                SC.delItem((String)request.getParameter("remitemid"));
                RequestDispatcher rd;
                rd=request.getRequestDispatcher("/Pages/Store/Store.jsp");
                rd.forward(request, response);
            }
            else if(URL.equals("/CancelRemove")){
                RequestDispatcher rd;
                rd=request.getRequestDispatcher("/Pages/Store/Store.jsp");
                rd.forward(request, response);
            }
            else if(URL.equals("/Sales")){
                RequestDispatcher rd;
                StoreCheckDAO SC = new StoreCheckDAO();
                Store store =(Store)request.getSession().getAttribute("store");
                List<Orders> sales = new ArrayList<Orders>();
                try{sales =(List<Orders>) SC.getStoreSales(store.getId());
                List<Storeitems> items= new ArrayList<Storeitems>();
                
                for(int i = 0;i<sales.size();i++){
                    Storeitems si = (Storeitems)SC.getItem(sales.get(i).getItemId());    
                    items.add(si);
                    request.getSession().setAttribute("itemssoldinthestore", items);
                }
                }catch(java.lang.IndexOutOfBoundsException ex){}
                
                   
                rd=request.getRequestDispatcher("/Pages/Store/Sales.jsp");
                rd.forward(request, response);
            }
            
            else if(URL.equals("/GotoHomepage")){
                
                RequestDispatcher rd;
                rd=request.getRequestDispatcher("/Pages/Dashboards/UserDashboard.jsp");
                rd.forward(request, response);
            
            }
            

    }

}
