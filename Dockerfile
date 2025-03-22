# Usa una imagen base con Java 24 y Maven preinstalados
FROM maven:3.9.9-amazoncorretto-23-debian AS builder

# Copia los archivos del proyecto al contenedor
COPY pom.xml /app/pom.xml
COPY src /app/src

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Construye el archivo JAR usando Maven
RUN mvn clean package

# Usa una imagen base con Java 24 para la ejecución
FROM openjdk:23-jdk-slim

# Copia el archivo JAR construido desde la etapa anterior
COPY --from=builder /app/target/*.jar /app/possumus.jar

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Expone el puerto en el que tu aplicación Java escucha (si es necesario)
EXPOSE 8080

# Comando para ejecutar tu aplicación Java
CMD ["java", "-jar", "possumus.jar"]