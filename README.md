## 📖 License
This project is licensed under the [MIT License](./LICENSE).

# MedManage API – Multi-Tenant Medical Management System

## Project Description
MedManage is a Spring Boot REST API for managing healthcare organizations, by providing an efficient solution for creating and manging appointments between patients and doctors — with multi-organization (tenant) support.

## Project Status
**In development** – key features are being implemented.

## Tech Stack
- Java + Spring Boot
- JPA/Hibernate
- PostgreSQL
- REST APIs
- Docker
- JWT Auth
- WebSocket

## 🔧 Features (In Progress)
- [x] Create and manage organizations
- [x] Admin login per organization
- [x] Register doctors and patients
- [x] Book and manage appointments
- [ ] Notifications via WebSocket (coming soon)

## 🛠 Setup Instructions
```bash
git clone https://github.com/your-username/medmanage-api.git
cd medmanage-api
./mvnw spring-boot:run
