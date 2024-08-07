/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.pseudocontroller;

import dao.CheckoutDAO;
import hibernateModel.Orders;
import hibernateModel.Storeitems;
import hibernateModel.User;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CartItemModel;

/**
 *
 * @author Chris
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/Checkout", "/CompleteOrder", "/AnotherAddress", "/Homepage", "/Indexx"})
public class PSEUDO_CheckoutController extends HttpServlet {

    public int doPost(String session, String userPath)
            throws ServletException, IOException {
        //response.setContentType("text/html");

        //String userPath = request.getServletPath();
        int toreturned = 0;
        //HttpSession session = request.getSession(false);

        if (userPath.equals("/Checkout")) {
            if (session == "sessionFalse") {
                toreturned = 1;
//                User is not logged in.  
//                RequestDispatcher rd = request.getRequestDispatcher("/Pages/Checkout/NotLoggedIn.jsp");
//                rd.forward(request, response);
            } else {
                toreturned = 2;
//                User IS logged in.  
//                RequestDispatcher rd = request.getRequestDispatcher("/Pages/Checkout/LoggedIn.jsp");
//                rd.forward(request, response);
            }
        } else if (userPath.equals("/AnotherAddress")) {
            toreturned = 3;
//            RequestDispatcher rd = request.getRequestDispatcher("/Pages/Checkout/NotLoggedIn.jsp");
//            rd.forward(request, response);
        } else if (userPath.equals("/CompleteOrder")) {
            
//            List<CartItemModel> itemList = (List<CartItemModel>) request.getSession().getAttribute("cartlist");
//            CheckoutDAO cd = new CheckoutDAO();
//            int counter = itemList.size();
//            Random randomGenerator = new Random();
//            int rndNumber = randomGenerator.nextInt(100);
//            Storeitems item = new Storeitems();
//            Orders order = new Orders();
//            for (int i = 0; i < counter; i++) {
//
//                item = cd.getItem(itemList.get(i).getSi().getId());
//                int quantity = item.getQuantity();
//                int newQuantity = quantity - itemList.get(i).getQua();
//                item.setQuantity(newQuantity);
//                cd.updateItem(item.getId(),newQuantity);//update done
//                
//                order.setOrderCode(rndNumber);
//                order.setQuantity(itemList.get(i).getQua());
//                order.setItemId(itemList.get(i).getSi().getId());
//                order.setStoreId(itemList.get(i).getSi().getStore().getId());
//                order.setDelivered(false);
//                User user = (User) request.getSession().getAttribute("user");
                  if (session == "sessionFalse") {
                    toreturned = 4;
                //  order.setUserId(0);
                  } else {
                    toreturned = 5;
                //  order.setUserId(user.getId());
                  }
//            cd.addOrder(order);//order done
//
//            }
//
//            RequestDispatcher rd = request.getRequestDispatcher("/Pages/Checkout/CompleteOrder.jsp");
//            rd.forward(request, response);
        } else if (userPath.equals("/Homepage")) {
            if (session == "sessionFalse") {
                toreturned = 6;
                // User is not logged in.  
//                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//                rd.forward(request, response);
            } else {
                toreturned = 7;
                // User IS logged in.  
//                RequestDispatcher rd = request.getRequestDispatcher("/Pages/Dashboards/UserDashboard.jsp");
//                rd.forward(request, response);
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
