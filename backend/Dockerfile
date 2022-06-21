FROM openjdk:8-jdk-alpine
FROM maven:3.8.6-jdk-8

WORKDIR /app

COPY porm.xml testng.xml ./

RUN mvn dependency:go-offline
RUN mvn clean install -DskipTests

COPY src ./src

CMD ["mvn", "spring-boot:run"]
