FROM openjdk:17-jdk-slim
COPY ../login-api/target/login-api-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
