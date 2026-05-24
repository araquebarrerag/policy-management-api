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

(Al momento de ejecutar el proyecto, por dentro se ejecuta un script que esta en data.sql, con unos datos predefinidos para las polizas y los riesgos)

## 4- Seguridad

Header obligatorio:

x-api-key: 123456

## 5- Ejemplos para el consumo de los endPoints

* GET /polizas: http://localhost:8080/polizas?type=COLLECTIVE&status=ACTIVE
* GET /polizas/{id}/riesgos: http://localhost:8080/polizas/1/riesgos
* POST /polizas/{id}/renovar: http://localhost:8080/polizas/1/renovar?ipc=0.05
* POST /polizas/{id}/cancelar: http://localhost:8080/polizas/1/cancelar
* POST /polizas/{id}/riesgos: http://localhost:8080/polizas/2/riesgos (Enviar el body con los datos del riesgo)
* POST /riesgos/{id}/cancelar: http://localhost:8080/riesgos/2/cancelar
* POST /core-mock/evento: http://localhost:8080/core-mock/evento (Enviar el evento y el id de la poliza)