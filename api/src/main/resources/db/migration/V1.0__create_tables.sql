CREATE TABLE skill
(
   id           int(12)       NOT NULL   PRIMARY KEY   AUTO_INCREMENT,
   name         varchar(50)   NOT NULL   UNIQUE,
   created_at   datetime      NOT NULL,
   updated_at   datetime      DEFAULT NULL
);

CREATE TABLE developer
(
   id           int(12)       NOT NULL   PRIMARY KEY   AUTO_INCREMENT,
   name         varchar(50)   NOT NULL,
   created_at   datetime      NOT NULL,
   updated_at   datetime      DEFAULT NULL
);

CREATE TABLE developer_skill
(
   developer_id   int(12)       NOT NULL,
   skill_id       int(12)       NOT NULL,
   created_at     datetime      NOT NULL,
   updated_at     datetime      DEFAULT NULL,
   PRIMARY KEY (`developer_id`, `skill_id`),
   FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`),
   FOREIGN KEY (`skill_id`) REFERENCES `skill` (`id`)
);
