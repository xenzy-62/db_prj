
CREATE TABLE Patient (
    Num_Patient   NUMBER PRIMARY KEY,
    LastName      VARCHAR2(50) NOT NULL,
    FirstName     VARCHAR2(50) NOT NULL,
    Date_of_Birth DATE,
    Phone         VARCHAR2(20),
    Address       VARCHAR2(100)
);


CREATE TABLE Doctor (
    Num_Doctor    NUMBER PRIMARY KEY,
    LastName      VARCHAR2(50) NOT NULL,
    FirstName     VARCHAR2(50) NOT NULL,
    Specialty     VARCHAR2(50),
    Phone         VARCHAR2(20)
);


CREATE TABLE Appointment (
    Num_Appointment NUMBER PRIMARY KEY,
    Num_Patient     NUMBER,
    Num_Doctor      NUMBER,
    Appt_Date       DATE,
    Appt_Time       VARCHAR2(5),
    Status          VARCHAR2(20),
    CONSTRAINT fk_patient FOREIGN KEY (Num_Patient) REFERENCES Patient(Num_Patient),
    CONSTRAINT fk_doctor FOREIGN KEY (Num_Doctor) REFERENCES Doctor(Num_Doctor),
    CONSTRAINT chk_status CHECK (Status IN ('Planned', 'Cancelled', 'Completed'))
);


CREATE SEQUENCE seq_patient START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_doctor START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_appointment START WITH 1 INCREMENT BY 1;

ALTER TABLE appointment ADD CONSTRAINT unique_doctor_time UNIQUE (Num_Doctor, Appt_Date , Appt_Time);
