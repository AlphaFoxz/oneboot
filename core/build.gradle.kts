dependencies {
    compileOnly("com.github.spotbugs:spotbugs-annotations") // 解决编译警告 找不到 javax.annotation.meta.When 的问题
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.mapstruct:mapstruct")
    annotationProcessor("org.mapstruct:mapstruct-processor")
    compileOnly("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    compileOnly("org.springframework.boot:spring-boot-starter")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    api("cn.hutool:hutool-all")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
}