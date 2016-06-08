# sample-spring-mvn-protobuf

Sample Spring Boot project using [Spring Boot](http://projects.spring.io/spring-boot/) together with [Google Protocol Buffers](https://developers.google.com/protocol-buffers/). The protobuf definitions are compiled by the [Maven](https://maven.apache.org) plugin [protoc-jar-maven-plugin](http://os72.github.io/protoc-jar-maven-plugin/index.html).

## Running

- To build the project and run the tests:
```
$ mvn clean install
```

- To start the Spring Boot application:
```
$ mvn spring-boot:run
```
The application will start an endpoint at `http://localhost:8080/customers/{id}` *(substitue `{id}` with a number between 1-4)*.

## Credits

Originally based on

- https://github.com/joshlong/spring-and-google-protocol-buffers
