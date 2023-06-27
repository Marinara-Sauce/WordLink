FROM maven:3.8.4-openjdk-11-slim AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src
COPY data ./data

CMD ["mvn", "spring-boot:run"]
