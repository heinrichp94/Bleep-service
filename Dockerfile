FROM amazoncorretto:11
RUN mkdir /opt/app
COPY build/libs/blooper-0.0.1-SNAPSHOT.jar /opt/app/blooper.jar
CMD ["java", "-jar", "/opt/app/blooper.jar"]
