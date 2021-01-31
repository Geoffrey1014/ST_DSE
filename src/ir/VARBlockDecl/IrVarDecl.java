package ir.VARBlockDecl;

import cfg.ValueOfDiffType;
import helper.LlBuilder;
import helper.LlSymbolTable;
import ir.Ir;
import ir.IrIdent;
import ir.Literal.IrLiteral;
import ir.VarTypeEnum;
import ll.location.LlLocation;
import ll.location.LlLocationVar;
import visitor.BaseVisitor;

import java.util.ArrayList;
import java.util.Objects;

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

    private ValueOfDiffType getIrValueSimple(IrValueSimple irValue) {
        if (irValue.type == VarTypeEnum.RES_BOOL) {
            return new ValueOfDiffType((Boolean) irValue.value.getValue());
        } else if (irValue.type == VarTypeEnum.RES_INT) {
            return new ValueOfDiffType((Integer) irValue.value.getValue());
        } else if (irValue.type == VarTypeEnum.RES_REAL) {
            return new ValueOfDiffType((Float) irValue.value.getValue());
        } else if (irValue.type == VarTypeEnum.RES_STRING) {
            return new ValueOfDiffType((String) irValue.value.getValue());
        }
        return null;
    }

    private ValueOfDiffType initIrValueSimple(IrTypeSimple irType) {
        if (irType.type == VarTypeEnum.RES_BOOL) {
            return new ValueOfDiffType(false);
        } else if (irType.type == VarTypeEnum.RES_INT) {
            return new ValueOfDiffType(0);
        } else if (irType.type == VarTypeEnum.RES_REAL) {
            return new ValueOfDiffType((float) 0.0);
        } else if (irType.type == VarTypeEnum.RES_STRING) {
            return new ValueOfDiffType("");
        }
        return null;
    }

    private ArrayList<ValueOfDiffType> getIrValueArray(IrValueArray irValueArray) {
        ArrayList<ValueOfDiffType> valueOfDiffTypeArrayList = new ArrayList<>();
        if (irValueArray.type == VarTypeEnum.RES_BOOL) {
            for (IrLiteral irLiteral : irValueArray.valueList) {
                valueOfDiffTypeArrayList.add(new ValueOfDiffType((Boolean) irLiteral.getValue()));
            }
        } else if (irValueArray.type == VarTypeEnum.RES_INT) {
            for (IrLiteral irLiteral : irValueArray.valueList) {
                valueOfDiffTypeArrayList.add(new ValueOfDiffType((Integer) irLiteral.getValue()));
            }
        } else if (irValueArray.type == VarTypeEnum.RES_REAL) {
            for (IrLiteral irLiteral : irValueArray.valueList) {
                valueOfDiffTypeArrayList.add(new ValueOfDiffType((Float) irLiteral.getValue()));
            }
        } else if (irValueArray.type == VarTypeEnum.RES_STRING) {
            for (IrLiteral irLiteral : irValueArray.valueList) {
                valueOfDiffTypeArrayList.add(new ValueOfDiffType((String) irLiteral.getValue()));
            }
        }
        return valueOfDiffTypeArrayList;
    }

    private ArrayList<ValueOfDiffType> initIrValueArray(IrTypeArray irTypeArray) {
        ArrayList<ValueOfDiffType> valueOfDiffTypeArrayList = new ArrayList<>();
        if (irTypeArray.type == VarTypeEnum.RES_BOOL) {
            for (int i = 0; i < irTypeArray.size; i++) {
                valueOfDiffTypeArrayList.add(new ValueOfDiffType((Boolean) false));
            }
        } else if (irTypeArray.type == VarTypeEnum.RES_INT) {
            for (int i = 0; i < irTypeArray.size; i++) {
                valueOfDiffTypeArrayList.add(new ValueOfDiffType(0));
            }
        } else if (irTypeArray.type == VarTypeEnum.RES_REAL) {
            for (int i = 0; i < irTypeArray.size; i++) {
                valueOfDiffTypeArrayList.add(new ValueOfDiffType((float)0.0));
            }
        } else if (irTypeArray.type == VarTypeEnum.RES_STRING) {
            for (int i = 0; i < irTypeArray.size; i++) {
                valueOfDiffTypeArrayList.add(new ValueOfDiffType(""));
            }
        }
        return valueOfDiffTypeArrayList;
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
                    ValueOfDiffType v = getIrValueSimple((IrValueSimple) this.irValue);
                    assert v != null;
                    symbolTable.varInput.put(locationVar, v);

                } else { //array
                    symbolTable.varInputArray.put(locationVar,
                            getIrValueArray((IrValueArray) this.irValue));
                }
            } else { // without initialization
                if (this.irType instanceof IrTypeSimple) {
                    symbolTable.varInput.put(locationVar,
                            Objects.requireNonNull(initIrValueSimple((IrTypeSimple) this.irType)));
                } else {
                    symbolTable.varInputArray.put(locationVar, initIrValueArray((IrTypeArray) this.irType));
                }
            }

        } else { // Non_INPUT
            if (this.irValue != null) {
                if (this.irValue instanceof IrValueSimple) {
                    ValueOfDiffType v = getIrValueSimple((IrValueSimple) this.irValue);
                    assert v != null;
                    symbolTable.varNonInput.put(locationVar, v);

                } else { //array
                    symbolTable.varNonInputArray.put(locationVar,
                            getIrValueArray((IrValueArray) this.irValue));
                }
            } else {// without initialization
                if (this.irType instanceof IrTypeSimple) {
                    symbolTable.varNonInput.put(locationVar,
                            Objects.requireNonNull(initIrValueSimple((IrTypeSimple) this.irType)));
                } else {
                    symbolTable.varNonInputArray.put(locationVar,initIrValueArray((IrTypeArray)this.irType));
                }
            }

        }
        // 下面这段代码在 value是array的情况下是错误的
//        if (value != null){
//            LlLocation tempVal = this.value.generateLlIr(builder, symbolTable);
//
//            LlAssignStmtRegular regularAssignment = new LlAssignStmtRegular(new LlLocationVar(this.name.getValue()), tempVal);
//            builder.appendStatement(regularAssignment);
//
//        }
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
