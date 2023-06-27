# Use the official OpenJDK 11 image as the base image
FROM adoptopenjdk:11-jdk-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file and any other necessary files to the container
COPY target/WordLink-0.0.1-SNAPSHOT.jar /app/api.jar
COPY data/wordlist.txt /app/data/wordlist.txt

ENV DB_ADR = mysqldb

# Expose the port on which the application will run (change it to the appropriate port number)
EXPOSE 8080

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "/app/api.jar"]
