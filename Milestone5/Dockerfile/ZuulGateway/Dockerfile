FROM java:8-alpine
ADD ./target/ZuulGateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","/app.jar"]
