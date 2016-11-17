DROP TABLE DistTrans;
DROP TABLE Addict;
DROP TABLE Dealer;
DROP TABLE DrugLord;
DROP TABLE Supplier;
DROP TABLE SupplyTrans;
DROP TABLE Territory;
DROP TABLE AddictUser;

CREATE TABLE AddictUser(
	aUserName varchar(25) Primary Key,
	password varchar(25)
	check (REGEXP_LIKE(addictUserName,'^[a-zA-Z][a-zA-Z0-9.,$;]*$')));
)

CREATE TABLE DrugLordUser(
	dlUserName varchar(25) Primary Key,
	password varchar(25),
	check (REGEXP_LIKE(dlUserName,'^[a-zA-Z][a-zA-Z0-9.,$;]*$')));
)

CREATE TABLE DealerUser(
	dUserName varchar(25) Primary Key,
	password varchar(25),
	check (REGEXP_LIKE(dUserName,'^[a-zA-Z][a-zA-Z0-9.,$;]*$')));
)

CREATE TABLE DistTrans (
	DTID int NOT NULL,
	Cash MONEY DEFAULT 0 NOT NULL,
	Cocaine decimal(19,4) DEFAULT 0 NOT NULL,
	TransDATE DATE NOT NULL,
	DID int NOT NULL,
	DLID int NOT NULL,
	TID int NOT NULL,
	AID int NOT NULL,
	PRIMARY KEY (DTID,DID,AID),
	FOREIGN KEY (DID) REFERENCES Dealer(DID), 
	FOREIGN KEY (DLID) REFERENCES DrugLord(DLID),
	FOREIGN KEY (TID) REFERENCES Territory(TID),
	FOREIGN KEY (AID) REFERENCES Addict(AID)
);


CREATE TABLE Addict(
	AID int NOT NULL,
	Cash MONEY DEFAULT 0 NOT NULL,
	Name VARCHAR(55)
	PRIMARY KEY (AID)
);


CREATE TABLE Dealer(
	DID int NOT NULL,
	Cash Money DEFAULT 0  NOT NULL,
	Cocaine decimal(19,4) DEFAULT 0  NOT NULL,
	Name VARCHAR(55),
	DLID int NOT NULL,
	Rating int NOT NULL,
    PRIMARY KEY (DID),
	FOREIGN KEY (DLID) REFERENCES DrugLord(DLID)
);


CREATE TABLE DrugLord(
	DLID int NOT NULL,
	Cash Money DEFAULT 0  NOT NULL,
	Cocaine decimal(19,4) DEFAULT 0  NOT NULL,
	Name VARCHAR(55),
	WarWith int;
	PRIMARY KEY (DID)
	FOREIGN KEY (WarWith);
);


CREATE TABLE Supplier(
	SID int NOT NULL,
	Name VARCHAR(55),
	PRIMARY KEY (SID)
);


CREATE TABLE SupplyTrans(
	STID int NOT NULL,
	Cash Money  DEFAULT 0  NOT NULL,
	Cocaine decimal(19,4) DEFAULT 0  NOT NULL,
	TransDate DATE NOT NULL,
	SID int NOT NULL,
	DLID int NOT NULL,
	PRIMARY KEY (STID,SID,DLID),
	FOREIGN KEY (SID) REFERENCES Supplier(SID),
	FOREIGN KEY (DLID) REFERENCES DrugLord(DLID)
);


CREATE TABLE Territory(
	TID int NOT NULL,
	Name VARCHAR(55),
    PRIMARY KEY (TID)
);

INSERT INTO DistTrans(DTID, Cash, Cocaine, TransDate, DID, DLID, TID, AID)
VALUES (0,10,1,CURRENT_TIMESTAMP,
	(SELECT d.DID
		FROM Dealer d
		WHERE d.name = 'El Negro'),
	(SELECT dl.DLID
		FROM DrugLord dl
		WHERE dl.name = 'Pablo Escobar'),
	(SELECT t.TID
		FROM Territory t
		WHERE t.name = 'Medellin'),
	(SELECT a.AID
		FROM Addict a
		WHERE a.name = 'Pena')
);
INSERT INTO DistTrans(DTID, Cash, Cocaine, TransDate, DID, DLID, TID, AID)
VALUES(1, 20000, 55, CURRENT_TIMESTAMP, 
	(SELECT d.DID
		FROM Dealer d
		Where d.name = 'Jesse Pinkman'),
	(SELECT dl.DLID
		FROM DrugLord dl
		Where dl.name = 'El Loco'),
	(SELECT t.TID
		FROM Territory t
		WHERE t.name = 'Bogota'),
	(SELECT a.AID
		FROM Addict a
		WHERE a.name = 'Xavier')
);
INSERT INTO DistTrans(DTID, Cash, Cocaine, TransDate, DID, DLID, TID, AID)
VALUES(2, 1000000, 150, CURRENT_TIMESTAMP,
	(SELECT d.DID
		FROM Dealer d 
		WHERE d.name = 'El Negro'),
	(SELECT dl.DLID
		FROM DrugLord dl
		WHERE dl.name = 'Pablo Escobar'),
	(SELECT t.TID
		FROM Territory t
		WHERE t.name = 'Medellin'),
	(SELECT a.AID
		FROM Addict a
		WHERE a.name = 'Bob')
);
INSERT INTO DIstTrans(DTID, Cash, Cocaine, TransDate, DID, DLID, TID, AID)
VALUES( 3, 500000, 150, CURRENT_TIMESTAMP,
	(SELECT d.DID
		FROM Dealer d
		WHERE d.name = 'Bruno Vasquez'),
	(SELECT dl.DLID
		FROM DrugLord dl
		WHERE dl.name = 'Pablo Escobar'),
	(SELECT t.TID
		FROM Territory t
		WHERE t.name = 'Medellin'),
	(SELECT a.AID
		FROM Addict a
		WHERE a.name = 'Patrick')
);
INSERT INTO DistTrans(DTID, Cash, Cocaine, TransDate, DID, DLID, TID, AID)
VALUES ( 4, 9000, 30, CURRENT_TIMESTAMP,
	(SELECT d.DID
		FROM Dealer d
		WHERE d.name = 'Tom Brady'),
	(SELECT dl.DLID
		FROM DrugLord dl
		Where dl.name = 'Juan David Ochoa'),
	(SELECT t.TID
		FROM Territory t
		WHERE t.name = 'Miami'),
	(SELECT a.AID
		FROM Addict a 
		WHERE a.name = 'Jeff')
);

INSERT INTO Addict (AID, Cash, Name) VALUES (0,101, 'Pena');
INSERT INTO Addict (AID, Cash, Name) VALUES (1, 270, 'Xavier');
INSERT INTO Addict (AID, Cash, Name) VALUES (2, 556, 'Jeff');
INSERT INTO Addict (AID, Cash, Name) VALUES (3, 220, 'Bob');
INSERT INTO Addict (AID, Cash, Name) VALUES (4, 10, 'Patrick');

INSERT INTO Dealer (DID, Cash, Cocaine, Name, DLID, Rating)
VALUES(0, 100, 10,'El Negro',
	(SELECT dl.dlid
		FROM DrugLord dl
		WHERE dl.name = 'Pablo Escobar'),4);
INSERT INTO Dealer (DID, Cash, Cocaine, Name, DLID, Rating)
VALUES(1, 222, 50, 'Jesse Pinkman',
	(SELECT dl.dlid 
		FROM DrugLord dl 
		WHERE dl.name = 'El Loco')3);
INSERT INTO Dealer (DID, Cash, Cocaine, Name, DLID, Rating)
VALUES( 2, 68, 19, 'Bruno Vasquez',
	(SELECT dl.dlid
		FROM Druglord dl
		WHERE dl.name = 'Pablo Escobar')1);
INSERT INTO Dealer (DID, Cash, Cocaine, Name, DLID, Rating)
VALUES ( 3, 100, 294, 'Gabriel Mardin',
	(SELECT dl.dlid
		From Druglord dl
		Where dl.name = 'Gonzalo Gacha')5);
INSERT INTO Dealer (DID, Cash, Cocaine, Name, DLID, Rating)
VALUES (4, 500, 100, 'Tom Brady',
	(SELECT dl.dlid
		From Druglord dl
		Where dl.name = 'Juan David Ochoa')3);

INSERT INTO DrugLord (DLID, Cash, Cocaine, Name, WarWith) VALUES (0,100000,1000,'Pablo Escobar',2);
INSERT INTO DrugLord (DLID, Cash, Cocaine, Name, WarWith) VALUES (1, 80000, 600, 'Gonzalo Gacha',3);
INSERT INTO DrugLord (DLID, Cash, Cocaine, Name, WarWith) VALUES (2, 500,100, 'El Limon',0);
INSERT INTO DrugLord (DLID, Cash, Cocaine, Name, WarWith) VALUES (3, 20000, 500, 'Juan David Ochoa',1);
INSERT INTO DrugLord (DLID, Cash, Cocaine, Name, WarWith) VALUES (4, 10500, 1050, 'El Loco');

INSERT INTO Supplier (SID, Name) VALUES (0, 'El Lion');
INSERT INTO Supplier (SID, Name) VALUES (1, 'Jimmy Fallon');
INSERT INTO Supplier (SID, Name) VALUES (2, 'Walter white');
INSERT INTO Supplier (SID, Name) VALUES (3, 'Mateo Moreno');
INSERT INTO Supplier (SID, Name) VALUES (4, 'Tonald Drump');

INSERT INTO SupplyTrans(STID, Cash, Cocaine, TransDate, SID, DLID)
VALUES (0,30000,100,CURRENT_TIMESTAMP,
	(SELECT s.SID
		FROM Supplier s
		WHERE s.name = 'El Lion'),
	(SELECT dl.DLID
		FROM DrugLord dl
		WHERE dl.name = 'Pablo Escobar')
);
INSERT INTO SupplyTrans(STID, Cash, Cocaine, TransDate, SID, DLID)
VALUES(1, 2000000, 1000, CURRENT_TIMESTAMP,
	(SELECT s.SID
		FROM Supplier s
		WHERE s.name = 'Walter White'),
	(SELECT dl.DLID
		FROM DrugLord dl
		WHERE dl.name = 'Gonzalo Gacha')
);
INSERT INTO SupplyTrans(STID, Cash, Cocaine, TransDate, SID, DLID)
VALUES(2, 90000, 120, CURRENT_TIMESTAMP,
	(SELECT s.SID
		FROM Supplier s
		WHERE s.name = 'Jimmy Fallon'),
	(SELECT dl.DLID
		FROM DrugLord dl
		WHERE dl.name = 'El Limon')
);
INSERT INTO SupplyTrans(STID, Cash, Cocaine, TransDate, SID, DLID)
VALUES(3, 60000, 95, CURRENT_TIMESTAMP,
	(SELECT s.SID
		FROM Supplier s
		WHERE s.name = 'Jimmy Fallon'),
	(SELECT dl.DLID
		FROM DrugLord dl
		WHERE dl.name = 'Pablo Escobar')
);
INSERT INTO SupplyTrans(STID, Cash, Cocaine, TransDate, SID, DLID)
VALUES(4, 10400, 140, CURRENT_TIMESTAMP,
	(SELECT s.SID
		FROM Supplier s
		WHERE s.name = 'Tonald Drump'),
	(SELECT dl.DLID
		FROM DrugLord dl
		WHERE dl.name = 'El Limon')
);

INSERT INTO Territory (TID, Name) VALUES (0,'Medellin');
INSERT INTO Territory (TID, Name) VALUES (1,'Bogota');
INSERT INTO Territory (TID, Name) VALUES(2,'Miami');
INSERT INTO Territory (TID, Name) VALUES(3,'New York');
INSERT INTO Territory (TID, Name) VALUES(4,'Cali');

