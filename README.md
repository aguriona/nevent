Microservicio RESTful para gestión de pedidos, desarrollado con **Spring Boot** y **MongoDB**.

## Características

- ✅ CRUD completo de pedidos
- ✅ Validación de datos en requests
- ✅ Manejo centralizado de errores
- ✅ Documentación Swagger/OpenAPI
- ✅ Paginación
- ✅ Pruebas unitarias e integración
- ✅ DTOs para requests/responses

## Requisitos Previos

- Java 17+
- MongoDB 
- Maven
  
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
