plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("nu.studer.jooq")
}
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
repositories {
    mavenCentral()
}
tasks.bootJar {
    enabled = false
}
allprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    group = "com.github.alphafoxz.oneboot"
    version = "0.0.1-alpha.0"
    repositories {
        mavenCentral()
    }
    dependencyManagement {
        imports {
            org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
        }
        dependencies {
            dependency("org.mapstruct:mapstruct:1.5.3.Final")
            dependency("org.mapstruct:mapstruct-processor:1.5.3.Final")
            dependency("org.apache.thrift:libthrift:0.18.1")
            dependency("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
            dependency("cn.hutool:hutool-all:5.8.16")
            dependency("org.jooq:jooq-postgres-extensions:3.18.6")
            dependency("org.jooq:jooq-codegen:3.18.6")
        }
    }
}
subprojects {
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
        implementation("org.apache.thrift:libthrift")

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

project(":common") {
    tasks.bootJar {
        enabled = false
    }
    tasks.jar {
        enabled = true
    }
    dependencies {
        implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
        implementation("org.springframework.boot:spring-boot-starter-data-redis")
    }
}

project(":preset_sys") {
    tasks.bootJar {
        enabled = false
    }
    tasks.jar {
        enabled = true
    }
    apply(plugin = "nu.studer.jooq")
    dependencies {
        implementation(project(":common"))

        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.security:spring-security-oauth2-authorization-server")
        jooqGenerator("org.postgresql:postgresql")
        jooqGenerator(project(":tasks"))
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
        implementation(project(":common"))
        implementation(project(":preset_sys"))

        jooqGenerator("org.postgresql:postgresql")
        jooqGenerator(project(":tasks"))
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
    apply(plugin = "nu.studer.jooq")
    dependencies {
        implementation(project(":common"))
        implementation(project(":app"))
        implementation(project(":preset_sys"))

        implementation("org.springframework.security:spring-security-oauth2-authorization-server")
        jooqGenerator("org.postgresql:postgresql")
        jooqGenerator(project(":tasks"))
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

