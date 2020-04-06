FROM openjdk:10-jre-slim
COPY ./target/spring-mongo-crud-0.0.1-SNAPSHOT.jar /usr/src/restapi/
WORKDIR /usr/src/restapi
EXPOSE 8080
CMD ["java", "-jar", "spring-mongo-crud-0.0.1-SNAPSHOT.jar"]