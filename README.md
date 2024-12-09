# Phone Registration API

This is a Spring Boot application that provides a REST API for phone registration.

## Features
- Register a phone using JSON data.
- RESTful API with Spring Boot.

## Technologies Used
- Java
- Spring Boot

## Endpoints

### Register Phone

- **URL**: `/api/phone/register`
- **Method**: `POST`
- **Description**: Registers a phone with the provided JSON data.

#### Register Phone Api (method: POST)
```json
curl --location 'http://localhost:8081/api/phone/register' \
--header 'Content-Type: application/json' \
--data '{
    "phoneNumber": "1234567891",
    "extension": "102",
    "provider": "Twilio",
    "agentId": "hishab-agent",
    "createdAt": "2024-10-07T10:16:30+02:00"
}'
```

#### Get All Phones(method: GET)
```json
curl --location 'http://localhost:8082/api/queries/phones'
```

#### Get Phone [According to phoneNumberId] (method: GET)
```json
curl --location 'http://localhost:8082/api/queries/phone/0b54ffbe-c704-4e57-a037-9d6867cb1f3f'
```

#### Get Phone [According to phoneNumber] (method: GET)
```json
curl --location 'http://localhost:8082/api/queries/phone/number?phoneNumber=1234567891'
```
#### How to Run
```agsl
./start_api.sh cmd local
./start_api.sh query local

```

#### Swagger UI
```agsl
http://34.131.50.55:9192/swagger-ui/index.html#/
http://34.131.50.55:9193/swagger-ui/index.html#/
```
