plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("nu.studer.jooq")
}
apply(plugin = "io.spring.dependency-management")
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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
//    tasks.withType<Test> {
//        useJUnitPlatform()
//    }
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
        }
    }
}
subprojects {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web") {
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
        }
        implementation("org.springframework.boot:spring-boot-starter-undertow")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
        implementation("cn.hutool:hutool-all")
        implementation("org.apache.thrift:libthrift")

        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        compileOnly("org.mapstruct:mapstruct")
        annotationProcessor("org.mapstruct:mapstruct-processor")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        testImplementation("org.springframework.boot:spring-boot-starter-test")

        //implementation "org.springframework.boot:spring-boot-starter-data-jpa"
        implementation("org.springframework.boot:spring-boot-starter-jooq")
        implementation("org.jooq:jooq-codegen")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        runtimeOnly("com.h2database:h2")
        runtimeOnly("org.postgresql:postgresql")
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
    }
}

project(":preset_sys") {
    tasks.bootJar {
        enabled = false
    }
    tasks.jar {
        enabled = true
    }
    dependencies {
        implementation(project(":common"))
    }
}

project(":app") {
    tasks.bootJar {
        enabled = true
        mainClass = "com.github.alphafoxz.oneboot.app.AppApplication"
    }
    dependencies {
        implementation(project(":common"))
        implementation(project(":preset_sys"))
        implementation("org.springframework.boot:spring-boot-starter-security")
    }
}

project(":sdk") {
    tasks.bootJar {
        enabled = false
    }
    tasks.jar {
        enabled = true
    }
    dependencies {
        implementation(project(":common"))
        implementation(project(":app"))
        implementation(project(":preset_sys"))
    }
}