# Journal App (Spring Boot + MongoDB)

A simple REST API for creating, reading, updating, and deleting journal entries.

## Tech Stack
- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data MongoDB
- Maven
- MongoDB

## Features
- Health check endpoint
- Create journal entry
- Fetch all journal entries
- Fetch journal entry by ID
- Update journal entry by ID
- Delete journal entry by ID

## Project Structure
```text
src/main/java/com/tanuj/journalApp
  |- JournalApplication.java
  |- controller/
  |   |- HealthCheck.java
  |   |- JournalEntryControllerV2.java
  |- entity/
  |   |- JournalEntry.java
  |- repository/
  |   |- JournalEntryRepo.java
  |- service/
      |- JournalEntryService.java
```

## Prerequisites
- Java 21 installed
- Maven installed (or use the included Maven Wrapper)
- MongoDB running locally on port `27017`

## Configuration
Current config is in `src/main/resources/application.properties`:

```properties
spring.application.name=journal App

spring.mongodb.host=localhost
spring.mongodb.port=27017
spring.mongodb.database=journaldb
```

Make sure a local MongoDB instance is available with database `journaldb`.

## Run the Application
Using Maven Wrapper (recommended):

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Or build + run:

```bash
./mvnw clean package
java -jar target/journalApp-0.0.1-SNAPSHOT.jar
```

## API Endpoints
Base URL: `http://localhost:8080`

### 1. Health Check
- Method: `GET`
- URL: `/health-check`
- Response: `"ok"`

Example:
```bash
curl http://localhost:8080/health-check
```

### 2. Get All Journal Entries
- Method: `GET`
- URL: `/journal/get-all`
- Success: `200 OK` with `List<JournalEntry>`
- Empty result: `404 Not Found`

Example:
```bash
curl http://localhost:8080/journal/get-all
```

### 3. Create Journal Entry
- Method: `POST`
- URL: `/journal/add-journal`
- Body:
```json
{
  "title": "My first note",
  "content": "Started working on my journal API."
}
```
- Success: `201 Created`
- Failure: `400 Bad Request`
- Note: `date` is set by server (`LocalDateTime.now()`).

Example:
```bash
curl -X POST http://localhost:8080/journal/add-journal \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"My first note\",\"content\":\"Started working on my journal API.\"}"
```

### 4. Get Journal Entry By ID
- Method: `GET`
- URL: `/journal/id/{myId}`
- Success: `200 OK`
- Not found: `404 Not Found`

Example:
```bash
curl http://localhost:8080/journal/id/65f0c8f6b0f1d54bb9f2c111
```

### 5. Update Journal Entry By ID
- Method: `PUT`
- URL: `/journal/id/{id}`
- Body (partial update supported for non-empty values):
```json
{
  "title": "Updated title",
  "content": "Updated content"
}
```
- Success: `200 OK`
- Not found: `404 Not Found`

Example:
```bash
curl -X PUT http://localhost:8080/journal/id/65f0c8f6b0f1d54bb9f2c111 \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"Updated title\",\"content\":\"Updated content\"}"
```

### 6. Delete Journal Entry By ID
- Method: `DELETE`
- URL: `/journal/id/{myId}`
- Success: `200 OK`
- If not present: `204 No Content`

Example:
```bash
curl -X DELETE http://localhost:8080/journal/id/65f0c8f6b0f1d54bb9f2c111
```

## JournalEntry Model
```json
{
  "id": "ObjectId",
  "title": "string",
  "content": "string",
  "date": "2026-02-14T12:30:00"
}
```

## Notes
- IDs are MongoDB `ObjectId`.
- The API currently does not include validation/authentication.
- If you move to production, add DTO validation, exception handling, and API docs (OpenAPI/Swagger).

