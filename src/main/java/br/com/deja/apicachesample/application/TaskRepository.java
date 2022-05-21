package br.com.deja.apicachesample.application;

import java.util.List;

public interface TaskRepository {
    List<Task> getAll();

    Task getById(String id);

    void deleteById(String id);

    Task save(Task task);
}
