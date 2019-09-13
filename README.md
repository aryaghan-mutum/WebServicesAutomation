## WebServicesAutomation Project

### Run Tests using gradle commands:

#### Command to run a particular class:
- `gradle test --tests TestClassName

#### Command to run a particular class and method:
- `gradle test --tests org.gradle.SomeTestClass.someSpecificMethod

#### Command to run all classes for particular package:
- `gradle test --tests com.rccl.cp.regression.service.packageName*

#### Command to run all the tests under regression package:
- `gradle test --tests com.rccl.cp.regression.service*

#### Command to run and write the test output into a test file:
- `gradle test --tests TestClassName --info > testFile.txt`

#### Command to run tests with stacktrace:
- `gradle test --tests TestClassName --info --stacktrace`

##### Execute all tests (rarely used)
- `./gradlew test`

### Notes:
- To be able to create tests cases to validate the content in the services, I created a JSON file from scratch. For example: `movies.service.json`.
- However, creating a huge payload takes time, so I have taken already existing JSON files to test from: `[samayo](https://github.com/samayo/country-json)`` Thanks!
