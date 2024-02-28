plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("nu.studer.jooq")
    id("org.graalvm.buildtools.native")
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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
    apply(plugin = "org.graalvm.buildtools.native")
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
            mavenBom(project.property("parentProject") as String)
        }
        dependencies {
            dependency(project.property("parentProject") as String)
            dependency("com.github.AlphaFoxz.oneboot-starter:cache_starter:dev-SNAPSHOT")
            dependency("com.github.AlphaFoxz.oneboot-starter:flowable_starter:dev-SNAPSHOT")
            dependency("com.github.AlphaFoxz.oneboot-starter:mysql_starter:dev-SNAPSHOT")
            dependency("com.github.AlphaFoxz.oneboot-starter:meilisearch_starter:dev-SNAPSHOT")
            dependency("com.github.AlphaFoxz.oneboot-starter:postgres_starter:dev-SNAPSHOT")
            dependency("com.github.AlphaFoxz.oneboot-processor:processor:dev-SNAPSHOT")
            dependency("com.github.AlphaFoxz.oneboot-processor:starter:dev-SNAPSHOT")
        }
    }
    tasks.withType<Test> {
        useJUnitPlatform()
        onlyIf {
            //在执行build任务时跳过test
            !gradle.taskGraph.hasTask(":build")
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
    dependencies {
        implementation("com.github.AlphaFoxz:oneboot-core:dev-SNAPSHOT") {
            isChanging = true
        }
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        compileOnly("com.google.code.findbugs:annotations") // 解决编译警告 找不到 javax.annotation.meta.When 的问题

        implementation("com.github.AlphaFoxz.oneboot-processor:processor") {
            isChanging = true
        }
        annotationProcessor("com.github.AlphaFoxz.oneboot-processor:processor")
        annotationProcessor("org.mapstruct:mapstruct-processor")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        developmentOnly("org.springframework.boot:spring-boot-devtools")
    }
}

project(":preset_sys") {
    tasks.bootJar {
        enabled = false
    }
    apply(plugin = "nu.studer.jooq")
    dependencies {
        api("com.github.AlphaFoxz.oneboot-starter:cache_starter")
        api("com.github.AlphaFoxz.oneboot-starter:postgres_starter")
        api("org.springframework.boot:spring-boot-starter-data-rest")

        implementation("org.springframework.boot:spring-boot-starter-aop")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.security:spring-security-oauth2-authorization-server")

        compileOnly("org.jooq:jooq-codegen")
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
        api(project(":preset_sys"))
        api("org.springframework.boot:spring-boot-starter-web") {
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
        }
        api("org.springframework.boot:spring-boot-starter-undertow")

        compileOnly("org.jooq:jooq-codegen")
        jooqGenerator("org.postgresql:postgresql")
        jooqGenerator(project(":gradle_tasks"))
    }
    graalvmNative {
        binaries.all {
            useFatJar = false
            jvmArgs.add("-H:Kind=EXECUTABLE")
            verbose = true
        }
    }
}

project(":sdk") {
    tasks.bootJar {
        enabled = true
        mustRunAfter(":app:bootJar")
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
        api(project(":app"))
        implementation("com.github.AlphaFoxz:restful-dsl-java:springboot3-dev-SNAPSHOT") {
            isChanging = true
        }

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        implementation("org.apache.poi:poi-ooxml")
        implementation("com.deepoove:poi-tl")
        implementation("org.springframework.security:spring-security-oauth2-authorization-server")

        compileOnly("org.jooq:jooq-codegen")
        jooqGenerator("org.postgresql:postgresql")
        jooqGenerator(project(":gradle_tasks"))
    }
}
project(":gradle_tasks") {
    tasks.bootJar {
        enabled = false
    }
    dependencies {
        compileOnly("org.jooq:jooq-codegen")
    }
}
dependencies {
    jooqGenerator("org.postgresql:postgresql")
}
