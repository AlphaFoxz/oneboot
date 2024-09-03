tasks.bootJar {
    enabled = true
}
//tasks.withType<Test> {
//    useJUnitPlatform()
//    onlyIf {
//        //在执行build任务时跳过test
//        !gradle.taskGraph.hasTask(":build")
//    }
//}
dependencies {
    implementation(project(":core"))
    implementation(project(":preset_sys"))
    implementation(project(":domain-preset_sys"))
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
    implementation("org.flywaydb:flyway-core")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(project(":preset_sys"))
}