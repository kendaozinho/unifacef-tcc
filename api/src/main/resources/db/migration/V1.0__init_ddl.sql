CREATE TABLE developer
(
   id           int(12)        NOT NULL   PRIMARY KEY   AUTO_INCREMENT,
   name         varchar(255)   NOT NULL,
   created_at   datetime       NOT NULL,
   updated_at   datetime       DEFAULT NULL
);
