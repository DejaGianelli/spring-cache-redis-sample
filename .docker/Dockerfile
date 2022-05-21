FROM openjdk:11-slim-buster
WORKDIR /app
COPY target/spring-api-cache-redis.jar app.jar
ENV JVM_OPT $JVM_OPT
EXPOSE 8080
EXPOSE 5005
ENTRYPOINT java $JVM_OPT -jar app.jar