package com.nikitin.activitiwithspring.repository;

import java.util.UUID;

import com.nikitin.activitiwithspring.entity.HistoryTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryTaskRepo extends JpaRepository<HistoryTask, UUID> {
}
