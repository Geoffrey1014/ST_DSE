package tools;

public class MyPrint {
    /**需求，初始化 MyPrint 的时候设置 level
     * 调用 MyPrint 对象 的 print 功能时， 根据初始化设置的 level，
     * control = 0 表示全都打印，数字越大表示打印的要求越严格，打印的东西越少
     * */
    public static SubMyPrint levelZero;
    public static SubMyPrint LevelOne ;
    public static SubMyPrint levelTwo;
    Integer control = 0 ;

    public MyPrint(Integer control ){

        this.control = control;
        levelZero = new SubMyPrint(0, control);
        LevelOne = new SubMyPrint(1, control);
        levelTwo = new SubMyPrint(2, control);

    }


}
