### Android 组件化，模块化架构探索
标签：Android
    
    本工程以架构方面的搭建为主，具体细节功能实现为辅
    第一期主要针对组件化，模块化，设计模式
    第二期主要尝试更高级的主题，如插件化，热修复，混合开发等

### 第一期 公共基础库

###### **网络层**
     封装原则：接口隔离
              灵活切换实现库，
              支持自定义实现功能类
     设计模式: 单例+Builder+策略模式+状态模式+工厂模式          
- 网络框架(Okhttp+Retrofit+Rxjava)
- 图片框架(Glide)

###### **数据存储层**

- Sqlite （原生sqlite+ORM框架的封装）
- SharedPreferences（工具类）
- Files（工具类）
- ContentProvider

###### **系统组件层**

- 组件的生命周期
- 组件通信
- 设计模式（MVP+RxJava+RxBus）
- 数据序列化封装
- Modular模块的封装
- Gradle多工程项目的搭建

###### **调式层**

- 正常的Log打印
- 数据库表结构查看
- 网络请求日志查看

###### **Ui显示层**

- ToolBar与沉浸栏
- 信息提示toast,notify,dialog,popupwindow,各种加载进度条
- 刷新控件
- 选项卡控件
- 侧滑控件



