
-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------
-- products 
-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------

Delete from products;

select * from products;

ALTER TABLE products AUTO_INCREMENT = 1; -- Esto es solo por si momentaneamete querés reiniciar los valores de id a 1

-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------
-- U knu Skool
-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------

INSERT INTO products (
  name,
  price,
  description,
  image,
  category_id,
  colour_id,
  brand_id,
  status,
  is_active,
  created_at
) VALUES 
(
  'U Knu Skool',
  189000,
  'La Knu Skool es un modelo reeditado de los años 90, cuando las zapatillas de skate eran extra hinchadas. Confeccionadas con parte superior de ante y lienzo, este modelo de perfil bajo presenta una gran lengüeta hinchada y un cuello en el tobillo, lo que le da un aspecto exagerado que juega con la Old Skool original. Manteniendo la estética "Off The Wall", nuestro icónico Vans Sidestripe ™ se ha rediseñado como un molde 3D de diamante hinchado, que se suma a la apariencia y sensación general gruesa. La adición de tiradores en el talón ofrece un fácil acceso para entrar y salir. Contiene tiradores en el talón para facilitar la calzada y suela waffle de goma distintiva.',
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/2/10042258_800.jpg',
  7,
  1,
  1,
  true,
  true,
  NOW()
),
(
  'U Knu Skool',
  189000,
  'La Knu Skool es un modelo reeditado de los años 90, cuando las zapatillas de skate eran extra hinchadas. Confeccionadas con parte superior de ante y lienzo, este modelo de perfil bajo presenta una gran lengüeta hinchada y un cuello en el tobillo, lo que le da un aspecto exagerado que juega con la Old Skool original. Manteniendo la estética "Off The Wall", nuestro icónico Vans Sidestripe ™ se ha rediseñado como un molde 3D de diamante hinchado, que se suma a la apariencia y sensación general gruesa. La adición de tiradores en el talón ofrece un fácil acceso para entrar y salir. Contiene tiradores en el talón para facilitar la calzada y suela waffle de goma distintiva.',
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/9/20/10010148_800.jpg',
  7,
  2,
  1,
  true,
  true,
  NOW()
),
(
 'U Knu Skool',
  189000,
  'La Knu Skool es un modelo reeditado de los años 90, cuando las zapatillas de skate eran extra hinchadas. Confeccionadas con parte superior de ante y lienzo, este modelo de perfil bajo presenta una gran lengüeta hinchada y un cuello en el tobillo, lo que le da un aspecto exagerado que juega con la Old Skool original. Manteniendo la estética "Off The Wall", nuestro icónico Vans Sidestripe ™ se ha rediseñado como un molde 3D de diamante hinchado, que se suma a la apariencia y sensación general gruesa. La adición de tiradores en el talón ofrece un fácil acceso para entrar y salir. Contiene tiradores en el talón para facilitar la calzada y suela waffle de goma distintiva.',
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2025/1/20/10518610_800.jpg',
  7,
  10,
  1,
  true,
  true,
  NOW()
);

-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------
-- U CLASSIC SLIP-ON
-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------
INSERT INTO products (
  name,
  price,
  description,
  image,
  category_id,
  colour_id,
  brand_id,
  status,
  is_active,
  created_at
) VALUES 
(
  'U CLASSIC SLIP-ON',
79000,
'El Canvas Classic Slip-on tiene un perfil bajo, parte superior de lona con apliques laterales elásticos, etiqueta de la bandera Vans y suela original Waffle de Vans.',
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/1/10039065_800.jpg',
  6,
  1,
  1,
  true,
  true,
  NOW()
),
(
  'U CLASSIC SLIP-ON',
  79000,
'El Canvas Classic Slip-on tiene un perfil bajo, parte superior de lona con apliques laterales elásticos, etiqueta de la bandera Vans y suela original Waffle de Vans.',
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/1/10039055_800.jpg',
  6,
  2,
  1,
  true,
  true,
  NOW()
),
(
 'U CLASSIC SLIP-ON',
  79000,
'El Canvas Classic Slip-on tiene un perfil bajo, parte superior de lona con apliques laterales elásticos, etiqueta de la bandera Vans y suela original Waffle de Vans.',
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/9/24/10017940_800.jpg',
  6,
  1,
  1,
  true,
  true,
  NOW()
);

-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------
-- U SK8-HI
-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------
INSERT INTO products (
  name,
  price,
  description,
  image,
  category_id,
  colour_id,
  brand_id,
  status,
  is_active,
  created_at
) VALUES 
(
  'U SK8-HI',
146000,
'El Canvas Classic Slip-on tiene un perfil bajo, parte superior de lona con apliques laterales elásticos, etiqueta de la bandera Vans y suela original Waffle de Vans.',
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/12/6/10254259_800.jpg',
  5,
  1,
  1,
  true,
  true,
  NOW()
),
(
 'U SK8-HI',
146000,
'El Canvas Classic Slip-on tiene un perfil bajo, parte superior de lona con apliques laterales elásticos, etiqueta de la bandera Vans y suela original Waffle de Vans.',
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2022/2_1/0/110/253/7273800.jpg',
  5,
  2,
  1,
  true,
  true,
  NOW()
),
(
 'U SK8-HI',
146000,
'El Canvas Classic Slip-on tiene un perfil bajo, parte superior de lona con apliques laterales elásticos, etiqueta de la bandera Vans y suela original Waffle de Vans.',
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2023/2/13/8093964.jpg',
  5,
  4,
  1,
  true,
  true,
  NOW()
);

-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------
-- U AUTHENTIC
-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------
INSERT INTO products (
  name,
  price,
  description,
  image,
  category_id,
  colour_id,
  brand_id,
  status,
  is_active,
  created_at
) VALUES 
(
  'U AUTHENTIC',
79000,
'La Authentic es el modelo fundamental y clásico de Vans. Lanzada en el año 1966, ahora es un modelo icónico de Vans. Es un modelo simple, con cordones, parte superior de lona duradera, ojales de metal, etiqueta de la bandera de Vans y suela original Waffle de Vans. *IMPORTANTE: Del talle 35 al 38 viene con 4 ojales y del 38.5 al 47 con 5.',
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/1/10039081_800.jpg',
  4,
  1,
  1,
  true,
  true,
  NOW()
),
(
 'U AUTHENTIC',
79000,
'La Authentic es el modelo fundamental y clásico de Vans. Lanzada en el año 1966, ahora es un modelo icónico de Vans. Es un modelo simple, con cordones, parte superior de lona duradera, ojales de metal, etiqueta de la bandera de Vans y suela original Waffle de Vans. *IMPORTANTE: Del talle 35 al 38 viene con 4 ojales y del 38.5 al 47 con 5.',
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/12/30/10369125_800.jpg',
  4,
  3,
  1,
  true,
  true,
  NOW()
),
(
  'U AUTHENTIC',
79000,
'La Authentic es el modelo fundamental y clásico de Vans. Lanzada en el año 1966, ahora es un modelo icónico de Vans. Es un modelo simple, con cordones, parte superior de lona duradera, ojales de metal, etiqueta de la bandera de Vans y suela original Waffle de Vans. *IMPORTANTE: Del talle 35 al 38 viene con 4 ojales y del 38.5 al 47 con 5.',
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/12/11/10270049_800.jpg',
  4,
  7,
  1,
  true,
  true,
  NOW()
);
-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------
-- U OLD SKOOL
-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------
INSERT INTO products (
  name,
  price,
  description,
  image,
  category_id,
  colour_id,
  brand_id,
  status,
  is_active,
  created_at
) VALUES 
(
  'U OLD SKOOL',
132000,
'Las Old Skool son las zapatillas clásicas de Vans y el primer modelo en lucir el icónico sidestripe de la marca. Nacieron como un calzado para skaters de los años 70´y con el correr de las décadas se transformó en un modelo básico de lifestyle. Son zapatillas de corte bajo confeccionadas con capellada resistente, tiene punteras reforzadas para añadir durabilidad, la suela de caucho original Vans Wafflesole y cuello acolchado para ofrecer sujeción y confort.',
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/12/30/10369247_800.jpg',
  2,
  9,
  1,
  true,
  true,
  NOW()
),
(
 'U OLD SKOOL',
132000,
'Las Old Skool son las zapatillas clásicas de Vans y el primer modelo en lucir el icónico sidestripe de la marca. Nacieron como un calzado para skaters de los años 70´y con el correr de las décadas se transformó en un modelo básico de lifestyle. Son zapatillas de corte bajo confeccionadas con capellada resistente, tiene punteras reforzadas para añadir durabilidad, la suela de caucho original Vans Wafflesole y cuello acolchado para ofrecer sujeción y confort.',
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2021/3_5/0/96/153/6330778.jpg',
  2,
  5,
  1,
  true,
  true,
  NOW()
),
(
  'U OLD SKOOL',
132000,
'Las Old Skool son las zapatillas clásicas de Vans y el primer modelo en lucir el icónico sidestripe de la marca. Nacieron como un calzado para skaters de los años 70´y con el correr de las décadas se transformó en un modelo básico de lifestyle. Son zapatillas de corte bajo confeccionadas con capellada resistente, tiene punteras reforzadas para añadir durabilidad, la suela de caucho original Vans Wafflesole y cuello acolchado para ofrecer sujeción y confort.',
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/12/11/10270414_800.jpg',
  2,
  6,
  1,
  true,
  true,
  NOW()
);