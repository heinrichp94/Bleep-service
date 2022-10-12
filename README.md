# Bleep-service

This service “bloops” out sensitive words of
the company’s choice. In this case all the SQL key words will be "blooped" out
# How to Run
## Clone this repo
```
git clone https://github.com/heinrichp94/Bleep-service.git
```

## Gradle
```
.\gradlew build
```

## Building the image for the first time
If you want to modify the files in the image, then you'll have to build locally.

Build with `docker-compose`:
```
docker-compose build
```

## Running the container

Modify the env variables to your liking in the `docker-compose.yml`.

Then spin up a new container using `docker-compose`
```
docker-compose up
```

## Swagger
```
http://localhost:8080/swagger-ui/index.html?configUrl=/api-docs/swagger-config#/
```
