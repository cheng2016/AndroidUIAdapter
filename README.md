# AndroidUIAdapter
安卓屏幕适配方案，根据设计图画布大小和像素尺寸实时适配，一次配置永久使用，适配所有市面上各种大小不一的安卓手机屏幕，可以直接在Android studio的XML编辑器中直接预览不同手机屏幕的效果，提高工作效率，且完全不会有性能问题，极易上手

### 使用

1. **第一步，复制该项目res目录下包含dimens.xml的velues文件夹到自己的项目的res目录下，其中values文件夹只复制其dimens.xml文件**

   ![](screenshot\dev20170607225734.png)

2. **直接在项目中引用， px / 密度 = dp（通常密度为2，例：10px / 2 =  5dp）**

   ```
   <Button
       android:layout_width="match_parent"
       android:layout_height="@dimen/widget_size_45"
       android:layout_marginTop="@dimen/widget_size_10"
       android:textSize="@dimen/font_size_10"/>
   ```

   ​

#### 配置（若项目要求UI极其严格，可进行以下精准配置）

##### 第一步：在你的项目的DimenTool.java文件中中注明你的设计稿的尺寸，并进行修改成你的设计图的尺寸

```
//根据UI画布大小比例进行换算，UI图相对分辨率为1334x750（注意这里只注重设备的宽度）
int density = 2;//默认密度是160,设备默认密度是320,所以缩放因子是320/160
int width = 750 / density;
```

![](screenshot/pic20170514140636.png)
##### 第二步：执行Main函数生成相对应的dimens.xml文件
![](screenshot/pic20170514140953.png)
##### 第三步：根据你的设计图标注算出相应的dp，并直接引用
通常标注图上是px为单位，换算成dp公式为 --> px/密度 = dp ,（密度现在市场上一般为2，详情参考下面链接），然后直接在你想要适配的xml中进行引用算出的dp，如设计图上写着32px即为16dp

![](screenshot/pic20170514132816.png) 

#### 屏幕适配参考链接

[一分钟了解Android屏幕 ldpi mdpi hdpi xhdpi](http://www.2cto.com/kf/201506/404773.html)

[Android屏幕大小适配问题解决](http://www.2cto.com/kf/201405/301671.html)

[Android屏幕适配小技巧sw<n>dp](http://blog.csdn.net/chenzujie/article/details/9874859)

[AndroidAutoLayout](https://github.com/hongyangAndroid/AndroidAutoLayout)



### Contact Me

- Github: github.com/cheng2016
- Email: mitnick.cheng@outlook.com
- QQ: 1102743539





