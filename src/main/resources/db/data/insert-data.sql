insert into app_user (id, first_name, last_name, date_of_birth, email, username, password) values ('042d7409-491a-4abe-a7d3-170395420f60', 'Sasindu', 'Jayalath', '1983-10-13', 'sss@fff.lk', 'manjula', '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.');

INSERT INTO  tbl_asset(id,version,created_by,created_date,modified_by,modified_date,is_deleted,`name`,code,description,make,model,serial_no,notes,asset_category_id,asset_status_id,department_id,asset_offline_tracker_id,asset_owner_id,parent_asset_id,warranty_id,asset_event_id) VALUES ('1', '0', '1', '2016-12-08 00:00:00', null, null, '0', 'Asset 01', 'A0001', 'This is Asset One Des', null, 'asset model ', null, null, '10', null, null, null, null, null, null, null, '0', null, null, null, null, null);
INSERT INTO  tbl_asset(id,version,created_by,created_date,modified_by,modified_date,is_deleted,`name`,code,description,make,model,serial_no,notes,asset_category_id,asset_status_id,department_id,asset_offline_tracker_id,asset_owner_id,parent_asset_id,warranty_id,asset_event_id) VALUES ('3', '0', '1', '2016-12-09 14:03:53', null, null, '0', 'Machine 1', 'M0001', 'Machine detail', 'Boge', 'A1234', '123derr45', 'asdas ad asdasdasd', '11', null, null, null, null, null, null, null, '1', 'asdasdas das dasdas', 'Kurunegala', 'Wayamba', '45452', '0');

INSERT INTO  tbl_asset_catogery(id,version,created_by,created_date,modified_by,modified_date,is_deleted,`name`,parent_id,override_rules,description,sys_code) VALUES ('10', '2', '1', '2016-11-30 12:33:36', '1', '2016-12-07 15:03:21', '0', 'Locations or Facilities', null, 'sass', 'Locations or Facilities Detail', '1');
INSERT INTO  tbl_asset_catogery(id,version,created_by,created_date,modified_by,modified_date,is_deleted,`name`,parent_id,override_rules,description,sys_code) VALUES ('11', '2', '1', '2016-11-30 12:46:37', '1', '2016-12-07 15:03:41', '0', 'Equipments or Machines', null, 'we', 'Equipment or Machines Detail', '0');
INSERT INTO tbl_asset_catogery(id,version,created_by,created_date,modified_by,modified_date,is_deleted,`name`,parent_id,override_rules,description,sys_code)  VALUES ('12', '0', '1', '2016-12-07 15:02:58', '1', null, '0', 'Tools', null, 'test', 'Tools Detail', '2');

INSERT INTO  tbl_maintainance_type(id,version,created_by,created_date,modified_by,modified_date,is_deleted,`name`,description) VALUES ('1', '0', '1', '2016-12-05 11:47:16', null, null, '0', 'Priventive', 'wwwwwwwwwwwwwwww', 'qq');

INSERT INTO  tbl_meter_reading_unit(id,version,created_by,created_date,modified_by,modified_date,is_deleted,`name`,decimles) VALUES ('9', '0', '1', '2016-11-30 19:05:45', null, null, '0', 'wasantha yapa13', '2', '$');

INSERT INTO tbl_notification(id,recipient_id,sender_id,notification_type,`subject`,content,is_open,version,is_popup,is_deleted,created_by,created_date,modified_by,modified_date,is_trashed) VALUES ('6', '6', '1', '2', 'sub 01', 'Test Content ', '0', '0', '2', '0', '1', '2016-12-07 02:24:39', '1', '2016-12-07 03:17:13', '0');
INSERT INTO tbl_notification(id,recipient_id,sender_id,notification_type,`subject`,content,is_open,version,is_popup,is_deleted,created_by,created_date,modified_by,modified_date,is_trashed) VALUES ('7', '6', '1', '1', 'sub 01', 'Test Content ', '0', '1', '0', '0', '1', '2016-12-07 02:24:39', null, null, null);
INSERT INTO tbl_notification(id,recipient_id,sender_id,notification_type,`subject`,content,is_open,version,is_popup,is_deleted,created_by,created_date,modified_by,modified_date,is_trashed) VALUES ('10', '1', '5', '2', 'from Sadun TO ADMIN', 'TST', '0', '0', '1', '0', '5', '2016-12-07 02:42:15', '5', '2016-12-07 03:06:00', '1');
INSERT INTO tbl_notification(id,recipient_id,sender_id,notification_type,`subject`,content,is_open,version,is_popup,is_deleted,created_by,created_date,modified_by,modified_date,is_trashed) VALUES ('11', '1', '5', '1', 'from Sadun TO ADMIN', 'TST', '0', '1', '1', '0', '5', '2016-12-07 02:42:16', '1', '2016-12-09 17:23:27', '1');
INSERT INTO tbl_notification(id,recipient_id,sender_id,notification_type,`subject`,content,is_open,version,is_popup,is_deleted,created_by,created_date,modified_by,modified_date,is_trashed)VALUES ('12', '5', '1', '2', 'From [Admin] to [Sadun]', 'From admin to sadun', '0', '0', '0', '0', '1', '2016-12-07 02:52:19', null, null, null);
INSERT INTO tbl_notification(id,recipient_id,sender_id,notification_type,`subject`,content,is_open,version,is_popup,is_deleted,created_by,created_date,modified_by,modified_date,is_trashed)VALUES ('13', '5', '1', '1', 'From [Admin] to [Sadun]', 'From admin to sadun', '0', '1', '1', '0', '1', '2016-12-07 02:52:19', '5', '2016-12-07 03:05:51', '1');
INSERT INTO tbl_notification(id,recipient_id,sender_id,notification_type,`subject`,content,is_open,version,is_popup,is_deleted,created_by,created_date,modified_by,modified_date,is_trashed) VALUES ('14', '6', '1', '2', 'Test', 'Con', '0', '0', '0', '0', '1', '2016-12-09 17:22:59', null, null, null);
INSERT INTO tbl_notification(id,recipient_id,sender_id,notification_type,`subject`,content,is_open,version,is_popup,is_deleted,created_by,created_date,modified_by,modified_date,is_trashed) VALUES ('15', '6', '1', '1', 'Test', 'Con', '0', '1', '0', '0', '1', '2016-12-09 17:23:00', null, null, null);


INSERT INTO  tbl_user(id,api_user,is_active,email_all_alert,full_name,image_path,business_approve_date,business_approve_status,business_user_private_key,currency_id,acc_expiry_date,approval_expiry_date,request_date,default_login_location,email_address,email_all_msg,email_system_error,hourly_rate,internal_mail_all_msg,last_login,notes,notify_expire_days_before,notify_on_DWOE,notify_on_DOAsset,notify_on_WOAssigned,notify_on_WOCompleted,notify_on_WODraft,notify_on_WOOnHold,notify_on_WOOpen,notify_on_WORequested,notify_on_WOWith_NoAsset,notfiy_on_WOwork_InProgress,personal_code,send_mail_on_expire,sys_code,system_level_user,telephone_1,telephone_2,user_approve_date,user_approve_status,user_status,user_title,welcome_email_sent,version,is_deleted,created_by,created_date,modified_by,modified_date,address) VALUES ('1', '1', '1', '0', 'Sasindu Ayya', null, null, null, null, null, null, null, null, null, null, 'sasindu@neolithtech.com', '0', '0', null, '0', null, 'dsfcsdfsdf', null, null, null, null, null, null, null, null, null, null, null, '123', '0', null, '1', '01111111111', '', null, null, null, 'Mr.', '0', '2', '0', '1', '2016-11-24 00:00:00', '1', '2016-12-08 13:07:14');
INSERT INTO  tbl_user(id,api_user,is_active,email_all_alert,full_name,image_path,business_approve_date,business_approve_status,business_user_private_key,currency_id,acc_expiry_date,approval_expiry_date,request_date,default_login_location,email_address,email_all_msg,email_system_error,hourly_rate,internal_mail_all_msg,last_login,notes,notify_expire_days_before,notify_on_DWOE,notify_on_DOAsset,notify_on_WOAssigned,notify_on_WOCompleted,notify_on_WODraft,notify_on_WOOnHold,notify_on_WOOpen,notify_on_WORequested,notify_on_WOWith_NoAsset,notfiy_on_WOwork_InProgress,personal_code,send_mail_on_expire,sys_code,system_level_user,telephone_1,telephone_2,user_approve_date,user_approve_status,user_status,user_title,welcome_email_sent,version,is_deleted,created_by,created_date,modified_by,modified_date,address) VALUES ('5', '1', '0', '1', 'Sadun', null, null, null, null, null, null, null, null, null, null, 'sadun@gmail.com', '1', '1', null, '1', null, 'This is Note ', null, null, null, null, null, null, null, null, null, null, null, '1600', '1', null, '1', '0112123456', '0716396731', null, null, null, 'Mr', '1', '1', '0', '1', '2016-12-02 10:06:02', '1', '2016-12-08 13:08:33');
INSERT INTO  tbl_user(id,api_user,is_active,email_all_alert,full_name,image_path,business_approve_date,business_approve_status,business_user_private_key,currency_id,acc_expiry_date,approval_expiry_date,request_date,default_login_location,email_address,email_all_msg,email_system_error,hourly_rate,internal_mail_all_msg,last_login,notes,notify_expire_days_before,notify_on_DWOE,notify_on_DOAsset,notify_on_WOAssigned,notify_on_WOCompleted,notify_on_WODraft,notify_on_WOOnHold,notify_on_WOOpen,notify_on_WORequested,notify_on_WOWith_NoAsset,notfiy_on_WOwork_InProgress,personal_code,send_mail_on_expire,sys_code,system_level_user,telephone_1,telephone_2,user_approve_date,user_approve_status,user_status,user_title,welcome_email_sent,version,is_deleted,created_by,created_date,modified_by,modified_date,address) VALUES ('6', '1', '1', '0', 'wasantha', null, null, null, null, null, null, null, null, null, null, 'wasanthabr93@gmail.com', null, null, null, null, null, 'dsfcsdfsdf', null, null, null, null, null, null, null, null, null, null, null, '201', null, null, null, null, null, null, null, null, null, null, '0', null, '1', '2016-12-01 10:06:02', '1', '2016-12-01 10:06:02');
INSERT INTO tbl_user(id,api_user,is_active,email_all_alert,full_name,image_path,business_approve_date,business_approve_status,business_user_private_key,currency_id,acc_expiry_date,approval_expiry_date,request_date,default_login_location,email_address,email_all_msg,email_system_error,hourly_rate,internal_mail_all_msg,last_login,notes,notify_expire_days_before,notify_on_DWOE,notify_on_DOAsset,notify_on_WOAssigned,notify_on_WOCompleted,notify_on_WODraft,notify_on_WOOnHold,notify_on_WOOpen,notify_on_WORequested,notify_on_WOWith_NoAsset,notfiy_on_WOwork_InProgress,personal_code,send_mail_on_expire,sys_code,system_level_user,telephone_1,telephone_2,user_approve_date,user_approve_status,user_status,user_title,welcome_email_sent,version,is_deleted,created_by,created_date,modified_by,modified_date,address)VALUES ('8', null, '0', '1', 'Jana', 'This is KKKKKKKKKK', null, null, null, null, null, null, null, null, null, 'abc@gmail.com', '0', '0', '23', '0', null, '', null, null, null, null, null, null, null, null, null, null, null, 'a', '0', null, '1', '', '', null, null, null, 'Mr', '0', '1', '0', '1', '2016-12-09 14:18:15', '1', '2016-12-09 17:20:45');
INSERT INTO tbl_user(id,api_user,is_active,email_all_alert,full_name,image_path,business_approve_date,business_approve_status,business_user_private_key,currency_id,acc_expiry_date,approval_expiry_date,request_date,default_login_location,email_address,email_all_msg,email_system_error,hourly_rate,internal_mail_all_msg,last_login,notes,notify_expire_days_before,notify_on_DWOE,notify_on_DOAsset,notify_on_WOAssigned,notify_on_WOCompleted,notify_on_WODraft,notify_on_WOOnHold,notify_on_WOOpen,notify_on_WORequested,notify_on_WOWith_NoAsset,notfiy_on_WOwork_InProgress,personal_code,send_mail_on_expire,sys_code,system_level_user,telephone_1,telephone_2,user_approve_date,user_approve_status,user_status,user_title,welcome_email_sent,version,is_deleted,created_by,created_date,modified_by,modified_date,address) VALUES ('9', null, '0', '0', 'Kevin', 'Kevn Ptsen ', null, null, null, null, null, null, null, null, null, 'kevin@gmail.com', '0', '0', null, '0', null, '', null, null, null, null, null, null, null, null, null, null, null, '', '0', null, '0', '', '', null, null, null, 'Mr', '0', '0', '0', '1', '2016-12-09 14:25:30', null, null);
INSERT INTO  tbl_user(id,api_user,is_active,email_all_alert,full_name,image_path,business_approve_date,business_approve_status,business_user_private_key,currency_id,acc_expiry_date,approval_expiry_date,request_date,default_login_location,email_address,email_all_msg,email_system_error,hourly_rate,internal_mail_all_msg,last_login,notes,notify_expire_days_before,notify_on_DWOE,notify_on_DOAsset,notify_on_WOAssigned,notify_on_WOCompleted,notify_on_WODraft,notify_on_WOOnHold,notify_on_WOOpen,notify_on_WORequested,notify_on_WOWith_NoAsset,notfiy_on_WOwork_InProgress,personal_code,send_mail_on_expire,sys_code,system_level_user,telephone_1,telephone_2,user_approve_date,user_approve_status,user_status,user_title,welcome_email_sent,version,is_deleted,created_by,created_date,modified_by,modified_date,address) VALUES ('11', null, '0', '0', 'Nirmal', 'No 520, Kalpitiya. ', null, null, null, null, null, null, null, null, null, 'nirmal@gmail.com', '0', '0', '150', '0', null, '', null, null, null, null, null, null, null, null, null, null, null, 'code 150', '0', null, '0', '', '', null, null, null, 'Mr', '0', '0', '0', '1', '2016-12-09 15:25:37', null, null);

INSERT INTO tbl_user_credentials(id,user_id,user_name,`password`,version,is_deleted,created_by,created_date,modified_by,modified_date) VALUES ('1', '1', 'admin', '$2a$10$Lodk.Nl.eDs7Mz24.Fwa1OO84A/jL9VF87cRFh20dA1HtIEXy6PgS', '0', '0', '1', '2016-11-24 00:00:00', null, null);
INSERT INTO tbl_user_credentials(id,user_id,user_name,`password`,version,is_deleted,created_by,created_date,modified_by,modified_date)  VALUES ('2', '5', 'sadun', '$2a$10$Lodk.Nl.eDs7Mz24.Fwa1OO84A/jL9VF87cRFh20dA1HtIEXy6PgS', '0', '0', '1', '2016-11-24 00:00:00', null, null);
INSERT INTO tbl_user_credentials(id,user_id,user_name,`password`,version,is_deleted,created_by,created_date,modified_by,modified_date)  VALUES ('6', '1', 'wasantha', '$2a$10$Lodk.Nl.eDs7Mz24.Fwa1OO84A/jL9VF87cRFh20dA1HtIEXy6PgS', '0', '0', '1', '2016-11-24 00:00:00', null, null);


INSERT INTO tbl_user_group(id,version,created_by,created_date,modified_by,modified_date,is_deleted,`name`,description) VALUES ('1', '1', '1', '2016-11-24 13:32:39', '1', '2016-11-24 13:34:10', '0', 'Admin', 'Admin Group Detail');
INSERT INTO tbl_user_group(id,version,created_by,created_date,modified_by,modified_date,is_deleted,`name`,description) VALUES ('2', '2', '1', '2016-11-24 13:33:06', '1', '2016-11-24 13:42:50', '0', 'HR Manager', 'HR Manager Group Detail');

INSERT INTO  tbl_user_site(id,version,is_deleted,created_by,modified_by,created_date,modified_date,user_id,site_id)VALUES ('1', '0', '0', '1', null, '2016-12-08 09:56:43', null, null, null);
INSERT INTO tbl_user_site(id,version,is_deleted,created_by,modified_by,created_date,modified_date,user_id,site_id) VALUES ('10', '0', '0', '1', null, '2016-12-08 12:46:55', null, null, null);
INSERT INTO tbl_user_site(id,version,is_deleted,created_by,modified_by,created_date,modified_date,user_id,site_id) VALUES ('11', '0', '0', '1', null, '2016-12-08 12:56:55', null, null, null);
INSERT INTO tbl_user_site(id,version,is_deleted,created_by,modified_by,created_date,modified_date,user_id,site_id) VALUES ('13', '0', '0', '1', null, '2016-12-09 14:25:30', null, '9', '1');
INSERT INTO tbl_user_site(id,version,is_deleted,created_by,modified_by,created_date,modified_date,user_id,site_id) VALUES ('14', '0', '0', '1', null, '2016-12-09 14:25:30', null, '9', '3');
INSERT INTO tbl_user_site(id,version,is_deleted,created_by,modified_by,created_date,modified_date,user_id,site_id) VALUES ('15', '0', '0', '1', null, '2016-12-09 14:28:05', null, null, null);

INSERT INTO  tbl_user_site_group(id,version,is_deleted,created_by,created_date,modified_by,modified_date,user_site_id,group_id) VALUES ('1', '0', '0', '1', '2016-12-08 09:56:43', null, null, '1', '1');
INSERT INTO tbl_user_site_group(id,version,is_deleted,created_by,created_date,modified_by,modified_date,user_site_id,group_id) VALUES ('2', '0', '0', '1', '2016-12-08 09:56:43', null, null, '1', '2');
INSERT INTO tbl_user_site_group(id,version,is_deleted,created_by,created_date,modified_by,modified_date,user_site_id,group_id) VALUES ('12', '0', '0', '1', '2016-12-08 12:46:56', null, null, '10', '1');
INSERT INTO tbl_user_site_group(id,version,is_deleted,created_by,created_date,modified_by,modified_date,user_site_id,group_id) VALUES ('13', '0', '0', '1', '2016-12-08 12:46:56', null, null, '10', '2');
INSERT INTO tbl_user_site_group(id,version,is_deleted,created_by,created_date,modified_by,modified_date,user_site_id,group_id) VALUES ('14', '0', '0', '1', '2016-12-09 14:28:05', null, null, '15', '1');
INSERT INTO tbl_user_site_group(id,version,is_deleted,created_by,created_date,modified_by,modified_date,user_site_id,group_id) VALUES ('15', '0', '0', '1', '2016-12-09 14:28:05', null, null, '15', '2');





