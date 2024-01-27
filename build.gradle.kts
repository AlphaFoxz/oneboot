plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("nu.studer.jooq")
//    id("org.graalvm.buildtools.native")
}
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
tasks.bootJar {
    enabled = false
}
allprojects {
    apply(plugin = "java-library")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
//    apply(plugin = "org.graalvm.buildtools.native")
    group = "com.github.alphafoxz.oneboot"
    version = "0.0.1-alpha.0"
    repositories {
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
    dependencyManagement {
        imports {
            org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
        }
        dependencies {
            dependency("org.mapstruct:mapstruct:1.5.5.Final")
            dependency("org.mapstruct:mapstruct-processor:1.5.5.Final")
            dependency("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
            dependency("cn.hutool:hutool-all:5.8.25")
            dependency("org.jooq:jooq-postgres-extensions:3.18.7")
            dependency("org.jooq:jooq-codegen:3.18.7")
            dependency("com.google.code.findbugs:annotations:3.0.1")
            dependency("org.apache.poi:poi-ooxml:5.2.5")
            dependency("com.deepoove:poi-tl:1.12.1")
        }
    }
}
subprojects {
    tasks.jar {
        enabled = true
        archiveClassifier = ""
    }
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web") {
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
        }
        implementation("org.springframework.boot:spring-boot-starter-undertow")
        implementation("org.springframework.boot:spring-boot-starter-aop")
        implementation("org.springframework.boot:spring-boot-starter-cache")
        implementation("org.springframework.boot:spring-boot-starter-data-rest")
        implementation("com.github.ben-manes.caffeine:caffeine")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
        implementation("cn.hutool:hutool-all")

        compileOnly("com.google.code.findbugs:annotations") // 解决编译警告 找不到 javax.annotation.meta.When 的问题
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        compileOnly("org.mapstruct:mapstruct")
        annotationProcessor("org.mapstruct:mapstruct-processor")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        testImplementation("org.springframework.boot:spring-boot-starter-test")

        implementation("org.springframework.boot:spring-boot-starter-jooq")
        developmentOnly("org.springframework.boot:spring-boot-devtools")

        compileOnly("org.jooq:jooq-codegen")
        implementation("org.postgresql:postgresql")
    }
}

project(":preset_sys") {
    tasks.bootJar {
        enabled = false
    }
    apply(plugin = "nu.studer.jooq")
    dependencies {
        implementation(project(":core"))

        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.security:spring-security-oauth2-authorization-server")
        jooqGenerator("org.postgresql:postgresql")
        jooqGenerator(project(":gradle_tasks"))
    }
}

project(":app") {
    tasks.bootJar {
        enabled = true
    }
    tasks.withType<Test> {
        useJUnitPlatform()
        onlyIf {
            //在执行build任务时跳过test
            !gradle.taskGraph.hasTask(":build")
        }
    }
    apply(plugin = "nu.studer.jooq")
    dependencies {
        implementation(project(":core"))
        implementation(project(":starter"))
        implementation(project(":preset_sys"))

        jooqGenerator("org.postgresql:postgresql")
        jooqGenerator(project(":gradle_tasks"))
    }
}

project(":sdk") {
    tasks.bootJar {
        enabled = true
    }
    tasks.withType<Test> {
        useJUnitPlatform()
        onlyIf {
            //在执行build任务时跳过test
            !gradle.taskGraph.hasTask(":build")
        }
    }
    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(1, TimeUnit.SECONDS)
    }
    apply(plugin = "nu.studer.jooq")
    dependencies {
        implementation(project(":core"))
        implementation(project(":starter"))
        implementation(project(":app"))
        implementation(project(":preset_sys"))
        implementation("com.github.AlphaFoxz.restful-dsl-java:spring-boot-starter-restful-dsl:3.0.0-alpha.2")

        compileOnly("com.github.AlphaFoxz:oneboot-annotation:test2-SNAPSHOT") {
            isChanging = true
        }
        annotationProcessor("com.github.AlphaFoxz:oneboot-annotation:test2-SNAPSHOT") {
            isChanging = true
        }

        implementation("org.springframework.security:spring-security-oauth2-authorization-server")
        implementation("org.apache.poi:poi-ooxml")
        implementation("com.deepoove:poi-tl")
        jooqGenerator("org.postgresql:postgresql")
        jooqGenerator(project(":gradle_tasks"))
    }
}
project(":gradle_tasks") {
    tasks.bootJar {
        enabled = false
    }
}
dependencies {
    jooqGenerator("org.postgresql:postgresql")
}

tasks.withType<Test> {
    useJUnitPlatform()
    onlyIf {
        //在执行build任务时跳过test
        !gradle.taskGraph.hasTask(":build")
    }
}
