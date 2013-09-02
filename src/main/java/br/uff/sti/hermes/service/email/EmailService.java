package br.uff.sti.hermes.service.email;

import br.uff.sti.hermes.ApplicationConstants;
import br.uff.sti.hermes.model.SendTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private MailSender mailSender;
    @Autowired
    private ApplicationConstants constants;

    public void sendMail(SendTask sendTask) {
        sendMail(sendTask.getSendTo(), sendTask.getReplyTo(), sendTask.getSubject(), sendTask.getContent());
    }

    private void sendMail(String to, String replyTo, String subject, String msg) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setFrom(constants.EMAIL_FROM);

        if (replyTo != null) {
            message.setReplyTo(replyTo);
        }
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }

    /**
     * @param mailSender the mailSender to set
     */
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    
}