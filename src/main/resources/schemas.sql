CREATE SCHEMA IF NOT EXISTS `restaurants_project` ;

CREATE TABLE `restaurants_project`.`restaurants` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(250) NOT NULL,
  `opening_time` VARCHAR(10) NOT NULL,
  `closing_time` VARCHAR(10) NOT NULL,
  `price_category` VARCHAR(10) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `restaurants_project`.`users` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(15) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `food_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `restaurant_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_restaurants_idx` (`restaurant_id`)
)

CREATE TABLE `restaurants_project`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `restaurant_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `created_date` DATETIME NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `FK_restaurants_idx` (`restaurant_id` ASC) VISIBLE,
  INDEX `FK_users_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK_restaurants`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `restaurants_project`.`restaurants` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `restaurants_project`.`users` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION);

CREATE TABLE `restaurants_project`.`food_orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `order_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_food_product_idx` (`product_id` ASC) VISIBLE,
  INDEX `FK_order_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `FK_food_product`
    FOREIGN KEY (`product_id`)
    REFERENCES `restaurants_project`.`food_product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `restaurants_project`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `restaurants_project`.`reviews` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `restaurant_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  `grade` INT NOT NULL,
  `comment` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_user_idx` (`user_id` ASC) VISIBLE,
  INDEX `FK_restaurant_idx` (`restaurant_id` ASC) VISIBLE,
  INDEX `FK_order_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `FK_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `restaurants_project`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_restaurant`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `restaurants_project`.`restaurants` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_order_review`
    FOREIGN KEY (`order_id`)
    REFERENCES `restaurants_project`.`orders` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `restaurants_project`.`reviews`
ADD COLUMN `created_date` DATE NOT NULL AFTER `comment`;
