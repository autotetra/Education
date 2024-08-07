/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CartDAO;
import dao.ItemViewDAO;
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
import model.ItemViewModel;

/**
 *
 * @author a
 */
@WebServlet(name = "ItemView", urlPatterns = {"/Viewitem"})
public class ItemViewController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                            throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        RequestDispatcher rd;
        String URL = request.getServletPath();

        if(URL.equals("/Viewitem")){
            ItemViewDAO ivd = new ItemViewDAO();
            ItemViewModel ivm = new ItemViewModel();
            ivm = (ItemViewModel)ivd.getItemInfo(request.getParameter("itemid"));
            //out.print(ivm.getCat().getCategoriename()+ivm.getItem().getDescription()+ivm.getStore().getStorename()+ivm.getUser().getLastname());
            request.getSession().setAttribute("itemforview", ivm);
            rd=request.getRequestDispatcher("/Pages/ItemView/ItemView.jsp");
            rd.forward(request, response);
        }
    }
}
