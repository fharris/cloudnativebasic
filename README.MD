# My cloud native basic app with springboot

In this exercise I will share how to dockerize a SpringBoot app, test it locally and push it to docker hub

## See the following resources:

- [Build Instructions](./installation.md)
- [Code Structure](./pics/structure.png)


## Dockerize 

An important step to make an application cloud-native, is the process of containerization.

We will need a [Dockerfile](./Dockerfile) for that:

```
FROM openjdk:23-jdk-slim 
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```
- 1: FROM
This is my first layer for the image. I’m basically mounting this first layer from a linux OS with openjdk23 version slim, which is small and recommended for this type of development
```
FROM openjdk:23-jdk-slim 
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


Now that we have our Dockerfile ready, we can actually build our docker container:
```
docker build -t fharris/cloudnativebasic-spring-docker .
```

Or if we want to build to another runtime with another CLI such as nerdctl for containerd :
```
nerdctl build -t fharris/cloudnativebasic-spring-docker .
```

Check the image:
```
docker images
```

If everything went ok and the image was built and its available on my local registry, all I need to do now is to run it locally:
```
docker run -p 8080:8080 -t fharris/cloudnativebasic-spring-docker
```
or  if you want to run it with nerdctl and containerd:
```
nerdctl run -p 8080:8080 -t fharris/cloudnativebasic-spring-docker
```

I’m saying with this that we will execute the image referenced mapping the port 8080 of my local machine to the port 8080 of the running container.

You should be able to test it locally here:  http://localhost:8080/hello

## Summary
The application was [built and tested locally](./installation.md). Then it was [dockerized](./Dockerfile) and tested again successfully.
It means we can now ship our image to our remote docker repository and make it available to other developers:
In my case, I would push the image to my docker hub public rep (fharris) with the name cloudnativebasic-spring-docker:
```
docker push fharris/cloudnativebasic-spring-docker
```
