package test.elfin.delegate;

import test.elfin.model.User;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class SendMailDelegate implements JavaDelegate {

    @Value("${smpt.username}")
    private String username;
    @Value("${smpt.password}")
    private String password;
    @Value("${smpt.server}")
    private String server;
    @Value("${smpt.port}")
    private int port;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        User user = (User) delegateExecution.getVariable("user");
        Properties props = new Properties();
        props.put("mail.smtp.host", server);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);
        props.put("mail.smtps.ssl.checkserveridentity", true);
        props.put("mail.smtps.ssl.trust", "*");
        props.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPassAuth() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject("Подтверждение регистрации");
            message.setText("Процесс регистрации прошел успешно");
            Transport.send(message, username, password);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
