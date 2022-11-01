package cool.structures;

import java.util.LinkedHashMap;

public class Scope {
    protected Scope parent;
    protected LinkedHashMap<String, VariableSymbol> variableSymbols = new LinkedHashMap<>();
    protected LinkedHashMap<String, FunctionSymbol> functionSymbols = new LinkedHashMap<>();

    public Scope(Scope parent) {
        this.parent = parent;
    }

    public Scope getParent() {
        return parent;
    }

    public void setParent(Scope parent) {
        this.parent = parent;
    }

    public LinkedHashMap<String, VariableSymbol> getVariableSymbols() {
        return variableSymbols;
    }

    public boolean addVariable(VariableSymbol variable) {
        if (variableSymbols.containsKey(variable.getName())) {
            return false;
        }
        variableSymbols.put(variable.getName(), variable);
        return true;
    }

    public VariableSymbol lookupVariable(String variableName) {
        VariableSymbol variableSymbol = variableSymbols.get(variableName);
        if (variableSymbol != null) {
            return variableSymbol;
        }
        if (parent != null) {
            return parent.lookupVariable(variableName);
        }
        return null;
    }

    public boolean addFunction(FunctionSymbol function) {
        if (functionSymbols.containsKey(function.getName())) {
            return false;
        }
        functionSymbols.put(function.getName(), function);
        return true;
    }

    public FunctionSymbol lookupFunction(String functionName) {
        FunctionSymbol functionSymbol = functionSymbols.get(functionName);
        if (functionSymbol != null) {
            return functionSymbol;
        }
        if (parent != null) {
            return parent.lookupFunction(functionName);
        }
        return null;
    }
}
