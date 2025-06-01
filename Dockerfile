# Dockerfile (cùng thư mục với pom.xml)
FROM openjdk:17-jdk-slim
COPY target/*.jar spring_t2208e-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/spring_t2208e-1.0-SNAPSHOT.jar"]
