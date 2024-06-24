BASE DE DATOS

CREATE TABLE tbUsuarios(
UUID_usuario VARCHAR2(50),
correoElectronico VARCHAR2(50),
clave VARCHAR2(50)
)

select * from tbUsuarios

create table Ticket (
uuid varchar2(50),
NumeroTicket int,
TituloTicket varchar2(20),
DescripcionTicket varchar2(100),
ResponsableTicket varchar2(50),
CorreoAutor varchar2(50),
TelefonoAutor varchar2(50),
Ubicacion varchar2(50),
EstadoTicket varchar2(10)
);

select * from Ticket;



