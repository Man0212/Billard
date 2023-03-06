
CREATE TABLE PLAYER(
    idPlayer INT,
    nom varchar(10)
);

insert into PLAYER values(5,'Man');
insert into PLAYER values(6,'Manoo');

CREATE TABLE GAME(
    IDChampionnat INT,
    idGame INT,
    idPlayer1 INT,
    idPlayer2 INT,
    idWinner INT
);

insert into GAME values(2020,5050,2,5,5);
insert into GAME values(2020,4050,1,3,1);
insert into GAME values(2020,1050,5,1,5);
insert into GAME values(2020,50,3,5,5);


CREATE TABLE SHOOT(
    idGame INT,
    idPlayer int,
    NBShoot int,
    NBPoint int
);

CREATE TABLE CHAMPIONNAT(
    IDChampionnat int,
    DateEvent DATE,
    IDCHampion int
);

insert into game()

