# Use the official OpenJDK base image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build directory to the container
COPY target/notesolver-0.0.1-SNAPSHOT.jar /app/notesolver-0.0.1-SNAPSHOT.jar

# Expose the port that your Spring Boot application is listening on (change '8080' to the appropriate port)
EXPOSE 8080

# Command to run your Spring Boot application
CMD ["java", "-jar", "notesolver-0.0.1-SNAPSHOT.jar"]