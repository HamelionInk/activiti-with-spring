package com.nikitin.activitiwithspring.controllers;

import java.util.List;
import java.util.Map;

import com.nikitin.activitiwithspring.entity.HistoryTask;
import com.nikitin.activitiwithspring.service.ActivitiService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivitiController {

    private final ActivitiService activitiService;
    private final RepositoryService repositoryService;
    private final TaskService taskService;

    @Autowired
    public ActivitiController(ActivitiService activitiService, RepositoryService repositoryService, TaskService taskService) {
        this.activitiService = activitiService;
        this.taskService = taskService;
        this.repositoryService = repositoryService;
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    @ResponseBody
    public String startProcess(@RequestParam(name = "postValue") String postValue) {
        activitiService.start(postValue);
        return "Number of process definitions : " + repositoryService.createProcessDefinitionQuery().count()
                        + "\nNumber of tasks : " + taskService.createTaskQuery().count()
                        + "\nNumber of tasks after process start: " + taskService.createTaskQuery().count();
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public List<Map<String, Object>> getTasks() {
        return activitiService.getTasks();
    }

    @RequestMapping(value = "/complete_task/{id}", method = RequestMethod.GET)
    public String completeTask(@PathVariable String id) {
         return activitiService.complete(id);
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public List<HistoryTask> getResult() {
        return activitiService.result();
    }
}
