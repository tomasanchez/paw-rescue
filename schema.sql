
    create table Asociaciones (
        id bigint not null auto_increment,
        coordenadaX varchar(255),
        coordenadaY varchar(255),
        direccion varchar(255),
        primary key (id)
    )

    create table Caracteristica (
        id bigint not null auto_increment,
        valor varchar(255),
        primary key (id)
    )

    create table Caracteristicas_By_Mascota (
        mascota_id bigint not null,
        caracteritsitca_id bigint not null
    )

    create table Caracteristicas_By_MascotaEncontrada (
        caracteritsitca_id bigint not null,
        mascota_id bigint not null
    )

    create table Chapitas (
        id bigint not null auto_increment,
        owner_id bigint,
        primary key (id)
    )

    create table Mascota (
        id bigint not null auto_increment,
        apodo varchar(255),
        descripcionFisica varchar(255),
        edad integer not null,
        nombre varchar(255),
        sexo varchar(255),
        tipoMascota varchar(255),
        chapita_id bigint,
        owner_id bigint,
        primary key (id)
    )

    create table MascotaEncontrada_foto (
        MascotaEncontrada_id bigint not null,
        foto varchar(255)
    )

    create table Mascota_fotos (
        Mascota_id bigint not null,
        fotos varchar(255)
    )

    create table Mascotas_Encontradas (
        id bigint not null auto_increment,
        descripcion varchar(255),
        fecha tinyblob,
        coordenadaX varchar(255),
        coordenadaY varchar(255),
        tamanio varchar(255),
        tipoMascota varchar(255),
        chapita_id bigint,
        primary key (id)
    )

    create table Preferencia (
        DTYPE varchar(31) not null,
        id bigint not null auto_increment,
        patioGrande bit,
        tipo varchar(255),
        primary key (id)
    )

    create table Preg_Posibles_Respuestas (
        pregunta_id bigint not null,
        rta_id bigint not null
    )

    create table Preguntas (
        tipo varchar(31) not null,
        id bigint not null auto_increment,
        encuesta varchar(255),
        pregunta_id bigint,
        primary key (id)
    )

    create table Preguntas_by_Adoptar (
        pregunta_id bigint,
        publicacion_id bigint,
        respuesta_id bigint,
        primary key (pregunta_id, publicacion_id)
    )

    create table Preguntas_by_Dar_Adopcion (
        pregunta_id bigint,
        publicacion_id bigint,
        respuesta_id bigint,
        primary key (pregunta_id, publicacion_id)
    )

    create table Publicaciones_Dar_Adopcion (
        id bigint not null auto_increment,
        activa bit not null,
        mascota_id bigint,
        primary key (id)
    )

    create table Publicaciones_De_Adoptar (
        id bigint not null auto_increment,
        activa bit not null,
        interesado_id bigint,
        primary key (id)
    )

    create table Publicaciones_Rescates (
        id bigint not null auto_increment,
        activa bit not null,
        asociacion_id bigint,
        mascota_id bigint,
        primary key (id)
    )

    create table Refugios (
        id bigint not null auto_increment,
        direccion varchar(255),
        nombre varchar(255),
        telefono varchar(255),
        primary key (id)
    )

    create table Rescate (
        id bigint not null auto_increment,
        apellido varchar(255),
        contacto_apellido varchar(255),
        mail varchar(255),
        contacto_nombre varchar(255),
        telefono varchar(255),
        document_id bigint,
        tipo varchar(255),
        fechaNacimiento tinyblob,
        nombre varchar(255),
        domicilioRescatista varchar(255),
        rescatistaAlbergaMascota bit,
        mascota_id bigint,
        refugio_id bigint,
        primary key (id)
    )

    create table Respuestas (
        id bigint not null auto_increment,
        respuesta varchar(255),
        primary key (id)
    )

    create table Usuario (
        id bigint not null auto_increment,
        apellido varchar(255),
        contacto_apellido varchar(255),
        mail varchar(255),
        contacto_nombre varchar(255),
        telefono varchar(255),
        document_id bigint,
        tipo varchar(255),
        fechaNacimiento tinyblob,
        nombre varchar(255),
        password varchar(255),
        privilegio integer,
        usuario varchar(255),
        asociacion_id bigint,
        primary key (id)
    )

    alter table Caracteristica 
        add constraint UK_dbypkqxveoli8c6e24wge1nlx  unique (valor)

    alter table Usuario 
        add constraint UK_op7pcokowhg0t5hyblw6qkd1j  unique (usuario, mail)

    alter table Caracteristicas_By_Mascota 
        add constraint FK_aniwoc3qagh4a7nt3vq8g0iom 
        foreign key (caracteritsitca_id) 
        references Caracteristica (id)

    alter table Caracteristicas_By_Mascota 
        add constraint FK_24b6rrw5koqsqd1fmvdyutqgy 
        foreign key (mascota_id) 
        references Mascota (id)

    alter table Caracteristicas_By_MascotaEncontrada 
        add constraint FK_7jugvxutgfrhu93i85h7ri84f 
        foreign key (mascota_id) 
        references Caracteristica (id)

    alter table Caracteristicas_By_MascotaEncontrada 
        add constraint FK_od27ytugs4c8jhljesc5hu2g7 
        foreign key (caracteritsitca_id) 
        references Mascotas_Encontradas (id)

    alter table Chapitas 
        add constraint FK_io8ups5s29rstj8p0v0o4srdn 
        foreign key (owner_id) 
        references Usuario (id)

    alter table Mascota 
        add constraint FK_fd5yx0y0l9ypgclkd0db7d1dx 
        foreign key (chapita_id) 
        references Chapitas (id)

    alter table Mascota 
        add constraint FK_6tlsnu1x25h30qs1yq1sl3tks 
        foreign key (owner_id) 
        references Usuario (id)

    alter table MascotaEncontrada_foto 
        add constraint FK_o52hm44cg4ttxrf16vonq3cte 
        foreign key (MascotaEncontrada_id) 
        references Mascotas_Encontradas (id)

    alter table Mascota_fotos 
        add constraint FK_dclevnore3slbildn3hia9nlo 
        foreign key (Mascota_id) 
        references Mascota (id)

    alter table Mascotas_Encontradas 
        add constraint FK_nqhjj26w5yb86iu3wmkka1kkw 
        foreign key (chapita_id) 
        references Chapitas (id)

    alter table Preg_Posibles_Respuestas 
        add constraint FK_tdc1lc8w42e5n2hd1rhsnsy6q 
        foreign key (rta_id) 
        references Respuestas (id)

    alter table Preg_Posibles_Respuestas 
        add constraint FK_141uju5n174gvytl3ix4npjtd 
        foreign key (pregunta_id) 
        references Preguntas (id)

    alter table Preguntas 
        add constraint FK_598iop8xejo4bx4ek7u95f6dw 
        foreign key (pregunta_id) 
        references Asociaciones (id)

    alter table Preguntas_by_Adoptar 
        add constraint FK_d5kw0swgkr7q0q64d54u6xtl8 
        foreign key (pregunta_id) 
        references Preguntas (id)

    alter table Preguntas_by_Adoptar 
        add constraint FK_e067xbpd4fw6xd26qpg3mn03d 
        foreign key (publicacion_id) 
        references Publicaciones_De_Adoptar (id)

    alter table Preguntas_by_Adoptar 
        add constraint FK_len2pqc8kk6rkdq2vmjl7gj3o 
        foreign key (respuesta_id) 
        references Respuestas (id)

    alter table Preguntas_by_Dar_Adopcion 
        add constraint FK_23xy4bhu11be38th32lw2xsqw 
        foreign key (pregunta_id) 
        references Preguntas (id)

    alter table Preguntas_by_Dar_Adopcion 
        add constraint FK_o0lgvy4y4qtapy9l0q3t87hm6 
        foreign key (publicacion_id) 
        references Publicaciones_Dar_Adopcion (id)

    alter table Preguntas_by_Dar_Adopcion 
        add constraint FK_ijvgr8u4al9hacj0rjy466h6i 
        foreign key (respuesta_id) 
        references Respuestas (id)

    alter table Publicaciones_Dar_Adopcion 
        add constraint FK_ot3u6fbqyngkpg2j2q7wkcwr5 
        foreign key (mascota_id) 
        references Mascota (id)

    alter table Publicaciones_De_Adoptar 
        add constraint FK_sj7c2p3x4892huyvecn7nv4tc 
        foreign key (interesado_id) 
        references Usuario (id)

    alter table Publicaciones_Rescates 
        add constraint FK_c6qeo12to2p7mukqp2e2w9mwo 
        foreign key (asociacion_id) 
        references Asociaciones (id)

    alter table Publicaciones_Rescates 
        add constraint FK_4fdv5vc6y0hf7u9o6g7ue5ta2 
        foreign key (mascota_id) 
        references Mascotas_Encontradas (id)

    alter table Rescate 
        add constraint FK_8as215usm6q9mm4vqmsfha1qe 
        foreign key (mascota_id) 
        references Mascotas_Encontradas (id)

    alter table Rescate 
        add constraint FK_r98lyin5qxgjiwjojug9x8teb 
        foreign key (refugio_id) 
        references Refugios (id)

    alter table Usuario 
        add constraint FK_87gmiyn3ee60a95udalecm7t2 
        foreign key (asociacion_id) 
        references Asociaciones (id)
