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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author dancastellani
 */
@Component
@Path("/sendtasks")
public class SendTaskApi {

    @Autowired
    private SendTaskService sendTaskService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<SendTask> list() {
        return sendTaskService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SendTask show(@PathParam(value = "id") int id) {
        return sendTaskService.getTaskbyId(id);
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer create(@FormParam("to") String to,
            @FormParam("replyTo") String replyTo,
            @FormParam("subject") String subject,
            @FormParam("content") String content) {

        SendTask task = new SendTask(to, replyTo, subject, content);
        return create(task);
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer create(SendTask task) {
        sendTaskService.save(task);
        return task.getId();
    }

    SendTaskService getSendTaskService() {
        return this.sendTaskService;
    }

    void setSendTaskService(SendTaskService sendTaskService) {
        this.sendTaskService = sendTaskService;
    }
}
