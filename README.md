# Expense Tracking App

## Overview

The Expense Tracking App is a robust Spring Boot application designed to streamline expense management. This application allows users to track, manage, and visualize their expenses through a set of RESTful APIs. It leverages modern technologies and practices including Docker for containerization, Swagger for API documentation, and Spring Security for robust authentication.

## Features

- **CRUD Operations for Expenses**: Create, Read, Update, and Delete expenses with ease.
- **Swagger Integration**: Interactive API documentation for testing and exploration.
- **Secure Authentication**: JWT-based security ensuring secure access to the application.
- **In-Memory Database**: H2 database for development and testing purposes.
- **Docker Support**: Easily deployable using Docker for consistent environments.

## Technologies

- **Backend**: Java 17, Spring Boot
- **Database**: H2 (for in-memory storage)
- **Security**: Spring Security with JWT
- **API Documentation**: Swagger 2.0
- **Containerization**: Docker

## Prerequisites

Before running the application, ensure you have the following installed:

- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) or later
- [Maven](https://maven.apache.org/) (for building the project)
- [Docker](https://www.docker.com/products/docker-desktop) (for containerization)
- [Git](https://git-scm.com/) (for version control)

## Directory Structure

```bash
    ExpenseTrackingApp
    ├── .github
    │   └── workflows
    │       └── ci-cd.yml
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   └── com
    │   │   │       └── expense
    |   |   |           |── config
    │   │   │           │   |── SecurityConfig.java
    |   |   |           |   └── SwaggerConfig.java
    │   │   │           ├── controller
    │   │   │           │   └── ExpenseController.java
    │   │   │           ├── entity
    │   │   │           │   └── Expense.java
    │   │   │           ├── exception
    │   │   │           │   └── ResourceNotFoundException.java
    │   │   │           ├── repository
    │   │   │           │   └── ExpenseRepository.java
    │   │   │           └── service
    │   │   │               └── ExpenseService.java
    │   │   └── resources
    │   │       ├── application.properties
    │   │       └── data.sql
    │   └── test
    │       └── java
    │           └── com
    │               └── expense
    │                   |── ExpenseControllerTest.java
    |                   |── ExpenseServiceTest.java
    |                   └── ExpenseTrackingAppApplicationTests.java
    ├── Dockerfile
    ├── README.md
    ├── docker-compose.yml
    └── pom.xml
```

## Setup Instructions

### Running the Application

#### Using Maven
1. Clone the repository:
    ```bash
    git clone https://github.com/AnushkaHere/ExpenseTrackingApp.git
    ```
2. Navigate to the project directory:
    ```bash
    cd ExpenseTrackingApp
    ```
3. Build the project:
    ```bash
    mvn clean install
    ```
4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

#### Using Docker
1. Clone the repository:
    ```bash
    git clone https://github.com/AnushkaHere/ExpenseTrackingApp.git
    ```
2. Navigate to the project directory:
    ```bash
    cd ExpenseTrackingApp
    ```
3. Build and run the containers:
    ```bash
    docker-compose up --build
    ```

### Accessing the Application
- The application will be available at `http://localhost:8080`.
- Swagger UI can be accessed at `http://localhost:8080/swagger-ui.html`.

### Testing the Application
You can use Postman or any other API client to test the endpoints. Here are some example requests:

#### Get All Expenses
- **Endpoint**: `GET /api/expenses`
- **Response**: Returns a list of all expenses

#### Get Expense by ID
- **Endpoint**: `GET /api/expenses/{id}`
- **Response**: Returns the details of the expense with the given ID

#### Create Expense
- **Endpoint**: `POST /api/expenses`
- **Request Body**:
    ```json
    {
        "description": "Groceries",
        "amount": 50.00,
        "date": "2023-07-20"
    }
    ```
- **Response**: Returns the created expense

#### Update Expense
- **Endpoint**: `PUT /api/expenses/{id}`
- **Request Body**:
    ```json
    {
        "description": "Groceries and utilities",
        "amount": 75.00,
        "date": "2023-07-20"
    }
    ```
- **Response**: Returns the updated expense

#### Delete Expense
- **Endpoint**: `DELETE /api/expenses/{id}`
- **Response**: Deletes the expense with the given ID

## CI/CD Pipeline
The CI/CD pipeline is configured using GitHub Actions. The workflow file is located at `.github/workflows/ci-cd.yml`. It includes steps for building the project, running tests, and deploying the application.
