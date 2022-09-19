package com.nikitin.activitiwithspring.mapper;

import com.nikitin.activitiwithspring.dto.HistoryTaskDto;
import com.nikitin.activitiwithspring.entity.HistoryTask;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistoryTaskMapper {

    HistoryTaskDto toDto(HistoryTask entity);
    HistoryTask toEntity(HistoryTaskDto dto);

}
