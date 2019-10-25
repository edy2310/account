# Practice Account Simulator

This exercise is made to simulate a basic personal banking account. It is possible to:
* Create an Account
* Login into an Account
* Once logged:
    * Make a deposit
    * Make a withdrawal
    * Consult your balance
* Supports an open web service to debit an specific account

## Built with
* Java 8 - Programming Language
* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [Gradle](https://gradle.org/) - Dependency Management, Task Manager and Scripting Library
* [H2 Database](https://www.h2database.com/html/main.html) - Main database
* [JUnit 5](https://junit.org/junit5/) - Unit testing library
* [Mockito](https://site.mockito.org/) - Data mocking library

## Getting Started
As a common Spring boot project, has all the configurations needed to be a "Download and Run" project. It has an embedded server, which makes this a simple bootable project.

If it is downloaded as source code, just run the "main" method, as a common Java project.

It can also be built to create a Jar file and make it runnable from any computer with a JVM installed.
To do so, run the gradle task: *bootJar*. Which will create a Jar file located on:
```
./build/libs/
```
This jar file is completely bootable from any JVM. To do so run:
```
$ java jar  ./[your_jar_file].jar
```

## Rest API
This table shows the rest API services available in the project.
###### All the endpoints are for internal use (Terminal). Except for "/transactions/debit/{id}", which is used as the web service.

##### Account Endpoints

| URI | Method | Description |
| ------------- | ------------- | ------------- |
| /accounts/{id}  | GET  | Get an account by ID
| /accounts  | POST  | Create an account
| /accounts/{id}  | PUT  | Modify the balance of an account
| /accounts/balance/{id}  | GET  | Get the balance of an account

##
##### Transaction Endpoints

| URI | Method | Description |
| ------------- | ------------- | ------------- |
| /transactions  | POST  | Create a transaction
| /transactions/debit/{id}  | POST  | Make a debit as a transaction in the specific ID account

Since the last endpoint is used from external services, here is the body it needs to send.

```
{
    "transactionType": ["DEPOSIT", "WITHDRAWAL", "DEBIT_CHECKS"],
    "amount": 0,
    "description": "A little description of the service charged"
}
```