# 🎓 EduConnect LMS Backend

> A production-grade backend system for a modern Learning Management Platform — built with **Spring Boot 3**, **MySQL**, and **Spring Security**.  
> Designed for scalability, modularity, and real-world use cases including course management, enrollment tracking, auto-grading, and dynamic certificate generation.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 🧭 Overview

**EduConnect** is a complete backend system for managing academic operations digitally.  
It provides REST APIs for:
- Course creation and management  
- Student registration and enrollment  
- Quiz and scoring automation  
- Certificate generation (PDF with iText)  
- Reporting and batch jobs for analytics  

The architecture follows clean layering — **Controller → Service → Repository → Entity** — making it fully maintainable and easy to scale.

---

## ⚙️ System Architecture

Client (React / Postman)
│
▼
REST Controller (API Layer)
│
▼
Service Layer (Business Logic)
│
▼
Repository Layer (Spring Data JPA)
│
▼
DB (MySQL)


🧩 **Additional Modules**
- **Security** — Spring Security with role-based access (ADMIN, STUDENT)
- **PDF Engine** — iText-based PDF certificates with unique IDs
- **Batch Reporting** — Spring Batch jobs for analytics or performance reports

---

## 🚀 Core Features

| Module | Description | Technology |
|--------|--------------|-------------|
| 🧑‍🎓 **Student Management** | CRUD for student profiles | Spring Data JPA |
| 📘 **Course Management** | Create, edit, and archive courses | REST APIs |
| 🔗 **Enrollment** | Enroll students with validations and transactional integrity | Spring Transaction |
| 🧠 **Quiz Engine** | Auto-grade logic with scoring algorithms | Custom Logic |
| 🏆 **Certificate Generation** | PDF certificates with iText + QR code | iText 7 |
| 🔒 **Security Layer** | Role-based access control (ADMIN/STUDENT) | Spring Security |
| 📊 **Reports & Batches** | Automated reports on enrollments and scores | Spring Batch |

---

## 🛠️ Tech Stack

- **Language**: Java 17  
- **Framework**: Spring Boot 3.1.5  
- **Database**: MySQL 8  
- **ORM**: Hibernate / JPA  
- **Security**: Spring Security (BCrypt)  
- **PDF Engine**: iText PDF  
- **Build Tool**: Maven  
- **API Docs**: SpringDoc OpenAPI 3 (Swagger UI)  

---

## 🧩 Project Structure

educonnect/
├── src/main/java/com/educonnect/
│ ├── controller/ # REST endpoints
│ ├── service/ # Business logic
│ ├── repository/ # Data access
│ ├── entity/ # JPA entities
│ ├── security/ # Auth config
│ ├── util/ # Utility & PDF generation
│ └── EduConnectApp.java # Main entry point
│
└── src/main/resources/
├── application.yml
└── schema.sql


---

## ⚙️ Installation & Setup

### 1️⃣ Clone Repository
```bash
git clone https://github.com/VINAYAGAMP/educonnect-lms.git
cd educonnect-lms
Create MySQL DB:

CREATE DATABASE educonnect;

Update your credentials:

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/educonnect
    username: root
    password: password

    mvn clean install
mvn spring-boot:run

✅ Runs on: http://localhost:8080

🔐 Authentication


Username: admin


Password: adminpass


Roles: ADMIN / STUDENT


🔸 Basic Auth used for demo — replace with JWT for production.

🧠 API Reference
👥 Student API
MethodEndpointDescriptionRoleGET/api/studentsList all studentsADMINPOST/api/studentsAdd new studentADMINGET/api/students/{id}Get student by IDADMIN, STUDENTPUT/api/students/{id}Update studentADMINDELETE/api/students/{id}Delete studentADMIN
📘 Course API
MethodEndpointDescriptionRoleGET/api/coursesView all coursesALLPOST/api/coursesCreate courseADMINPUT/api/courses/{id}Update courseADMINDELETE/api/courses/{id}Delete courseADMIN
🔗 Enrollment API
MethodEndpointDescriptionRoleGET/api/enrollmentsAll enrollmentsADMINPOST/api/enrollmentsEnroll studentADMINGET/api/enrollments/{id}Get enrollmentADMINPUT/api/enrollments/{id}Update statusADMINDELETE/api/enrollments/{id}Remove enrollmentADMIN
🏆 Certificate API
MethodEndpointDescriptionRoleGET/api/certificates/generateGenerate completion certificateADMIN/STUDENT
Parameters:


studentName


courseTitle


semester



📜 Example Request
Generate Certificate
curl -X GET -u admin:adminpass \
"http://localhost:8080/api/certificates/generate?studentName=John%20Doe&courseTitle=Spring%20Boot&semester=Fall%202024" \
--output certificate.pdf


🧾 Database Schema
student(id, name, email, department)
course(id, title, description, instructor)
enrollment(id, student_id, course_id, grade, status)
user(id, username, password)
user_roles(user_id, role)


🧩 Design Principles


SOLID Architecture: Single-responsibility and separation of concerns


Transactional Safety: Enrollment and grading use @Transactional


DTO Pattern: Data isolation between API and persistence


Error Handling: Centralized exception handler using @ControllerAdvice


Scalability Ready: Stateless services + DB connection pooling


Extensibility: Can evolve into a microservice-based architecture



🧠 Common Issues
IssueCauseFixDatabase not connectingWrong credentialsUpdate application.ymlLombok errorsIDE missing pluginInstall Lombok in IntelliJ/EclipseAuth failsWrong passwordUse admin/adminpassPDF not generatingiText jar missingRun mvn clean install

📊 API Documentation


Swagger UI: http://localhost:8080/swagger-ui.html


OpenAPI Spec: http://localhost:8080/v3/api-docs



🤝 Contributing


Fork repository


Create feature branch


Commit your changes


Push and open PR


Example:
git checkout -b feature/add-jwt
git commit -m "Add JWT Authentication"
git push origin feature/add-jwt


👨‍💻 Author
Vinayagam
vinayagampoomalai@gmail.com
Java Backend Developer 

📝 License
Licensed under the MIT License — free for personal and academic use.


⚡ EduConnect LMS — where education meets automation.

