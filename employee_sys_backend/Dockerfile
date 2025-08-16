FROM openjdk:17-jdk-alpine
# RUN addgroup -S spring && emplyee_sys -S spring -G spring
# USER spring:spring
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]