FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/stock-task-mds-0.0.1.jar mds-task.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "mds-task.jar"]
