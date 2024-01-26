# Microservices Patterns Implementation

This repository contains the implementation of various microservices patterns in a sample microservices architecture. Each pattern is designed to address specific concerns related to observability, integration, database management, decomposition, and more.

## Patterns Planned

### Integration Patterns

1. **API Gateway**

   - Description: Acts as an entry point for the microservices architecture, handling requests, authentication, and routing.

2. **Backends for Frontend (BFF)**

   - Description: Provides dedicated backends tailored for specific user interfaces or client applications.

3. **Service Registry**
   - Description: Dynamically registers and discovers microservices to facilitate service-to-service communication.

### Observability Patterns

1. **Log Aggregation**

   - Description: Aggregates logs for centralized monitoring and analysis.

2. **Performance Metrics**

   - Description: Collects metrics to monitor the performance of services.

3. **Distributed Tracing**

   - Description: Traces the flow of requests across microservices for debugging and performance analysis.

4. **Health Check**
   - Description: Monitors the health of microservices to ensure proper functioning.

### Cross-Cutting Patterns

1. **Circuit Breaker**
   - Description: Prevents a service from making calls to a potentially failing service, enhancing fault tolerance.

### Database Patterns

1. **Database per Service**

   - Description: Each microservice has its own database, promoting independence and autonomy.

2. **Command Query Responsibility Segregation (CQRS)**
   - Description: Separates read and write operations for a data store.

### Decomposition Patterns

1. **Saga Pattern**

   - Description: Manages distributed transactions across multiple microservices.

2. **Bulkhead Pattern**

   - Description: Isolates components to contain and limit the impact of failures.

3. **Sidecar Pattern**
   - Description: Attaches a secondary, independent service to the main application to provide additional functionality.

## Getting Started

### Prerequisites

## Prerequisites

Before running the project, make sure you have the following prerequisites installed:

- Java Development Kit (JDK)
- Maven
- MongoDB (ensure it's running locally or provide connection details in the configuration)
- PostgreSQL (ensure it's running locally or provide connection details in the configuration)

### Installation and Usage

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/varunkumarnr/Microservice-Design-Patterns.git
   cd microservices-patterns
   cd into a folder
   mvn clean install
   ```

## Contributing

Feel free to contribute to the project! Follow the guidelines in [CONTRIBUTING.md] for more details.

## License

This project is licensed under the MIT - see the [LICENSE.md] file for details.
