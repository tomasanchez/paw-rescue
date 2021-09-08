
    create table Refugio (
        id bigint not null auto_increment,
        capacidad integer not null,
        direccion varchar(255),
        lugares_disponibles integer not null,
        nombre varchar(255),
        patio bit,
        telefono varchar(255),
        primary key (id)
    )
