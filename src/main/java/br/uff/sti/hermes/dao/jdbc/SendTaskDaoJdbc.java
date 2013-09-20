/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.dao.jdbc;

import br.uff.sti.hermes.dao.SendTaskDao;
import br.uff.sti.hermes.model.SendTask;
import java.util.List;
import br.uff.sti.hermes.exception.ObjectNotFoundException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@Component(value = "SendTaskDao")
public class SendTaskDaoJdbc extends JdbcDaoSupport implements SendTaskDao {
    
    static final String SQL_DELETE_BY_ID = "delete from sendtask where id = ?";
    static final String SQL_SELECT_BY_ID = "select * from sendtask where id = ?";
    static final String SQL_GET_ALL = "select * from sendtask";
    static final String SQL_GET_BY_STATUS = "select * from sendtask where status = ?";
    //TODO: review the sequence next value. This works for HSQL, but I think do not work for oracle or mysql.
    static final String SQL_INSERT = "insert into sendtask (sendto, replyto, subject, content, status) VALUES (?, ?, ?, ?, ?)";
    static final String SQL_UPDATE = "update sendtask set sendto=?, replyto=?, subject=?, content=?, status=? where id = ?";

//    @Autowired
//    private DataSource dataSource;
    @Autowired
    SendTaskDaoJdbc(DataSource dataSource) {
        setDataSource(dataSource);
    }
    
    @Override
    @Transactional
    public int insert(SendTask task) {
        
        getJdbcTemplate().update(SQL_INSERT,
                new Object[]{
            task.getSendTo(),
            task.getReplyTo(),
            task.getSubject(),
            task.getContent(),
            task.getStatus().toString()
        });
        
        Integer nextSeqVal = getJdbcTemplate().queryForInt("select max(id) from sendtask");
        
        return nextSeqVal;
    }
    
    @Override
    public List<SendTask> getAll() {
        return getJdbcTemplate().query(SQL_GET_ALL, new BeanPropertyRowMapper(SendTask.class));
    }
    
    @Override
    public SendTask getById(int id) throws ObjectNotFoundException{
        try {
            return (SendTask) getJdbcTemplate().queryForObject(
                    SQL_SELECT_BY_ID,
                    new Object[]{id},
                    new BeanPropertyRowMapper(SendTask.class));
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectNotFoundException("Send Task with id " + id + " not found.");
        }
    }
    
    @Override
    public List<SendTask> getByStatus(SendTask.Status status) {
        return getJdbcTemplate().queryForList(SQL_GET_BY_STATUS,
                new Object[]{status.toString()},
                SendTask.class);
    }
    
    @Override
    public void update(SendTask task) {
        final Object[] params = new Object[]{
            task.getSendTo(),
            task.getReplyTo(),
            task.getSubject(),
            task.getContent(),
            task.getStatus().toString(),
            task.getId()
        };
        
        getJdbcTemplate().update(SQL_UPDATE, params);
    }
    
    @Override
    public void delete(int id) throws ObjectNotFoundException {
        int updatedRows = getJdbcTemplate().update(SQL_DELETE_BY_ID, id);
        
        if (updatedRows == 0) {
            throw new ObjectNotFoundException("The task with id <" + id + "> was not found.");
        }
    }
}
