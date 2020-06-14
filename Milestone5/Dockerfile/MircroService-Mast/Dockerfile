FROM java:8-alpine
ADD ./target/MircroService-Mast-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app.jar"]
