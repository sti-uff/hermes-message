/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.service.email;

import br.uff.sti.hermes.model.SendTask;
import br.uff.sti.hermes.service.SendTaskService;
import java.util.List;
import org.apache.log4j.Logger;
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
        try {
            List<SendTask> tasks = sendTaskService.getByStatus(SendTask.Status.TODO);

            for (SendTask sendTask : tasks) {
                updateSendTaskStatus(sendTask, SendTask.Status.DOING);
                emailService.sendMail(sendTask);
                updateSendTaskStatus(sendTask, SendTask.Status.DONE);
            }

        } catch (RuntimeException ex) {
            /*
             * During tests flyway reset database and the getByStatus(...) above breaks.
             * There should be a way to not execute this job during tests as it is not needed.
             * TODO: refactor this and remove this job execution during tests
             */
            Logger.getLogger(EmailSenderJob.class).error("Error executing processSendTasks", ex);
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
