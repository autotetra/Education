
package controller;
import dao.ModeratorDAO;
import dao.RegisterDAO;
import hibernateModel.Categories;
import hibernateModel.Store;
import hibernateModel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RegisterModel;

@WebServlet(name = "ModeratorController", urlPatterns = {"/ListUsers",     "/CreateUser",   "/DeleteUser",     "/userInfo",    "/StoreList","/StoreInfo",
                                                        "/CreateCategory", "/SaveCategory", "/DeleteCategory", "/ViewCategory", "/Save","/Update"})
public class ModeratorController extends HttpServlet {

 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                throws ServletException, IOException {  
                    response.setContentType("text/html");  
                    PrintWriter out=response.getWriter();  
                    RequestDispatcher rd;
                    String URL = request.getServletPath();
                    
     switch (URL) {
          case "/ViewCategory":
            ModeratorDAO categoryList= new ModeratorDAO();
              List<Categories> ViewList = (List<Categories>)categoryList.getAllCategries();
             request.getSession().setAttribute("ViewList", ViewList);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Moderator/ViewCategory.jsp");
             rd.forward(request, response);
             break;
         case "/DeleteCategory":
              int userIDc = Integer.parseInt(request.getParameter("userId"));
              ModeratorDAO catDelDao= new ModeratorDAO();
              catDelDao.deleteCatById(userIDc);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Moderator/DeleteCategory.jsp");
             rd.forward(request, response);
             break;
             
         case "/CreateCategory":
             rd=request.getRequestDispatcher("/Pages/Dashboards/Moderator/CreateCategory.jsp");
             rd.forward(request, response);
             break;
       case "/SaveCategory":
            String catName=request.getParameter("categoryName");
            ModeratorDAO catDao=new ModeratorDAO();
             Categories cat=new Categories();
             cat.setCategoriename(catName);
             catDao.createCategory(cat);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Moderator/CategoryCreated.jsp");
             rd.forward(request, response);
             break;   
         case "/ListUsers":
              ModeratorDAO MD= new ModeratorDAO();
              List<User> modUserList = (List<User>)MD.getAllUsers();
             request.getSession().setAttribute("modUserList", modUserList);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Moderator/ListUsers.jsp");
             rd.forward(request, response);
              break;
         case "/Delete":
             rd=request.getRequestDispatcher("/Pages/Dashboards/Moderator/DeleteUser.jsp");
             rd.forward(request, response);
             break;
             
             case "/StoreList":
           ModeratorDAO storeListDao=new ModeratorDAO();
           List<Store> storeList=(List<Store>)storeListDao.getAllStores();
           request.getSession().setAttribute("StoreList", storeList);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Moderator/StoreList.jsp");
             rd.forward(request, response);
             break; 
         
        
         case "/DeleteUser":
             int userIdFromPost = Integer.parseInt(request.getParameter("userId"));
              ModeratorDAO admDao= new ModeratorDAO();
             admDao.deleteUserById(userIdFromPost);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Moderator/DeleteUser.jsp");
             rd.forward(request, response);
             break;
             //create user input page
         case "/CreateUser":
             rd=request.getRequestDispatcher("/Pages/Dashboards/Moderator/CreateUser.jsp");
             rd.forward(request, response);
             break;
         case "/userInfo":
             String userID=request.getParameter("userId"); 
             ModeratorDAO MD1 = new ModeratorDAO();
             User userById = new User();
             userById=MD1.getUserById(userID);
             request.getSession().setAttribute("userById", userById);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Moderator/UserUpdate.jsp");
             rd.forward(request, response);
             break;
             
         case "/StoreInfo":
            String storeID=request.getParameter("storeId"); 
             ModeratorDAO MD4 = new ModeratorDAO();
             User storeById = new User();
            userById=MD4.getUserById(storeID);
            request.getSession().setAttribute("storeById", storeById);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Moderator/StoreUpdated.jsp");
             rd.forward(request, response);
             break;
        
          case "/Save":
                RegisterModel rm = new RegisterModel();

            rm.setName(request.getParameter("name"));
            rm.setSurname(request.getParameter("surname"));
            rm.setUsername(request.getParameter("username"));
            rm.setEmail(request.getParameter("email"));
            rm.setPassword(request.getParameter("password"));
            rm.setConfirmpassword(request.getParameter("confirmpassword"));
            //rm.setRank(request.getParameter("rank")); 
            rm.setSequrityquestion(request.getParameter("question"));
            rm.setSecurityanswer(request.getParameter("securityanswer"));
            rm.setCountry(request.getParameter("country"));
            rm.setCity(request.getParameter("city"));
            rm.setState(request.getParameter("state"));
            rm.setAddress(request.getParameter("address"));
            rm.setPostalcode(request.getParameter("postalcode"));
            rm.setPhone(request.getParameter("phone"));
            
                 User user = new User( rm.getName(), 
                                       rm.getSurname(),
                                       rm.getUsername(),
                                       rm.getRank(), 
                                       rm.getEmail(), 
                                       rm.getSequrityquestion(), 
                                       rm.getSecurityanswer(),
                                       rm.getPassword(), 
                                       rm.getCity(), 
                                       rm.getCountry(),
                                       rm.getState(), 
                                       rm.getAddress(), 
                                       rm.getPostalcode(), 
                                       rm.getPhone());
                    RegisterDAO userDAO = new RegisterDAO();
                    userDAO.addUser(user);
             rd=request.getRequestDispatcher("/Pages/Dashboards/ModeratorDashboard.jsp");
             rd.forward(request, response);
             break;
              
       case "/Update":
             int userId= Integer.parseInt(request.getParameter("userId"));
            String name=request.getParameter("name");
            String surname=request.getParameter("surname");
            String usernme=request.getParameter("username");
            String email =request.getParameter("email");
            String pass= request.getParameter("password");
            String rank=request.getParameter("rank"); 
            String question=request.getParameter("question");
            String security=request.getParameter("securityanswer");
            String coutry=request.getParameter("country");
            String city=request.getParameter("city");
            String state=request.getParameter("state");
            String address=request.getParameter("address");
            String postcode=request.getParameter("postalcode");
            String phone=request.getParameter("phone");
            String role="user";
            
                 User userU = new User();
                 
                 userU.setId(userId);
                 userU.setName(name);
                 userU.setLastname(surname);
                 userU.setUsername(usernme);
                 userU.setRank(role);
                 userU.setEmail(email);
                 userU.setSecretQ(question);
                 userU.setSecretA(security);                 
                 userU.setPassword(pass);
                 userU.setCountry(coutry);
                 userU.setCity(city);
                 userU.setState(state);
                 userU.setAddress(address);
                 userU.setPostalCode(postcode);
                 userU.setPhone(phone);
                      ModeratorDAO modDAO=new ModeratorDAO();
                      modDAO.updateUser(userU);
              
             rd=request.getRequestDispatcher("/Pages/Dashboards/Moderator/UpdateSuccess.jsp");
             rd.forward(request, response);
             break;        
     }
 }

 @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        doPost(req, resp);  
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}