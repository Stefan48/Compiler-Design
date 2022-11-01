package cool.structures;

public class Type extends Scope {
    public static final Type OBJECT = new Type("Object", "");
    public static final Type BOOL = new Type("Bool", Type.OBJECT);
    public static final Type INT = new Type("Int", Type.OBJECT);
    public static final Type STRING  = new Type("String", Type.OBJECT);
    public static final Type IO = new Type("IO", Type.OBJECT);
    public static final Type SELF_TYPE = new Type("SELF_TYPE", Type.OBJECT);

    static {
        // Object class's functions
        Type.OBJECT.addFunction(new FunctionSymbol("abort", Type.OBJECT, Type.OBJECT));
        Type.OBJECT.addFunction(new FunctionSymbol("type_name", Type.STRING, Type.OBJECT));
        Type.OBJECT.addFunction(new FunctionSymbol("copy", Type.SELF_TYPE, Type.OBJECT));


        // String class's functions
        Type.STRING.addFunction(new FunctionSymbol("length", Type.INT, Type.STRING));

        FunctionSymbol concatFunction = new FunctionSymbol("concat", Type.STRING, Type.STRING);
        concatFunction.addVariable(new VariableSymbol("s", Type.STRING));
        Type.STRING.addFunction(concatFunction);

        FunctionSymbol substrFunction = new FunctionSymbol("substr", Type.STRING, Type.STRING);
        substrFunction.addVariable(new VariableSymbol("i", Type.INT));
        substrFunction.addVariable(new VariableSymbol("l", Type.INT));
        Type.STRING.addFunction(substrFunction);


        // IO class's functions
        FunctionSymbol outStringFunction = new FunctionSymbol("out_string", Type.SELF_TYPE, Type.IO);
        outStringFunction.addVariable(new VariableSymbol("x", Type.STRING));
        Type.IO.addFunction(outStringFunction);

        FunctionSymbol outIntFunction = new FunctionSymbol("out_int", Type.SELF_TYPE, Type.IO);
        outIntFunction.addVariable(new VariableSymbol("x", Type.INT));
        Type.IO.addFunction(outIntFunction);

        Type.IO.addFunction(new FunctionSymbol("in_string", Type.STRING, Type.IO));
        Type.IO.addFunction(new FunctionSymbol("in_int", Type.INT, Type.IO));
    }

    public static Type lowestCommonAncestor(Type type1, Type type2) {
        Type type = type1;
        while (type != Type.OBJECT) {
            if (type2.isDescendantOf(type)) {
                break;
            }
            type = (Type)type.parent;
        }
        return type;
    }

    protected String name;
    protected String parentName;

    public Type(String name, Type parent) {
        super(parent);
        this.name = name;
        this.parentName = parent.name;
        this.addVariable(new VariableSymbol("self", Type.SELF_TYPE));
    }

    public Type(String name, String parentName) {
        super(null);
        this.name = name;
        this.parentName = parentName;
        this.addVariable(new VariableSymbol("self", Type.SELF_TYPE));
    }

    public String getName() {
        return name;
    }

    public String getParentName() {
        return parentName;
    }

    public boolean isDescendantOf(Type type) {
        if (type == Type.OBJECT) {
            return true;
        }
        Type t = this;
        while (t != Type.OBJECT) {
            if (t == type) {
                return true;
            }
            t = (Type)t.parent;
        }
        return false;
    }

    public Type dynamicType(Scope scope) {
        if (this == Type.SELF_TYPE) {
            Scope s = scope;
            while (!(s instanceof Type)) {
                s = s.parent;
            }
            return (Type)s;
        }
        return this;
    }
}
