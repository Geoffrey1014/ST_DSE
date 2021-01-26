package cfg;

public class Operation {
    public Operation(){

    }

    //TODO: i have not finish up the complete version
    // it seems not necessary to judge consistency of types
    public ValueOfDiffType add(ValueOfDiffType left, ValueOfDiffType right){

        if (left.getType() == BasicTypeEnum.INTEGER){
            Integer v = left.getvInteger() + right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Float v =left.getvFloat() + right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType sub(ValueOfDiffType left, ValueOfDiffType right){

        if (left.getType() == BasicTypeEnum.INTEGER){
            Integer v = left.getvInteger() - right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Float v =left.getvFloat() - right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType mul(ValueOfDiffType left, ValueOfDiffType right){

        if (left.getType() == BasicTypeEnum.INTEGER){
            Integer v = left.getvInteger() * right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Float v =left.getvFloat() * right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType devide(ValueOfDiffType left, ValueOfDiffType right){

        if (left.getType() == BasicTypeEnum.INTEGER){
            Integer v = left.getvInteger() / right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Float v =left.getvFloat() / right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType mod(ValueOfDiffType left, ValueOfDiffType right){

        if (left.getType() == BasicTypeEnum.INTEGER){
            Integer v = left.getvInteger() % right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Float v =left.getvFloat() % right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType GT(ValueOfDiffType left, ValueOfDiffType right){
        if (left.getType() == BasicTypeEnum.INTEGER){
            Boolean v = left.getvInteger() > right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Boolean v =left.getvFloat() > right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }
    public ValueOfDiffType EGT(ValueOfDiffType left, ValueOfDiffType right){
        if (left.getType() == BasicTypeEnum.INTEGER){
            Boolean v = left.getvInteger() >= right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Boolean v =left.getvFloat() >= right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType LT(ValueOfDiffType left, ValueOfDiffType right){
        if (left.getType() == BasicTypeEnum.INTEGER){
            Boolean v = left.getvInteger() < right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Boolean v =left.getvFloat() < right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType ELT(ValueOfDiffType left, ValueOfDiffType right){
        if (left.getType() == BasicTypeEnum.INTEGER){
            Boolean v = left.getvInteger() <= right.getvInteger();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Boolean v =left.getvFloat() <= right.getvFloat();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType equal(ValueOfDiffType left, ValueOfDiffType right){
        BasicTypeEnum type = left.getType();
        switch (type){
            case BOOLEAN:
                return new ValueOfDiffType(left.getvBoolean() == right.getvBoolean());
            case INTEGER:
                return new ValueOfDiffType(left.getvInteger().equals(right.getvInteger()));
            case FLOAT:
                return new ValueOfDiffType(left.getvFloat().equals(right.getvFloat()));
            case STRING:
                return new ValueOfDiffType(left.getvString().equals(right.getvString()));
            default:
                return null;
        }
    }

    public ValueOfDiffType notEqual(ValueOfDiffType left, ValueOfDiffType right){
        BasicTypeEnum type = left.getType();
        switch (type){
            case BOOLEAN:
                return new ValueOfDiffType(left.getvBoolean() != right.getvBoolean());
            case INTEGER:
                return new ValueOfDiffType(!left.getvInteger().equals(right.getvInteger()));
            case FLOAT:
                return new ValueOfDiffType(!left.getvFloat().equals(right.getvFloat()));
            case STRING:
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
