package com.nikitin.activitiwithspring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.UUID;

@Entity
public class HistoryTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "history_id")
    private String taskId;

    @Column(name = "task_definition_key")
    private String taskDefinitionKey;

    @Column(name = "post_value")
    private int postValue;

    @Column(name = "random_data")
    private int randomData;

    @Column(name = "sum")
    private int sum;

    public HistoryTask() {
    }

    public UUID getId() {
	return id;
    }

    public void setId(UUID id) {
	this.id = id;
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
