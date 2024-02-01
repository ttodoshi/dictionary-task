FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package -DskipTests


FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/dictionary-1.0-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "dictionary-1.0-SNAPSHOT.jar"]
