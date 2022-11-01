package cool.structures;

public class VariableSymbol {
    protected String name;
    protected Type type;

    public VariableSymbol(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public VariableSymbol(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
