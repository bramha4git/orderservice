FROM openjdk:8-jdk-alpine
ADD target/order.jar order.jar
ENTRYPOINT ["java", "-jar", "order.jar"]
EXPOSE 8080