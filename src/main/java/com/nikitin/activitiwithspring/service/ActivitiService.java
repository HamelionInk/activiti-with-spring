package com.nikitin.activitiwithspring.service;

import java.util.List;
import java.util.Map;

import com.nikitin.activitiwithspring.entity.HistoryTask;
import org.activiti.engine.task.Task;

public interface ActivitiService {
    void start(int postValue);
    List<Map<String, Object>> getTasks();
    String complete(String id);
    void saveCompleteTaskHistory(List<Task> completeTask, String id);
    List<HistoryTask> result();

}
