# Use an image with Java 17 jdk slim
#If you only need to run the application, the JRE is sufficient. (Meaning compiling JAR has to be done in host machine)
#If you need to compile or debug Java code within the container, you need the JDK.
FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/projecte-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} projecte.jar
ENTRYPOINT ["java","-jar","/projecte.jar"]
#CMD: Provides default arguments for the container. It can be overridden by passing arguments to docker run.
#ENTRYPOINT: Sets the command that will always run when the container starts. It is not easily overridden.






# Another system that can be used that will build automatically de JAR file is:

#FROM openjdk:17-jdk-slim

# Set the working directory in the container
#WORKDIR /estalvis-app

# Copy the current directory contents (relative to Dockerfile) into the container at /estalvis-app
#COPY . /estalvis-app

# Install dependencies and build the application
#RUN ./gradlew build

# Run the application
#Specifies the command to run when the container starts.
#  - java: The Java runtime executable.
#  - -jar: Tells Java to run a JAR file.
#  - build/libs/your-application.jar: The path to the JAR file to run.
#ENTRYPOINT ["java","-jar","/projecte.jar"]