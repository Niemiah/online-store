-- MySQL Script generated by MySQL Workbench
-- Fri Jun  2 06:30:42 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema online_database_schema
-- -----------------------------------------------------
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Users` (
  `user_id` INT NULL DEFAULT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NULL DEFAULT NULL,
  `last_name` VARCHAR(50) NULL DEFAULT NULL,
  `email` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`email` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `mydb`.`Products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Products` (
  `product_id` INT NULL DEFAULT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(50) NULL DEFAULT NULL,
  `product_description` VARCHAR(255) NULL DEFAULT NULL,
  `product_price` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`));


-- -----------------------------------------------------
-- Table `mydb`.`Categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Categories` (
  `category_id` INT NULL DEFAULT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`));


-- -----------------------------------------------------
-- Table `mydb`.`Product_Categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Product_Categories` (
  `product_id` INT NULL DEFAULT NULL,
  `category_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`product_id`, `category_id`),
  INDEX (`category_id` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`product_id`)
    REFERENCES `mydb`.`Products` (`product_id`)
    ON DELETE CASCADE,
  CONSTRAINT ``
    FOREIGN KEY (`category_id`)
    REFERENCES `mydb`.`Categories` (`category_id`)
    ON DELETE CASCADE);


-- -----------------------------------------------------
-- Table `mydb`.`Orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Orders` (
  `order_id` INT NULL DEFAULT NULL AUTO_INCREMENT,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  INDEX (`user_id` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`Users` (`user_id`)
    ON DELETE CASCADE);


-- -----------------------------------------------------
-- Table `mydb`.`Shipping_Methods`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Shipping_Methods` (
  `shipping_id` INT NULL DEFAULT NULL AUTO_INCREMENT,
  `method_name` VARCHAR(50) NULL DEFAULT NULL,
  `shipping_cost` DECIMAL(10,2) NULL DEFAULT NULL,
  `shipping_time` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`shipping_id`));


-- -----------------------------------------------------
-- Table `mydb`.`Order_Details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Order_Details` (
  `order_id` INT NULL DEFAULT NULL,
  `product_id` INT NULL DEFAULT NULL,
  `quantity` INT NULL DEFAULT NULL,
  `Shipping_Methods_shipping_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `product_id`, `Shipping_Methods_shipping_id`),
  INDEX (`product_id` ASC) VISIBLE,
  INDEX `fk_Order_Details_Shipping_Methods1_idx` (`Shipping_Methods_shipping_id` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`order_id`)
    REFERENCES `mydb`.`Orders` (`order_id`)
    ON DELETE CASCADE,
  CONSTRAINT ``
    FOREIGN KEY (`product_id`)
    REFERENCES `mydb`.`Products` (`product_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_Order_Details_Shipping_Methods1`
    FOREIGN KEY (`Shipping_Methods_shipping_id`)
    REFERENCES `mydb`.`Shipping_Methods` (`shipping_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`Addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Addresses` (
  `address_id` INT NULL DEFAULT NULL AUTO_INCREMENT,
  `address_line1` VARCHAR(100) NULL DEFAULT NULL,
  `city` VARCHAR(50) NULL DEFAULT NULL,
  `state` VARCHAR(50) NULL DEFAULT NULL,
  `postal_code` VARCHAR(10) NULL DEFAULT NULL,
  `country` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`address_id`));


-- -----------------------------------------------------
-- Table `mydb`.`Payments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Payments` (
  `payment_id` INT NULL DEFAULT NULL AUTO_INCREMENT,
  `user_id` INT NULL DEFAULT NULL,
  `card_number` VARCHAR(16) NULL DEFAULT NULL,
  `expiration_date` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  INDEX (`user_id` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`Users` (`user_id`)
    ON DELETE CASCADE);


-- -----------------------------------------------------
-- Table `mydb`.`Invoices`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Invoices` (
  `invoice_id` INT NULL DEFAULT NULL AUTO_INCREMENT,
  `order_id` INT NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  `payment_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`invoice_id`),
  INDEX (`order_id` ASC) VISIBLE,
  INDEX (`payment_id` ASC) VISIBLE,
  INDEX (`user_id` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`order_id`)
    REFERENCES `mydb`.`Orders` (`order_id`)
    ON DELETE CASCADE,
  CONSTRAINT ``
    FOREIGN KEY (`payment_id`)
    REFERENCES `mydb`.`Payments` (`payment_id`)
    ON DELETE CASCADE,
  CONSTRAINT ``
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`Users` (`user_id`)
    ON DELETE SET NULL);


-- -----------------------------------------------------
-- Table `mydb`.`Inventory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Inventory` (
  `inventory_id` INT NULL DEFAULT NULL AUTO_INCREMENT,
  `stock` INT NULL DEFAULT NULL,
  `Product_Categories_product_id` INT NOT NULL,
  `Product_Categories_category_id` INT NOT NULL,
  PRIMARY KEY (`inventory_id`),
  INDEX `fk_Inventory_Product_Categories1_idx` (`Product_Categories_product_id` ASC, `Product_Categories_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_Inventory_Product_Categories1`
    FOREIGN KEY (`Product_Categories_product_id` , `Product_Categories_category_id`)
    REFERENCES `mydb`.`Product_Categories` (`product_id` , `category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `mydb`.`Reviews`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Reviews` (
  `review_id` INT NULL DEFAULT NULL AUTO_INCREMENT,
  `product_id` INT NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  `rating` INT NULL DEFAULT NULL,
  `review_text` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  INDEX (`product_id` ASC) VISIBLE,
  INDEX (`user_id` ASC) VISIBLE,
  CONSTRAINT ``
    FOREIGN KEY (`product_id`)
    REFERENCES `mydb`.`Products` (`product_id`)
    ON DELETE SET NULL,
  CONSTRAINT ``
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`Users` (`user_id`)
    ON DELETE SET NULL);


-- -----------------------------------------------------
-- Table `mydb`.`Users_has_Addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Users_has_Addresses` (
  `Users_user_id` INT NOT NULL,
  `Addresses_address_id` INT NOT NULL,
  PRIMARY KEY (`Users_user_id`, `Addresses_address_id`),
  INDEX `fk_Users_has_Addresses_Addresses1_idx` (`Addresses_address_id` ASC) VISIBLE,
  INDEX `fk_Users_has_Addresses_Users1_idx` (`Users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_Users_has_Addresses_Users1`
    FOREIGN KEY (`Users_user_id`)
    REFERENCES `mydb`.`Users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_has_Addresses_Addresses1`
    FOREIGN KEY (`Addresses_address_id`)
    REFERENCES `mydb`.`Addresses` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
