CREATE DATABASE IF NOT EXISTS uavtestdb;
USE uavtestdb;
DROP TABLE IF EXISTS termin_student;
DROP TABLE IF EXISTS termin;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS uebung;

CREATE TABLE IF NOT EXISTS uebung
(
    id             BIGINT AUTO_INCREMENT,
    name           VARCHAR(100),
    modus          VARCHAR(20),
    min_groesse    INTEGER,
    max_groesse    INTEGER,
    anmeldebeginn  DATETIME,
    anmeldeschluss DATETIME,
    bearbeitet     BOOLEAN,
    PRIMARY KEY (id)
 );

CREATE TABLE IF NOT EXISTS termin
(
    id          BIGINT AUTO_INCREMENT,
    zeitpunkt   DATETIME,
    reserviert  BOOLEAN,
    min_groesse INTEGER,
    max_groesse INTEGER,
    tutor       VARCHAR(50),
    uebung_key  BIGINT,
    uebung      BIGINT,
    gruppenname VARCHAR(50),
    PRIMARY KEY (id),
    CONSTRAINT fk_termin_uebung FOREIGN KEY (uebung) REFERENCES uebung (id)
);

CREATE TABLE IF NOT EXISTS student
(
    id     BIGINT AUTO_INCREMENT,
    github VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS termin_student
(
    termin_key  BIGINT,
    student_key BIGINT,
    termin      BIGINT,
    id          BIGINT,
    PRIMARY KEY (termin, id),
    CONSTRAINT fk_termin_student FOREIGN KEY (id) REFERENCES student (id),
    CONSTRAINT fk_student_termin FOREIGN KEY (termin) REFERENCES termin (id)
);

INSERT INTO student (github) VALUES ('Alex');
INSERT INTO student (github) VALUES ('Dieter');
INSERT INTO student (github) VALUES ('Fritz');
INSERT INTO student (github) VALUES ('Berta');
INSERT INTO student (github) VALUES ('HarryPotter');
INSERT INTO student (github) VALUES ('Alexa');

INSERT INTO uebung (name, modus, min_groesse, max_groesse, anmeldebeginn, anmeldeschluss, bearbeitet) VALUES
('Mob Tool', 'GRUPPENANMELDUNG', 2, 4, '2021-03-12T18:00', '2021-03-19T18:00', false);

INSERT INTO uebung (name, modus, min_groesse, max_groesse, anmeldebeginn, anmeldeschluss, bearbeitet) VALUES
('Stream API', 'INDIVIDUALANMELDUNG', 2, 4, '2021-03-19T18:00', '2021-03-16T18:00', false);