FROM amazoncorretto:17-alpine-jdk
COPY  build/libs/RancherService-0.1.1-SNAPSHOT.jar RancherService.jar
EXPOSE 8082
EXPOSE 8090
ENTRYPOINT ["java","-jar","/RancherService.jar"]