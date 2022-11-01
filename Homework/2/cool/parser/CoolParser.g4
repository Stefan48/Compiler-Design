parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header{
    package cool.parser;
}

program :  (classDefs+=classDef SEMI)+ EOF;

classDef : CLASS name=TYPE (INHERITS parentName=TYPE)? LBRACE (members+=feature SEMI)* RBRACE;

feature : name=ID LPAREN (params+=formal (COMMA params+=formal)*)? RPAREN COLON type=TYPE LBRACE body=expr RBRACE   # funcDef
        | name=ID COLON type=TYPE (ASSIGN e=expr)?                                                                  # varDef
        ;

formal : name=ID COLON type=TYPE;

expr : caller=expr (AT type=TYPE)? DOT func=ID LPAREN (params+=expr (COMMA params+=expr)*)? RPAREN                  # dispatch
     | func=ID LPAREN (params+=expr (COMMA params+=expr)*)? RPAREN                                                  # call
     | IF cond=expr THEN thenBranch=expr ELSE elseBranch=expr FI                                                    # if
     | WHILE cond=expr LOOP body=expr POOL                                                                          # while
     | LBRACE (expressions+=expr SEMI)+ RBRACE                                                                      # block
     | LET localVarDefs+=localVarDef (COMMA localVarDefs+=localVarDef)* IN body=expr                                # let
     | CASE e=expr OF (branches+=caseBranch)+ ESAC                                                                  # case
     | NEW type=TYPE                                                                                                # new
     | ISVOID e=expr                                                                                                # isVoid
     | NEG e=expr                                                                                                   # neg
     | left=expr op=(MULT | DIV) right=expr                                                                         # multDiv
     | left=expr op=(PLUS | MINUS) right=expr                                                                       # plusMinus
     | left=expr op=(LT | LE | EQUAL) right=expr                                                                    # rel
     | name=ID ASSIGN e=expr                                                                                        # assign
     | NOT e=expr                                                                                                   # not
     | LPAREN e=expr RPAREN                                                                                         # paren
     | ID                                                                                                           # id
     | BOOL                                                                                                         # bool
     | INT                                                                                                          # int
     | STRING                                                                                                       # string
     ;

localVarDef: name=ID COLON type=TYPE (ASSIGN e=expr)? ;

caseBranch: name=ID COLON type=TYPE IMPLY res=expr SEMI;
