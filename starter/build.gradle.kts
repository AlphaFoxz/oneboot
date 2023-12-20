dependencies {
    implementation(project(":common"))
    implementation(project(":starter:meilisearch_starter"))

    compileOnly("org.springframework.boot:spring-boot-autoconfigure")
}
