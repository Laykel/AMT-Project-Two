FROM openjdk:8-jdk-alpine

COPY tmp/stalkerlog*.jar stalkerlog.jar
ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "stalkerlog.jar"]
