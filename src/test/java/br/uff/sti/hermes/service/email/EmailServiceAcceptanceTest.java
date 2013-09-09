/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.service.email;

import br.uff.sti.hermes.model.SendTask;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 *
 * @author dancastellani
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/applicationContext.xml")
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class})
public class EmailServiceAcceptanceTest {

    @Autowired
    EmailService emailService;

    /**
     * This method is used to send a real email.
     * To make it work, before, change Hermes configuration on config.propperties to your email configuration.
     * Thus, change the to and replyTo variables in the test method, and uncomment the @Test.
     */
//    @Test
    public void sendTestMail() {
        final String to = "your@email.com";
        final String replyTo = "your@email.com";
        SendTask task = new SendTask(to, replyTo, "Hermes Test Mail", "Hermes says: Hello!");
        emailService.sendMail(task);
    }
    
    @Test
    public void whenEmailServiceIsCreatedMailSenderShouldBeInitialized() {
        assertNotNull(emailService.getMailSender());
    }
}
