/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.service;

import br.uff.sti.hermes.dao.SendTaskDao;
import br.uff.sti.hermes.exception.ObjectNotFoundException;
import br.uff.sti.hermes.model.SendTask;
import java.util.Collection;
import java.util.List;
import br.uff.sti.hermes.exception.ObjectNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author dancastellani
 */
@Component
public class SendTaskService {

    @Autowired
    private SendTaskDao sendTaskDao;

    public SendTask save(SendTask task) {
        if (task.getId() == null) {
            insert(task);
        } else {
            update(task);
        }
        return task;
    }

    public SendTask getTaskbyId(int id) throws ObjectNotFoundException {
        try {
            return sendTaskDao.getById(id);
        } catch (ObjectNotFoundException ex) {
            Logger.getLogger(SendTaskService.class).error(ex.getMessage(), ex);
            throw ex;
        }
    }

    public Collection<SendTask> getAll() {
        return sendTaskDao.getAll();
    }

    public List<SendTask> getByStatus(SendTask.Status status) {
        return sendTaskDao.getByStatus(status);
    }

    private void insert(SendTask task) {
        int taskId = sendTaskDao.insert(task);
        task.setId(taskId);
    }

    private void update(SendTask task) {
        sendTaskDao.update(task);
    }

    public void delete(int id) throws ObjectNotFoundException {
        try {
            sendTaskDao.delete(id);
        } catch (ObjectNotFoundException ex) {
            Logger.getLogger(SendTaskService.class).info(ex);
            throw ex;
        }
    }

    /**
     * @param sendTaskDao the sendTaskDao to set
     */
    public void setSendTaskDao(SendTaskDao sendTaskDao) {
        this.sendTaskDao = sendTaskDao;
    }


}
