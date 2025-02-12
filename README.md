Microservicio RESTful para gestión de pedidos, desarrollado con **Spring Boot** y **MongoDB**. Permite operaciones CRUD, validaciones integradas, y documentación Swagger.

## Características

- ✅ CRUD completo de pedidos
- ✅ Validación de datos en requests (Bean Validation)
- ✅ Manejo centralizado de errores
- ✅ Documentación Swagger/OpenAPI
- ✅ Paginación y filtros (estado, fecha)
- ✅ Pruebas unitarias e integración (Testcontainers)
- ✅ DTOs para requests/responses
- ✅ Cálculo automático de subtotal por ítem

## Requisitos Previos

- Java 17+
- MongoDB 6.0+ (Docker recomendado)
- Maven 3.8+
- Postman o herramienta similar (opcional)

## Configuración

### 1. Clonar Repositorio
```bash
git clone https://github.com/tu-usuario/order-microservice.git
cd order-microservice
```
### 2. Iniciar MongoDb
```bash
mongod
```
### 3. Compilar y Ejecutar
```bash
mvn clean spring-boot:run
```
### 4. Accede a Swagger UI
http://localhost:8080/swagger-ui.html
