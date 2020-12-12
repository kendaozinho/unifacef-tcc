CREATE TABLE comics
(
   id           int(12)        NOT NULL   PRIMARY KEY   AUTO_INCREMENT,
   title        varchar(255)   NOT NULL,
   created_at   datetime       NOT NULL,
   updated_at   datetime       DEFAULT NULL
);
