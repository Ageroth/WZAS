FROM openjdk:11
COPY ./target/wzas-0.0.1-SNAPSHOT.jar /usr/src/restapi/
WORKDIR /usr/src/restapi
EXPOSE 8080
CMD ["java", "-jar", "wzas-0.0.1-SNAPSHOT.jar"]