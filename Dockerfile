FROM maven:3.8.2-jdk-11 AS build

RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim
RUN mkdir /app
##RUN addgroup --system javauser && adduser -S -s /bin/false -G javauser javauser
COPY --from=build /project/target/Demographics-Data-0.0.1-SNAPSHOT.jar /app/java-application.jar
WORKDIR /app
##RUN chown -R javauser:javauser /app
##USER javauser
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n -Djava.security.egd=file:/dev/./urandom

EXPOSE 8080 5005
CMD "java" "-jar" "java-application.jar"

