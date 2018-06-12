Create table if not exists`gb_db_nikbali`.`Test`(
`id` int not null auto_increment ,
`name` varchar(255) not null,
`created` DATETIME default CURRENT_TIMESTAMP,
primary key(id),
UNIQUE (name)
);

Create table if not exists`gb_db_nikbali`.`Theme`(
`id` int not null auto_increment ,
`name` varchar(255) not null,
`created` DATETIME default CURRENT_TIMESTAMP,
`score` int not null default 1,
`test_id` int,
primary key(id),
FOREIGN KEY (test_id) REFERENCES Test(id),
UNIQUE (name)
);


CREATE  TABLE IF NOT EXISTS `gb_db_nikbali`.`Question` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `text` VARCHAR(1000) NOT NULL ,
  `image_path` VARCHAR(20),
  `theme_id` int ,
  PRIMARY KEY (`id`) ,
  FOREIGN KEY (theme_id) REFERENCES Theme(id)
  );

CREATE  TABLE IF NOT EXISTS `gb_db_nikbali`.`Answer` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `text` VARCHAR(200) not null ,
  `right_flag` boolean NOT NULL default 0,
  `question_id` int,
  PRIMARY KEY (`id`),
  FOREIGN KEY (question_id) REFERENCES Question(id)
  );
  




