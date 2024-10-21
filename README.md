# Franquicias API

Este es un proyecto de API RESTful desarrollado con **Spring Boot** que permite gestionar franquicias, sucursales y productos. La aplicación permite realizar operaciones CRUD para cada una de las entidades (Franchise, Branch, Product) y proporciona puntos de entrada para gestionar relaciones entre ellas, como agregar productos a sucursales.

## Características

- Gestión de **Franquicias**: Crear, listar, actualizar y eliminar franquicias.
- Gestión de **Sucursales**: Asociar sucursales a una franquicia, actualizar el nombre de las sucursales, eliminar sucursales, entre otras operaciones.
- Gestión de **Productos**: Agregar productos a sucursales, actualizar el stock o nombre de un producto, eliminar productos.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.3.4**: Framework principal de la aplicación.
- **Spring Data JPA**: Para interactuar con la base de datos usando repositorios JPA.
- **PostgreSQL 42.6.0**: Base de datos relacional
- **H2 Database**: Base de datos en memoria para pruebas.
- **Mockito**: Utilizado para realizar pruebas unitarias de los servicios y controladores.
- **JUnit 5**: Framework de pruebas unitarias.
- **Maven**: Para la gestión de dependencias y construcción del proyecto.
- **Docker**: Para la contenedorización de la aplicación.

## Requisitos

- **Java 17** o superior.
- **Maven 3.8+**
- **Docker**

## Instalación y Configuración

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/juanFajardo9999/Franquicias.git
2. **Compilar y empaquetar la aplicación**
   ```bash
   ./mvnw clean package 
3. **Ejecutar con Docker**
   ```bash
   docker-compose up --build 
4. **Ejecutar Tests**
   ```bash
   ./mvnw test 
5. **Uso de la API**
   ```bash
   https://documenter.getpostman.com/view/39096431/2sAXxY2nZe
