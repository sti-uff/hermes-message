/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.service.email;

import br.uff.sti.hermes.ApplicationConstants;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author dancastellani
 */
public class EmailServiceUnitTest {

    EmailService emailService;
    MailSender mailSenderMock;
    ApplicationConstants constants;
    //
    final String[] recipients = new String[]{"one@email", "two@email"};
    final String replyTo = "replyTo";
    final String subject = "subject";
    final String content = "content";

    @Before
    public void setup() {
        emailService = new EmailService();

        mailSenderMock = mock(MailSender.class);
        emailService.setMailSender(mailSenderMock);

        constants = new ApplicationConstants();
        constants.EMAIL_FROM = "from@hermes";
        constants.MAX_RECEPIENTS_PER_EMAIL = constants.DEFAULT_MAX_RECEPIENTS_PER_EMAIL;
        emailService.setConstants(constants);
    }

    @Test
    public void whenProcessRecipientsWithOneEmailAndGroupSizeIsThreeShouldReturnOneRecipientGroup() {
        constants.MAX_RECEPIENTS_PER_EMAIL = 3;
        final List<String[]> expectedAnswer = new LinkedList<String[]>();
        expectedAnswer.add(new String[]{"a@hermes.uff.br"});

        List<String[]> answer = emailService.processRecipients("a@hermes.uff.br");

        assertEquals(expectedAnswer.size(), answer.size());
        assertArrayEquals(expectedAnswer.get(0), answer.get(0));
    }

    @Test
    public void whenProcessRecipientsWithThreeEmailsAndGroupSizeIsThreeShouldReturnOneRecipientGroup() {
        constants.MAX_RECEPIENTS_PER_EMAIL = 3;
        final List<String[]> expectedAnswer = new LinkedList<String[]>();
        expectedAnswer.add(new String[]{"a@hermes.uff.br", "b@hermes.uff.br", "c@hermes.uff.br"});

        List<String[]> answer = emailService.processRecipients("a@hermes.uff.br;b@hermes.uff.br;c@hermes.uff.br");

        assertEquals(1, answer.size());
        assertArrayEquals(expectedAnswer.get(0), answer.get(0));
    }

    @Test
    public void whenProcessRecipientsWithFourEmailsAndGroupSizeIsThreeShouldReturnTwoRecipientGroup() {
        constants.MAX_RECEPIENTS_PER_EMAIL = 3;
        final List<String[]> expectedAnswer = new LinkedList<String[]>();
        expectedAnswer.add(new String[]{"a@hermes.uff.br", "b@hermes.uff.br", "c@hermes.uff.br"});
        expectedAnswer.add(new String[]{"d@hermes.uff.br"});

        List<String[]> answer = emailService.processRecipients("a@hermes.uff.br;b@hermes.uff.br;c@hermes.uff.br;d@hermes.uff.br");

        assertEquals(2, answer.size());
        assertArrayEquals(expectedAnswer.get(0), answer.get(0));
        assertArrayEquals(expectedAnswer.get(1), answer.get(1));
    }

    @Test
    public void whenSendMailShouldCallMailSender() {
        EmailService emailServiceSpy = spy(emailService);
        emailServiceSpy.sendMail(recipients[0], replyTo, subject, replyTo);
        verify(mailSenderMock).send(any(SimpleMailMessage.class));
    }

    @Test
    public void whenCreatingEmailTheRecipientsShouldBeOnBccNotOnTo() {
        SimpleMailMessage email = emailService.createEmail(recipients, replyTo, subject, content);

        assertNotNull(email);
        assertArrayEquals("The recipients shoud be setted on bcc to preserve the recipients information.", recipients, email.getBcc());
        assertNull("To field should not be used.", email.getTo());
    }

    @Test
    public void whenCreatingEmailMustSetSubject() {
        SimpleMailMessage email = emailService.createEmail(recipients, replyTo, subject, content);

        assertNotNull(email);
        assertEquals("The parameter subject should be used to set the email subject.", subject, email.getSubject());
    }

    @Test
    public void whenCreatingEmailMustSetContent() {
        SimpleMailMessage email = emailService.createEmail(recipients, replyTo, subject, content);

        assertNotNull(email);
        assertEquals("The parameter content should be used to set the email content.", content, email.getText());
    }

    @Test
    public void whenCreatingEmailMustSetReplyTo() {
        SimpleMailMessage email = emailService.createEmail(recipients, replyTo, subject, content);

        assertNotNull(email);
        assertEquals("The parameter replyTo should be used to set the email replyTo.", replyTo, email.getReplyTo());
    }

    @Test
    public void whenCreatingEmailMustSetFrom() {
        SimpleMailMessage email = emailService.createEmail(recipients, replyTo, subject, content);

        assertNotNull(email);
        assertEquals("The constant email from should be used from ApplicationConstants.", constants.EMAIL_FROM, email.getFrom());
    }
}
