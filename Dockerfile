FROM eclipse-temurin:21-jdk-jammy
 
WORKDIR /app
 
# COPY .mvn/ .mvn
# COPY mvnw pom.xml ./
# RUN ./mvnw dependency:go-offline

# COPY src ./src
COPY target/*.jar app.jar
 
# CMD ["./mvnw", "spring-boot:run"]
CMD ["java", "-jar", "app.jar"]