# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /voice-bridge-phone-register-service

# Copy the main pom.xml and module poms for dependency caching
COPY pom.xml /voice-bridge-phone-register-service/pom.xml
COPY phone.core/pom.xml /voice-bridge-phone-register-service/phone.core/pom.xml
COPY phone.cmd.api/pom.xml /voice-bridge-phone-register-service/phone.cmd.api/pom.xml
COPY phone.query.api/pom.xml /voice-bridge-phone-register-service/phone.query.api/pom.xml

# Download dependencies separately from source code for caching
RUN mvn -f /voice-bridge-phone-register-service/pom.xml dependency:go-offline

# Copy the entire project source code and build
COPY . /voice-bridge-phone-register-service
RUN mvn -f /voice-bridge-phone-register-service/pom.xml clean package -DskipTests

# Stage 2: Run the application
#FROM eclipse-temurin:21-jdk-slim
FROM openjdk:21-jdk-slim
WORKDIR /voice-bridge-phone-register-service
COPY --from=build /voice-bridge-phone-register-service/phone.query.api/target/phone.query.api-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/voice-bridge-phone-register-service/app.jar"]
