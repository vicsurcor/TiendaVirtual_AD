CREATE TABLE `tiendavirtual`.`compras` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `object_id` INT NOT NULL,
  `seller_id` INT NOT NULL,
  `buyer_id` INT NOT NULL,
  `sell_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `object_id_idx` (`object_id` ASC) VISIBLE,
  INDEX `seller_id_idx` (`seller_id` ASC) VISIBLE,
  INDEX `buyer_id_idx` (`buyer_id` ASC) VISIBLE,
  CONSTRAINT `object_id`
    FOREIGN KEY (`object_id`)
    REFERENCES `tiendavirtual`.`objetos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `seller_id`
    FOREIGN KEY (`seller_id`)
    REFERENCES `tiendavirtual`.`objetos` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `buyer_id`
    FOREIGN KEY (`buyer_id`)
    REFERENCES `tiendavirtual`.`usuarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);