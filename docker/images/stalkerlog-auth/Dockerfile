FROM openjdk:8-jdk-alpine

COPY tmp/stalkerlog-auth*.jar stalkerlog-auth.jar
ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "stalkerlog-auth.jar"]
