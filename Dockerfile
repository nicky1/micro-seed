FROM 10.253.6.113:5000/env/jdk7
COPY ./target/waffle.jar /root/startup/
WORKDIR /root/startup/
EXPOSE 8080
CMD ["java","-Xms1024m","-Xmx1024m","-DAPP_DOMAIN=waffle","-jar", "waffle.jar"]