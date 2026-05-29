package com.nira.things;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        List<Task> pendingTasks = tasks.stream().filter(t -> !t.getCompleted()).toList();
        List<Task> doneTasks    = tasks.stream().filter(t ->  t.getCompleted()).toList();

        model.addAttribute("tasks", tasks);
        model.addAttribute("pendingTasks", pendingTasks);
        model.addAttribute("doneTasks", doneTasks);

        return "tasks";
    }

    @PostMapping
    public String addTask(@RequestParam String title){
        taskService.createTask(title);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @PutMapping("/{id}")
    public String toggleTask(@PathVariable Long id){
        taskService.toggleTask(id);
        return "redirect:/";
    }
}
