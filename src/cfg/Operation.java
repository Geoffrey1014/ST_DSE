package cfg;

public class Operation {
    public Operation(){

    }

    //TODO: i have not finish up the complete version
    // it seems not necessary to judge consistancy of types
    public ValueOfDiffType addOper(ValueOfDiffType left, ValueOfDiffType right){

        if (left.getType() == 1){
            Integer v = left.getvInteger() + right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == 2){
            Float v =left.getvFloat() + right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType sub(ValueOfDiffType left, ValueOfDiffType right){

        if (left.getType() == 1){
            Integer v = left.getvInteger() - right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == 2){
            Float v =left.getvFloat() - right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType mul(ValueOfDiffType left, ValueOfDiffType right){

        if (left.getType() == 1){
            Integer v = left.getvInteger() * right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == 2){
            Float v =left.getvFloat() * right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType devide(ValueOfDiffType left, ValueOfDiffType right){

        if (left.getType() == 1){
            Integer v = left.getvInteger() / right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == 2){
            Float v =left.getvFloat() / right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType mod(ValueOfDiffType left, ValueOfDiffType right){

        if (left.getType() == 1){
            Integer v = left.getvInteger() % right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == 2){
            Float v =left.getvFloat() % right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType GT(ValueOfDiffType left, ValueOfDiffType right){
        if (left.getType() == 1){
            Boolean v = left.getvInteger() > right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == 2){
            Boolean v =left.getvFloat() > right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }
    public ValueOfDiffType EGT(ValueOfDiffType left, ValueOfDiffType right){
        if (left.getType() == 1){
            Boolean v = left.getvInteger() >= right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == 2){
            Boolean v =left.getvFloat() >= right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType LT(ValueOfDiffType left, ValueOfDiffType right){
        if (left.getType() == 1){
            Boolean v = left.getvInteger() < right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == 2){
            Boolean v =left.getvFloat() < right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType ELT(ValueOfDiffType left, ValueOfDiffType right){
        if (left.getType() == 1){
            Boolean v = left.getvInteger() <= right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == 2){
            Boolean v =left.getvFloat() <= right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType equal(ValueOfDiffType left, ValueOfDiffType right){
        int type = left.getType();
        switch (type){
            case 0:
                return new ValueOfDiffType(left.getvBoolean() == right.getvBoolean());
            case 1:
                return new ValueOfDiffType(left.getvInteger().equals(right.getvInteger()));
            case 2:
                return new ValueOfDiffType(left.getvFloat().equals(right.getvFloat()));
            case 3:
                return new ValueOfDiffType(left.getvString().equals(right.getvString()));
            default:
                return null;
        }
    }

    public ValueOfDiffType notEqual(ValueOfDiffType left, ValueOfDiffType right){
        int type = left.getType();
        switch (type){
            case 0:
                return new ValueOfDiffType(left.getvBoolean() != right.getvBoolean());
            case 1:
                return new ValueOfDiffType(!left.getvInteger().equals(right.getvInteger()));
            case 2:
                return new ValueOfDiffType(!left.getvFloat().equals(right.getvFloat()));
            case 3:
                return new ValueOfDiffType(!left.getvString().equals(right.getvString()));
            default:
                return null;
        }
    }
    public ValueOfDiffType and(ValueOfDiffType left, ValueOfDiffType right){
        return new ValueOfDiffType(left.getvBoolean() && right.getvBoolean());
    }
    public ValueOfDiffType or(ValueOfDiffType left, ValueOfDiffType right){

        return new ValueOfDiffType(left.getvBoolean() || right.getvBoolean());
    }
}
