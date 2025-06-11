package com.example.apirest.config;

import com.example.apirest.entities.*;
import com.example.apirest.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("Verificando estado de la base de datos...");
        
        // Verificar si ya existen datos básicos
        boolean dataExists = checkIfDataExists();
        
        if (!dataExists) {
            System.out.println("Base de datos vacía - cargando datos iniciales...");
            loadInitialData();
            System.out.println("Base de datos inicializada correctamente!");
        } else {
            System.out.println("La base de datos ya contiene datos - omitiendo inicialización.");
            System.out.println("Para recargar datos, elimina las tablas manualmente.");
        }
    }
    
    private boolean checkIfDataExists() {
        try {
            // Verificar si existen productos (indicador de que ya se cargaron datos)
            var products = productService.findAll();
            return !products.isEmpty();
        } catch (Exception e) {
            // Si hay error al consultar, asumimos que no hay datos
            return false;
        }
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
        loadTestOrders(); // Agregar las 5 órdenes de prueba
    }

    private void loadColours() throws Exception {
        System.out.println("Cargando colores...");
        
        String[][] colourData = {
            {"Negro", "#000000"},
            {"Marron", "#8B4513"},
            {"Azul", "#0000FF"},
            {"Gris", "#808080"},
            {"Crema", "#F5F5F5"},
            {"Rosa", "#FFC0CB"},
            {"Blanco", "#FFFFFF"},
            {"Violeta", "#800080"}, // 8
            {"Morado", "#800080"}, // 9
            {"Naranja", "#FFA500"} // 10
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
        
        // Solo un tipo: Zapatillas
        String[] typeNames = {
            "Zapatillas"
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
        
        // Las categorías que querías: Classics, Classic +, Skateboarding, Surf
        String[][] categoryData = {
            {"Classics", "1"},          // ID: 1
            {"Classic +", "1"},         // ID: 2  
            {"Skateboarding", "1"},     // ID: 3
            {"Surf", "1"}               // ID: 4
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
        //{"Nombre", Precio, "Descripción", "URL_Imagen", CategoryID, ColourID, BrandID}
        // Productos con las categorías que definiste (1-4)
        Object[][] productData = {
            // U Knu Skool - Classics (ID: 1)
            //negro
            {"U Knu Skool", 189000.0, 
             "La Knu Skool es un modelo reeditado de los años 90, cuando las zapatillas de skate eran extra hinchadas. Confeccionadas con parte superior de ante y lienzo, este modelo de perfil bajo presenta una gran lengüeta hinchada y un cuello en el tobillo, lo que le da un aspecto exagerado que juega con la Old Skool original. Manteniendo la estética \"Off The Wall\", nuestro icónico Vans Sidestripe ™ se ha rediseñado como un molde 3D de diamante hinchado, que se suma a la apariencia y sensación general gruesa. La adición de tiradores en el talón ofrece un fácil acceso para entrar y salir. Contiene tiradores en el talón para facilitar la calzada y suela waffle de goma distintiva.",
             "http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_negro/1.jpg", 1, 1, 1},
             //marron
            {"U Knu Skool", 189000.0, 
             "La Knu Skool es un modelo reeditado de los años 90, cuando las zapatillas de skate eran extra hinchadas. Confeccionadas con parte superior de ante y lienzo, este modelo de perfil bajo presenta una gran lengüeta hinchada y un cuello en el tobillo, lo que le da un aspecto exagerado que juega con la Old Skool original. Manteniendo la estética \"Off The Wall\", nuestro icónico Vans Sidestripe ™ se ha rediseñado como un molde 3D de diamante hinchado, que se suma a la apariencia y sensación general gruesa. La adición de tiradores en el talón ofrece un fácil acceso para entrar y salir. Contiene tiradores en el talón para facilitar la calzada y suela waffle de goma distintiva.",
             "http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_marron/1.jpg", 1, 2, 1},
            //negro negro
            {"U Knu Skool", 189000.0, 
             "La Knu Skool es un modelo reeditado de los años 90, cuando las zapatillas de skate eran extra hinchadas. Confeccionadas con parte superior de ante y lienzo, este modelo de perfil bajo presenta una gran lengüeta hinchada y un cuello en el tobillo, lo que le da un aspecto exagerado que juega con la Old Skool original. Manteniendo la estética \"Off The Wall\", nuestro icónico Vans Sidestripe ™ se ha rediseñado como un molde 3D de diamante hinchado, que se suma a la apariencia y sensación general gruesa. La adición de tiradores en el talón ofrece un fácil acceso para entrar y salir. Contiene tiradores en el talón para facilitar la calzada y suela waffle de goma distintiva.",
             "http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_negro_negro/1.jpg", 1, 1, 1},
             //azul
             {"U Knu Skool", 189000.0, 
             "La Knu Skool es un modelo reeditado de los años 90, cuando las zapatillas de skate eran extra hinchadas. Confeccionadas con parte superior de ante y lienzo, este modelo de perfil bajo presenta una gran lengüeta hinchada y un cuello en el tobillo, lo que le da un aspecto exagerado que juega con la Old Skool original. Manteniendo la estética \"Off The Wall\", nuestro icónico Vans Sidestripe ™ se ha rediseñado como un molde 3D de diamante hinchado, que se suma a la apariencia y sensación general gruesa. La adición de tiradores en el talón ofrece un fácil acceso para entrar y salir. Contiene tiradores en el talón para facilitar la calzada y suela waffle de goma distintiva.",
             "http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_azul/1.jpg", 1, 3, 1},
             //gris
             {"U Knu Skool", 189000.0, 
             "La Knu Skool es un modelo reeditado de los años 90, cuando las zapatillas de skate eran extra hinchadas. Confeccionadas con parte superior de ante y lienzo, este modelo de perfil bajo presenta una gran lengüeta hinchada y un cuello en el tobillo, lo que le da un aspecto exagerado que juega con la Old Skool original. Manteniendo la estética \"Off The Wall\", nuestro icónico Vans Sidestripe ™ se ha rediseñado como un molde 3D de diamante hinchado, que se suma a la apariencia y sensación general gruesa. La adición de tiradores en el talón ofrece un fácil acceso para entrar y salir. Contiene tiradores en el talón para facilitar la calzada y suela waffle de goma distintiva.",
             "http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_gris/1.jpg", 1, 4, 1},
             //u knu stack
             {"U Knu Stack", 189000.0, 
             "Un estilo hinchado de los 90 con suela de plataforma gruesa Las Knu Stack comienzan con nuestra interpretación moderna de una silueta clásica de los 90 y luego la lleva a un nivel superior. Con el doble de altura, una lengüeta hinchada y una banda lateral moldeada en 3D, este calzado directo cuenta con detalles de estilo dramáticos que combinan un ícono del pasado con las tendencias audaces de hoy. Zapato bajo reeditado de los 90. Altura de la plataforma de 34 mm. Parte superior de gamuza resistente.Lengüeta hinchada y cuello en el tobillo. Sidestripe hinchado moldeado en 3D. Cordones gruesos de gran tamaño. Suela exterior waffle de goma distintiva.",
             "http://localhost:9000/api/v1/product-images/files/classics/u_knu_stack_negro/1.jpg", 1, 1, 1},

            // Classic + (ID: 2)
            //hylane negro
            {"Vans Zapatillas U Hylane", 189000.0,
             "Hylane es una zapatilla inspirada en el skate Y2K que reinterpreta nuestra silueta Upland de 1999. Los guiños al pasado coexisten con detalles de diseño elevados como la Sidestripe brillante en 3D y los logotipos bordados, mientras que la postura robusta y la lengüeta de skate abultada brindan un aspecto y una sensación retro. Terminada con una construcción de suela tipo cupsole y una textura única de V entrelazada, Hylane transforma una estética de la vieja escuela en un elemento esencial del guardarropa actual. Parte superior de cuero y sintético. Sidestripe brillante y abullonada.Lengüeta de patín hinchada Y2K.Construcción robusta de suela tipo copa.Diseño de pared lateral gruesa con canal de compresión.Suela exterior tipo waffle invertida con logotipo de Vans de gran tamaño y ranuras flexibles.",
             "http://localhost:9000/api/v1/product-images/files/classics_plus/u_hylane/1.jpg", 2, 1, 1},
            //u upland
            {"Vans Zapatillas U Upland", 189000.0,
             "Conoce las Upland, una zapatilla del pasado rediseñada para el futuro. Hemos tomado el clásico low-top de los 90 y le hemos añadido algunos toques modernos. La forma rediseñada del pie brinda ajuste y comodidad mejorados, mientras que los materiales elevados combinan durabilidad y estilo. Con cordones de gran tamaño y logotipos atrevidos, el Upland une a la perfección nuestra herencia con todo lo que nos depara la actualidad. Zapatillas bajas gruesas estilo años 90 Inspirada en los archivos, se basa en el legado de la silueta Upland original. La suela cosida envuelve todo el zapato para una durabilidad increíble. La parte superior combina cuero y polietileno para brindar estilo y resistencia. Nueva y mejorada forma del pie para mayor comodidad. El cuello hinchado y la lengüeta acolchada brindan ese soporte adicional El logotipo Heritage \"Flying V\" hace un guiño al legado de Upland Los cordones anchos añaden un toque clásico.",
             "http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland/1.jpg", 2, 1, 1},
            //u upland crema
            {"Vans Zapatillas U Upland", 189000.0,
             "Conoce las Upland, una zapatilla del pasado rediseñada para el futuro. Hemos tomado el clásico low-top de los 90 y le hemos añadido algunos toques modernos. La forma rediseñada del pie brinda ajuste y comodidad mejorados, mientras que los materiales elevados combinan durabilidad y estilo. Con cordones de gran tamaño y logotipos atrevidos, el Upland une a la perfección nuestra herencia con todo lo que nos depara la actualidad. Zapatillas bajas gruesas estilo años 90 Inspirada en los archivos, se basa en el legado de la silueta Upland original. La suela cosida envuelve todo el zapato para una durabilidad increíble. La parte superior combina cuero y polietileno para brindar estilo y resistencia. Nueva y mejorada forma del pie para mayor comodidad. El cuello hinchado y la lengüeta acolchada brindan ese soporte adicional El logotipo Heritage \"Flying V\" hace un guiño al legado de Upland Los cordones anchos añaden un toque clásico.",
             "http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_crema/1.jpg", 2, 5, 1},
             //u upland negro negro 
             {"Vans Zapatillas U Upland", 189000.0,
             "Conoce las Upland, una zapatilla del pasado rediseñada para el futuro. Hemos tomado el clásico low-top de los 90 y le hemos añadido algunos toques modernos. La forma rediseñada del pie brinda ajuste y comodidad mejorados, mientras que los materiales elevados combinan durabilidad y estilo. Con cordones de gran tamaño y logotipos atrevidos, el Upland une a la perfección nuestra herencia con todo lo que nos depara la actualidad. Zapatillas bajas gruesas estilo años 90 Inspirada en los archivos, se basa en el legado de la silueta Upland original. La suela cosida envuelve todo el zapato para una durabilidad increíble. La parte superior combina cuero y polietileno para brindar estilo y resistencia. Nueva y mejorada forma del pie para mayor comodidad. El cuello hinchado y la lengüeta acolchada brindan ese soporte adicional El logotipo Heritage \"Flying V\" hace un guiño al legado de Upland Los cordones anchos añaden un toque clásico.",
             "http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_negro_negro/1.jpg", 2, 1, 1},
             //u upland rosa
             {"Vans Zapatillas U Upland", 189000.0,
             "Conoce las Upland, una zapatilla del pasado rediseñada para el futuro. Hemos tomado el clásico low-top de los 90 y le hemos añadido algunos toques modernos. La forma rediseñada del pie brinda ajuste y comodidad mejorados, mientras que los materiales elevados combinan durabilidad y estilo. Con cordones de gran tamaño y logotipos atrevidos, el Upland une a la perfección nuestra herencia con todo lo que nos depara la actualidad. Zapatillas bajas gruesas estilo años 90 Inspirada en los archivos, se basa en el legado de la silueta Upland original. La suela cosida envuelve todo el zapato para una durabilidad increíble. La parte superior combina cuero y polietileno para brindar estilo y resistencia. Nueva y mejorada forma del pie para mayor comodidad. El cuello hinchado y la lengüeta acolchada brindan ese soporte adicional El logotipo Heritage \"Flying V\" hace un guiño al legado de Upland Los cordones anchos añaden un toque clásico.",
             "http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_rosa/1.jpg", 2, 6, 1},
             //u upland blanco
             {"Vans Zapatillas U Upland", 189000.0,
             "Conoce las Upland, una zapatilla del pasado rediseñada para el futuro. Hemos tomado el clásico low-top de los 90 y le hemos añadido algunos toques modernos. La forma rediseñada del pie brinda ajuste y comodidad mejorados, mientras que los materiales elevados combinan durabilidad y estilo. Con cordones de gran tamaño y logotipos atrevidos, el Upland une a la perfección nuestra herencia con todo lo que nos depara la actualidad. Zapatillas bajas gruesas estilo años 90 Inspirada en los archivos, se basa en el legado de la silueta Upland original. La suela cosida envuelve todo el zapato para una durabilidad increíble. La parte superior combina cuero y polietileno para brindar estilo y resistencia. Nueva y mejorada forma del pie para mayor comodidad. El cuello hinchado y la lengüeta acolchada brindan ese soporte adicional El logotipo Heritage \"Flying V\" hace un guiño al legado de Upland Los cordones anchos añaden un toque clásico.",
             "http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_blanco/1.jpg", 2, 7, 1},
            
            
            //SKATE (ID: 3)

            // m current caples marron
            {"Vans Zapatillas M Skate Curren Caples", 146000.0,
             "Las nuevas zapatillas de Vans y Curren Caples son una apuesta conjunta que busca la perfección de la tecnología vulcanizada. Para ello, se toma la mítica silueta y perfil estilizado de la firma para crear algo completamente nuevo, capaz de albergar una de las suelas con más capacidades de performance de la firma. Y, como debut, se presentan en cuatro colorways que tocan tanto a las raíces californianas de Caples como el legado histórico de la firma. En cuanto a su construcción, las nuevas Vans Curren presentan un cuerpo superior tonal en variantes de cuero o ante y lona acentuado por costuras minimalistas y una Side Stripe mucho más estilizada. Aquí, se incluyen los detalles típicos de cualquier zapatilla Vans, como un tag en el medial, ojales metálicos y cordones simples. Solo que, obviamente, el empeine viene coronado por un parche colaborativo. Pero la verdadera magia de las nuevas Vans Curren se encuentra en la parte inferior. El lower de este nuevo modelo presenta una construcción de goma vulcanizada completamente custom cargada de las mejores tecnologías de la firma. La goma, por ejemplo, está hecha del compuesto SickStick, lo que proporciona capacidades ultra avanzadas. También, nos encontramos con refuerzos DuraCap en lugares estratégicos para garantizar una mayor durabilidad y plantillas PopCush, que proporcionan protección optimizada ante impactos. • Parte superior de cuero. • Parche de lengüeta personalizado. • Plantillas Pop Cush. • Refuerzo de talón. • Lengüeta con cierre. • Construcción Duracap transpirable. • Revestimiento de dos piezas. • Pestaña tejida Vans. • Suela con fórmula SickStick de Vans.",
             "http://localhost:9000/api/v1/product-images/files/skate/m_current_caples_marron/1.jpg", 3, 2, 1},
             //m curren blanco
             {"Vans Zapatillas M Skate Curren Caples", 146000.0,
             "Las nuevas zapatillas de Vans y Curren Caples son una apuesta conjunta que busca la perfección de la tecnología vulcanizada. Para ello, se toma la mítica silueta y perfil estilizado de la firma para crear algo completamente nuevo, capaz de albergar una de las suelas con más capacidades de performance de la firma. Y, como debut, se presentan en cuatro colorways que tocan tanto a las raíces californianas de Caples como el legado histórico de la firma. En cuanto a su construcción, las nuevas Vans Curren presentan un cuerpo superior tonal en variantes de cuero o ante y lona acentuado por costuras minimalistas y una Side Stripe mucho más estilizada. Aquí, se incluyen los detalles típicos de cualquier zapatilla Vans, como un tag en el medial, ojales metálicos y cordones simples. Solo que, obviamente, el empeine viene coronado por un parche colaborativo. Pero la verdadera magia de las nuevas Vans Curren se encuentra en la parte inferior. El lower de este nuevo modelo presenta una construcción de goma vulcanizada completamente custom cargada de las mejores tecnologías de la firma. La goma, por ejemplo, está hecha del compuesto SickStick, lo que proporciona capacidades ultra avanzadas. También, nos encontramos con refuerzos DuraCap en lugares estratégicos para garantizar una mayor durabilidad y plantillas PopCush, que proporcionan protección optimizada ante impactos. • Parte superior de cuero. • Parche de lengüeta personalizado. • Plantillas Pop Cush. • Refuerzo de talón. • Lengüeta con cierre. • Construcción Duracap transpirable. • Revestimiento de dos piezas. • Pestaña tejida Vans. • Suela con fórmula SickStick de Vans.",
             "http://localhost:9000/api/v1/product-images/files/skate/m_current_caples_blanco/1.jpg", 3, 7, 1},
            //m2 waybee violeta
            {"Vans Zapatillas M Skate 2 Wayvee", 146000.0,
             "El Wayvee 2.0 está diseñado para skaters que exigen tanto rendimiento como estilo. Este modelo actualizado, que evoluciona a partir de su diseño original, refina elementos clave para ofrecer una experiencia de patinaje de nivel superior. Con una malla transpirable, un panel de guarda barros duradero y la construcción Wafflecup característica de Vans, el Wayvee 2.0 combina tecnología de vanguardia con una estética atemporal. • Parte superior de malla transpirable para una comodidad ligera y ventilada. • Diseñado para andar en skate con características mejoradas de durabilidad y rendimiento. • Protección de cordones integrada para una funcionalidad específica para skate. • Panel guarda barro duradero para mayor protección en áreas de alto desgaste. • Las plantillas PopCush™ ofrecen protección contra impactos y reducen la fatiga de las piernas para patinar durante períodos prolongados. • Las capas inferiores DURACAP™ añaden refuerzo a las áreas de mayor desgaste. • La goma SickStick™, la más pegajosa hasta el momento, te mantiene pegado a tu tabla. • Wafflecup™ combina el soporte y la durabilidad de la suela tipo copa con la sensación y flexibilidad de una tabla vulcanizada. • Insignia icónica Sidestripe™.",
             "http://localhost:9000/api/v1/product-images/files/skate/m2_violeta/1.jpg",3, 8, 1},
             //m2 waybee blanco
            {"Vans Zapatillas M Skate 2 Wayvee", 146000.0,
            "El Wayvee 2.0 está diseñado para skaters que exigen tanto rendimiento como estilo. Este modelo actualizado, que evoluciona a partir de su diseño original, refina elementos clave para ofrecer una experiencia de patinaje de nivel superior. Con una malla transpirable, un panel de guarda barros duradero y la construcción Wafflecup característica de Vans, el Wayvee 2.0 combina tecnología de vanguardia con una estética atemporal. • Parte superior de malla transpirable para una comodidad ligera y ventilada. • Diseñado para andar en skate con características mejoradas de durabilidad y rendimiento. • Protección de cordones integrada para una funcionalidad específica para skate. • Panel guarda barro duradero para mayor protección en áreas de alto desgaste. • Las plantillas PopCush™ ofrecen protección contra impactos y reducen la fatiga de las piernas para patinar durante períodos prolongados. • Las capas inferiores DURACAP™ añaden refuerzo a las áreas de mayor desgaste. • La goma SickStick™, la más pegajosa hasta el momento, te mantiene pegado a tu tabla. • Wafflecup™ combina el soporte y la durabilidad de la suela tipo copa con la sensación y flexibilidad de una tabla vulcanizada. • Insignia icónica Sidestripe™.",
            "http://localhost:9000/api/v1/product-images/files/skate/m2_blanco/1.jpg",3, 7, 1},

            //SURF

            //u_u_exo_negro_negro
            {"Vans Zapatillas U ULTRARANGE EXO", 146000.0,
            "La Ultrarange EXO de Vans ofrece un diseño innovador y funcional pensado para la aventura diaria. Confeccionada en mesh y material sintético, este modelo combina resistencia, transpirabilidad y estilo urbano. Su estructura incorpora la tecnología UltraCush™ en la entresuela para una amortiguación liviana, mientras que la suela de goma antideslizante con patrón tipo waffle invertido mejora el agarre en distintas superficies. El diseño verde con detalles en crudo le da un toque moderno y versátil, ideal para cualquier ocasión. Descubrí el confort y la durabilidad que necesitas. IMPORTANTE: Este modelo cuenta con una horma angosta. Recomendamos comprar un punto más. hoy con envío a domicilio o retiralo por nuestras sucursales. Pagá en cuotas y aprovechá las promociones vigentes.",
            "http://localhost:9000/api/v1/product-images/files/surf/u_u_exo_negro_negro/1.jpg",4, 1, 1},
            //u_u_exo_blanco
            {"Vans Zapatillas U ULTRARANGE EXO", 146000.0,
            "La Ultrarange EXO de Vans ofrece un diseño innovador y funcional pensado para la aventura diaria. Confeccionada en mesh y material sintético, este modelo combina resistencia, transpirabilidad y estilo urbano. Su estructura incorpora la tecnología UltraCush™ en la entresuela para una amortiguación liviana, mientras que la suela de goma antideslizante con patrón tipo waffle invertido mejora el agarre en distintas superficies. El diseño verde con detalles en crudo le da un toque moderno y versátil, ideal para cualquier ocasión. Descubrí el confort y la durabilidad que necesitas. IMPORTANTE: Este modelo cuenta con una horma angosta. Recomendamos comprar un punto más. hoy con envío a domicilio o retiralo por nuestras sucursales. Pagá en cuotas y aprovechá las promociones vigentes.",
            "http://localhost:9000/api/v1/product-images/files/surf/u_u_exo_blanco/1.jpg",4, 7, 1},
            //u_u_exo_blanco_negro
            {"Vans Zapatillas U ULTRARANGE EXO", 146000.0,
            "La Ultrarange EXO de Vans ofrece un diseño innovador y funcional pensado para la aventura diaria. Confeccionada en mesh y material sintético, este modelo combina resistencia, transpirabilidad y estilo urbano. Su estructura incorpora la tecnología UltraCush™ en la entresuela para una amortiguación liviana, mientras que la suela de goma antideslizante con patrón tipo waffle invertido mejora el agarre en distintas superficies. El diseño verde con detalles en crudo le da un toque moderno y versátil, ideal para cualquier ocasión. Descubrí el confort y la durabilidad que necesitas. IMPORTANTE: Este modelo cuenta con una horma angosta. Recomendamos comprar un punto más. hoy con envío a domicilio o retiralo por nuestras sucursales. Pagá en cuotas y aprovechá las promociones vigentes.",
            "http://localhost:9000/api/v1/product-images/files/surf/u_u_exo_blanco_negro/1.jpg",4, 7, 1},
            //Vans Zapatillas U Crosspath negro negro
            {"Vans Zapatillas U Crosspath", 239000.0,
            "Donde quiera que lo lleve su estilo de vida activo, MTE Crosspath lo tiene cubierto para explorar durante todo el día. Ampliando la inspiración detrás de la familia UltraRange de Vans, ofrece la combinación perfecta de estilo y funcionalidad para aventuras urbanas y al aire libre. Este zapato bajo resistente al agua combina una construcción liviana y consciente de la sostenibilidad con máxima amortiguación y tracción mejorada para cualquier terreno, desde el sendero hasta la calle. Este producto, que cuenta con el logotipo Vans Checkerboard Globe, está compuesto al menos en un 30 % de uno o una combinación de materiales reciclados y renovables.CARACTERÍSTICAS DEL PRODUCTO: IMPERMEABLE: El tratamiento repelente al agua duradero lo ayuda a enfrentar los elementos, brindando resistencia al agua de primer nivel.DURABILIDAD: La malla de PET reciclada, liviana y resistente, y la parte superior textil con paneles reforzados contra la abrasión ofrecen mayor protección y agilidad. TRACCIÓN: La suela exterior rediseñada presenta una banda de rodadura diseñada específicamente para terrenos mixtos, irregulares, sueltos o mojados.COMODIDAD Y ESTABILIDAD: La tecnología UltraCush del MTE Crosspath proporciona la máxima amortiguación.",
            "http://localhost:9000/api/v1/product-images/files/surf/u_cross_negro_negro/1.jpg",4, 1, 1},
            //Vans Zapatillas U Crosspath naranja
            {"Vans Zapatillas U Crosspath", 239000.0,
            "Donde quiera que lo lleve su estilo de vida activo, MTE Crosspath lo tiene cubierto para explorar durante todo el día. Ampliando la inspiración detrás de la familia UltraRange de Vans, ofrece la combinación perfecta de estilo y funcionalidad para aventuras urbanas y al aire libre. Este zapato bajo resistente al agua combina una construcción liviana y consciente de la sostenibilidad con máxima amortiguación y tracción mejorada para cualquier terreno, desde el sendero hasta la calle. Este producto, que cuenta con el logotipo Vans Checkerboard Globe, está compuesto al menos en un 30 % de uno o una combinación de materiales reciclados y renovables.CARACTERÍSTICAS DEL PRODUCTO: IMPERMEABLE: El tratamiento repelente al agua duradero lo ayuda a enfrentar los elementos, brindando resistencia al agua de primer nivel.DURABILIDAD: La malla de PET reciclada, liviana y resistente, y la parte superior textil con paneles reforzados contra la abrasión ofrecen mayor protección y agilidad. TRACCIÓN: La suela exterior rediseñada presenta una banda de rodadura diseñada específicamente para terrenos mixtos, irregulares, sueltos o mojados.COMODIDAD Y ESTABILIDAD: La tecnología UltraCush del MTE Crosspath proporciona la máxima amortiguación.",
            "http://localhost:9000/api/v1/product-images/files/surf/u_cross_naranja/1.jpg",4, 10, 1},
            //Vans Zapatillas U Crosspath negro
            {"Vans Zapatillas U Crosspath", 239000.0,
            "Donde quiera que lo lleve su estilo de vida activo, MTE Crosspath lo tiene cubierto para explorar durante todo el día. Ampliando la inspiración detrás de la familia UltraRange de Vans, ofrece la combinación perfecta de estilo y funcionalidad para aventuras urbanas y al aire libre. Este zapato bajo resistente al agua combina una construcción liviana y consciente de la sostenibilidad con máxima amortiguación y tracción mejorada para cualquier terreno, desde el sendero hasta la calle. Este producto, que cuenta con el logotipo Vans Checkerboard Globe, está compuesto al menos en un 30 % de uno o una combinación de materiales reciclados y renovables.CARACTERÍSTICAS DEL PRODUCTO: IMPERMEABLE: El tratamiento repelente al agua duradero lo ayuda a enfrentar los elementos, brindando resistencia al agua de primer nivel.DURABILIDAD: La malla de PET reciclada, liviana y resistente, y la parte superior textil con paneles reforzados contra la abrasión ofrecen mayor protección y agilidad. TRACCIÓN: La suela exterior rediseñada presenta una banda de rodadura diseñada específicamente para terrenos mixtos, irregulares, sueltos o mojados.COMODIDAD Y ESTABILIDAD: La tecnología UltraCush del MTE Crosspath proporciona la máxima amortiguación.",
            "http://localhost:9000/api/v1/product-images/files/surf/u_cross_negro/1.jpg",4, 1, 1},
            //////////////////// PRUEBAS /////////////////////
            //m2 waybee blanco
            {"Vans Zapatillas M Skate 2 Wayvee", 100.0,
            "El Wayvee 2.0 está diseñado para skaters que exigen tanto rendimiento como estilo. Este modelo actualizado, que evoluciona a partir de su diseño original, refina elementos clave para ofrecer una experiencia de patinaje de nivel superior. Con una malla transpirable, un panel de guarda barros duradero y la construcción Wafflecup característica de Vans, el Wayvee 2.0 combina tecnología de vanguardia con una estética atemporal. • Parte superior de malla transpirable para una comodidad ligera y ventilada. • Diseñado para andar en skate con características mejoradas de durabilidad y rendimiento. • Protección de cordones integrada para una funcionalidad específica para skate. • Panel guarda barro duradero para mayor protección en áreas de alto desgaste. • Las plantillas PopCush™ ofrecen protección contra impactos y reducen la fatiga de las piernas para patinar durante períodos prolongados. • Las capas inferiores DURACAP™ añaden refuerzo a las áreas de mayor desgaste. • La goma SickStick™, la más pegajosa hasta el momento, te mantiene pegado a tu tabla. • Wafflecup™ combina el soporte y la durabilidad de la suela tipo copa con la sensación y flexibilidad de una tabla vulcanizada. • Insignia icónica Sidestripe™.",
            "http://localhost:9000/api/v1/product-images/files/skate/m2_blanco/1.jpg",3, 7, 1},
            //u upland blanco
            {"Vans Zapatillas U Upland", 100.0,
            "Conoce las Upland, una zapatilla del pasado rediseñada para el futuro. Hemos tomado el clásico low-top de los 90 y le hemos añadido algunos toques modernos. La forma rediseñada del pie brinda ajuste y comodidad mejorados, mientras que los materiales elevados combinan durabilidad y estilo. Con cordones de gran tamaño y logotipos atrevidos, el Upland une a la perfección nuestra herencia con todo lo que nos depara la actualidad. Zapatillas bajas gruesas estilo años 90 Inspirada en los archivos, se basa en el legado de la silueta Upland original. La suela cosida envuelve todo el zapato para una durabilidad increíble. La parte superior combina cuero y polietileno para brindar estilo y resistencia. Nueva y mejorada forma del pie para mayor comodidad. El cuello hinchado y la lengüeta acolchada brindan ese soporte adicional El logotipo Heritage \"Flying V\" hace un guiño al legado de Upland Los cordones anchos añaden un toque clásico.",
            "http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_blanco/1.jpg", 2, 7, 1},
            //u knu stack
            {"U Knu Stack", 100.0, 
            "Un estilo hinchado de los 90 con suela de plataforma gruesa Las Knu Stack comienzan con nuestra interpretación moderna de una silueta clásica de los 90 y luego la lleva a un nivel superior. Con el doble de altura, una lengüeta hinchada y una banda lateral moldeada en 3D, este calzado directo cuenta con detalles de estilo dramáticos que combinan un ícono del pasado con las tendencias audaces de hoy. Zapato bajo reeditado de los 90. Altura de la plataforma de 34 mm. Parte superior de gamuza resistente.Lengüeta hinchada y cuello en el tobillo. Sidestripe hinchado moldeado en 3D. Cordones gruesos de gran tamaño. Suela exterior waffle de goma distintiva.",
            "http://localhost:9000/api/v1/product-images/files/classics/u_knu_stack_negro/1.jpg", 1, 1, 1},
            //Vans Zapatillas U Crosspath negro
            {"Vans Zapatillas U Crosspath", 100.0,
            "Donde quiera que lo lleve su estilo de vida activo, MTE Crosspath lo tiene cubierto para explorar durante todo el día. Ampliando la inspiración detrás de la familia UltraRange de Vans, ofrece la combinación perfecta de estilo y funcionalidad para aventuras urbanas y al aire libre. Este zapato bajo resistente al agua combina una construcción liviana y consciente de la sostenibilidad con máxima amortiguación y tracción mejorada para cualquier terreno, desde el sendero hasta la calle. Este producto, que cuenta con el logotipo Vans Checkerboard Globe, está compuesto al menos en un 30 % de uno o una combinación de materiales reciclados y renovables.CARACTERÍSTICAS DEL PRODUCTO: IMPERMEABLE: El tratamiento repelente al agua duradero lo ayuda a enfrentar los elementos, brindando resistencia al agua de primer nivel.DURABILIDAD: La malla de PET reciclada, liviana y resistente, y la parte superior textil con paneles reforzados contra la abrasión ofrecen mayor protección y agilidad. TRACCIÓN: La suela exterior rediseñada presenta una banda de rodadura diseñada específicamente para terrenos mixtos, irregulares, sueltos o mojados.COMODIDAD Y ESTABILIDAD: La tecnología UltraCush del MTE Crosspath proporciona la máxima amortiguación.",
            "http://localhost:9000/api/v1/product-images/files/surf/u_cross_negro/1.jpg",4, 1, 1},

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
        
        // Imágenes para TODOS los productos con URLs corregidas
        Object[][] imageData = {
            // Producto 1 - U Knu Skool Negro
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_negro/1.jpg", 1, true},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_negro/2.jpg", 1, false},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_negro/3.jpg", 1, false},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_negro/4.jpg", 1, false},
            
            // Producto 2 - U Knu Skool marron
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_marron/1.jpg", 2, true},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_marron/2.jpg", 2, false},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_marron/3.jpg", 2, false},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_marron/4.jpg", 2, false},
            
            // Producto 3 - U Knu negro negro
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_negro_negro/1.jpg", 3, true},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_negro_negro/2.jpg", 3, false},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_negro_negro/3.jpg", 3, false},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_negro_negro/4.jpg", 3, false},
            
            // Producto 4 - U Knu azul
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_azul/1.jpg", 4, true},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_azul/2.jpg", 4, false},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_azul/3.jpg", 4, false},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_azul/4.jpg", 4, false},
            
            // Producto 5 - U Knu gris
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_gris/1.jpg", 5, true},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_gris/2.jpg", 5, false},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_gris/3.jpg", 5, false},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_skool_gris/4.jpg", 5, false},
            
            // Producto 6 - U Knu stack
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_stack_negro/1.jpg", 6, true},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_stack_negro/2.jpg", 6, false},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_stack_negro/3.jpg", 6, false},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_stack_negro/4.jpg", 6, false},
            
            // Producto 7 - U Hylane
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_hylane/1.jpg", 7, true},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_hylane/2.jpg", 7, false},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_hylane/3.jpg", 7, false},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_hylane/4.jpg", 7, false},
            
            // Producto 8 - U Upland
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland/1.jpg", 8, true},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland/2.jpg", 8, false},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland/3.jpg", 8, false},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland/4.jpg", 8, false},
            
            // Producto 9 - U Upland crema
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_crema/1.jpg", 9, true},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_crema/2.jpg", 9, false},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_crema/3.jpg", 9, false},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_crema/4.jpg", 9, false},
            
            // Producto 10 - U Upland negro negro
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_negro_negro/1.jpg", 10, true},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_negro_negro/2.jpg", 10, false},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_negro_negro/3.jpg", 10, false},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_negro_negro/4.jpg", 10, false},
            
            // Producto 11 - U Upland rosa
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_rosa/1.jpg", 11, true},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_rosa/2.jpg", 11, false},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_rosa/3.jpg", 11, false},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_rosa/4.jpg", 11, false},
            
            // Producto 12 - U Upland blanco
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_blanco/1.jpg", 12, true},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_blanco/2.jpg", 12, false},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_blanco/3.jpg", 12, false},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_blanco/4.jpg", 12, false},
            
            // Producto 13 - M Skate Curren Caples marron
            {"http://localhost:9000/api/v1/product-images/files/skate/m_current_caples_marron/1.jpg", 13, true},
            {"http://localhost:9000/api/v1/product-images/files/skate/m_current_caples_marron/2.jpg", 13, false},
            {"http://localhost:9000/api/v1/product-images/files/skate/m_current_caples_marron/3.jpg", 13, false},
            {"http://localhost:9000/api/v1/product-images/files/skate/m_current_caples_marron/4.jpg", 13, false},
            
            // Producto 14 - M Skate Curren Caples blanco
            {"http://localhost:9000/api/v1/product-images/files/skate/m_current_caples_blanco/1.jpg", 14, true},
            {"http://localhost:9000/api/v1/product-images/files/skate/m_current_caples_blanco/2.jpg", 14, false},
            {"http://localhost:9000/api/v1/product-images/files/skate/m_current_caples_blanco/3.jpg", 14, false},
            {"http://localhost:9000/api/v1/product-images/files/skate/m_current_caples_blanco/4.jpg", 14, false},
            
            // Producto 15 - M Skate 2 Wayvee violeta
            {"http://localhost:9000/api/v1/product-images/files/skate/m2_violeta/1.jpg", 15, true},
            {"http://localhost:9000/api/v1/product-images/files/skate/m2_violeta/2.jpg", 15, false},
            {"http://localhost:9000/api/v1/product-images/files/skate/m2_violeta/3.jpg", 15, false},
            {"http://localhost:9000/api/v1/product-images/files/skate/m2_violeta/4.jpg", 15, false},
            
            // Producto 16 - M Skate 2 Wayvee blanco
            {"http://localhost:9000/api/v1/product-images/files/skate/m2_blanco/1.jpg", 16, true},
            {"http://localhost:9000/api/v1/product-images/files/skate/m2_blanco/2.jpg", 16, false},
            {"http://localhost:9000/api/v1/product-images/files/skate/m2_blanco/3.jpg", 16, false},
            {"http://localhost:9000/api/v1/product-images/files/skate/m2_blanco/4.jpg", 16, false},

            //SURF

            //u_u_exo_negro_negro
            {"http://localhost:9000/api/v1/product-images/files/surf/u_u_exo_negro_negro/1.jpg", 17, true},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_u_exo_negro_negro/2.jpg", 17, false},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_u_exo_negro_negro/3.jpg", 17, false},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_u_exo_negro_negro/4.jpg", 17, false},

            //u_u_exo_blanco
            {"http://localhost:9000/api/v1/product-images/files/surf/u_u_exo_blanco/1.jpg", 18, true},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_u_exo_blanco/2.jpg", 18, false},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_u_exo_blanco/3.jpg", 18, false},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_u_exo_blanco/4.jpg", 18, false},

            //u_u_exo_negro
            {"http://localhost:9000/api/v1/product-images/files/surf/u_u_exo_negro/1.jpg", 19, true},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_u_exo_negro/2.jpg", 19, false},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_u_exo_negro/3.jpg", 19, false},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_u_exo_negro/4.jpg", 19, false},

            //u_cross_negro_negro
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_negro_negro/1.jpg", 20, true},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_negro_negro/2.jpg", 20, false},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_negro_negro/3.jpg", 20, false},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_negro_negro/4.jpg", 20, false},

            //u_cross_naranja
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_naranja/1.jpg", 21, true},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_naranja/2.jpg", 21, false},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_naranja/3.jpg", 21, false},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_naranja/4.jpg", 21, false},

            //u_cross_negro
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_negro/1.jpg", 22, true},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_negro/2.jpg", 22, false},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_negro/3.jpg", 22, false},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_negro/4.jpg", 22, false},
            
            
            // PRUEBASSS  M Skate 2 Wayvee blanco
            {"http://localhost:9000/api/v1/product-images/files/skate/m2_blanco/1.jpg", 23, true},
            {"http://localhost:9000/api/v1/product-images/files/skate/m2_blanco/2.jpg", 23, false},
            {"http://localhost:9000/api/v1/product-images/files/skate/m2_blanco/3.jpg", 23, false},
            {"http://localhost:9000/api/v1/product-images/files/skate/m2_blanco/4.jpg", 23, false},

            ////// PRUEBAS////// upland blanco
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_blanco/1.jpg", 24, true},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_blanco/2.jpg", 24, false},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_blanco/3.jpg", 24, false},
            {"http://localhost:9000/api/v1/product-images/files/classics_plus/u_upland_blanco/4.jpg", 24, false},

            ////// PRUEBAS////// knu stack
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_stack_negro/1.jpg", 25, true},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_stack_negro/2.jpg", 25, false},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_stack_negro/3.jpg", 25, false},
            {"http://localhost:9000/api/v1/product-images/files/classics/u_knu_stack_negro/4.jpg", 25, false},

            //u_cross_negro PREUBAS
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_negro/1.jpg", 26, true},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_negro/2.jpg", 26, false},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_negro/3.jpg", 26, false},
            {"http://localhost:9000/api/v1/product-images/files/surf/u_cross_negro/4.jpg", 26, false},








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
            String[] euSizes = {"35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47"};
            
            for (String sizeNumber : euSizes) {
                Size size = new Size();
                size.setNumber(sizeNumber);
                size.setSystemType(Size.SystemType.ARG);
                size.setCreatedAt(LocalDateTime.now());
                size.setIsActive(true);
                sizeService.save(size);
            }
            
            // Talles estadounidenses estándar para zapatillas
            /*
             * 
             String[] usSizes = {"6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10", "10.5", "11", "11.5", "12", "12.5", "13"};
             
             for (String sizeNumber : usSizes) {
                Size size = new Size();
                size.setNumber(sizeNumber);
                size.setSystemType(Size.SystemType.US);
                size.setCreatedAt(LocalDateTime.now());
                size.setIsActive(true);
                sizeService.save(size);
            }
            */
            
            System.out.println("OK " + euSizes.length + " talles europeos = argentinos.");
            System.out.println("Total de talles cargados: " + euSizes.length);
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
            int zeroStockCount = 0;
            
            // Asociar cada producto con todos los talles disponibles
            for (var product : products) {
                // Para cada producto, crear una lista de stocks
                java.util.List<Integer> productStockList = new java.util.ArrayList<>();
                
                // Garantizar que al menos 4 talles tengan stock 0 para este producto
                int zeroStockForProduct = Math.min(4, sizes.size());
                for (int i = 0; i < zeroStockForProduct; i++) {
                    productStockList.add(0);
                }
                
                // Completar el resto con valores aleatorios entre 1 y 100
                for (int i = productStockList.size(); i < sizes.size(); i++) {
                    int stock = (int) (Math.random() * 100) + 1; // 1 a 100
                    productStockList.add(stock);
                }
                
                // Mezclar la lista para distribuir aleatoriamente los stocks de este producto
                java.util.Collections.shuffle(productStockList);
                
                // Asignar stocks a cada talle de este producto
                int sizeIndex = 0;
                for (var size : sizes) {
                    ProductSize productSize = new ProductSize();
                    productSize.setIdProduct(product.getId());
                    productSize.setIdSize(size.getId());
                    
                    // Asignar stock de la lista pre-generada para este producto
                    int stock = productStockList.get(sizeIndex++);
                    productSize.setStock(stock);
                    
                    if (stock == 0) {
                        zeroStockCount++;
                    }
                    
                    productSizeService.save(productSize);
                    associationsCount++;
                    totalStock += stock;
                }
            }
            
            System.out.println("Se han creado " + associationsCount + " asociaciones producto-talle con stock.");
            System.out.println("(" + products.size() + " productos × " + sizes.size() + " talles = " + associationsCount + " asociaciones)");
            System.out.println("Stock total generado: " + totalStock + " unidades");
            System.out.println("Stock promedio por producto-talle: " + (totalStock / associationsCount) + " unidades");
            System.out.println("Talles sin stock (stock = 0): " + zeroStockCount);
            System.out.println("Cada producto tiene al menos 4 talles sin stock");
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

    private void loadTestOrders() throws Exception {
        System.out.println("Cargando 5 órdenes de prueba idénticas...");
        
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
            System.out.println("No se encontró el usuario cliente para crear las órdenes de prueba");
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
        
        // Obtener todos los productos
        var products = productService.findAll();
        if (products.size() < 4) {
            System.out.println("No hay suficientes productos para crear las órdenes de prueba");
            return;
        }
        
        // Obtener los últimos 4 productos (que tienen precio $100)
        // Según el DataInitializer, los productos con precio $100 son los últimos 4
        List<Product> testProducts = new ArrayList<>();
        for (int i = products.size() - 4; i < products.size(); i++) {
            if (products.get(i).getPrice() == 100.0) {
                testProducts.add(products.get(i));
            }
        }
        
        if (testProducts.size() < 4) {
            System.out.println("No se encontraron los 4 productos de $100 para las órdenes de prueba");
            return;
        }
        
        // Obtener talles disponibles
        var sizes = sizeService.findAll();
        if (sizes.size() < 4) {
            System.out.println("No hay suficientes talles disponibles para crear las órdenes de prueba");
            return;
        }
        
        // Calcular el total: 4 productos × $100 × 2 unidades = $800
        double total = 800.0;
        
        // Crear 5 órdenes de prueba idénticas
        for (int orderNum = 1; orderNum <= 5; orderNum++) {
            // Crear la orden de prueba
            PurchaseOrder testOrder = new PurchaseOrder();
            testOrder.setUserId(cliente.getId());
            testOrder.setUserAddressId(clienteAddress.getAddressId());
            testOrder.setTotal(total);
            testOrder.setPaymentMethod("MercadoPago");
            testOrder.setStatus(PurchaseOrder.Status.PENDING);
            testOrder.setIsActive(true);
            testOrder.setCreatedAt(LocalDateTime.now());
            
            // Guardar la orden
            PurchaseOrder savedOrder = purchaseOrderService.save(testOrder);
            
            // Crear los detalles de la orden (2 unidades de cada producto)
            for (int i = 0; i < 4; i++) {
                Detail detail = new Detail();
                detail.setQuantity(2); // 2 unidades de cada producto
                detail.setProductId(testProducts.get(i).getId());
                detail.setOrderId(savedOrder.getId());
                detail.setSizeId(sizes.get(i).getId()); // Usar diferentes talles
                detail.setIsActive(true);
                detail.setCreatedAt(LocalDateTime.now());
                
                detailService.save(detail);
            }
            
            System.out.println("Orden de prueba #" + orderNum + " creada (ID: " + savedOrder.getId() + ")");
        }
        
        System.out.println("=== RESUMEN DE ÓRDENES DE PRUEBA ===");
        System.out.println("5 órdenes de prueba creadas (PENDING):");
        System.out.println("   - Cliente: " + cliente.getName() + " " + cliente.getLastName());
        System.out.println("   - Total por orden: $" + total);
        System.out.println("   - Estado: PENDING");
        System.out.println("   - Productos por orden:");
        for (int i = 0; i < testProducts.size(); i++) {
            System.out.println("     * " + testProducts.get(i).getName() + " (Cantidad: 2, Precio: $" + testProducts.get(i).getPrice() + ")");
        }
        System.out.println("   - Total de productos por orden: 8 unidades");
        System.out.println("   - Total general: $" + (total * 5) + " (5 órdenes × $" + total + ")");
    }
} 