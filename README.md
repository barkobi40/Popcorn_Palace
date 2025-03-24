<p align="center">
  <img src="https://cdn-icons-png.flaticon.com/512/2935/2935125.png" width="120" alt="Popcorn Icon" />
</p>

<h1 align="center">Popcorn Palace – Movie Ticket Booking System</h1>

<p align="center">
  <em>A RESTful API for managing movies, showtimes, seats, and bookings using Java Spring Boot & PostgreSQL.</em>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-2.7.0-brightgreen" />
  <img src="https://img.shields.io/badge/Java-17-blue" />
  <img src="https://img.shields.io/badge/PostgreSQL-Database-blue" />
  <img src="https://img.shields.io/badge/License-MIT-lightgrey" />
  <img src="https://img.shields.io/badge/Status-Completed-brightgreen" />
</p>

---

##  Overview
Popcorn Palace is a backend system that handles:
-  Movie management
-  Showtime scheduling
-  Seat booking
-  Ticket reservation and customer handling

Built as part of a backend development assignment using Spring Boot and Dockerized PostgreSQL.

---

##  Features

### Movies API
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/movies` | GET | Fetch all movies |
| `/movies` | POST | Add new movie |
| `/movies/{id}` | PUT | Update movie |
| `/movies/{id}` | DELETE | Delete movie |

### Showtimes API
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/showtimes` | POST | Add new showtime (no overlaps allowed) |
| `/showtimes/{id}` | GET | Get showtime by ID |
| `/showtimes/{id}` | PUT | Update showtime |
| `/showtimes/{id}` | DELETE | Delete showtime |


### Seats API
| Endpoint | Method | Description |
|----------|--------|-------------|
| `/seats/{showtimeId}` | GET | Get all seats for a showtime |

---

##  Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL (via Docker Compose)
- Swagger (springdoc-openapi)
- JUnit & Mockito

---

##  Installation & Run

### Prerequisites:
- Java 17+
- Maven
- Docker + Docker Compose

### Start PostgreSQL:
```bash
docker-compose up -d
```

### Run App:
```bash
mvn spring-boot:run
```

Swagger available at:
```
http://localhost:8080/swagger-ui/index.html
```

---

##  Project Structure
```
src/
├── controller/
├── service/
├── model/
├── repository/
├── dto/
├── config/  ➜ Swagger, Exception handlers
├── test/
```

---

##  API Documentation (Swagger)
After running the project, go to: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) ✨
