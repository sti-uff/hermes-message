package br.uff.sti.hermes.service.email;

import br.uff.sti.hermes.ApplicationConstants;
import br.uff.sti.hermes.model.SendTask;
import br.uff.sti.hermes.service.SendTaskService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author dancastellani
 */
public class EmailSenderJobUnitTest {

    private EmailSenderJob emailSender;
    private SendTaskService sendTaskServiceMock;
    private EmailService emailServiceMock;
    private ApplicationConstants applicationConstants;

    @Before
    public void setup() {
        emailSender = new EmailSenderJob();

        sendTaskServiceMock = mock(SendTaskService.class);
        emailSender.setSendTaskService(sendTaskServiceMock);

        emailServiceMock = mock(EmailService.class);
        emailSender.setEmailService(emailServiceMock);

        applicationConstants = new ApplicationConstants();
        emailSender.setApplicationConstants(null);
    }

    @Test
    public void whenJobRunsShouldGetEmailsWithStatusTodo() {
        List<SendTask> emptyList = new ArrayList<SendTask>(0);
        when(sendTaskServiceMock.getByStatus(any(SendTask.Status.class))).thenReturn(emptyList);

        emailSender.processSendTasks();

        verify(sendTaskServiceMock).getByStatus(SendTask.Status.TODO);
    }

    @Test
    public void whenJobRunsAndHasSendTasksToProcessShouldCallEmailServiceToSendTheEmails() {
        List<SendTask> taskList = new ArrayList<SendTask>(1);
        taskList.add(mock(SendTask.class));
        when(sendTaskServiceMock.getByStatus(any(SendTask.Status.class))).thenReturn(taskList);

        emailSender.processSendTasks();

        verify(emailServiceMock).sendMail(any(SendTask.class));
    }

    @Test
    public void whenJobRunsAndHasSendTasksToProcessShouldChangeTasksStatus() {
        List<SendTask> taskList = new ArrayList<SendTask>(1);
        SendTask mockedTask = mock(SendTask.class);
        taskList.add(mockedTask);
        when(sendTaskServiceMock.getByStatus(any(SendTask.Status.class))).thenReturn(taskList);
        emailSender = spy(emailSender);

        emailSender.processSendTasks();

        verify(emailSender).updateSendTaskStatus(mockedTask, SendTask.Status.DONE);
    }

    @Test
    public void whenUpdateStatusOnEmailSenderJobIsCalledByItselfShouldCallSendTaskServiceToUpdateSendTask() {
        emailSender.updateSendTaskStatus(mock(SendTask.class), any(SendTask.Status.class));

        verify(sendTaskServiceMock).save(any(SendTask.class));
    }
}
