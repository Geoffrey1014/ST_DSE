package simulation;

import ll.location.LlLocationVar;
import org.junit.Assert;
import org.junit.Test;

public class ConmemoryTest {
    @Test
    public void testEQ(){
        ConMemory conMemory1 = new ConMemory();
        ConMemory conMemory2 = new ConMemory();
        conMemory1.put(new LlLocationVar("a"), new ValueOfDiffType(false));
        conMemory2.put(new LlLocationVar("a"), new ValueOfDiffType(false));
        boolean result = conMemory1.equals(conMemory2);
        Assert.assertTrue(result);
    }
}
