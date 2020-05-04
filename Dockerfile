FROM ubuntu-jdk

ENV name=PMA(Docker)
ENV dbuser
ENV dbpwd
ENV jdbcurl

MAINTAINER Elimane Fofana "Elimane42@gmail.com"

RUN apt-get update && apt-get install -y openjdk-8-jdk

WORKDIR /usr/local/bin/

ADD target/pma.jar .

ENTRYPOINT ["java", "-jar", "pma.jar"]
