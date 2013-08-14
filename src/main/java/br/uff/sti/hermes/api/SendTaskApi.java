/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.api;

import br.uff.sti.hermes.model.SendTask;
import br.uff.sti.hermes.service.SendTaskService;
import java.util.Collection;
import java.util.Set;
import javax.ws.rs.GET;
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
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<SendTask> list() {
        return sendTaskService.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SendTask show(@PathParam(value = "id") int id) {
        return sendTaskService.getTask(id);
    }
//    @POST
//    @Path("/")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public SendTask create(SendTask task) {
//        System.out.println("-----------------> create: " + task);
//        return task;
//    }

    SendTaskService getSendTaskService() {
        return this.sendTaskService;
    }

    void setSendTaskService(SendTaskService sendTaskService) {
        this.sendTaskService = sendTaskService;
    }
}
