package com.nikitin.activitiwithspring.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nikitin.activitiwithspring.entity.HistoryTask;
import com.nikitin.activitiwithspring.repository.HistoryTaskRepo;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitiService {

    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final HistoryTaskRepo historyTaskRepo;

    @Autowired
    public ActivitiService(RuntimeService runtimeService, TaskService taskService, HistoryTaskRepo historyTaskRepo) {
	this.runtimeService = runtimeService;
	this.taskService = taskService;
	this.historyTaskRepo = historyTaskRepo;

    }

    public void start(String postValue) {
	runtimeService.createProcessInstanceBuilder()
			.processDefinitionKey("oneTaskProcess")
			.variable("postValue", postValue)
			.start();
    }

    public List<Map<String, Object>> getTasks() {
	List<Task> resultTask = taskService.createTaskQuery().taskName("TaskWork").list();

	List<Map<String, Object>> customTaskList = new ArrayList<>();
	for (Task task : resultTask) {
	    Map<String, Object> map = new LinkedHashMap<>();
	    map.put("taskId", task.getId());
	    map.put("taskDefinitionKey", task.getTaskDefinitionKey());

	    customTaskList.add(map);
	}
	return customTaskList;
    }

    public String complete(String id) {
	List<Task> resultTask = taskService.createTaskQuery().taskId(id).list();
	if(!resultTask.isEmpty()) {
	    saveCompleteTaskHistory(resultTask, id);
	    taskService.complete(id);
	    return "task found - complete";
	} else {
	    return "task not fount";
	}
    }

    public void saveCompleteTaskHistory(List<Task> completeTask, String id) {
	HistoryTask historyTask = new HistoryTask();
	for(Task task : completeTask) {
	    historyTask.setTaskId(task.getId());
	    historyTask.setTaskDefinitionKey(task.getTaskDefinitionKey());
	    historyTask.setPostValue((String) taskService.getVariable(id, "postValue"));
	    historyTask.setRandomData((Integer) taskService.getVariable(id, "randomData"));
	    historyTask.setSum(Integer.parseInt(historyTask.getPostValue()) + historyTask.getRandomData());
	}
	historyTaskRepo.save(historyTask);
    }

    public List<HistoryTask> result() {
	return historyTaskRepo.findAll();
    }
}
