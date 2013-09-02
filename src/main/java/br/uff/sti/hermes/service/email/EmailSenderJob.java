/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.service.email;

import br.uff.sti.hermes.model.SendTask;
import br.uff.sti.hermes.service.SendTaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author DanCastellani
 */
@Component
public class EmailSenderJob {

    @Autowired
    private EmailService emailService;
    @Autowired
    private SendTaskService sendTaskService;

    public void processSendTasks() {
        List<SendTask> tasks = sendTaskService.getByStatus(SendTask.Status.TODO);
        
        for (SendTask sendTask : tasks) {
            updateSendTaskStatus(sendTask, SendTask.Status.DOING);
            emailService.sendMail(sendTask);
            updateSendTaskStatus(sendTask, SendTask.Status.DONE);
        }
    }

    /**
     * @param sendTaskService the sendTaskService to set
     */
    public void setSendTaskService(SendTaskService sendTaskService) {
        this.sendTaskService = sendTaskService;
    }

    /**
     * @param emailService the emailService to set
     */
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    void updateSendTaskStatus(SendTask sendTask, SendTask.Status status) {
        sendTask.setStatus(status);
        sendTaskService.save(sendTask);
    }
}
