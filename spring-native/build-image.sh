mvn clean '-Dmaven.test.skip=true'
# 一定要加 -Pnative 参数
mvn -Pnative spring-boot:build-image '-Dmaven.test.skip=true'
