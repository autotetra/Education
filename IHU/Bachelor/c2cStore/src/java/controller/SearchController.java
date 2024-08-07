package controller;

import dao.SearchDAO;
import hibernateModel.Storeitems;
import java.util.List;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "searchController", urlPatterns = {"/Search"})
public class SearchController extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws IOException, ServletException
    {
        if(request.getParameter("searchKey").equals(""))
        {
            RequestDispatcher rd = request.getRequestDispatcher("/Pages/SearchItem/noItemFound.jsp"); //URL To Send
            rd.forward(request, response); //Forward Request
        }
        else if (request.getParameter("searchKey").length() < 2)
        {
            RequestDispatcher rd = request.getRequestDispatcher("/Pages/SearchItem/keySmall.jsp"); //URL To Send
            rd.forward(request, response); //Forward Request
        }
        else
        {
            String searchKeyword = request.getParameter("searchKey");
            SearchDAO sD = new SearchDAO();
            try
            {
                List<Storeitems> searchResultItems = sD.getItems(searchKeyword);
                request.getSession().setAttribute("searchResultList", searchResultItems);
                RequestDispatcher rd = request.getRequestDispatcher("/Pages/SearchItem/showFoundItems.jsp"); //URL To Send
                rd.forward(request, response); //Forward Request
            }
            catch (java.lang.NullPointerException ex)
            {
                //Exception
            }
        }
    }
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse res)  
    throws ServletException, IOException 
    {
        doPost(req, res);
    }
}
