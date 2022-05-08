FROM openjdk:8
ADD trainer/target/docker-spring-boot.jar docker-spring-boot.jar
EXPOSE 8000
ARG JAR_FILE=trainer/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","docker-spring-boot.jar"]