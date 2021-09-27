
    create table Refugio (
        id bigint generated by default as identity (start with 1),
        capacidad integer not null,
        direccion varchar(255),
        lugares_disponibles integer not null,
        nombre varchar(255),
        patio boolean,
        telefono varchar(255),
        primary key (id)
    )
