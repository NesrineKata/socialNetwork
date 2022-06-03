# SocialNetwork

Prototypical social network project

## Getting Started

Cloning project:
```
git clone https://github.com/MarcelIwanicki/social-network
```
Launching project on localhost:8080
```
mvn compile
mvn spring-boot:run
```

### Prerequisites

Java IDE with integrated Maven or just maven

on Debian

```
sudo apt-get install maven
```

## Running the tests

Testing project
```
mvn surefire:test -Dtest=com.sharing.overload.ClassName
```
where ClassName is desired test class

## Deployment

```
mvn clean
mvn package
```

## Built With

* [SpringBoot](https://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [H2 Database](https://www.h2database.com/html/main.html) - Internal database
* [Lombok](https://projectlombok.org/) - Simplified java with annotations
* [Thymeleaf](https://www.thymeleaf.org/) - HTML templates for Spring Boot
* [STOMP](https://stomp-js.github.io/stomp-websocket/codo/extra/docs-src/Usage.md.html) - Simple (or Streaming) Text Orientated Messaging Protocol 
* [Bootstrap](https://getbootstrap.com/) - Bootstrap is an open source toolkit for developing with HTML, CSS, and JS

## Authors

* **Marcel Iwanicki** - *Initial work* - [MarcelIwanicki](https://github.com/MarcelIwanicki)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
