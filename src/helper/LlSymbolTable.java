package helper;

import ll.LlComponent;

import java.util.Hashtable;

public class LlSymbolTable {
    //TODO 注意，这个肯定很重要，我不是要生成汇编代码的，是要模拟执行的，那么LLIR的符号表应该是很重要的。对我来说，是要在LLIR模拟执行，所以LLIR应该包括更多信息
    //其实是个memory
    public Hashtable<LlComponent, String> llTable;

    public LlSymbolTable(){
        llTable = new Hashtable<>();
    }

    public void put(LlComponent key, String value){
        this.llTable.put(key, value);
    }

    public String get(LlComponent key){
        return this.llTable.get(key);
    }
}