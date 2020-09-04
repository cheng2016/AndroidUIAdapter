# AndroidUIAdapter
安卓屏幕适配方案，只需一个步骤即可使用，也可根据自己项目设计图画布大小和像素尺寸实时精准适配，适配所有市面上各种大小不一的安卓手机屏幕，特点是可以直接在Android studio的XML编辑器中直接预览不同手机屏幕的适配效果，提高工作效率，且完全不会有性能问题，极易上手

## 说明

dp只是相对单位；

dp = 屏幕相对分辨率（如：1920 * 1080） / 相对密度

举个栗子：

dp = （相对宽度）1080 / （密度）3 = 360 dp;  则其自动适配的文件夹即为 values-sw360dp

### 使用

1. **第一步，复制该项目res目录下包含dimens.xml的velues文件夹到自己的项目的res目录下，其中values文件夹只复制其dimens.xml文件**

   ![](screenshot/dev20170607225734.png)

2. **直接在项目中引用， px / 密度 = dp（通常密度为2，例：10px / 2 =  5dp）**

   ```
   <Button
       android:layout_width="match_parent"
       android:layout_height="@dimen/widget_size_45"
       android:layout_marginTop="@dimen/widget_size_10"
       android:textSize="@dimen/font_size_10"/>
   ```

   ​

### 可选配置

##### 第一步：在你的项目的DimenTool.java文件中中注明你的设计稿的尺寸（设计稿尺寸请联系你们公司的美工确认哦!），并进行修改成你的设计图的尺寸

```
        /**
         * 密度因子
         */
        int density;
        /**
         * 屏幕相对宽度
         */
        int width;
        /**
         * 例子1.根据UI画布大小比例进行换算，假设UI图分辨率为1334x750
         * 设备默认缩放因子密度为 density  = 320 / 160 , 即 density = 2
         * 宽度 width = 750
         * 可选项，根据你实际的UI设计图来定义
         */
        density = 2;
        width = 750 / density;
        /**
         * 例子2.根据UI画布大小比例进行换算，假设UI图分辨率为 1920x1080
         * 高分率缩放因子密度一般为 density = 480 / 160 , 即 density = 3
         * 宽度 width = 1080
         * 可选项，根据你实际的UI设计图来定义
         */
        density = 3;
        width = 1080 / density;
```

![](screenshot/pic20170514140636.png)
##### 第二步：执行Main函数生成相对应的dimens.xml文件
![](screenshot/pic20170514140953.png)



### 注意

**如果dimens.xml中没有你们想要的尺寸，打开values文件下的dimens.xml文件中添加即可，再执行main函数生成相对应尺寸的即可**

![](screenshot/dev20170607231430.png)


## Download  [![Stable Version](https://jitpack.io/v/cheng2016/AndroidUIAdapter.svg)](https://jitpack.io/#cheng2016/AndroidUIAdapter)
    implementation 'com.github.cheng2016:AndroidUIAdapter:V1.0.0'
<br/>


#### 屏幕适配参考链接

[一分钟了解Android屏幕 ldpi mdpi hdpi xhdpi](http://www.2cto.com/kf/201506/404773.html)

[Android屏幕大小适配问题解决](http://www.2cto.com/kf/201405/301671.html)

[Android屏幕适配小技巧sw<n>dp](http://blog.csdn.net/chenzujie/article/details/9874859)

[AndroidAutoLayout](https://github.com/hongyangAndroid/AndroidAutoLayout)

[约束布局ConstraintLayout强烈推荐](https://www.jianshu.com/p/17ec9bd6ca8a)

## Contact Me

- Github: github.com/cheng2016
- Email: mitnick.cheng@outlook.com
- QQ: 1102743539


# License

    Copyright 2016 cheng2016,Inc.
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



