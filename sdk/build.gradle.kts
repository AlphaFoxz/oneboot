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
    implementation(project(":core"))
    implementation(project(":app"))
    implementation(project(":preset_sys"))
    implementation("com.github.AlphaFoxz:restful-dsl-java:springboot3-dev-SNAPSHOT") {
//        isChanging = true
    }
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.apache.poi:poi-ooxml")
    implementation("com.deepoove:poi-tl")
    implementation("org.springframework.security:spring-security-oauth2-authorization-server")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")

    compileOnly("org.jooq:jooq-codegen")
}