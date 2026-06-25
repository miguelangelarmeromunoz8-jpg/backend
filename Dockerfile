# Usar OpenJDK 17 para compilar
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests

# Usar OpenJDK 17 JRE para ejecutar (más ligero)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/actividad1-0.0.1-SNAPSHOT.jar /app/actividad1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/actividad1.jar"]