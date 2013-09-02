package br.uff.sti.hermes.service;

import br.uff.sti.hermes.dao.SendTaskDao;
import br.uff.sti.hermes.model.SendTask;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author dancastellani
 */
public class SendTaskServiceUnittest {

    private SendTaskService sendTaskService;
    private SendTaskDao sendTaskDaoMock;

    @Before
    public void setup() {
        sendTaskService = new SendTaskService();

        sendTaskDaoMock = mock(SendTaskDao.class);
        sendTaskService.setSendTaskDao(sendTaskDaoMock);

    }

    @Test
    public void whenSaveSendTaskWitouhIdShouldInsertSendTask() {
        SendTask aSendTask = new SendTask();
        when(sendTaskDaoMock.insert(aSendTask)).thenReturn(anyInt());

        sendTaskService.save(aSendTask);

        verify(sendTaskDaoMock).insert(aSendTask);
    }

    @Test
    public void whenSaveSendTaskWithIdShouldUpdateSendTask() {
        SendTask aSendTask = new SendTask();
        aSendTask.setId(0);
        when(sendTaskDaoMock.insert(aSendTask)).thenReturn(anyInt());

        sendTaskService.save(aSendTask);

        verify(sendTaskDaoMock).update(aSendTask);
    }
}
