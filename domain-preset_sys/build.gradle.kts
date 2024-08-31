dependencies {
    compileOnly("jakarta.annotation:jakarta.annotation-api")
    compileOnly("org.springframework.boot:spring-boot-starter-web")

    implementation(project(":core"))
    implementation("org.springframework:spring-context")

    testImplementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.withType<JavaCompile> {
}