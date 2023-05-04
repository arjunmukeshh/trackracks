create table user (     name varchar(150) NOT NULL,     age INT,    upwd varchar(150), weight float,     height float,     gender varchar(2),    email varchar(150) ,    uname varchar(150),    primary key(uname) );

create table workout (  workoutID INT unsigned NOT NULL auto_increment,     uname varchar(150) ,     workoutDate date,     description varchar(150),   primary key (workoutID) 
, foreign key(uname) references user(uname));

create table exercise(
	workoutID int unsigned,
    exerciseID int unsigned NOT NULL auto_increment,
    name varchar(150),
    sets int,
    reps int,
    weight float,
    tirednessfactor int,
    primary key(exerciseID),
    foreign key(workoutID) references workout(workoutID) ON DELETE CASCADE
);



create table diet (  dietID INT unsigned NOT NULL auto_increment,     uname varchar(150) ,     dietDate date,     description varchar(150),   primary key (dietID) 
, foreign key(uname) references user(uname));

create table food(
	dietID int unsigned,
    foodID int unsigned NOT NULL auto_increment,
    name varchar(150),
    calories float,
    primary key(foodID),
    foreign key(dietID) references diet(dietID) ON DELETE CASCADE
);