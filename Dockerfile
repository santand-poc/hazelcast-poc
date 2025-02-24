# Pobieramy obraz OpenJDK
FROM openjdk:17-jdk-slim

WORKDIR /app

# Kopiujemy plik JAR
COPY target/*.jar app.jar

# Ustawiamy domyślną komendę
CMD ["java", "-jar", "/app/app.jar"]
