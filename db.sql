SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `issuemanager` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `issuemanager` ;

-- -----------------------------------------------------
-- Table `issuemanager`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `issuemanager`.`role` ;

CREATE TABLE IF NOT EXISTS `issuemanager`.`role` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `issuemanager`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `issuemanager`.`user` ;

CREATE TABLE IF NOT EXISTS `issuemanager`.`user` (
  `id` INT NOT NULL,
  `name` VARCHAR(60) NULL,
  `username` VARCHAR(15) NULL,
  `password` VARCHAR(40) NULL,
  `role_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `FK_USER_ROLE_idx` (`role_id` ASC),
  CONSTRAINT `FK_USER_ROLE`
    FOREIGN KEY (`role_id`)
    REFERENCES `issuemanager`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `issuemanager`.`project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `issuemanager`.`project` ;

CREATE TABLE IF NOT EXISTS `issuemanager`.`project` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `issuemanager`.`workflow`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `issuemanager`.`workflow` ;

CREATE TABLE IF NOT EXISTS `issuemanager`.`workflow` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `issuemanager`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `issuemanager`.`status` ;

CREATE TABLE IF NOT EXISTS `issuemanager`.`status` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `workflow_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_STATUS_WORKFLOW_idx` (`workflow_id` ASC),
  CONSTRAINT `FK_STATUS_WORKFLOW`
    FOREIGN KEY (`workflow_id`)
    REFERENCES `issuemanager`.`workflow` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `issuemanager`.`issue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `issuemanager`.`issue` ;

CREATE TABLE IF NOT EXISTS `issuemanager`.`issue` (
  `id` INT NOT NULL,
  `status_id` INT NULL,
  `creator_id` INT NULL,
  `project_id` INT NULL,
  `description` TEXT NULL,
  `date` DATETIME NULL,
  `assignee_id` INT NULL,
  `owner_id` INT NULL,
  `priority` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_ISSUE_PROJECT_idx` (`project_id` ASC),
  INDEX `FK_ISSUE_STATUS_idx` (`status_id` ASC),
  INDEX `FK_ISSUE_CREATOR_idx` (`creator_id` ASC),
  INDEX `FK_ISSUE_ASSIGNEE_idx` (`assignee_id` ASC),
  INDEX `FK_ISSUE_OWNER_idx` (`owner_id` ASC),
  CONSTRAINT `FK_ISSUE_PROJECT`
    FOREIGN KEY (`project_id`)
    REFERENCES `issuemanager`.`project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ISSUE_STATUS`
    FOREIGN KEY (`status_id`)
    REFERENCES `issuemanager`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ISSUE_CREATOR`
    FOREIGN KEY (`creator_id`)
    REFERENCES `issuemanager`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ISSUE_ASSIGNEE`
    FOREIGN KEY (`assignee_id`)
    REFERENCES `issuemanager`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ISSUE_OWNER`
    FOREIGN KEY (`owner_id`)
    REFERENCES `issuemanager`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `issuemanager`.`issuelog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `issuemanager`.`issuelog` ;

CREATE TABLE IF NOT EXISTS `issuemanager`.`issuelog` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `message` VARCHAR(250) NULL,
  `issue_id` INT NULL,
  `status_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_ISSUELOG_ISSUE_idx` (`issue_id` ASC),
  INDEX `FK_ISSUELOG_STATUS_idx` (`status_id` ASC),
  CONSTRAINT `FK_ISSUELOG_ISSUE`
    FOREIGN KEY (`issue_id`)
    REFERENCES `issuemanager`.`issue` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ISSUELOG_STATUS`
    FOREIGN KEY (`status_id`)
    REFERENCES `issuemanager`.`status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `issuemanager`.`hash`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `issuemanager`.`hash` ;

CREATE TABLE IF NOT EXISTS `issuemanager`.`hash` (
  `user_id` INT NOT NULL,
  `hash` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FK_USER_HASH`
    FOREIGN KEY (`user_id`)
    REFERENCES `issuemanager`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
