# Project 52: Computer Adaptive Testing in Educational Assessment

## Project Setup
1. Install R
2. Install the mstR package by entering ```install.packages("mstR")``` in the R console
3. Install the Gradle dependencies specified in the build.gradle file
4. Install frontend dependencies by running npm install in src\main\js

## Build Process
1. Build the frontend using ```npm run build```
2. Move build files in backend static folder (src\main\resources)
3. Build the backend into a jar using ```gradle build```

## Deployment
The application is deployed on an AWS EC2 instance setup with Java, R and mstR
