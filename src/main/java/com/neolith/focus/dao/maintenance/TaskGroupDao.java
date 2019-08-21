package com.neolith.focus.dao.maintenance;

import com.neolith.focus.model.maintenance.task.TaskGroup;
import com.neolith.focus.repository.FocusDataTableRepository;
 
import org.springframework.stereotype.Repository;


@Repository
public interface TaskGroupDao extends FocusDataTableRepository<TaskGroup, Integer> {


}
