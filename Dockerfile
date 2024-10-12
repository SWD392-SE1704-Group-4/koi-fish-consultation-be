# Use an official Maven image to build the app
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copy the pom.xml and project files to the container
COPY ./pom.xml ./ 
COPY ./Fengshui-Koi-Consultation-System-Api ./Fengshui-Koi-Consultation-System-Api
COPY ./Fengshui-Koi-Consultation-System-Common ./Fengshui-Koi-Consultation-System-Common

# Package the application
RUN mvn clean package -DskipTests

# Use an OpenJDK runtime image to run the app
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/Fengshui-Koi-Consultation-System-Api/target/*.jar /app/app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Command to run the app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
