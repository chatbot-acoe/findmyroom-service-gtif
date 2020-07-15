FROM java:8
EXPOSE 8080
ADD ./target/dev.war inventory.war
ENTRYPOINT ["java","-jar","inventory.war"]