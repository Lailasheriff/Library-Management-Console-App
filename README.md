# Library Management Console App

## Project Overview

This is a Java based Library Management System containerized using Docker.
It supports Admin and Regular User roles.
The system uses Hibernate ORM with MySQL database and is orchestrated using Docker Compose.

---

## Prerequisites
install the following:
- https://www.docker.com/
- https://docs.docker.com/compose/

---

## Setup Instructions

1. Clone the repo:
    ```bash
    git clone https://github.com/Lailasheriff/Library-Management-Console-App library-management-app
    cd library-management-app
    ```
2. clean previous state
   ```bash
   docker-compose down -v 
   ```
   if you want to preserve volumes run this instead:
   ```bash
   docker-compose down 
   ```
3. Start all services:
    ```bash
    docker-compose up --build
    ```

---

## Environment Variable Descriptions
All configurations is managed using .env file:

```env
DB_HOST=db 
DB_PORT=3306
DB_NAME=library_db 
DB_USER=root 
DB_PASSWORD=your password
```
---
## Access Instructions
#### Adminer access
open: http://localhost:8080

- System: MySql/MariaDB
- Server: db(from DB_HOST)
- Username: root(from DB_USER)
- Password: your password(from DB_PASSWORD)
- Database: library_db(from DB_NAME)

#### Application usage
while app is running open a new terminal and run the following:
   ```bash
    docker exec -it library-app sh
   ```
this puts you inside the container then:
   ```bash
    java -jar app.jar
   ```
---
## Troubleshooting Tips
1. error with wait-for-it.sh run:
   ```bash
    chmod +x wait-for-it.sh
   ```
2. port 3306 already in use run:
   ```bash
    sudo lsof -i :3306
   ```
   get PID then run:
   ```bash
    sudo kill -9 <PID>
   ```
   if it didn't work then probably mysql is using it run:
   ```bash
    sudo /usr/local/mysql/support-files/mysql.server stop
   ```
3. The container name is already in use run:
   ```bash
   docker stop container_name
   docker rm container_name
   ```

