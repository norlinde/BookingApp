SET GLOBAL  time_zone = '+1:00';
    CREATE DATABASE IF NOT EXISTS BookingApp;
    USE BookingApp;

    CREATE TABLE Specialist(
    id int not null auto_increment,
    specialist varchar(100) not null,
    last_updated timestamp default current_timestamp,
    created_at timestamp default current_timestamp,
    primary key(id)
    );
    select * from specialist;


    CREATE TABLE IF NOT EXISTS User(
    id int not null auto_increment,
    name varchar(100) not null,
    phoneNumber int not null,
    specialist varchar(100) not null,
    date varchar(100) not null,
    time varchar(100) not null,
    primary key(id)
    );
    
    use BookingApp;
    select * from user;
    SELECT * FROM User;
  SELECT user.id, user.name, user.phoneNumber, user.specialist, user.date, user.time FROM User where user.phoneNumber = 112233;