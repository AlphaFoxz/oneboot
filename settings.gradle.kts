pluginManagement {
    val springbootPluginVersion: String by settings
    val springDependencyManagementPluginVersion: String by settings
    val jooqPluginVersion: String by settings
    val jooqGeneratePluginVersion: String by settings
    val extraJavaModuleInfoVersion: String by settings
    plugins {
        id("org.springframework.boot") version springbootPluginVersion
        id("io.spring.dependency-management") version springDependencyManagementPluginVersion
        id("org.jooq.jooq-codegen-gradle") version jooqGeneratePluginVersion
        id("org.gradlex.extra-java-module-info") version extraJavaModuleInfoVersion
    }
}
rootProject.name = "oneboot"

include(
    ":sdk",
    ":app",
    ":preset_sys",
    ":domain-preset_sys",
    ":core",
    ":_tasks",
    ":_tasks:strategy",
)
