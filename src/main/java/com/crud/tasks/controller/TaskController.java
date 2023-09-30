package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;
    @GetMapping(value = "getTasks")
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }
    @GetMapping(value = "getTaskById")
    public TaskDto getTaskById(@RequestParam Long taskId)
    {
        return taskMapper.mapToTaskDto(Objects.requireNonNull(service.getTaskById(taskId).orElse(null)));
    }

    @GetMapping
    public List<TaskDto> getTask() {

        return new ArrayList<>();
    }
    @GetMapping(value = "{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) {
        return new TaskDto(1L, "test title", "test_content");
    }
    @DeleteMapping
    public void deleteTask(Long taskId) {

    }
    @PutMapping
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(2L, "Edited test title", "Test content");
    }
    @PostMapping
    public void createTask(TaskDto taskDto) {

    }
}
