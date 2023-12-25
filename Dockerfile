

FROM openjdk:11-jdk-alpine AS build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn

COPY pom.xml .


RUN ./mvnw dependency:go-offline -B


COPY src src


RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependecy && (cd target/dependency; jar -xf ../*.jar)


FROM openjdk:8-jre-alpine

ARG DEPENDENCY=/app/target/dependency


COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDECY}/META-INF /app/META-INF
COPY --from=build ${DEPENDECY}/BOOT-INF/classes /app

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.blogmaker.blog.BlogMakerApplication"]