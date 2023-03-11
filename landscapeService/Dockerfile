FROM gradle:7.5.1-jdk17-alpine as build
WORKDIR /workspace/app

COPY . /workspace/app
RUN gradle clean build

FROM amazoncorretto:17-alpine-jdk
COPY --from=build /workspace/app/build/libs/*.jar /app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]