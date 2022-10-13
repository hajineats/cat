# Project 52: Computer Adaptive Testing in Educational Assessment

Computer adaptive testing (CAT) is a form of testing which uses algorithms to adjust its difficulty based on the ability of the user with the aim of providing more precise ability measurements. This project focuses on a form of CAT known as multistage testing (MST) and aims to compare the effectiveness of MST against traditional fixed length tests by implementing an online testing platform supporting both testing paradigms. The R package, mstR, is used to implement the features of the multistage test (e.g. ability estimation and module selection) which is integrated with the backend via RCaller.

The backend is created in Spring Boot can be found in [`src/main/java/com/p4p/cat`](src/main/java/com/p4p/cat). 

The frontend uses React and can be found in [`src/main/js`](src/main/js).

The questions used to create the tests are from the Trends in International Mathematics and Science Study (TIMSS) 2011 Grade 8 mathematics assessment and can be found in [`src/main/scripts/all.json`](src/main/scripts/all.json).

## Research Documents
For more information about the project design, development, and testing process, view the [`doc`](doc) folder which contains all the relevant material.

## Project Setup
1. Install R
2. Install the mstR package by entering ```install.packages("mstR")``` in the R console
3. Install the Gradle dependencies specified in the build.gradle file
4. Install frontend dependencies by running npm install in src/main/js

Note: The application uses MongoDB therefore you should set up an application.properties file in src\main\resources with a line specifying the database URI i.e. ```spring.data.mongodb.uri=YOUR_MONGODB_URI```

## Build Process
1. Build the frontend using ```npm run build```
2. Move frontend build files to the backend static folder (`src/main/resources`)
3. Build the backend into a jar using ```gradle build```

## Deployment
The application is deployed on an AWS EC2 instance setup with Java, R and mstR. The database is hosted remotely using MongoDB Atlas Cluster.
