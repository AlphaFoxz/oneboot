# Oneboot - 致力于实现一套企业应用开发最优解决方案

## 项目结构说明

```log
- .sdk //SDK工具私有目录
- app //应用模块，包含启动类、业务代码
  - config //应用模块配置类
  - toolkit //应用模块工具类
- common //公共依赖模块
  - config //公共模块配置类，会被扫描
  - toolkit //各种公共工具类
- preset_sys //预置系统模块
  - **待定**
- sdk //开发工具模块，包含代码生成和其他各种功能
  - config
  - controller
  - gen
  - service
  - SdkApplication.java //SDK启动类，只在本地开发的时候使用
```

## 关于代码生成

### java rpc代码

1. 依赖了[Apache Thrift](https://thrift.apache.org/)框架
2. 由java通过命令行对thrift可执行文件的调用实现代码生成

### java restful接口部分

1. java端存储了thrift模板，和rpc部分一致，方便管理
2. 在[oneboot_rust](https://github.com/AlphaFoxz/oneboot_rust)项目中，由[pest](https://crates.io/crates/pest)这个crate实现了一个针对thrift语法的解析器，输出[语法树](https://baike.baidu.com/item/%E8%AF%AD%E6%B3%95%E6%95%B0?fromtitle=%E8%AF%AD%E6%B3%95%E6%A0%91)
3. 然后通过rpc代码进行通信，将语法树（JSON）发送给java
4. java通过对语法树进行遍历，构建出对应的java bean。
5. 通过java service对bean进行分析，生成指定代码

## TODO list

### 组件整合

- [X]  SpringSecurity - 安全模块
- [X]  SpringDoc - API文档
- [X]  Thrift - Rpc服务
- [ ]  Meilisearch - 搜索引擎（场景：面向中小型项目，索引上限2TiB）

### 代码生成

- [X]  rpc跨语言代码
- [X]  SpringDoc接口
- [ ]  typescript前端代码

### 特色功能

- [ ]  项目功能检查（进行中）
- [ ]  接口一览
- [ ]  独立的SDK客户端（Windows、Linux、Android）
- [ ]  全平台统一枚举标准
- [ ]  用户可拓展的命令行终端
