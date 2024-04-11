package model;

import com.sun.mail.smtp.SMTPTransport;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.MailModel;
//CLASS SET MAIL SENDING PROPERTIES
public class SendMailModel
{
    MailModel sm = new MailModel();
    public SendMailModel(MailModel inSm)
    {
        this.sm = inSm;
    }
    
    public void sendMailTo(String recipient)
    throws MessagingException
    {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        
        Session session = Session.getInstance(props, null);
        final MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(sm.getUsername()+"@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient, false));
        msg.setSubject(sm.getSubject());
        msg.setText(sm.getMessage(), "utf-8");
        msg.setSentDate(new Date());
        SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
        t.connect("smtp.gmail.com", sm.getUsername(), sm.getPassword());
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
    }
    
    public void setModelSubj(String subj)
    {
        sm.setSubject(subj);
    }
    
    public void setModelMsg(String inMsg)
    {
        sm.setMessage(inMsg);
    }
}
