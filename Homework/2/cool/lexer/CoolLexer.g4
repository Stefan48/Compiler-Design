lexer grammar CoolLexer;

tokens { ERROR } 

@header{
    package cool.lexer;	
}

@members{    
    private void raiseError(String msg) {
        setText(msg);
        setType(ERROR);
    }
}

IF : 'if';
THEN : 'then';
ELSE : 'else';
FI : 'fi';
CASE : 'case';
OF : 'of';
ESAC : 'esac';
LET : 'let';
IN : 'in';
WHILE : 'while';
LOOP : 'loop';
POOL : 'pool';
CLASS : 'class';
INHERITS : 'inherits';
NEW : 'new';
NOT : 'not';
ISVOID : 'isvoid';
BOOL : 'true' | 'false';

fragment LOWERCASE : [a-z];
fragment UPPERCASE : [A-Z];
fragment TRAILING_CHAR : LOWERCASE | UPPERCASE | DIGIT | '_';
TYPE : UPPERCASE TRAILING_CHAR*;
ID : LOWERCASE TRAILING_CHAR*;

fragment DIGIT : [0-9];
INT : DIGIT+;

STRING: '"' ('\\"' | '\\' '\r'?'\n' | .)*?
    (
	'"' {
	        boolean err = false;
            String str = getText();
            str = str.substring(1, str.length() - 1);
            for (int i = 0; i < str.length(); ++i) {
                if (str.charAt(i) == '\0') {
                    err = true;
                    raiseError("String contains null character");
                }
                else if (str.charAt(i) == '\\') {
                    if (i < str.length() - 1) {
                        if (str.charAt(i+1) == 'n') {
                            str = str.substring(0, i) + '\n' + ((i+2 < str.length()) ? str.substring(i+2) : "");
                        }
                        else if (str.charAt(i+1) == 't') {
                            str = str.substring(0, i) + '\t' + ((i+2 < str.length()) ? str.substring(i+2) : "");
                        }
                        else if (str.charAt(i+1) == 'b') {
                            str = str.substring(0, i) + '\b' + ((i+2 < str.length()) ? str.substring(i+2) : "");
                        }
                        else if (str.charAt(i+1) == 'f') {
                            str = str.substring(0, i) + '\f' + ((i+2 < str.length()) ? str.substring(i+2) : "");
                        }
                        else if (str.charAt(i+1) == '\0') {
                            err = true;
                            raiseError("String contains null character");
                        }
                        else {
                            str = str.substring(0, i) + str.substring(i+1);
                        }
                    }
                }
            }
            if (str.length() > 1024) {
                err = true;
                raiseError("String constant too long");
            }
            if (!err) {
                setText(str);
            }
        }
	| EOF {
	        raiseError("EOF in string constant");
	    }
	| '\n' {
	        raiseError("Unterminated string constant");
	    }
    );

LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';
DOT : '.';
AT : '@';
IMPLY : '=>';
NEG : '~';
MULT : '*';
DIV : '/';
PLUS : '+';
MINUS : '-';
EQUAL : '=';
LT : '<';
LE : '<=';
ASSIGN : '<-';
COLON : ':';
SEMI : ';';
COMMA : ',';

LINE_COMMENT : '--' .*? ('\n' | EOF) -> skip;
BLOCK_COMMENT :
    '(*'
    (BLOCK_COMMENT | .)*?
    ('*)' { skip(); } | EOF { raiseError("EOF in comment"); });
UNOPENED_COMMENT : '*)' { raiseError("Unmatched *)"); };

WS : [ \n\f\r\t]+ -> skip;

INVALID_CHAR : . { raiseError("Invalid character: " + getText()); };