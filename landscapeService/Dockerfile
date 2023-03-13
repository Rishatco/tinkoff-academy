FROM gradle:7.5.1-jdk17-alpine as build
WORKDIR /workspace/app

COPY . /workspace/app
RUN apk update && apk add gcompat
RUN gradle bootJar

FROM amazoncorretto:17-alpine-jdk
COPY --from=build /workspace/app/build/libs/LandscapeService-0.3.1-SNAPSHOT.jar /LandscapeService.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/LandscapeService.jar"]