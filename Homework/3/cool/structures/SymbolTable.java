package cool.structures;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.antlr.v4.runtime.*;

import cool.compiler.Compiler;
import cool.parser.CoolParser;

public class SymbolTable {
    public static Map<String, Type> types = new LinkedHashMap<>();
    
    private static boolean semanticErrors;
    
    public static void defineBasicClasses() {
        semanticErrors = false;
        
        // Populate global scope
        types.put(Type.OBJECT.getName(), Type.OBJECT);
        types.put(Type.BOOL.getName(), Type.BOOL);
        types.put(Type.INT.getName(), Type.INT);
        types.put(Type.STRING.getName(), Type.STRING);
        types.put(Type.IO.getName(), Type.IO);
        types.put(Type.SELF_TYPE.getName(), Type.SELF_TYPE);
    }

    public static boolean addType(Type type) {
        if (SymbolTable.types.containsKey(type.name)) {
            return false;
        }
        SymbolTable.types.put(type.name, type);
        return true;
    }

    public static void clear() {
        SymbolTable.types.clear();
    }
    
    /**
     * Displays a semantic error message.
     * 
     * @param ctx Used to determine the enclosing class context of this error,
     *            which knows the file name in which the class was defined.
     * @param info Used for line and column information.
     * @param str The error message.
     */
    public static void error(ParserRuleContext ctx, Token info, String str) {
        while (! (ctx.getParent() instanceof CoolParser.ProgramContext))
            ctx = ctx.getParent();
        
        String message = "\"" + new File(Compiler.fileNames.get(ctx)).getName()
                + "\", line " + info.getLine()
                + ":" + (info.getCharPositionInLine() + 1)
                + ", Semantic error: " + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }
    
    public static void error(String str) {
        String message = "Semantic error: " + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }
    
    public static boolean hasSemanticErrors() {
        return semanticErrors;
    }
}
