FROM openjdk:17

# 빌드 시 전달받을 ARG 변수 설정
ARG DATASOURCE_URL_LOCAL
ARG DATASOURCE_USERNAME
ARG DATASOURCE_PASSWORD
ARG JWT_SECRET_KEY
ARG JWT_EXPIRED_IN
ARG REFRESH_TOKEN_EXPIRED_IN

# ARG 값을 ENV로 설정하여 런타임에서도 사용 가능하도록 설정
ENV DATASOURCE_URL_LOCAL=$DATASOURCE_URL_LOCAL
ENV DATASOURCE_USERNAME=$DATASOURCE_USERNAME
ENV DATASOURCE_PASSWORD=$DATASOURCE_PASSWORD
ENV JWT_SECRET_KEY=$JWT_SECRET_KEY
ENV JWT_EXPIRED_IN=$JWT_EXPIRED_IN
ENV REFRESH_TOKEN_EXPIRED_IN=$REFRESH_TOKEN_EXPIRED_IN

# JAR 파일 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 실행 명령어
ENTRYPOINT ["java", "-jar", "/app.jar"]