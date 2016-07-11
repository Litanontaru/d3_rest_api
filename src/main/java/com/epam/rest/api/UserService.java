package com.epam.rest.api;

import com.epam.rest.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Andrei_Yakushin
 * @since 7/11/2016 12:48 PM
 */
@Path("user")
public class UserService {
    private static final Map<String, User> USER_MAP = new ConcurrentHashMap<>();
    private static final AtomicInteger NEXT_INDEX = new AtomicInteger(0);

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String read(@PathParam("id") String id) {
        User user = USER_MAP.get(id);
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(user);
    }

    @POST
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String create(@PathParam("name") String name) {
        String id = Integer.toString(NEXT_INDEX.getAndIncrement());
        User user = new User();
        user.setName(name);
        user.setId(id);
        USER_MAP.put(id, user);
        Gson gson = new GsonBuilder().create();
        return gson.toJson(user);
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String update(@PathParam("id") String id, String modification) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        User user = USER_MAP.get(id);
        if (user != null) {
            try {
                User change = gson.fromJson(modification, User.class);
                if (change.getName() != null) {
                    user.setName(change.getName());
                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                user = null;
            }
        }
        return gson.toJson(user);
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") String id) {
        User user = USER_MAP.remove(id);
        if (user == null) {
            return "Not found";
        } else {
            return "Done";
        }
    }
}
