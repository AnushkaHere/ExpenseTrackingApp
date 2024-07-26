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

## Setup Instructions

### Local Development

1. **Clone the Repository:**

     ```bash
       git clone https://github.com/YourUsername/ExpenseTrackingApp.git
     ```
     
     ```bash
       cd ExpenseTrackingApp
     ```
2. **Build and Run the Application:**

    Ensure you have Maven installed. You can run the application using Maven:

    ```bash
      ./mvnw spring-boot:run
    ```

3. **Access the Application:**

    Open your browser and navigate to http://localhost:8080/api/expenses to start interacting with the API.

4. **Running with Docker:**

    Build the Docker Image:

    ```bash
      docker build -t expense-tracking-app .
    ```

5. **Run the Docker Container:**

    ```bash
      docker run -p 8080:8080 expense-tracking-app
    ```

6. **Access the Application:**

    Open your browser and navigate to http://localhost:8080/api/expenses.

## API Documentation
  
  Swagger UI
  The Swagger UI is available at http://localhost:8080/swagger-ui/index.html. It provides a user-friendly interface for exploring the API endpoints and their details.
  
  ### Endpoints
  
  **Expenses API**
  - GET /api/expenses: Retrieve a list of all expenses
  - GET /api/expenses/{id}: Retrieve a specific expense by ID
  - POST /api/expenses: Create a new expense
  - PUT /api/expenses/{id}: Update an existing expense
  - DELETE /api/expenses/{id}: Delete an expense
  
  **Example API Requests**
  
  Retrieve All Expenses
 
  ```bash
    curl -X GET "http://localhost:8080/api/expenses" -H "accept: application/json"
  ```
  Retrieve an Expense by ID
  
  ```bash
    curl -X GET "http://localhost:8080/api/expenses/1" -H "accept: application/json"
  ```
  
  Create a New Expense
  ```bash
    curl -X POST "http://localhost:8080/api/expenses" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"amount\":100,\"description\":\"Groceries\",\"date\":\"2023-01-01\"}"
  ```

  Update an Expense
  
  ```bash
    curl -X PUT "http://localhost:8080/api/expenses/1" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"amount\":150,\"description\":\"Groceries and more\",\"date\":\"2023-01-01\"}"
  ```
  
  Delete an Expense
  ```bash
    curl -X DELETE "http://localhost:8080/api/expenses/1" -H "accept: application/json"
  ```

## CI/CD Pipeline

The CI/CD pipeline is configured using GitHub Actions. The workflow file is located at `.github/workflows/main.yml`. The pipeline performs the following steps:

1. Checks out the code from the repository.
2. Sets up JDK 17.
3. Caches Maven dependencies.
4. Builds the application using Maven.
5. Builds the Docker image.
6. Runs the Docker container.
7. Waits for the application to start.
8. Runs the tests.
9. Pushes the Docker image to Docker Hub if the tests pass.

### Secrets

The following secrets must be configured in your GitHub repository for the CI/CD pipeline to work:

- `DOCKER_USERNAME`: Your Docker Hub username.
- `DOCKER_PASSWORD`: Your Docker Hub password.

...
