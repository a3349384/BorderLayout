# BorderLayout
一个用于Android的支持CornerRadius的ViewGroup

gradle:
compile 'com.a3349384.borderlayout:library:1.0.1'

![image](https://raw.githubusercontent.com/a3349384/BorderLayout/master/BorderLayout_1.png)

采用GradientDrawable实现。
使用方式如下：

    <com.zmy.BorderLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        app:BorderColor="@android:color/black"
        app:BorderWidth="1dp"
        app:BorderBackgroundColor="@android:color/holo_red_light"
        app:CornerRadius="10">
    </com.zmy.BorderLayout>

其中：

    BorderColor为边框颜色
    
    BorderWidth为边框宽度
    
    BorderBackgroundColor为背景色
    
    CornerRadius为四周弧度，单位为dp。支持如下三种格式：
     1、“10”，表示四周弧度均为10dp
     2、“5,10”,表示四周弧度为5dp,10dp,5dp,10dp（左上右下的顺序）
     3、“5，10，12，20”，表示四周弧度为5dp,10dp,12dp,20dp（左上右下的顺序）
