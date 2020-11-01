package ir.Arg;

import ir.IrIdent;
import ir.Location.IrLocationVar;
import ir.POUDecl.IrPouDecl;
import ir.VARBlockDecl.IrVarDecl;
import visitor.BaseVisitor;

/**
 * 如果是在这里进行语意检查的话，
 *     * 3。 ArgOutputAssign  要和 Var_OUTPUT  匹配
 *      * 3.1  ArgOutputAssign.fbOutput 对应一个 IrFbStLocation。 functionBlockName 就是 IrFbStLocation.varNameFirst， IrFbStLocation 检查
 *      * 3.2  ArgOutputAssign.acceptLocation 是个 IrLocationVar 类型，IrLocationVar.irDeclObject 应该 被赋值 IrFbStLocation， IrLocationVar 检查
 *      * 3.3  acceptLocation 的类型和 IrFbStLocation 是否一致
 *      *
 * IrArgOutputAssign 中需要 携带 被 call function 的 信息
 */
public class IrArgOutputAssign extends IrArg {
    public final IrIdent fbOutput;
    public final IrLocationVar acceptLocation;  // IrLocationVar 的  irDeclObject 需要设置

    public IrPouDecl irDeclPou;
    public IrVarDecl irDeclVar; // IrVarDecl

    //TODO 这里只用 IrIdent 类型可能尤文痛

    public IrArgOutputAssign(int lineNumber, int colNumber, IrIdent fbOutput, IrLocationVar acceptLocation) {
        super(lineNumber, colNumber);
        this.fbOutput = fbOutput;
        this.acceptLocation = acceptLocation;
    }


    @Override
    public String prettyPrint(String indentSpace) {

        String prettyString = indentSpace + "|--assignStmtEquals\n";

        // pretty print the lhs
        prettyString += ("  " + indentSpace + "|--lhs\n");
        prettyString += this.acceptLocation.prettyPrint("    " + indentSpace);

        // print the rhs
        prettyString += ("  " + indentSpace + "|--rhs\n");
        prettyString += this.fbOutput.prettyPrint("    " + indentSpace);

        return prettyString;

    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.IrArgOutputAssign(this);
    }
}
