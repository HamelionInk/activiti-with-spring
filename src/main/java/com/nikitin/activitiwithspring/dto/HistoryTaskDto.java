package com.nikitin.activitiwithspring.dto;

public class HistoryTaskDto {
    private String taskId;
    private String taskDefinitionKey;
    private int postValue;
    private int randomData;
    private int sum;

    public HistoryTaskDto() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public int getPostValue() {
        return postValue;
    }

    public void setPostValue(int postValue) {
        this.postValue = postValue;
    }

    public int getRandomData() {
        return randomData;
    }

    public void setRandomData(int randomData) {
        this.randomData = randomData;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
