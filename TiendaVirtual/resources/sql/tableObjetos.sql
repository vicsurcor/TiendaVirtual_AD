CREATE TABLE `tiendavirtual`.`objetos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DOUBLE NULL DEFAULT 0.00,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `tiendavirtual`.`usuarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);