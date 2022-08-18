FROM adoptopenjdk/openjdk11
EXPOSE 9091
ARG jar_file=target/*.jar
COPY $jar_file app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]