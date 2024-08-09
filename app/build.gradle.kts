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
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation(project(":preset_sys"))
    implementation(project(":domain-preset_sys"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(project(":preset_sys"))
}