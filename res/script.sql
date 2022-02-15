create database agenda;

create table pessoa (
id int not null auto_increment primary key,
nome varchar(80),
idade int,
dataCadastro date);