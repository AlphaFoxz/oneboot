dependencies {
    compileOnly("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    compileOnly("com.github.spotbugs:spotbugs-annotations") // 解决编译警告 找不到 javax.annotation.meta.When 的问题
    compileOnly("org.mapstruct:mapstruct")
    compileOnly("org.projectlombok:lombok")
    compileOnly("org.springframework.boot:spring-boot-starter")

    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    compileOnly("org.jooq:jooq-codegen")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")

    api("cn.hutool:hutool-all")
}