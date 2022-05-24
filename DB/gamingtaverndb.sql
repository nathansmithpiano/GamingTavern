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
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `role` VARCHAR(45) NOT NULL DEFAULT 0,
  `first_name` VARCHAR(45) NULL,
  `middle_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `image_url` VARCHAR(100) NULL,
  `created` TIMESTAMP NOT NULL,
  `updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `endorsement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `endorsement` ;

CREATE TABLE IF NOT EXISTS `endorsement` (
  `id` INT NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `description_UNIQUE` (`description` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `received_endorsements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `received_endorsements` ;

CREATE TABLE IF NOT EXISTS `received_endorsements` (
  `user_id` INT NOT NULL,
  `endorsement_id` INT NOT NULL,
  `created` TIMESTAMP NOT NULL,
  PRIMARY KEY (`user_id`, `endorsement_id`),
  INDEX `fk_user_endorsement_endorsement1_idx` (`endorsement_id` ASC),
  INDEX `fk_user_endorsement_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_endorsement_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_endorsement_endorsement1`
    FOREIGN KEY (`endorsement_id`)
    REFERENCES `endorsement` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `alias` ;

CREATE TABLE IF NOT EXISTS `alias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `is_active` TINYINT NOT NULL DEFAULT 1,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
  `name` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NULL,
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
-- Table `service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `service` ;

CREATE TABLE IF NOT EXISTS `service` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_friends`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_friends` ;

CREATE TABLE IF NOT EXISTS `user_friends` (
  `user_id` INT NOT NULL,
  `friend_id` INT NOT NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
-- Table `user_blocks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_blocks` ;

CREATE TABLE IF NOT EXISTS `user_blocks` (
  `user_id` INT NOT NULL,
  `block_id` INT NOT NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
-- Table `game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `game` ;

CREATE TABLE IF NOT EXISTS `game` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `rating` VARCHAR(45) NULL,
  `studio` VARCHAR(45) NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `abbreviation` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state_province` VARCHAR(45) NULL,
  `zip` INT NULL,
  `country` VARCHAR(45) NULL,
  `timezone` VARCHAR(45) NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `clan` ;

CREATE TABLE IF NOT EXISTS `clan` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `server`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `server` ;

CREATE TABLE IF NOT EXISTS `server` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `game_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `ip` VARCHAR(45) NULL,
  `url` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `capacity` INT NULL,
  `description` VARCHAR(255) NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_server_game1_idx` (`game_id` ASC),
  CONSTRAINT `fk_server_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event` ;

CREATE TABLE IF NOT EXISTS `event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `date` DATETIME NULL,
  `timezone` VARCHAR(45) NULL,
  `capacity` INT NULL,
  `description` VARCHAR(255) NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `chat` ;

CREATE TABLE IF NOT EXISTS `chat` (
  `id` INT NOT NULL,
  `description` VARCHAR(255) NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `message` ;

CREATE TABLE IF NOT EXISTS `message` (
  `id` INT NOT NULL,
  `chat_id` INT NOT NULL,
  `contents` VARCHAR(45) NOT NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `fk_message_chat1_idx` (`chat_id` ASC),
  CONSTRAINT `fk_message_chat1`
    FOREIGN KEY (`chat_id`)
    REFERENCES `chat` (`id`)
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
-- Table `service_game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `service_game` ;

CREATE TABLE IF NOT EXISTS `service_game` (
  `service_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  PRIMARY KEY (`service_id`, `game_id`),
  INDEX `fk_service_game_game1_idx` (`game_id` ASC),
  INDEX `fk_service_game_service1_idx` (`service_id` ASC),
  CONSTRAINT `fk_service_game_service1`
    FOREIGN KEY (`service_id`)
    REFERENCES `service` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_game_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
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
-- Table `alias_service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `alias_service` ;

CREATE TABLE IF NOT EXISTS `alias_service` (
  `alias_id` INT NOT NULL,
  `service_id` INT NOT NULL,
  PRIMARY KEY (`alias_id`, `service_id`),
  INDEX `fk_alias_service_service1_idx` (`service_id` ASC),
  INDEX `fk_alias_service_alias1_idx` (`alias_id` ASC),
  CONSTRAINT `fk_alias_service_alias1`
    FOREIGN KEY (`alias_id`)
    REFERENCES `alias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_alias_service_service1`
    FOREIGN KEY (`service_id`)
    REFERENCES `service` (`id`)
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
-- Table `alias_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `alias_event` ;

CREATE TABLE IF NOT EXISTS `alias_event` (
  `alias_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`alias_id`, `event_id`),
  INDEX `fk_alias_event_event1_idx` (`event_id` ASC),
  INDEX `fk_alias_event_alias1_idx` (`alias_id` ASC),
  CONSTRAINT `fk_alias_event_alias1`
    FOREIGN KEY (`alias_id`)
    REFERENCES `alias` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_alias_event_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event_location` ;

CREATE TABLE IF NOT EXISTS `event_location` (
  `event_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  PRIMARY KEY (`event_id`, `location_id`),
  INDEX `fk_event_location_location1_idx` (`location_id` ASC),
  INDEX `fk_event_location_event1_idx` (`event_id` ASC),
  CONSTRAINT `fk_event_location_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_location_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event_game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event_game` ;

CREATE TABLE IF NOT EXISTS `event_game` (
  `event_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  PRIMARY KEY (`event_id`, `game_id`),
  INDEX `fk_event_game_game1_idx` (`game_id` ASC),
  INDEX `fk_event_game_event1_idx` (`event_id` ASC),
  CONSTRAINT `fk_event_game_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_game_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_chat` ;

CREATE TABLE IF NOT EXISTS `user_chat` (
  `user_id` INT NOT NULL,
  `chat_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `chat_id`),
  INDEX `fk_user_chat_chat1_idx` (`chat_id` ASC),
  INDEX `fk_user_chat_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_chat_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_chat_chat1`
    FOREIGN KEY (`chat_id`)
    REFERENCES `chat` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sent_endorsements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sent_endorsements` ;

CREATE TABLE IF NOT EXISTS `sent_endorsements` (
  `user_id` INT NOT NULL,
  `endorsement_id` INT NOT NULL,
  `created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`, `endorsement_id`),
  INDEX `fk_user_endorsement1_endorsement1_idx` (`endorsement_id` ASC),
  INDEX `fk_user_endorsement1_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_endorsement1_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_endorsement1_endorsement1`
    FOREIGN KEY (`endorsement_id`)
    REFERENCES `endorsement` (`id`)
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
INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `role`, `first_name`, `middle_name`, `last_name`, `image_url`, `created`, `updated`) VALUES (1, 'admin', '$2a$10$xkJUK7zUTr0fzqJINIbCO.szpKVuGxYH4jII5XA2CTlFBwkH/sDeO', 1, 'ROLE_ADMIN', NULL, NULL, NULL, NULL, DEFAULT, DEFAULT);

COMMIT;

