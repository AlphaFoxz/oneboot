pluginManagement {
    val springbootPluginVersion: String by settings
    val springDependencyManagementPluginVersion: String by settings
    val jooqPluginVersion: String by settings
    plugins {
        id("org.springframework.boot") version springbootPluginVersion
        id("io.spring.dependency-management") version springDependencyManagementPluginVersion
        id("nu.studer.jooq") version jooqPluginVersion
    }
}
rootProject.name = "oneboot"

include(
    ":gradle_tasks",
    ":common",
    ":starter",
    ":starter:meilisearch_starter",
    ":preset_sys",
    ":app",
    ":sdk",
)

