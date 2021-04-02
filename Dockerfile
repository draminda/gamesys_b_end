FROM maven:3.6-jdk-11

COPY ./target/news-0.0.1.war app.war

ENV PORT 80
EXPOSE $PORT

ENTRYPOINT ["java","-jar","-Dserver.port=${PORT}" ,"/app.war"]
