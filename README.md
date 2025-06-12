# Ecommerce de Zapatillas - API REST

Este proyecto es una API REST desarrollada con Spring Boot para un ecommerce de zapatillas.

## Requisitos Previos

- Java 17 o superior
- Maven
- MySQL (o la base de datos configurada en el proyecto)

## Configuración del Proyecto

1. Clona el repositorio:
```bash
git clone [URL_DEL_REPOSITORIO]
```

2. Navega al directorio del proyecto:
```bash
cd apirest
```

## Ejecución del Proyecto

### Usando Maven Wrapper (recomendado)

```bash
./mvnw spring-boot:run
```

### Usando Maven (si está instalado globalmente)

```bash
mvn spring-boot:run
```

## Estructura del Proyecto

- `src/main/java`: Código fuente Java
- `src/main/resources`: Archivos de configuración y recursos
- `src/test`: Pruebas unitarias y de integración

## Tecnologías Utilizadas

- Spring Boot 3.4.5
- Spring Data JPA
- Spring Web
- Maven
- MySQL (o la base de datos configurada)

## Documentación de la API

La documentación de la API estará disponible en:
- Swagger UI: http://localhost:9000/swagger-ui.html (si está configurado)
- API Endpoints: http://localhost:9000/api/v1

## Desarrollo

Para contribuir al proyecto:

1. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
2. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
3. Push a la rama (`git push origin feature/AmazingFeature`)
4. Abre un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo LICENSE para más detalles. 



//Endpoints nuevos

GET    /api/v1/{entity}              # Solo activos y no eliminados
GET    /api/v1/{entity}/active       # Solo activos
GET    /api/v1/{entity}/inactive     # Solo inactivos
GET    /api/v1/{entity}/soft-deleted # Solo eliminados suavemente
PUT    /api/v1/{entity}/{id}/activate    # Activar elemento
PUT    /api/v1/{entity}/{id}/deactivate  # Desactivar elemento
PUT    /api/v1/{entity}/{id}/soft-delete # Eliminar suavemente