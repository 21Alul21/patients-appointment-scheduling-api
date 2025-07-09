## 📖 License
This project is licensed under the [MIT License](./LICENSE).

# MedManage API – Multi-Tenant Medical Management System

## Project Description
MedManage is a Spring Boot REST API for managing healthcare organizations. It provides a subscription based system, enabling subcribed organizations to effectively create, shedule and manage appointments between patients and doctors — with multi-organization (tenant) support. It contains a trial organizations to test the application before choosing to pay for a subscription. 

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

## Features (In Progress)
- [x] Create and manage organizations
- [x] Admin login per organization
- [x] Register doctors and patients
- [x] Book and manage appointments
- [ ] Subscription payment through the organization admin
- [ ] Notifications via WebSocket (coming soon)

## Setup Instructions
```bash
clone this git repository
git clone https://github.com/21Alul21/patients-appointment-scheduling-api.git 
cd AppointmentManagement
./mvnw spring-boot:run
