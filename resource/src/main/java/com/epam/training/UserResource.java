package com.epam.training;

import com.google.common.io.ByteStreams;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

/**
 * @author vkrasovsky
 */
@Path("users")
@Singleton//TODO: One instance for all queries
public class UserResource {
    private UserService userService = new UserServiceImpl();
//    private UserService userService = new UserServiceStubbedImpl();


    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response create(@QueryParam("format") String format, User user) {
        return Response.ok(userService.create(user), processFormat(format)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") String id, @QueryParam("format") String format, User user) {
        user.setEmail(id);
        return Response.ok(userService.update(user), processFormat(format)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") String id, @QueryParam("format") String format) {
        userService.delete(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response get(@PathParam("id") String id, @QueryParam("format") String format) {
        return Response.ok(userService.get(id)).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAll(@QueryParam("format") String format) {
        //TODO: for xml add @XmlRootElement, default constructor, generic entity
        return Response.ok(new GenericEntity<Collection<User>>(userService.getAll()) {
        }, processFormat(format)).build();
    }

    @POST
    @Path("/{id}/uploadphoto")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public Response uploadPhoto(@PathParam("id") String id,
                                @FormDataParam("file1") InputStream file1,
                                @FormDataParam("file1") FormDataContentDisposition file1Detail
                                /*@FormDataParam("file2") InputStream file2,*/
                                /*@FormDataParam("file2") FormDataContentDisposition file2Detail*/) {

        try {
            userService.uploadPhoto(id, ByteStreams.toByteArray(file1));
        } catch (IOException e) {
            throw new RuntimeException("Cannot save photo for user");
        }

        return Response.ok().build();
    }

    @GET
    @Path("/{id}/downloadphoto")
    @Produces({MediaType.MULTIPART_FORM_DATA})
    public Response downloadPhoto(@PathParam("id") String id) {
        String fileName = "photo_" + id + "_.png";
        return Response.ok(userService.downloadPhoto(id)).header("Content-Disposition", String.format("attachment; filename=%s", fileName)).build();
    }


    private String processFormat(String format) {
        return "xml".equals(format) ? MediaType.APPLICATION_XML : MediaType.APPLICATION_JSON;
    }
}