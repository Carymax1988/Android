LocationManager是个系统服务，不能直接实例化一个LocationManager对象，需要调用方法getSystemService(Context.LOCALTION_SERVICE)来实例化。

功能：
1.查询所有的LocationProviders的列表，得到最近已知位置坐标。
2.注册或注销一个位置提供商，以便周期性地更新用户的当前位置。
3.如果设备是在一个给定纬度、经度及距离（指定半径，单位米）范围内，可以注册或者注销一个特定的Intent。