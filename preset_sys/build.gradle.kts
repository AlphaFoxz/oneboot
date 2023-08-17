import nu.studer.gradle.jooq.JooqEdition
import org.jooq.meta.jaxb.ForcedType
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
                        inputSchema = "preset_sys"
                        recordVersionFields = "record_version"
                        recordTimestampFields = "record_timestamp"
                        forcedTypes.addAll(listOf(
                                ForcedType().apply {
                                    name = "varchar"
                                    includeExpression = ".*"
                                    includeTypes = "JSONB?"
                                }
                        ))
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = true
                        isImmutablePojos = true
                        isPojosAsJavaRecordClasses = true
                        isFluentSetters = true
                        isNonnullAnnotation = true
                        nonnullAnnotationType = "org.springframework.lang.NonNull"
                        isNullableAnnotation = true
                        nullableAnnotationType = "org.springframework.lang.Nullable"
                        isValidationAnnotations = true
                    }
                    target.apply {
                        packageName = "com.github.alphafoxz.oneboot.preset_sys.gen.jooq"
                        directory = "$rootDir/preset_sys/src/gen"
                        isClean = true
                    }
                    strategy.apply {
                        name = "com.github.alphafoxz.oneboot.tasks.jooq.OnebootGeneratorStrategy"
                    }
                }
            }
        }
    }
}