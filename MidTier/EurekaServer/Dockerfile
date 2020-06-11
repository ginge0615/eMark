FROM java:8-alpine
ADD ./target/EurekaServer-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9001
ENTRYPOINT ["java","-jar","/app.jar"]
