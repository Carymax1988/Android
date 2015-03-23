MonkeyRunner
1、编写语言：Python
2、运行环境：Python环境，adb链接PC运行
3、测试对象：UI测试
4、测试限制：主要使用坐标，逻辑判断较差

Instrumentation
1、编写语言：Java
2、运行环境：adb命令启动或者手机中直接启动测试
3、测试限制：单个Activity测试，需与测试应用相同的签名
4、测试对象：主要用于白盒测试和UI测试

Robotium
1、编写语言：java基于Instrumentation封装
2、运行环境：与Instrumentation相同
3、测试限制：与Instrumentation相同

UiAutomator测试框架
自动化测试

 * android create uitest-project -n demo -t 19 -p D:\AndroidStudy\TestAutomator
 * adb push D:\AndroidStudy\TestAutomator\bin\demo.jar /data/local/tmp
 * adb shell uiautomator runtest demo.jar -c com.liujiaqi.Test