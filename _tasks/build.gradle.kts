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
dependencies {
    jooqCodegen("org.jooq:jooq-codegen")
    jooqCodegen("org.jooq:jooq-meta")
    jooqCodegen("org.jooq:jooq-meta-extensions")
    jooqCodegen("org.postgresql:postgresql")
    jooqCodegen("org.springframework:spring-context")
    jooqCodegen(project(":_tasks:strategy"))
    implementation(project(":_tasks:strategy"))
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
        stra.name = "org.jooq.codegen.DefaultGeneratorStrategy"
//        stra.name = "com.github.alphafoxz.oneboot.gradle_tasks.jooq.OnebootJooqGeneratorStrategy"
    }
}

class Generate : Action<GenerateExtension> {
    override fun execute(gen: GenerateExtension) {
        // 强类型Record
        gen.isRecords = true
        gen.isDaos = false
//        gen.isSpringDao = true
        // 数据库序列
        gen.isSequences = true
        gen.isIndexes = true
        // 注释
        gen.isComments = true
//        gen.isCommentsOnTables = true
//        gen.isCommentsOnKeys = true
//        gen.isCommentsOnColumns = true
//        gen.isCommentsOnPackages = true
//        gen.isCommentsOnParameters = true
        // 数据库实体
        gen.isPojos = true
        gen.isImmutablePojos = true
        gen.isPojosAsJavaRecordClasses = true
        gen.visibilityModifier = VisibilityModifier.DEFAULT
        // 类型
        gen.isJavaTimeTypes = true
        // 注解
        gen.isNullableAnnotation = true
        gen.isNonnullAnnotation = true
        gen.nullableAnnotationType = "jakarta.annotation.Nullable"
        gen.nonnullAnnotationType = "jakarta.annotation.Nonnull"
        gen.isSpringAnnotations = true
        gen.generatedAnnotationType = GeneratedAnnotationType.DETECT_FROM_JDK
        // 忽略项
        gen.isGeneratedAnnotation = false
        gen.isGeneratedAnnotationDate = false
        gen.isJooqVersionReference = false
        gen.isGeneratedAnnotationJooqVersion = false
        gen.isInterfaces = false // NOTE 未来版本可能会删除对此功能的支持。
        gen.isImmutableInterfaces = false // NOTE 未来版本可能会删除对此功能的支持。
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
        db.recordTimestampFields = "_version"
        db.forcedTypes.addAll(listOf(
            ForcedType().apply {
                name = "varchar"
                includeExpression = ".*"
                includeTypes = "JSONB?"
            }
        ))
    }
}

class Target(private val moduleName: String) : Action<TargetExtension> {
    override fun execute(tar: TargetExtension) {
        tar.packageName = "com.github.alphafoxz.oneboot.$moduleName.gen.jooq"
        tar.directory = outDir(moduleName).path
        tar.isClean = true
    }
}

fun outDir(moduleName: String): File {
    return file("$rootDir/_tasks/out/$moduleName/jooq")
}

fun publishGen(moduleName: String) {
    val outDir = outDir(moduleName)
    val sourceDir = file("${outDir.path}/com/github/alphafoxz/oneboot/preset_sys/gen/jooq")
    val targetDir = file("$rootDir/preset_sys/src/main/java/com/github/alphafoxz/oneboot/${moduleName}/gen/jooq")
    if (targetDir.exists()) {
        System.err.println("jooq目录已存在 $targetDir")
        return
    }
    if (!outDir.exists()) {
        System.err.println("生成失败")
        return
    }
    targetDir.deleteOnExit()
    sourceDir.renameTo(targetDir)
    println("生成成功 $targetDir\n")
}

jooq {
    configuration {
        generator {
            generate(Generate())
            strategy(Strategy())
            jdbc(DevJdbc())
        }
    }
    executions {
        create("-dev-preset_sys") {
            description = "Generate preset_sys"
            configuration {
                generator {
                    generate(Generate())
                    database(Database("preset_sys"))
                    target(Target("preset_sys"))
                }
            }
        }
        create("-dev-app") {
            description = "Generate app"
            configuration {
                generator {
                    generate(Generate())
                    database(Database("app"))
                    target(Target("app"))
                }
            }
        }
    }
}

tasks.register<JavaExec>("生成preset_sys模块对象映射") {
    description = "GeneratePresetSys"
    group = "tools"
    dependsOn("checkNodeDeps")
    classpath = project.sourceSets["main"].runtimeClasspath
    mainClass = "com.github.alphafoxz.oneboot.gradle_tasks.GeneratePresetSys"
    args(rootDir.path)
}
tasks.register<JavaExec>("运行工具") {
    description = "ExecTools"
    group = "tools"
    dependsOn("checkNodeDeps")
    classpath = project.sourceSets["main"].runtimeClasspath
    mainClass = "com.github.alphafoxz.oneboot.gradle_tasks.ExecTools"
    args(rootDir.path)
}
tasks.register<JavaExec>("读取最新工具版本、更新env文件") {
    description = "UpdateEnvFile"
    group = "tools"
    classpath = project.sourceSets["main"].runtimeClasspath
    mainClass = "com.github.alphafoxz.oneboot.gradle_tasks.UpdateEnvFile"
    args(rootDir.path)
}

tasks.register<JavaExec>("checkNodeDeps") {
    group = "tools"
    classpath = project.sourceSets["main"].runtimeClasspath
    mainClass = "com.github.alphafoxz.oneboot.gradle_tasks.CheckTools"
}

// 生成之后发布代码
tasks.withType(CodegenTask::class.java).forEach {
    val split = it.name.split(Regex("\\-"))
    if (split.size < 3) {
        return@forEach
    }
//    val plat = split[1]
    val moduleName = split[2]
    it.doLast {
        publishGen(moduleName)
    }
}