pluginManagement {
    val springbootPluginVersion: String by settings
    val springDependencyManagementPluginVersion: String by settings
    val jooqPluginVersion: String by settings
    val extraJavaModuleInfoVersion: String by settings
    plugins {
        id("org.springframework.boot") version springbootPluginVersion
        id("io.spring.dependency-management") version springDependencyManagementPluginVersion
        id("nu.studer.jooq") version jooqPluginVersion
        id("org.gradlex.extra-java-module-info") version extraJavaModuleInfoVersion
    }
}
rootProject.name = "oneboot"

include(
//    ":gradle_tasks",
    ":domain-preset_sys",
    ":preset_sys",
    ":app",
    ":sdk",
)
