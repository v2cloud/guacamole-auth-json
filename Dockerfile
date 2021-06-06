FROM maven:3-jdk-8 AS builder

COPY . /tmp/json-auth-build

WORKDIR /tmp/json-auth-build

RUN mvn package

RUN mv target/*.jar target/json-auth.jar


FROM alpine:3.12

RUN mkdir /build && mkdir /extensions

COPY --from=builder /tmp/json-auth-build/target/*.jar /build

CMD ["/bin/sh", "-c", "rm -f /extensions/json-auth.jar; cp /build/json-auth.jar /extensions/"]
