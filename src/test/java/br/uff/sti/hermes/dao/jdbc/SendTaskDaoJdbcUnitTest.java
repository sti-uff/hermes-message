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
    DependencyInjectionTestExecutionListener.class})
public class SendTaskDaoJdbcUnitTest {

    @Autowired
    private SendTaskDao sendTaskDao;

    @Test()
    public void whenSaveSendTaskShouldInsertAllAttributes() {
        SendTask taskToSave = new SendTask(null, "to", "replyto", "subject", "content", SendTask.Status.TODO);
        int taskId = sendTaskDao.insert(taskToSave);

        SendTask savedTask = sendTaskDao.getById(taskId);
        assertTrue(taskId == savedTask.getId());
        assertEquals(taskToSave.getSendTo(), savedTask.getSendTo());
        assertEquals(taskToSave.getReplyTo(), savedTask.getReplyTo());
        assertEquals(taskToSave.getSubject(), savedTask.getSubject());
        assertEquals(taskToSave.getContent(), savedTask.getContent());
        assertEquals(taskToSave.getStatus(), savedTask.getStatus());
    }
}
