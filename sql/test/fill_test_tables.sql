-- -----------------------------------------------------
-- Fill Table `account`
-- -----------------------------------------------------
INSERT INTO `test_music`.`account` (`login`, `password`, `role`) VALUES ('admin', 'admin', 'ADMIN');
INSERT INTO `test_music`.`account` (`login`, `password`, `role`) VALUES ('user', 'user', 'CLIENT');


-- -----------------------------------------------------
-- Fill Table `genre`
-- -----------------------------------------------------
INSERT INTO `test_music`.`genre` (`name`) VALUES ('POP');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('INDIE');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('ROCK');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('METAL');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('ALTERNATIVE');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('ELECTRONIC');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('DANCE');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('RAP');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('HIP_HOP');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('R&B');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('JAZZ');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('BLUES');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('REGGAE');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('PUNK');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('CLASSICAL');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('RETRO POP');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('COUNTRY');
INSERT INTO `test_music`.`genre` (`name`) VALUES ('CHILDREN');

-- -----------------------------------------------------
-- Fill Table `user`
-- -----------------------------------------------------
INSERT INTO `test_music`.`user` (`first_name`, `last_name`, `email`, `subscription`, `account_id`)
    VALUES ('Наталья', 'Юруш', 'natallia.yurush@gmail.com', '1', '2');

-- -----------------------------------------------------
-- Fill Table `artist`
-- -----------------------------------------------------
INSERT INTO `test_music`.`artist` (`name`)
    VALUES ('Nizkiz');

-- -----------------------------------------------------
-- Fill Table `album`
-- -----------------------------------------------------
INSERT INTO `test_music`.`album` (`name`, `year`, `artist_id`)
    VALUES ('Правiлы', '2020', '1');
INSERT INTO `test_music`.`album` (`name`, `year`, `artist_id`)
    VALUES ('Синоптик', '2020', '1');


-- -----------------------------------------------------
-- Fill Table `track`
-- -----------------------------------------------------
INSERT INTO `test_music`.`track` (`name`, `audio_path`, `genre_name`, `album_id`)
    VALUES ('Правiлы', 'tracks/Nizkiz - Правілы.mp3', 'ROCK', '1');
INSERT INTO `test_music`.`track` (`name`, `audio_path`, `genre_name`, `album_id`)
    VALUES ('Цемра', 'пролд', 'ROCK', '3');
INSERT INTO `test_music`.`track` (`name`, `audio_path`, `genre_name`, `album_id`)
    VALUES ('Никому', 'рпаваыв', 'ROCK', '3');

-- -----------------------------------------------------
-- Fill Table `playlist`
-- -----------------------------------------------------
INSERT INTO `test_music`.`playlist` (`name`, `visible`, `account_id`) VALUES ('воля', '0', '2');



INSERT INTO artist_track (artist_id, track_id) VALUES (1, 4), (1, 5);




