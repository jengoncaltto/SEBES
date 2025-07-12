# Etapa 1: Build do JAR usando Maven e Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia arquivos do projeto para o container
COPY pom.xml .
COPY src ./src

# Compila o projeto (sem testes)
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final somente com o JAR
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copia o .jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Usa a variável PORT da Render
ENV PORT=8080
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]