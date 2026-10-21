# spring-boot-flyway-mongodb

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-flyway-mongodb/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-flyway-mongodb/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen)
![MongoDB](https://img.shields.io/badge/MongoDB-8.0-green)

A sample Spring Boot application that manages **MongoDB schema and data migrations with Flyway**, using JavaScript
migration scripts, and exposes a simple REST API backed by Spring Data MongoDB.

## Tech Stack

| Component       | Version / Notes                                       |
|-----------------|-------------------------------------------------------|
| Java            | 25 (Temurin in CI)                                    |
| Spring Boot     | 4.1.1 (Web, Data MongoDB, Docker Compose, DevTools)   |
| Flyway          | `flyway-core` + `flyway-database-mongodb`             |
| MongoDB         | 8.0 (via `compose.yaml`)                              |
| Lombok          | 1.18.x                                                |
| Testing         | JUnit 5, `@DataMongoTest`, Testcontainers 2 (MongoDB) |
| Build           | Maven (wrapper included)                              |

## Prerequisites

- JDK 25
- Docker (for running MongoDB locally and for the Testcontainers-based tests)

## Getting Started

1. Clone the repository:

   ```shell
   git clone https://github.com/hendisantika/spring-boot-flyway-mongodb.git
   cd spring-boot-flyway-mongodb
   ```

2. Run the application:

   ```shell
   ./mvnw spring-boot:run
   ```

   Thanks to `spring-boot-docker-compose`, the MongoDB container defined in `compose.yaml` is started automatically.
   You can also start it yourself with `docker compose up -d`.

3. Call the API:

   ```shell
   curl http://localhost:8080/api/users
   ```

## Configuration

Main settings live in `src/main/resources/application.yml`:

```yaml
spring:
  flyway:
    enabled: true
    locations: classpath:db/migration
    sqlMigrationSuffixes:
      - ".js"
  mongodb:
    uri: mongodb://yu71:53cret@localhost:27017/users_db?authSource=admin
    database: users_db
    representation:
      uuid: standard
```

> **Spring Boot 4 note:** MongoDB connection properties moved from `spring.data.mongodb.*` to `spring.mongodb.*`, and
> the UUID representation must be set explicitly (`spring.mongodb.representation.uuid`) because `User` uses a `UUID` id.

## Database Migrations

Flyway migrations are MongoDB shell scripts in `src/main/resources/db/migration`:

| Script                                       | Description                                 |
|----------------------------------------------|---------------------------------------------|
| `V1_01012025_0800__create_initial_collections.js` | Creates the `users` and `orders` collections |
| `V2_01012025_0810__initial_collections_data.js`   | Seeds initial users                          |

Add new migrations following the `V<version>__<description>.js` naming convention.

## REST API

| Method | Endpoint     | Description    |
|--------|--------------|----------------|
| GET    | `/api/users` | List all users |

On startup, a `CommandLineRunner` also inserts a few sample users.

## Testing

```shell
./mvnw test
```

`SpringBootFlywayMongodbApplicationTests` is a `@DataMongoTest` that spins up a `mongo:8.0.1` container with
Testcontainers and wires it into Spring via `@ServiceConnection`. Docker must be running.

## CI/CD & Dependency Management

- **GitHub Actions** (`.github/workflows/maven.yml`): builds and tests on JDK 25 for every push and pull request to
  `main`, and submits the Maven dependency graph on pushes.
- **Dependabot** (`.github/dependabot.yml`):
  - Maven: daily, grouped by Spring Boot, Flyway, Testcontainers, Maven plugins, and other minor/patch updates.
  - GitHub Actions: weekly, all actions grouped into a single PR.
  - Docker Compose: weekly updates for the MongoDB image in `compose.yaml`.
  - PRs use conventional commit prefixes (`build(deps)`, `ci(deps)`, `build(docker)`) and are labelled automatically.

## Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: [@hendisantika34](https://t.me/hendisantika34)
