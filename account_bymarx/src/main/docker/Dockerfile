FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD account-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

# http://blog.csdn.net/kkkloveyou/article/details/50942275