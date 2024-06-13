# Instalación y arranque del proyecto

## Requisitos previos:
### Versión de java:

#### 1- Java 17  [Descargar](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

#### 2- Java 21 [Descargar](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)

### Versión de spring-boot framework:

#### Spring-boot framewwork: 3.1.5 [Descargar](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot/3.1.5)

## Instrucciones
### Clonar el proyecto
```
$ git clone https://github.com/davidmp19/Proyecto-David_Menendez
```
### Arrancar las base de datos: [Ver repositorio](https://github.com/davidmp19/Docker)

### (Método 1) Abrir el proyecto con el IDE deseado:
#### Esperar a que se descarguen las dependecias de maven.
#### Arrancar el proyecto.

### (Método 2) Abrir un cmd y entrar al proyecto:

#### (Opcional) Realizar instalación limpia de dependencias:
```
$ mvn clean install
```
#### (Opcional) Enpaquetar el proyecto:
```
$ mvn clean package
```
#### Iniciar el proyecto:
```
$ mvn spring-boot:run
```