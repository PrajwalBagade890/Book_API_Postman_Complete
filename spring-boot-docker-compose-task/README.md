# Book API – Postman Collection Testing

This project is the existing Spring Boot + MySQL + Docker Compose + JSON request logging project, updated from Product API to Book API. It includes a Postman collection with automated CRUD tests.

## Requirements
- Java 17+ and Maven (only for running outside Docker)
- Docker Desktop with Docker Compose
- Postman

## Run with Docker
From this project root, run:

```bash
docker compose up --build
```

Wait until Spring Boot starts on port 8080. The database is persisted in the `mysql_data` Docker volume.

## Book API endpoints
| Method | URL | Expected status |
|---|---|---:|
| GET | `http://localhost:8080/api/books` | 200 |
| POST | `http://localhost:8080/api/books` | 201 |
| GET | `http://localhost:8080/api/books/{id}` | 200 |
| PUT | `http://localhost:8080/api/books/{id}` | 200 |
| DELETE | `http://localhost:8080/api/books/{id}` | 204 |

### Book JSON example
```json
{
  "title": "The Alchemist",
  "author": "Paulo Coelho",
  "price": 299.00
}
```

## Run Postman tests
1. Open Postman.
2. Click **Import** and select `postman/Book_API.postman_collection.json`.
3. Ensure the Spring Boot application is running at `http://localhost:8080`. The collection's `baseUrl` variable is already configured.
4. Open the **Book API** collection and click **Run** (Collection Runner).
5. Keep the request order unchanged: GET all → POST → GET by ID → PUT → DELETE.
6. Click **Run Book API**. The POST test stores the created ID in the collection variable `bookId`, which later requests use.
7. Confirm all five requests and their tests pass.

The collection creates one test book and deletes it at the end. Run the collection again to repeat the test.

## Export collection JSON
The ready-to-import collection is already included at `postman/Book_API.postman_collection.json`. To export from Postman: collection menu (`...`) → **Export** → Collection v2.1 → **Export**. Save the JSON in the `postman` folder, replacing the existing file if desired.

## Verify JSON request logs
Each HTTP request is logged as a JSON event with method, path, status, and duration. View logs with:

```bash
docker compose logs -f app
```

## Stop containers
```bash
docker compose down
```
To also delete database data (not normally required):
```bash
docker compose down -v
```

## GitHub commit
Run from the project root:
```bash
git add .
git commit -m "Add Book API Postman CRUD collection tests"
git push
```
