--This schema will modelJedi Academy members and their ranks

--This will be a one to many relationship, one rank can be assigned to many members, members can only have one rank.

CREATE TABLE ranks (
	rank_id serial PRIMARY KEY,
	rank_title TEXT,
	rank_tenure int
);


CREATE TABLE members (
	member_id serial PRIMARY KEY,
	first_name TEXT,
	last_name TEXT,
	rank_id_fk int REFERENCES ranks (rank_id) --foreign key: establishes a relationship between the tables
);

CREATE TABLE users (
	user_id serial PRIMARY KEY,
	user_username TEXT,
	user_password TEXT
);





--I've created my tables, now I just want to put some data in

INSERT INTO ranks (rank_title, rank_tenure)
VALUES ('Grandmaster', 20),
	   ('Battle Master', 15),
	   ('Jedi Master', 10),
	   ('Jedi Knight', 7),
	   ('Jedi Padawan', 3);
	  
SELECT * FROM ranks;

INSERT INTO members (first_name, last_name, rank_id_fk)
VALUES ('Luke', 'Skywalker', 1),
	   ('Kyle', 'Katarn', 2),
	   ('Jaina', 'Solo', 3),
	   ('Anakin', 'Solo', 4),
	   ('Ben', 'Skywalker', 5);
	   
SELECT * FROM members;

INSERT INTO users (user_username, user_password)
VALUES ('Skyguy', 'RogueOne'),
	   ('RogueJedi', 'Smuggler'),
	   ('SoloChild', 'JacenSolo'),
	   ('ChosenOne2.0', 'Chewbacca'),
	   ('DesertHermit', 'SonofSkywalker');
	   
SELECT * FROM users;



drop table members;
drop table ranks;
drop table users;



