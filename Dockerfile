FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/Franquicias-0.0.1-SNAPSHOT.jar /app/Franquicias-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Franquicias-0.0.1-SNAPSHOT.jar"]
