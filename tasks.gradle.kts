gradle.rootProject {
    tasks.getByName("generateJooq") {
        onlyIf {
            //只有单独调用生成代码任务的时候,才执行
            gradle.taskGraph.allTasks.size == 1
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        onlyIf {
            //在执行build任务时跳过test
            !gradle.taskGraph.hasTask(":build")
        }
    }
}