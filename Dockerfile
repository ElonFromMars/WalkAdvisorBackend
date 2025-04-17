# Stage 1: build with Gradle
FROM gradle:7.6-jdk17 AS builder
WORKDIR /app
# copy wrapper, settings, build files and source
COPY gradlew gradlew.bat ./
COPY gradle ./gradle
COPY build.gradle settings.gradle ./
COPY src ./src
RUN chmod +x gradlew && ./gradlew bootJar --no-daemon

# Stage 2: run the JAR
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
