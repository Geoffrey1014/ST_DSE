public class MyPrint {
    Boolean control = false;
    public MyPrint(Boolean control ){
        this.control = control;
    }

    public void print(Object s){
        if(this.control){
            System.out.println(s);
        }
    }
}
