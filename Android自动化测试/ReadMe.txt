MonkeyRunner
1����д���ԣ�Python
2�����л�����Python������adb����PC����
3�����Զ���UI����
4���������ƣ���Ҫʹ�����꣬�߼��жϽϲ�

Instrumentation
1����д���ԣ�Java
2�����л�����adb�������������ֻ���ֱ����������
3���������ƣ�����Activity���ԣ��������Ӧ����ͬ��ǩ��
4�����Զ�����Ҫ���ڰ׺в��Ժ�UI����

Robotium
1����д���ԣ�java����Instrumentation��װ
2�����л�������Instrumentation��ͬ
3���������ƣ���Instrumentation��ͬ

UiAutomator���Կ��
�Զ�������

 * android create uitest-project -n demo -t 19 -p D:\AndroidStudy\TestAutomator
 * adb push D:\AndroidStudy\TestAutomator\bin\demo.jar /data/local/tmp
 * adb shell uiautomator runtest demo.jar -c com.liujiaqi.Test