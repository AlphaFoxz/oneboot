tasks.bootJar {
    enabled = false
}
dependencies {
    implementation(project(":core"))
    implementation(project(":domain-preset_sys"))
    implementation("com.github.AlphaFoxz.oneboot-starter:cache_starter")
    implementation("com.github.AlphaFoxz.oneboot-starter:postgres_starter")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-jooq")

    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-oauth2-authorization-server")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
}

tasks.withType<JavaCompile> {
}