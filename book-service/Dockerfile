FROM openjdk:11-slim

LABEL desc="Basic Dockerfile"

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"] 


