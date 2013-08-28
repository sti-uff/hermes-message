/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.dao.jdbc;

import br.uff.sti.hermes.dao.SendTaskDao;
import br.uff.sti.hermes.model.SendTask;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
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

    static final String SQL_SELECT_BY_ID = "select * from sendtask where id = ?";
    static final String SQL_GET_ALL = "select * from sendtask";
    //TODO: review the sequence next value. This works for HSQL, but I think do not work for oracle or mysql.
    static final String SQL_INSERT = "insert into sendtask (id, sendto, replyto, subject, content, status) VALUES (?, ?, ?, ?, ?, ?)";

//    @Autowired
//    private DataSource dataSource;
    @Autowired
    SendTaskDaoJdbc(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    @Transactional
    public int insert(SendTask task) {
        Integer nextSeqVal = getJdbcTemplate().queryForInt("call NEXT VALUE FOR sendtask_seq");

        getJdbcTemplate().update(SQL_INSERT,
                new Object[]{
            nextSeqVal,
            task.getSendTo(),
            task.getReplyTo(),
            task.getSubject(),
            task.getContent(),
            task.getStatus().toString()
        });

        return nextSeqVal;
    }

    @Override
    public List<SendTask> getAll() {
        return getJdbcTemplate().query(SQL_GET_ALL, new BeanPropertyRowMapper(SendTask.class));
    }

    @Override
    public SendTask getById(int id) {
        return (SendTask) getJdbcTemplate().queryForObject(
                SQL_SELECT_BY_ID,
                new Object[]{id},
                new BeanPropertyRowMapper(SendTask.class));
    }
//    private class SendTaskRowMapper implements RowMapper {
//
//        @Override
//        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//            SendTask sendTask = new SendTask();
//            sendTask.setId(rs.getInt("id"));
//            sendTask.setSendTo(rs.getString("sendto"));
//            sendTask.setReplyTo(rs.getString("replyto"));
//            sendTask.setSubject(rs.getString("subject"));
//            sendTask.setContent(rs.getString("content"));
//            sendTask.setStatus(SendTask.Status.get(rs.getString("status")));
//            sendTask.setCreatedAt(DateUtil.getCalendar(rs.getDate("createdat")));
//            return sendTask;
//        }
//    }
}
