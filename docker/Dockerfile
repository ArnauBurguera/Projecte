FROM openjdk:17-jdk-alpine
#seems it is not multiplatform this image you should cheng¡e it to one that is suported not just in intel
ARG JAR_FILE=build/libs/projecte-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]