FROM amazoncorretto:17-alpine-jdk
COPY  build/libs/HandymanService-0.1.1-SNAPSHOT.jar HandymanService.jar
EXPOSE 8080
EXPOSE 8091
ENTRYPOINT ["java","-jar","/HandymanService.jar"]