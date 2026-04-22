FROM docker.io/maven:3.9.9-amazoncorretto-21 AS builder
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM docker.io/amazoncorretto:21.0.10
WORKDIR /app
COPY --from=builder /build/target/baby-tracker-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
