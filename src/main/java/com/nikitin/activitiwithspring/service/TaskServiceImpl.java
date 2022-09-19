package com.nikitin.activitiwithspring.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
	int randomData = (int) (Math.random() * 200);
	delegateExecution.setVariable("randomData", randomData);
    }
}
