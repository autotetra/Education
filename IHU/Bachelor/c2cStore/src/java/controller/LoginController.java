    package controller;  
import dao.CourierDAO;
    import dao.LoginDAO;
import dao.UserPanelDAO;
import hibernateModel.Orders;
import hibernateModel.Storeitems;
    import model.LoginUserModel;
    import java.io.IOException;  
    import java.io.PrintWriter;  
    import javax.servlet.RequestDispatcher;  
    import javax.servlet.ServletException;  
    import javax.servlet.http.HttpServlet;  
    import javax.servlet.http.HttpServletRequest;  
    import javax.servlet.http.HttpServletResponse; 
    //import hibernateUtis.LoginUtil;
    import hibernateModel.User;
import java.util.List;
    
    public class LoginController extends HttpServlet {  
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                throws ServletException, IOException {  
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
              
            String name=request.getParameter("name");  
            String password=request.getParameter("password");  
             
            User ur = new User();
            LoginDAO LU = new LoginDAO();
            LoginUserModel LUM=new LoginUserModel();
            try{
                ur = LU.getDBUser(name);
                LUM.setUser(ur);
                LUM.setPassword(password);
                String dispaddress = LUM.getRDFoward();
                RequestDispatcher rd;
                rd=request.getRequestDispatcher(dispaddress);  
                request.getSession(true).setAttribute("user", ur);//STARTS A SESSION
                
                UserPanelDAO upd = new UserPanelDAO();
                List<Storeitems> items = null;
                items =(List<Storeitems>)upd.retrieveItems();
                request.getSession().setAttribute("randomitems", items);
                CourierDAO cd= new CourierDAO();
                List<Orders> orders = null;
                orders = (List<Orders>)cd.retrieveOrders();
                request.getSession().setAttribute("allOrders", orders);
                
                rd.forward(request, response);
              
            }
            catch(java.lang.NullPointerException ex){
                RequestDispatcher rd;
                rd=request.getRequestDispatcher("/Pages/Login/WrongName.jsp");  
                rd.forward(request, response);
            }
 
        }  
        
    //Unecesary. Kept for legacy or future development.
        @Override  
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
                throws ServletException, IOException {  
            doPost(req, resp);  
        }  
    }  
