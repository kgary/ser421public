# Spring Booktown REST API example for SER421
Updated 9/30/22

## Running
The API is configured to use Spring Boot and Gradle, so "./gradlew bootRun" from the command-line will do the trick.

This API has been tested against Spring version 2.7.4

## Configuration
No special confirguation required in application/properties

Dependencies on Spring Starter modules Sprint Boot Development Tools, Spring Web, Spring REST Hateoas, and Spring test (though no special testing has been implemented).
My build.gradle dependencies section:

```
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-hateoas'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```