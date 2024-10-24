# Request-Response-DesignPattern-example
Rent a car project

# Request-Response Design Pattern in Spring Boot

## Overview
In this project, we implemented the **Request-Response Design Pattern** using Spring Boot. This design pattern helps in structuring the flow of data between the client and the server by separating requests (coming from the client) and responses (sent back from the server). It ensures clear communication channels, better maintainability, and scalability of the application.

## Key Concepts

1. **Entities**: These represent the core data models that are mapped to the database using JPA annotations.
    - **Car**: Represents the car entity with attributes like `id`, `plate`, `dailyPrice`, `modelYear`, and `state`.
    - **Brand**: Represents the brand of cars and has a one-to-many relationship with `Model`.
    - **Model**: Represents the model of a car, linked to both `Brand` and `Car`.

2. **Repositories**: These interfaces extend Spring Data JPA's `JpaRepository` and provide methods to interact with the database.
    - `BrandRepository`
    - `ModelRepository`

3. **Service Layer**: Handles the business logic and is responsible for interacting with repositories and mapping entities to DTOs (Data Transfer Objects).
    - **BrandService**: Defines methods for managing brands.
    - **ModelService**: Defines methods for managing models.

4. **DTOs (Data Transfer Objects)**: These are used to map data between the request and response objects and the core entities. This ensures that only the required data is exposed to the client.
    - **CreateBrandRequest**: A request DTO used when creating a new brand.
    - **GetAllBrandsResponse**: A response DTO for retrieving brand information.
    - **UpdateBrandRequest**: A request DTO used for updating brand information.

5. **Business Rules**: Business-specific validations and rules are separated out into a `BrandBusinessRules` class to ensure clean code and separation of concerns.
    - Example: Check if a brand name already exists in the system before adding a new brand.

6. **Controllers**: The controllers act as the interface between the client (frontend or API consumer) and the service layer. They handle incoming HTTP requests and send appropriate responses.
    - **BrandsController**: Handles CRUD operations related to brands.
    - **ModelsController**: Handles CRUD operations related to models.

## Project Structure

src/
└─── main/
├─── java/
│    └─── kodlama/io/rentACar/
│         ├─── business/
│         │    ├─── abstracts/            # Servis arabirimleri (interfaces)
│         │    ├─── concretes/            # Somut servis sınıfları
│         │    ├─── requests/             # İstek (Request) nesneleri
│         │    ├─── responses/            # Yanıt (Response) nesneleri
│         │    └─── rules/                # İş kuralları
│         ├─── core/
│         │    └─── utilities/            # Yardımcı sınıflar
│         │         ├─── exceptions/      # Hata yönetimi sınıfları
│         │         └─── mappers/         # Nesne mapper'ları
│         ├─── dataAccess/
│         │    └─── abstracts/            # Veritabanı erişim arayüzleri (Repository'ler)
│         ├─── entities/
│         │    └─── concretes/            # Varlık (Entity) sınıfları
│         └─── webApi/
│              └─── controllers/          # REST API controller'ları
└─── resources/
├─── application.properties          # Yapılandırma dosyası
└─── data.sql                        # Veritabanı başlangıç verisi (varsa)

### Important Annotations Used

- `@Entity`: Used to define classes that map to database tables.
- `@Table`: Specifies the table name in the database.
- `@ManyToOne`, `@OneToMany`: Define relationships between entities.
- `@RestController`: Marks a class as a RESTful web service controller.
- `@RequestMapping`: Maps web requests to specific handler methods.
- `@Valid`: Ensures that incoming request data is validated.

## Request-Response Flow

### 1. Client sends a request to the server (e.g., POST to create a new brand):
- Request Body:
  ```json
  {
    "name": "Toyota"
  }
  ```

### 2. Controller receives the request and calls the service layer:
- The controller, annotated with `@PostMapping`, invokes the `add` method of `BrandService`.

### 3. Service layer processes the request:
- Service validates the input using the `BrandBusinessRules`.
- The request is then mapped to the `Brand` entity using the `ModelMapperService`.
- Data is saved to the database via `BrandRepository`.

### 4. Service layer sends the response back to the controller:
- The service returns a success response or a meaningful error if the process fails.

### 5. Controller sends the response back to the client:
- Status: `201 Created`
- Response Body (on success):
  ```json
  {
    "message": "Brand created successfully"
  }
  ```

## Exception Handling

In the application, custom exception handling is implemented to provide clear error messages to the client while abstracting unnecessary details. This is done using:
- **BusinessException**: Custom exception for business logic failures.
- **ProblemDetails**: Standardized error response format.
- **ValidationProblemDetails**: Special subclass for handling validation errors.

## Benefits of the Request-Response Pattern
- **Separation of Concerns**: By separating requests, responses, and entities, we ensure that each layer has a single responsibility.
- **Improved Maintainability**: Clear distinction between layers and models makes it easier to manage and scale the application.
- **Structured Error Handling**: With custom exception handling, the application can provide meaningful and user-friendly error messages.

---

This project follows clean architecture principles and ensures that the core business logic is isolated from external concerns like database access and web requests. This approach makes the system highly testable, maintainable, and scalable.

