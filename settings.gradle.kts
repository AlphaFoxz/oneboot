pluginManagement {
    val springbootPluginVersion: String by settings
    val springDependencyManagementPluginVersion: String by settings
    val jooqPluginVersion: String by settings
    val graalvmBuildtoolsVersion: String by settings
    plugins {
        id("org.springframework.boot") version springbootPluginVersion
        id("io.spring.dependency-management") version springDependencyManagementPluginVersion
        id("nu.studer.jooq") version jooqPluginVersion
        id("org.graalvm.buildtools.native") version graalvmBuildtoolsVersion
    }
}

rootProject.name = "oneboot"

include(
    ":gradle_tasks",
    ":core",
    ":starter",
//    ":starter:meilisearch_starter",
    ":preset_sys",
    ":app",
    ":sdk",
)

