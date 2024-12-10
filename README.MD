# MDS Stock Task


## Getting Started

Follow the steps below to set up and run the application.

### 1. Build application
```bash
docker-compose up --build
```

### 2. Execute POST service from postman
Endpoint
```bash
localhost:8080/api/v1/profit/check
```



Request body
```json
{
    "code":"AMZN",
    "dateFrom": "1999-05-05",
    "dateTo": "1999-05-15"
}
```

