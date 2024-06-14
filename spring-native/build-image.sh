mvn clean '-Dmaven.test.skip=true'

# 方法 1，需安装 Docker，构建产物为 docker images
mvn spring-boot:build-image

# 方法 2，需安装 vs_BuildTools，构建产物为可执行文件（exe 等）
mvn -Pnative spring-boot:build-image