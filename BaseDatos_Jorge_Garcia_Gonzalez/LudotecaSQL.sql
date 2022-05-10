drop schema if exists jorge_ludoteca;
create schema jorge_ludoteca;
use jorge_ludoteca;

/*Tablas*/

drop table if exists alumno;
create table alumno(
    id int auto_increment primary key,
    nombre varchar(20) not null,
    apellidos varchar(50) not null,
    email varchar(50) not null,
    sancion date not null,
    telefono varchar(9) not null,
    numPrestamos int not null
);

drop table if exists juego;
create table juego(
    id int auto_increment primary key,
    nombre varchar(20) not null,
    numJugadores varchar(4) not null,
    tematica varchar(20) not null,
    tipo varchar(20) not null,
    disponible tinyint(1) not null
);

drop table if exists prestamo;
create table prestamo(
    id int auto_increment primary key,
    ampliacion tinyint(1) not null,
    fechaEntrega date not null,
    fechaDevolucion date not null,
    idAlumno int not null,
    foreign key (idAlumno) references jorge_ludoteca.alumno (id)
                     on update cascade on delete cascade,
    idJuego int not null,
    foreign key (idJuego) references jorge_ludoteca.juego (id)
);

/*TRIGGER*/

delimiter $$
drop trigger if exists modifica_alumno_juego_despues_elimina_prestamo$$
create trigger modifica_alumno_juego_despues_elimina_prestamo
    after delete on prestamo
    for each row
begin
    update alumno set numPrestamos = (numPrestamos -1)  where id = old.idAlumno;
    update juego set disponible = 1  where id = old.idJuego;
end $$
delimiter ;

delimiter $$
drop trigger if exists trigger_insertar_Prestamo$$
create trigger trigger_insertar_Prestamo
    after insert on prestamo
    for each row
begin
    update alumno set numPrestamos = (numPrestamos + 1) where alumno.id = new.idAlumno;
    update juego set disponible = 0 where juego.id = new.idJuego;
end $$
delimiter ;

/*PROCEDURE*/

delimiter $$
drop procedure if exists eliminaPrestamo$$
create procedure eliminaPrestamo(IN id_prestamo int, IN id_alumno int, IN sancion_alumno date, OUT resultado int)
begin
    declare exit handler for sqlwarning, sqlexception
        begin
            set resultado = 1;
            rollback ;
        end ;
    start transaction;
        delete from prestamo where id = id_prestamo;
        update alumno set sancion = sancion_alumno where id = id_alumno;
        set resultado = 0;
    commit ;
end $$
delimiter ;
