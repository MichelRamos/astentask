FROM maven AS builder
COPY . astentask
WORKDIR /astentask
RUN mvn clean package -DskipTests

FROM openjdk:21
COPY --from=builder /astentask/target/astentask-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]
