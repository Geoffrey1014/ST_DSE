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
    @Test
    public void testFindModelExample1(){
        try{
            javaExample.findModelExample1(ctxModleTrue);
        } catch (JavaExample.TestFailedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testFindModelExample2(){
        try{
            javaExample.findModelExample2(ctxModleTrue);
        } catch (JavaExample.TestFailedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testPushPopExample1(){
        try{
            javaExample.pushPopExample1(ctxModleTrue);
        } catch (JavaExample.TestFailedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIteExample(){
        javaExample.iteExample(ctxModleTrue);
    }

    @Test
    public void testEvalExample1(){
        javaExample.evalExample1(ctxModleTrue);
    }
}
