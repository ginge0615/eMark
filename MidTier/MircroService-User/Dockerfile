FROM java:8-alpine
ADD ./target/MircroService-User-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]
