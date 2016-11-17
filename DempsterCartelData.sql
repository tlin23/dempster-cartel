 /* DROP ALL TABLE*/
DROP TABLE addict;
DROP TABLE dealer;
DROP TABLE drugLord;
DROP TABLE supplier;
DROP TABLE supplyTrans;
DROP TABLE territory;
DROP TABLE distTrans;
DROP TABLE addictUser;
DROP TABLE drugLordUser;
DROP TABLE dealerUser;

/* DROP ALL SEQUENCES */
DROP SEQUENCE a_seq;
DROP SEQUENCE dl_seq;
DROP SEQUENCE d_seq;
DROP SEQUENCE t_seq;
DROP SEQUENCE s_seq;
DROP SEQUENCE strans_seq;
DROP SEQUENCE dtrans_seq;

CREATE TABLE AddictUser(
	aUserName varchar(25) Primary Key,
	password varchar(25),	
	check (REGEXP_LIKE(aUserName,'^[a-zA-Z][a-zA-Z0-9.,$;]*$'))
);

CREATE TABLE DrugLordUser(
	dlUserName varchar(25) Primary Key,
	password varchar(25),
	check (REGEXP_LIKE(dlUserName,'^[a-zA-Z][a-zA-Z0-9.,$;]*$'))
);

CREATE TABLE DealerUser(
	dUserName varchar(25) Primary Key,
	password varchar(25),
	check (REGEXP_LIKE(dUserName,'^[a-zA-Z][a-zA-Z0-9.,$;]*$'))
);

CREATE TABLE Territory(
	TID int NOT NULL,
	Name VARCHAR(55),
    PRIMARY KEY (TID)
);

CREATE TABLE Addict(
	AID int NOT NULL,
	Cash decimal(19,4) DEFAULT 0 NOT NULL,
	Name VARCHAR(55),
	PRIMARY KEY (AID),
	check (Cash > = 0)
);

CREATE TABLE DrugLord(
	DLID int NOT NULL,
	Cash decimal(19,4) DEFAULT 0  NOT NULL,
	Cocaine decimal(19,4) DEFAULT 0  NOT NULL,
	Name VARCHAR(55),
	WLID int,
    PRIMARY KEY (DLID),
    FOREIGN KEY (WLID) REFERENCES Druglord(DLID),
    check (Cash > = 0 ),
    check (Cocaine >= 0)
);

CREATE TABLE Dealer(
	DID int NOT NULL,
	Cash  decimal(19,4) DEFAULT 0  NOT NULL,
	Cocaine decimal(19,4) DEFAULT 0  NOT NULL,
	Name VARCHAR(55),
	DLID int NOT NULL,
	Rating int NOT NULL,
    PRIMARY KEY (DID),
	FOREIGN KEY (DLID) REFERENCES DrugLord(DLID),
	check(Rating > = 0 and Rating < = 5),
	check (Cash > = 0 ),
    check (Cocaine >= 0)
);


CREATE TABLE Supplier(
	SID int NOT NULL,
	Name VARCHAR(55),
    PRIMARY KEY (SID)
);

CREATE TABLE SupplyTrans(
	STID int NOT NULL,
	Cash decimal(19,4) DEFAULT 0  NOT NULL,
	Cocaine decimal(19,4) DEFAULT 0  NOT NULL,
	transDate date NOT NULL,
	SID int NOT NULL,
	DLID int NOT NULL,
    PRIMARY KEY (STID),
	FOREIGN KEY (SID) REFERENCES Supplier(SID),
	FOREIGN KEY (DLID) REFERENCES DrugLord(DLID),
	check (Cash > = 0 ),
    check (Cocaine >= 0)
);

CREATE TABLE DistTrans(
	DTID int NOT NULL,
	Cash decimal(19,4) DEFAULT 0 NOT NULL,
	Cocaine decimal(19,4) DEFAULT 0 NOT NULL,
	transDate date NOT NULL,
	DID int NOT NULL,
	DLID int NOT NULL,
	TID int NOT NULL,
	AID int NOT NULL,
	PRIMARY KEY (DTID),
	FOREIGN KEY (DID) REFERENCES Dealer(DID),
	FOREIGN KEY (DLID) REFERENCES DrugLord(DLID),
	FOREIGN KEY (TID) REFERENCES Territory(TID),
	FOREIGN KEY (AID) REFERENCES Addict(AID),
	check (Cash > = 0 ),
    check (Cocaine >= 0)
);

/*Create Sequences */
CREATE SEQUENCE a_seq START WITH 1;
CREATE SEQUENCE dl_seq START WITH 1;
CREATE SEQUENCE d_seq START WITH 1;
CREATE SEQUENCE t_seq START WITH 1;
CREATE SEQUENCE s_seq START WITH 1;
CREATE SEQUENCE strans_seq START WITH 1;
CREATE SEQUENCE dtrans_seq START WITH 1;

/*Create Triggers */

CREATE OR REPLACE TRIGGER a_bir
	BEFORE INSERT ON Addict
	FOR EACH ROW
	BEGIN 
		SELECT a_seq.NEXTVAL
		INTO :new.AID
		FROM dual;
	END;
/

CREATE OR REPLACE TRIGGER dl_bir
	BEFORE INSERT ON DrugLord
	FOR EACH ROW
	BEGIN 
		SELECT dl_seq.NEXTVAL
		INTO :new.DLID
		FROM dual;
	END;
/

CREATE OR REPLACE TRIGGER d_bir
	BEFORE INSERT ON Dealer
	FOR EACH ROW
	BEGIN 
		SELECT d_seq.NEXTVAL
		INTO :new.DID
		FROM dual;
	END;
/

CREATE OR REPLACE TRIGGER t_bir
	BEFORE INSERT ON Territory
	FOR EACH ROW
	BEGIN 
		SELECT t_seq.NEXTVAL
		INTO :new.TID
		FROM dual;
	END;
 /

CREATE OR REPLACE TRIGGER s_bir
	BEFORE INSERT ON Supplier
	FOR EACH ROW
	BEGIN 
		SELECT s_seq.NEXTVAL
		INTO :new.SID
		FROM dual;
	END;
 /

CREATE OR REPLACE TRIGGER strans_bir
	BEFORE INSERT ON SupplyTrans
	FOR EACH ROW
	BEGIN 
		SELECT strans_seq.NEXTVAL
		INTO :new.STID
		FROM dual;
	END;
 /

CREATE OR REPLACE TRIGGER dtrans_bir
	BEFORE INSERT ON DistTrans
	FOR EACH ROW
	BEGIN 
		SELECT dtrans_seq.NEXTVAL
		INTO :new.DTID
		FROM dual;
	END;
 /


INSERT INTO AddictUser(aUserName,password) VALUES ( 'addict', 'addict');
INSERT INTO DealerUser(dUserName, password) VALUES ( 'dealer', 'dealer');
INSERT INTO DrugLordUser(dlUserName, password) VALUES( 'drugLord','drugLord');


INSERT INTO Addict (AID, Cash, Name) VALUES (0, 101, 'Pena');
INSERT INTO Addict (AID, Cash, Name) VALUES (1, 270, 'Xavier');
INSERT INTO Addict (AID, Cash, Name) VALUES (2, 556, 'Jeff');
INSERT INTO Addict (AID, Cash, Name) VALUES (3, 220, 'Bob');
INSERT INTO Addict (AID, Cash, Name) VALUES (4, 10, 'Patrick');

INSERT INTO DrugLord (DLID, Cash, Cocaine, Name, WLID) 
VALUES (0,100000,1000,'Pablo Escobar',NULL);
INSERT INTO DrugLord (DLID, Cash, Cocaine, Name, WLID) 
VALUES (1, 80000, 600, 'Gonzalo Gacha',NULL);
INSERT INTO DrugLord (DLID, Cash, Cocaine, Name, WLID ) 
VALUES (2, 500,100, 'El Limon',NULL);
INSERT INTO DrugLord (DLID, Cash, Cocaine, Name, WLID)
 VALUES (3, 20000, 500, 'Juan David Ochoa',NULL);
INSERT INTO DrugLord (DLID, Cash, Cocaine, Name, WLID)
 VALUES (4, 10500, 1050, 'El Loco',NULL);

UPDATE DrugLord SET WLID = '4' WHERE DLID = '1';
UPDATE DrugLord SET WLID = '1' WHERE DLID = '4';
UPDATE DrugLord SET WLID = '2' WHERE DLID = '3';
UPDATE DrugLord SET WLID = '3' WHERE DLID = '2';


INSERT INTO Supplier (SID, Name) VALUES (0, 'El Lion');
INSERT INTO Supplier (SID, Name) VALUES (1, 'Jimmy Fallon');
INSERT INTO Supplier (SID, Name) VALUES (2, 'Walter White');
INSERT INTO Supplier (SID, Name) VALUES (3, 'Mateo Moreno');
INSERT INTO Supplier (SID, Name) VALUES (4, 'Tonald Drump');

INSERT INTO Territory (TID, Name) VALUES (0,'Medellin');
INSERT INTO Territory (TID, Name) VALUES (0,'Bogota');
INSERT INTO Territory (TID, Name) VALUES(0,'Miami');
INSERT INTO Territory (TID, Name) VALUES(0,'New York');
INSERT INTO Territory (TID, Name) VALUES(0,'Cali');

INSERT INTO Dealer (DID, Cash, Cocaine, Name, DLID, Rating)
VALUES(0, 100, 10,'El Negro',
	(SELECT dl.dlid
		FROM DrugLord dl
		WHERE dl.name = 'Pablo Escobar'),5);
		
INSERT INTO Dealer (DID, Cash, Cocaine, Name, DLID, Rating)
VALUES(1, 222, 50, 'Jesse Pinkman',
	(SELECT dl.dlid 
		FROM DrugLord dl 
		WHERE dl.name = 'El Loco'),3);
INSERT INTO Dealer (DID, Cash, Cocaine, Name, DLID, Rating)
VALUES( 2, 68, 19, 'Bruno Vasquez',
	(SELECT dl.dlid
		FROM Druglord dl
		WHERE dl.name = 'Pablo Escobar'),3);
INSERT INTO Dealer(DID, Cash, Cocaine, Name, DLID, Rating)
VALUES ( 3, 100, 294, 'Gabriel Mardin',
	(SELECT dl.dlid
		From Druglord dl
		Where dl.name = 'Gonzalo Gacha'),1);
INSERT INTO Dealer(DID, Cash, Cocaine, Name, DLID, Rating)
VALUES (4, 500, 100, 'Tom Brady',
	(SELECT dl.dlid
		From Druglord dl
		Where dl.name = 'Juan David Ochoa'),0);

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
VALUES(0, 20000, 55, CURRENT_TIMESTAMP, 
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
VALUES(0, 1000000, 150, CURRENT_TIMESTAMP,
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
INSERT INTO DistTrans(DTID, Cash, Cocaine, TransDate, DID, DLID, TID, AID)
VALUES(0, 500000, 150, CURRENT_TIMESTAMP,
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
VALUES ( 0, 9000, 30, CURRENT_TIMESTAMP,
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



	