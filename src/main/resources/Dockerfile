FROM tomcat:9.0.83-jdk17
LABEL authors="myname"

RUN rm -Rf /usr/local/tomcat/webapps/ROOT
COPY ./target/spring1.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]