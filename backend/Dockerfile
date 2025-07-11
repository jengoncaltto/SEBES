# Usa a imagem base com Java 21 (jdk slim)
FROM eclipse-temurin:21-jdk-jammy

# Copia o jar gerado para dentro do container
COPY target/*.jar app.jar

# Expõe a porta padrão do Spring Boot (8080)
EXPOSE 8080

# Comando para rodar o app
ENTRYPOINT ["java", "-jar", "/app.jar"]
