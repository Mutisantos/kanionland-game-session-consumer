# Use OpenJDK base image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file from your build
COPY target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8100

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
