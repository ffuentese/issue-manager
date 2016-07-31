SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `issuemanager` DEFAULT CHARACTER SET utf8 ;
USE `issuemanager` ;

-- -----------------------------------------------------
-- Table `issuemanager`.`hash`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issuemanager`.`hash` (
  `user_id` INT(11) NOT NULL,
  `hash` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
INSERT INTO `hash` VALUES (1,'mmu');

-- -----------------------------------------------------
-- Table `issuemanager`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issuemanager`.`role` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
INSERT INTO `issuemanager`.`role` (`id`, `name`) VALUES (1, 'Administrator');
INSERT INTO `issuemanager`.`role` (`id`, `name`) VALUES (2, 'Team Member');
INSERT INTO `issuemanager`.`role` (`id`, `name`) VALUES (3, 'Team Leader');

-- -----------------------------------------------------
-- Table `issuemanager`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issuemanager`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NULL DEFAULT NULL,
  `username` VARCHAR(15) NULL DEFAULT NULL,
  `password` VARCHAR(40) NULL DEFAULT NULL,
  `role_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `FK_USER_ROLE_idx` (`role_id` ASC),
  CONSTRAINT `FK_USER_ROLE`
    FOREIGN KEY (`role_id`)
    REFERENCES `issuemanager`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;
INSERT INTO `issuemanager`.`user`
(`id`,
`name`,
`username`,
`password`,
`role_id`)
VALUES
(1,
'admin',
'admin',
'2a6e7e2ba2585f14dafcaf329478bd393e66daef',
1);


-- -----------------------------------------------------
-- Table `issuemanager`.`workflow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issuemanager`.`workflow` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;
INSERT INTO `workflow` VALUES (1,'Example','This is an example'),(2,'Coliflor','Libro de Joan');

-- -----------------------------------------------------
-- Table `issuemanager`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issuemanager`.`project` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `workflow_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_PROJECT_WORKFLOW_idx` (`workflow_id` ASC),
  CONSTRAINT `FK_PROJECT_WORKFLOW`
    FOREIGN KEY (`workflow_id`)
    REFERENCES `issuemanager`.`workflow` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;
INSERT INTO `project` VALUES (2,'IssueManager',1);

-- -----------------------------------------------------
-- Table `issuemanager`.`issue`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issuemanager`.`issue` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL DEFAULT NULL,
  `creator_id` INT(11) NULL DEFAULT NULL,
  `project_id` INT(11) NULL DEFAULT NULL,
  `date` DATETIME NULL DEFAULT NULL,
  `owner_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_ISSUE_PROJECT_idx` (`project_id` ASC),
  INDEX `FK_ISSUE_STATUS_idx` (`name` ASC),
  INDEX `FK_ISSUE_CREATOR_idx` (`creator_id` ASC),
  INDEX `FK_ISSUE_OWNER_idx` (`owner_id` ASC),
  CONSTRAINT `FK_ISSUE_CREATOR`
    FOREIGN KEY (`creator_id`)
    REFERENCES `issuemanager`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ISSUE_OWNER`
    FOREIGN KEY (`owner_id`)
    REFERENCES `issuemanager`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_ISSUE_PROJECT`
    FOREIGN KEY (`project_id`)
    REFERENCES `issuemanager`.`project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `issuemanager`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issuemanager`.`status` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `workflow_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_STATUS_WORKFLOW_idx` (`workflow_id` ASC),
  CONSTRAINT `FK_STATUS_WORKFLOW`
    FOREIGN KEY (`workflow_id`)
    REFERENCES `issuemanager`.`workflow` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;
INSERT INTO `status` VALUES (1,'Open',1),(2,'In Review',1),(3,'None',1),(4,'Open',2);

-- -----------------------------------------------------
-- Table `issuemanager`.`issuelog`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `issuemanager`.`issuelog` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(250) NULL DEFAULT NULL,
  `issue_id` INT(11) NOT NULL,
  `status_id` INT(11) NOT NULL,
  `date` DATETIME NULL DEFAULT NULL,
  `asignee_id` INT(11) NOT NULL,
  `priority` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_ISSUELOG_ISSUE_idx` (`issue_id` ASC),
  INDEX `FK_ISSUELOG_STATUS_idx` (`status_id` ASC),
  INDEX `FK_ISSUELOG_ASIGNEE_idx` (`asignee_id` ASC),
  CONSTRAINT `FK_ISSUELOG_ASSIGNEE`
    FOREIGN KEY (`asignee_id`)
    REFERENCES `issuemanager`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
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
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
