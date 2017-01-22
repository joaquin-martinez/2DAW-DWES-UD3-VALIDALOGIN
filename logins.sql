create database logins ;
use logins ;
create table login ( 
login varchar(8) not null primary key ,
clave varchar(8) not null ,
nombre varchar(128) not null );

insert into login values ('usuario','usuario','usuario'),('joaquin','joaquin','joaquin');



