# Use an Amazon Corretto image to build the application
FROM amazoncorretto:22-alpine AS build

# Install Maven
RUN apk add --no-cache maven

# Set the working directory inside the container
WORKDIR /cb-ubb-ingestion-service

# Copy the pom.xml and any other necessary files for Maven to resolve dependencies
COPY pom.xml ./

# Copy the source code
COPY src ./src

# Run the Maven build
RUN mvn clean package -DskipTests

# Use an Amazon Corretto 22 image to create the runtime environment
FROM amazoncorretto:22-alpine

# Set the working directory inside the container
WORKDIR /cb-ubb-ingestion-service

# Define a build argument for the JAR file version
ARG JAR_VERSION=1.0.0

# Copy the built JAR file from the previous stage
COPY --from=build /cb-ubb-ingestion-service/target/cb-ubb-ingestion-service-${JAR_VERSION}.jar cb-ubb-ingestion-service.jar

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "cb-ubb-ingestion-service.jar"]