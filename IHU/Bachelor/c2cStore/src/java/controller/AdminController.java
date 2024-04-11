package controller;
import dao.AdminDAO;
import dao.ItemViewDAO;
import dao.RegisterDAO;
import hibernateModel.User;
import hibernateModel.Categories;
import hibernateModel.Orders;
import hibernateModel.Store;
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
import model.ItemViewModel;
import model.RegisterModel;

/**
 *
 * @author Denis
 */
@WebServlet(name = "AdminController", urlPatterns = {"/ListUsersAdmin","/ListCatAdmin","/createUserAdmin","/DeleteCategoryAdmin",
                                                        "/CreateCategoryAdmin","/SaveCategoryAdmin","/UpdateCatAdmin" ,"/catInfoAdmin",
                                                      "/ListModeratorsAdmin", "/DeleteUserAdmin","/ListOrdersAdmin","/ListStoreAdmin",
                                                      "/userInfoAdmin", "/SaveAdmin","/UpdateAdmin","/ListItemsAdmin","/DeleteItemAdmin","/itemInfoAdmin",
                                                       "/DeleteStoreAdmin","/UpdateItemAdmin"})
public class AdminController extends HttpServlet {

 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)  
                throws ServletException, IOException {  
                    response.setContentType("text/html");  
                    PrintWriter out=response.getWriter();  
                    RequestDispatcher rd;
                    String action = request.getServletPath();
                    
     switch (action) {
        /* 
         case "/Delete":
             rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/DeleteUser.jsp");
             rd.forward(request, response);
             break;
         */
    //=================================CATEGORY MANAGMENT===================================     
        case "/ListCatAdmin":
              AdminDAO catDaoList= new AdminDAO();
              List<Categories> catList = (List<Categories>)catDaoList.getAllCategries();
              request.getSession().setAttribute("catList", catList);
              rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/ListCat.jsp");
              rd.forward(request, response);
             break;
        case "/DeleteCategoryAdmin":
              int userIDc = Integer.parseInt(request.getParameter("userId"));
              AdminDAO catDelDao= new AdminDAO();
              catDelDao.deleteCatById(userIDc);
              rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/DeleteCat.jsp");
              rd.forward(request, response);
             break;
        case "/CreateCategoryAdmin":
             rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/createCategory.jsp");
             rd.forward(request, response);
             break;
        
        case "/catInfoAdmin":
             String catId= request.getParameter("catId");
             Categories catById=new Categories();
             AdminDAO catInfoDao=new AdminDAO();
             catById=catInfoDao.getCatById(catId);
             request.getSession().setAttribute("catById", catById);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/catUpdate.jsp");
             rd.forward(request, response);
             break;
        case "/SaveCategoryAdmin":
                String catName=request.getParameter("catName");
                AdminDAO catDao=new AdminDAO();
                Categories cat=new Categories();
                cat.setCategoriename(catName);
                catDao.createCategory(cat);
                rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/categorySuccess.jsp");
                rd.forward(request, response);
             break; 
            case "/UpdateCatAdmin":
               int catIdForUpd= Integer.parseInt(request.getParameter("catId")); 
               String catNameForUpdate=request.getParameter("catName");
               Categories catUpdate=new Categories();
               catUpdate.setId(catIdForUpd);
               catUpdate.setCategoriename(catNameForUpdate);
               AdminDAO catUpdateDao=new AdminDAO();
               catUpdateDao.updateCat(catUpdate);
               rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/updatedsuccess.jsp");
               rd.forward(request, response);
             break;
    
     //=================================END OF CATEGORY MANAGMENT===================================          
                                                                        
    //=====================================USER MANAGMENT========================================
        
        case "/ListUsersAdmin":
              AdminDAO adminListDao= new AdminDAO();
              List<User> adminUserList = (List<User>)adminListDao.getAllUsers();
              request.getSession().setAttribute("adminUserList", adminUserList);
              rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/ListUser.jsp");
              rd.forward(request, response);
             break;
        case "/DeleteUserAdmin":
              int userIdFromPost = Integer.parseInt(request.getParameter("userId"));
              AdminDAO adminDeleteDao= new AdminDAO();
              adminDeleteDao.deleteUserById(userIdFromPost);
              rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/DeleteUser.jsp");
              rd.forward(request, response);
             break;
        case "/createUserAdmin":
             rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/createUser.jsp");
             rd.forward(request, response);
             break;
        case "/userInfoAdmin":
             String userID=request.getParameter("userId"); 
             AdminDAO adminUserDao = new AdminDAO();
             User userById = new User();
             userById=adminUserDao.getUserById(userID);
             request.getSession().setAttribute("userById", userById);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/userupdate.jsp");
             rd.forward(request, response);
             break;
        case "/ListModeratorsAdmin":
             AdminDAO listModDao= new AdminDAO();
             List<User> modList=(List<User>)listModDao.getAllModerators();
             request.getSession().setAttribute("modList", modList);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/ListModerator.jsp");
             rd.forward(request, response);
            break;
        case "/SaveAdmin":
                    RegisterModel regModel = new RegisterModel();
                    regModel.setName(request.getParameter("name"));
                    regModel.setSurname(request.getParameter("surname"));
                    regModel.setUsername(request.getParameter("username"));
                    regModel.setEmail(request.getParameter("email"));
                    regModel.setPassword(request.getParameter("password"));
                    regModel.setConfirmpassword(request.getParameter("confirmpassword"));
                    regModel.setRank(request.getParameter("rank")); 
                    regModel.setSequrityquestion(request.getParameter("question"));
                    regModel.setSecurityanswer(request.getParameter("securityanswer"));
                    regModel.setCountry(request.getParameter("country"));
                    regModel.setCity(request.getParameter("city"));
                    regModel.setState(request.getParameter("state"));
                    regModel.setAddress(request.getParameter("address"));
                    regModel.setPostalcode(request.getParameter("postalcode"));
                    regModel.setPhone(request.getParameter("phone"));
            //TODO model checks
                 User user = new User( regModel.getName(), 
                                       regModel.getSurname(),
                                       regModel.getUsername(),
                                       regModel.getRank(), 
                                       regModel.getEmail(), 
                                       regModel.getSequrityquestion(), 
                                       regModel.getSecurityanswer(),
                                       regModel.getPassword(), 
                                       regModel.getCity(), 
                                       regModel.getCountry(),
                                       regModel.getState(), 
                                       regModel.getAddress(), 
                                       regModel.getPostalcode(), 
                                       regModel.getPhone());
                    RegisterDAO registerDao = new RegisterDAO();
                    registerDao.addUser(user);
                    rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/updatedsuccess.jsp");
                    rd.forward(request, response);
             break;
        case "/UpdateAdmin":
            int userIdForUpd= Integer.parseInt(request.getParameter("userId"));
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
           //put request parameters direct to hibernate model (without model logic )    
            User userU = new User();
                 userU.setId(userIdForUpd);
                 userU.setName(name);
                 userU.setLastname(surname);
                 userU.setUsername(usernme);
                 userU.setRank(rank);
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
                      AdminDAO userUpdateDao=new AdminDAO();
                      userUpdateDao.updateUser(userU);
              
             rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/updatedsuccess.jsp");
             rd.forward(request, response);
             break;   
          //=====================================END OF USER MANAGMENT======================================== 
            
//======================================ORDER MANAGMENT=============================================   
      case "/ListOrdersAdmin":
          //TODO 
          // AdminDAO orderListDao= new AdminDAO();
             // List<Orders> orderList = (List<Orders>)orderListDao.getAllOrders();
            //  request.getSession().setAttribute("orderList", orderList);
          AdminDAO orderListDao=new AdminDAO();
          List<Orders> orderList=(List<Orders>)orderListDao.getAllOrders();
          request.getSession().setAttribute("orderList", orderList);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/ListOrders.jsp");
             rd.forward(request, response);
             break;
     
     
 //======================================END OF ORDER MANAGMENT====================================== 
 //====================================STORE MANAGMENT===============================================
       case "/ListStoreAdmin":
           AdminDAO storeListDao=new AdminDAO();
           List<Store> storeList=(List<Store>)storeListDao.getAllStores();
           request.getSession().setAttribute("storeList", storeList);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/ListStore.jsp");
             rd.forward(request, response);
             break; 
       case "/DeleteStoreAdmin":
           //FIXME 
           int storeID = Integer.parseInt(request.getParameter("storeId"));
            // AdminDAO storeDeleteDao= new AdminDAO();
            //storeDeleteDao.deleteStoreById(storeID);
             request.getSession().setAttribute("storeID", storeID);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/DeleteStore.jsp");
             rd.forward(request, response);
             break;
 //===================================END OF STORE MANAGMENT======================================== 
           
 //===================================ITEM MANAGMENT===============================================
       case "/ListItemsAdmin":
           AdminDAO itemListDao=new AdminDAO();
           List<Storeitems> itemList=(List<Storeitems>)itemListDao.getAllItems();
           request.getSession().setAttribute("itemList", itemList);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/ListItems.jsp");
             rd.forward(request, response);
             break;   
           
         case "/DeleteItemAdmin":
             int itemIdFromPost = Integer.parseInt(request.getParameter("itemId"));
             AdminDAO itemDeleteDao= new AdminDAO();
             itemDeleteDao.deleteItemById(itemIdFromPost);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/DeleteItem.jsp");
             rd.forward(request, response);
             break;
         case "/itemInfoAdmin":
             String itemID=request.getParameter("itemId"); 
             ItemViewDAO ivd=new ItemViewDAO();
             ItemViewModel ivm = new ItemViewModel();
             ivm = (ItemViewModel)ivd.getItemInfo(itemID);
             request.getSession().setAttribute("itemforview", ivm);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/itemUpdate.jsp");
             rd.forward(request, response);
             break;
        case "/UpdateItemAdmin":
            //FIXME
             int itemIdForUpd= Integer.parseInt(request.getParameter("itemId"));
            // String desc=request.getParameter("description");
             //String categoryName=request.getParameter("catName");
            // String storeName=request.getParameter("storeName");
           //ItemViewModel ivmForUpdate = new ItemViewModel();
          
            //ivmForUpdate.getItem().setDescription(desc);
           // ivmForUpdate.getItem().setId(itemIdForUpd);
           // ivmForUpdate.getItem().setCategories(categoryName);
            // ivmForUpdate.getStore().setStorename(storeName);
            //AdminDAO userUpdateDao=new AdminDAO();
              //        userUpdateDao.updateUser(userU);
             rd=request.getRequestDispatcher("/Pages/Dashboards/Admin/updatedsuccess.jsp");
             rd.forward(request, response);
             break;
            
            
 //===================================END OF ITEM MANAGMENT========================================   
   
     }//END OF SWITCH
    
     
  
 }//END OF doPost

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