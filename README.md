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

## TODO list

### 组件整合

- [X]  SpringSecurity - 安全模块
- [X]  SpringDoc - API文档
- [X]  Thrift - Rpc服务

### 代码生成

- [X]  rpc跨语言代码
- [ ]  SpringDoc接口
- [ ]  typescript前端代码

### 特色功能

- [ ]  项目功能检查（进行中）
- [ ]  接口一览
- [ ]  独立的SDK客户端（Windows、Linux、Android）
- [ ]  全平台统一枚举标准
- [ ]  用户可拓展的命令行终端
