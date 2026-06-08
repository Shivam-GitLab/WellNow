# WellNow - Personalized Wellness & Activity Tracking Platform

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Status](https://img.shields.io/badge/Status-Active%20Development-brightgreen?style=for-the-badge)
![Repository](https://img.shields.io/badge/Repository-Public-blue?style=for-the-badge)

---


## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Architecture](#project-architecture)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Configuration](#configuration)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

---

## 🎯 Overview

**WellNow** is a comprehensive wellness and activity tracking platform built with **Spring Boot** and **Java**. It enables users to log their fitness activities, track health metrics, and receive personalized wellness recommendations based on their activity patterns and health data.

The platform provides a robust backend infrastructure for managing user authentication, activity logging, metric tracking, and intelligent recommendation generation to support healthier lifestyles.

### Core Purpose
- **Activity Tracking**: Log various fitness activities with detailed metrics
- **Health Monitoring**: Track calories burned, duration, and custom health metrics
- **Smart Recommendations**: Generate personalized wellness recommendations based on activity history
- **User Management**: Secure user authentication and profile management

---

## ✨ Features

### 🔐 Authentication & User Management
- User registration with email verification
- Secure password handling (BCrypt encryption recommended)
- JWT-based authentication (configuration in place)
- User profile management with timestamps

### 📊 Activity Tracking
- Support for multiple activity types (Running, Walking, Cycling, etc.)
- Flexible metrics storage using JSON format
- Duration and calorie tracking
- Activity timestamps (start time, creation time, update time)
- Associate activities with specific users

### 💡 Recommendation Engine
- Intelligent recommendation generation based on user activities
- Activity-specific recommendations
- User-specific wellness advice
- Automatic linking between activities and recommendations

### 🛡️ Error Handling
- Global exception handling across the application
- Validation error responses
- User-friendly error messages

### 🔄 Data Relationships
- One-to-Many relationships: User → Activities
- One-to-Many relationships: User → Recommendations
- One-to-Many relationships: Activity → Recommendations
- Cascade operations for data integrity

---

## 🛠️ Technology Stack

### Backend Framework
- **Spring Boot** - Application framework for rapid development
- **Spring Data JPA** - ORM and database access
- **Spring Web** - RESTful API development

### Database & Persistence
- **Jakarta Persistence API (JPA)** - Object-relational mapping
- **Hibernate** - ORM implementation with advanced features
  - JSON type support for flexible schema
  - Timestamp automation
  - Cascade and orphan removal

### Security & Authentication
- **JWT (JSON Web Tokens)** - Token-based authentication
- **Spring Security** - Authentication and authorization (configured)

### Utilities & Libraries
- **Lombok** - Reduce boilerplate code (Getters, Setters, Constructors, Builders)
- **Jackson** - JSON serialization/deserialization
- **Maven** - Build and dependency management

### Database Support
- Supports JSON data types (MySQL 5.7+, PostgreSQL, etc.)
- UUID-based primary keys for distributed systems
- Automatic timestamp management

---

## 🏗️ Project Architecture

### Layered Architecture Pattern

```
┌─────────────────────────────────────────────────────────┐
│                    REST Controllers                      │
│  (AuthController, RecommendationController)             │
└─────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────┐
│                    Service Layer                         │
│  (UserService, RecommendationService)                   │
└─────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────┐
│                   Repository Layer                       │
│  (JPA Repositories for Data Access)                     │
└─────────────────────────────────────────────────────────┘
                            ↓
┌─────────────────────────────────────────────────────────┐
│                     Database Layer                       │
│  (Relational Database with JSON support)                │
└─────────────────────────────────────────────────────────┘
```

### Module Organization

```
src/main/java/com/sm/wellnow/
├── auth/                          # Authentication & User Management
│   ├── controller/                # REST endpoints for auth
│   ├── service/                   # Business logic for authentication
│   ├── dto/                       # Data transfer objects
│   │   ├── register/              # Registration request/response
│   │   └── login/                 # Login request/response
│   └── entity/                    # User JPA entity
├── activity/                      # Activity Tracking Module
│   ├── controller/                # Activity endpoints
│   ├── service/                   # Activity business logic
│   ├── dto/                       # Activity DTOs
│   ├── entity/                    # Activity JPA entity
│   └── enums/                     # ActivityType enumeration
├── recommendation/                # Recommendation Engine Module
│   ├── controller/                # Recommendation endpoints
│   ├── service/                   # Recommendation logic
│   ├── dto/                       # Recommendation DTOs
│   └── entity/                    # Recommendation JPA entity
├── config/                        # Application Configuration
│   └── JwtUtils.java              # JWT token utilities
├── common/                        # Shared utilities
│   └── exceptions/                # Global exception handling
└── WellNowApplication.java        # Spring Boot entry point
```

---

## 🚀 Getting Started

### Prerequisites

- **Java 17+** (Spring Boot 3.x compatibility)
- **Maven 3.8+** (Build tool)
- **Database** (MySQL 5.7+, PostgreSQL, or similar with JSON support)
- **Git** (Version control)

### Installation Steps

#### 1. Clone the Repository
```bash
git clone https://github.com/Shivam-GitLab/WellNow.git
cd WellNow
```

#### 2. Configure Database Connection
Edit `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/wellnow_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

# JWT Configuration
app.jwt.secret=your_secret_key_here_min_256_bits
app.jwt.expiration=86400000
```

#### 3. Build the Project
```bash
mvn clean install
```

#### 4. Run the Application
```bash
mvn spring-boot:run
```

The server will start at: `http://localhost:8080`

#### 5. Verify Installation
```bash
curl http://localhost:8080/actuator/health
```

---

## 📡 API Endpoints

### Authentication Endpoints

#### Register a New User
```http
POST /api/auth/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "securePassword123",
  "firstName": "John",
  "lastName": "Doe"
}
```

**Response (201 Created):**
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "createdAt": "2026-04-30T10:30:00Z"
}
```

#### Login User
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "securePassword123"
}
```

**Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe"
  }
}
```

### Activity Endpoints

#### Log an Activity
```http
POST /api/activity/log
Content-Type: application/json
Authorization: Bearer {token}

{
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "activityType": "RUNNING",
  "duration": 45,
  "caloriesBurned": 520,
  "startTime": "2026-04-30T07:00:00Z",
  "additionalMetrics": {
    "distance": 7.5,
    "pace": "6:00 min/km",
    "avgHeartRate": 145,
    "terrain": "outdoor"
  }
}
```

**Response (200 OK):**
```json
{
  "id": "660f9511-f40c-52e5-b827-557766551111",
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "activityType": "RUNNING",
  "duration": 45,
  "caloriesBurned": 520,
  "startTime": "2026-04-30T07:00:00Z",
  "additionalMetrics": {
    "distance": 7.5,
    "pace": "6:00 min/km",
    "avgHeartRate": 145,
    "terrain": "outdoor"
  },
  "createdAt": "2026-04-30T10:30:00Z"
}
```

### Recommendation Endpoints

#### Generate Recommendation
```http
POST /api/recommendation/generate
Content-Type: application/json
Authorization: Bearer {token}

{
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "activityId": "660f9511-f40c-52e5-b827-557766551111",
  "recommendationType": "IMPROVEMENT"
}
```

**Response (200 OK):**
```json
{
  "id": "770g0622-g51d-63f6-c938-668877662222",
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "activityId": "660f9511-f40c-52e5-b827-557766551111",
  "recommendation": "Increase running frequency to 4 times per week for better cardiovascular fitness",
  "confidence": 85.5,
  "createdAt": "2026-04-30T10:30:00Z"
}
```

#### Get User Recommendations
```http
GET /api/recommendation/user/{userId}
Authorization: Bearer {token}
```

**Response (200 OK):**
```json
[
  {
    "id": "770g0622-g51d-63f6-c938-668877662222",
    "recommendation": "Increase running frequency...",
    "confidence": 85.5
  }
]
```

#### Get Activity Recommendations
```http
GET /api/recommendation/activity/{activityId}
Authorization: Bearer {token}
```

---

## 🗄️ Database Schema

### Users Table

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | UUID | PRIMARY KEY | Unique user identifier |
| email | VARCHAR | UNIQUE, NOT NULL | User's email address |
| password | VARCHAR | NOT NULL | Encrypted password |
| firstName | VARCHAR | | User's first name |
| lastName | VARCHAR | | User's last name |
| createdAt | TIMESTAMP | NOT NULL | Account creation time |
| updatedAt | TIMESTAMP | NOT NULL | Last update time |

### Activities Table

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | UUID | PRIMARY KEY | Unique activity identifier |
| user_id | UUID | FOREIGN KEY | References Users table |
| type | VARCHAR | NOT NULL | Activity type (ENUM) |
| duration | INT | | Duration in minutes |
| caloriesBurned | INT | | Calories burned |
| startTime | TIMESTAMP | | Activity start time |
| additionalMetrics | JSON | | Flexible metrics (distance, heart rate, etc.) |
| createdAt | TIMESTAMP | NOT NULL | Record creation time |
| updatedAt | TIMESTAMP | NOT NULL | Last update time |

### Recommendations Table

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | UUID | PRIMARY KEY | Unique recommendation ID |
| user_id | UUID | FOREIGN KEY | References Users table |
| activity_id | UUID | FOREIGN KEY | References Activities table |
| recommendation | TEXT | NOT NULL | Recommendation text |
| confidence | DECIMAL | | Confidence score (0-100) |
| createdAt | TIMESTAMP | NOT NULL | Generation time |
| updatedAt | TIMESTAMP | NOT NULL | Last update time |

### Entity Relationships

```
User (1) ──→ (M) Activity
User (1) ──→ (M) Recommendation
Activity (1) ──→ (M) Recommendation
```

---

## ⚙️ Configuration

### Application Properties

Create/edit `src/main/resources/application.properties`:

```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/

# Application Name
spring.application.name=WellNow

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/wellnow_db
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true

# Logging Configuration
logging.level.root=INFO
logging.level.com.sm.wellnow=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

# JWT Configuration
app.jwt.secret=your_very_long_secret_key_min_256_bits_for_HS256
app.jwt.expiration=86400000

# Jackson Configuration
spring.jackson.serialization.write-dates-as-timestamps=false
spring.jackson.time-zone=UTC
```

### Application YAML Alternative

Or create `src/main/resources/application.yml`:

```yaml
server:
  port: 8080
  servlet:
    context-path: /

spring:
  application:
    name: WellNow
  datasource:
    url: jdbc:mysql://localhost:3306/wellnow_db
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
        format_sql: true

logging:
  level:
    root: INFO
    com.sm.wellnow: DEBUG

app:
  jwt:
    secret: your_very_long_secret_key_min_256_bits
    expiration: 86400000
```

---

## 📂 Project Structure

```
WellNow/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/sm/wellnow/
│   │   │       ├── WellNowApplication.java          # Entry point
│   │   │       ├── auth/
│   │   │       │   ├── controller/
│   │   │       │   │   └── AuthController.java      # Auth endpoints
│   │   │       │   ├── service/
│   │   │       │   │   └── UserService.java         # User business logic
│   │   │       │   ├── dto/
│   │   │       │   │   ├── register/
│   │   │       │   │   │   ├── RegisterRequest.java
│   │   │       │   │   │   └── RegisterResponse.java
│   │   │       │   │   └── login/
│   │   │       │   │       ├── LoginRequest.java
│   │   │       │   │       └── LoginResponse.java
│   │   │       │   └── entity/
│   │   │       │       └── User.java                # User JPA entity
│   │   │       ├── activity/
│   │   │       │   ├── controller/
│   │   │       │   │   └── ActivityController.java
│   │   │       │   ├── service/
│   │   │       │   │   └── ActivityService.java
│   │   │       │   ├── dto/
│   │   │       │   │   └── ActivityRequest.java
│   │   │       │   ├── entity/
│   │   │       │   │   └── Activity.java
│   │   │       │   └── enums/
│   │   │       │       └── ActivityType.java
│   │   │       ├── recommendation/
│   │   │       │   ├── controller/
│   │   │       │   │   └── RecommendationController.java
│   │   │       │   ├── service/
│   │   │       │   │   └── RecommendationService.java
│   │   │       │   ├── dto/
│   │   │       │   │   └── RecommendationRequest.java
│   │   │       │   └── entity/
│   │   │       │       └── Recommendation.java
│   │   │       ├── config/
│   │   │       │   └── JwtUtils.java                # JWT utilities
│   │   │       └── common/
│   │   │           └── exceptions/
│   │   │               └── GlobalExceptionHandler.java
│   │   └── resources/
│   │       ├── application.properties               # Config file
│   │       └── application.yml                      # YAML config (alternative)
│   └── test/                                         # Test classes
├── pom.xml                                           # Maven configuration
├── README.md                                         # This file
└── .gitignore                                        # Git ignore rules
```

---

## 🧪 Testing

### Running Tests
```bash
mvn test
```

### Running Specific Test Class
```bash
mvn test -Dtest=UserServiceTest
```

### Running with Coverage
```bash
mvn test jacoco:report
```

---

## 🔒 Security Considerations

⚠️ **Important Security Notes:**

1. **Password Encryption**: Passwords must be encrypted using BCrypt (Spring Security)
   ```java
   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }
   ```

2. **JWT Security**: 
   - Use a strong, random secret key (min 256 bits for HS256)
   - Store secrets in environment variables, not in code
   - Set appropriate expiration times

3. **HTTPS**: Always use HTTPS in production

4. **CORS Configuration**: Configure CORS appropriately for your frontend

5. **Input Validation**: All DTOs should have validation annotations

---

## 🚦 Status & Roadmap

### ✅ Completed
- [x] Core entity models (User, Activity, Recommendation)
- [x] Authentication controller setup
- [x] Activity tracking structure
- [x] Recommendation engine foundation
- [x] Global exception handling
- [x] JPA relationships and cascading

### 🔄 In Progress
- [ ] Complete authentication logic (login endpoint)
- [ ] Implement activity logging service
- [ ] Develop recommendation algorithm
- [ ] Add unit and integration tests
- [ ] API documentation with Swagger/SpringDoc

### 📋 Planned
- [ ] User profile management endpoints
- [ ] Activity analytics and statistics
- [ ] Advanced recommendation filtering
- [ ] Social features (friend tracking, challenges)
- [ ] Mobile app integration
- [ ] Real-time notifications
- [ ] Machine learning-based predictions

---

## 🤝 Contributing

Contributions are welcome! Here's how to contribute:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Contribution Guidelines
- Follow Spring Boot best practices
- Write meaningful commit messages
- Add tests for new features
- Update documentation as needed
- Ensure code follows project naming conventions

---

## 📝 License

This project is currently **unlicensed**. You are free to use it for learning and development purposes. If you plan to use this in production, consider adding an appropriate license.

---

## 📞 Support & Contact

- **Author**: Shivam-GitLab
- **Repository**: https://github.com/Shivam-GitLab/WellNow
- **Issues**: [GitHub Issues](https://github.com/Shivam-GitLab/WellNow/issues)
- **Discussions**: [GitHub Discussions](https://github.com/Shivam-GitLab/WellNow/discussions)

---

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Hibernate/JPA for ORM capabilities
- Lombok for reducing boilerplate code
- Maven for build management

---

**Last Updated**: 2026-04-30  
**Repository**: [Shivam-GitLab/WellNow](https://github.com/Shivam-GitLab/WellNow)
