package com.tc

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.JavaExec

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient
import groovyx.net.http.HttpResponseDecorator
import org.gradle.api.tasks.bundling.Zip

/**
 * Created by cai.tian on 2017/4/19.
 */
class DemoPlugin  implements Plugin<Project> {

    private static final String PROJECT_VERIFY_TASK = "hello1"
    @Override
    void apply(Project project) {

        project.plugins.apply(JavaPlugin.class)

       //自定义task要显示调用才可以执行  相当在apple方法加入语句
        project.task('echo') << {
            println '-------Author cai.tian '
            //println project.author.name
            //println project.author.email
        }
        //自定义task要显示调用才可以执行
        def gatherInfoTask = project.getTasks().create(PROJECT_VERIFY_TASK).doLast { gatherInfoFunc(project) }
        //在名称为jar的Task 执行之前执行gatherInfoTask（依赖）
        project.tasks.jar.dependsOn(gatherInfoTask)
        project.tasks.build.dependsOn(gatherInfoTask)
  /*      if (isWebappProject(project.name)) {
            project.tasks.war.dependsOn(gatherInfoTask)
        }*/
        // 为project加入属性
        project.extensions.create("runEntity", RunEntity)
        println project.runEntity.timeFormat

        project.task("mytaskOk", group: "mygroup", type: MyTask)

        prepareRunOspTask(project)


        println '-------自定义插件执行完毕'
    }

    protected void gatherInfoFunc(Project project) {
        println PROJECT_VERIFY_TASK+'-------doLast'
        println project.configurations.runtime.allArtifacts.files
    }

    /**
     * 执行main 函数
     * @param project
     */
    private void prepareRunOspTask(Project project) {
        project.task("RUN_COPY", group: "RUN_GROUP", type: JavaExec).doFirst {

        }
    }
}
