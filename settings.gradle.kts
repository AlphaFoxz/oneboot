pluginManagement {
    val springbootPluginVersion: String by settings
    val springDependencyManagementPluginVersion: String by settings
    val jooqPluginVersion: String by settings
    plugins {
        id("org.springframework.boot") version "${springbootPluginVersion}"
        id("io.spring.dependency-management") version "${springDependencyManagementPluginVersion}"
        id("nu.studer.jooq") version "${jooqPluginVersion}"
    }
}
//plugins {
//    id("com.gradle.enterprise") version "3.11.4"
//}
rootProject.name = "oneboot"

include(":common", ":preset_sys", ":app", ":sdk", ":tasks")

