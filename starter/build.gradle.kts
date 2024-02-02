tasks.bootJar {
    enabled = false
}
tasks.jar {
    enabled = false
}
subprojects {
    tasks.bootJar {
        enabled = false
    }
    tasks.jar {
        enabled = true
        archiveClassifier.set("")
    }
    dependencies {
        implementation(project(":core"))
        compileOnly("org.springframework.boot:spring-boot-starter-web")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    }
}
