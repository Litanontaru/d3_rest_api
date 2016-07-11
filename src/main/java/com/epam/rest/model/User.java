package com.epam.rest.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Andrei_Yakushin
 * @since 7/11/2016 12:55 PM
 */
public class User {
    public static final Map<String, User> USER_MAP = new ConcurrentHashMap<>();
    public static final AtomicInteger NEXT_INDEX = new AtomicInteger(0);

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
