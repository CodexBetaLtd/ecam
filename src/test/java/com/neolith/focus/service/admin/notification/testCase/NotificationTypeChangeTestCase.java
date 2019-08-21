package com.neolith.focus.service.admin.notification.testCase;

import com.neolith.focus.common.TestCase;
import com.neolith.focus.constants.NotificationType;
import com.neolith.focus.dao.admin.UserDao;
import com.neolith.focus.dao.biz.NotificationDao;
import com.neolith.focus.dto.biz.notification.NotificationDTO;
import com.neolith.focus.model.biz.notification.Notification;
import com.neolith.focus.result.admin.UserResult;
import com.neolith.focus.result.notification.NotificationResult;
import com.neolith.focus.service.admin.api.UserService;
import com.neolith.focus.service.admin.notification.NotificationData;
import com.neolith.focus.service.admin.user.UserDummyData;
import com.neolith.focus.service.notification.api.NotificationService;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by neo89 on 12/20/16.
 */
@Component
public class NotificationTypeChangeTestCase extends TestCase {

    protected final String RESULT_IS_ERROR = "isError";
    protected NotificationDTO notificationDTO;
    protected NotificationResult resultDetail;
    protected UserResult saveUserSenderResult;
    protected UserResult saveUserReceiverResult;
    protected boolean isError;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationDao notificationDao;

    @Override
    protected void checkActualResults() throws Exception {
        setActualResult(RESULT_IS_ERROR, isError);
    }


    protected void preCondition() throws Exception {

        saveUserSenderResult = userService.save(UserDummyData.getInstance().getDummyUserDTOOne());
        saveUserReceiverResult = userService.save(UserDummyData.getInstance().getDummyUserDTOTwo());

        notificationDTO = NotificationData.getInstance().getDummyNotificationDTO();
        Assert.assertNotNull("Notification DTO should not be NULL.", notificationDTO);
        notificationDTO.setSentUserId(saveUserSenderResult.getIntegerList().get(0));
        notificationDTO.setReceivedUserId(saveUserReceiverResult.getIntegerList().get(0));
        resultDetail = notificationService.saveNotification(notificationDTO);
        Assert.assertEquals("Two Notification should be found", 2, resultDetail.getIntegerList().size());
    }

    protected void postCondition() throws Exception {
        List<Integer> integers = resultDetail.getIntegerList();
        for (Integer id : integers) {
            notificationService.deleteNotification(id);
        }
        userDao.delete(saveUserSenderResult.getIntegerList().get(0));
        userDao.delete(saveUserReceiverResult.getIntegerList().get(0));
    }


    @Override
    protected void executeTest() throws Exception {
        try {
            preCondition();
            testSaveNotificationTypeChange(resultDetail);
        } catch (Exception e) {
            isError = true;
        } finally {
            postCondition();
        }
    }


    protected void testSaveNotificationTypeChange(NotificationResult resultDetail) throws Exception {
        Notification senderNotification = notificationDao.findOne(resultDetail.getIntegerList().get(0));
        Notification receiverNotification = notificationDao.findOne(resultDetail.getIntegerList().get(1));
        Assert.assertEquals("Sender Notification Equals to Receiver Notification (Sender)", senderNotification.getSender().getId(), receiverNotification.getSender().getId());
        Assert.assertEquals("Sender Notification Equals to Receiver Notification (Receiver)", senderNotification.getReceiver().getId(), receiverNotification.getReceiver().getId());
        Assert.assertEquals("Sender Notification Equals to Sender Type", NotificationType.OUTBOX_NOTIFICATION.getId(), senderNotification.getNotificationType().getId());
        Assert.assertEquals("Receiver Notification Equals to Receiver Type", NotificationType.INBOX_NOTIFICATION.getId(), receiverNotification.getNotificationType().getId());

    }




}
