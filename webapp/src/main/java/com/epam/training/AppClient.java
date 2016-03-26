package com.epam.training;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;

/**
 * @author vkrasovsky
 */
public class AppClient {
    private static final String LOCAL_URL = "http://localhost:8080";

    private static final String PATH_CREATE = "webapi/users";
    private static final String PATH_UPDATE = "webapi/users/%s";
    private static final String PATH_DELETE = "webapi/users/%s";
    private static final String PATH_GET = "webapi/users/%s";
    private static final String PATH_GET_ALL = "webapi/users";

    private static final String PATH_UPLOAD_PHOTO = "webapi/users/%s/uploadphoto";
    private static final String PATH_DOWNLOAD_PHOTO = "webapi/users/%s/downloadphoto";

    private static final String FORMAT_RESPONSE_JSON = "json";
    private static final String FORMAT_RESPONSE_XML = "xml";

    Client client = ClientBuilder.newClient().register(MultiPartFeature.class);

    public User create(User user) {
        WebTarget target = client.target(LOCAL_URL).path(PATH_CREATE).queryParam("format", FORMAT_RESPONSE_XML);
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
        return response.readEntity(User.class);
    }

    public User update(User user) {
        WebTarget target = client.target(LOCAL_URL).path(String.format(PATH_UPDATE, user.getEmail())).queryParam("format", FORMAT_RESPONSE_JSON);
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_XML_TYPE).put(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
        return response.readEntity(User.class);
    }

    public User get(String id) {
        WebTarget target = client.target(LOCAL_URL).path(String.format(PATH_GET, id));
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_XML_TYPE).get();
        return response.readEntity(User.class);
    }

    public void delete(String id) {
        WebTarget target = client.target(LOCAL_URL).path(String.format(PATH_DELETE, id));
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_XML_TYPE).delete();
    }

    public Collection<User> getAll() {
        WebTarget target = client.target(LOCAL_URL).path(PATH_GET_ALL);
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_XML_TYPE).get();
        return response.readEntity(new GenericType<Collection<User>>() {
        });
    }

    public void uploadPhoto(String id) {
        URL resource = Resources.getResource("Cartman___The_Coon___vector_by_V3NU.png");
        File fileToUpload = new File(resource.getFile());

        FormDataMultiPart multiPart = new FormDataMultiPart();
        multiPart.bodyPart(new FileDataBodyPart("file1", fileToUpload, MediaType.APPLICATION_OCTET_STREAM_TYPE));

        WebTarget target = client.target(LOCAL_URL).path(String.format(PATH_UPLOAD_PHOTO, id));
        Response response = target.request().post(Entity.entity(multiPart, MediaType.MULTIPART_FORM_DATA_TYPE));
    }

    public void downloadPhoto(String id) {
        WebTarget target = client.target(LOCAL_URL).path(String.format(PATH_DOWNLOAD_PHOTO, id));
        Response response = target.request().get();
        byte[] bytes = response.readEntity(byte[].class);
        File file = new File("webapp/target/downloaded.png");
        try {
            Files.write(bytes, file);
        } catch (IOException e) {
            //
        }
    }
}
