#
# Build stage
#

FROM eclipse-temurin:17-jdk-alpine AS build
COPY . .

RUN apk update && apk add dos2unix
#RUN dos2unix ./mvnw ./mvnw.cmd

RUN apk update && apk add maven

RUN mvn clean package -DskipTests

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/API-0.0.1-SNAPSHOT.jar api.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","api.jar"]

#docker build -t simple-news-application-api .
#docker tag simple-news-application-api:latest sumuks13/simple-news-application-api:latest
#docker push sumuks13/simple-news-application-api:latest
