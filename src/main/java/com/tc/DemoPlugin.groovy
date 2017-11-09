package com.tc

import org.gradle.api.Plugin
import org.gradle.api.Project


import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient
import groovyx.net.http.HttpResponseDecorator

/**
 * Created by cai.tian on 2017/4/19.
 */
class DemoPlugin  implements Plugin<Project> {

    private static final String PROJECT_VERIFY_TASK = "verifyProject"
    @Override
    void apply(Project project) {
       // project.extensions.create("author", Author)
        project.task('echo') << {
            println '-------Author cai.tian '
            //println project.author.name
            //println project.author.email
        }
        def gatherInfoTask = project.getTasks().create(PROJECT_VERIFY_TASK).doLast { gatherInfoFunc(project) }
        project.tasks.jar.dependsOn(gatherInfoTask) //在名称为jar的Task 执行之前执行gatherInfoTask（依赖）
        project.tasks.build.dependsOn(gatherInfoTask)
  /*      if (isWebappProject(project.name)) {
            project.tasks.war.dependsOn(gatherInfoTask)
        }*/
        println '-------自定义插件执行完毕';
    }

    protected void gatherInfoFunc(Project project) {
        println '-------jar inport';
    }
}
