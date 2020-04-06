FROM openjdk:10-jre-slim
COPY ./target/songs-1.0-SNAPSHOT.jar /usr/src/restapi/
WORKDIR /usr/src/restapi
EXPOSE 8080
CMD ["java", "-jar", "songs-1.0-SNAPSHOT.jar"]
