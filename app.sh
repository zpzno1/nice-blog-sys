#!/bin/bash
APP_NAME=NiceBlog
SOURCE_CODE_PATH="/webapp/nice-blog-sys"
TARGET_JAR_PATH=$SOURCE_CODE_PATH/blog-sys-web/target/NiceBlog.jar

#使用说明，用来提示输入参数
usage() {
    echo "Usage: sh 执行脚本.sh [start|stop|restart|status]"
    exit 1
}

#检查程序是否在运行
is_exist() {
    pid=`ps -ef|grep $APP_NAME|grep -v grep|awk '{print $2}'`
    #如果不存在返回1，存在返回0
    if [ -n "$pid" ]
    then
         return 0
    else
         return 1;
    fi
}

#编译打包源代码
package(){
    cd ${SOURCE_CODE_PATH}
    mvn clean compile package
}

#拉取源代码
pull(){
    cd ${SOURCE_CODE_PATH}
    git pull origin master
}

#启动方法
start() {
    is_exist
        if [ $? -eq "0" ]; then
        echo "${APP_NAME} is already running. pid=${pid},you can stop or restart it."
    else
       pull
       echo "[step 1]: ${APP_NAME} SUCCESS PULL CODE FROM GITEE(拉取源码成功)."
       package
       echo "[step 2]: ${APP_NAME} SUCCESS BUILD CODE(源码构建成功)."
       nohup java -Xms512m -Xmx1024m -jar ${TARGET_JAR_PATH}  --spring.profiles.active=mysql-production ->/dev/null 2>&1 &
       echo "[step 3]: ${APP_NAME} SUCCESS RUNNING AND THE PID IS ${pid}(服务启动成功)."
	fi
}
#停止方法
stop() {
    is_exist
    if [ $? -eq "0" ]; then
        kill -9 $pid
        echo "${APP_NAME} is stoped."
    else
        echo "${APP_NAME} is not running"
    fi
}
#输出运行状态
status() {
    is_exist
    if [ $? -eq "0" ]; then
        echo "${APP_NAME} is running. Pid is ${pid}"
    else
        echo "${APP_NAME} is NOT running."
    fi
}
#重启
restart() {
    stop
    start
}
#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
    "start")
        start
        ;;
    "stop")
        stop
        ;;
    "status")
        status
        ;;
    "restart")
        restart
        ;;
    *)
    usage
    ;;
esac
