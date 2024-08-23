# asc-cources-backend

This is a open source backend server for a course management system. The front-end application can be found [here](https://github.com/s-bose7/asc-courses-frontend). The application strictly follows [MVC](docs/asc-mvc-architecture.png) architectural pattern, and uses RESTFul APIs for exchanging information from/to the client. 

# Prerequisite

- Make sure you have Docker installed on your machine.
- Make sure you have Java17 installed on your machine.

# Build Process

## Running locally
```bash
# Get the code
git clone https://github.com/s-bose7/asc-courses-backend.git

# Move to project folder
cd asc-courses-backend

# Build the application
./gradlew clean build

# Run the server
./gradlew bootRun
```
After running the server one can test the APIs hosted at `http://localhost:8080/api/v1`. For more infomation on what kind of requests are allowed [see here](docs/api-docs.md).

## Using Docker compose
```bash
```

# Example Requests

#### Creating a Course

```bash
curl -X POST http://localhost:8080/api/v1/courses \
-header "Content-Type: application/json" \
-data '{
    "courseTitle": "Introduction to Compiler Design",
    "courseCode": "CS 101",
    "courseDescription": "This course provides an introduction to modern compilers and their implementation"
}'

```

#### Creating a Course instance
```bash
curl -X POST http://localhost:8080/api/v1/instances \
-header "Content-Type: application/json" \
-data '{
    "year": 2024,
    "semester": 5,
    "Course": {
        "id": 2,
        "courseTitle": "Introduction to Compiler Design",
        "courseCode": "CS 101",
        "courseDescription": "This course provides an introduction to modern compilers and their implementation"
    }
}'
```