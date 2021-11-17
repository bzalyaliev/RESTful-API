#on demo purposes: how to use traditional dockerfile build
FROM openjdk:11

COPY target/sock-0.0.1-SNAPSHOT.jar sock-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/sock-0.0.1-SNAPSHOT.jar"]