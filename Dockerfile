#FROM adoptopenjdk:11-jre-openj9 AS builder
#WORKDIR target/dependency
#ARG APPJAR=build/libs/*.jar
#COPY ${APPJAR} app.jar
#RUN jar -xf ./app.jar

FROM adoptopenjdk:11-jre-openj9
RUN ls -al -R

RUN mkdir /app
#ARG APPJAR=build/libs/*.jar

ARG APPJAR=pages.jar
COPY ${APPJAR} /app.jar
RUN ls -al -R
CMD ["java", "-jar", "/app.jar"]


