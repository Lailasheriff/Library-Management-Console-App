FROM eclipse-temurin:24-jdk

WORKDIR /app

COPY app.jar app.jar
COPY wait-for-it.sh wait-for-it.sh

RUN chmod +x wait-for-it.sh