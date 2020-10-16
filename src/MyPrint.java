public class MyPrint {
    /**需求，初始化 MyPrint 的时候设置 level
     * 调用 MyPrint 对象 的 print 功能时， 根据初始化设置的 level，
     * control = 0 表示全都打印，数字越大表示打印的要求越严格，打印的东西越少
     * */
    public SubMyPrint levelZero = new SubMyPrint(0);
    public SubMyPrint LevelOne = new SubMyPrint(1);
    public SubMyPrint levelTwo = new SubMyPrint(2);
    Integer control = 0;

    public MyPrint(Integer control ){
        this.control = control;
    }

//    public void print(Integer level,Object s){
//        if (level >= this.control){
//            System.out.println();
//        }
//    }

    class SubMyPrint{
        private Integer level;
        SubMyPrint(Integer l){
            this.level = l;
        }
        public void print(Object s){
            if ( this.level <= control){
                System.out.println(s);
            }
        }

    }

}
