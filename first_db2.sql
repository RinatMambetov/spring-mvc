COMMIT
--one to many

CREATE TABLE director(
    director_id serial PRIMARY KEY, "name" varchar(100) NOT NULL UNIQUE, age int CHECK (age>10)
)
DROP TABLE IF EXISTS director
SELECT *
FROM director

INSERT INTO director(name, age)
VALUES
('directorJohn', 30),('directorJane', 20),('directorJack', 10),('directorJill', 15),('directorJessica', 25),('directorJim', 30)

CREATE TABLE movie(
    movie_id serial PRIMARY KEY, director_id int REFERENCES director(director_id), "name" varchar(200) NOT NULL, year_of_production int CHECK (year_of_production>1900)
)
DROP TABLE IF EXISTS movie
SELECT *
FROM movie

INSERT INTO movie(director_id, "name", year_of_production)
VALUES
(1, 'Star Wars', 1977),(1, 'Star War2', 1978),(1, 'Star War3', 1979),(1, 'Star War4', 1980),(2, 'Taxi Driver', 1981),(2, 'Taxi Driver2', 1982),(2, 'Taxi Driver3', 1983),(3, 'Goodfellas', 1984),(3, 'Goodfellas2', 1985),(4, 'The Matrix', 1986),(6, 'Inseption', 1987)

SELECT director.name, movie.name
FROM director
JOIN movie
USING (director_id)

SELECT director.name, movie.name
FROM director
LEFT JOIN movie
USING (director_id)
--one to one

CREATE TABLE citizen(
    citizen_id serial PRIMARY KEY, "name" varchar(100) NOT NULL, age int CHECK (age>0)
)
DROP TABLE IF EXISTS citizen
SELECT *
FROM citizen

INSERT INTO citizen(name, age)
VALUES
('citizenJohn', 30),('citizenJane', 20),('citizenJack', 30),('citizenJill', 40),('citizenJoe', 50),('citizenJim', 30)

CREATE TABLE passport(
    citizen_id int PRIMARY KEY REFERENCES citizen(citizen_id), passport_number int UNIQUE
)
DROP TABLE IF EXISTS passport
SELECT *
FROM passport

INSERT INTO passport(citizen_id, passport_number)
VALUES
(1, 1),(2, 2),(3, 3),(4, 4),(5, 5),(6, 6)
--error
--insert into passport(citizen_id,passport_number) values (1, 11)

SELECT citizen.name, passport.passport_number
FROM citizen
JOIN passport
USING (citizen_id)

SELECT citizen.name, passport.passport_number
FROM citizen
LEFT JOIN passport
USING (citizen_id)
--many to many

CREATE TABLE actor(
    actor_id serial PRIMARY KEY, "name" varchar(100) NOT NULL UNIQUE, age int CHECK (age>0)
)

DROP TABLE IF EXISTS actor
SELECT *
FROM actor

INSERT INTO actor(name, age)
VALUES
('actorJohn', 30),('actorJane', 20),('actorJack', 30),('actorJill', 40),('actorJoe', 50),('actorJim', 30)

CREATE TABLE actor_movie(
    movie_id int REFERENCES movie(movie_id), actor_id int REFERENCES actor(actor_id), PRIMARY KEY(movie_id, actor_id)
)

DROP TABLE IF EXISTS actor_movie

SELECT *
FROM actor_movie

INSERT INTO actor_movie(actor_id, movie_id)
VALUES
(1, 1),(1, 2),(1, 3),(2, 3),(2, 4),(2, 10),(3, 5),(3, 6),(3, 4),(3, 11),(4, 5),(4, 6),(4, 9),(5, 1),(5, 7),(6, 2),(6, 9)
--error
--insert into movie_actor(movie_id, actor_id) values
--(1,1)

SELECT actor.name, movie.name
FROM actor
JOIN actor_movie
USING (actor_id)
JOIN movie
USING (movie_id)

