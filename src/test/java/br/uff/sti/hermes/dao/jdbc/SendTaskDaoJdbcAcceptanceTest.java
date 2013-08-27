/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.dao.jdbc;

import br.uff.sti.hermes.dao.SendTaskDao;
import br.uff.sti.hermes.model.SendTask;
import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.junit.FlywayTestExecutionListener;
import java.util.List;
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
@ContextConfiguration(locations = "/applicationContext.xml")
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    FlywayTestExecutionListener.class})
public class SendTaskDaoJdbcAcceptanceTest {

    @Autowired
    private SendTaskDao sendTaskDao;

    @Test
    public void springShouldInjectSendTaskDaoJdbcIntoSendTaskDao() {
        assertEquals(SendTaskDaoJdbc.class, sendTaskDao.getClass());
    }

    @Test
    public void whenGetByIdWithOneShouldReturnTestSubject() {
        SendTask task = sendTaskDao.getById(1);

        assertNotNull(task);

        assertEquals("mail@send.to", task.getSendTo());
        assertEquals("mail@reply.to", task.getReplyTo());
        assertEquals("test subject", task.getSubject());
        assertEquals("test mail", task.getContent());
        assertEquals(SendTask.Status.TODO, task.getStatus());
    }

    @FlywayTest
    @Test
    public void whenGetAllReturnTwoTestSubjects() {
        List<SendTask> allTasks = sendTaskDao.getAll();

        assertNotNull(allTasks);
        assertEquals(2, allTasks.size());
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void whenSaveTaskWithoutIdShouldRaiseError() {
        SendTask task = new SendTask();
        sendTaskDao.save(task);

        fail("Saving clean SendTask should raise error!");
    }

    @Test
    public void whenSaveTaskInsertAllAttributes() {
        SendTask task = new SendTask(0, "to", "replyto", "subject", "content", SendTask.Status.TODO);
        sendTaskDao.save(task);
    }
}
