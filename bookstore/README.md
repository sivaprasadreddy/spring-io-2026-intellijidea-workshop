# BookStore Demo Application

## Prerequisites
* JDK 25+
* Docker and Docker Compose
* [IntelliJ IDEA](https://www.jetbrains.com/idea/)

Install JDK, Maven, Gradle, etc using [SDKMAN](https://sdkman.io/)

```shell
$ curl -s "https://get.sdkman.io" | bash
$ source "$HOME/.sdkman/bin/sdkman-init.sh"
$ sdk install java 25-tem
$ sdk install maven
```

## How to run?

```shell
# Run tests
$ ./mvnw clean verify

# Run application using Docker Compose
$ ./mvnw spring-boot:run

# Run application using Testcontainers
$ ./mvnw spring-boot:test-run
```