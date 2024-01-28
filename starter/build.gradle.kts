tasks.bootJar {
    enabled = false
}
dependencies {
    compileOnly(project(":core"))
    implementation(project(":starter:flowable_starter"))
//    implementation(project(":starter:meilisearch_starter"))
}
