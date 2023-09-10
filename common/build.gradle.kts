import nu.studer.gradle.jooq.JooqEdition
import org.jooq.meta.jaxb.Logging
import org.jooq.meta.jaxb.Property
import java.io.FileInputStream
import java.util.*

fun getPropertyValue(key: String): String {
    val configFilePath = "$rootDir/gradle.properties"
    val properties = Properties()
    FileInputStream(configFilePath).use { inputStream ->
        properties.load(inputStream)
    }
    return properties.getProperty(key)
}

jooq {
    version.set(getPropertyValue("jooqGeneratePluginVersion"))
    edition.set(JooqEdition.OSS)
    configurations {
        create("dev") {
            generateSchemaSourceOnCompilation.set(false)

            jooqConfiguration.apply {
                logging = Logging.WARN
                jdbc.apply {
                    driver = getPropertyValue("dev.driver")
                    url = getPropertyValue("dev.url")
                    user = getPropertyValue("dev.user")
                    password = getPropertyValue("dev.password")
                    properties.add(Property().apply {
                        key = "ssl"
                        value = "false"
                    })
                }
                generator.apply {
                    name = "org.jooq.codegen.JavaGenerator"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "common"
                        recordVersionFields = "record_version"
                        recordTimestampFields = "record_timestamp"
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = true
                        isImmutablePojos = true
                        isFluentSetters = true
                    }
                    target.apply {
                        packageName = "com.github.alphafoxz.oneboot.common.gen.jooq"
                        directory = "$rootDir/common/src/gen"
                        isClean = true
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}