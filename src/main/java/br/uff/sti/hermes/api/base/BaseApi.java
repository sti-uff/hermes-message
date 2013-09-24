/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.api.base;

import br.uff.sti.hermes.api.base.exception.ApiException;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.jaxrs.PATCH;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

/**
 *
 * @author dancastellani
 */
//@Path("/")
public abstract class BaseApi<T> {

    @ApiOperation(value = "List", response = Collection.class, httpMethod = "GET")
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<T> getResources() {
        Logger.getLogger(this.getClass()).info("Get list.");
        return list();
    }

    @ApiOperation(value = "Create a new resource item with a object JSON", response = Integer.class, httpMethod = "POST")
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer postResource(T item) {
        Logger.getLogger(this.getClass()).info("Create resource item: " + item);
        int id = create(item);
        Logger.getLogger(this.getClass()).info("Created: " + id);
        return id;
    }

    @ApiOperation(value = "Show details by id", httpMethod = "GET")
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public T getItem(@PathParam(value = "id") int id) throws ApiException {
        try {
            Logger.getLogger(this.getClass()).info("Retrieving information about item with id: " + id + ".");
            return show(id);

        } catch (ApiException ex) {
            Logger.getLogger(this.getClass()).info("Error.");
            throw ex;
        }
    }

    @ApiOperation(value = "Replace a resource item with a object JSON", response = Integer.class, httpMethod = "PUT")
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putItem(@PathParam(value = "id") Integer id, T item) {
        Logger.getLogger(this.getClass()).info("Replace resource item with id: " + id + ", with item: " + item);
        try {
            update(id, item);
            Logger.getLogger(this.getClass()).info("Replaced: " + id);
            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (ApiException ex) {
            Logger.getLogger(this.getClass()).error("Error replacing item with id: " + id + ".", ex);
            return Response.serverError().status(Response.Status.PRECONDITION_FAILED).build();
        }
    }

    @ApiOperation(value = "Deletes a resource item by id", response = Response.class, httpMethod = "DELETE")
    @DELETE
    @Path("/{id}")
    public Response deleteItem(@PathParam(value = "id") int id) {
        Logger.getLogger(this.getClass()).info("Delete item: " + id);
        try {
            delete(id);
            Logger.getLogger(this.getClass()).info("Deleted: " + id);
            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (ApiException ex) {
            Logger.getLogger(this.getClass()).error("Error deleting item with id: " + id + ".", ex);
            return Response.serverError().status(Response.Status.PRECONDITION_FAILED).build();
        }
    }

    abstract public Collection<T> list();

    abstract public T show(int id) throws ApiException;

    abstract public Integer create(T item);

    abstract public void delete(int id) throws ApiException;

    abstract public void update(Integer id, T item) throws ApiException;
}
