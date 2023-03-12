FROM maven:3.8.7-eclipse-temurin-17-alpine
WORKDIR /api
COPY ./pom.xml .
RUN mvn verify clean --fail-never

COPY ./src ./src
RUN mv ./src/main/resources/application-prod.properties  ./src/main/resources/application.properties 
RUN mvn package -DskipTests

EXPOSE 8080

# Executing the spring app
ENTRYPOINT ["java", "-jar",  "target/dosiAPI-1.0.jar"]
