CREATE TABLE `tiendavirtual`.`usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fullName` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `creationDate` DATE NULL,
  `modDate` DATE NULL,
  `balance` DOUBLE NULL DEFAULT 0,
  PRIMARY KEY (`id`));