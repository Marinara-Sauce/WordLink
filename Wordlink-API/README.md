# Introduction
This folder contains the API for Wordlink created in Java Spring.

# Setup
To setup this project, you must have Java 11 and Maven installed and configured on your system. Afterwards, point a terminal to this directory and type
`mvn install` to install all dependencies.

# Compilation
To compile the project, use `mvn clean compile`. `clean` is optional but recommended as it'll delete the current `target` folder, removing outdated
binaries.

# Running the Project
To run the project, type `mvn spring-boot:run`. This will host the API to port 8080. You can use either `curl` or an REST API related program such as
[Insomnia](https://insomnia.rest/) to test it.

# Packaging the Project
To package the project to a .jar file, use the command `mvn package`. This will build a JAR in the target directory.

# Unit Testing
Aint nobody got time for that
