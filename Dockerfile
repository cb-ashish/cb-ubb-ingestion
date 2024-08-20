# Use an Amazon Corretto image to build the application
FROM amazoncorretto:22-alpine AS build

# Install Maven
RUN apk add --no-cache maven

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and any other necessary files for Maven to resolve dependencies
COPY pom.xml ./

# Copy the source code
COPY src ./src

# Run the Maven build
RUN mvn clean package -DskipTests

# Use an Amazon Corretto 22 image to create the runtime environment
FROM amazoncorretto:22-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
