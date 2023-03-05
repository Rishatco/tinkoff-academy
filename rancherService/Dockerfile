FROM amazoncorretto:17-alpine-jdk
MAINTAINER ru.tinkoff
COPY  build/libs/RancherService-0.1.1-SNAPSHOT.jar RancherService.jar
ENTRYPOINT ["java","-jar","/RancherService.jar"]