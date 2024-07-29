# Docker Compose Setup for Microservices

La aplicación está desarrollada con la última versión de Spring boot y con OpenJDK 17
(Puedes ver la rama `feat/msvs-dockerized` para ejecutar el proeycto dockerizado)

Pasos a seguir para levantar la aplicación con Docker, los servicios son:

1. MySQL Database
2. Config Server (`msvc-config`)
3. Eureka Server (`msvc-eureka`)
4. Movements Service (`msvc-movimientos`)
5. Client Person Service (`msvc-clientperson`)
6. API Gateway (`msvc-gateway`)

## Prerequisites

Debe tener instalado lo siguiente:

- Docker
- Docker Compose


Cada microservicio cuenta con un archivo `Dockerfile` para crear la imagen, la cual es usada en el `docker-compose`


## Posibles errores
Puede ser que los contenedores fallen al ejecutarse, para resolver el error debes reiniciar los contenedores el siguiente orden:

1. MySQL Database
2. Config Server (`msvc-config`)
3. Eureka Server (`msvc-eureka`)
4. Movements Service (`msvc-movimientos`)
5. Client Person Service (`msvc-clientperson`)
6. API Gateway (`msvc-gateway`)