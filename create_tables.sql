CREATE  TABLE IF NOT EXISTS `gb_db_nikbali`.`Question` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `text` VARCHAR(1000) NOT NULL ,
  `image_path` VARCHAR(20),
  PRIMARY KEY (`id`) );

CREATE  TABLE IF NOT EXISTS `gb_db_nikbali`.`Answer` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `text` VARCHAR(200) not null ,
  `right_flag` boolean,
  `question_id` int,
  PRIMARY KEY (`id`) );
  
ALTER TABLE Answer
ADD CONSTRAINT `FK_Answer_Question`
FOREIGN KEY (question_id)
REFERENCES `Question`(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE Question
ADD CONSTRAINT `FK_Question_RightAnswer`
FOREIGN KEY (right_answer_id)
REFERENCES `Answer`(id)
ON UPDATE CASCADE;
