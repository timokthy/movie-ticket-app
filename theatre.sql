DROP DATABASE IF EXISTS THEATRE;
CREATE DATABASE THEATRE; 
USE THEATRE;

DROP TABLE IF EXISTS GUEST;
CREATE TABLE GUEST (
	UserId			integer not null auto_increment,
	Email	varchar(100),
	primary key (UserId)
);

INSERT INTO GUEST (UserId, Email)
VALUES
('1','bigsam87@hotmail.com'),
('2','bigsam87@hotmail.com'),
('3','bigsam87@hotmail.com')
;


DROP TABLE IF EXISTS REGISTERED;
CREATE TABLE REGISTERED (
	UserId			integer not null auto_increment,
	Username		varchar(20) not null,
    UserPassword	varchar(20) not null,
    Email			varchar(100) not null,
    Address			varchar(80) not null,
    CardNum			varchar(16) not null,
    DateOfReg		datetime not null,
	primary key (UserId)
);

INSERT INTO REGISTERED (UserId, Username, UserPassword, Email, Address, CardNum, DateOfReg)
VALUES
('1020','Mitch Falkner','nogoingback', 'user1@gmail.com', '4899 Galts Ave', '5126692418955227', '2021-07-19 13:40:26'),
('1038','Timothy Hugo','apocalyse2012', 'user2@gmail.com', '3113 Pine Street', '4916431888491190', '2021-08-20 15:05:48'),
('1126','Ruth Bale','uphill@30', 'user3@gmail.com', '2101 Heritage Drive', '5475826633726644', '2021-09-30 10:29:02'),
('1222','Ronald Fitzgerald','pennywise!0', 'user4@gmail.com','1838 Port Washington Road', '5578350409362068', '2021-10-09 17:52:13');

DROP TABLE IF EXISTS AUDITORIUM;
CREATE TABLE AUDITORIUM (
	AuditoriumNum	varchar(3) not null,
	Capacity		integer not null,
	primary key (AuditoriumNum)
);

INSERT INTO AUDITORIUM (AuditoriumNum, Capacity)
VALUES
('A1','25'),
('A2','25');

DROP TABLE IF EXISTS MOVIE;
CREATE TABLE MOVIE (
	MovieTitle			varchar(50) not null,
	ProdcutionCompany	varchar(50) not null,
    Genre				varchar(10) not null,
    Rating				varchar(4) not null,
    Duration			integer(3) not null,
	primary key (MovieTitle)
);

INSERT INTO MOVIE(MovieTitle, ProdcutionCompany, Genre, Rating, Duration)
VALUES
('Dune','Warner Bros. Pictures','Adventure', 'PG', '155'),
('Ghostbusters: Afterlife','Columbia Pictures','Adventure', 'PG', '124'),
('Wolf','Universal Pictures','Mystery', '18A', '95'),
('Belfast','Universal Pictures','Drama', 'PG', '98'),
('House of Gucci','Universal Pictures','Drama', '14A', '157'),
('The Addams Family 2','Universal Pictures','Animation', 'PG', '93'),
('Jungle Cruise','Walt Disney Studios Motion Pictures','Adventure', 'PG', '127'),
('The Last Duel','20th Century Studios','Drama', '14A', '153'),
('Red Notice','Netflix','Action', 'PG', '115'),
('Shang-Chi and the Legend of the Ten Rings','Walt Disney Studios Motion Pictures','Fantasy', 'PG', '132'),
('The Unforgivable','Netflix','Drama', '14A', '113');


DROP TABLE IF EXISTS SHOWTIME;
CREATE TABLE SHOWTIME (
	ScheduleId		integer not null,
	Title			varchar(50) not null,
    DayShowing		varchar(3) not null,
    Timeslot		datetime not null,
    Auditorium		varchar(3) not null,
    Seats		varchar(25) not null,
	primary key (ScheduleId),
    foreign key (Title) references MOVIE(MovieTitle),
	foreign key (Auditorium) references AUDITORIUM(AuditoriumNum)
);

INSERT INTO SHOWTIME(ScheduleId, Title, DayShowing, Timeslot, Auditorium, Seats)
VALUES
('2111','Ghostbusters: Afterlife','Mon', '2021-12-13 16:10:00', 'A2', '1111110101010101001011111'),
('2113','The Addams Family 2','Mon', '2021-12-13 18:15:00', 'A2', '0101010101010101001011101'),
('2129','Belfast','Tue', '2021-12-14 16:15:00', 'A1', '0101010101110111000011011'),
('2131','The Unforgivable','Tue', '2021-12-14 18:10:00', 'A1', '0101010101010101001011111'),
('2137','Wolf','Wed', '2021-12-08 16:10:00', 'A1', '0000010101010101001011011'),
('2141','The Last Duel','Wed', '2021-12-08 16:10:00', 'A2', '0111011101010101001011111'),
('2143','Ghostbusters: Afterlife','Wed', '2021-12-08 18:10:00', 'A1', '0101010101010100001101101')

;

DROP TABLE IF EXISTS TICKET;
CREATE TABLE TICKET (
	TicketNum		integer not null,
	MovieName		varchar(50) not null,
    TicketHolder	integer not null,
    SeatNum			integer(2) not null,
    MovieDate		datetime not null,
    TicketStatus	varchar(10) not null,
    Price			double,
	primary key (TicketNum)
);

INSERT INTO TICKET(TicketNum, MovieName, TicketHolder, SeatNum, MovieDate, TicketStatus, Price)
VALUES
('124', 'The Addams Family 2', '1020', '15', '2021-12-04 16:58:14', 'Active', 18.25),
('135', 'The Unforgivable', '1038', '05', '2021-12-06 18:08:49', 'Active', 18.25),
('160', 'Dune', '1126', '18', '2021-12-08 20:01:34', 'Active', 18.25),
('127', 'The Unforgivable', '1009', '07', '2021-12-03 13:19:43', 'Active', 18.25),
('150', 'The Last Duel', '1103', '22', '2021-12-07 14:49:11', 'Active', 18.25),
('153', 'Shang-Chi and the Legend of the Ten Rings', '1222', '11', '2021-12-08 15:11:02', 'Active', 18.25),
('155', 'Belfast', '1361', '12', '2021-12-11 12:24:18', 'Active', 18.25),
('163', 'The Unforgivable', '1049', '06', '2021-12-10 09:14:51', 'Active', 18.25),
('141', 'The Last Duel', '1020', '17', '2021-12-05 11:43:33', 'Active', 18.25),
('174', 'Jungle Cruise', '1038', '04', '2021-12-12 13:32:16', 'Active', 18.25),
('177', 'Red Notice', '1151', '22', '2021-12-13 17:00:38', 'Active', 18.25),
('137', 'House of Gucci', '1126', '14', '2021-12-10 10:31:40', 'Active', 18.25),
('182', 'Ghostbusters: Afterlife', '1031', '25', '2021-12-10 11:23:37', 'Active', 18.25),
('190', 'Wolf', '1222', '08', '2021-12-04 15:18:20', 'Active', 18.25),
('201', 'The Addams Family 2', '1126', '19', '2021-12-09 19:15:12', 'Active', 18.25);

DROP TABLE IF EXISTS PAYMENT;
CREATE TABLE PAYMENT (
	UserId			integer not null,
	Total			double not null,
    PaymentDate		varchar(100) not null,
    CardInfo		varchar(100) not null,
    
	primary key (UserId, PaymentDate)
);

INSERT INTO PAYMENT (UserId, Total, PaymentDate, CardInfo)
VALUES
(1038,18, '2021-12-01 20:15:12', '4916431888491190'),
(1222,18, '2021-12-04 18:13:10', '5578350409362068');


DROP TABLE IF EXISTS CREDIT;
CREATE TABLE CREDIT (
	UserId			integer not null,
	CreditAmount	double not null,
    ExpirationDate	datetime not null,
    
	primary key (UserId, ExpirationDate)
);

INSERT INTO CREDIT (UserId, CreditAmount, ExpirationDate)
VALUES
('1009','2.7', '2021-12-12 19:15:12'),
('1049','2.7', '2021-12-10 03:54:42');