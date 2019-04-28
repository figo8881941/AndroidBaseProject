#./gradlew clean  clean项目
#./gradlew build  构建项目
# 指定flavor包：./gradlew assemble(flavor)(Debug|Release)
#./gradlew assembleDebug or /gradlew aD 编译并打Debug包
#./gradlew assembleRelease or /gradlew aR 编译并打Release的包
#./gradlew installRelease or /gradlew iR Release模式打包并安装
#./gradlew installDebug or /gradlew iD Debug模式打包并安装
#./gradlew uninstallRelease or ./gradlew uR 卸载Release模式包
#./gradlew uninstallDebug or ./gradlew uD 卸载Debug模式包

#设置系统临时JAVA_HOME变量，让这里gradle使用的JDK跟Android Studio的一致
export JAVA_HOME=/Applications/Android\ Studio.app/Contents/jre/jdk/Contents/Home
rm -rf ./app/build
./gradlew installRelease
