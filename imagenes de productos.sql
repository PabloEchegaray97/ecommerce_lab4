delete from product_image;
select * from product_image;
ALTER TABLE product_image AUTO_INCREMENT = 1; -- Esto es solo por si momentaneamete querés reiniciar los valores de id a 1

INSERT INTO product_image (
  link,
  product_id,
  is_principal_product_image,
  created_at,
  is_active
) VALUES 
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/2/10042258_800.jpg',
  1,
  true,
  NOW(),
  true
),
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/2/10042259_800.jpg',
  1,
  false,
  NOW(),
  true
),
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/2/10042260_800.jpg',
  1,
  false,
  NOW(),
  true
),
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/2/10042261_800.jpg',
  1,
  false,
  NOW(),
  true
);

-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------
-- U CLASSIC SLIP-ON
-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------

INSERT INTO product_image (
  link,
  product_id,
  is_principal_product_image,
  created_at,
  is_active
) VALUES 
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/1/10039065_800.jpg',
  4,
  true,
  NOW(),
  true
),
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/1/10039066_800.jpg',
  4,
  false,
  NOW(),
  true
),
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/1/10039068_800.jpg',
  4,
  false,
  NOW(),
  true
),
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/1/10039067_800.jpg',
  4,
  false,
  NOW(),
  true
);

-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------
-- U SK8-HI
-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------

INSERT INTO product_image (
  link,
  product_id,
  is_principal_product_image,
  created_at,
  is_active
) VALUES 
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/12/6/10254259_800.jpg',
  7,
  true,
  NOW(),
  true
),
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/12/6/10254041_800.jpg',
  7,
  false,
  NOW(),
  true
),
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/12/6/10254042_800.jpg',
  7,
  false,
  NOW(),
  true
),
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/12/6/10254043_800.jpg',
  7,
  false,
  NOW(),
  true
);

-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------
-- U AUTHENTIC
-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------

INSERT INTO product_image (
  link,
  product_id,
  is_principal_product_image,
  created_at,
  is_active
) VALUES 
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/1/10039081_800.jpg',
  10,
  true,
  NOW(),
  true
),
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/1/10039082_800.jpg',
  10,
  false,
  NOW(),
  true
),
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/1/10039084_800.jpg',
   10,
  false,
  NOW(),
  true
),
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/10/1/10039083_800.jpg',
   10,
  false,
  NOW(),
  true
);
-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------
-- U OLD SKOOL
-- ----------------------------------------------------------------------------------------------
-- ----------------------------------------------------------------------------------------------

INSERT INTO product_image (
  link,
  product_id,
  is_principal_product_image,
  created_at,
  is_active
) VALUES 
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/12/30/10369245_800.jpg',
  13,
  true,
  NOW(),
  true
),
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/12/30/10369247_800.jpg',
  13,
  false,
  NOW(),
  true
),
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/12/30/10369247_800.jpg',
   13,
  false,
  NOW(),
  true
),
(
  'https://mmgrim2.azureedge.net/MediaFiles/Grimoldi/2024/12/30/10369247_800.jpg',
   13,
  false,
  NOW(),
  true
);
