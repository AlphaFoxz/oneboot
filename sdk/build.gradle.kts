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
}