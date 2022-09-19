package com.nikitin.activitiwithspring.service;

import org.springframework.stereotype.Service;

@Service
public class TaskWorkService {
    public void printA() {
	System.out.println("A");
    }

    public void printB() {
	System.out.println("B");
    }
}
