FROM java:8-alpine
ADD ./target/MircroService-Transaction-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app.jar"]
