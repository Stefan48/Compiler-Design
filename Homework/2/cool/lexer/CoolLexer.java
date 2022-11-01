// Generated from /home/student/CPL/Teme/1/Tema1/src/cool/lexer/CoolLexer.g4 by ANTLR 4.8

    package cool.lexer;	

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, IF=2, THEN=3, ELSE=4, FI=5, CASE=6, OF=7, ESAC=8, LET=9, IN=10, 
		WHILE=11, LOOP=12, POOL=13, CLASS=14, INHERITS=15, NEW=16, NOT=17, ISVOID=18, 
		BOOL=19, TYPE=20, ID=21, INT=22, STRING=23, LPAREN=24, RPAREN=25, LBRACE=26, 
		RBRACE=27, DOT=28, AT=29, IMPLY=30, NEG=31, MULT=32, DIV=33, PLUS=34, 
		MINUS=35, EQUAL=36, LT=37, LE=38, ASSIGN=39, COLON=40, SEMI=41, COMMA=42, 
		LINE_COMMENT=43, BLOCK_COMMENT=44, UNOPENED_COMMENT=45, WS=46, INVALID_CHAR=47;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "THEN", "ELSE", "FI", "CASE", "OF", "ESAC", "LET", "IN", "WHILE", 
			"LOOP", "POOL", "CLASS", "INHERITS", "NEW", "NOT", "ISVOID", "BOOL", 
			"LOWERCASE", "UPPERCASE", "TRAILING_CHAR", "TYPE", "ID", "DIGIT", "INT", 
			"STRING", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "DOT", "AT", "IMPLY", 
			"NEG", "MULT", "DIV", "PLUS", "MINUS", "EQUAL", "LT", "LE", "ASSIGN", 
			"COLON", "SEMI", "COMMA", "LINE_COMMENT", "BLOCK_COMMENT", "UNOPENED_COMMENT", 
			"WS", "INVALID_CHAR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'if'", "'then'", "'else'", "'fi'", "'case'", "'of'", "'esac'", 
			"'let'", "'in'", "'while'", "'loop'", "'pool'", "'class'", "'inherits'", 
			"'new'", "'not'", "'isvoid'", null, null, null, null, null, "'('", "')'", 
			"'{'", "'}'", "'.'", "'@'", "'=>'", "'~'", "'*'", "'/'", "'+'", "'-'", 
			"'='", "'<'", "'<='", "'<-'", "':'", "';'", "','", null, null, "'*)'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "IF", "THEN", "ELSE", "FI", "CASE", "OF", "ESAC", "LET", 
			"IN", "WHILE", "LOOP", "POOL", "CLASS", "INHERITS", "NEW", "NOT", "ISVOID", 
			"BOOL", "TYPE", "ID", "INT", "STRING", "LPAREN", "RPAREN", "LBRACE", 
			"RBRACE", "DOT", "AT", "IMPLY", "NEG", "MULT", "DIV", "PLUS", "MINUS", 
			"EQUAL", "LT", "LE", "ASSIGN", "COLON", "SEMI", "COMMA", "LINE_COMMENT", 
			"BLOCK_COMMENT", "UNOPENED_COMMENT", "WS", "INVALID_CHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	    
	    private void raiseError(String msg) {
	        setText(msg);
	        setType(ERROR);
	    }


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CoolLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 25:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 46:
			BLOCK_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 47:
			UNOPENED_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 49:
			INVALID_CHAR_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

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
			        
			break;
		case 1:

				        raiseError("EOF in string constant");
				    
			break;
		case 2:

				        raiseError("Unterminated string constant");
				    
			break;
		}
	}
	private void BLOCK_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			 skip(); 
			break;
		case 4:
			 raiseError("EOF in comment"); 
			break;
		}
	}
	private void UNOPENED_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			 raiseError("Unmatched *)"); 
			break;
		}
	}
	private void INVALID_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:
			 raiseError("Invalid character: " + getText()); 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\61\u0151\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3\2"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u00c3\n\23"+
		"\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26\5\26\u00cd\n\26\3\27\3\27\7\27"+
		"\u00d1\n\27\f\27\16\27\u00d4\13\27\3\30\3\30\7\30\u00d8\n\30\f\30\16\30"+
		"\u00db\13\30\3\31\3\31\3\32\6\32\u00e0\n\32\r\32\16\32\u00e1\3\33\3\33"+
		"\3\33\3\33\3\33\5\33\u00e9\n\33\3\33\3\33\7\33\u00ed\n\33\f\33\16\33\u00f0"+
		"\13\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u00f8\n\33\3\34\3\34\3\35\3"+
		"\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3"+
		"&\3\'\3\'\3(\3(\3)\3)\3*\3*\3*\3+\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3/\3/"+
		"\7/\u0127\n/\f/\16/\u012a\13/\3/\5/\u012d\n/\3/\3/\3\60\3\60\3\60\3\60"+
		"\3\60\7\60\u0136\n\60\f\60\16\60\u0139\13\60\3\60\3\60\3\60\3\60\3\60"+
		"\3\60\5\60\u0141\n\60\3\61\3\61\3\61\3\61\3\61\3\62\6\62\u0149\n\62\r"+
		"\62\16\62\u014a\3\62\3\62\3\63\3\63\3\63\5\u00ee\u0128\u0137\2\64\3\4"+
		"\5\5\7\6\t\7\13\b\r\t\17\n\21\13\23\f\25\r\27\16\31\17\33\20\35\21\37"+
		"\22!\23#\24%\25\'\2)\2+\2-\26/\27\61\2\63\30\65\31\67\329\33;\34=\35?"+
		"\36A\37C E!G\"I#K$M%O&Q\'S(U)W*Y+[,]-_.a/c\60e\61\3\2\7\3\2c|\3\2C\\\3"+
		"\2\62;\3\3\f\f\5\2\13\f\16\17\"\"\2\u015e\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2"+
		"\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I"+
		"\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2"+
		"\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2"+
		"\2c\3\2\2\2\2e\3\2\2\2\3g\3\2\2\2\5j\3\2\2\2\7o\3\2\2\2\tt\3\2\2\2\13"+
		"w\3\2\2\2\r|\3\2\2\2\17\177\3\2\2\2\21\u0084\3\2\2\2\23\u0088\3\2\2\2"+
		"\25\u008b\3\2\2\2\27\u0091\3\2\2\2\31\u0096\3\2\2\2\33\u009b\3\2\2\2\35"+
		"\u00a1\3\2\2\2\37\u00aa\3\2\2\2!\u00ae\3\2\2\2#\u00b2\3\2\2\2%\u00c2\3"+
		"\2\2\2\'\u00c4\3\2\2\2)\u00c6\3\2\2\2+\u00cc\3\2\2\2-\u00ce\3\2\2\2/\u00d5"+
		"\3\2\2\2\61\u00dc\3\2\2\2\63\u00df\3\2\2\2\65\u00e3\3\2\2\2\67\u00f9\3"+
		"\2\2\29\u00fb\3\2\2\2;\u00fd\3\2\2\2=\u00ff\3\2\2\2?\u0101\3\2\2\2A\u0103"+
		"\3\2\2\2C\u0105\3\2\2\2E\u0108\3\2\2\2G\u010a\3\2\2\2I\u010c\3\2\2\2K"+
		"\u010e\3\2\2\2M\u0110\3\2\2\2O\u0112\3\2\2\2Q\u0114\3\2\2\2S\u0116\3\2"+
		"\2\2U\u0119\3\2\2\2W\u011c\3\2\2\2Y\u011e\3\2\2\2[\u0120\3\2\2\2]\u0122"+
		"\3\2\2\2_\u0130\3\2\2\2a\u0142\3\2\2\2c\u0148\3\2\2\2e\u014e\3\2\2\2g"+
		"h\7k\2\2hi\7h\2\2i\4\3\2\2\2jk\7v\2\2kl\7j\2\2lm\7g\2\2mn\7p\2\2n\6\3"+
		"\2\2\2op\7g\2\2pq\7n\2\2qr\7u\2\2rs\7g\2\2s\b\3\2\2\2tu\7h\2\2uv\7k\2"+
		"\2v\n\3\2\2\2wx\7e\2\2xy\7c\2\2yz\7u\2\2z{\7g\2\2{\f\3\2\2\2|}\7q\2\2"+
		"}~\7h\2\2~\16\3\2\2\2\177\u0080\7g\2\2\u0080\u0081\7u\2\2\u0081\u0082"+
		"\7c\2\2\u0082\u0083\7e\2\2\u0083\20\3\2\2\2\u0084\u0085\7n\2\2\u0085\u0086"+
		"\7g\2\2\u0086\u0087\7v\2\2\u0087\22\3\2\2\2\u0088\u0089\7k\2\2\u0089\u008a"+
		"\7p\2\2\u008a\24\3\2\2\2\u008b\u008c\7y\2\2\u008c\u008d\7j\2\2\u008d\u008e"+
		"\7k\2\2\u008e\u008f\7n\2\2\u008f\u0090\7g\2\2\u0090\26\3\2\2\2\u0091\u0092"+
		"\7n\2\2\u0092\u0093\7q\2\2\u0093\u0094\7q\2\2\u0094\u0095\7r\2\2\u0095"+
		"\30\3\2\2\2\u0096\u0097\7r\2\2\u0097\u0098\7q\2\2\u0098\u0099\7q\2\2\u0099"+
		"\u009a\7n\2\2\u009a\32\3\2\2\2\u009b\u009c\7e\2\2\u009c\u009d\7n\2\2\u009d"+
		"\u009e\7c\2\2\u009e\u009f\7u\2\2\u009f\u00a0\7u\2\2\u00a0\34\3\2\2\2\u00a1"+
		"\u00a2\7k\2\2\u00a2\u00a3\7p\2\2\u00a3\u00a4\7j\2\2\u00a4\u00a5\7g\2\2"+
		"\u00a5\u00a6\7t\2\2\u00a6\u00a7\7k\2\2\u00a7\u00a8\7v\2\2\u00a8\u00a9"+
		"\7u\2\2\u00a9\36\3\2\2\2\u00aa\u00ab\7p\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad"+
		"\7y\2\2\u00ad \3\2\2\2\u00ae\u00af\7p\2\2\u00af\u00b0\7q\2\2\u00b0\u00b1"+
		"\7v\2\2\u00b1\"\3\2\2\2\u00b2\u00b3\7k\2\2\u00b3\u00b4\7u\2\2\u00b4\u00b5"+
		"\7x\2\2\u00b5\u00b6\7q\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8\7f\2\2\u00b8"+
		"$\3\2\2\2\u00b9\u00ba\7v\2\2\u00ba\u00bb\7t\2\2\u00bb\u00bc\7w\2\2\u00bc"+
		"\u00c3\7g\2\2\u00bd\u00be\7h\2\2\u00be\u00bf\7c\2\2\u00bf\u00c0\7n\2\2"+
		"\u00c0\u00c1\7u\2\2\u00c1\u00c3\7g\2\2\u00c2\u00b9\3\2\2\2\u00c2\u00bd"+
		"\3\2\2\2\u00c3&\3\2\2\2\u00c4\u00c5\t\2\2\2\u00c5(\3\2\2\2\u00c6\u00c7"+
		"\t\3\2\2\u00c7*\3\2\2\2\u00c8\u00cd\5\'\24\2\u00c9\u00cd\5)\25\2\u00ca"+
		"\u00cd\5\61\31\2\u00cb\u00cd\7a\2\2\u00cc\u00c8\3\2\2\2\u00cc\u00c9\3"+
		"\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cb\3\2\2\2\u00cd,\3\2\2\2\u00ce\u00d2"+
		"\5)\25\2\u00cf\u00d1\5+\26\2\u00d0\u00cf\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2"+
		"\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3.\3\2\2\2\u00d4\u00d2\3\2\2\2"+
		"\u00d5\u00d9\5\'\24\2\u00d6\u00d8\5+\26\2\u00d7\u00d6\3\2\2\2\u00d8\u00db"+
		"\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\60\3\2\2\2\u00db"+
		"\u00d9\3\2\2\2\u00dc\u00dd\t\4\2\2\u00dd\62\3\2\2\2\u00de\u00e0\5\61\31"+
		"\2\u00df\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2"+
		"\3\2\2\2\u00e2\64\3\2\2\2\u00e3\u00ee\7$\2\2\u00e4\u00e5\7^\2\2\u00e5"+
		"\u00ed\7$\2\2\u00e6\u00e8\7^\2\2\u00e7\u00e9\7\17\2\2\u00e8\u00e7\3\2"+
		"\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ed\7\f\2\2\u00eb"+
		"\u00ed\13\2\2\2\u00ec\u00e4\3\2\2\2\u00ec\u00e6\3\2\2\2\u00ec\u00eb\3"+
		"\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef"+
		"\u00f7\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f2\7$\2\2\u00f2\u00f8\b\33"+
		"\2\2\u00f3\u00f4\7\2\2\3\u00f4\u00f8\b\33\3\2\u00f5\u00f6\7\f\2\2\u00f6"+
		"\u00f8\b\33\4\2\u00f7\u00f1\3\2\2\2\u00f7\u00f3\3\2\2\2\u00f7\u00f5\3"+
		"\2\2\2\u00f8\66\3\2\2\2\u00f9\u00fa\7*\2\2\u00fa8\3\2\2\2\u00fb\u00fc"+
		"\7+\2\2\u00fc:\3\2\2\2\u00fd\u00fe\7}\2\2\u00fe<\3\2\2\2\u00ff\u0100\7"+
		"\177\2\2\u0100>\3\2\2\2\u0101\u0102\7\60\2\2\u0102@\3\2\2\2\u0103\u0104"+
		"\7B\2\2\u0104B\3\2\2\2\u0105\u0106\7?\2\2\u0106\u0107\7@\2\2\u0107D\3"+
		"\2\2\2\u0108\u0109\7\u0080\2\2\u0109F\3\2\2\2\u010a\u010b\7,\2\2\u010b"+
		"H\3\2\2\2\u010c\u010d\7\61\2\2\u010dJ\3\2\2\2\u010e\u010f\7-\2\2\u010f"+
		"L\3\2\2\2\u0110\u0111\7/\2\2\u0111N\3\2\2\2\u0112\u0113\7?\2\2\u0113P"+
		"\3\2\2\2\u0114\u0115\7>\2\2\u0115R\3\2\2\2\u0116\u0117\7>\2\2\u0117\u0118"+
		"\7?\2\2\u0118T\3\2\2\2\u0119\u011a\7>\2\2\u011a\u011b\7/\2\2\u011bV\3"+
		"\2\2\2\u011c\u011d\7<\2\2\u011dX\3\2\2\2\u011e\u011f\7=\2\2\u011fZ\3\2"+
		"\2\2\u0120\u0121\7.\2\2\u0121\\\3\2\2\2\u0122\u0123\7/\2\2\u0123\u0124"+
		"\7/\2\2\u0124\u0128\3\2\2\2\u0125\u0127\13\2\2\2\u0126\u0125\3\2\2\2\u0127"+
		"\u012a\3\2\2\2\u0128\u0129\3\2\2\2\u0128\u0126\3\2\2\2\u0129\u012c\3\2"+
		"\2\2\u012a\u0128\3\2\2\2\u012b\u012d\t\5\2\2\u012c\u012b\3\2\2\2\u012d"+
		"\u012e\3\2\2\2\u012e\u012f\b/\5\2\u012f^\3\2\2\2\u0130\u0131\7*\2\2\u0131"+
		"\u0132\7,\2\2\u0132\u0137\3\2\2\2\u0133\u0136\5_\60\2\u0134\u0136\13\2"+
		"\2\2\u0135\u0133\3\2\2\2\u0135\u0134\3\2\2\2\u0136\u0139\3\2\2\2\u0137"+
		"\u0138\3\2\2\2\u0137\u0135\3\2\2\2\u0138\u0140\3\2\2\2\u0139\u0137\3\2"+
		"\2\2\u013a\u013b\7,\2\2\u013b\u013c\7+\2\2\u013c\u013d\3\2\2\2\u013d\u0141"+
		"\b\60\6\2\u013e\u013f\7\2\2\3\u013f\u0141\b\60\7\2\u0140\u013a\3\2\2\2"+
		"\u0140\u013e\3\2\2\2\u0141`\3\2\2\2\u0142\u0143\7,\2\2\u0143\u0144\7+"+
		"\2\2\u0144\u0145\3\2\2\2\u0145\u0146\b\61\b\2\u0146b\3\2\2\2\u0147\u0149"+
		"\t\6\2\2\u0148\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u0148\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d\b\62\5\2\u014dd\3\2\2\2"+
		"\u014e\u014f\13\2\2\2\u014f\u0150\b\63\t\2\u0150f\3\2\2\2\22\2\u00c2\u00cc"+
		"\u00d2\u00d9\u00e1\u00e8\u00ec\u00ee\u00f7\u0128\u012c\u0135\u0137\u0140"+
		"\u014a\n\3\33\2\3\33\3\3\33\4\b\2\2\3\60\5\3\60\6\3\61\7\3\63\b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}