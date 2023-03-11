FROM gradle:7.5.1-jdk17-alpine as build
WORKDIR /workspace/app

COPY . /workspace/app
RUN apk update && apk add gcompat
RUN gradle clean build

FROM amazoncorretto:17-alpine-jdk
COPY --from=build /workspace/app/build/libs/*.jar /app.jar
EXPOSE 8082
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app.jar"]