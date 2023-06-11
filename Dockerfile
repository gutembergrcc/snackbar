FROM openjdk:18.0.2.1-slim-buster

COPY build/libs/*.jar /opt/app/application-plain.jar

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

CMD java -jar /opt/app/application-plain.jar