    package model; 

    import hibernateModel.User;
    
    public class LoginUserModel {  
        private String name, password, rank;
        private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
         
    public String getRDFoward(){
    
        if(user.getPassword().equals(password)){
        
            if(user.getRank().equals("user")){
                return "/Pages/Dashboards/UserDashboard.jsp";
            }
            else if(user.getRank().equals("moderator")){
                return "/Pages/Dashboards/ModeratorDashboard.jsp";
            }
            else if(user.getRank().equals("courier")){
                return "/Pages/ACS/Courier.jsp";
            }
            else{
                return "/Pages/Dashboards/SuperUserDashboard.jsp";
            }
        }
        else{
            return "/Pages/Login/WrongPass.jsp";
        }
    
    }
          
}  
