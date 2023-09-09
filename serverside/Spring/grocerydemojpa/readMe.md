# Spring Boot Gradle Application with JPA and H2 Database

## Overview

This is a sample Spring Boot application that demonstrates how to use Gradle as a build tool, integrate with JPA (Java Persistence API), and use H2 as an in-memory database.

## Prerequisites

- Java 8 or higher
- Gradle
- Your favorite IDE (Eclipse, IntelliJ, etc.)

## Getting Started

### Clone the repository

```shell
git clone https://github.com/kgary/ser421public.git
```

### Navigate to the project directory

```shell
   cd ser421public/serverside/Spring/grocerydemojpa
```

### Build the project

```shell
gradle clean build
```

Or, if you have a Gradle wrapper:

```shell
./gradlew clean build
```

### Running the application

```shell
gradle bootRun
```

Or using the Gradle wrapper:

```shell
./gradlew bootRun
```

The application will start running at `http://localhost:8080/groceries`.

## Features

- Data persistence using JPA and H2 database.
- GroceryList Database schema and sample data are auto-created on application start-up.

## API Endpoints

| Method | Endpoint   | Description                               |
| ------ | ---------- | ----------------------------------------- |
| GET    | /groceries | Get all the groceries                     |
| POST   | /groceries | Selected groceries to proceed to checkout |
| GET    | /checkout  | Get all the selected groceris             |
| POST   | /checkout  | Redirected to success page                |

## Database

This application uses H2, an in-memory database. The console is auto-configured on `http://localhost:8080/h2-console`.

- JDBC URL: `jdbc:h2:mem:jpagrocerydemo`
- User Name: `ser421`
- Password: `password`
