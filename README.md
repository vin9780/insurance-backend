# 🛡️ Insurance Policy Management System - Backend

A full-stack enterprise-grade Insurance Policy Management System built with Spring Boot, Java, and MySQL.

## 🚀 Tech Stack

| Technology | Version |
|---|---|
| Java | 17 |
| Spring Boot | 3.5.14 |
| Hibernate/JPA | 5.6.x |
| MySQL | 8.0 |
| Maven | 3.8.6 |
| JUnit | 5 |
| Mockito | 4.x |
| Docker | 20.x |
| Swagger UI | 2.1.0 |

## 📦 Features

- ✅ Customer Management (CRUD)
- ✅ Policy Management (CRUD)
- ✅ Premium Calculation Business Logic
- ✅ REST APIs with proper HTTP methods
- ✅ JPA/Hibernate ORM with MySQL
- ✅ Unit & Integration Testing (JUnit + Mockito)
- ✅ Factory & Observer Design Patterns
- ✅ Swagger UI API Documentation
- ✅ Docker & Docker Compose
- ✅ GitHub Actions CI/CD Pipeline
- ✅ Qodana Code Quality Analysis

## 🗄️ Database Schema

```sql
-- Customers Table
CREATE TABLE customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(255),
    age INT
);

-- Policies Table
CREATE TABLE policies (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    policy_number VARCHAR(255) UNIQUE NOT NULL,
    policy_type VARCHAR(255) NOT NULL,
    premium DOUBLE,
    status VARCHAR(255),
    start_date DATE,
    end_date DATE,
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);
```

## 🔧 How to Run Locally

### Prerequisites
- Java 17
- MySQL 8.0
- Maven 3.8.6

### Steps
```bash
# Clone the repository
git clone https://github.com/vin9780/insurance-backend.git

# Navigate to project
cd insurance-backend

# Configure MySQL in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/insurance_db
spring.datasource.username=root
spring.datasource.password=yourpassword

# Run the application
./mvnw spring-boot:run
```

### Using Docker
```bash
docker-compose up
```

## 📖 API Documentation
Once running, access Swagger UI at: http://localhost:8080/swagger-ui/index.html

## 🔗 API Endpoints

### Customer APIs
| Method | Endpoint | Description |
|---|---|---|
| GET | /api/customers | Get all customers |
| GET | /api/customers/{id} | Get customer by ID |
| POST | /api/customers | Create customer |
| PUT | /api/customers/{id} | Update customer |
| DELETE | /api/customers/{id} | Delete customer |

### Policy APIs
| Method | Endpoint | Description |
|---|---|---|
| GET | /api/policies | Get all policies |
| GET | /api/policies/{id} | Get policy by ID |
| POST | /api/policies/customer/{id} | Create policy |
| PUT | /api/policies/{id}/status | Update policy status |
| DELETE | /api/policies/{id} | Delete policy |

## 🧪 Running Tests
```bash
./mvnw test
```

## 🏗️ Design Patterns Used
- **Singleton** — Spring Service beans
- **Factory** — PolicyFactory for creating policy types
- **Observer** — Event listener for policy creation events

## 👨‍💻 Author
Vineel Ragireddy — [GitHub](https://github.com/vin9780)
