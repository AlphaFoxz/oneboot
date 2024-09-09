import org.jooq.codegen.gradle.CodegenTask
import org.jooq.codegen.gradle.MetaExtensions.*
import org.jooq.meta.jaxb.ForcedType
import org.jooq.meta.jaxb.GeneratedAnnotationType
import org.jooq.meta.jaxb.Property
import org.jooq.meta.jaxb.VisibilityModifier
import java.io.FileInputStream
import java.util.*

plugins {
    id("org.jooq.jooq-codegen-gradle")
}
dependencyManagement {
    dependencies {
        dependency("org.postgresql:postgresql:42.6.0")
        dependency("org.springframework:spring-context:6.1.1")
    }
}
dependencies {
    implementation(project(":core"))
    compileOnly(project(":_tasks:strategy"))
    jooqCodegen("org.jooq:jooq-codegen")
    jooqCodegen("org.jooq:jooq-meta")
    jooqCodegen("org.jooq:jooq-meta-extensions")
    jooqCodegen("org.postgresql:postgresql")
    jooqCodegen("org.springframework:spring-context")
    jooqCodegen(project(":_tasks:strategy"))
    jooqCodegen(project(":core"))
}

fun getPropertyValue(key: String): String {
    val configFilePath = "$rootDir/gradle.properties"
    val properties = Properties()
    FileInputStream(configFilePath).use { inputStream ->
        properties.load(inputStream)
    }
    return properties.getProperty(key)
}

class Strategy : Action<StrategyExtension> {
    override fun execute(stra: StrategyExtension) {
        stra.name = "com.github.alphafoxz.oneboot.gradle_tasks.jooq.OnebootJooqGeneratorStrategy"
    }
}

class Generate : Action<GenerateExtension> {
    override fun execute(gen: GenerateExtension) {
        gen.isGeneratedAnnotation = false
        gen.isGeneratedAnnotationDate = false
        gen.isGeneratedAnnotationJooqVersion = false
        gen.visibilityModifier = VisibilityModifier.NONE
        gen.isRecords = true
        gen.generatedAnnotationType = GeneratedAnnotationType.JAVAX_ANNOTATION_GENERATED
    }
}

class DevJdbc : Action<JdbcExtension> {
    override fun execute(jdbc: JdbcExtension) {
        jdbc.driver = getPropertyValue("dev.driver")
        jdbc.url = getPropertyValue("dev.url")
        jdbc.user = getPropertyValue("dev.user")
        jdbc.password = getPropertyValue("dev.password")
        jdbc.properties.add(Property().apply {
            key = "ssl"
            value = "false"
        })
    }
}

class Database(private val schemaName: String) : Action<DatabaseExtension> {
    override fun execute(db: DatabaseExtension) {
        db.name = "org.jooq.meta.postgres.PostgresDatabase"
        db.inputSchema = schemaName
//        db.recordTimestampFields = "_version"
        db.forcedTypes.addAll(listOf(
            ForcedType().apply {
                name = "varchar"
                includeExpression = ".*"
                includeTypes = "JSONB?"
            }
        ))
    }
}

fun outDir(moduleName: String): File {
    return file("$rootDir/_tasks/out/$moduleName/jooq")
}

fun publishGen(moduleName: String) {
    val outDir = outDir(moduleName)
    val sourceDir = file("${outDir.path}/com/github/alphafoxz/oneboot/preset_sys/gen/jooq")
    val targetDir = file("$rootDir/preset_sys/src/main/java/com/github/alphafoxz/oneboot/${moduleName}/gen/jooq")
    if (!outDir.exists()) {
        System.err.println("生成失败")
        return
    }
    targetDir.deleteOnExit()
    sourceDir.renameTo(targetDir)
}

jooq {
    configuration {
        generator {
            strategy(Strategy())
            jdbc(DevJdbc())
            generate(Generate())
        }
    }
    executions {
        create("-dev-preset_sys") {
            description = "Generate preset_sys"
            configuration {
                generator {
                    database(Database("preset_sys"))
                    target {
                        packageName = "com.github.alphafoxz.oneboot.preset_sys.gen.jooq"
                        directory = outDir("preset_sys").path
                        isClean = true
                    }
                }
            }
        }
        create("-dev-app") {
            description = "Generate app"
            configuration {
                generator {
                    database(Database("preset_sys"))
                    target {
                        packageName = "com.github.alphafoxz.oneboot.preset_sys.gen.jooq"
                        directory = outDir("preset_sys").path
                        isClean = true
                    }
                }
            }
        }
    }
}

// 生成之后发布代码
tasks.withType(CodegenTask::class.java).forEach {
    val split = it.name.split(Regex("\\-"))
    print(split)
    if (split.size < 3) {
        return@forEach
    }
//    val plat = split[1]
    val moduleName = split[2]
    it.doLast {
        publishGen(moduleName)
    }
}