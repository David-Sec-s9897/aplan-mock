# Stage 1: Build the Quarkus application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Quarkus application files to the container
COPY . /app

# Build the application
RUN mvn package -DskipTests

# Stage 2: Build the final image to run the Quarkus app
FROM eclipse-temurin:latest

# Copy the built application from the previous stage
#COPY --from=build /app/target/quarkus-app/lib/ /app/lib/
COPY --from=build /app/target/quarkus-app/lib/ /app/lib/
COPY --from=build /app/target/quarkus-app/app/ /app/app/
COPY --from=build /app/target/quarkus-app/quarkus/ /app/quarkus/
#COPY --from=build /app/target/quarkus-app/*.jar /app/
COPY --from=build /app/target/quarkus-app/*.jar /app/

# Set the entry point for running the Quarkus app
CMD ["java", "-jar", "/app/quarkus-run.jar"]

# Expose the default port for Quarkus
EXPOSE 8080