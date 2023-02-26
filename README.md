# WordLink
Wordlink is a web based word game created for BrickHack 9 using Java Spring and Angular.

# Requirements
To run/build from source, you need the following programs:
### API
* Java 11
* Maven
### UI
* Node.JS
* Angular

Angular can be installed via Node.JS, simply use `npm install -g @angular/cli` (or equivilent with your prefered package manager such as yarn.)

# Installation / Running the Program
First clone the repository. You'll receive two projects:
* `Wordlink-API`
* `Wordlink-UI`

The API uses Spring. To set it up, make sure you have Java 11 installed along with Maven, and that your environment variables are setup correctly.
Once the project is setup, point your terminal to `Wordlink-API` and run `mvn install` to install all the proper libraries. Then to run the project,
use the command `mvn compile` to compile the project, then `mvn spring-boot:run` to run it. The API will then be hosted on port 8080.

As for the UI, the UI runs with Angular. I recommend using Angular's development server for this as it'll auto-refresh the pages with any new changes
made to the source code. To set this up, first point your terminal to `WordLink-UI`. Then, install node modules with `npm install`. Once that's done,
use `ng serve` to start the development server. The UI will then be hosted on port 4200. Open a web browser and go to `http://localhost:4200` to start
using the application. Note: the API must also be running for this to work.

# Packaging the Project
Specific instructions for packaging will be located in that appropriate project's README.md file.

# License
This project is licensed under the [Creative Commons Attribution-NonCommercial-ShareAlike 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/) license.
