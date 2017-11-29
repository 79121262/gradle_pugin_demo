import com.tc.DemoPlugin
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.antlr.internal.antlr2.GenerationPlan
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

/**
 * Created by cai.tian on 2017/11/10.
 */
class test {
    def generation
    def project

    @Test
    public void setUp() {

        DemoPlugin d=new DemoPlugin();

        project = ProjectBuilder.builder().withName('DemoPlugin').build()  //获取project

        project.plugins.apply('java') //添加依赖插件java


        d.apply(project);
        //project.app

    }
}
