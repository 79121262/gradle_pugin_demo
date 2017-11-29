package com.tc

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.gradle.api.Action
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;
import org.gradle.api.tasks.bundling.Zip

/**
 * task 定义时println就会打印
 * task 具体调用时才会进行具体操作
 */
class MyTask extends Zip {
    MyTask() {
        description = 'im tc task'
        def ospEngineZip
        def ospEngineZipFolder
        if(!project.configurations.names.contains('ospEngine')) {
            project.configurations { ospEngine }
            project.dependencies.add('ospEngine', "com.vip.osp:osp-engine:2.5.17:zip@zip")
        }
        project.configurations.ospEngine.each {
            if(it.name.startsWith("osp-engine")) {
                ospEngineZip = it.absolutePath
                ospEngineZipFolder = it.parent
            }
        }
        project.copy {// fit to 2.4
            from project.zipTree(ospEngineZip)
            into ospEngineZipFolder
        }
        baseName = project.name
        version = project.version
        classifier = 'engine'

        /*project.configurations.runtime.each { 用这个runTime 就会变为空
            println "------"+ it.name;
        }*/
        project.configurations.runtime.allArtifacts.files.each {
            println "++"+it.name
        }
        println "--dsads----"+ospEngineZipFolder
        from "$ospEngineZipFolder/osp-engine-2.5.17-zip"
        from "$ospEngineZipFolder/osp-engine-2.5.17" // fit to 2.4
        into("thirddir", { from project.configurations.runtime })//compile 的依赖就是runtime
        into("servicesdir", {
            from project.configurations.runtime.allArtifacts.files
        })

        def http = new HTTPBuilder('http://www.baidu.com')
        http.request(Method.GET){
            req ->
                uri.path="/"
                response.success = {
                    resp, reader ->
                        assert resp.statusLine.statusCode == 200
                        println "Got response: ${resp.statusLine}"
                        println "Content-Type: ${resp.headers.'Content-Type'}"
                        //System.out << reader
                        println reader
                }
                response.'404' = {
                    println 'Not found'
                }
        }
        println '-------自定义 im tc task'  //task 定义时println就会打印
    }
}