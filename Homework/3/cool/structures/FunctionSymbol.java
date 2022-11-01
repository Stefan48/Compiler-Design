package cool.structures;

public class FunctionSymbol extends Scope {
    protected String name;
    protected Type returnType;

    public FunctionSymbol(String name, Type returnType, Scope parent) {
        super(parent);
        this.name = name;
        this.returnType = returnType;
    }

    public FunctionSymbol(String name, Scope parent) {
        super(parent);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }
}
