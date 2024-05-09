
# Determining  Fraudulent Return orders in Reverse Supply Chain

## ReturnOrderSchedulerService

### Overview
ReturnOrderSchedulerService is a Java Maven-based application that manages the scheduling of return order data processing and sends this data to Kafka for further operations. It is essential for automating the process of return order management in the reverse supply chain.

### Setup and Installation
**Prerequisites**:
- Java JDK 11 or higher
- Maven for dependency management
- Kafka and Zookeeper setup

**Configuration**:
Update the `application.properties` file with your database and Kafka configuration details.

**Running the Service**:
To start the service, run the following command in the terminal:
```bash
mvn spring-boot:run
```

## ROFrauddetectionFlow

### Overview
ROFrauddetectionFlow is a Java Maven application that handles the fraud detection flow, assessing whether return orders are fraudulent based on predefined criteria.

### Setup and Installation
**Prerequisites**:
- Java JDK 11 or higher
- Maven for dependency management

**Configuration**:
Configure the database and fraud detection API endpoint in the `application.properties`.

**Running the Service**:
Execute the following commands to run the application:
```bash
mvn clean install
mvn spring-boot:run
```

## ROFraudDataAnalyser

### Overview
ROFraudDataAnalyser is a Java Spring Boot application with Maven and JPA for REST API operations related to return order data analysis.

### Setup and Installation
**Prerequisites**:
- Java JDK 11 or higher
- Maven for building the project
- Spring Boot for REST API

**Configuration**:
Ensure all database configurations are set in `application.properties`.

**Running the Service**:
Start the application with:
```bash
mvn spring-boot:run
```

## Fraud Detection Service (Python Random Forest Algorithm)

### Overview
This service utilizes a Flask application to host an API that uses a trained Random Forest model to predict fraud in return orders.

### Setup and Installation
**Prerequisites**:
- Python 3.8 or higher
- Required Python libraries: Flask, Pandas, Scikit-learn, which can be installed via pip.

**Configuration**:
Configure the Flask application settings in `app.py`.

**Running the Service**:
To run the Flask application:
```bash
python appFlask.py
```
