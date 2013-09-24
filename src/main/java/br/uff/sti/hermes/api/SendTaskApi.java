/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.api;

import br.uff.sti.hermes.api.base.BaseApi;
import br.uff.sti.hermes.api.base.exception.ApiException;
import br.uff.sti.hermes.model.SendTask;
import br.uff.sti.hermes.service.SendTaskService;
import java.util.Collection;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import br.uff.sti.hermes.exception.ObjectNotFoundException;
import com.wordnik.swagger.annotations.Api;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.springframework.stereotype.Component;

/**
 *
 * @author dancastellani
 */
@Api(value = "/sendtasks", description = "Operations about SendTasks. This Api is to create and retrieve status about the emails sending tasks.")
@Produces({"application/json", "application/xml"})
@Component
@Path("/sendtasks")
public class SendTaskApi extends BaseApi<SendTask> {

    @Autowired
    private SendTaskService sendTaskService;

    public Collection<SendTask> list() {
        return sendTaskService.getAll();
    }

    public SendTask show(@PathParam(value = "id") int id) throws ApiException {
        try {
            return sendTaskService.getTaskbyId(id);
        } catch (ObjectNotFoundException ex) {
            throw new ApiException(ex);
        }
    }

    public Integer create(@FormParam("to") String to,
            @FormParam("replyTo") String replyTo,
            @FormParam("subject") String subject,
            @FormParam("content") String content) {
        Logger.getLogger(SendTaskApi.class).info("Create with form params");

        SendTask task = new SendTask(to, replyTo, subject, content);
        return create(task);
    }

    public Integer create(SendTask task) {
        Logger.getLogger(SendTaskApi.class).info("Create task: " + task);

        sendTaskService.save(task);
        Logger.getLogger(SendTaskApi.class).info("Created: " + task.getId());
        return task.getId();
    }

    public void delete(@PathParam(value = "id") int id) throws ApiException {
        try {
            sendTaskService.delete(id);

        } catch (ObjectNotFoundException ex) {
            throw new ApiException(ex);
        }
    }

    SendTaskService getSendTaskService() {
        return this.sendTaskService;
    }

    void setSendTaskService(SendTaskService sendTaskService) {
        this.sendTaskService = sendTaskService;
    }

    public void update(Integer id, SendTask item) throws ApiException {
        sendTaskService.save(item);
    }
}
