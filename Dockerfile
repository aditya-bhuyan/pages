FROM adoptopenjdk:11-jre-openj9
RUN mkdir /app
ARG APPJAR=pages.jar
COPY ${APPJAR} /app.jar
CMD ["java", "-jar", "/app.jar"]


