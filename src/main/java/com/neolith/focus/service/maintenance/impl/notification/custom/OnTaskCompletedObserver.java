package com.neolith.focus.service.maintenance.impl.notification.custom;

import com.neolith.focus.dto.maintenance.workOrder.WorkOrderDTO;
import com.neolith.focus.service.maintenance.api.EmailAndNotificationSender;

public class OnTaskCompletedObserver implements EmailNotificationObserver {


	EmailAndNotificationSender emailAndNotificationSender;

	public OnTaskCompletedObserver(EmailAndNotificationSender emailAndNotificationSender) {
		this.emailAndNotificationSender = emailAndNotificationSender;
	}

	@Override
	public void update(WorkOrderDTO workOrderDTO) {
		onTaskCompleted(workOrderDTO);
	}


	public void onTaskCompleted(WorkOrderDTO workOrderDTO){
		try {
			//            String email = "";
			//            String subject = "Work Order Task Completed";
			//            String message = " Work Order Task Completed Email Body";
			//            emailAndNotificationSender.sendMail(subject,message);
			//            emailAndNotificationSender.sendNotification(subject,message);
		}catch (Exception ex){
			ex.printStackTrace();
			throw new RuntimeException();
		}

	}
}
