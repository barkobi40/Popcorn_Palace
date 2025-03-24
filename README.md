# 🍿 Popcorn Palace - Movie Ticket Booking System (Spring Boot)

This project is a RESTful backend application for booking movie tickets, built using **Spring Boot** and **PostgreSQL**.

---

## 🎯 Features

### 🎥 Movie Management
- `POST /movies` – Add a new movie
- `GET /movies` – Get all movies
- `PUT /movies/{id}` – Update movie details
- `DELETE /movies/{id}` – Delete a movie

### 🕒 Showtime Management
- `POST /showtimes` – Add a showtime for a movie
- `GET /showtimes/{id}` – Fetch a showtime by ID
- `PUT /showtimes/{id}` – Update showtime details
- `DELETE /showtimes/{id}` – Delete a showtime  
⚠️ Prevents **overlapping showtimes** in the same theater

### 🎟️ Seat & Booking Management
- `POST /seats/{showtimeId}` – Add a seat to a showtime
- `GET /seats/{showtimeId}` – Get all seats for a showtime
- `POST /bookings/{seatId}/{customerId}` – Book a seat for a customer  
⚠️ Prevents **double booking** of seats
- `DELETE /bookings/{bookingId}` – Cancel a booking

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- Docker & Docker Compose
- Swagger/OpenAPI for documentation
- JUnit & Mockito for testing

---

## 📚 Documentation

- Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

