/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.api;

import br.uff.sti.hermes.model.SendTask;
import br.uff.sti.hermes.service.SendTaskService;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 *
 * @author dancastellani
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class SendTaskApiUnitTest {

    @Autowired
    private SendTaskApi sendTaskApi;
    private SendTaskService serviceMock;

    @Before
    public void setup() {
        serviceMock = mock(SendTaskService.class);
        sendTaskApi.setSendTaskService(serviceMock);
    }

    @Test
    public void getTasksByIdShouldCallServiceMock() {
        SendTask task = new SendTask("to", "replyTo", "subject", "content");
        when(serviceMock.getTaskbyId(1)).thenReturn(task);

        assertEquals(task, sendTaskApi.show(1));
    }

    @Test
    public void getTasksShouldCallServiceMock() {
        List<SendTask> taskList = mock(List.class);
        taskList.add(new SendTask("to", "replyTo", "subject", "content"));
        when(serviceMock.getAll()).thenReturn(taskList);

        assertEquals(taskList, sendTaskApi.list());
    }
}