FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD /target/giftstore-0.0.1-SNAPSHOT.jar app.jar
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-server -Xms800m -Xmx800m -XX:MaxNewSize=256m -Djava.awt.headless=true"
EXPOSE 8075
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]