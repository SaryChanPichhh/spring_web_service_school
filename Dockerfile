# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the pom.xml and download dependencies (caching step)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build the application
COPY src src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy all generated jars from the build stage
COPY --from=build /app/target/*.jar /app/

# Remove the -plain jar (if Spring Boot generated one) and rename the fat jar to app.jar
RUN rm -f /app/*-plain.jar && mv /app/*.jar /app/app.jar

# Render assigns a dynamic port via the PORT environment variable.
# We set a default of 8080 for local testing.
ENV PORT=8080
EXPOSE $PORT

# Start the Spring Boot application, telling it to listen on the $PORT variable
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT} -jar /app/app.jar"]
