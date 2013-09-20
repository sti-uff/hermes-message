/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.api;

import br.uff.sti.hermes.dao.SendTaskDao;
import br.uff.sti.hermes.exception.ObjectNotFoundException;
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
@ContextConfiguration(locations = "/spring/applicationContext.xml")
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    FlywayTestExecutionListener.class})
public class SendTaskApiAcceptanceTest {

    private static final String API_GET_SEND_TASK_BASE = "api/sendtasks/";
    private static final String API_GET_SEND_TASK_INFO = API_GET_SEND_TASK_BASE;
    private static final String API_POST_NEW_SEND_TASK = API_GET_SEND_TASK_BASE;
    private static final String API_DELETE_SEND_TASK = API_GET_SEND_TASK_BASE;
    @Autowired
    private SendTaskApi sendTaskApi;
    @Autowired
    private SendTaskDao sendTaskDao;
    private final SendTask taskOne = new SendTask(null, "mail@send.to", "replyTo", "subect", "content", SendTask.Status.TODO);
    private final SendTask taskTwo = new SendTask(null, "another.mail@send.to", "another.replyTo", "another.subect", "another.content", SendTask.Status.TODO);

    @Before
    public void setupTest() {
        //id = 1
        int id = sendTaskDao.insert(taskOne);
        taskOne.setId(id);
        //id = 2
        id = sendTaskDao.insert(taskTwo);
        taskTwo.setId(id);
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
    public void whenCallApiToGetSendTaskOneInfoShouldReturnTestSubject() throws ObjectNotFoundException {
        assumeNotNull(sendTaskDao.getById(1));
        SendTask sendTask = sendTaskApi.show(1);

        assertNotNull(sendTask);
        assertTrue(sendTask.getId() == 1);
    }

    @Test
    @FlywayTest
    public void whenCallApiToCreateSendTaskWithParametersShouldSaveAndReturnTheTaskId() {
        SendTask task = new SendTask("to", "replyTo", "subject", "content");

        Integer taskId = sendTaskApi.create(task.getSendTo(), task.getReplyTo(), task.getSubject(), task.getContent());

        assertNotNull(taskId);
        assertTrue(3 == taskId);
    }

    @Test
    @FlywayTest
    public void whenCallApiToCreateNewSendTaskShouldReturnTheTasksId() {
        SendTask task = new SendTask("to", "replyTo", "subject", "content");

        Integer taskId = sendTaskApi.create(task);

        assertNotNull(taskId);
        assertTrue(3 == taskId);
    }

    // -------------------- Http Tests
    @Test
    @FlywayTest
    public void whenCallHttpApiToGetSendTaskOneInformationShouldReturnStatusCode200() throws ObjectNotFoundException {
        assumeNotNull(sendTaskDao.getById(1));

        expect().statusCode(200).when().get(API_GET_SEND_TASK_INFO + "1");
    }

    @Test
    @FlywayTest
    public void whenCallHttpApiToGetSendTaskOneInformationShouldReturnAllInformation() throws ObjectNotFoundException {
        assumeNotNull(sendTaskDao.getById(1));

        SendTask returnedTask = get(API_GET_SEND_TASK_INFO + "1").as(SendTask.class);

        assertEquals(taskOne.getSendTo(), returnedTask.getSendTo());
        assertEquals(taskOne.getReplyTo(), returnedTask.getReplyTo());
        assertEquals(taskOne.getSubject(), returnedTask.getSubject());
        assertEquals(taskOne.getContent(), returnedTask.getContent());
        assertEquals(taskOne.getStatus(), returnedTask.getStatus());
    }

    @Test
    @FlywayTest
    public void whenCallHttpApiToPostSendTaskShouldReturnStatus200AndSendTaskId() throws ObjectNotFoundException {
        final String to = "test.to";
        final String replyTo = "test.replyTo";
        final String subject = "test.subject";
        final String content = "test.content";

        Integer id = expect().statusCode(200)
                .given()
                .param("to", to)
                .param("replyTo", replyTo)
                .param("subject", subject)
                .param("content", content)
                .when().post(API_POST_NEW_SEND_TASK).as(Integer.class);

        assertNotNull(id);

        assertNotNull(sendTaskDao.getById(id));
    }

    @Test(expected = ObjectNotFoundException.class)
    @FlywayTest
    public void whenCallHttpApiToDeleteSendTaskShouldReturnStatus200AndDeleteSendTask() throws ObjectNotFoundException {
        int idOfTaskToDelete = taskOne.getId();
        expect().statusCode(Response.Status.NO_CONTENT.getStatusCode())
                .when().delete(API_DELETE_SEND_TASK + idOfTaskToDelete);

        sendTaskDao.getById(idOfTaskToDelete);
        fail("SendTask should be deleted. Thus, shloud be raised exception when looking for it.");
    }

    @Test
    @FlywayTest
    public void whenCallHttpApiToDeleteSendTaskShouldReturnStatus412WhenSendTaskNotFound() {
        int idThatDontExist = -1;

        expect().statusCode(Response.Status.PRECONDITION_FAILED.getStatusCode())
                .when().delete(API_DELETE_SEND_TASK + idThatDontExist);
    }
}
