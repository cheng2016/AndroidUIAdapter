# AndroidUIAdapter
安卓屏幕适配方案，适配所有市面上各种大小不一的安卓手机屏幕

#### 用法

##### 第一步
在你的项目的DimenTool.java文件中中注明你的设计稿的尺寸，并进行修改成你的设计图的尺寸
![](screenshot/pic20170514140636.png)
##### 第二步执行Main函数生成相对应的demens.xml文件
![](screenshot/pic20170514140953.png)
##### 第三步，根据你的设计图标注算出相应的dp（通常标注图上是px为单位，换算成dp公式为 ==》px/密度 = dp ,[密度现在市场上一般为2，详情参考下面链接]），然后直接在你想要适配的xml中进行引用算出的dp
![](screenshot/pic20170514132816.png)

#### 屏幕适配参考链接

[一分钟了解Android屏幕 ldpi mdpi hdpi xhdpi](http://www.2cto.com/kf/201506/404773.html)

[Android屏幕大小适配问题解决](http://www.2cto.com/kf/201405/301671.html)

[Android屏幕适配小技巧sw<n>dp](http://blog.csdn.net/chenzujie/article/details/9874859)
