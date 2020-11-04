### WebServicesAutomation Project

The purpose of the suite is to test the content in the REST services (JSON files) using Functional Programming in Java Streams and Lambda Expressions.

#### Useful Commands:
```properties
java -version
```

#### Tools
- Java 8
- Rest Assured
- JUnit and TestNG
- Gradle 

#### Run Tests using gradle commands:

##### Command to run a particular class:
- `gradle test --tests TestClassName`

##### Command to run all classes for particular package:
- `gradle test --tests com.microservice.regression.packageName* -- info`

##### Command to run and write the test output into a test file:
- `gradle test --tests TestClassName --info > testFile.txt`

##### Command to run tests with stacktrace:
- `gradle test --tests TestClassName --info --stacktrace`

##### Execute all tests
- `./gradlew test`

##### Useful REST API Links
- https://www.restapitutorial.com/lessons/httpmethods.html

##### REST Assured
- given() -> set cookies, add auth, add param, set headers info
- when() -> get, post, put delete
- then() -> validate status code, extract response, extract headers cookies and response body

#### Notes:
To be able to create tests cases to validate the content in the services, I created a JSON file from scratch. For example: `movies.service.json`.
However, creating a huge payload takes time, so I have taken already existing JSON files to test from: [samayo](https://github.com/samayo/country-json) Thanks to him!

- Used `https://reqres.in/` to use dummy REST service Apis for testing
- Create custom API: `https://github.com/typicode/json-server` or `https://www.mockapi.io/` or `https://my-json-server.typicode.com/`