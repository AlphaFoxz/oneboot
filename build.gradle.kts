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
    java {
        modularity.inferModulePath = true
    }
    dependencies {
        implementation("com.github.AlphaFoxz:oneboot-core:alpha-SNAPSHOT") {
            isChanging = false
        }
        implementation("cn.hutool:hutool-all")
        implementation("org.springframework:spring-context")
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        compileOnly("com.google.code.findbugs:annotations") // 解决编译警告 找不到 javax.annotation.meta.When 的问题

        compileOnly("com.github.AlphaFoxz.oneboot-processor:processor") {
            isChanging = false
        }
        annotationProcessor("com.github.AlphaFoxz.oneboot-processor:processor")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        developmentOnly("org.springframework.boot:spring-boot-devtools")
    }
}

dependencies {
    jooqGenerator("org.postgresql:postgresql")
}
