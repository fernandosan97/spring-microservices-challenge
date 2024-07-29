#FROM eclipse-temurin:17-jdk-alpine
FROM openjdk:17-jdk-slim
WORKDIR /root

COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

RUN ./mvnw dependency:go-offline

COPY ./src /root/src

RUN ./mvnw clean install -DskipTests

ENTRYPOINT ["java", "-jar", "/root/target/microservice.movimientos-0.0.1-SNAPSHOT.jar"]