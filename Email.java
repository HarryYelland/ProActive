import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {
    //Reference from: https://www.genuinecoder.com/send-email-from-java-application-using-java-mail-api/
    public static void sendMail(String recipient, String emailMessage) throws MessagingException {
        System.out.println("Preparing To Email User");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String account = "ProActiveHealthTracker@gmail.com";
        String password = "V{Qdz5uSqFjA";

        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(account, password);
            }
        });

        Message message = prepareMessage(session, account, recipient, emailMessage);
        Transport.send(message);
        System.out.println("Message Sent To User");
    }

    private static Message prepareMessage(Session session, String accountEmail, String recipient, String emailMessage) throws MessagingException {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(accountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("ProActive Health Tracker");
            message.setText(emailMessage);
            return message;
        } catch (Exception e) {
        }

        return null;
    }
}
