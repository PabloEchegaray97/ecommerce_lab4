package com.example.apirest.config;

import com.example.apirest.entities.*;
import com.example.apirest.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ColourServiceImpl colourService;
    
    @Autowired
    private BrandServiceImpl brandService;
    
    @Autowired
    private TypeServiceImpl typeService;
    
    @Autowired
    private CategoryServiceImpl categoryService;
    
    @Autowired
    private ProductServiceImp productService;
    
    @Autowired
    private ProductImageServiceImpl productImageService;

    @Autowired
    private SizeServiceImpl sizeService;
    
    @Autowired
    private ProductSizeService productSizeService;
    
    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private AdressServiceImpl adressService;
    
    @Autowired
    private UsersAdressService usersAdressService;
    
    @Autowired
    private PurchaseOrderServiceImpl purchaseOrderService;
    
    @Autowired
    private DetailServiceImpl detailService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Inicializando base de datos con datos de prueba...");
        System.out.println("Tablas recreadas - cargando datos nuevos...");
        
        // Cargar datos iniciales siempre (create-drop garantiza tablas vacías)
        loadInitialData();
        
        System.out.println("Base de datos inicializada correctamente!");
    }

    private void loadInitialData() throws Exception {
        loadColours();
        loadBrands();
        loadTypes();
        loadCategories();
        loadProducts();
        loadProductImages();
        loadSizes();
        loadProductSizes();
        loadUsers();
        loadUserAddresses();
        loadPurchaseOrder();
        loadCancelledOrder();
        loadPaidOrder();
    }

    private void loadColours() throws Exception {
        System.out.println("Cargando colores...");
        
        String[][] colourData = {
            {"Negro", "#000000"},
            {"Blanco", "#FFFFFF"},
            {"Rojo", "#FF0000"},
            {"Azul", "#0000FF"},
            {"Verde", "#00FF00"},
            {"Amarillo", "#FFFF00"},
            {"Rosa", "#FF69B4"},
            {"Naranja", "#FFA500"},
            {"Morado", "#800080"},
            {"Gris", "#808080"}
        };

        for (String[] data : colourData) {
            Colour colour = new Colour();
            colour.setName(data[0]);
            colour.setValue(data[1]);
            colourService.save(colour);
        }
        
        System.out.println("OK " + colourData.length + " colores cargados");
    }

    private void loadBrands() throws Exception {
        System.out.println("Cargando marcas...");
        
        String[] brandNames = {"Vans"};

        for (String name : brandNames) {
            Brand brand = new Brand();
            brand.setName(name);
            brandService.save(brand);
        }
        
        System.out.println("OK " + brandNames.length + " marcas cargadas");
    }

    private void loadTypes() throws Exception {
        System.out.println("Cargando tipos...");
        
        String[] typeNames = {
            "Deportiva", "Casual", "Running", "Basketball", 
            "Skateboarding", "Training", "Lifestyle", "Outdoor"
        };

        for (String name : typeNames) {
            Type type = new Type();
            type.setName(name);
            typeService.save(type);
        }
        
        System.out.println("OK " + typeNames.length + " tipos cargados");
    }

    private void loadCategories() throws Exception {
        System.out.println("Cargando categorías...");
        
        String[][] categoryData = {
            {"Zapatillas Deportivas", "1"},
            {"Zapatillas Casuales", "2"},
            {"Zapatillas Running", "3"},
            {"Zapatillas Basketball", "4"},
            {"Zapatillas Skate", "5"},
            {"Zapatillas Training", "6"},
            {"Zapatillas Lifestyle", "7"},
            {"Zapatillas Outdoor", "8"}
        };

        for (String[] data : categoryData) {
            Category category = new Category();
            category.setName(data[0]);
            category.setTypeId(Integer.parseInt(data[1]));
            categoryService.save(category);
        }
        
        System.out.println("OK " + categoryData.length + " categorías cargadas");
    }

    private void loadProducts() throws Exception {
        System.out.println("Cargando productos...");
        
        // Basado en productos.sql
        Object[][] productData = {
            // U Knu Skool
            {"U Knu Skool", 189000.0, 
             "La Knu Skool es un modelo reeditado de los años 90, cuando las zapatillas de skate eran extra hinchadas. Confeccionadas con parte superior de ante y lienzo, este modelo de perfil bajo presenta una gran lengüeta hinchada y un cuello en el tobillo, lo que le da un aspecto exagerado que juega con la Old Skool original. Manteniendo la estética \"Off The Wall\", nuestro icónico Vans Sidestripe ™ se ha rediseñado como un molde 3D de diamante hinchado, que se suma a la apariencia y sensación general gruesa. La adición de tiradores en el talón ofrece un fácil acceso para entrar y salir. Contiene tiradores en el talón para facilitar la calzada y suela waffle de goma distintiva.",
             "http://localhost:9000/api/v1/product-images/files/1.jpg", 5, 1, 1},
            {"U Knu Skool", 189000.0, 
             "La Knu Skool es un modelo reeditado de los años 90, cuando las zapatillas de skate eran extra hinchadas. Confeccionadas con parte superior de ante y lienzo, este modelo de perfil bajo presenta una gran lengüeta hinchada y un cuello en el tobillo, lo que le da un aspecto exagerado que juega con la Old Skool original. Manteniendo la estética \"Off The Wall\", nuestro icónico Vans Sidestripe ™ se ha rediseñado como un molde 3D de diamante hinchado, que se suma a la apariencia y sensación general gruesa. La adición de tiradores en el talón ofrece un fácil acceso para entrar y salir. Contiene tiradores en el talón para facilitar la calzada y suela waffle de goma distintiva.",
             "http://localhost:9000/api/v1/product-images/files/2.jpg", 5, 2, 1},
            {"U Knu Skool", 189000.0, 
             "La Knu Skool es un modelo reeditado de los años 90, cuando las zapatillas de skate eran extra hinchadas. Confeccionadas con parte superior de ante y lienzo, este modelo de perfil bajo presenta una gran lengüeta hinchada y un cuello en el tobillo, lo que le da un aspecto exagerado que juega con la Old Skool original. Manteniendo la estética \"Off The Wall\", nuestro icónico Vans Sidestripe ™ se ha rediseñado como un molde 3D de diamante hinchado, que se suma a la apariencia y sensación general gruesa. La adición de tiradores en el talón ofrece un fácil acceso para entrar y salir. Contiene tiradores en el talón para facilitar la calzada y suela waffle de goma distintiva.",
             "http://localhost:9000/api/v1/product-images/files/3.jpg", 5, 10, 1},
            
            // U CLASSIC SLIP-ON
            {"U CLASSIC SLIP-ON", 79000.0,
             "El Canvas Classic Slip-on tiene un perfil bajo, parte superior de lona con apliques laterales elásticos, etiqueta de la bandera Vans y suela original Waffle de Vans.",
             "http://localhost:9000/api/v1/product-images/files/4.jpg", 2, 1, 1},
            {"U CLASSIC SLIP-ON", 79000.0,
             "El Canvas Classic Slip-on tiene un perfil bajo, parte superior de lona con apliques laterales elásticos, etiqueta de la bandera Vans y suela original Waffle de Vans.",
             "http://localhost:9000/api/v1/product-images/files/5.jpg", 2, 2, 1},
            {"U CLASSIC SLIP-ON", 79000.0,
             "El Canvas Classic Slip-on tiene un perfil bajo, parte superior de lona con apliques laterales elásticos, etiqueta de la bandera Vans y suela original Waffle de Vans.",
             "http://localhost:9000/api/v1/product-images/files/6.jpg", 2, 1, 1},
            
            // U SK8-HI
            {"U SK8-HI", 146000.0,
             "El Canvas Classic Slip-on tiene un perfil bajo, parte superior de lona con apliques laterales elásticos, etiqueta de la bandera Vans y suela original Waffle de Vans.",
             "http://localhost:9000/api/v1/product-images/files/7.jpg", 5, 1, 1},
            {"U SK8-HI", 146000.0,
             "El Canvas Classic Slip-on tiene un perfil bajo, parte superior de lona con apliques laterales elásticos, etiqueta de la bandera Vans y suela original Waffle de Vans.",
             "http://localhost:9000/api/v1/product-images/files/8.jpg", 5, 2, 1},
            {"U SK8-HI", 146000.0,
             "El Canvas Classic Slip-on tiene un perfil bajo, parte superior de lona con apliques laterales elásticos, etiqueta de la bandera Vans y suela original Waffle de Vans.",
             "http://localhost:9000/api/v1/product-images/files/9.jpg", 5, 4, 1},
            
            // U AUTHENTIC
            {"U AUTHENTIC", 79000.0,
             "La Authentic es el modelo fundamental y clásico de Vans. Lanzada en el año 1966, ahora es un modelo icónico de Vans. Es un modelo simple, con cordones, parte superior de lona duradera, ojales de metal, etiqueta de la bandera de Vans y suela original Waffle de Vans. *IMPORTANTE: Del talle 35 al 38 viene con 4 ojales y del 38.5 al 47 con 5.",
             "http://localhost:9000/api/v1/product-images/files/10.jpg", 2, 1, 1},
            {"U AUTHENTIC", 79000.0,
             "La Authentic es el modelo fundamental y clásico de Vans. Lanzada en el año 1966, ahora es un modelo icónico de Vans. Es un modelo simple, con cordones, parte superior de lona duradera, ojales de metal, etiqueta de la bandera de Vans y suela original Waffle de Vans. *IMPORTANTE: Del talle 35 al 38 viene con 4 ojales y del 38.5 al 47 con 5.",
             "http://localhost:9000/api/v1/product-images/files/11.jpg", 2, 3, 1},
            {"U AUTHENTIC", 79000.0,
             "La Authentic es el modelo fundamental y clásico de Vans. Lanzada en el año 1966, ahora es un modelo icónico de Vans. Es un modelo simple, con cordones, parte superior de lona duradera, ojales de metal, etiqueta de la bandera de Vans y suela original Waffle de Vans. *IMPORTANTE: Del talle 35 al 38 viene con 4 ojales y del 38.5 al 47 con 5.",
             "http://localhost:9000/api/v1/product-images/files/12.jpg", 2, 7, 1},
            
            // U OLD SKOOL
            {"U OLD SKOOL", 132000.0,
             "Las Old Skool son las zapatillas clásicas de Vans y el primer modelo en lucir el icónico sidestripe de la marca. Nacieron como un calzado para skaters de los años 70´y con el correr de las décadas se transformó en un modelo básico de lifestyle. Son zapatillas de corte bajo confeccionadas con capellada resistente, tiene punteras reforzadas para añadir durabilidad, la suela de caucho original Vans Wafflesole y cuello acolchado para ofrecer sujeción y confort.",
             "http://localhost:9000/api/v1/product-images/files/13.jpg", 7, 9, 1},
            {"U OLD SKOOL", 132000.0,
             "Las Old Skool son las zapatillas clásicas de Vans y el primer modelo en lucir el icónico sidestripe de la marca. Nacieron como un calzado para skaters de los años 70´y con el correr de las décadas se transformó en un modelo básico de lifestyle. Son zapatillas de corte bajo confeccionadas con capellada resistente, tiene punteras reforzadas para añadir durabilidad, la suela de caucho original Vans Wafflesole y cuello acolchado para ofrecer sujeción y confort.",
             "http://localhost:9000/api/v1/product-images/files/14.jpg", 7, 5, 1},
            {"U OLD SKOOL", 132000.0,
             "Las Old Skool son las zapatillas clásicas de Vans y el primer modelo en lucir el icónico sidestripe de la marca. Nacieron como un calzado para skaters de los años 70´y con el correr de las décadas se transformó en un modelo básico de lifestyle. Son zapatillas de corte bajo confeccionadas con capellada resistente, tiene punteras reforzadas para añadir durabilidad, la suela de caucho original Vans Wafflesole y cuello acolchado para ofrecer sujeción y confort.",
             "http://localhost:9000/api/v1/product-images/files/15.jpg", 7, 6, 1}
        };

        for (Object[] data : productData) {
            Product product = new Product();
            product.setName((String) data[0]);
            product.setPrice((Double) data[1]);
            product.setDescription((String) data[2]);
            product.setImage((String) data[3]);
            product.setCategoryId((Integer) data[4]);
            product.setColourId((Integer) data[5]);
            product.setBrandId((Integer) data[6]);
            product.setStatus(true);
            product.setIsActive(true);
            product.setCreatedAt(LocalDateTime.now());
            productService.save(product);
        }
        
        System.out.println("OK " + productData.length + " productos cargados");
    }

    private void loadProductImages() throws Exception {
        System.out.println("Cargando imágenes de productos...");
        
        // Imágenes para TODOS los 15 productos
        Object[][] imageData = {
            // Producto 1 - U Knu Skool Negro
            {"http://localhost:9000/api/v1/product-images/files/16.jpg", 1, true},
            {"http://localhost:9000/api/v1/product-images/files/17.jpg", 1, false},
            {"http://localhost:9000/api/v1/product-images/files/18.jpg", 1, false},
            {"http://localhost:9000/api/v1/product-images/files/19.jpg", 1, false},
            
            // Producto 2 - U Knu Skool Blanco
            {"http://localhost:9000/api/v1/product-images/files/20.jpg", 2, true},
            {"http://localhost:9000/api/v1/product-images/files/21.jpg", 2, false},
            {"http://localhost:9000/api/v1/product-images/files/22.jpg", 2, false},
            {"http://localhost:9000/api/v1/product-images/files/23.jpg", 2, false},
            
            // Producto 3 - U Knu Skool Gris
            {"http://localhost:9000/api/v1/product-images/files/24.jpg", 3, true},
            {"http://localhost:9000/api/v1/product-images/files/25.jpg", 3, false},
            {"http://localhost:9000/api/v1/product-images/files/26.jpg", 3, false},
            {"http://localhost:9000/api/v1/product-images/files/27.jpg", 3, false},
            
            // Producto 4 - U CLASSIC SLIP-ON Negro
            {"http://localhost:9000/api/v1/product-images/files/28.jpg", 4, true},
            {"http://localhost:9000/api/v1/product-images/files/29.jpg", 4, false},
            {"http://localhost:9000/api/v1/product-images/files/30.jpg", 4, false},
            {"http://localhost:9000/api/v1/product-images/files/31.jpg", 4, false},
            
            // Producto 5 - U CLASSIC SLIP-ON Blanco
            {"http://localhost:9000/api/v1/product-images/files/32.jpg", 5, true},
            {"http://localhost:9000/api/v1/product-images/files/33.jpg", 5, false},
            {"http://localhost:9000/api/v1/product-images/files/34.jpg", 5, false},
            {"http://localhost:9000/api/v1/product-images/files/35.jpg", 5, false},
            
            // Producto 6 - U CLASSIC SLIP-ON Negro (variante)
            {"http://localhost:9000/api/v1/product-images/files/36.jpg", 6, true},
            {"http://localhost:9000/api/v1/product-images/files/37.jpg", 6, false},
            {"http://localhost:9000/api/v1/product-images/files/38.jpg", 6, false},
            {"http://localhost:9000/api/v1/product-images/files/39.jpg", 6, false},
            
            // Producto 7 - U SK8-HI Negro
            {"http://localhost:9000/api/v1/product-images/files/40.jpg", 7, true},
            {"http://localhost:9000/api/v1/product-images/files/41.jpg", 7, false},
            {"http://localhost:9000/api/v1/product-images/files/42.jpg", 7, false},
            {"http://localhost:9000/api/v1/product-images/files/43.jpg", 7, false},
            
            // Producto 8 - U SK8-HI Blanco
            {"http://localhost:9000/api/v1/product-images/files/44.jpg", 8, true},
            {"http://localhost:9000/api/v1/product-images/files/45.jpg", 8, false},
            {"http://localhost:9000/api/v1/product-images/files/46.jpg", 8, false},
            {"http://localhost:9000/api/v1/product-images/files/47.jpg", 8, false},
            
            // Producto 9 - U SK8-HI Azul
            {"http://localhost:9000/api/v1/product-images/files/48.jpg", 9, true},
            {"http://localhost:9000/api/v1/product-images/files/49.jpg", 9, false},
            {"http://localhost:9000/api/v1/product-images/files/50.jpg", 9, false},
            {"http://localhost:9000/api/v1/product-images/files/51.jpg", 9, false},
            
            // Producto 10 - U AUTHENTIC Negro
            {"http://localhost:9000/api/v1/product-images/files/52.jpg", 10, true},
            {"http://localhost:9000/api/v1/product-images/files/53.jpg", 10, false},
            {"http://localhost:9000/api/v1/product-images/files/54.jpg", 10, false},
            {"http://localhost:9000/api/v1/product-images/files/55.jpg", 10, false},
            
            // Producto 11 - U AUTHENTIC Rojo
            {"http://localhost:9000/api/v1/product-images/files/56.jpg", 11, true},
            {"http://localhost:9000/api/v1/product-images/files/57.jpg", 11, false},
            {"http://localhost:9000/api/v1/product-images/files/58.jpg", 11, false},
            {"http://localhost:9000/api/v1/product-images/files/59.jpg", 11, false},
            
            // Producto 12 - U AUTHENTIC Rosa
            {"http://localhost:9000/api/v1/product-images/files/60.jpg", 12, true},
            {"http://localhost:9000/api/v1/product-images/files/61.jpg", 12, false},
            {"http://localhost:9000/api/v1/product-images/files/62.jpg", 12, false},
            {"http://localhost:9000/api/v1/product-images/files/63.jpg", 12, false},
            
            // Producto 13 - U OLD SKOOL Morado
            {"http://localhost:9000/api/v1/product-images/files/64.jpg", 13, true},
            {"http://localhost:9000/api/v1/product-images/files/65.jpg", 13, false},
            {"http://localhost:9000/api/v1/product-images/files/66.jpg", 13, false},
            {"http://localhost:9000/api/v1/product-images/files/67.jpg", 13, false},
            
            // Producto 14 - U OLD SKOOL Verde
            {"http://localhost:9000/api/v1/product-images/files/68.jpg", 14, true},
            {"http://localhost:9000/api/v1/product-images/files/69.jpg", 14, false},
            {"http://localhost:9000/api/v1/product-images/files/70.jpg", 14, false},
            {"http://localhost:9000/api/v1/product-images/files/71.jpg", 14, false},
            
            // Producto 15 - U OLD SKOOL Amarillo
            {"http://localhost:9000/api/v1/product-images/files/72.jpg", 15, true},
            {"http://localhost:9000/api/v1/product-images/files/73.jpg", 15, false},
            {"http://localhost:9000/api/v1/product-images/files/74.jpg", 15, false},
            {"http://localhost:9000/api/v1/product-images/files/75.jpg", 15, false}
        };

        for (Object[] data : imageData) {
            ProductImage productImage = new ProductImage();
            productImage.setLink((String) data[0]);
            productImage.setProductId((Integer) data[1]);
            productImage.setPrincipalProductImage((Boolean) data[2]);
            productImage.setCreatedAt(LocalDateTime.now());
            productImage.setIsActive(true);
            productImageService.save(productImage);
        }
        
        System.out.println("OK " + imageData.length + " imágenes de productos cargadas (4 por cada uno de los " + (imageData.length/4) + " productos)");
    }

    private void loadSizes() throws Exception {
        System.out.println("Cargando talles...");
        
        // Verificar si ya existen talles usando try-catch
        boolean sizesExist = false;
        try {
            var existingSizes = sizeService.findAll();
            sizesExist = !existingSizes.isEmpty();
        } catch (Exception e) {
            // Si no hay talles, la excepción es esperada
            sizesExist = false;
        }
        
        if (!sizesExist) {
            System.out.println("Cargando talles...");
            
            // Talles europeos estándar para zapatillas
            String[] euSizes = {"35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46"};
            
            for (String sizeNumber : euSizes) {
                Size size = new Size();
                size.setNumber(sizeNumber);
                size.setSystemType(Size.SystemType.EU);
                size.setCreatedAt(LocalDateTime.now());
                size.setIsActive(true);
                sizeService.save(size);
            }
            
            // Talles estadounidenses estándar para zapatillas
            String[] usSizes = {"6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10", "10.5", "11", "11.5", "12", "12.5", "13"};
            
            for (String sizeNumber : usSizes) {
                Size size = new Size();
                size.setNumber(sizeNumber);
                size.setSystemType(Size.SystemType.US);
                size.setCreatedAt(LocalDateTime.now());
                size.setIsActive(true);
                sizeService.save(size);
            }
            
            System.out.println("OK " + euSizes.length + " talles europeos y " + usSizes.length + " talles estadounidenses.");
            System.out.println("Total de talles cargados: " + (euSizes.length + usSizes.length));
        } else {
            System.out.println("Los talles ya están cargados en la base de datos.");
        }
    }
    
    private void loadProductSizes() throws Exception {
        System.out.println("Asociando talles con productos y asignando stock...");
        
        // Verificar si ya existen asociaciones producto-talle usando try-catch
        boolean productSizesExist = false;
        try {
            var existingProductSizes = productSizeService.findAll();
            productSizesExist = !existingProductSizes.isEmpty();
        } catch (Exception e) {
            // Si no hay asociaciones, la excepc es esperada
            productSizesExist = false;
        }
        
        if (!productSizesExist) {
            System.out.println("Asociando talles con productos y generando stock...");
            
            // Obtener todos los productos y talles
            var products = productService.findAll();
            var sizes = sizeService.findAll();
            
            int associationsCount = 0;
            int totalStock = 0;
            
            // Asociar cada producto con todos los talles disponibles
            for (var product : products) {
                for (var size : sizes) {
                    ProductSize productSize = new ProductSize();
                    productSize.setIdProduct(product.getId());
                    productSize.setIdSize(size.getId());
                    
                    // Generar stock aleatorio entre 0 y 100
                    int stock = (int) (Math.random() * 101); // 0 a 100 
                    productSize.setStock(stock);
                    
                    productSizeService.save(productSize);
                    associationsCount++;
                    totalStock += stock;
                }
            }
            
            System.out.println("Se han creado " + associationsCount + " asociaciones producto-talle con stock.");
            System.out.println("(" + products.size() + " productos × " + sizes.size() + " talles = " + associationsCount + " asociaciones)");
            System.out.println("Stock total generado: " + totalStock + " unidades");
            System.out.println("Stock promedio por producto-talle: " + (totalStock / associationsCount) + " unidades");
        } else {
            System.out.println("Las asociaciones producto-talle ya están cargadas en la base de datos.");
        }
    }
    
    private void loadUsers() throws Exception {
        System.out.println("Cargando usuarios...");
        
        // Usuario Admin
        User admin = new User();
        admin.setName("Administrador");
        admin.setLastName("Sistema");
        admin.setUsername("admin");
        admin.setEmail("admin@zapatillas.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(User.UserRole.ADMIN);
        admin.setIsActive(true);
        admin.setCreatedAt(LocalDateTime.now());
        userService.save(admin);
        
        // Usuario Cliente
        User cliente = new User();
        cliente.setName("Juan Carlos");
        cliente.setLastName("Pérez González");
        cliente.setUsername("juanperez");
        cliente.setEmail("juan.perez@email.com");
        cliente.setPassword(passwordEncoder.encode("cliente123"));
        cliente.setRole(User.UserRole.CLIENT);
        cliente.setIsActive(true);
        cliente.setCreatedAt(LocalDateTime.now());
        userService.save(cliente);
        
        System.out.println("2 usuarios cargados (1 admin, 1 cliente)");
    }
    
    private void loadUserAddresses() throws Exception {
        System.out.println("Cargando direcciones de usuarios...");
        
        // Obtener los usuarios reales de la base de datos
        var users = userService.findAll();
        User cliente = null;
        
        // Buscar el usuario cliente
        for (User user : users) {
            if ("juanperez".equals(user.getUsername())) {
                cliente = user;
                break;
            }
        }
        
        if (cliente == null) {
            System.out.println("No se encontró el usuario cliente para asociar direcciones");
            return;
        }
        
        // Dirección 1 para el cliente (Juan Pérez)
        Adress direccion1 = new Adress();
        direccion1.setStreet("Av. San Martín 1234");
        direccion1.setTown("Mendoza");
        direccion1.setState("Mendoza");
        direccion1.setCpi("5500");
        direccion1.setCountry("Argentina");
        direccion1.setIsActive(true);
        direccion1.setCreatedAt(LocalDateTime.now());
        adressService.save(direccion1);
        
        // Dirección 2 para el cliente (Juan Pérez)
        Adress direccion2 = new Adress();
        direccion2.setStreet("Calle Las Heras 567, Depto 3B");
        direccion2.setTown("Godoy Cruz");
        direccion2.setState("Mendoza");
        direccion2.setCpi("5501");
        direccion2.setCountry("Argentina");
        direccion2.setIsActive(true);
        direccion2.setCreatedAt(LocalDateTime.now());
        adressService.save(direccion2);
        
        // Obtener las direcciones guardadas
        var addresses = adressService.findAll();
        Adress savedDireccion1 = null;
        Adress savedDireccion2 = null;
        
        for (Adress address : addresses) {
            if ("Av. San Martín 1234".equals(address.getStreet())) {
                savedDireccion1 = address;
            } else if ("Calle Las Heras 567, Depto 3B".equals(address.getStreet())) {
                savedDireccion2 = address;
            }
        }
        
        // Asociar las direcciones con el usuario cliente usando IDs reales
        if (savedDireccion1 != null) {
            UsersAdress userAddress1 = new UsersAdress();
            userAddress1.setUserId(cliente.getId());
            userAddress1.setAddressId(savedDireccion1.getId());
            usersAdressService.save(userAddress1);
        }
        
        if (savedDireccion2 != null) {
            UsersAdress userAddress2 = new UsersAdress();
            userAddress2.setUserId(cliente.getId());
            userAddress2.setAddressId(savedDireccion2.getId());
            usersAdressService.save(userAddress2);
        }
        
        System.out.println("2 direcciones cargadas y asociadas al cliente Juan Pérez (ID: " + cliente.getId() + ")");
    }

    private void loadPurchaseOrder() throws Exception {
        System.out.println("Cargando orden de compra...");
        
        // Obtener el cliente Juan Pérez
        var users = userService.findAll();
        User cliente = null;
        for (User user : users) {
            if ("juanperez".equals(user.getUsername())) {
                cliente = user;
                break;
            }
        }
        
        if (cliente == null) {
            System.out.println("No se encontró el usuario cliente para crear la orden");
            return;
        }
        
        // Obtener la primera dirección del cliente
        var userAddresses = usersAdressService.findAll();
        UsersAdress clienteAddress = null;
        for (UsersAdress userAddress : userAddresses) {
            if (userAddress.getUserId().equals(cliente.getId())) {
                clienteAddress = userAddress;
                break;
            }
        }
        
        if (clienteAddress == null) {
            System.out.println("No se encontró dirección para el cliente");
            return;
        }
        
        // Obtener los primeros 3 productos
        var products = productService.findAll();
        if (products.size() < 3) {
            System.out.println("No hay suficientes productos para crear la orden");
            return;
        }
        
        // Obtener talles disponibles
        var sizes = sizeService.findAll();
        if (sizes.isEmpty()) {
            System.out.println("No hay talles disponibles para crear la orden");
            return;
        }
        
        // Calcular el total de la orden
        // Producto 1: cantidad 2, Producto 2: cantidad 2, Producto 3: cantidad 1
        double total = (products.get(0).getPrice() * 2) + 
                      (products.get(1).getPrice() * 2) + 
                      (products.get(2).getPrice() * 1);
        
        // Crear la orden de compra
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setUserId(cliente.getId());
        purchaseOrder.setUserAddressId(clienteAddress.getAddressId());
        purchaseOrder.setTotal(total);
        purchaseOrder.setPaymentMethod("Tarjeta de Crédito");
        purchaseOrder.setStatus(PurchaseOrder.Status.PENDING);
        purchaseOrder.setIsActive(true);
        purchaseOrder.setCreatedAt(LocalDateTime.now());
        
        // Guardar la orden
        PurchaseOrder savedOrder = purchaseOrderService.save(purchaseOrder);
        
        // Crear los detalles de la orden con talles
        int[] quantities = {2, 2, 1}; // Cantidades para los primeros 3 productos
        Integer[] sizeIds = {sizes.get(0).getId(), sizes.get(1).getId(), sizes.get(2).getId()}; // Talles diferentes
        
        for (int i = 0; i < 3; i++) {
            Detail detail = new Detail();
            detail.setQuantity(quantities[i]);
            detail.setProductId(products.get(i).getId());
            detail.setOrderId(savedOrder.getId());
            detail.setSizeId(sizeIds[i]); // Asignar talle
            detail.setIsActive(true);
            detail.setCreatedAt(LocalDateTime.now());
            
            detailService.save(detail);
        }
        
        System.out.println("Orden de compra #1 creada (PENDING):");
        System.out.println("   - Cliente: " + cliente.getName() + " " + cliente.getLastName());
        System.out.println("   - Total: $" + total);
        System.out.println("   - Estado: PENDING");
        System.out.println("   - Productos:");
        for (int i = 0; i < 3; i++) {
            System.out.println("     * " + products.get(i).getName() + " (Cantidad: " + quantities[i] + ", Talle: " + sizes.get(i).getNumber() + " " + sizes.get(i).getSystemType() + ")");
        }
        System.out.println("   - Total de productos: " + (quantities[0] + quantities[1] + quantities[2]) + " unidades");
    }

    private void loadCancelledOrder() throws Exception {
        System.out.println("Cargando orden cancelada...");
        
        // Obtener el cliente Juan Pérez
        var users = userService.findAll();
        User cliente = null;
        for (User user : users) {
            if ("juanperez".equals(user.getUsername())) {
                cliente = user;
                break;
            }
        }
        
        if (cliente == null) {
            System.out.println("No se encontró el usuario cliente para crear la orden cancelada");
            return;
        }
        
        // Obtener la segunda dirección del cliente
        var userAddresses = usersAdressService.findAll();
        UsersAdress clienteAddress = null;
        int addressCount = 0;
        for (UsersAdress userAddress : userAddresses) {
            if (userAddress.getUserId().equals(cliente.getId())) {
                addressCount++;
                if (addressCount == 2) { // Usar la segunda dirección
                    clienteAddress = userAddress;
                    break;
                }
            }
        }
        
        // Si no hay segunda dirección, usar la primera
        if (clienteAddress == null) {
            for (UsersAdress userAddress : userAddresses) {
                if (userAddress.getUserId().equals(cliente.getId())) {
                    clienteAddress = userAddress;
                    break;
                }
            }
        }
        
        if (clienteAddress == null) {
            System.out.println("No se encontró dirección para el cliente");
            return;
        }
        
        // Obtener productos diferentes (productos 4, 5, 6)
        var products = productService.findAll();
        if (products.size() < 6) {
            System.out.println("No hay suficientes productos para crear la orden cancelada");
            return;
        }
        
        // Obtener talles disponibles
        var sizes = sizeService.findAll();
        if (sizes.isEmpty()) {
            System.out.println("No hay talles disponibles para crear la orden cancelada");
            return;
        }
        
        // Calcular el total de la orden cancelada
        // Producto 4: cantidad 1, Producto 5: cantidad 3, Producto 6: cantidad 1
        double total = (products.get(3).getPrice() * 1) + 
                      (products.get(4).getPrice() * 3) + 
                      (products.get(5).getPrice() * 1);
        
        // Crear la orden cancelada
        PurchaseOrder cancelledOrder = new PurchaseOrder();
        cancelledOrder.setUserId(cliente.getId());
        cancelledOrder.setUserAddressId(clienteAddress.getAddressId());
        cancelledOrder.setTotal(total);
        cancelledOrder.setPaymentMethod("Transferencia Bancaria");
        cancelledOrder.setStatus(PurchaseOrder.Status.CANCELLED);
        cancelledOrder.setIsActive(true);
        cancelledOrder.setCreatedAt(LocalDateTime.now().minusDays(5)); // 5 días atrás
        
        // Guardar la orden
        PurchaseOrder savedOrder = purchaseOrderService.save(cancelledOrder);
        
        // Crear los detalles de la orden cancelada con talles
        int[] quantities = {1, 3, 1}; // Cantidades para productos 4, 5, 6
        Integer[] sizeIds = {sizes.get(3).getId(), sizes.get(4).getId(), sizes.get(5).getId()}; // Talles diferentes
        
        for (int i = 0; i < 3; i++) {
            Detail detail = new Detail();
            detail.setQuantity(quantities[i]);
            detail.setProductId(products.get(i + 3).getId()); // Productos 4, 5, 6
            detail.setOrderId(savedOrder.getId());
            detail.setSizeId(sizeIds[i]); // Asignar talle
            detail.setIsActive(true);
            detail.setCreatedAt(LocalDateTime.now().minusDays(5));
            
            detailService.save(detail);
        }
        
        System.out.println("Orden de compra #2 creada (CANCELLED):");
        System.out.println("   - Cliente: " + cliente.getName() + " " + cliente.getLastName());
        System.out.println("   - Total: $" + total);
        System.out.println("   - Estado: CANCELLED");
        System.out.println("   - Fecha: " + LocalDateTime.now().minusDays(5).toLocalDate());
        System.out.println("   - Productos:");
        for (int i = 0; i < 3; i++) {
            System.out.println("     * " + products.get(i + 3).getName() + " (Cantidad: " + quantities[i] + ", Talle: " + sizes.get(i + 3).getNumber() + " " + sizes.get(i + 3).getSystemType() + ")");
        }
        System.out.println("   - Total de productos: " + (quantities[0] + quantities[1] + quantities[2]) + " unidades");
    }

    private void loadPaidOrder() throws Exception {
        System.out.println("Cargando orden pagada...");
        
        // Obtener el cliente Juan Pérez
        var users = userService.findAll();
        User cliente = null;
        for (User user : users) {
            if ("juanperez".equals(user.getUsername())) {
                cliente = user;
                break;
            }
        }
        
        if (cliente == null) {
            System.out.println("No se encontró el usuario cliente para crear la orden pagada");
            return;
        }
        
        // Obtener la primera dirección del cliente
        var userAddresses = usersAdressService.findAll();
        UsersAdress clienteAddress = null;
        for (UsersAdress userAddress : userAddresses) {
            if (userAddress.getUserId().equals(cliente.getId())) {
                clienteAddress = userAddress;
                break;
            }
        }
        
        if (clienteAddress == null) {
            System.out.println("No se encontró dirección para el cliente");
            return;
        }
        
        // Obtener productos diferentes (productos 7, 8, 9, 10)
        var products = productService.findAll();
        if (products.size() < 10) {
            System.out.println("No hay suficientes productos para crear la orden pagada");
            return;
        }
        
        // Obtener talles disponibles
        var sizes = sizeService.findAll();
        if (sizes.isEmpty()) {
            System.out.println("No hay talles disponibles para crear la orden pagada");
            return;
        }
        
        // Calcular el total de la orden pagada
        // Producto 7: cantidad 1, Producto 8: cantidad 1, Producto 9: cantidad 2, Producto 10: cantidad 1
        double total = (products.get(6).getPrice() * 1) + 
                      (products.get(7).getPrice() * 1) + 
                      (products.get(8).getPrice() * 2) + 
                      (products.get(9).getPrice() * 1);
        
        // Crear la orden pagada
        PurchaseOrder paidOrder = new PurchaseOrder();
        paidOrder.setUserId(cliente.getId());
        paidOrder.setUserAddressId(clienteAddress.getAddressId());
        paidOrder.setTotal(total);
        paidOrder.setPaymentMethod("PayPal");
        paidOrder.setStatus(PurchaseOrder.Status.PAID);
        paidOrder.setIsActive(true);
        paidOrder.setCreatedAt(LocalDateTime.now().minusDays(2)); // 2 días atrás
        
        // Guardar la orden
        PurchaseOrder savedOrder = purchaseOrderService.save(paidOrder);
        
        // Crear los detalles de la orden pagada con talles
        int[] quantities = {1, 1, 2, 1}; // Cantidades para productos 7, 8, 9, 10
        Integer[] sizeIds = {sizes.get(6).getId(), sizes.get(7).getId(), sizes.get(8).getId(), sizes.get(9).getId()}; // Talles diferentes
        
        for (int i = 0; i < 4; i++) {
            Detail detail = new Detail();
            detail.setQuantity(quantities[i]);
            detail.setProductId(products.get(i + 6).getId()); // Productos 7, 8, 9, 10
            detail.setOrderId(savedOrder.getId());
            detail.setSizeId(sizeIds[i]); // Asignar talle
            detail.setIsActive(true);
            detail.setCreatedAt(LocalDateTime.now().minusDays(2));
            
            detailService.save(detail);
        }
        
        System.out.println("Orden de compra #3 creada (PAID):");
        System.out.println("   - Cliente: " + cliente.getName() + " " + cliente.getLastName());
        System.out.println("   - Total: $" + total);
        System.out.println("   - Estado: PAID");
        System.out.println("   - Fecha: " + LocalDateTime.now().minusDays(2).toLocalDate());
        System.out.println("   - Productos:");
        for (int i = 0; i < 4; i++) {
            System.out.println("     * " + products.get(i + 6).getName() + " (Cantidad: " + quantities[i] + ", Talle: " + sizes.get(i + 6).getNumber() + " " + sizes.get(i + 6).getSystemType() + ")");
        }
        System.out.println("   - Total de productos: " + (quantities[0] + quantities[1] + quantities[2] + quantities[3]) + " unidades");
    }
} 