# Spring Boot Gradle Application with JPA and H2 Database

## Overview

This is a sample Spring Boot application that provides a RESTful API to manage authors and their books using Java Persistence API (JPA) and an in-memory H2 database.

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
cd ser421public/serverside/Spring/BooktownRestAPIJPA
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

The application will start and by default will be accessible at `http://localhost:8080`.

## Features

- CRUD operations for authors
- Efficient and standardized error handling.
- Data persistence using JPA and H2 database.
- Author and Book Database schema with sample data are auto-created on application start-up.

## API Endpoints

- GET /authors: Fetch all authors.
- GET /authors/{id}: Fetch a specific author by ID.
- POST /authors: Create a new author.
- PUT /authors/{id}: Update an existing author or create a new one.
- PATCH /authors/{id}: Modify an existing author by ID.
- DELETE /authors/{id}: Delete an author by ID.
- GET /books: Fetch all books.
- GET /books/{isbn}: Fetch a specific book by ISBN.
- DELETE /books/{isbn}: Delete a book by ISBN.

## Database

This application uses H2, an in-memory database. The console is auto-configured on `http://localhost:8080/h2-console`.

- JDBC URL: `jdbc:h2:mem:booktownjpa`
- User Name: `ser421`
- Password: `password`
