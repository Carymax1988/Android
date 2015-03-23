Volley是Android平台网络通信库：更快、更简单、更健壮。（数据量不大，通信频繁）
Volley提供的功能：
1.JSON、图片（异步）
2.网络请求的排序
3.网络请求的优先级处理
4.缓存
5.多级别的取消请求
6.与Activity生命周期联动

获取volley
git clone https://android.googlesource.com/platform/frameworks/volley
cd volley
git checkout 008e0cc8
android update project -p .
ant jar
