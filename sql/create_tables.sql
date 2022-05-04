USE music;

-- -----------------------------------------------------
-- Table `music`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `account`
(
    `id`       int                     NOT NULL AUTO_INCREMENT,
    `login`    varchar(45)             NOT NULL,
    `password` varchar(255)            NOT NULL,
    `role`     enum ('ADMIN','CLIENT') NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `login_UNIQUE` (`login`)
);

-- -----------------------------------------------------
-- Table `music`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user`
(
    `account_id`   int         NOT NULL,
    `first_name`   varchar(45) NOT NULL,
    `last_name`    varchar(45) NOT NULL,
    `email`        varchar(45) NOT NULL,
    `subscription` tinyint     NOT NULL DEFAULT '0',
    PRIMARY KEY (`account_id`),
    UNIQUE KEY `email_UNIQUE` (`email`),
    CONSTRAINT `fk_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table `music`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `genre`
(
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`name`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
);


-- -----------------------------------------------------
-- Table `music`.`artist`
-- -----------------------------------------------------
CREATE TABLE `artist`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `name`       varchar(45) NOT NULL,
    `image_path` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

-- -----------------------------------------------------
-- Table `music`.`album`
-- -----------------------------------------------------
CREATE TABLE `album`
(
    `id`        int         NOT NULL AUTO_INCREMENT,
    `name`      varchar(45) NOT NULL,
    `year`      int DEFAULT NULL,
    `artist_id` int DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `artist_id_idx` (`artist_id`),
    CONSTRAINT `fk_album_artist_id` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table `music`.`track`
-- -----------------------------------------------------
CREATE TABLE `track`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `name`       varchar(45) NOT NULL,
    `audio_path` varchar(60) NOT NULL,
    `genre_name` varchar(20) DEFAULT NULL,
    `album_id`   int         DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `album_id_idx` (`album_id`),
    KEY `genre_name_FK_idx` (`genre_name`),
    CONSTRAINT `album_id_FK` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT `genre_name_FK` FOREIGN KEY (`genre_name`) REFERENCES `genre` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table `music`.`artist_track`
-- -----------------------------------------------------
CREATE TABLE `artist_track`
(
    `artist_id` int NOT NULL,
    `track_id`  int NOT NULL,
    KEY `artist_id_idx` (`artist_id`),
    KEY `fk_track_id_idx` (`track_id`),
    CONSTRAINT `fk_artist_id` FOREIGN KEY (`artist_id`) REFERENCES `artist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_track_id` FOREIGN KEY (`track_id`) REFERENCES `track` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table `music`.`playlist`
-- -----------------------------------------------------
CREATE TABLE `playlist`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `name`       varchar(45) NOT NULL,
    `visible`    tinyint     NOT NULL DEFAULT '0',
    `account_id` int                  DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_playlist_account_id_idx` (`account_id`),
    CONSTRAINT `fk_playlist_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table `music`.`playlist_track`
-- -----------------------------------------------------
CREATE TABLE `playlist_track`
(
    `playlist_id` int NOT NULL,
    `track_id`    int NOT NULL,
    KEY `fk_playlist_id_idx` (`playlist_id`),
    KEY `fk_track_id_idx` (`track_id`),
    CONSTRAINT `fk_playlist_id` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_track_playlist_id` FOREIGN KEY (`track_id`) REFERENCES `track` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table `music`.`subscriber`
-- -----------------------------------------------------
CREATE TABLE `subscriber`
(
    `user_id`       int NOT NULL,
    `subscriber_id` int NOT NULL,
    KEY `fk_subscriber_user_id_idx` (`user_id`),
    KEY `fk_subscriber_id_idx` (`subscriber_id`),
    CONSTRAINT `fk_subscriber_id` FOREIGN KEY (`subscriber_id`) REFERENCES `user` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_subscriber_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`account_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `comment`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `text`       varchar(700) NOT NULL,
    `date`       datetime     NOT NULL,
    `path`       varchar(255) NOT NULL,
    `track_id`   int          NOT NULL,
    `account_id` int          NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_com_track_id_idx` (`track_id`),
    KEY `fk_com_account_id_idx` (`account_id`),
    CONSTRAINT `fk_com_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_com_track_id` FOREIGN KEY (`track_id`) REFERENCES `track` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);