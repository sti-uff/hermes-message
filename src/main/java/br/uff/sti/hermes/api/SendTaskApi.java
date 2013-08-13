/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.api;

import br.uff.sti.hermes.model.SendTask;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;

/**
 *
 * @author dancastellani
 */
@Component
@Path("/sendtasks")
public class SendTaskApi {

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SendTask> list() {
        System.out.println("-----------------> public List<Post> list() {");
        List<SendTask> tasks = new ArrayList<SendTask>();
        tasks.add(new SendTask("to", "replyTo", "subject", "content"));
        return tasks;
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SendTask create(SendTask task) {
        System.out.println("-----------------> public List<Post> create: " + task);
        return task;
    }
}
