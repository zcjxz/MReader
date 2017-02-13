# 项目介绍

## 1.简单介绍

    这是一款集IT资料，妹子福利，电影，书籍的阅读终端。

	本人负责 Android 版 app 的开发
	
	API 由干货集中营，和豆瓣提供
	
	整体设计符合 Metro Design 风格
	
	网络请求使用的是 Retrofit + RxJava

## 2.项目分析
    
	整体界面使用 DrawerLayout + （ViewPager+Fragment）搭建

	干货界面使用 TabLayout + ViewPager 搭建
	
	界面间的切换使用懒加载模式，为用户节省流量
	
	福利界面使用 RecycleView 实现瀑布流，完成了下拉刷新，上拉加载等功能
