package com.tc

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by cai.tian on 2017/4/19.
 */
class DemoPlugin  implements Plugin<Project> {
    @Override
    void apply(Project project) {
       // project.extensions.create("author", Author)
        project.task('echo') << {
            println '-------Author information:'
            //println project.author.name
            //println project.author.email
        }
    }
}
