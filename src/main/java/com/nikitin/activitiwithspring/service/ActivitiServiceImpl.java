package com.nikitin.activitiwithspring.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nikitin.activitiwithspring.dto.HistoryTaskDto;
import com.nikitin.activitiwithspring.entity.HistoryTask;
import com.nikitin.activitiwithspring.mapper.HistoryTaskMapper;
import com.nikitin.activitiwithspring.repository.HistoryTaskRepository;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitiServiceImpl implements ActivitiService {

    private final RuntimeService runtimeService;
    private final TaskService taskService;
    private final HistoryTaskRepository historyTaskRepository;
    private final HistoryTaskMapper historyTaskMapper;

    @Autowired
    public ActivitiServiceImpl(RuntimeService runtimeService, TaskService taskService, HistoryTaskRepository historyTaskRepository,
		    HistoryTaskMapper historyTaskMapper) {
	this.runtimeService = runtimeService;
	this.taskService = taskService;
	this.historyTaskRepository = historyTaskRepository;
	this.historyTaskMapper = historyTaskMapper;
    }

    @Override
    public void start(int postValue) {
	runtimeService.createProcessInstanceBuilder()
			.processDefinitionKey("oneTaskProcess")
			.variable("postValue", postValue)
			.start();
    }

    @Override
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

    @Override
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

    @Override
    public void saveCompleteTaskHistory(List<Task> completeTask, String id) {
	HistoryTaskDto historyTaskDto = new HistoryTaskDto();
	for(Task task : completeTask) {
	    historyTaskDto.setTaskId(task.getId());
	    historyTaskDto.setTaskDefinitionKey(task.getTaskDefinitionKey());
	    historyTaskDto.setPostValue((Integer) taskService.getVariable(id, "postValue"));
	    historyTaskDto.setRandomData((Integer) taskService.getVariable(id, "randomData"));
	    historyTaskDto.setSum(historyTaskDto.getPostValue() + historyTaskDto.getRandomData());
	}
	historyTaskRepository.save(historyTaskMapper.toEntity(historyTaskDto));
    }

    @Override
    public List<HistoryTask> result() {
	return historyTaskRepository.findAll();
    }
}
