# Kotkogram REST API

Kotkogram is a social media app for cats, designed as a learning project. It allows users to create, read, update, and delete posts about cats, complete with images and a comment section. The REST API is built with Java, Spring Boot, and PostgreSQL.

## Features

- User Authentication
- Posts CRUD operations
- Comments CRUD operations

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 21
- Maven
- PostgreSQL

### Installing

1. Clone the repository

```bash
git clone https://github.com/mariovyord/kotkogram.git
```

2. Navigate to the project directory

```bash
cd kotkogram
```

3. Build the project with Maven

```bash
mvn clean install
```

4. Run the project using Spring Boot Maven plugin

```bash
mvn spring-boot:run
```

The server will start on http://localhost:8080.
