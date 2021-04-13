-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tweetapp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tweetapp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tweetapp` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema tweetapp
-- -----------------------------------------------------
USE `tweetapp` ;

-- -----------------------------------------------------
-- Table `tweetapp`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tweetapp`.`users` (
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `gender` VARCHAR(45) NOT NULL,
  `dob` DATE NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `isloggedin` BOOLEAN NULL,
  PRIMARY KEY (`first_name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tweetapp`.`tweets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tweetapp`.`tweets` (
  `email` VARCHAR(50) NOT NULL,
  `tweets` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`tweets`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
