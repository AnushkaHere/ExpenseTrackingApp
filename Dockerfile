FROM openjdk:17-jdk-slim
COPY --from=build /home/app/target/expense-tracking-app.jar /usr/local/lib/expense-tracking-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/expense-tracking-app.jar"]