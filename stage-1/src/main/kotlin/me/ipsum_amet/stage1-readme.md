# See it Live [here](https://kibetrns-hngxbackend-stage1.onrender.com)


# Objective
Create and host an endpoint using any programming language of your choice.
The endpoint should take two GET request query parameters and return specific information in JSON format.

# Requirements
The information required includes:

- Slack name
- Current day of the week
- Current UTC time (with validation of +/-2)
- Track
- The GitHub URL of the file being run
- The GitHub URL of the full source code.
- A  Status Code of Success

# Running the App

This guide will walk you through the steps to Stage 1 application using Docker or by building and running the JAR file directly.

## Prerequisites

Before you begin, make sure you have the following prerequisites installed on your system:

- [Docker](https://www.docker.com/get-started) - Make sure Docker is installed and running on your machine.

OR

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) - Make sure you have Java 11 or higher installed.

## Option 1: Running with Docker
Clone the HNGx Backend repository and navigate to stage-1 directory:
```
git clone https://github.com/kibetrns/HNGx-Backend.git
```
Navigate to stage-1 directory
```
cd stage-1
```

Build the Docker image:
```
docker build -t stage-1
```

Run the Docker container:
```
docker run -p 8080:8080 stage-1
```
The application should be accessible at http://localhost:8080.

## Option 2: Building and Running the JAR File Directly
Clone the HNGx Backend repository and navigate to stage-1 directory:
```
git clone https://github.com/kibetrns/HNGx-Backend.git
```
Navigate to stage-1 directory
```
cd stage-1
```

Build the JAR file using Gradle:
```
./gradlew buildFatJar
```

Run the application:
```
java -jar build/libs/stage-1.jar
```