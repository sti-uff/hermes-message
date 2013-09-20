/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.dao;

import br.uff.sti.hermes.model.SendTask;
import java.util.List;
import br.uff.sti.hermes.exception.ObjectNotFoundException;

/**
 *
 * @author Daniel
 */
public interface SendTaskDao {

    public List<SendTask> getAll();

    public SendTask getById(int id) throws ObjectNotFoundException;

    public int insert(SendTask task);

    public List<SendTask> getByStatus(SendTask.Status status);

    public void update(SendTask task);

    public void delete(int id) throws ObjectNotFoundException;
}
