/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.service;

import br.uff.sti.hermes.model.SendTask;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 *
 * @author dancastellani
 */
@Component
public class SendTaskService {

    Map<Integer, SendTask> tasks = new HashMap<Integer, SendTask>();

    public void addTaks(SendTask task) {
        tasks.put(task.getId(), task);
    }
    
    public SendTask getTask(int id){
        return tasks.get(id);
    }

    public Collection<SendTask> getAll() {
        return tasks.values();
    }

}
