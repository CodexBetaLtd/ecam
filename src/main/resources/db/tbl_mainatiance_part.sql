USE `focus_test`;

DROP TABLE IF EXISTS `tbl_mainatiance_part`;
CREATE TABLE `mainatiance_part` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` int(11) NOT NULL,
  `modified_date` datetime NOT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `part_id` int(11) DEFAULT NULL,
  `mantainace_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;