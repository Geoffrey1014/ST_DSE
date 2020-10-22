package tools;

public  class SubMyPrint{
    private Integer level;
    private Integer control;
    SubMyPrint(Integer l, Integer control){
        this.level = l;
        this.control = control;
    }
    public void print(Object s){
        if ( this.level >= this.control){
            System.out.println(s);
        }
    }

}