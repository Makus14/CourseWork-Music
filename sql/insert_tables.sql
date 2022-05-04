USE music;

-- -----------------------------------------------------
-- fill `account` table
-- -----------------------------------------------------
INSERT INTO `music`.`account` (login, password, role) VALUES ('admin', '$2a$10$.hnnAJoGqnJEuQyI5sGnCecI68/oKttMGwN2UnVw./68B2xULF7iy', 'ADMIN');
INSERT INTO `music`.`account` (login, password, role) VALUES ('user', '$2a$10$tphOpZhMFU7iZ8E6b0NxNuc0Y6ezKybYSB.ulBmPnTR2UTyeJe/iS', 'CLIENT');

-- -----------------------------------------------------
-- fill `user` table
-- -----------------------------------------------------
INSERT INTO `music`.`user` (account_id, first_name, last_name, email, subscription) VALUES (2, 'user', 'user', 'user@gmail.com', 1);

-- -----------------------------------------------------
-- fill `genre` table
-- -----------------------------------------------------
INSERT INTO `music`.`genre` (`name`) VALUES ('ALTERNATIVE');
INSERT INTO `music`.`genre` (`name`) VALUES ('BLUES');
INSERT INTO `music`.`genre` (`name`) VALUES ('CHILDREN');
INSERT INTO `music`.`genre` (`name`) VALUES ('CLASSICAL');
INSERT INTO `music`.`genre` (`name`) VALUES ('COUNTRY');
INSERT INTO `music`.`genre` (`name`) VALUES ('DANCE');
INSERT INTO `music`.`genre` (`name`) VALUES ('ELECTRONIC');
INSERT INTO `music`.`genre` (`name`) VALUES ('HIP_HOP');
INSERT INTO `music`.`genre` (`name`) VALUES ('INDIE');
INSERT INTO `music`.`genre` (`name`) VALUES ('JAZZ');
INSERT INTO `music`.`genre` (`name`) VALUES ('METAL');
INSERT INTO `music`.`genre` (`name`) VALUES ('POP');
INSERT INTO `music`.`genre` (`name`) VALUES ('PUNK');
INSERT INTO `music`.`genre` (`name`) VALUES ('R&B');
INSERT INTO `music`.`genre` (`name`) VALUES ('RAP');
INSERT INTO `music`.`genre` (`name`) VALUES ('REGGAE');
INSERT INTO `music`.`genre` (`name`) VALUES ('RETRO POP');
INSERT INTO `music`.`genre` (`name`) VALUES ('ROCK');


