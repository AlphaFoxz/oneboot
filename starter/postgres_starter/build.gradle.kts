//plugins {
//    id("org.gradlex.extra-java-module-info")
//}
//extraJavaModuleInfo {
//    failOnAutomaticModules = true
//    module("com.meilisearch.sdk:meilisearch-java", "meilisearch.java") {
//        exportAllPackages()
//        requireAllDefinedDependencies()
//    }
//}
tasks.bootJar {
    enabled = false
}
dependencies {
    api("org.springframework.boot:spring-boot-starter-jooq")
    api("org.postgresql:postgresql")
}
