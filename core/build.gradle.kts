tasks.bootJar {
    enabled = false
}
tasks.jar {
    enabled = true
}
dependencies {
    // SpringMVC中的Jackson处理http时间转换
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    api("cn.hutool:hutool-all")
}
