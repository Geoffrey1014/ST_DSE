package simulation;

public class Operation {
    public Operation(){

    }

    //TODO: i have not finish up the complete version
    // it seems not necessary to judge consistency of types

    public ValueOfDiffType add(ValueOfDiffType left, ValueOfDiffType right){

        if (left.getType() == BasicTypeEnum.INTEGER){
            Long v = left.getvLong() + right.getvLong();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Double v =left.getvDouble() + right.getvDouble();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType sub(ValueOfDiffType left, ValueOfDiffType right){

        if (left.getType() == BasicTypeEnum.INTEGER){
            Long v = left.getvLong() - right.getvLong();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Double v =left.getvDouble() - right.getvDouble();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    //TODO: 其他的Double和Long之间的转换待做， 因为有可能会导致错误查不出
    public ValueOfDiffType mul(ValueOfDiffType left, ValueOfDiffType right){

        if (left.getType() == BasicTypeEnum.INTEGER){
            Long v = left.getvLong() * getRightOperandLong(right);
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Double v =left.getvDouble() * getRightOperandDouble(right);
            return new ValueOfDiffType(v);
        }
        return null;
    }

    private Double getRightOperandDouble(ValueOfDiffType right){
        Double vRight = right.getvDouble();
        if(vRight == null){
            vRight = right.getvLong().doubleValue();
        }
        return vRight;
    }
    private Long getRightOperandLong(ValueOfDiffType right){
        Long vRight = right.getvLong();
        if(vRight == null){
            vRight = right.getvDouble().longValue();
        }
        return vRight;
    }

    public ValueOfDiffType devide(ValueOfDiffType left, ValueOfDiffType right){

        if (left.getType() == BasicTypeEnum.INTEGER){
            Long v = left.getvLong() / right.getvLong();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Double v =left.getvDouble() / right.getvDouble();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType mod(ValueOfDiffType left, ValueOfDiffType right){

        if (left.getType() == BasicTypeEnum.INTEGER){
            Long v = left.getvLong() % right.getvLong();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Double v =left.getvDouble() % right.getvDouble();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType GT(ValueOfDiffType left, ValueOfDiffType right){
        if (left.getType() == BasicTypeEnum.INTEGER){
            Boolean v = left.getvLong() > right.getvLong();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Boolean v =left.getvDouble() > right.getvDouble();
            return new ValueOfDiffType(v);
        }
        return null;
    }
    public ValueOfDiffType EGT(ValueOfDiffType left, ValueOfDiffType right){
        if (left.getType() == BasicTypeEnum.INTEGER){
            Boolean v = left.getvLong() >= right.getvLong();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Boolean v =left.getvDouble() >= right.getvDouble();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType LT(ValueOfDiffType left, ValueOfDiffType right){
        if (left.getType() == BasicTypeEnum.INTEGER){
            Boolean v = left.getvLong() < right.getvLong();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Boolean v =left.getvDouble() < right.getvDouble();
            return new ValueOfDiffType(v);
        }
        return null;
    }

    public ValueOfDiffType ELT(ValueOfDiffType left, ValueOfDiffType right){
        if (left.getType() == BasicTypeEnum.INTEGER){
            Boolean v = left.getvLong() <= right.getvLong();
            return new ValueOfDiffType(v);
        }
        else if(left.getType() == BasicTypeEnum.FLOAT){
            Boolean v =left.getvDouble() <= right.getvDouble();
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
                return new ValueOfDiffType(left.getvLong().equals(right.getvLong()));
            case FLOAT:
                return new ValueOfDiffType(left.getvDouble().equals(right.getvDouble()));
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
                return new ValueOfDiffType(!left.getvLong().equals(right.getvLong()));
            case FLOAT:
                return new ValueOfDiffType(!left.getvDouble().equals(right.getvDouble()));
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

    public ValueOfDiffType not(ValueOfDiffType operand){
        return new ValueOfDiffType(!operand.getvBoolean());
    }

    public ValueOfDiffType neg(ValueOfDiffType right) {
        BasicTypeEnum type = right.getType();
        switch (type){
            case BOOLEAN:
                return new ValueOfDiffType(!right.getvBoolean());
            case INTEGER:
                return new ValueOfDiffType(-right.getvLong());
            case FLOAT:
                return new ValueOfDiffType(-right.getvDouble());
            case STRING:
                System.out.println("can not negate a string");
            default:
                return null;
        }
    }
}
