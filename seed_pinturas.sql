USE pinturas_db;

SET @creador_id = 1;

INSERT INTO usuarios (nombre, apellido, correo, password) VALUES
('Ana', 'Martínez', 'ana@correo.com',
 '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy');

INSERT INTO pinturas (titulo, anio, descripcion, url_imagen, cantidad, precio, creador_id) VALUES
('Noche estrellada sobre el Ródano', 1888,
 'Una vista nocturna del río Ródano donde las luces de la ciudad se reflejan en el agua bajo un cielo cargado de estrellas, capturando la calma y el misterio de la noche.',
 'https://upload.wikimedia.org/wikipedia/commons/9/94/Starry_Night_Over_the_Rhone.jpg',
 8, 1850.00, @creador_id),

('La gran ola de Kanagawa', 1831,
 'Grabado japonés que muestra una ola colosal a punto de romper sobre tres botes, con el monte Fuji asomando pequeño al fondo, símbolo de la fuerza de la naturaleza.',
 'https://upload.wikimedia.org/wikipedia/commons/0/0d/Great_Wave_off_Kanagawa2.jpg',
 12, 1200.00, @creador_id),

('Impresión, sol naciente', 1872,
 'La obra que dio nombre al impresionismo, mostrando el puerto de Le Havre al amanecer con pinceladas sueltas y una atmósfera brumosa llena de luz anaranjada.',
 'https://upload.wikimedia.org/wikipedia/commons/5/54/Claude_Monet%2C_Impression%2C_soleil_levant.jpg',
 5, 2400.00, @creador_id),

('El nacimiento de Venus', 1485,
 'Composición renacentista donde la diosa Venus emerge del mar sobre una concha, rodeada de figuras mitológicas, celebrando la belleza y la armonía clásica.',
 'https://upload.wikimedia.org/wikipedia/commons/0/0b/Sandro_Botticelli_-_La_nascita_di_Venere_-_Google_Art_Project_-_edited.jpg',
 3, 3100.00, @creador_id),

('Almendro en flor', 1890,
 'Ramas de almendro florecido recortadas contra un cielo azul intenso, una obra luminosa y esperanzadora inspirada en el arte japonés y pintada como regalo familiar.',
 'https://upload.wikimedia.org/wikipedia/commons/9/9d/Vincent_van_Gogh_-_Almond_blossom_-_VGM_F671.jpg',
 10, 1650.00, @creador_id),

('La joven de la perla', 1665,
 'Retrato íntimo de una muchacha que vuelve la mirada hacia el espectador, con un turbante azul y un pendiente de perla que captura la luz, conocido como la Mona Lisa del norte.',
 'https://upload.wikimedia.org/wikipedia/commons/0/0f/1665_Girl_with_a_Pearl_Earring.jpg',
 0, 2750.00, @creador_id);

INSERT INTO compras (usuario_id, pintura_id)
SELECT 1, id FROM pinturas WHERE titulo = 'La gran ola de Kanagawa';

INSERT INTO compras (usuario_id, pintura_id)
SELECT 1, id FROM pinturas WHERE titulo = 'Almendro en flor';

INSERT INTO compras (usuario_id, pintura_id)
SELECT 2, id FROM pinturas WHERE titulo = 'La gran ola de Kanagawa';

INSERT INTO compras (usuario_id, pintura_id)
SELECT 2, id FROM pinturas WHERE titulo = 'Impresión, sol naciente';

UPDATE pinturas SET cantidad = cantidad - 1 WHERE titulo = 'La gran ola de Kanagawa';
UPDATE pinturas SET cantidad = cantidad - 1 WHERE titulo = 'Almendro en flor';
UPDATE pinturas SET cantidad = cantidad - 1 WHERE titulo = 'La gran ola de Kanagawa';
UPDATE pinturas SET cantidad = cantidad - 1 WHERE titulo = 'Impresión, sol naciente';

