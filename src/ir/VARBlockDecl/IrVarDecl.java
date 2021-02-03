package ir.VARBlockDecl;

import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.Ir;
import ir.IrIdent;
import ir.Literal.IrLiteral;
import ir.VarTypeEnum;
import ll.assignStmt.LlAssignStmtRegular;
import ll.literal.*;
import ll.location.LlLocation;
import ll.location.LlLocationVar;
import visitor.BaseVisitor;

import java.util.ArrayList;

public class IrVarDecl extends Ir {
    public final IrType irType;
    public final IrValue irValue;
    // irType and irValue both include the info of whether it is an array
    public IrIdent name;
    public VarAccessTypeEnum accessType;

    public IrVarDecl(int lineNumber, int colNumber, IrIdent name, IrType irType, IrValue irValue) {
        super(lineNumber, colNumber);
        this.name = name;
        this.irType = irType;
        this.irValue = irValue;
    }


    @Override
    public String prettyPrint(String indentSpace) {
        String prettyString = indentSpace + "|--varDecl\n";
        prettyString += ("  " + indentSpace + "|--name: " + this.getName() + "\n");
        prettyString += this.getIrType().prettyPrint("    " + indentSpace);
        if (irValue != null) {
            prettyString += this.getIrValueSimple().prettyPrint(indentSpace);
        }
        return prettyString;
    }

    @Override
    public void accept(BaseVisitor<Void> visitor) {
        visitor.visitIrVarDecl(this);
    }

    private LlLiteral getIrValueSimple(IrValueSimple irValue) {
        if (irValue.type == VarTypeEnum.RES_BOOL) {
            return new LlLiteralBool((Boolean) irValue.value.getValue());
        } else if (irValue.type == VarTypeEnum.RES_INT) {
            return new LlLiteralInt((Integer) irValue.value.getValue());
        } else if (irValue.type == VarTypeEnum.RES_REAL) {
            return new LlLiteralReal((Float) irValue.value.getValue());
        } else if (irValue.type == VarTypeEnum.RES_STRING) {
            return new LlLiteralString((String) irValue.value.getValue());
        }
        return null;
    }

    private LlLiteral initIrValueSimple(IrTypeSimple irType) {
        if (irType.type == VarTypeEnum.RES_BOOL) {
            return new LlLiteralBool(false);
        } else if (irType.type == VarTypeEnum.RES_INT) {
            return new LlLiteralInt(0);
        } else if (irType.type == VarTypeEnum.RES_REAL) {
            return new LlLiteralReal((float) 0.0);
        } else if (irType.type == VarTypeEnum.RES_STRING) {
            return new LlLiteralString("");
        }
        return null;
    }

    private ArrayList<LlLiteral> getIrValueArray(IrValueArray irValueArray) {
        ArrayList<LlLiteral> llLiteralArrayList = new ArrayList<>();
        if (irValueArray.type == VarTypeEnum.RES_BOOL) {
            for (IrLiteral irLiteral : irValueArray.valueList) {
                llLiteralArrayList.add(new LlLiteralBool((Boolean) irLiteral.getValue()));
            }
        } else if (irValueArray.type == VarTypeEnum.RES_INT) {
            for (IrLiteral irLiteral : irValueArray.valueList) {
                llLiteralArrayList.add(new LlLiteralInt((Integer) irLiteral.getValue()));
            }
        } else if (irValueArray.type == VarTypeEnum.RES_REAL) {
            for (IrLiteral irLiteral : irValueArray.valueList) {
                llLiteralArrayList.add(new LlLiteralReal((Float) irLiteral.getValue()));
            }
        } else if (irValueArray.type == VarTypeEnum.RES_STRING) {
            for (IrLiteral irLiteral : irValueArray.valueList) {
                llLiteralArrayList.add(new LlLiteralString((String) irLiteral.getValue()));
            }
        }
        return llLiteralArrayList;
    }

    private ArrayList<LlLiteral> initIrValueArray(IrTypeArray irTypeArray) {
        ArrayList<LlLiteral> llLiteralArrayList = new ArrayList<>();
        if (irTypeArray.type == VarTypeEnum.RES_BOOL) {
            for (int i = 0; i < irTypeArray.size; i++) {
                llLiteralArrayList.add(new LlLiteralBool((Boolean) false));
            }
        } else if (irTypeArray.type == VarTypeEnum.RES_INT) {
            for (int i = 0; i < irTypeArray.size; i++) {
                llLiteralArrayList.add(new LlLiteralInt(0));
            }
        } else if (irTypeArray.type == VarTypeEnum.RES_REAL) {
            for (int i = 0; i < irTypeArray.size; i++) {
                llLiteralArrayList.add(new LlLiteralReal((float)0.0));
            }
        } else if (irTypeArray.type == VarTypeEnum.RES_STRING) {
            for (int i = 0; i < irTypeArray.size; i++) {
                llLiteralArrayList.add(new LlLiteralString(""));
            }
        }
        return llLiteralArrayList;
    }

    @Override
    public LlLocation generateLlIr(LlBuilder builder, LlSymbolTable symbolTable) {
        // 这里没有把值也存储进去，因为C语言是声明，是没有赋值的
        // 我们是需要赋值的，没有赋值的要给予初始值
        // 可以分成 INPUT Non_INPUT, ARRAY, NON_ARRAY 四类
        LlLocationVar locationVar = new LlLocationVar(this.name.getValue());
        if (this.accessType == VarAccessTypeEnum.VAR_INPUT) {
            if (this.irValue != null) {
                if (this.irValue instanceof IrValueSimple) {
                    LlLiteral v = getIrValueSimple((IrValueSimple) this.irValue);
                    assert v != null;
                    symbolTable.varInput.put(locationVar, v);
                    builder.appendStatement(new LlAssignStmtRegular(locationVar, v));

                } else { //array TODO need to be handled
                    symbolTable.varInputArray.put(locationVar,
                            getIrValueArray((IrValueArray) this.irValue));
                }
            } else { // without initialization
                if (this.irType instanceof IrTypeSimple) {
                    LlLiteral v = initIrValueSimple((IrTypeSimple) this.irType);
                    assert v != null;
                    symbolTable.varInput.put(locationVar, v);
                    builder.appendStatement(new LlAssignStmtRegular(locationVar, v));
                } else {
                    symbolTable.varInputArray.put(locationVar, initIrValueArray((IrTypeArray) this.irType));
                }
            }

        } else { // Non_INPUT
            if (this.irValue != null) {
                if (this.irValue instanceof IrValueSimple) {
                    LlLiteral v = getIrValueSimple((IrValueSimple) this.irValue);
                    assert v != null;
                    symbolTable.varNonInput.put(locationVar, v);
                    builder.appendStatement(new LlAssignStmtRegular(locationVar, v));
                } else { //array
                    symbolTable.varNonInputArray.put(locationVar,
                            getIrValueArray((IrValueArray) this.irValue));
                }
            } else {// without initialization
                if (this.irType instanceof IrTypeSimple) {
                    LlLiteral v = initIrValueSimple((IrTypeSimple) this.irType);
                    assert v != null;
                    symbolTable.varNonInput.put(locationVar, v);
                    builder.appendStatement(new LlAssignStmtRegular(locationVar, v));
                } else {
                    symbolTable.varNonInputArray.put(locationVar,initIrValueArray((IrTypeArray)this.irType));
                }
            }
        }

        return null;
    }

    public String getName() {
        return name.getValue();
    }

    public IrType getIrType() {
        return irType;
    }

    public IrValue getIrValueSimple() {
        return irValue;
    }
}
