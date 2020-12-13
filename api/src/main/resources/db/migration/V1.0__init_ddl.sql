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

INSERT INTO `skill` (`id`, `name`, `created_at`) VALUES
   (1, 'Java', NOW()),
   (2, 'C#', NOW()),
   (3, 'JavaScript', NOW());

INSERT INTO `developer` (`id`, `name`, `created_at`) VALUES
   (1, 'Kenneth Gottschalk de Azevedo', NOW()),
   (2, 'Caio Cesar Alves Borges', NOW());

INSERT INTO `developer_skill` (`developer_id`, `skill_id`, `created_at`) VALUES
   (1, 1, NOW()),
   (1, 2, NOW()),
   (2, 1, NOW()),
   (2, 3, NOW());
