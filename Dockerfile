FROM eclipse-temurin:17.0.5_8-jre-alpine

COPY build/libs/*.jar /opt/app/snackbar.jar
COPY wait-for-it.sh /usr/local/bin/
RUN apk update && apk add bash
RUN chmod +x /usr/local/bin/wait-for-it.sh
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

CMD ["/usr/local/bin/wait-for-it.sh", "snackbar-mysql-db:3306", "-t", "60", "--", "java", "-jar", "/opt/app/snackbar.jar"]
