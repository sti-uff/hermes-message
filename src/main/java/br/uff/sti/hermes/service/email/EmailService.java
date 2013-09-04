package br.uff.sti.hermes.service.email;

import br.uff.sti.hermes.ApplicationConstants;
import br.uff.sti.hermes.model.SendTask;
import java.util.LinkedList;
import java.util.List;
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

    void sendMail(String to, String replyTo, String subject, String msg) {
        for (String[] processedTo : processRecipients(to)) {
            SimpleMailMessage email = createEmail(processedTo, replyTo, subject, msg);
            mailSender.send(email);
        }
    }

    SimpleMailMessage createEmail(String[] recipientGroup, String replyTo, String subject, String msg) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setBcc(recipientGroup);
        email.setFrom(constants.EMAIL_FROM);
        email.setReplyTo(replyTo);
        email.setSubject(subject);
        email.setText(msg);
        return email;
    }

    List<String[]> processRecipients(String to) {
        String[] splitedRecipients = to.split(ApplicationConstants.MAIL_SEPARATOR);

        List<String[]> processedGroups = new LinkedList<String[]>();
        List<String> recipientGroup = new LinkedList<String>();
        for (String recipient : splitedRecipients) {
            recipientGroup.add(recipient);

            if (recipientGroup.size() % constants.MAX_RECEPIENTS_PER_EMAIL == 0) {
                processedGroups.add(recipientGroup.toArray(new String[0]));
                recipientGroup = new LinkedList<String>();
            }
        }
        if (!recipientGroup.isEmpty()) {
            processedGroups.add(recipientGroup.toArray(new String[0]));
        }

        return processedGroups;
    }

    /**
     * @param mailSender the mailSender to set
     */
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * @return the mailSender
     */
    public MailSender getMailSender() {
        return mailSender;
    }

    /**
     * @param constants the constants to set
     */
    public void setConstants(ApplicationConstants constants) {
        this.constants = constants;
    }
}