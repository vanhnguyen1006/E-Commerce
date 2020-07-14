package com.example.demo.entity.mail;

import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Embeddable;
import java.util.Properties;
import java.util.regex.Pattern;

@Embeddable
public class SendMail {
    final String host = "smpt.gmail.com";
    final String userSender = "0ozstarzo0@gmail.com";
    final String password = "Foreverjo10061995";
    private String msg = "<b>Gumi</b> say HELLO\"";

    private String receiverUserName = "tranbing88@gmail.com";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReceiverUserName() {
        return receiverUserName;
    }

    public void setReceiverUserName(String receiverUserName) {
        this.receiverUserName = receiverUserName;
    }

    public boolean isValidEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null){
            return false;
        }
        return pat.matcher(email).matches();
    }

    public void sendEmail() throws MessagingException {
        Properties proper = new Properties();
        proper.put("mail.smtp.host",host);
        proper.put("mail.smtp.auth", "true");
        proper.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(proper, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userSender,password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        MimeMessageHelper helper = new MimeMessageHelper(message);
        message.setFrom(new InternetAddress(userSender));
        message.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(receiverUserName)});
        message.setSubject("Gumi");
        message.setText(msg,"utf-8","html");
        Transport.send(message);
    }
}
