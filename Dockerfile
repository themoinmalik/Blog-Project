FROM openjdk:17-jdk-alpine


COPY target/blogApp.jar .

EXPOSE 8181

ENTRYPOINT ["java", "-jar", "blogApp.jar"]