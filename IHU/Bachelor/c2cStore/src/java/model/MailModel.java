package model;

public class MailModel
{
    private String uName = "chrisnmr72";
    private String passwd = "+R+I+P+R+I+P+";
    private String subj = "ModelMail";
    private String msg = "ModelMail";
    
    //Getter + Setter
    
    public void setSubject(String inSubj)
    {
        this.subj = inSubj;
    }
    
    public void setMessage(String inMsg)
    {
        this.msg = inMsg;
    }
    
    public String getUsername()
    {
        return uName;
    }
    
    public String getPassword()
    {
        return passwd;
    }
    
    public String getSubject()
    {
        return subj;
    }
    
    public String getMessage()
    {
        return msg;
    }
}
