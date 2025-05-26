# Police Department Management System

## Overview

This full-stack application was developed as part of an academic project focused on modern software development practices. The system manages **Police Departments** (categories) and their assigned **Policemen** (elements), following a microservice-based architecture with RESTful communication, a responsive Angular frontend, and Dockerized deployment.

---

## Tech Stack

- **Backend:**
  - Java 17+
  - Spring Boot
  - Spring Data JPA (H2 In-Memory Database)
  - Spring MVC
  - Spring Cloud Gateway
- **Frontend:**
  - Angular (TypeScript)
  - Angular Router
- **DevOps:**
  - Docker (Eclipse Temurin, NGINX)
- **Build Tools:**
  - Maven
- **Others:**
  - Project Lombok
  - UUIDs for entity IDs
  - REST API testing with `.http` files in IntelliJ

---

## Domain Model

- **`PoliceDepartment`**
  - Fields: `id (UUID)`, `name (String)`, `location (String)`
  - One-to-many relationship with Policemen

- **`Policeman`**
  - Fields: `id (UUID)`, `fullName (String)`, `badgeNumber (int)`
  - Belongs to one PoliceDepartment

---

## Features

### Backend
- Entities mapped with JPA annotations and snake_case naming
- Bidirectional 1:N relationship using `@OneToMany` and `@ManyToOne`
- Spring Boot services and repositories with dependency injection
- H2 in-memory database with UUIDs as primary keys
- Initial data loader via Spring `@Component`

### REST API
- CRUD operations for PoliceDepartments and Policemen
- DTOs for create/update/read operations
- RESTful URI structure with proper HTTP methods and status codes
- Validation and error handling for common scenarios (e.g., missing department)

### Microservices Architecture
- Separate services for:
  - `police-department-service`
  - `policeman-service` (with embedded simplified department reference)
- Event-based sync for department add/delete
- Centralized API Gateway with Spring Cloud Gateway

### Angular Frontend
- Views for:
  - Listing, adding, editing, deleting departments
  - Viewing department details with list of policemen
  - Adding/removing policemen
- Angular routing and HTTP client used for API interaction

### Dockerized Deployment
- Docker containers for:
  - Police Department microservice
  - Policeman microservice
  - API Gateway
  - Angular frontend (served via NGINX)
- Configurable via environment variables
