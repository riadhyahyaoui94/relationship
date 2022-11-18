FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8083
ADD target/relationship-1.jar relationship-1.jar
ENTRYPOINT ["java","-jar","/relationship-1.jar"]