
## install 

### install maven

## install VSC extensions

- Extension Pack for Java
- Spring Boot Extension Pack

## Create a Spring Boot project

Using Spring Initializr inside VS Code (recommended)
- Open Command Palette: Ctrl+Shift+P (or Cmd+Shift+P on Mac)
- Run: Spring Initializr: Create a Maven Project
- Select options:
  - Project: Maven
  - Language: Java
  - Spring Boot version: default (latest stable)
  - Group Id: com.example
  - Artifact Id: demo
  - Packaging: Jar
  - Java version: 17
  - Choose dependencies (you can add more later): 
    - ✅ Spring Web
    - Spring Data JPA
    - PostgreSQL Driver
    - Flyway (recommended)

## Run project

- Open DemoApplication.java
- Click ▶ Run above main()

## Test

http://localhost:8080

```
curl -X POST http://localhost:8080/api/products -H "Content-Type: application/json" -d '{"name":"MacBook Pro","price":2999.99}'

curl http://localhost:8080/api/products
```

test actuator
```
http://localhost:8080/actuator/health
http://localhost:8080/actuator/health/liveness

```

## create and run Postgresql container database

1. start Docker Desktop
2. run this command:
```
docker run -d --name postgres -e POSTGRES_DB=appdb -e POSTGRES_USER=appuser -e POSTGRES_PASSWORD=secret  -p 5432:5432 -v postgres_data:/var/lib/postgresql/data postgres:16
```
3. test
```
docker exec -it postgres psql -U appuser -d appdb
SELECT * FROM products;
```

