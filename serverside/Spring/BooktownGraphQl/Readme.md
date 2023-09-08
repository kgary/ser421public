# Spring Boot GraphQL API

This is a sample Spring Boot application that provides a GraphQL API for managing books and authors.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [GraphQL Schema](#graphql-schema)
- [Endpoints](#endpoints)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This Spring Boot application demonstrates how to build a GraphQL API for managing books and authors. It uses the Spring Boot framework along with the GraphQL Java library to expose a flexible and efficient API for querying and mutating data.

## Features

- Retrieve a list of authors.
- Retrieve an author by their unique ID.
- Retrieve a list of books.
- Retrieve a book by its ISBN.
- Add a new author.
- Add a new book associated with an author.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) installed (Java 8 or higher).

## Getting Started

1. Clone this repository:

   ```shell
   git clone https://github.com/kgary/ser421public.git
   
2. Go to serverside/spring/BooktownGraphQLAPI/ser421
   ```shell
   cd serverside/Spring/BooktownGraphQLAPI/ser421

3. Use gradlew to run the application
   ```shell
   ./gradlew bootRun


## Usage

To interact with the GraphQL API, you have a few options:

- Use a tool like [Postman](https://www.postman.com/) or [Insomnia](https://insomnia.rest/) to make HTTP requests to the API endpoints.
- Use the built-in GraphQL Playground available at [http://localhost:8080/graphiql?path=/graphql](http://localhost:8080/graphiql?path=/graphql) when the application is running.

## GraphQL Schema

The GraphQL schema defines the types and operations available in the API. You can explore the schema and perform queries and mutations using the GraphQL Playground.

For detailed schema documentation, please refer to the "GraphQL Schema" section in this README.

## Endpoints

- **GraphQL Playground**: [http://localhost:8080/graphiql?path=/graphql](http://localhost:8080/graphiql?path=/graphql)


**Check GraphQLReadme.md for sample queries and mutation**

   

