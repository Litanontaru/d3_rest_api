package com.epam.rest.api;

import com.epam.rest.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.epam.rest.model.User.USER_MAP;

/**
 * @author Andrei_Yakushin
 * @since 7/11/2016 2:04 PM
 */
@Path("users")
public class UsersService {
    @GET
    @Path("like/{mask}")
    @Produces(MediaType.APPLICATION_JSON)
    public String read(@PathParam("mask") String mask) {
        Pattern pattern = Pattern.compile(mask);
        List<User> users = USER_MAP
                .values()
                .stream()
                .filter(user -> pattern.matcher(user.getName()).find())
                .collect(Collectors.toList());
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(users);
    }
}
