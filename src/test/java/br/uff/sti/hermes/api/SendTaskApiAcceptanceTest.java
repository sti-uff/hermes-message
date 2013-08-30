/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.api;

import br.uff.sti.hermes.dao.SendTaskDao;
import br.uff.sti.hermes.model.SendTask;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.junit.FlywayTestExecutionListener;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;
import java.util.Collection;
import javax.ws.rs.core.Response;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import org.junit.Before;
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
@ContextConfiguration(locations = "/applicationContext.xml")
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    FlywayTestExecutionListener.class})
public class SendTaskApiAcceptanceTest {

    private static final String API_GET_SEND_TASK_BASE = "api/sendtasks/";
    private static final String API_GET_SEND_TASK_INFO = API_GET_SEND_TASK_BASE;
    @Autowired
    private SendTaskApi sendTaskApi;
    @Autowired
    private SendTaskDao sendTaskDao;

    @Before
    public void setupTest() {
        //id = 1
        SendTask task = new SendTask(null, "mail@send.to", "replyTo", "subect", "content", SendTask.Status.TODO);
        sendTaskDao.insert(task);

        //id = 2
        task = new SendTask(null, "another.mail@send.to", "another.replyTo", "another.subect", "another.content", SendTask.Status.TODO);
        sendTaskDao.insert(task);
    }

    @Test
    public void whenApplicationStartupSpringAutowiresSendTaskServiceOnSendTaskApi() {
        assertNotNull(sendTaskApi.getSendTaskService());
    }

    @Test
    @FlywayTest
    public void whenCallApiToGetSendTaskListShouldReturnTwoElementsList() {
        Collection<SendTask> taskList = sendTaskApi.list();
        assertNotNull(taskList);

        assertEquals(2, taskList.size());
    }

    @Test
    @FlywayTest
    public void whenCallHttpApiToGetSendTaskListShouldReturnStatusCode200() {
        expect().statusCode(200).when().get(API_GET_SEND_TASK_BASE);
    }

    @Test
    @FlywayTest
    public void whenCallApiToGetSendTaskOneInfoShouldReturnTestSubject() {
        assumeNotNull(sendTaskDao.getById(1));
        SendTask sendTask = sendTaskApi.show(1);

        assertNotNull(sendTask);
        assertTrue(sendTask.getId() == 1);
    }

    @Test
    @FlywayTest
    public void whenCallApiToPostSendTaskShouldSaveAndReturnTheTaskId() {
        SendTask task = new SendTask("to", "replyTo", "subject", "content");
        int taskId = sendTaskApi.create(task);

        assertEquals(3, taskId);
    }
    
    // -------------------- Http Tests
    @Test
    @FlywayTest
    public void whenCallHttpApiToGetSendTaskOneInformationShouldReturnStatusCode200() {
        assumeNotNull(sendTaskDao.getById(1));

        expect().statusCode(200).when().get(API_GET_SEND_TASK_INFO + "1");
    }
}
