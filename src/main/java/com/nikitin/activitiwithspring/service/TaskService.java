package com.nikitin.activitiwithspring.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
	int randomData = (int) (Math.random() * 200);
	System.out.println(randomData);
	delegateExecution.setVariable("randomData", randomData);
    }
}
