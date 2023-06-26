FROM gradle:7.5.1-jdk17-alpine as build
WORKDIR /workspace/app

COPY . /workspace/app
RUN apk update && apk add gcompat
RUN gradle bootJar

FROM amazoncorretto:17-alpine-jdk
COPY --from=build /workspace/app/build/libs/RancherService-0.8.1-SNAPSHOT.jar /RancherService.jar

EXPOSE 8082
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/RancherService.jar"]