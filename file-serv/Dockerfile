# Этап сборки
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Этап выполнения
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/file-serv-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
