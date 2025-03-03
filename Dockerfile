FROM openjdk:21-oracle
ARG PORT=8080
EXPOSE ${PORT}
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]