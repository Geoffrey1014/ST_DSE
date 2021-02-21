package z3;

import com.microsoft.z3.Context;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class JavaExampleTest {
    JavaExample javaExample;
    Context ctxModleTrue;
    @Before
    public void setUp(){
        javaExample = new JavaExample();
        HashMap<String, String> cfg = new HashMap<String, String>();
        cfg.put("model", "true");
        ctxModleTrue = new Context(cfg);
    }

    @Test
    public void testOptimizeExample(){
        javaExample.optimizeExample(ctxModleTrue);
    }
    @Test
    public void testBasicTests() {
        try{
            javaExample.basicTests(ctxModleTrue);
        }
        catch (JavaExample.TestFailedException ex)
        {
            System.out.println("TEST CASE FAILED: " + ex.getMessage());
            System.out.println("Stack trace: ");
            ex.printStackTrace(System.out);
        }
    }
    @Test
    public void testLogicExample(){
        try{
            javaExample.logicExample(ctxModleTrue);
        } catch (JavaExample.TestFailedException e) {
            e.printStackTrace();
        }

    }
}
