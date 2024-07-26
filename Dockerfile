FROM openjdk:11-jre-slim
VOLUME /tmp
COPY target/expense-tracking-system.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
