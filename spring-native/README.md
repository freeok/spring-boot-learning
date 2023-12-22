构建 Spring Boot 原生应用程序有两种主要方法：

1. 使用 Spring Boot 对 Cloud Native Buildpacks 的支持来生成包含本机可执行文件的轻量级容器。
2. 使用 GraalVM 本机构建工具生成本机可执行文件。

主要区别如下

1. 环境依赖不同

   方法 1 需要安装 Docker

   方法 2 需要安装 Visual Studio(需要用到部分单个组件：2 个 MSVC，1 个 Windows 10 SDK)
2. 执行的 maven 命令不同

   方法1是`mvn spring-boot:build-image`

   方法2是`mvn -Pnative native:compile`

本 Demo 采用第 1 种方法

先决条件：

1. 安装 graalvm-jdk（环境变量需设置 JAVA_HOME、GRAALVM_HOME）
2. 安装 native-image：`gu.cmd install native-image`，将从 GitHub 下载（gu 是 Graalvm 提供的工具，位于 graalvm-jdk/bin 下）
3. 安装 Docker Desktop