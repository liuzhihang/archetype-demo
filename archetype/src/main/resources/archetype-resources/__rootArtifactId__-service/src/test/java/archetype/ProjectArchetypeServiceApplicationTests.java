#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.archetype;

import ${package}.${middlePackage}.entity.Demo;
import ${package}.${middlePackage}.mapper.DemoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = ProjectArchetypeServiceApplication.class)
class ProjectArchetypeServiceApplicationTests {


    @Resource
    private DemoMapper demoMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void dbTest() {

        Demo demo = demoMapper.selectById(1);

        System.out.println("demo = " + demo);

    }

}
