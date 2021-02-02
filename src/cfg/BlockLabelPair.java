package cfg;


public class BlockLabelPair {
    private final BasicBlock basicBlock;
    private final String label;

    public BlockLabelPair(BasicBlock basicBlock, String label) {
        this.basicBlock = basicBlock;
        this.label = label;
    }

    public BasicBlock getBasicBlock() {
        return this.basicBlock;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BlockLabelPair) {
            BlockLabelPair that = (BlockLabelPair) obj;
            if(that.label.equals("NO_DEF_1010")) return false;
            return  this.label.equals(that.label) && this.basicBlock.equals(that.basicBlock);
        }
        return false;
    }

    @Override
    public int hashCode() {

        if (this.basicBlock != null){
            return this.basicBlock.hashCode() * this.label.hashCode();
        }
        else {
            return this.label.hashCode();
        }
    }

    @Override
    public String toString() {
        return "BlockLabelPair{" +
                "basicBlock=" + basicBlock.getLabelsToStmtsMap().keySet().iterator().next() +
                ", label='" + label + '\'' +
                '}';
    }
}
