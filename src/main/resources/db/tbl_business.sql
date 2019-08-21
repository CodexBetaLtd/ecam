USE `focus_test`;

DROP TABLE IF EXISTS `tbl_business`;
CREATE TABLE `tbl_business` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `business_classification_id` int(11) NOT NULL,
  `business_type_id` int(11) NOT NULL,
  `country_id` int(11) NOT NULL,
  `primary_currency_id` int(11) NOT NULL,
  `default_company` tinyint(1) DEFAULT NULL,
  `group_quotes` tinyint(1) DEFAULT NULL,
  `our_business` tinyint(1) DEFAULT NULL,
  `preferred_business` tinyint(1) DEFAULT NULL,
  `public_community` tinyint(1) DEFAULT NULL,
  `is_focus_source_vendor` tinyint(1) DEFAULT NULL,
  `last_scheduled_maintenance_run_time` datetime DEFAULT NULL,
  `focus_source_seller_id` int(11) DEFAULT NULL,
  `sys_code` int(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `business_corp_id` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `community_password` varchar(45) DEFAULT NULL,
  `community_private_key` varchar(45) DEFAULT NULL,
  `fax` varchar(15) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `notes` varchar(100) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `phone2` varchar(15) DEFAULT NULL,
  `postal_code` varchar(45) DEFAULT NULL,
  `primary_contact` varchar(45) DEFAULT NULL,
  `primary_email` varchar(45) DEFAULT NULL,
  `secondary_email` varchar(45) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `timezone` varchar(45) DEFAULT NULL,
  `web_site` varchar(45) DEFAULT NULL,
  `version` int(11) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` int(11) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tbl_business_1_idx` (`business_classification_id`) USING BTREE,
  KEY `fk_tbl_business_2_idx` (`business_type_id`) USING BTREE,
  KEY `fk_tbl_business_1_idx1` (`primary_currency_id`),
  CONSTRAINT `tbl_business_fk_1` FOREIGN KEY (`business_classification_id`) REFERENCES `tbl_business_classification` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tbl_business_fk_2` FOREIGN KEY (`business_type_id`) REFERENCES `tbl_business_type_def` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tbl_business_fk_3` FOREIGN KEY (`primary_currency_id`) REFERENCES `tbl_currency` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION