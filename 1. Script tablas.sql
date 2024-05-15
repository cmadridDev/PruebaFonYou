CREATE TABLE estudiante
(
    id serial NOT NULL,
    nombre character varying(100) NOT NULL,
    fecha_nacimiento timestamp without time zone NOT NULL,
    ciudad character varying(100) NOT NULL,
    zona_horaria character varying(50) NOT NULL,
    fecha_anulado timestamp without time zone,
    PRIMARY KEY (id)
);

CREATE TABLE examen
(
    id serial NOT NULL,
    nombre character varying(255) NOT NULL,
    descripcion character varying(255) NOT NULL,
    fecha_anulado timestamp without time zone,
    PRIMARY KEY (id)
);

CREATE TABLE examen_estudiante
(
    id_examen integer NOT NULL,
    id_estudiante integer NOT NULL,
    fecha_examen timestamp without time zone NOT NULL,
    zona_horaria character varying(50) NOT NULL,
    calificacion integer,
    fecha_anulado timestamp without time zone,
    PRIMARY KEY (id_examen, id_estudiante),
    FOREIGN KEY (id_examen)
        REFERENCES examen (id),
    FOREIGN KEY (id_estudiante)
        REFERENCES estudiante (id)
);

CREATE TABLE pregunta
(
    id serial NOT NULL,
    id_examen integer NOT NULL,
    descripcion character varying(255) NOT NULL,
	puntaje integer NOT NULL,
    fecha_anulado timestamp without time zone,
    PRIMARY KEY (id),
    FOREIGN KEY (id_examen)
        REFERENCES examen (id)
);

CREATE TABLE opcion
(
    id serial NOT NULL,
    id_pregunta integer NOT NULL,
    descripcion character varying(255) NOT NULL,
    correcta boolean NOT NULL DEFAULT false,
    PRIMARY KEY (id),
    FOREIGN KEY (id_pregunta)
        REFERENCES pregunta (id)
);

CREATE TABLE respuesta_examen_estudiante
(
    id_estudiante integer NOT NULL,
    id_examen integer NOT NULL,
    id_pregunta integer NOT NULL,
    id_opcion integer NOT NULL,
    PRIMARY KEY (id_opcion, id_pregunta, id_examen, id_estudiante),
    FOREIGN KEY (id_estudiante)
        REFERENCES estudiante (id),
    FOREIGN KEY (id_examen)
        REFERENCES examen (id),
    FOREIGN KEY (id_pregunta)
        REFERENCES pregunta (id),
    FOREIGN KEY (id_opcion)
        REFERENCES opcion (id)
);


