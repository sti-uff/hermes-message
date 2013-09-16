/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.api;

import br.uff.sti.hermes.model.SendTask;
import br.uff.sti.hermes.service.SendTaskService;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 *
 * @author dancastellani
 */
@Api(value = "/sendtasks", description = "Operations about SendTasks. This Api is to create and retrieve status about the emails sending tasks.")
@Produces({"application/json", "application/xml"})
@Component
@Path("/sendtasks")
public class SendTaskApi {

    @Autowired
    private SendTaskService sendTaskService;

    @ApiOperation(value = "List SendTasks", notes = "More notes about this method", response = Collection.class)
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<SendTask> list() {
        return sendTaskService.getAll();
    }

    @ApiOperation(value = "Show details of a SendTask by id", notes = "More notes about this method", response = SendTask.class)
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SendTask show(@PathParam(value = "id") int id) {
        return sendTaskService.getTaskbyId(id);
    }

    @ApiOperation(value = "Create a new SendTask with form params", notes = "More notes about this method", response = Integer.class)
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer create(@FormParam("to") String to,
            @FormParam("replyTo") String replyTo,
            @FormParam("subject") String subject,
            @FormParam("content") String content) {
        Logger.getLogger(SendTaskApi.class).debug("Create with form params");

        SendTask task = new SendTask(to, replyTo, subject, content);
        return create(task);
    }

    @ApiOperation(value = "Create a new SendTask with a SendTask JSON", notes = "More notes about this method", response = Integer.class)
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer create(SendTask task) {
        Logger.getLogger(SendTaskApi.class).debug("Create task: " + task);

        sendTaskService.save(task);
        Logger.getLogger(SendTaskApi.class).debug("Created: " + task.getId());
        return task.getId();
    }

    SendTaskService getSendTaskService() {
        return this.sendTaskService;
    }

    void setSendTaskService(SendTaskService sendTaskService) {
        this.sendTaskService = sendTaskService;
    }
}
