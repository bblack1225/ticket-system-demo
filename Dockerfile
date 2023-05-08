FROM eclipse-temurin:17

ENV APP_HOME=/app
ENV APP_JAR=ticketsystem.jar

RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME

COPY ./target/$APP_JAR $APP_HOME/

EXPOSE 8094

CMD ["java", "-jar", "ticketsystem.jar"]


