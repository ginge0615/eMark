FROM java:8-alpine
ADD ./target/MircroService-Item-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app.jar"]
