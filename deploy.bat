chcp 65001
@echo off

REM 进入项目根目录（根据你的项目路径修改）
cd /d %~dp0

echo ===========================
echo 1. 使用 Maven 打包项目（跳过测试）
call mvn clean package -DskipTests
echo Maven 命令返回码：%errorlevel%

if errorlevel 1 (
    echo Maven 打包失败，终止部署！
    pause
    exit /b 1
)

echo ===========================
echo 2. 停止并移除 login-api 容器（如果存在）
REM 尝试停止 login-api 容器，如果容器不存在或已停止，则忽略错误
call docker stop login-api-container >NUL 2>&1
REM 尝试移除 login-api 容器，如果容器不存在，则忽略错误
call docker rm login-api-container >NUL 2>&1

echo ===========================
echo 3. 重新构建 login-api 镜像并启动 login-api 服务
REM --build 确保构建最新的 login-api 镜像
REM -d 后台运行
REM login-api 指定只操作 login-api 服务
call docker compose up -d --build login-api

if errorlevel 1 (
    echo Docker Compose 启动 login-api 失败，终止部署！
    pause
    exit /b 1
)

echo ===========================
echo 部署完成！

pause