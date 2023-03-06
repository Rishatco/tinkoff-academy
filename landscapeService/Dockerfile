FROM amazoncorretto:17-alpine-jdk
COPY  build/libs/LandscapeService-0.1.1-SNAPSHOT.jar LandscapeService.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/LandscapeService.jar"]