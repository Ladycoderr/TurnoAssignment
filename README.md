# TurnoAssignment
This project is a **Loan Origination System (LOS)** designed to handle loan applications through automated and manual workflows. It supports Loan Creation,Loan approval by agent-manager review, mock notifications, and scalable RESTful APIs.

### Prerequisites

- Java 17
- Maven
- MySQL

### 1. Clone the Repository

```bash
git clone https://github.com/Ladycoderr/loan-origination-system.git
```
## 🚀 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Hibernate
- Maven
- Lombok
- Postman

Run Steps
  1. Start MySQL and create a database:
     ``` CREATE DATABASE loan_origination_system;```
  2. Configure application properties(Update your MySQL credentials in src/main/resources/application.properties):
     ```
       spring.datasource.url = jdbc:mysql://localhost:3306/LoanService
       spring.datasource.username=your_mysql_username
       spring.datasource.password=your_mysql_password
     ```
  3. Build the project using Maven:
     ``` mvn clean install```
  4. Run the application:
     ```mvn spring-boot:run```
  5. Access the application:
     ```API base URL: http://localhost:9090/api/v1```
  
  

  
API COLLECTION LINK : https://api.postman.com/collections/29708201-e30ab7d3-b1ca-47ba-ab11-da59910f9163?access_key=PMAT-01JXAXCABVJQTFG9TV1ZQT5PH9







