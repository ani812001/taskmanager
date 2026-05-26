package com.example.taskmanager.controller;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/tasks")
    public String taskList(Model model) {
        model.addAttribute("tasks", service.getAllTasks());
        return "task-list";
    }

    @GetMapping("/add")
    public String addTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "add-task";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task) {
        service.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/task/{id}")
    public String taskDetails(@PathVariable Long id, Model model) {
        model.addAttribute("task", service.getTaskById(id));
        return "task-details";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("task", service.getTaskById(id));
        return "update-task";
    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task) {
        service.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
        return "redirect:/tasks";
    }
}