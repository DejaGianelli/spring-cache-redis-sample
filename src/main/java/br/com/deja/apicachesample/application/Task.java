package br.com.deja.apicachesample.application;

import java.io.Serializable;

public class Task implements Serializable {

    private String id;
    private String description;

    public Task() {
    }

    public Task(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
