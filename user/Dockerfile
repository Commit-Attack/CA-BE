FROM azul/zulu-openjdk:17
ARG JAR_FILE_PATH=build/libs/user-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE_PATH} app.jar
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT java -jar /app.jar