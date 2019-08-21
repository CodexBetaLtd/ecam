package com.neolith.focus.dao.maintenance;

import java.util.Date;
import java.util.Set;
 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neolith.focus.constants.SMTriggerType;
import com.neolith.focus.model.maintenance.scheduledmaintenance.ScheduledMaintenanceTrigger;
import com.neolith.focus.repository.FocusDataTableRepository;

@Repository
public interface ScheduledMaintenanceTriggerDao extends FocusDataTableRepository<ScheduledMaintenanceTrigger, Integer> {

	@Query("select smt from ScheduledMaintenanceTrigger as smt join smt.ttNextCalenderEvent as nce "
			+ "where smt.triggerType = :type and nce.scheduledDate >= :startTime and nce.scheduledDate <= :endTime")
	Set<ScheduledMaintenanceTrigger> findCurrentHourTriggers(@Param("startTime") Date startTime, @Param("endTime") Date endTime,
			@Param("type") SMTriggerType type);

	@Query("SELECT COUNT(smt) FROM ScheduledMaintenanceTrigger AS smt JOIN smt.ttNextCalenderEvent AS nce "
			+ "WHERE nce.scheduledDate >= :from and nce.scheduledDate <= :to")
	Integer getSMTriggerCount(@Param("from")Date fromDate, @Param("to")Date toDate);

}
