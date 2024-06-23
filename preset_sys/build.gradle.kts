tasks.bootJar {
    enabled = false
}
dependencies {
    api("com.github.AlphaFoxz.oneboot-starter:cache_starter")
    api("com.github.AlphaFoxz.oneboot-starter:postgres_starter")
    api("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.hibernate:hibernate-core")

    implementation(project(":domain-preset_sys"))
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-oauth2-authorization-server")
}

tasks.withType<JavaCompile> {
}