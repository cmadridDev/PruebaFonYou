INSERT INTO estudiante (nombre, fecha_nacimiento, ciudad, zona_horaria) VALUES
('César', '1991-08-11', 'Machala', 'America/Guayaquil'),--id: 1
('Carla', '1975-04-22', 'Paris', 'Europe/Paris'),--id: 2
('José', '2005-12-20', 'Cairo', 'Africa/Cairo'),--id: 3
('Pedro', '2000-02-28', 'México', 'America/Mexico_City'),--id: 4
('Rosa', '1998-09-24', 'Tokio', 'Asia/Tokyo');--id: 5

INSERT INTO examen (nombre, descripcion) VALUES
('Geografía', 'Exámen de geografía'),--id: 1
('Matemáticas', 'Exámen de matemáticas II'),--id: 2
('Arte', 'Historia del Arte'),--id: 3
('Literatura', 'Literatura Moderna'),--id: 4
('Inglés', 'Ingles Básico A'),--id: 5
('Música', 'Música clásica');--id: 6

INSERT INTO pregunta (id_examen, descripcion, puntaje) VALUES
(1, 'Capital de Ecuador', 30),--id: 1
(1, 'Capital de Colombia', 30),--id: 2
(1, 'Capital de Perú', 40),--id: 3

(2, '2+2', 25),--id: 4
(2, '5*5', 25),--id: 5
(2, '1+5', 25),--id: 6
(2, '2!', 25),--id: 7

(3, 'Autor Mona Lisa', 30),--id: 8
(3, 'Autor Última Cena', 70),--id: 9

(4, 'Que es la literatura moderna', 100),--id: 10

(5, 'Apple', 100),--id: 11

(6, 'Do Re Xx Fa Sol', 30),--id: 12
(6, 'Músico Sordo', 70);--id: 13

INSERT INTO opcion (id_pregunta, descripcion, correcta) VALUES
(1, 'Quito', true),--id: 1
(1, 'Guayaquil', false),--id: 2
(1, 'Cuenca', false),--id: 3
(1, 'Esmeraldas', false),--id: 4

(2, 'Bogota', true),--id: 5
(2, 'Medellin', false),--id: 6
(2, 'Cali', false),--id: 7
(2, 'Barranquilla', false),--id: 8

(3, 'Lima', true),--id: 9
(3, 'Cusco', false),--id: 10
(3, 'Trujillo', false),--id: 11
(3, 'Callao', false),--id: 12

(4, '4', true),--id: 13
(4, '5', false),--id: 14
(4, '6', false),--id: 15
(4, '7', false),--id: 16

(5, '25', true),--id: 17
(5, '10', false),--id: 18
(5, '15', false),--id: 19
(5, '5', false),--id: 20

(6, '6', true),--id: 21
(6, '7', false),--id: 22
(6, '2', false),--id: 23
(6, '1', false),--id: 24

(7, '2', true),--id: 25
(7, '3', false),--id: 26
(7, '7', false),--id: 27
(7, '5', false),--id: 28

(8, 'Leonardo da Vinci', true),--id: 29
(8, 'Picasso', false),--id: 30
(8, 'Donatello', false),--id: 31
(8, 'Guayasamin', false),--id: 32

(9, 'Leonardo da Vinci', true),--id: 33
(9, 'Picasso', false),--id: 34
(9, 'Donatello', false),--id: 35
(9, 'Guayasamin', false),--id: 36

(10, 'Moderna', true),--id: 37
(10, 'Otras opciones', false),--id: 38
(10, 'Una opcion falsa', false),--id: 39
(10, 'Esta no es', false),--id: 40

(11, 'Manzana', true),--id: 41
(11, 'Pera', false),--id: 42
(11, 'Celular', false),--id: 43
(11, 'Mac', false),--id: 44

(12, 'Mi', true),--id: 45
(12, 'La', false),--id: 46
(12, 'Bemol', false),--id: 47
(12, 'Sostenido', false),--id: 48

(13, 'Beethoven', true),--id: 49
(13, 'Bad Bunny', false),--id: 50
(13, 'Mozart', false),--id: 51
(13, 'Bach', false);--id: 52
