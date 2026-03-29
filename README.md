# Kallisto Test Project

A Spring Boot 4.0.5 application built with Kotlin 2.2.21, designed to manage user data with a RESTful API and persistent storage using H2 database.

## 🚀 Features

- **Spring Boot 4.0.5**: Modern Java framework with Kotlin support.
- **Data Persistence**: Spring Data JPA with H2 database.
- **API Documentation**: Integrated Swagger UI for API exploration.
- **Docker Support**: Containerized build and execution via Docker and Docker Compose.
- **Validation**: Bean validation for API requests.

---

## 🏗️ Project Structure

The project follows a standard Spring Boot/Kotlin architecture:

```text
kallisto-test/
├── src/main/kotlin/com/kallisto/kallistotest/
│   ├── application/           # Business logic and services (e.g., UserService)
│   ├── domain/                # Entities and repositories (e.g., User, UserRepository)
│   ├── mapper/                # DTO to Entity mappers
│   ├── web/                   # Controllers and exception handlers
│   │   └── dto/               # Data Transfer Objects
│   └── KallistoTestApplication.kt # Main application entry point
├── src/main/resources/
│   ├── static/                # Static web assets
│   └── application.yaml       # Application configuration
├── Dockerfile                 # Multi-stage Docker build configuration
├── docker-compose.yml         # Container orchestration for local development
├── build.gradle.kts           # Gradle build script (Kotlin DSL)
└── settings.gradle.kts        # Gradle project settings
```

---

## 🛠️ Build and Run (Local)

### Prerequisites

- Java 21 JDK
- Gradle (or use the provided wrapper)

### Run with Gradle

To start the application locally:

```bash
./gradlew bootRun
```

The application will be available at `http://localhost:8080`.

---

## 🐳 Docker Configuration

The project includes a multi-stage `Dockerfile` and `docker-compose.yml` for simplified deployment and development.

### Dockerfile Breakdown

- **Build Stage**: Uses `gradle:9.4.1-jdk21` to build the application JAR.
- **Runtime Stage**: Uses `eclipse-temurin:21` to run the compiled JAR.
- **Port**: Exposes port `8080`.

### Run with Docker Compose

To build and start the application container:

```bash
docker-compose up --build
```

- **Admin Panel Access**: `http://localhost:8080`
- **Data Persistence**: A local volume `./data` is mapped to persist the H2 database file.
- **Environment**: The database URL is overridden in `docker-compose.yml` to use a file-based H2 database for persistence across restarts.

---

## 📖 API Documentation

The project automatically generates API documentation using SpringDoc OpenAPI.

- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **API Docs**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---
