
Drop table Takes;
Drop table Prereq;
Drop table Course;
Drop table Student;
Drop table Professor;

Create table Course (CourseID varchar(10) not null, 
		     ProfID varchar(9) not null,
		     Name varchar(30) not null,
		     Location varchar(25) not null,
		     Time varchar(15) not null);

Create table Student (StudentID varchar(9) not null,
			Name varchar(30) not null,
			BillingInfo varchar(50) );

Create table Professor (ProfID varchar(9) not null,
			Name varchar(30) not null);

Create table Prereq (CourseID varchar(10) not null,
			PrereqCourseID varchar(10) not null);

Create table Takes (StudentID varchar(9) not null,
			CourseID varchar(10) not null);

alter table Course Add Primary Key (CourseID);
alter table Student Add Primary Key (StudentID);
alter table Professor Add Primary Key (ProfID);
alter table Prereq Add Primary Key (CourseID, PrereqCourseID);
alter table Takes Add Primary Key (StudentID, CourseID);

alter table Prereq add foreign key (CourseID) references Course(CourseID);
alter table Prereq add foreign key (PrereqCourseID) references Course(CourseID);
alter table Course add foreign key (ProfID) references Professor(ProfID);
alter table Takes add foreign key (StudentID) references Student(StudentID);
alter table Takes add foreign key (CourseID) references Course(CourseID);

insert into student values ('222222222','Bill','dont bill me');
insert into student values ('333333333','Susie','Visa');
insert into student values ('444444444','Kwan','I have no money');
insert into student values ('555555555','Bob','books are too expensive');
insert into student values ('666666666','Julie','I cant even pay my rent');
insert into student values ('777777777','Uyn','I lost it all in the stock market');
insert into student values ('888888888','Charlie','I lost it all in Las Vegas');
insert into student values ('999999999','Susie','Mastercard');

insert into professor values ('098765432','John Tvedt');
insert into professor values ('111111111','Roseanne Tesoriero');
insert into professor values ('123456789','Kevin Gary');

insert into Course values ('CSC113','111111111','Intro to Computer Science I','Pangborn 301','TTh 2:10-4:30');
insert into Course values ('CSC123','098765432','Computer Science I','Pangborn 301','MW 1:10-3:30');
insert into Course values ('CSC631','098765432','Software Engineering','Pangborn 302','M 5:10-7:40');
insert into Course values ('CSC636','123456789','Distributed Computing','McMahon 9','W 5:10-7:40');
insert into Course values ('CSC641','123456789','Database Management Systems','McMahon 9','Th 5:10-7:40');

insert into takes values ('222222222','CSC113');
insert into takes values ('222222222','CSC123');
insert into takes values ('333333333','CSC631');
insert into takes values ('333333333','CSC636');
insert into takes values ('444444444','CSC631');
insert into takes values ('444444444','CSC636');
insert into takes values ('555555555','CSC123');
insert into takes values ('555555555','CSC631');
insert into takes values ('555555555','CSC641');
insert into takes values ('666666666','CSC636');
insert into takes values ('666666666','CSC641');
insert into takes values ('777777777','CSC113');
insert into takes values ('777777777','CSC123');
insert into takes values ('777777777','CSC631');
insert into takes values ('888888888','CSC631');
insert into takes values ('888888888','CSC641');
insert into takes values ('999999999','CSC113');
insert into takes values ('999999999','CSC123');
insert into takes values ('999999999','CSC631');
insert into takes values ('999999999','CSC636');
insert into takes values ('999999999','CSC641');

insert into prereq values ('CSC636','CSC123');
insert into prereq values ('CSC641','CSC113');

commit;