CREATE TABLE movie (
    id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
    director varchar(32),
    title varchar(32),
    genre varchar(32),
    rating int NOT NULL
);