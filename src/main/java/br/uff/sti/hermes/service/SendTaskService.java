/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.service;

import br.uff.sti.hermes.dao.SendTaskDao;
import br.uff.sti.hermes.model.SendTask;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author dancastellani
 */
@Component
public class SendTaskService {

    @Autowired
    SendTaskDao sendTaskDao;

    public void saveTask(SendTask task) {
        sendTaskDao.save(task);
    }

    public SendTask getTaskbyId(int id) {
        return sendTaskDao.getById(id);
    }

    public Collection<SendTask> getAll() {
        return sendTaskDao.getAll();
    }
}
