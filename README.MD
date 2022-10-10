# My cloud native basic app with springboot

## See the following resources:

- [Build Instructions](./installation.md)
- [Code Structure](./pics/structure.png)


## Dockerize 

An important step to make an application cloud-native, is the process of containerization.

We will need a [Dockerfile](./Dockerfile) for that:

```
FROM openjdk:11-jre-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```
- 1: FROM
This is my first layer for the image. I’m basically mounting this first layer from a linux OS with openjdk11 version jre-slim, which is small and recommended for this type of development
```
FROM openjdk:11-jre-slim
```

- 2: ARG
The springboot app after compiled results in a JAR file with an embedded Tomcat app server(the other option would be to generate a .WAR file and deploy it in a webserver). This was the result of the gradlew command (./gradlew build). I could have used maven instead.
With this ARG line I’m basically creating this argument or variable called JAR_FILE and assigning it the value of the path to the jar file.
```
ARG JAR_FILE=build/libs/*.jar
```

- 3: COPY
With COPY I’m then copying my JAR_FILE (cloudnativebasic-0.0.1-SNAPSHOT.jar) and calling it a simpler name (app.jar)
```
COPY ${JAR_FILE} app.jar
```

- 4: ENTRYPOINT
Finally with the ENTRYPOINT I’m defining the command I’m going to execute to run the application which is “java” with the option “-jar” against the file “app.jar”. This would be equivalent to write in the command line “java -jar ./app.jar”
```
ENTRYPOINT ["java","-jar","/app.jar"]
```