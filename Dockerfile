# BUILD stage
FROM maven:3.8.3-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# RELEASE stage
#
FROM openjdk:17-jdk-slim AS release
COPY --from=build /home/app/target/*.jar /app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "-Dfile.encoding=UTF8"]

