# syntax=docker/dockerfile:1

# DOCKER_BUILDKIT=1 docker build -f Dockerfile.dev -t imoder/m01-cloud-provider-payment8002:latest . \
#    && docker run --env DOCKER_BUILDKIT=1 --name m01-cloud-provider-payment8002 imoder/m01-cloud-provider-payment8002:latest

# 指定基础镜像
FROM  openjdk:8-jdk-alpine

WORKDIR /app

VOLUME /app

# 拷贝jar包
ADD /target/*.jar app.jar

# 暴露端口号
EXPOSE 8001

# 执行命令
ENTRYPOINT ["java", "-Xms256m", "-Xmx256m", "-jar","/app/app.jar"]