FROM openjdk
WORKDIR usr/lib
ADD ./target/examportalproject-0.0.1-SNAPSHOT.jar /usr/lib/examportalproject-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","examportalproject-0.0.1-SNAPSHOT.jar"]