
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


INSERT INTO `map_role_permission` VALUES ('1', null, null, null, null, null, '1', '1');

INSERT INTO `map_user_role` VALUES ('1', null, null, null, null, null, '1', '1');

INSERT INTO `permission` VALUES ('1', '10000', '2018-08-08 01:14:05', 'NORMAL', '10000', '2018-08-09 01:14:13', '1', '1', '11', '1', '1', '1', null, '1', '1.00', 'ENABLED', '111', '1');


INSERT INTO `role` VALUES ('1', null, null, null, null, null, 'ROLE_ADMIN', b'1', 'ROLE_ADMIN');


INSERT INTO `user` VALUES ('1', '10000', '2018-08-08 01:07:57', 'NORMAL', '10000', '2018-08-09 01:08:08', 'a', '111', null, 'admin', 'will@minlia.com', '1', '10000', '13333333333', 'will', 'WXOPENID', '$2a$10$RFjJZFShqs1BHEPuXPzVuudpX2oK509lpV7T4y6gYVM7w5fXtXVzS', 'ENABLED', '1', 'will');

SET FOREIGN_KEY_CHECKS = 1;
