-- -----------------------------------------------------
-- Schema searchdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `searchdb`;

-- -----------------------------------------------------
-- Schema searchdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `searchdb` DEFAULT CHARACTER SET utf8;
USE `searchdb`;

-- -----------------------------------------------------
-- Table `searchdb`.`country_name`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `searchdb`.`country_name`;

CREATE TABLE IF NOT EXISTS `searchdb`.`country_name` (
  `id_country` INT(10) UNSIGNED NOT NULL,
  `name_country` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_country`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `searchdb`.`registration_number`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `searchdb`.`registration_number`;

CREATE TABLE IF NOT EXISTS `searchdb`.`registration_number` (
  `id_registr` INT(10) UNSIGNED NOT NULL,
  `name_registr` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_registr`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `searchdb`.`registr_country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `searchdb`.`registr_country`;

CREATE TABLE IF NOT EXISTS `searchdb`.`registr_country` (
  `registr_id` INT(10) UNSIGNED NOT NULL,
  `country_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`registr_id`, `country_id`),
  INDEX `fk_country_id_idx` (`country_id` ASC),
  INDEX `fk_registr_id_idx` (`registr_id` ASC),
  CONSTRAINT `fk_country_id`
  FOREIGN KEY (`registr_id`)
  REFERENCES `searchdb`.`country_name` (`id_country`),
  CONSTRAINT `fk_registr_id`
  FOREIGN KEY (`registr_id`)
  REFERENCES `searchdb`.`registration_number` (`id_registr`))
  ENGINE = InnoDB;