plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("nu.studer.jooq")
}
tasks.bootJar {
    enabled = false
}
tasks.jar {
    enabled = false
}
allprojects {
    apply(plugin = "java-library")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    group = "com.github.alphafoxz.oneboot"
    version = "0.0.1-alpha.0"
    repositories {
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencyManagement {
        imports {
            org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
//            mavenBom(project.property("parentProject") as String)
        }
        dependencies {
            /** 开发工具 */
            dependency("cn.hutool:hutool-all:5.8.32") // hutool工具包
            dependency("com.google.auto.service:auto-service:1.1.1") // spring自动注入
            dependency("com.github.spotbugs:spotbugs-annotations:4.8.6") // 解决编译警告 找不到 javax.annotation.meta.When 的问题
            dependency("com.squareup:javapoet:1.13.0") // java代码生成器（语法树）
            dependency("org.mapstruct:mapstruct:1.6.0") // java代码生成器
            dependency("org.mapstruct:mapstruct-processor:1.6.0") // java代码生成器
            dependency("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0") // api文档
            dependency("org.mockito:mockito-core:3.+")
            /** 持久化组件 */
            dependency("com.mysql:mysql-connector-j:8.4.0") // mysql驱动
            dependency("mysql:mysql-connector-java:8.0.33") // mysql驱动 （停止更新）
            dependency("org.jooq:jooq-postgres-extensions:3.19.11") // jooq
            dependency("org.jooq:jooq-codegen:3.19.11") // jooq
            dependency("org.hibernate:hibernate-core:6.6.0.Final") // JPA
            /** 文档处理 */
            dependency("org.apache.poi:poi-ooxml:5.3.0") // poi
            dependency("com.deepoove:poi-tl:1.12.2") // poi

//            dependency(project.property("parentProject") as String)
            dependency("com.github.AlphaFoxz.oneboot-starter:cache_starter:dev-SNAPSHOT")
            dependency("com.github.AlphaFoxz.oneboot-starter:flowable_starter:dev-SNAPSHOT")
            dependency("com.github.AlphaFoxz.oneboot-starter:mysql_starter:dev-SNAPSHOT")
            dependency("com.github.AlphaFoxz.oneboot-starter:meilisearch_starter:dev-SNAPSHOT")
            dependency("com.github.AlphaFoxz.oneboot-starter:postgres_starter:dev-SNAPSHOT")
            dependency("com.github.AlphaFoxz.oneboot-processor:processor:dev-SNAPSHOT")
            dependency("com.github.AlphaFoxz.oneboot-processor:starter:dev-SNAPSHOT")
        }
    }
}
subprojects {
    tasks.jar {
        enabled = true
        archiveClassifier = ""
        exclude("**/_compile_only/**")
    }
    tasks.withType<JavaCompile> {
        options.compilerArgs.add("-Amapstruct.defaultComponentModel=spring")
    }
    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(1, TimeUnit.SECONDS)
    }
    java {
        modularity.inferModulePath = true
    }
    dependencies {
//        implementation("com.github.AlphaFoxz:oneboot-core:alpha-SNAPSHOT") {
//            isChanging = false
//        }
        implementation("cn.hutool:hutool-all")
        implementation("org.springframework:spring-context")
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        compileOnly("com.github.spotbugs:spotbugs-annotations") // 解决编译警告 找不到 javax.annotation.meta.When 的问题

        compileOnly("com.github.AlphaFoxz.oneboot-processor:processor") {
//            isChanging = false
        }
        annotationProcessor("com.github.AlphaFoxz.oneboot-processor:processor")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        developmentOnly("org.springframework.boot:spring-boot-devtools")
    }
}

dependencies {
    jooqGenerator("org.postgresql:postgresql")
}
