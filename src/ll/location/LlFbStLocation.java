package ll.location;

/**
 * 这个有部分面向对象特征啊，怎么搞？
 * 总之没有指针，那么就不能生成FB的不同对象。
 * LlFbStLocation 应该保存那些信息？ IrFbStLocation的信息应该都要有的，或者被拆分
 * 运行时，应该做什么动作？内存状态怎么变化？
 */
public class LlFbStLocation extends LlLocation {

    private final String pouName;
    public LlFbStLocation(String varName, String pouName) {
        super(varName);
        this.pouName = pouName;
    }

    public String getPouName() {
        return pouName;
    }

    @Override
    public String toString() {
        String s = "";
        s += this.pouName + ".";
        s += this.getVarName();

        return s;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LlFbStLocation)) {
            return false;
        }
        return ((LlFbStLocation) obj).getVarName().equals(this.getVarName())
                && ((LlFbStLocation) obj).getPouName().equals(this.pouName);
    }
}
