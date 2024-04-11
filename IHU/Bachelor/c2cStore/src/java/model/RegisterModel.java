
package model;
import hibernateModel.Storeitems;
import hibernateModel.User;
import java.util.regex.*;



public class RegisterModel {
    
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String confirmpassword;
    private String sequrityquestion;
    private String securityanswer;
    private String country;
    private String state;
    private String city;
    private String address;
    private String postalcode;
    private String phone;
    private String rank = "user";
    private User usernameValid;
    private User emailValid;
    private final String usernamePattern;
    private final String emailPattern;
    private boolean emailCheck;
    private boolean usernameCheck;
    private boolean passwordCheck;
    
    
    

    public RegisterModel() {
        this.usernamePattern = "^[a-zA-Z0-9_-]{5,15}$";
        this.emailPattern  = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    }

   
    
    public User getEmailValid() {
        return emailValid;
    }

    public void setEmailValid(User emailValid) {
        this.emailValid = emailValid;
    }

    public User getUsernameValid() {
        return usernameValid;
    }

    public void setUsernameValid(User usernameValid) {
        this.usernameValid = usernameValid;
    }
    

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getSecurityanswer() {
        return securityanswer;
    }

    public void setSecurityanswer(String securityanswer) {
        this.securityanswer = securityanswer;
    }

    public String getSequrityquestion() {
        return sequrityquestion;
    }

    public void setSequrityquestion(String sequrityquestion) {
        this.sequrityquestion = sequrityquestion;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    public boolean isEmailCheck() {
        return emailCheck;
    }

    public void setEmailCheck(boolean emailCheck) {
        this.emailCheck = emailCheck;
    }

    public boolean isUsernameCheck() {
        return usernameCheck;
    }

    public void setUsernameCheck(boolean usernameCheck) {
        this.usernameCheck = usernameCheck;
    }
    
    public boolean isPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(boolean passwordCheck) {
        this.passwordCheck = passwordCheck;
    }
        
    
    public void validateUsername(){
        Pattern up = Pattern.compile(usernamePattern);
        Matcher um = up.matcher(this.username);
        setUsernameCheck(um.matches());
    }
    public void validateEmail(){
        Pattern ep = Pattern.compile(emailPattern);
        Matcher em = ep.matcher(this.email);
        setEmailCheck(em.matches());
    }
    public void validatePassword(){
        if(this.password.equals(this.confirmpassword))
            setPasswordCheck(true);
        else
            setPasswordCheck(false);
    }    
    public boolean validate(){
        if(usernameCheck==true && emailCheck==true && passwordCheck==true ){
            return true;
        }
        else{
            return false;
        }
    }
}
