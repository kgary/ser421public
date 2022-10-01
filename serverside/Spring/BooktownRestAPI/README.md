# Spring Booktown REST API example for SER421

### Current Status
Updated 10/01/22
This example demonstrates a Spring REST API with 2 model objects, Books and Authors. 
An Author may write many Books, but a Book may only have one Author.
This is not being implemented with a persistence store, so maintaining relationships between Book and Authors is does with a Mock service that uses an internal data structure
This also means that Spring Data Rest module is not used as there is no database, so a number of the convenience features provided by Spring assuming a backing store are not available here. 
This is in part why there are lengthy gyrations with Controller Advice and model helper objects.

You can POST{/id}, PUT{/id} (create new if no id or update if id is present), GET all, GET{/id} one, DELETE{/id} and PATCH{/id} on Authors (/authors)
You can GET all, GET{/isbn} one, and DELETE{/isbn} on Books. dELETE should modify Authors to remove the relationship (see Issues). You can only create Books through Authors.

### Running
The API is configured to use Spring Boot and Gradle, so "./gradlew bootRun" from the command-line will do the trick.

This API has been tested against Spring version 2.7.4

### Configuration
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
#### Issues
Right now you can delete a Book and it will be removed but the Author rendering still has it show up for some reason!

