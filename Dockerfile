# Use Maven for the build stage
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copy the main pom.xml and the source code for the Common and Api modules
COPY ./pom.xml ./
COPY ./Fengshui-Koi-Consultation-System-Common ./Fengshui-Koi-Consultation-System-Common
COPY ./Fengshui-Koi-Consultation-System-Api ./Fengshui-Koi-Consultation-System-Api

# Build the Common module first and then the core-business module
RUN mvn -f ./Fengshui-Koi-Consultation-System-Common/pom.xml clean install
RUN mvn -f ./Fengshui-Koi-Consultation-System-Api/core-business/pom.xml clean package

# Use a lightweight JDK for running the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/Fengshui-Koi-Consultation-System-Api/core-business/target/core-business-0.0.1-SNAPSHOT.jar /app/core-business.jar

# Expose the default port for Spring Boot
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "core-business.jar"]
