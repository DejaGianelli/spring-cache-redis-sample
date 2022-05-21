package br.com.deja.apicachesample.infra.repository;

import br.com.deja.apicachesample.application.Task;
import br.com.deja.apicachesample.application.TaskRepository;
import br.com.deja.apicachesample.infra.utils.LongRunningTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryTaskRepository.class);

    private final List<Task> tasks = new ArrayList<>();

    public InMemoryTaskRepository() {
        this.tasks.add(new Task("9d107bb8-f141-4cc4-9dcd-1c7196f26791", "Lorem ipsum cras blandit"));
        this.tasks.add(new Task("fbd74089-3312-4f79-afe7-29dac09339a8", "Lorem dui ultrices nam"));
        this.tasks.add(new Task("e471b4fa-621b-498d-ae78-b82bf965cfeb", "Tempor himenaeos sollicitudin nostra"));
    }

    @Override
    @Cacheable(value = "task", key = "#root.methodName")
    public List<Task> getAll() {
        logger.info("Executing a long running task...");
        LongRunningTask.simulate(5000);
        return tasks;
    }

    @Override
    @Cacheable(value = "task", key = "#id", unless = "#result == null")
    public Task getById(String id) {
        logger.info("Executing a long running task...");
        LongRunningTask.simulate(5000);
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    @CacheEvict(value = "task", key = "#id")
    public void deleteById(String id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    @Override
    @CachePut(value = "task", key = "#task.id")
    public Task save(Task task) {

        var optionalTask = tasks.stream()
                .filter(t -> t.getId().equals(task.getId()))
                .findFirst();

        if (optionalTask.isEmpty()) {
            tasks.add(task);
            return task;
        }

        tasks.set(tasks.indexOf(optionalTask.get()), task);
        return task;
    }
}
