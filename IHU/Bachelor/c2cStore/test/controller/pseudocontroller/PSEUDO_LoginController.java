    package controller.pseudocontroller;  
    import dao.LoginDAO;
import dao.UserPanelDAO;
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
    import java.util.ArrayList;
    import java.util.List;
    
    public class PSEUDO_LoginController extends HttpServlet {  
        
        public int doPost(String name,String password)  
                throws ServletException, IOException {  
              
            User ur = new User();
            LoginDAO LU = new LoginDAO();
            LoginUserModel LUM=new LoginUserModel();
            int value = 0;
            try{
                ur = LU.getDBUser(name);
                LUM.setUser(ur);
                LUM.setPassword(password);
                String dispaddress = LUM.getRDFoward();
                RequestDispatcher rd;
                
                UserPanelDAO upd = new UserPanelDAO();
                List<Storeitems> items = null;
                items =(List<Storeitems>)upd.retrieveItems();
                if(dispaddress.equals("/Pages/Dashboards/UserDashboard.jsp")){
                    value = 1;
                }
                else {
                    value = 2;
                }
               return value;
              
                
            }
            catch(java.lang.NullPointerException ex){
                RequestDispatcher rd;
                value=3;
                return value;
            }
 
        }  
        
    //Unecesary. Kept for legacy or future development.
        @Override  
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
                throws ServletException, IOException {  
            doPost(req, resp);  
        }  
    }  
