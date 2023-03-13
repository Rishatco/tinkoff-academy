FROM gradle:7.5.1-jdk17-alpine as build
WORKDIR /workspace/app

COPY . /workspace/app
RUN apk update && apk add gcompat
RUN gradle bootJar

FROM amazoncorretto:17-alpine-jdk
COPY --from=build /workspace/app/build/libs/HandymanService-0.3.1-SNAPSHOT.jar /HandymanService.jar
EXPOSE 8080
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "/HandymanService.jar"]