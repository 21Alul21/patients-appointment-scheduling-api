## 📖 License
This project is licensed under the [MIT License](./LICENSE).

# MedManage API – Multi-Tenant Medical Management System

## Project Description
MedManage is a Spring Boot REST API for managing healthcare organizations. It provides a subscription based system, enabling subscribed organizations to effectively create, shedule and manage appointments between patients and doctors in an organization. It contains a free trial duration for organizations to test the application before choosing to pay for a subscription. 

## Functional Workflow
An organization (hospital) registers through to use the service through an organization admin, the admin either chooses to use the service initially in a trial mode (free without charge for a short duration) or outrightly pay for subscription over a preferred duration.

with subscription paid, organization admins can register patients and doctors to their organization. Registered patients can book appointments with doctors within an organization and doctors and get feedback on doctor availability.

## User Stories
- As an organization admin, I want to register my organization so that we can use the appointment system.
- As an organization admin, I want to select a trial or subscription plan so that I can evaluate or use the service long-term.
- As an organization admin, I want to register doctors and patients to our organization so that they can interact on the platform.
- As a patient, I want to book an appointment with a doctor in my organization so that I can receive medical attention.
- As a doctor, I want to receive notifications about appointment requests so that I can confirm or decline based on my availability.


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
- [x] Notifications via WebSocket

## Setup Instructions
Step 1: clone the project repository 
```bash
git clone https://github.com/21Alul21/patients-appointment-scheduling-api.git
```
Step 2: Navigate into the AppointmentManagement directory
```bash
cd AppointmentManagement
```

Step 3: Run the spring boot application 
```bash
./mvnw spring-boot:run
