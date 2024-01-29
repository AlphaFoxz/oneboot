//plugins {
//    id("org.gradlex.extra-java-module-info")
//}
//extraJavaModuleInfo {
//    failOnAutomaticModules = true
//    module("org.flowable:flowable-spring-boot-starter", "flowable.engine") {
//        exportAllPackages()
//        requireAllDefinedDependencies()
//    }
//}
tasks.bootJar {
    enabled = false
}
dependencies {
    implementation(project(":core"))

    implementation("org.flowable:flowable-spring-boot-autoconfigure:7.0.1")
    implementation("org.flowable:flowable-spring-boot-starter:7.0.1")
//    implementation("org.flowable:flowable-spring-boot-starter-rest:7.0.0")
//    implementation("org.flowable:flowable-ui-admin-conf:7.0.0")
//    implementation("org.flowable:flowable-ui-idm-conf:7.0.0")
//    implementation("org.flowable:flowable-ui-modeler-conf:7.0.0")
//    implementation("org.flowable:flowable-ui-task-conf:7.0.0")
}