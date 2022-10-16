FROM gradle:jdk11 as build
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle build -x test

FROM amazoncorretto:11
RUN mkdir /opt/app
COPY --from=build /home/gradle/source/build/libs/blooper-0.0.1-SNAPSHOT.jar /opt/app/blooper.jar
CMD ["java", "-jar", "/opt/app/blooper.jar"]
