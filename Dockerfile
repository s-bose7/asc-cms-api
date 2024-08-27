# Stage 1: Build the application
FROM gradle:8.2-jdk17 AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Download dependencies and build the application
RUN ./gradlew build --no-daemon

# Copy the entire source code
COPY . .

# Build the application
RUN ./gradlew build -x test --no-daemon

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/asc-cms-api-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port the application will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
