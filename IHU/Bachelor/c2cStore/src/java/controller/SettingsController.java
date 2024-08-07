/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.RegisterDAO;
import dao.SettingsDAO;
import dao.StoreCheckDAO;
import hibernateModel.Store;
import hibernateModel.Storeitems;
import hibernateModel.User;
import java.io.IOException;
import static java.lang.System.out;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chris
 */
@WebServlet(name = "Settings", urlPatterns = {"/Settings", "/EditProfile", "/DeleteProfile", "/SaveProfile", "/DeleteProfileSure"})
public class SettingsController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String userPath = request.getServletPath();

        if (userPath.equals("/Settings")) {
            RequestDispatcher rd = request.getRequestDispatcher("/Pages/Settings/Settings.jsp");
            rd.forward(request, response);
        } else if (userPath.equals("/EditProfile")) {
            RequestDispatcher rd = request.getRequestDispatcher("/Pages/Settings/EditProfile.jsp");
            rd.forward(request, response);
        } else if (userPath.equals("/SaveProfile")) {
            User user = new User();
            user = (User) request.getSession().getAttribute("user");

            user.setName(request.getParameter("name"));
            user.setLastname(request.getParameter("surname"));
            user.setUsername(request.getParameter("username"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setSecretQ(request.getParameter("question"));
            user.setSecretA(request.getParameter("securityanswer"));
            user.setCountry(request.getParameter("country"));
            user.setCity(request.getParameter("city"));
            user.setState(request.getParameter("state"));
            user.setAddress(request.getParameter("address"));
            user.setPostalCode(request.getParameter("postalcode"));
            user.setPhone(request.getParameter("phone"));

            SettingsDAO settingsDAO = new SettingsDAO();
            settingsDAO.updateUser(user);

            RequestDispatcher rd = request.getRequestDispatcher("/Pages/Dashboards/UserDashboard.jsp");
            rd.forward(request, response);
        } else if (userPath.equals("/DeleteProfileSure")) {
            User user = (User) request.getSession().getAttribute("user");
            StoreCheckDAO storeCheckDAO = new StoreCheckDAO();
            SettingsDAO settingsDAO = new SettingsDAO();
            Store store = new Store();
            store = storeCheckDAO.checkForStore(user);

            if (store != null) {
                List<Storeitems> items = null;
                items = (List<Storeitems>) storeCheckDAO.getItems(store);
                if (items != null) {
                    for (int i = 0; i < items.size(); i++) {
                        storeCheckDAO.delItem(items.get(i).getId().toString());
                    }
                }
                settingsDAO.deleteStore(store.getId());
                settingsDAO.deleteUserById(user.getId());

            } else if (user != null) {

                settingsDAO.deleteUserById(user.getId());
            }

            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);

        } else if (userPath.equals("/DeleteProfile")) {
            RequestDispatcher rd = request.getRequestDispatcher("/Pages/Settings/DeleteProfile.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

}
