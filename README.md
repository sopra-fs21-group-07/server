# SoPra RESTful Service Group 07 FS21
## Information about the Project: 
Link under which server is running:
https://sopra-fs21-group-07-server.herokuapp.com/
<br>Link for reviewing API Calls:
https://sopra-fs21-group-07-server.herokuapp.com/swagger-ui.html#/


## Current project status
(Heroku batch not available)
<p align="left">
  <a href="https://github.com/sopra-fs21-group-07/server/actions">
    <img src="https://github.com/sopra-fs21-group-07/server/workflows/Deploy%20Project/badge.svg">
  </a>
  <br></br>
  <a href="https://sonarcloud.io/dashboard?id=sopra-fs21-group-07_server">
      <img src="https://sonarcloud.io/api/project_badges/measure?project=sopra-fs21-group-07_server&metric=coverage">
  </a>
  <br></br>
  <a href="https://sonarcloud.io/dashboard?id=sopra-fs21-group-07_server">
        <img src="https://sonarcloud.io/api/project_badges/measure?project=sopra-fs21-group-07_server&metric=alert_status">
    </a>
</p>

## Introduction: 
The projects aim is to bring people on mountains together. There are always people who want to go on a mountain tour but just cant find people who want to join them. That why we created our mountain app.

## Technologies
Cloudinary,
Spring,
SonarQube,
React,
npm,
JSX,
Java,
Java Persistence,
heroku,
gradle,
GitHub Projects,
GitHub Actions,
Map Geo Admin

## High-level components

- Tours [tour](https://github.com/sopra-fs21-group-07/server/tree/main/src/main/java/sopra/tour)
- Map [mapAPI](https://github.com/sopra-fs21-group-07/server/tree/main/src/main/java/sopra/mapAPI)
- User authentication [userauthentication](https://github.com/sopra-fs21-group-07/server/tree/main/src/main/java/sopra/userauthentication)

## Launch & Deployment

You can use the local Gradle Wrapper to build the application.

Plattform-Prefix:

-   MAC OS X: `./gradlew`
-   Linux: `./gradlew`
-   Windows: `./gradlew.bat`

More Information about [Gradle Wrapper](https://docs.gradle.org/current/Userguide/gradle_wrapper.html) and [Gradle](https://gradle.org/docs/).

### Build

```bash
./gradlew build
```

### Run

```bash
./gradlew bootRun
```


## API Endpoint Testing

### Postman

-   We highly recommend to use [Postman](https://www.getpostman.com) in order to test your API Endpoints.

## Illustrations

![alt_text](https://github.com/sopra-fs21-group-07/client/blob/main/src/components/images/Dashboard_App.png)

## Roadmap

- Add a weather API
- Add an avalanche API
- Make a review page for tours (backend exists)

## Acknowledgements

This project was started using this template -> [Client](https://github.com/HASEL-UZH/sopra-fs21-template-client)

## Team Members

- [Rafael Dubach](https://github.com/radubauzh)
- [Raphael Wäspi](https://github.com/sumsumcity)
- [Dylan Baumgartner](https://github.com/mrspacerobot)
- [Beat Furrer](https://github.com/elBeato)
- [Layla Husselman](https://github.com/14y14)

## License

[MIT license](https://github.com/sopra-fs21-group-07/client/blob/master/LICENSE)
