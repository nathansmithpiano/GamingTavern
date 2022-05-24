-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gamingtaverndb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `gamingtaverndb` ;

-- -----------------------------------------------------
-- Schema gamingtaverndb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gamingtaverndb` DEFAULT CHARACTER SET utf8 ;
USE `gamingtaverndb` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `role` VARCHAR(45) NOT NULL DEFAULT 0,
  `first_name` VARCHAR(45) NULL,
  `middle_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(1000) NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `endorsement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `endorsement` ;

CREATE TABLE IF NOT EXISTS `endorsement` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `image_url` VARCHAR(1000) NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `description_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `alias` ;

CREATE TABLE IF NOT EXISTS `alias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(1000) NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_alias_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_alias_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `equipment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `equipment` ;

CREATE TABLE IF NOT EXISTS `equipment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `model` VARCHAR(100) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_equipment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_equipment` ;

CREATE TABLE IF NOT EXISTS `user_equipment` (
  `user_id` INT NOT NULL,
  `equipment_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `equipment_id`),
  INDEX `fk_user_equipment_equipment1_idx` (`equipment_id` ASC),
  INDEX `fk_user_equipment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_equipment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_equipment_equipment1`
    FOREIGN KEY (`equipment_id`)
    REFERENCES `equipment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `platform`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `platform` ;

CREATE TABLE IF NOT EXISTS `platform` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `name` VARCHAR(100) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_friend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_friend` ;

CREATE TABLE IF NOT EXISTS `user_friend` (
  `user_id` INT NOT NULL,
  `friend_id` INT NOT NULL,
  `created` DATETIME NULL,
  PRIMARY KEY (`user_id`, `friend_id`),
  INDEX `fk_user_user_user3_idx` (`friend_id` ASC),
  INDEX `fk_user_user_user2_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_user_user2`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_user_user3`
    FOREIGN KEY (`friend_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `blocked_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `blocked_user` ;

CREATE TABLE IF NOT EXISTS `blocked_user` (
  `user_id` INT NOT NULL,
  `block_id` INT NOT NULL,
  `created` DATETIME NULL,
  `reason` TEXT NULL,
  PRIMARY KEY (`user_id`, `block_id`),
  INDEX `fk_user_user_user4_idx` (`block_id` ASC),
  INDEX `fk_user_user_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_user_user4`
    FOREIGN KEY (`block_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rating` ;

CREATE TABLE IF NOT EXISTS `rating` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game` ;

CREATE TABLE IF NOT EXISTS `game` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating_id` INT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `name` VARCHAR(255) NOT NULL,
  `studio` VARCHAR(45) NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  `image_url` VARCHAR(1000) NULL,
  `url` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `fk_game_rating1_idx` (`rating_id` ASC),
  CONSTRAINT `fk_game_rating1`
    FOREIGN KEY (`rating_id`)
    REFERENCES `rating` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `street` VARCHAR(100) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  `timezone` VARCHAR(45) NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clan` ;

CREATE TABLE IF NOT EXISTS `clan` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `creator_id` INT NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `name` VARCHAR(100) NOT NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(1000) NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_clan_alias1_idx` (`creator_id` ASC),
  CONSTRAINT `fk_clan_alias1`
    FOREIGN KEY (`creator_id`)
    REFERENCES `alias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `server`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `server` ;

CREATE TABLE IF NOT EXISTS `server` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `game_id` INT NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `name` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `ip` VARCHAR(45) NULL,
  `url` VARCHAR(1000) NULL,
  `password` VARCHAR(45) NULL,
  `capacity` INT NULL,
  `description` VARCHAR(255) NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_server_game1_idx` (`game_id` ASC),
  CONSTRAINT `fk_server_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meetup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meetup` ;

CREATE TABLE IF NOT EXISTS `meetup` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `date` DATETIME NULL,
  `timezone` VARCHAR(45) NULL,
  `capacity` INT NULL,
  `description` TEXT NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_meetup_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_meetup_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat` ;

CREATE TABLE IF NOT EXISTS `chat` (
  `id` INT NOT NULL,
  `created_by_user_id` INT NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `title` VARCHAR(100) NULL,
  `description` VARCHAR(255) NULL,
  `image_url` VARCHAR(100) NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_chat_user1_idx` (`created_by_user_id` ASC),
  CONSTRAINT `fk_chat_user1`
    FOREIGN KEY (`created_by_user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat_user` ;

CREATE TABLE IF NOT EXISTS `chat_user` (
  `chat_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `added` DATETIME NULL,
  PRIMARY KEY (`chat_id`, `user_id`),
  INDEX `fk_chat_user_user1_idx` (`user_id` ASC),
  INDEX `fk_chat_user_chat1_idx` (`chat_id` ASC),
  CONSTRAINT `fk_chat_user_chat1`
    FOREIGN KEY (`chat_id`)
    REFERENCES `chat` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `message` ;

CREATE TABLE IF NOT EXISTS `message` (
  `id` INT NOT NULL,
  `chat_user_chat_id` INT NOT NULL,
  `chat_user_user_id` INT NOT NULL,
  `message_id` INT NULL,
  `contents` TEXT NOT NULL,
  `created` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_message_chat_user1_idx` (`chat_user_chat_id` ASC, `chat_user_user_id` ASC),
  INDEX `fk_message_message1_idx` (`message_id` ASC),
  CONSTRAINT `fk_message_chat_user1`
    FOREIGN KEY (`chat_user_chat_id` , `chat_user_user_id`)
    REFERENCES `chat_user` (`chat_id` , `user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_message_message1`
    FOREIGN KEY (`message_id`)
    REFERENCES `message` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_location` ;

CREATE TABLE IF NOT EXISTS `user_location` (
  `user_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `location_id`),
  INDEX `fk_user_location_location1_idx` (`location_id` ASC),
  INDEX `fk_user_location_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_location_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_location_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alias_clan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `alias_clan` ;

CREATE TABLE IF NOT EXISTS `alias_clan` (
  `alias_id` INT NOT NULL,
  `clan_id` INT NOT NULL,
  PRIMARY KEY (`alias_id`, `clan_id`),
  INDEX `fk_alias_clan_clan1_idx` (`clan_id` ASC),
  INDEX `fk_alias_clan_alias1_idx` (`alias_id` ASC),
  CONSTRAINT `fk_alias_clan_alias1`
    FOREIGN KEY (`alias_id`)
    REFERENCES `alias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_alias_clan_clan1`
    FOREIGN KEY (`clan_id`)
    REFERENCES `clan` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alias_game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `alias_game` ;

CREATE TABLE IF NOT EXISTS `alias_game` (
  `alias_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  PRIMARY KEY (`alias_id`, `game_id`),
  INDEX `fk_alias_game_game1_idx` (`game_id` ASC),
  INDEX `fk_alias_game_alias1_idx` (`alias_id` ASC),
  CONSTRAINT `fk_alias_game_alias1`
    FOREIGN KEY (`alias_id`)
    REFERENCES `alias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_alias_game_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clan_game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clan_game` ;

CREATE TABLE IF NOT EXISTS `clan_game` (
  `clan_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  PRIMARY KEY (`clan_id`, `game_id`),
  INDEX `fk_clan_game_game1_idx` (`game_id` ASC),
  INDEX `fk_clan_game_clan1_idx` (`clan_id` ASC),
  CONSTRAINT `fk_clan_game_clan1`
    FOREIGN KEY (`clan_id`)
    REFERENCES `clan` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clan_game_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alias_server`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `alias_server` ;

CREATE TABLE IF NOT EXISTS `alias_server` (
  `alias_id` INT NOT NULL,
  `server_id` INT NOT NULL,
  PRIMARY KEY (`alias_id`, `server_id`),
  INDEX `fk_alias_server_server1_idx` (`server_id` ASC),
  INDEX `fk_alias_server_alias1_idx` (`alias_id` ASC),
  CONSTRAINT `fk_alias_server_alias1`
    FOREIGN KEY (`alias_id`)
    REFERENCES `alias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_alias_server_server1`
    FOREIGN KEY (`server_id`)
    REFERENCES `server` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clan_server`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clan_server` ;

CREATE TABLE IF NOT EXISTS `clan_server` (
  `clan_id` INT NOT NULL,
  `server_id` INT NOT NULL,
  PRIMARY KEY (`clan_id`, `server_id`),
  INDEX `fk_clan_server_server1_idx` (`server_id` ASC),
  INDEX `fk_clan_server_clan1_idx` (`clan_id` ASC),
  CONSTRAINT `fk_clan_server_clan1`
    FOREIGN KEY (`clan_id`)
    REFERENCES `clan` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clan_server_server1`
    FOREIGN KEY (`server_id`)
    REFERENCES `server` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_endorsement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_endorsement` ;

CREATE TABLE IF NOT EXISTS `user_endorsement` (
  `user_id` INT NOT NULL,
  `endorsed_user_id` INT NOT NULL,
  `endorsement_id` INT NOT NULL,
  `created` DATETIME NULL,
  PRIMARY KEY (`user_id`, `endorsed_user_id`, `endorsement_id`),
  INDEX `fk_user_endorsement1_endorsement1_idx` (`endorsement_id` ASC),
  INDEX `fk_user_endorsement1_user1_idx` (`user_id` ASC),
  INDEX `fk_user_endorsement_user1_idx` (`endorsed_user_id` ASC),
  CONSTRAINT `fk_user_endorsement1_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_endorsement1_endorsement1`
    FOREIGN KEY (`endorsement_id`)
    REFERENCES `endorsement` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_endorsement_user1`
    FOREIGN KEY (`endorsed_user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meetup_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meetup_location` ;

CREATE TABLE IF NOT EXISTS `meetup_location` (
  `meetup_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  PRIMARY KEY (`meetup_id`, `location_id`),
  INDEX `fk_meetup_location_location1_idx` (`location_id` ASC),
  INDEX `fk_meetup_location_meetup1_idx` (`meetup_id` ASC),
  CONSTRAINT `fk_meetup_location_meetup1`
    FOREIGN KEY (`meetup_id`)
    REFERENCES `meetup` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meetup_location_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meetup_game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meetup_game` ;

CREATE TABLE IF NOT EXISTS `meetup_game` (
  `meetup_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  PRIMARY KEY (`meetup_id`, `game_id`),
  INDEX `fk_meetup_game_game1_idx` (`game_id` ASC),
  INDEX `fk_meetup_game_meetup1_idx` (`meetup_id` ASC),
  CONSTRAINT `fk_meetup_game_meetup1`
    FOREIGN KEY (`meetup_id`)
    REFERENCES `meetup` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meetup_game_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `meetup_alias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `meetup_alias` ;

CREATE TABLE IF NOT EXISTS `meetup_alias` (
  `meetup_id` INT NOT NULL,
  `alias_id` INT NOT NULL,
  PRIMARY KEY (`meetup_id`, `alias_id`),
  INDEX `fk_meetup_alias_alias1_idx` (`alias_id` ASC),
  INDEX `fk_meetup_alias_meetup1_idx` (`meetup_id` ASC),
  CONSTRAINT `fk_meetup_alias_meetup1`
    FOREIGN KEY (`meetup_id`)
    REFERENCES `meetup` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_meetup_alias_alias1`
    FOREIGN KEY (`alias_id`)
    REFERENCES `alias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alias_platform`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `alias_platform` ;

CREATE TABLE IF NOT EXISTS `alias_platform` (
  `alias_id` INT NOT NULL,
  `platform_id` INT NOT NULL,
  PRIMARY KEY (`alias_id`, `platform_id`),
  INDEX `fk_alias_platform_platform1_idx` (`platform_id` ASC),
  INDEX `fk_alias_platform_alias1_idx` (`alias_id` ASC),
  CONSTRAINT `fk_alias_platform_alias1`
    FOREIGN KEY (`alias_id`)
    REFERENCES `alias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_alias_platform_platform1`
    FOREIGN KEY (`platform_id`)
    REFERENCES `platform` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `platform_game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `platform_game` ;

CREATE TABLE IF NOT EXISTS `platform_game` (
  `platform_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  PRIMARY KEY (`platform_id`, `game_id`),
  INDEX `fk_platform_game_game1_idx` (`game_id` ASC),
  INDEX `fk_platform_game_platform1_idx` (`platform_id` ASC),
  CONSTRAINT `fk_platform_game_platform1`
    FOREIGN KEY (`platform_id`)
    REFERENCES `platform` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_platform_game_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS gamingtavernuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'gamingtavernuser'@'localhost' IDENTIFIED BY 'gamingtavernuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'gamingtavernuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `gamingtaverndb`;
INSERT INTO `user` (`id`, `enabled`, `username`, `password`, `role`, `first_name`, `middle_name`, `last_name`, `description`, `image_url`, `created`, `updated`) VALUES (1, 1, 'admin', '$2a$10$xkJUK7zUTr0fzqJINIbCO.szpKVuGxYH4jII5XA2CTlFBwkH/sDeO', 'ROLE_ADMIN', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

COMMIT;

