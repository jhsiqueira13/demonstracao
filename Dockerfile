FROM eclipse-temurin
run apt-get -y update

ENV TZ="America/Sao_Paulo"

RUN echo $TZ > /etc/timezone

COPY target/demonstracao-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 80
CMD ["java","-jar","/app/app.jar"]
