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
    implementation("com.meilisearch.sdk:meilisearch-java:0.11.5")
}
