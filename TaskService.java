
package com.yourcompany.workforcemgmt.service;

import com.yourcompany.workforcemgmt.model.Task;
import com.yourcompany.workforcemgmt.util.Status;
import com.yourcompany.workforcemgmt.util.Priority;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final Map<Long, Task> taskMap = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Task createTask(String title, LocalDate startDate, LocalDate dueDate, Long staffId) {
        Task task = new Task();
        task.setId(idGenerator.getAndIncrement());
        task.setTitle(title);
        task.setStartDate(startDate);
        task.setDueDate(dueDate);
        task.setStaffId(staffId);
        task.setStatus(Status.ACTIVE);
        task.setPriority(Priority.MEDIUM);
        taskMap.put(task.getId(), task);
        return task;
    }

    public List<Task> getAllActiveTasks() {
        return taskMap.values().stream()
                .filter(task -> task.getStatus() != Status.CANCELLED)
                .collect(Collectors.toList());
    }

    public Task getTaskById(Long id) {
        return taskMap.get(id);
    }
}
