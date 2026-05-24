# API Gestión de Pólizas

## Requisitos
- Java 17
- PostgreSQL

## 1. Configurar la coneccion a Postgres

En el archivo application.properties configurar las propiedades

* spring.datasource.url: Configurar correctamente el puerto de Postgres
* spring.datasource.username: Configurar el nombre del usuario de Postgres
* spring.datasource.password: Configurar la contraseña del usuario de Postgres

## 2. Crear base de datos

Antes de inicializar el proyecto crear la base de datos en postgres

CREATE DATABASE policy_management_db;

## 3. Ejecutar Proyecto

mvn spring-boot:run

## 4- Seguridad

Header obligatorio:

x-api-key: 123456