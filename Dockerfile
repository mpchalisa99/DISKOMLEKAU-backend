FROM gradle:8-jdk17 AS build
COPY . /app
WORKDIR /app
RUN gradle buildFatJar --no-daemon

FROM eclipse-temurin:17-jre
EXPOSE 8080
COPY --from=build /app/build/libs/*-all.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
