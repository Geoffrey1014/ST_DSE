// Generated from /Users/heweigang/MY_project/ST_DSE/src/grammar/STParser.g4 by ANTLR 4.8
package grammar.gen;


import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class STParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		L_SQUARE=1, R_SQUARE=2, L_PAREN=3, R_PAREN=4, L_CURL=5, R_CURL=6, COMMA=7, 
		SEMI_COL=8, COLON=9, DOT=10, ADD_OP=11, SUB_OP=12, MUL_OP=13, DIV_OP=14, 
		MOD_OP=15, POWER_OP=16, LT_OP=17, GT_OP=18, LEQ_OP=19, GEQ_OP=20, EQ_OP=21, 
		NEQ_OP=22, AND_OP=23, XOR=24, OR=25, NOT_OP=26, AS_OP=27, RT_AS_OP=28, 
		RES_FOR=29, RES_DO=30, RES_END_FOR=31, RES_TO=32, RES_BY=33, RES_WHILE=34, 
		RES_END_WHILE=35, RES_IF=36, RES_THEN=37, RES_ELSE=38, RES_ELSIF=39, RES_END_IF=40, 
		RES_EXIT=41, RES_ARRAY=42, FromTo=43, RES_OF=44, RES_BOOL=45, RES_STRING=46, 
		RES_SINT=47, RES_INT=48, RES_DINT=49, RES_LINT=50, RES_REAL=51, RES_LREAL=52, 
		RES_USINT=53, RES_UINT=54, RES_UDINT=55, RES_ULINT=56, RES_DATE=57, RES_TIME_OF_DAY=58, 
		RES_TOD=59, RES_DATE_AND_TIME=60, RES_DT=61, RES_BYTE=62, RES_WORD=63, 
		RES_DWORD=64, RES_LWORD=65, RES_PROGRAM=66, RES_END_PROGRAM=67, RES_FUNCTION_BLOCK=68, 
		RES_END_FUNCTION_BLOCK=69, RES_FUNCTION=70, RES_END_FUNCTION=71, RES_VAR=72, 
		RES_VAR_INPUT=73, RES_VAR_OUTPUT=74, RES_VAR_INPUT_OUTPUT=75, RES_END_VAR=76, 
		RES_VAR_TEMP=77, BOOL=78, Binary_literal=79, Octal_literal=80, Decimal_literal=81, 
		Pure_decimal_digits=82, Hexadecimal_literal=83, Floating_point_e=84, Sign=85, 
		Static_string_literal=86, ID=87, Block_comment=88, WS=89;
	public static final int
		RULE_pous = 0, RULE_pou = 1, RULE_program = 2, RULE_function_block = 3, 
		RULE_function = 4, RULE_stat_list = 5, RULE_stat = 6, RULE_invoc_stat = 7, 
		RULE_assign_stat = 8, RULE_if_stat = 9, RULE_if_else_stat = 10, RULE_if_elsif_stat = 11, 
		RULE_if_elsif_else_stat = 12, RULE_if_stmt = 13, RULE_elsif_stmt = 14, 
		RULE_else_stmt = 15, RULE_for_stat = 16, RULE_for_range = 17, RULE_while_stat = 18, 
		RULE_exit_stat = 19, RULE_invoc_expr = 20, RULE_param_assignment = 21, 
		RULE_expression = 22, RULE_primary_expression = 23, RULE_location = 24, 
		RULE_var_block = 25, RULE_variable_declaration = 26, RULE_type_rule = 27, 
		RULE_elementary_type_name = 28, RULE_numeric_type_name = 29, RULE_integer_type_name = 30, 
		RULE_signed_integer_type_name = 31, RULE_unsigned_integer_type_name = 32, 
		RULE_real_type_name = 33, RULE_date_type_name = 34, RULE_bit_string_type_name = 35, 
		RULE_string_type_name = 36, RULE_variable_initializer = 37, RULE_array_initialization = 38, 
		RULE_constant = 39, RULE_numeric_literal = 40, RULE_floating_point_literal = 41, 
		RULE_floating_point_fraction = 42, RULE_decimal_exponent = 43, RULE_integer_literal = 44, 
		RULE_string_literal = 45;
	private static String[] makeRuleNames() {
		return new String[] {
			"pous", "pou", "program", "function_block", "function", "stat_list", 
			"stat", "invoc_stat", "assign_stat", "if_stat", "if_else_stat", "if_elsif_stat", 
			"if_elsif_else_stat", "if_stmt", "elsif_stmt", "else_stmt", "for_stat", 
			"for_range", "while_stat", "exit_stat", "invoc_expr", "param_assignment", 
			"expression", "primary_expression", "location", "var_block", "variable_declaration", 
			"type_rule", "elementary_type_name", "numeric_type_name", "integer_type_name", 
			"signed_integer_type_name", "unsigned_integer_type_name", "real_type_name", 
			"date_type_name", "bit_string_type_name", "string_type_name", "variable_initializer", 
			"array_initialization", "constant", "numeric_literal", "floating_point_literal", 
			"floating_point_fraction", "decimal_exponent", "integer_literal", "string_literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "']'", "'('", "')'", "'{'", "'}'", "','", "';'", "':'", 
			"'.'", "'+'", "'-'", "'*'", "'/'", "'MOD'", "'**'", "'<'", "'>'", "'<='", 
			"'>='", "'='", "'<>'", null, "'XOR'", "'OR'", "'NOT'", "':='", "'=>'", 
			"'FOR'", "'DO'", "'END_FOR'", "'TO'", "'BY'", "'WHILE'", "'END_WHILE'", 
			"'IF'", "'THEN'", "'ELSE'", "'ELSIF'", "'END_IF'", "'EXIT'", "'ARRAY'", 
			"'..'", "'OF'", "'BOOL'", "'STRING'", "'SINT'", "'INT'", "'DINT'", "'LINT'", 
			"'REAL'", "'LREAL'", "'USINT'", "'UINT'", "'UDINT'", "'ULINT'", "'DATE'", 
			"'TIME_OF_DAY'", "'TOD'", "'DATE_AND_TIME'", "'DT'", "'BYTE'", "'WORD'", 
			"'DWORD'", "'LWORD'", "'PROGRAM'", "'END_PROGRAM'", "'FUNCTION_BLOCK'", 
			"'END_FUNCTION_BLOCK'", "'FUNCTION'", "'END_FUNCTION'", "'VAR'", "'VAR_INPUT'", 
			"'VAR_OUTPUT'", "'VAR_INPUT_OUTPUT'", "'END_VAR'", "'VAR_TEMP'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "L_SQUARE", "R_SQUARE", "L_PAREN", "R_PAREN", "L_CURL", "R_CURL", 
			"COMMA", "SEMI_COL", "COLON", "DOT", "ADD_OP", "SUB_OP", "MUL_OP", "DIV_OP", 
			"MOD_OP", "POWER_OP", "LT_OP", "GT_OP", "LEQ_OP", "GEQ_OP", "EQ_OP", 
			"NEQ_OP", "AND_OP", "XOR", "OR", "NOT_OP", "AS_OP", "RT_AS_OP", "RES_FOR", 
			"RES_DO", "RES_END_FOR", "RES_TO", "RES_BY", "RES_WHILE", "RES_END_WHILE", 
			"RES_IF", "RES_THEN", "RES_ELSE", "RES_ELSIF", "RES_END_IF", "RES_EXIT", 
			"RES_ARRAY", "FromTo", "RES_OF", "RES_BOOL", "RES_STRING", "RES_SINT", 
			"RES_INT", "RES_DINT", "RES_LINT", "RES_REAL", "RES_LREAL", "RES_USINT", 
			"RES_UINT", "RES_UDINT", "RES_ULINT", "RES_DATE", "RES_TIME_OF_DAY", 
			"RES_TOD", "RES_DATE_AND_TIME", "RES_DT", "RES_BYTE", "RES_WORD", "RES_DWORD", 
			"RES_LWORD", "RES_PROGRAM", "RES_END_PROGRAM", "RES_FUNCTION_BLOCK", 
			"RES_END_FUNCTION_BLOCK", "RES_FUNCTION", "RES_END_FUNCTION", "RES_VAR", 
			"RES_VAR_INPUT", "RES_VAR_OUTPUT", "RES_VAR_INPUT_OUTPUT", "RES_END_VAR", 
			"RES_VAR_TEMP", "BOOL", "Binary_literal", "Octal_literal", "Decimal_literal", 
			"Pure_decimal_digits", "Hexadecimal_literal", "Floating_point_e", "Sign", 
			"Static_string_literal", "ID", "Block_comment", "WS"
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

	@Override
	public String getGrammarFileName() { return "STParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public STParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class PousContext extends ParserRuleContext {
		public List<PouContext> pou() {
			return getRuleContexts(PouContext.class);
		}
		public PouContext pou(int i) {
			return getRuleContext(PouContext.class,i);
		}
		public PousContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pous; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterPous(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitPous(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitPous(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PousContext pous() throws RecognitionException {
		PousContext _localctx = new PousContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_pous);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(92);
				pou();
				}
				}
				setState(95); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (RES_PROGRAM - 66)) | (1L << (RES_FUNCTION_BLOCK - 66)) | (1L << (RES_FUNCTION - 66)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PouContext extends ParserRuleContext {
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public Function_blockContext function_block() {
			return getRuleContext(Function_blockContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public PouContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pou; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterPou(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitPou(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitPou(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PouContext pou() throws RecognitionException {
		PouContext _localctx = new PouContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_pou);
		try {
			setState(100);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RES_PROGRAM:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				program();
				}
				break;
			case RES_FUNCTION_BLOCK:
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				function_block();
				}
				break;
			case RES_FUNCTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				function();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramContext extends ParserRuleContext {
		public Token name;
		public Var_blockContext var_block;
		public List<Var_blockContext> var_blocks = new ArrayList<Var_blockContext>();
		public TerminalNode RES_PROGRAM() { return getToken(STParser.RES_PROGRAM, 0); }
		public Stat_listContext stat_list() {
			return getRuleContext(Stat_listContext.class,0);
		}
		public TerminalNode RES_END_PROGRAM() { return getToken(STParser.RES_END_PROGRAM, 0); }
		public TerminalNode ID() { return getToken(STParser.ID, 0); }
		public List<Var_blockContext> var_block() {
			return getRuleContexts(Var_blockContext.class);
		}
		public Var_blockContext var_block(int i) {
			return getRuleContext(Var_blockContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(RES_PROGRAM);
			setState(103);
			((ProgramContext)_localctx).name = match(ID);
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & ((1L << (RES_VAR - 72)) | (1L << (RES_VAR_INPUT - 72)) | (1L << (RES_VAR_OUTPUT - 72)) | (1L << (RES_VAR_INPUT_OUTPUT - 72)) | (1L << (RES_VAR_TEMP - 72)))) != 0)) {
				{
				{
				setState(104);
				((ProgramContext)_localctx).var_block = var_block();
				((ProgramContext)_localctx).var_blocks.add(((ProgramContext)_localctx).var_block);
				}
				}
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(110);
			stat_list();
			setState(111);
			match(RES_END_PROGRAM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_blockContext extends ParserRuleContext {
		public Var_blockContext var_block;
		public List<Var_blockContext> var_blocks = new ArrayList<Var_blockContext>();
		public TerminalNode RES_FUNCTION_BLOCK() { return getToken(STParser.RES_FUNCTION_BLOCK, 0); }
		public TerminalNode ID() { return getToken(STParser.ID, 0); }
		public Stat_listContext stat_list() {
			return getRuleContext(Stat_listContext.class,0);
		}
		public TerminalNode RES_END_FUNCTION_BLOCK() { return getToken(STParser.RES_END_FUNCTION_BLOCK, 0); }
		public List<Var_blockContext> var_block() {
			return getRuleContexts(Var_blockContext.class);
		}
		public Var_blockContext var_block(int i) {
			return getRuleContext(Var_blockContext.class,i);
		}
		public Function_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterFunction_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitFunction_block(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitFunction_block(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Function_blockContext function_block() throws RecognitionException {
		Function_blockContext _localctx = new Function_blockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_function_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(RES_FUNCTION_BLOCK);
			setState(114);
			match(ID);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & ((1L << (RES_VAR - 72)) | (1L << (RES_VAR_INPUT - 72)) | (1L << (RES_VAR_OUTPUT - 72)) | (1L << (RES_VAR_INPUT_OUTPUT - 72)) | (1L << (RES_VAR_TEMP - 72)))) != 0)) {
				{
				{
				setState(115);
				((Function_blockContext)_localctx).var_block = var_block();
				((Function_blockContext)_localctx).var_blocks.add(((Function_blockContext)_localctx).var_block);
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(121);
			stat_list();
			setState(122);
			match(RES_END_FUNCTION_BLOCK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionContext extends ParserRuleContext {
		public Var_blockContext var_block;
		public List<Var_blockContext> var_blocks = new ArrayList<Var_blockContext>();
		public TerminalNode RES_FUNCTION() { return getToken(STParser.RES_FUNCTION, 0); }
		public TerminalNode ID() { return getToken(STParser.ID, 0); }
		public TerminalNode COLON() { return getToken(STParser.COLON, 0); }
		public Type_ruleContext type_rule() {
			return getRuleContext(Type_ruleContext.class,0);
		}
		public Stat_listContext stat_list() {
			return getRuleContext(Stat_listContext.class,0);
		}
		public TerminalNode RES_END_FUNCTION() { return getToken(STParser.RES_END_FUNCTION, 0); }
		public List<Var_blockContext> var_block() {
			return getRuleContexts(Var_blockContext.class);
		}
		public Var_blockContext var_block(int i) {
			return getRuleContext(Var_blockContext.class,i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(RES_FUNCTION);
			setState(125);
			match(ID);
			setState(126);
			match(COLON);
			setState(127);
			type_rule();
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & ((1L << (RES_VAR - 72)) | (1L << (RES_VAR_INPUT - 72)) | (1L << (RES_VAR_OUTPUT - 72)) | (1L << (RES_VAR_INPUT_OUTPUT - 72)) | (1L << (RES_VAR_TEMP - 72)))) != 0)) {
				{
				{
				setState(128);
				((FunctionContext)_localctx).var_block = var_block();
				((FunctionContext)_localctx).var_blocks.add(((FunctionContext)_localctx).var_block);
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(134);
			stat_list();
			setState(135);
			match(RES_END_FUNCTION);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Stat_listContext extends ParserRuleContext {
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public Stat_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterStat_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitStat_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitStat_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Stat_listContext stat_list() throws RecognitionException {
		Stat_listContext _localctx = new Stat_listContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_stat_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 29)) & ~0x3f) == 0 && ((1L << (_la - 29)) & ((1L << (RES_FOR - 29)) | (1L << (RES_WHILE - 29)) | (1L << (RES_IF - 29)) | (1L << (RES_EXIT - 29)) | (1L << (ID - 29)))) != 0)) {
				{
				{
				setState(137);
				stat();
				}
				}
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public Assign_statContext assign_stat() {
			return getRuleContext(Assign_statContext.class,0);
		}
		public If_statContext if_stat() {
			return getRuleContext(If_statContext.class,0);
		}
		public If_else_statContext if_else_stat() {
			return getRuleContext(If_else_statContext.class,0);
		}
		public If_elsif_statContext if_elsif_stat() {
			return getRuleContext(If_elsif_statContext.class,0);
		}
		public If_elsif_else_statContext if_elsif_else_stat() {
			return getRuleContext(If_elsif_else_statContext.class,0);
		}
		public For_statContext for_stat() {
			return getRuleContext(For_statContext.class,0);
		}
		public While_statContext while_stat() {
			return getRuleContext(While_statContext.class,0);
		}
		public Exit_statContext exit_stat() {
			return getRuleContext(Exit_statContext.class,0);
		}
		public Invoc_statContext invoc_stat() {
			return getRuleContext(Invoc_statContext.class,0);
		}
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_stat);
		try {
			setState(152);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(143);
				assign_stat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(144);
				if_stat();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(145);
				if_else_stat();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(146);
				if_elsif_stat();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(147);
				if_elsif_else_stat();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(148);
				for_stat();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(149);
				while_stat();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(150);
				exit_stat();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(151);
				invoc_stat();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Invoc_statContext extends ParserRuleContext {
		public Invoc_exprContext invoc_expr() {
			return getRuleContext(Invoc_exprContext.class,0);
		}
		public TerminalNode SEMI_COL() { return getToken(STParser.SEMI_COL, 0); }
		public Invoc_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_invoc_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterInvoc_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitInvoc_stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitInvoc_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Invoc_statContext invoc_stat() throws RecognitionException {
		Invoc_statContext _localctx = new Invoc_statContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_invoc_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			invoc_expr();
			setState(155);
			match(SEMI_COL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assign_statContext extends ParserRuleContext {
		public LocationContext location() {
			return getRuleContext(LocationContext.class,0);
		}
		public TerminalNode AS_OP() { return getToken(STParser.AS_OP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI_COL() { return getToken(STParser.SEMI_COL, 0); }
		public Assign_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterAssign_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitAssign_stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitAssign_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assign_statContext assign_stat() throws RecognitionException {
		Assign_statContext _localctx = new Assign_statContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_assign_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			location();
			setState(158);
			match(AS_OP);
			setState(159);
			expression(0);
			setState(160);
			match(SEMI_COL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_statContext extends ParserRuleContext {
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public TerminalNode RES_END_IF() { return getToken(STParser.RES_END_IF, 0); }
		public TerminalNode SEMI_COL() { return getToken(STParser.SEMI_COL, 0); }
		public If_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterIf_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitIf_stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitIf_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_statContext if_stat() throws RecognitionException {
		If_statContext _localctx = new If_statContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_if_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			if_stmt();
			setState(163);
			match(RES_END_IF);
			setState(164);
			match(SEMI_COL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_else_statContext extends ParserRuleContext {
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public Else_stmtContext else_stmt() {
			return getRuleContext(Else_stmtContext.class,0);
		}
		public TerminalNode RES_END_IF() { return getToken(STParser.RES_END_IF, 0); }
		public TerminalNode SEMI_COL() { return getToken(STParser.SEMI_COL, 0); }
		public If_else_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_else_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterIf_else_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitIf_else_stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitIf_else_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_else_statContext if_else_stat() throws RecognitionException {
		If_else_statContext _localctx = new If_else_statContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_if_else_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			if_stmt();
			setState(167);
			else_stmt();
			setState(168);
			match(RES_END_IF);
			setState(169);
			match(SEMI_COL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_elsif_statContext extends ParserRuleContext {
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public TerminalNode RES_END_IF() { return getToken(STParser.RES_END_IF, 0); }
		public TerminalNode SEMI_COL() { return getToken(STParser.SEMI_COL, 0); }
		public List<Elsif_stmtContext> elsif_stmt() {
			return getRuleContexts(Elsif_stmtContext.class);
		}
		public Elsif_stmtContext elsif_stmt(int i) {
			return getRuleContext(Elsif_stmtContext.class,i);
		}
		public If_elsif_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_elsif_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterIf_elsif_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitIf_elsif_stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitIf_elsif_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_elsif_statContext if_elsif_stat() throws RecognitionException {
		If_elsif_statContext _localctx = new If_elsif_statContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_if_elsif_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			if_stmt();
			setState(173); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(172);
				elsif_stmt();
				}
				}
				setState(175); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RES_ELSIF );
			setState(177);
			match(RES_END_IF);
			setState(178);
			match(SEMI_COL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_elsif_else_statContext extends ParserRuleContext {
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public Else_stmtContext else_stmt() {
			return getRuleContext(Else_stmtContext.class,0);
		}
		public TerminalNode RES_END_IF() { return getToken(STParser.RES_END_IF, 0); }
		public TerminalNode SEMI_COL() { return getToken(STParser.SEMI_COL, 0); }
		public List<Elsif_stmtContext> elsif_stmt() {
			return getRuleContexts(Elsif_stmtContext.class);
		}
		public Elsif_stmtContext elsif_stmt(int i) {
			return getRuleContext(Elsif_stmtContext.class,i);
		}
		public If_elsif_else_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_elsif_else_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterIf_elsif_else_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitIf_elsif_else_stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitIf_elsif_else_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_elsif_else_statContext if_elsif_else_stat() throws RecognitionException {
		If_elsif_else_statContext _localctx = new If_elsif_else_statContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_if_elsif_else_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			if_stmt();
			setState(182); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(181);
				elsif_stmt();
				}
				}
				setState(184); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RES_ELSIF );
			setState(186);
			else_stmt();
			setState(187);
			match(RES_END_IF);
			setState(188);
			match(SEMI_COL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_stmtContext extends ParserRuleContext {
		public TerminalNode RES_IF() { return getToken(STParser.RES_IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RES_THEN() { return getToken(STParser.RES_THEN, 0); }
		public Stat_listContext stat_list() {
			return getRuleContext(Stat_listContext.class,0);
		}
		public If_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterIf_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitIf_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitIf_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_stmtContext if_stmt() throws RecognitionException {
		If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_if_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(RES_IF);
			setState(191);
			expression(0);
			setState(192);
			match(RES_THEN);
			setState(193);
			stat_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Elsif_stmtContext extends ParserRuleContext {
		public TerminalNode RES_ELSIF() { return getToken(STParser.RES_ELSIF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RES_THEN() { return getToken(STParser.RES_THEN, 0); }
		public Stat_listContext stat_list() {
			return getRuleContext(Stat_listContext.class,0);
		}
		public Elsif_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elsif_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterElsif_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitElsif_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitElsif_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Elsif_stmtContext elsif_stmt() throws RecognitionException {
		Elsif_stmtContext _localctx = new Elsif_stmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_elsif_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			match(RES_ELSIF);
			setState(196);
			expression(0);
			setState(197);
			match(RES_THEN);
			setState(198);
			stat_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Else_stmtContext extends ParserRuleContext {
		public TerminalNode RES_ELSE() { return getToken(STParser.RES_ELSE, 0); }
		public Stat_listContext stat_list() {
			return getRuleContext(Stat_listContext.class,0);
		}
		public Else_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterElse_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitElse_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitElse_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Else_stmtContext else_stmt() throws RecognitionException {
		Else_stmtContext _localctx = new Else_stmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_else_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(RES_ELSE);
			setState(201);
			stat_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class For_statContext extends ParserRuleContext {
		public Token control_variable;
		public TerminalNode RES_FOR() { return getToken(STParser.RES_FOR, 0); }
		public TerminalNode AS_OP() { return getToken(STParser.AS_OP, 0); }
		public For_rangeContext for_range() {
			return getRuleContext(For_rangeContext.class,0);
		}
		public TerminalNode RES_DO() { return getToken(STParser.RES_DO, 0); }
		public Stat_listContext stat_list() {
			return getRuleContext(Stat_listContext.class,0);
		}
		public TerminalNode RES_END_FOR() { return getToken(STParser.RES_END_FOR, 0); }
		public TerminalNode SEMI_COL() { return getToken(STParser.SEMI_COL, 0); }
		public TerminalNode ID() { return getToken(STParser.ID, 0); }
		public For_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterFor_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitFor_stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitFor_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_statContext for_stat() throws RecognitionException {
		For_statContext _localctx = new For_statContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_for_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(RES_FOR);
			setState(204);
			((For_statContext)_localctx).control_variable = match(ID);
			setState(205);
			match(AS_OP);
			setState(206);
			for_range();
			setState(207);
			match(RES_DO);
			setState(208);
			stat_list();
			setState(209);
			match(RES_END_FOR);
			setState(210);
			match(SEMI_COL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class For_rangeContext extends ParserRuleContext {
		public ExpressionContext step;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode RES_TO() { return getToken(STParser.RES_TO, 0); }
		public TerminalNode RES_BY() { return getToken(STParser.RES_BY, 0); }
		public For_rangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterFor_range(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitFor_range(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitFor_range(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_rangeContext for_range() throws RecognitionException {
		For_rangeContext _localctx = new For_rangeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_for_range);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			expression(0);
			setState(213);
			match(RES_TO);
			setState(214);
			expression(0);
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RES_BY) {
				{
				setState(215);
				match(RES_BY);
				setState(216);
				((For_rangeContext)_localctx).step = expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class While_statContext extends ParserRuleContext {
		public TerminalNode RES_WHILE() { return getToken(STParser.RES_WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RES_DO() { return getToken(STParser.RES_DO, 0); }
		public Stat_listContext stat_list() {
			return getRuleContext(Stat_listContext.class,0);
		}
		public TerminalNode RES_END_WHILE() { return getToken(STParser.RES_END_WHILE, 0); }
		public TerminalNode SEMI_COL() { return getToken(STParser.SEMI_COL, 0); }
		public While_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterWhile_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitWhile_stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitWhile_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_statContext while_stat() throws RecognitionException {
		While_statContext _localctx = new While_statContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_while_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(RES_WHILE);
			setState(220);
			expression(0);
			setState(221);
			match(RES_DO);
			setState(222);
			stat_list();
			setState(223);
			match(RES_END_WHILE);
			setState(224);
			match(SEMI_COL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Exit_statContext extends ParserRuleContext {
		public TerminalNode RES_EXIT() { return getToken(STParser.RES_EXIT, 0); }
		public Exit_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exit_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterExit_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitExit_stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitExit_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exit_statContext exit_stat() throws RecognitionException {
		Exit_statContext _localctx = new Exit_statContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_exit_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(RES_EXIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Invoc_exprContext extends ParserRuleContext {
		public Token fb_name;
		public TerminalNode L_PAREN() { return getToken(STParser.L_PAREN, 0); }
		public TerminalNode R_PAREN() { return getToken(STParser.R_PAREN, 0); }
		public TerminalNode ID() { return getToken(STParser.ID, 0); }
		public List<Param_assignmentContext> param_assignment() {
			return getRuleContexts(Param_assignmentContext.class);
		}
		public Param_assignmentContext param_assignment(int i) {
			return getRuleContext(Param_assignmentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(STParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(STParser.COMMA, i);
		}
		public Invoc_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_invoc_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterInvoc_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitInvoc_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitInvoc_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Invoc_exprContext invoc_expr() throws RecognitionException {
		Invoc_exprContext _localctx = new Invoc_exprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_invoc_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			((Invoc_exprContext)_localctx).fb_name = match(ID);
			setState(229);
			match(L_PAREN);
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L_PAREN) | (1L << SUB_OP) | (1L << NOT_OP))) != 0) || ((((_la - 78)) & ~0x3f) == 0 && ((1L << (_la - 78)) & ((1L << (BOOL - 78)) | (1L << (Binary_literal - 78)) | (1L << (Octal_literal - 78)) | (1L << (Decimal_literal - 78)) | (1L << (Pure_decimal_digits - 78)) | (1L << (Hexadecimal_literal - 78)) | (1L << (Static_string_literal - 78)) | (1L << (ID - 78)))) != 0)) {
				{
				setState(230);
				param_assignment();
				setState(235);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(231);
					match(COMMA);
					setState(232);
					param_assignment();
					}
					}
					setState(237);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(240);
			match(R_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_assignmentContext extends ParserRuleContext {
		public Param_assignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_assignment; }
	 
		public Param_assignmentContext() { }
		public void copyFrom(Param_assignmentContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExternArgContext extends Param_assignmentContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExternArgContext(Param_assignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterExternArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitExternArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitExternArg(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignOutputContext extends Param_assignmentContext {
		public List<TerminalNode> ID() { return getTokens(STParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(STParser.ID, i);
		}
		public TerminalNode RT_AS_OP() { return getToken(STParser.RT_AS_OP, 0); }
		public TerminalNode NOT_OP() { return getToken(STParser.NOT_OP, 0); }
		public AssignOutputContext(Param_assignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterAssignOutput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitAssignOutput(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitAssignOutput(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignParamContext extends Param_assignmentContext {
		public TerminalNode ID() { return getToken(STParser.ID, 0); }
		public TerminalNode AS_OP() { return getToken(STParser.AS_OP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignParamContext(Param_assignmentContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterAssignParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitAssignParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitAssignParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Param_assignmentContext param_assignment() throws RecognitionException {
		Param_assignmentContext _localctx = new Param_assignmentContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_param_assignment);
		int _la;
		try {
			setState(252);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new ExternArgContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(242);
				expression(0);
				}
				break;
			case 2:
				_localctx = new AssignParamContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(243);
				match(ID);
				setState(244);
				match(AS_OP);
				setState(245);
				expression(0);
				}
				break;
			case 3:
				_localctx = new AssignOutputContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT_OP) {
					{
					setState(246);
					match(NOT_OP);
					}
				}

				setState(249);
				match(ID);
				setState(250);
				match(RT_AS_OP);
				setState(251);
				match(ID);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArithExprContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL_OP() { return getToken(STParser.MUL_OP, 0); }
		public TerminalNode DIV_OP() { return getToken(STParser.DIV_OP, 0); }
		public TerminalNode MOD_OP() { return getToken(STParser.MOD_OP, 0); }
		public TerminalNode ADD_OP() { return getToken(STParser.ADD_OP, 0); }
		public TerminalNode SUB_OP() { return getToken(STParser.SUB_OP, 0); }
		public TerminalNode POWER_OP() { return getToken(STParser.POWER_OP, 0); }
		public ArithExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterArithExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitArithExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitArithExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ComparisonContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LT_OP() { return getToken(STParser.LT_OP, 0); }
		public TerminalNode GT_OP() { return getToken(STParser.GT_OP, 0); }
		public TerminalNode LEQ_OP() { return getToken(STParser.LEQ_OP, 0); }
		public TerminalNode GEQ_OP() { return getToken(STParser.GEQ_OP, 0); }
		public ComparisonContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitComparison(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitComparison(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExperContext extends ExpressionContext {
		public TerminalNode L_PAREN() { return getToken(STParser.L_PAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(STParser.R_PAREN, 0); }
		public ParenExperContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterParenExper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitParenExper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitParenExper(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EquateExprContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQ_OP() { return getToken(STParser.EQ_OP, 0); }
		public TerminalNode NEQ_OP() { return getToken(STParser.NEQ_OP, 0); }
		public EquateExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterEquateExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitEquateExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitEquateExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrimaryExprContext extends ExpressionContext {
		public Primary_expressionContext primary_expression() {
			return getRuleContext(Primary_expressionContext.class,0);
		}
		public PrimaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterPrimaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitPrimaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitPrimaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotExprContext extends ExpressionContext {
		public TerminalNode NOT_OP() { return getToken(STParser.NOT_OP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterNotExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitNotExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitNotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LogicContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND_OP() { return getToken(STParser.AND_OP, 0); }
		public TerminalNode XOR() { return getToken(STParser.XOR, 0); }
		public TerminalNode OR() { return getToken(STParser.OR, 0); }
		public LogicContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterLogic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitLogic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitLogic(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NegateExprContext extends ExpressionContext {
		public TerminalNode SUB_OP() { return getToken(STParser.SUB_OP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NegateExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterNegateExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitNegateExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitNegateExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 44;
		enterRecursionRule(_localctx, 44, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				_localctx = new PrimaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(255);
				primary_expression();
				}
				break;
			case 2:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(256);
				match(NOT_OP);
				setState(257);
				expression(7);
				}
				break;
			case 3:
				{
				_localctx = new NegateExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(258);
				match(SUB_OP);
				setState(259);
				expression(6);
				}
				break;
			case 4:
				{
				_localctx = new ParenExperContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(260);
				match(L_PAREN);
				setState(261);
				expression(0);
				setState(262);
				match(R_PAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(280);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(278);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new ArithExprContext(new ExpressionContext(_parentctx, _parentState));
						((ArithExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(266);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(267);
						((ArithExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD_OP) | (1L << SUB_OP) | (1L << MUL_OP) | (1L << DIV_OP) | (1L << MOD_OP) | (1L << POWER_OP))) != 0)) ) {
							((ArithExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(268);
						((ArithExprContext)_localctx).right = expression(6);
						}
						break;
					case 2:
						{
						_localctx = new ComparisonContext(new ExpressionContext(_parentctx, _parentState));
						((ComparisonContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(269);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(270);
						((ComparisonContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LT_OP) | (1L << GT_OP) | (1L << LEQ_OP) | (1L << GEQ_OP))) != 0)) ) {
							((ComparisonContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(271);
						((ComparisonContext)_localctx).right = expression(5);
						}
						break;
					case 3:
						{
						_localctx = new EquateExprContext(new ExpressionContext(_parentctx, _parentState));
						((EquateExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(272);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(273);
						((EquateExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQ_OP || _la==NEQ_OP) ) {
							((EquateExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(274);
						((EquateExprContext)_localctx).right = expression(4);
						}
						break;
					case 4:
						{
						_localctx = new LogicContext(new ExpressionContext(_parentctx, _parentState));
						((LogicContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(275);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(276);
						((LogicContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND_OP) | (1L << XOR) | (1L << OR))) != 0)) ) {
							((LogicContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(277);
						((LogicContext)_localctx).right = expression(3);
						}
						break;
					}
					} 
				}
				setState(282);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Primary_expressionContext extends ParserRuleContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public LocationContext location() {
			return getRuleContext(LocationContext.class,0);
		}
		public Invoc_exprContext invoc_expr() {
			return getRuleContext(Invoc_exprContext.class,0);
		}
		public Primary_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterPrimary_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitPrimary_expression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitPrimary_expression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Primary_expressionContext primary_expression() throws RecognitionException {
		Primary_expressionContext _localctx = new Primary_expressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_primary_expression);
		try {
			setState(286);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(283);
				constant();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(284);
				location();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(285);
				invoc_expr();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocationContext extends ParserRuleContext {
		public LocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location; }
	 
		public LocationContext() { }
		public void copyFrom(LocationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayLocationContext extends LocationContext {
		public TerminalNode ID() { return getToken(STParser.ID, 0); }
		public TerminalNode L_SQUARE() { return getToken(STParser.L_SQUARE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode R_SQUARE() { return getToken(STParser.R_SQUARE, 0); }
		public ArrayLocationContext(LocationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterArrayLocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitArrayLocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitArrayLocation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FbLcationContext extends LocationContext {
		public List<TerminalNode> ID() { return getTokens(STParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(STParser.ID, i);
		}
		public TerminalNode DOT() { return getToken(STParser.DOT, 0); }
		public FbLcationContext(LocationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterFbLcation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitFbLcation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitFbLcation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarLocationContext extends LocationContext {
		public TerminalNode ID() { return getToken(STParser.ID, 0); }
		public VarLocationContext(LocationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterVarLocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitVarLocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitVarLocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocationContext location() throws RecognitionException {
		LocationContext _localctx = new LocationContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_location);
		try {
			setState(297);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				_localctx = new VarLocationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				match(ID);
				}
				break;
			case 2:
				_localctx = new ArrayLocationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(289);
				match(ID);
				setState(290);
				match(L_SQUARE);
				setState(291);
				expression(0);
				setState(292);
				match(R_SQUARE);
				}
				break;
			case 3:
				_localctx = new FbLcationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(294);
				match(ID);
				setState(295);
				match(DOT);
				setState(296);
				match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_blockContext extends ParserRuleContext {
		public Token var_acc_type;
		public TerminalNode RES_END_VAR() { return getToken(STParser.RES_END_VAR, 0); }
		public TerminalNode RES_VAR() { return getToken(STParser.RES_VAR, 0); }
		public TerminalNode RES_VAR_INPUT() { return getToken(STParser.RES_VAR_INPUT, 0); }
		public TerminalNode RES_VAR_OUTPUT() { return getToken(STParser.RES_VAR_OUTPUT, 0); }
		public TerminalNode RES_VAR_INPUT_OUTPUT() { return getToken(STParser.RES_VAR_INPUT_OUTPUT, 0); }
		public TerminalNode RES_VAR_TEMP() { return getToken(STParser.RES_VAR_TEMP, 0); }
		public List<Variable_declarationContext> variable_declaration() {
			return getRuleContexts(Variable_declarationContext.class);
		}
		public Variable_declarationContext variable_declaration(int i) {
			return getRuleContext(Variable_declarationContext.class,i);
		}
		public Var_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterVar_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitVar_block(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitVar_block(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_blockContext var_block() throws RecognitionException {
		Var_blockContext _localctx = new Var_blockContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_var_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			((Var_blockContext)_localctx).var_acc_type = _input.LT(1);
			_la = _input.LA(1);
			if ( !(((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & ((1L << (RES_VAR - 72)) | (1L << (RES_VAR_INPUT - 72)) | (1L << (RES_VAR_OUTPUT - 72)) | (1L << (RES_VAR_INPUT_OUTPUT - 72)) | (1L << (RES_VAR_TEMP - 72)))) != 0)) ) {
				((Var_blockContext)_localctx).var_acc_type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(300);
				variable_declaration();
				}
				}
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(306);
			match(RES_END_VAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_declarationContext extends ParserRuleContext {
		public Token ID;
		public List<Token> names = new ArrayList<Token>();
		public TerminalNode COLON() { return getToken(STParser.COLON, 0); }
		public Type_ruleContext type_rule() {
			return getRuleContext(Type_ruleContext.class,0);
		}
		public TerminalNode SEMI_COL() { return getToken(STParser.SEMI_COL, 0); }
		public List<TerminalNode> ID() { return getTokens(STParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(STParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(STParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(STParser.COMMA, i);
		}
		public TerminalNode AS_OP() { return getToken(STParser.AS_OP, 0); }
		public Variable_initializerContext variable_initializer() {
			return getRuleContext(Variable_initializerContext.class,0);
		}
		public Variable_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterVariable_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitVariable_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitVariable_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_declarationContext variable_declaration() throws RecognitionException {
		Variable_declarationContext _localctx = new Variable_declarationContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_variable_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			((Variable_declarationContext)_localctx).ID = match(ID);
			((Variable_declarationContext)_localctx).names.add(((Variable_declarationContext)_localctx).ID);
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(309);
				match(COMMA);
				setState(310);
				((Variable_declarationContext)_localctx).ID = match(ID);
				((Variable_declarationContext)_localctx).names.add(((Variable_declarationContext)_localctx).ID);
				}
				}
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(316);
			match(COLON);
			setState(317);
			type_rule();
			setState(320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS_OP) {
				{
				setState(318);
				match(AS_OP);
				setState(319);
				variable_initializer();
				}
			}

			setState(322);
			match(SEMI_COL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_ruleContext extends ParserRuleContext {
		public Type_ruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_rule; }
	 
		public Type_ruleContext() { }
		public void copyFrom(Type_ruleContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SimpleTypeContext extends Type_ruleContext {
		public Elementary_type_nameContext elementary_type_name() {
			return getRuleContext(Elementary_type_nameContext.class,0);
		}
		public SimpleTypeContext(Type_ruleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterSimpleType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitSimpleType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitSimpleType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayTypeContext extends Type_ruleContext {
		public Integer_literalContext integer_literal;
		public List<Integer_literalContext> range = new ArrayList<Integer_literalContext>();
		public TerminalNode RES_ARRAY() { return getToken(STParser.RES_ARRAY, 0); }
		public TerminalNode L_SQUARE() { return getToken(STParser.L_SQUARE, 0); }
		public TerminalNode FromTo() { return getToken(STParser.FromTo, 0); }
		public TerminalNode R_SQUARE() { return getToken(STParser.R_SQUARE, 0); }
		public TerminalNode RES_OF() { return getToken(STParser.RES_OF, 0); }
		public Elementary_type_nameContext elementary_type_name() {
			return getRuleContext(Elementary_type_nameContext.class,0);
		}
		public List<Integer_literalContext> integer_literal() {
			return getRuleContexts(Integer_literalContext.class);
		}
		public Integer_literalContext integer_literal(int i) {
			return getRuleContext(Integer_literalContext.class,i);
		}
		public ArrayTypeContext(Type_ruleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitArrayType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_ruleContext type_rule() throws RecognitionException {
		Type_ruleContext _localctx = new Type_ruleContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_type_rule);
		try {
			setState(334);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RES_BOOL:
			case RES_STRING:
			case RES_SINT:
			case RES_INT:
			case RES_DINT:
			case RES_LINT:
			case RES_REAL:
			case RES_LREAL:
			case RES_USINT:
			case RES_UINT:
			case RES_UDINT:
			case RES_ULINT:
			case RES_DATE:
			case RES_TIME_OF_DAY:
			case RES_TOD:
			case RES_DATE_AND_TIME:
			case RES_DT:
			case RES_BYTE:
			case RES_WORD:
			case RES_DWORD:
			case RES_LWORD:
				_localctx = new SimpleTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(324);
				elementary_type_name();
				}
				break;
			case RES_ARRAY:
				_localctx = new ArrayTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(325);
				match(RES_ARRAY);
				setState(326);
				match(L_SQUARE);
				setState(327);
				((ArrayTypeContext)_localctx).integer_literal = integer_literal();
				((ArrayTypeContext)_localctx).range.add(((ArrayTypeContext)_localctx).integer_literal);
				setState(328);
				match(FromTo);
				setState(329);
				((ArrayTypeContext)_localctx).integer_literal = integer_literal();
				((ArrayTypeContext)_localctx).range.add(((ArrayTypeContext)_localctx).integer_literal);
				setState(330);
				match(R_SQUARE);
				setState(331);
				match(RES_OF);
				setState(332);
				elementary_type_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Elementary_type_nameContext extends ParserRuleContext {
		public Numeric_type_nameContext numeric_type_name() {
			return getRuleContext(Numeric_type_nameContext.class,0);
		}
		public Date_type_nameContext date_type_name() {
			return getRuleContext(Date_type_nameContext.class,0);
		}
		public Bit_string_type_nameContext bit_string_type_name() {
			return getRuleContext(Bit_string_type_nameContext.class,0);
		}
		public String_type_nameContext string_type_name() {
			return getRuleContext(String_type_nameContext.class,0);
		}
		public Elementary_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementary_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterElementary_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitElementary_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitElementary_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Elementary_type_nameContext elementary_type_name() throws RecognitionException {
		Elementary_type_nameContext _localctx = new Elementary_type_nameContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_elementary_type_name);
		try {
			setState(340);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RES_SINT:
			case RES_INT:
			case RES_DINT:
			case RES_LINT:
			case RES_REAL:
			case RES_LREAL:
			case RES_USINT:
			case RES_UINT:
			case RES_UDINT:
			case RES_ULINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(336);
				numeric_type_name();
				}
				break;
			case RES_DATE:
			case RES_TIME_OF_DAY:
			case RES_TOD:
			case RES_DATE_AND_TIME:
			case RES_DT:
				enterOuterAlt(_localctx, 2);
				{
				setState(337);
				date_type_name();
				}
				break;
			case RES_BOOL:
			case RES_BYTE:
			case RES_WORD:
			case RES_DWORD:
			case RES_LWORD:
				enterOuterAlt(_localctx, 3);
				{
				setState(338);
				bit_string_type_name();
				}
				break;
			case RES_STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(339);
				string_type_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Numeric_type_nameContext extends ParserRuleContext {
		public Integer_type_nameContext integer_type_name() {
			return getRuleContext(Integer_type_nameContext.class,0);
		}
		public Real_type_nameContext real_type_name() {
			return getRuleContext(Real_type_nameContext.class,0);
		}
		public Numeric_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterNumeric_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitNumeric_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitNumeric_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numeric_type_nameContext numeric_type_name() throws RecognitionException {
		Numeric_type_nameContext _localctx = new Numeric_type_nameContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_numeric_type_name);
		try {
			setState(344);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RES_SINT:
			case RES_INT:
			case RES_DINT:
			case RES_LINT:
			case RES_USINT:
			case RES_UINT:
			case RES_UDINT:
			case RES_ULINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(342);
				integer_type_name();
				}
				break;
			case RES_REAL:
			case RES_LREAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(343);
				real_type_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Integer_type_nameContext extends ParserRuleContext {
		public Signed_integer_type_nameContext signed_integer_type_name() {
			return getRuleContext(Signed_integer_type_nameContext.class,0);
		}
		public Unsigned_integer_type_nameContext unsigned_integer_type_name() {
			return getRuleContext(Unsigned_integer_type_nameContext.class,0);
		}
		public Integer_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterInteger_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitInteger_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitInteger_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Integer_type_nameContext integer_type_name() throws RecognitionException {
		Integer_type_nameContext _localctx = new Integer_type_nameContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_integer_type_name);
		try {
			setState(348);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RES_SINT:
			case RES_INT:
			case RES_DINT:
			case RES_LINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(346);
				signed_integer_type_name();
				}
				break;
			case RES_USINT:
			case RES_UINT:
			case RES_UDINT:
			case RES_ULINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(347);
				unsigned_integer_type_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Signed_integer_type_nameContext extends ParserRuleContext {
		public TerminalNode RES_SINT() { return getToken(STParser.RES_SINT, 0); }
		public TerminalNode RES_INT() { return getToken(STParser.RES_INT, 0); }
		public TerminalNode RES_DINT() { return getToken(STParser.RES_DINT, 0); }
		public TerminalNode RES_LINT() { return getToken(STParser.RES_LINT, 0); }
		public Signed_integer_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signed_integer_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterSigned_integer_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitSigned_integer_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitSigned_integer_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Signed_integer_type_nameContext signed_integer_type_name() throws RecognitionException {
		Signed_integer_type_nameContext _localctx = new Signed_integer_type_nameContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_signed_integer_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RES_SINT) | (1L << RES_INT) | (1L << RES_DINT) | (1L << RES_LINT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unsigned_integer_type_nameContext extends ParserRuleContext {
		public TerminalNode RES_USINT() { return getToken(STParser.RES_USINT, 0); }
		public TerminalNode RES_UINT() { return getToken(STParser.RES_UINT, 0); }
		public TerminalNode RES_UDINT() { return getToken(STParser.RES_UDINT, 0); }
		public TerminalNode RES_ULINT() { return getToken(STParser.RES_ULINT, 0); }
		public Unsigned_integer_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsigned_integer_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterUnsigned_integer_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitUnsigned_integer_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitUnsigned_integer_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unsigned_integer_type_nameContext unsigned_integer_type_name() throws RecognitionException {
		Unsigned_integer_type_nameContext _localctx = new Unsigned_integer_type_nameContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_unsigned_integer_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(352);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RES_USINT) | (1L << RES_UINT) | (1L << RES_UDINT) | (1L << RES_ULINT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Real_type_nameContext extends ParserRuleContext {
		public TerminalNode RES_REAL() { return getToken(STParser.RES_REAL, 0); }
		public TerminalNode RES_LREAL() { return getToken(STParser.RES_LREAL, 0); }
		public Real_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_real_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterReal_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitReal_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitReal_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Real_type_nameContext real_type_name() throws RecognitionException {
		Real_type_nameContext _localctx = new Real_type_nameContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_real_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			_la = _input.LA(1);
			if ( !(_la==RES_REAL || _la==RES_LREAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Date_type_nameContext extends ParserRuleContext {
		public TerminalNode RES_DATE() { return getToken(STParser.RES_DATE, 0); }
		public TerminalNode RES_TIME_OF_DAY() { return getToken(STParser.RES_TIME_OF_DAY, 0); }
		public TerminalNode RES_TOD() { return getToken(STParser.RES_TOD, 0); }
		public TerminalNode RES_DATE_AND_TIME() { return getToken(STParser.RES_DATE_AND_TIME, 0); }
		public TerminalNode RES_DT() { return getToken(STParser.RES_DT, 0); }
		public Date_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterDate_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitDate_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitDate_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Date_type_nameContext date_type_name() throws RecognitionException {
		Date_type_nameContext _localctx = new Date_type_nameContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_date_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RES_DATE) | (1L << RES_TIME_OF_DAY) | (1L << RES_TOD) | (1L << RES_DATE_AND_TIME) | (1L << RES_DT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bit_string_type_nameContext extends ParserRuleContext {
		public TerminalNode RES_BOOL() { return getToken(STParser.RES_BOOL, 0); }
		public TerminalNode RES_BYTE() { return getToken(STParser.RES_BYTE, 0); }
		public TerminalNode RES_WORD() { return getToken(STParser.RES_WORD, 0); }
		public TerminalNode RES_DWORD() { return getToken(STParser.RES_DWORD, 0); }
		public TerminalNode RES_LWORD() { return getToken(STParser.RES_LWORD, 0); }
		public Bit_string_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bit_string_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterBit_string_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitBit_string_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitBit_string_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bit_string_type_nameContext bit_string_type_name() throws RecognitionException {
		Bit_string_type_nameContext _localctx = new Bit_string_type_nameContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_bit_string_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			_la = _input.LA(1);
			if ( !(((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (RES_BOOL - 45)) | (1L << (RES_BYTE - 45)) | (1L << (RES_WORD - 45)) | (1L << (RES_DWORD - 45)) | (1L << (RES_LWORD - 45)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_type_nameContext extends ParserRuleContext {
		public TerminalNode RES_STRING() { return getToken(STParser.RES_STRING, 0); }
		public String_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterString_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitString_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitString_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final String_type_nameContext string_type_name() throws RecognitionException {
		String_type_nameContext _localctx = new String_type_nameContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_string_type_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			match(RES_STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_initializerContext extends ParserRuleContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public Array_initializationContext array_initialization() {
			return getRuleContext(Array_initializationContext.class,0);
		}
		public Variable_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterVariable_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitVariable_initializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitVariable_initializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_initializerContext variable_initializer() throws RecognitionException {
		Variable_initializerContext _localctx = new Variable_initializerContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_variable_initializer);
		try {
			setState(364);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUB_OP:
			case BOOL:
			case Binary_literal:
			case Octal_literal:
			case Decimal_literal:
			case Pure_decimal_digits:
			case Hexadecimal_literal:
			case Static_string_literal:
				enterOuterAlt(_localctx, 1);
				{
				setState(362);
				constant();
				}
				break;
			case L_SQUARE:
				enterOuterAlt(_localctx, 2);
				{
				setState(363);
				array_initialization();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Array_initializationContext extends ParserRuleContext {
		public TerminalNode L_SQUARE() { return getToken(STParser.L_SQUARE, 0); }
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public TerminalNode R_SQUARE() { return getToken(STParser.R_SQUARE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(STParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(STParser.COMMA, i);
		}
		public Array_initializationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_initialization; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterArray_initialization(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitArray_initialization(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitArray_initialization(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Array_initializationContext array_initialization() throws RecognitionException {
		Array_initializationContext _localctx = new Array_initializationContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_array_initialization);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			match(L_SQUARE);
			setState(372);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(367);
					constant();
					setState(368);
					match(COMMA);
					}
					} 
				}
				setState(374);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			setState(375);
			constant();
			setState(376);
			match(R_SQUARE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public Numeric_literalContext numeric_literal() {
			return getRuleContext(Numeric_literalContext.class,0);
		}
		public String_literalContext string_literal() {
			return getRuleContext(String_literalContext.class,0);
		}
		public TerminalNode BOOL() { return getToken(STParser.BOOL, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_constant);
		try {
			setState(381);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUB_OP:
			case Binary_literal:
			case Octal_literal:
			case Decimal_literal:
			case Pure_decimal_digits:
			case Hexadecimal_literal:
				enterOuterAlt(_localctx, 1);
				{
				setState(378);
				numeric_literal();
				}
				break;
			case Static_string_literal:
				enterOuterAlt(_localctx, 2);
				{
				setState(379);
				string_literal();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 3);
				{
				setState(380);
				match(BOOL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Numeric_literalContext extends ParserRuleContext {
		public Integer_literalContext integer_literal() {
			return getRuleContext(Integer_literalContext.class,0);
		}
		public Floating_point_literalContext floating_point_literal() {
			return getRuleContext(Floating_point_literalContext.class,0);
		}
		public Numeric_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterNumeric_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitNumeric_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitNumeric_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numeric_literalContext numeric_literal() throws RecognitionException {
		Numeric_literalContext _localctx = new Numeric_literalContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_numeric_literal);
		try {
			setState(385);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(383);
				integer_literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(384);
				floating_point_literal();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Floating_point_literalContext extends ParserRuleContext {
		public Floating_point_fractionContext floating_point_fraction() {
			return getRuleContext(Floating_point_fractionContext.class,0);
		}
		public Decimal_exponentContext decimal_exponent() {
			return getRuleContext(Decimal_exponentContext.class,0);
		}
		public Floating_point_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floating_point_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterFloating_point_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitFloating_point_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitFloating_point_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Floating_point_literalContext floating_point_literal() throws RecognitionException {
		Floating_point_literalContext _localctx = new Floating_point_literalContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_floating_point_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(387);
			floating_point_fraction();
			setState(389);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(388);
				decimal_exponent();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Floating_point_fractionContext extends ParserRuleContext {
		public List<TerminalNode> Decimal_literal() { return getTokens(STParser.Decimal_literal); }
		public TerminalNode Decimal_literal(int i) {
			return getToken(STParser.Decimal_literal, i);
		}
		public TerminalNode SUB_OP() { return getToken(STParser.SUB_OP, 0); }
		public TerminalNode DOT() { return getToken(STParser.DOT, 0); }
		public Floating_point_fractionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floating_point_fraction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterFloating_point_fraction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitFloating_point_fraction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitFloating_point_fraction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Floating_point_fractionContext floating_point_fraction() throws RecognitionException {
		Floating_point_fractionContext _localctx = new Floating_point_fractionContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_floating_point_fraction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SUB_OP) {
				{
				setState(391);
				match(SUB_OP);
				}
			}

			setState(394);
			match(Decimal_literal);
			setState(397);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(395);
				match(DOT);
				setState(396);
				match(Decimal_literal);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Decimal_exponentContext extends ParserRuleContext {
		public TerminalNode Floating_point_e() { return getToken(STParser.Floating_point_e, 0); }
		public TerminalNode Decimal_literal() { return getToken(STParser.Decimal_literal, 0); }
		public TerminalNode Sign() { return getToken(STParser.Sign, 0); }
		public Decimal_exponentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal_exponent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterDecimal_exponent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitDecimal_exponent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitDecimal_exponent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decimal_exponentContext decimal_exponent() throws RecognitionException {
		Decimal_exponentContext _localctx = new Decimal_exponentContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_decimal_exponent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			match(Floating_point_e);
			setState(401);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Sign) {
				{
				setState(400);
				match(Sign);
				}
			}

			setState(403);
			match(Decimal_literal);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Integer_literalContext extends ParserRuleContext {
		public TerminalNode Binary_literal() { return getToken(STParser.Binary_literal, 0); }
		public TerminalNode Octal_literal() { return getToken(STParser.Octal_literal, 0); }
		public TerminalNode Decimal_literal() { return getToken(STParser.Decimal_literal, 0); }
		public TerminalNode Pure_decimal_digits() { return getToken(STParser.Pure_decimal_digits, 0); }
		public TerminalNode Hexadecimal_literal() { return getToken(STParser.Hexadecimal_literal, 0); }
		public TerminalNode SUB_OP() { return getToken(STParser.SUB_OP, 0); }
		public Integer_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterInteger_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitInteger_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitInteger_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Integer_literalContext integer_literal() throws RecognitionException {
		Integer_literalContext _localctx = new Integer_literalContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_integer_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SUB_OP) {
				{
				setState(405);
				match(SUB_OP);
				}
			}

			setState(408);
			_la = _input.LA(1);
			if ( !(((((_la - 79)) & ~0x3f) == 0 && ((1L << (_la - 79)) & ((1L << (Binary_literal - 79)) | (1L << (Octal_literal - 79)) | (1L << (Decimal_literal - 79)) | (1L << (Pure_decimal_digits - 79)) | (1L << (Hexadecimal_literal - 79)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_literalContext extends ParserRuleContext {
		public TerminalNode Static_string_literal() { return getToken(STParser.Static_string_literal, 0); }
		public String_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterString_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitString_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitString_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final String_literalContext string_literal() throws RecognitionException {
		String_literalContext _localctx = new String_literalContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_string_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(410);
			match(Static_string_literal);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 22:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 5);
		case 1:
			return precpred(_ctx, 4);
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3[\u019f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\3\2\6\2`\n\2\r\2\16\2a\3\3\3\3\3\3\5\3g\n\3\3\4"+
		"\3\4\3\4\7\4l\n\4\f\4\16\4o\13\4\3\4\3\4\3\4\3\5\3\5\3\5\7\5w\n\5\f\5"+
		"\16\5z\13\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\7\6\u0084\n\6\f\6\16\6\u0087"+
		"\13\6\3\6\3\6\3\6\3\7\7\7\u008d\n\7\f\7\16\7\u0090\13\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\5\b\u009b\n\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\6\r\u00b0\n\r\r\r\16\r"+
		"\u00b1\3\r\3\r\3\r\3\16\3\16\6\16\u00b9\n\16\r\16\16\16\u00ba\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23"+
		"\3\23\5\23\u00dc\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\26"+
		"\3\26\3\26\3\26\3\26\7\26\u00ec\n\26\f\26\16\26\u00ef\13\26\5\26\u00f1"+
		"\n\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\5\27\u00fa\n\27\3\27\3\27\3\27"+
		"\5\27\u00ff\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30"+
		"\u010b\n\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\7\30\u0119\n\30\f\30\16\30\u011c\13\30\3\31\3\31\3\31\5\31\u0121\n\31"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u012c\n\32\3\33\3\33"+
		"\7\33\u0130\n\33\f\33\16\33\u0133\13\33\3\33\3\33\3\34\3\34\3\34\7\34"+
		"\u013a\n\34\f\34\16\34\u013d\13\34\3\34\3\34\3\34\3\34\5\34\u0143\n\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u0151"+
		"\n\35\3\36\3\36\3\36\3\36\5\36\u0157\n\36\3\37\3\37\5\37\u015b\n\37\3"+
		" \3 \5 \u015f\n \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\5\'\u016f"+
		"\n\'\3(\3(\3(\3(\7(\u0175\n(\f(\16(\u0178\13(\3(\3(\3(\3)\3)\3)\5)\u0180"+
		"\n)\3*\3*\5*\u0184\n*\3+\3+\5+\u0188\n+\3,\5,\u018b\n,\3,\3,\3,\5,\u0190"+
		"\n,\3-\3-\5-\u0194\n-\3-\3-\3.\5.\u0199\n.\3.\3.\3/\3/\3/\2\3.\60\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNP"+
		"RTVXZ\\\2\r\3\2\r\22\3\2\23\26\3\2\27\30\3\2\31\33\4\2JMOO\3\2\61\64\3"+
		"\2\67:\3\2\65\66\3\2;?\4\2//@C\3\2QU\2\u01a5\2_\3\2\2\2\4f\3\2\2\2\6h"+
		"\3\2\2\2\bs\3\2\2\2\n~\3\2\2\2\f\u008e\3\2\2\2\16\u009a\3\2\2\2\20\u009c"+
		"\3\2\2\2\22\u009f\3\2\2\2\24\u00a4\3\2\2\2\26\u00a8\3\2\2\2\30\u00ad\3"+
		"\2\2\2\32\u00b6\3\2\2\2\34\u00c0\3\2\2\2\36\u00c5\3\2\2\2 \u00ca\3\2\2"+
		"\2\"\u00cd\3\2\2\2$\u00d6\3\2\2\2&\u00dd\3\2\2\2(\u00e4\3\2\2\2*\u00e6"+
		"\3\2\2\2,\u00fe\3\2\2\2.\u010a\3\2\2\2\60\u0120\3\2\2\2\62\u012b\3\2\2"+
		"\2\64\u012d\3\2\2\2\66\u0136\3\2\2\28\u0150\3\2\2\2:\u0156\3\2\2\2<\u015a"+
		"\3\2\2\2>\u015e\3\2\2\2@\u0160\3\2\2\2B\u0162\3\2\2\2D\u0164\3\2\2\2F"+
		"\u0166\3\2\2\2H\u0168\3\2\2\2J\u016a\3\2\2\2L\u016e\3\2\2\2N\u0170\3\2"+
		"\2\2P\u017f\3\2\2\2R\u0183\3\2\2\2T\u0185\3\2\2\2V\u018a\3\2\2\2X\u0191"+
		"\3\2\2\2Z\u0198\3\2\2\2\\\u019c\3\2\2\2^`\5\4\3\2_^\3\2\2\2`a\3\2\2\2"+
		"a_\3\2\2\2ab\3\2\2\2b\3\3\2\2\2cg\5\6\4\2dg\5\b\5\2eg\5\n\6\2fc\3\2\2"+
		"\2fd\3\2\2\2fe\3\2\2\2g\5\3\2\2\2hi\7D\2\2im\7Y\2\2jl\5\64\33\2kj\3\2"+
		"\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2np\3\2\2\2om\3\2\2\2pq\5\f\7\2qr\7E"+
		"\2\2r\7\3\2\2\2st\7F\2\2tx\7Y\2\2uw\5\64\33\2vu\3\2\2\2wz\3\2\2\2xv\3"+
		"\2\2\2xy\3\2\2\2y{\3\2\2\2zx\3\2\2\2{|\5\f\7\2|}\7G\2\2}\t\3\2\2\2~\177"+
		"\7H\2\2\177\u0080\7Y\2\2\u0080\u0081\7\13\2\2\u0081\u0085\58\35\2\u0082"+
		"\u0084\5\64\33\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3"+
		"\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087\u0085\3\2\2\2\u0088"+
		"\u0089\5\f\7\2\u0089\u008a\7I\2\2\u008a\13\3\2\2\2\u008b\u008d\5\16\b"+
		"\2\u008c\u008b\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f"+
		"\3\2\2\2\u008f\r\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u009b\5\22\n\2\u0092"+
		"\u009b\5\24\13\2\u0093\u009b\5\26\f\2\u0094\u009b\5\30\r\2\u0095\u009b"+
		"\5\32\16\2\u0096\u009b\5\"\22\2\u0097\u009b\5&\24\2\u0098\u009b\5(\25"+
		"\2\u0099\u009b\5\20\t\2\u009a\u0091\3\2\2\2\u009a\u0092\3\2\2\2\u009a"+
		"\u0093\3\2\2\2\u009a\u0094\3\2\2\2\u009a\u0095\3\2\2\2\u009a\u0096\3\2"+
		"\2\2\u009a\u0097\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u0099\3\2\2\2\u009b"+
		"\17\3\2\2\2\u009c\u009d\5*\26\2\u009d\u009e\7\n\2\2\u009e\21\3\2\2\2\u009f"+
		"\u00a0\5\62\32\2\u00a0\u00a1\7\35\2\2\u00a1\u00a2\5.\30\2\u00a2\u00a3"+
		"\7\n\2\2\u00a3\23\3\2\2\2\u00a4\u00a5\5\34\17\2\u00a5\u00a6\7*\2\2\u00a6"+
		"\u00a7\7\n\2\2\u00a7\25\3\2\2\2\u00a8\u00a9\5\34\17\2\u00a9\u00aa\5 \21"+
		"\2\u00aa\u00ab\7*\2\2\u00ab\u00ac\7\n\2\2\u00ac\27\3\2\2\2\u00ad\u00af"+
		"\5\34\17\2\u00ae\u00b0\5\36\20\2\u00af\u00ae\3\2\2\2\u00b0\u00b1\3\2\2"+
		"\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4"+
		"\7*\2\2\u00b4\u00b5\7\n\2\2\u00b5\31\3\2\2\2\u00b6\u00b8\5\34\17\2\u00b7"+
		"\u00b9\5\36\20\2\u00b8\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00b8\3"+
		"\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\5 \21\2\u00bd"+
		"\u00be\7*\2\2\u00be\u00bf\7\n\2\2\u00bf\33\3\2\2\2\u00c0\u00c1\7&\2\2"+
		"\u00c1\u00c2\5.\30\2\u00c2\u00c3\7\'\2\2\u00c3\u00c4\5\f\7\2\u00c4\35"+
		"\3\2\2\2\u00c5\u00c6\7)\2\2\u00c6\u00c7\5.\30\2\u00c7\u00c8\7\'\2\2\u00c8"+
		"\u00c9\5\f\7\2\u00c9\37\3\2\2\2\u00ca\u00cb\7(\2\2\u00cb\u00cc\5\f\7\2"+
		"\u00cc!\3\2\2\2\u00cd\u00ce\7\37\2\2\u00ce\u00cf\7Y\2\2\u00cf\u00d0\7"+
		"\35\2\2\u00d0\u00d1\5$\23\2\u00d1\u00d2\7 \2\2\u00d2\u00d3\5\f\7\2\u00d3"+
		"\u00d4\7!\2\2\u00d4\u00d5\7\n\2\2\u00d5#\3\2\2\2\u00d6\u00d7\5.\30\2\u00d7"+
		"\u00d8\7\"\2\2\u00d8\u00db\5.\30\2\u00d9\u00da\7#\2\2\u00da\u00dc\5.\30"+
		"\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc%\3\2\2\2\u00dd\u00de"+
		"\7$\2\2\u00de\u00df\5.\30\2\u00df\u00e0\7 \2\2\u00e0\u00e1\5\f\7\2\u00e1"+
		"\u00e2\7%\2\2\u00e2\u00e3\7\n\2\2\u00e3\'\3\2\2\2\u00e4\u00e5\7+\2\2\u00e5"+
		")\3\2\2\2\u00e6\u00e7\7Y\2\2\u00e7\u00f0\7\5\2\2\u00e8\u00ed\5,\27\2\u00e9"+
		"\u00ea\7\t\2\2\u00ea\u00ec\5,\27\2\u00eb\u00e9\3\2\2\2\u00ec\u00ef\3\2"+
		"\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef"+
		"\u00ed\3\2\2\2\u00f0\u00e8\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f2\3\2"+
		"\2\2\u00f2\u00f3\7\6\2\2\u00f3+\3\2\2\2\u00f4\u00ff\5.\30\2\u00f5\u00f6"+
		"\7Y\2\2\u00f6\u00f7\7\35\2\2\u00f7\u00ff\5.\30\2\u00f8\u00fa\7\34\2\2"+
		"\u00f9\u00f8\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fc"+
		"\7Y\2\2\u00fc\u00fd\7\36\2\2\u00fd\u00ff\7Y\2\2\u00fe\u00f4\3\2\2\2\u00fe"+
		"\u00f5\3\2\2\2\u00fe\u00f9\3\2\2\2\u00ff-\3\2\2\2\u0100\u0101\b\30\1\2"+
		"\u0101\u010b\5\60\31\2\u0102\u0103\7\34\2\2\u0103\u010b\5.\30\t\u0104"+
		"\u0105\7\16\2\2\u0105\u010b\5.\30\b\u0106\u0107\7\5\2\2\u0107\u0108\5"+
		".\30\2\u0108\u0109\7\6\2\2\u0109\u010b\3\2\2\2\u010a\u0100\3\2\2\2\u010a"+
		"\u0102\3\2\2\2\u010a\u0104\3\2\2\2\u010a\u0106\3\2\2\2\u010b\u011a\3\2"+
		"\2\2\u010c\u010d\f\7\2\2\u010d\u010e\t\2\2\2\u010e\u0119\5.\30\b\u010f"+
		"\u0110\f\6\2\2\u0110\u0111\t\3\2\2\u0111\u0119\5.\30\7\u0112\u0113\f\5"+
		"\2\2\u0113\u0114\t\4\2\2\u0114\u0119\5.\30\6\u0115\u0116\f\4\2\2\u0116"+
		"\u0117\t\5\2\2\u0117\u0119\5.\30\5\u0118\u010c\3\2\2\2\u0118\u010f\3\2"+
		"\2\2\u0118\u0112\3\2\2\2\u0118\u0115\3\2\2\2\u0119\u011c\3\2\2\2\u011a"+
		"\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b/\3\2\2\2\u011c\u011a\3\2\2\2"+
		"\u011d\u0121\5P)\2\u011e\u0121\5\62\32\2\u011f\u0121\5*\26\2\u0120\u011d"+
		"\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u011f\3\2\2\2\u0121\61\3\2\2\2\u0122"+
		"\u012c\7Y\2\2\u0123\u0124\7Y\2\2\u0124\u0125\7\3\2\2\u0125\u0126\5.\30"+
		"\2\u0126\u0127\7\4\2\2\u0127\u012c\3\2\2\2\u0128\u0129\7Y\2\2\u0129\u012a"+
		"\7\f\2\2\u012a\u012c\7Y\2\2\u012b\u0122\3\2\2\2\u012b\u0123\3\2\2\2\u012b"+
		"\u0128\3\2\2\2\u012c\63\3\2\2\2\u012d\u0131\t\6\2\2\u012e\u0130\5\66\34"+
		"\2\u012f\u012e\3\2\2\2\u0130\u0133\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0132"+
		"\3\2\2\2\u0132\u0134\3\2\2\2\u0133\u0131\3\2\2\2\u0134\u0135\7N\2\2\u0135"+
		"\65\3\2\2\2\u0136\u013b\7Y\2\2\u0137\u0138\7\t\2\2\u0138\u013a\7Y\2\2"+
		"\u0139\u0137\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c"+
		"\3\2\2\2\u013c\u013e\3\2\2\2\u013d\u013b\3\2\2\2\u013e\u013f\7\13\2\2"+
		"\u013f\u0142\58\35\2\u0140\u0141\7\35\2\2\u0141\u0143\5L\'\2\u0142\u0140"+
		"\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0145\7\n\2\2\u0145"+
		"\67\3\2\2\2\u0146\u0151\5:\36\2\u0147\u0148\7,\2\2\u0148\u0149\7\3\2\2"+
		"\u0149\u014a\5Z.\2\u014a\u014b\7-\2\2\u014b\u014c\5Z.\2\u014c\u014d\7"+
		"\4\2\2\u014d\u014e\7.\2\2\u014e\u014f\5:\36\2\u014f\u0151\3\2\2\2\u0150"+
		"\u0146\3\2\2\2\u0150\u0147\3\2\2\2\u01519\3\2\2\2\u0152\u0157\5<\37\2"+
		"\u0153\u0157\5F$\2\u0154\u0157\5H%\2\u0155\u0157\5J&\2\u0156\u0152\3\2"+
		"\2\2\u0156\u0153\3\2\2\2\u0156\u0154\3\2\2\2\u0156\u0155\3\2\2\2\u0157"+
		";\3\2\2\2\u0158\u015b\5> \2\u0159\u015b\5D#\2\u015a\u0158\3\2\2\2\u015a"+
		"\u0159\3\2\2\2\u015b=\3\2\2\2\u015c\u015f\5@!\2\u015d\u015f\5B\"\2\u015e"+
		"\u015c\3\2\2\2\u015e\u015d\3\2\2\2\u015f?\3\2\2\2\u0160\u0161\t\7\2\2"+
		"\u0161A\3\2\2\2\u0162\u0163\t\b\2\2\u0163C\3\2\2\2\u0164\u0165\t\t\2\2"+
		"\u0165E\3\2\2\2\u0166\u0167\t\n\2\2\u0167G\3\2\2\2\u0168\u0169\t\13\2"+
		"\2\u0169I\3\2\2\2\u016a\u016b\7\60\2\2\u016bK\3\2\2\2\u016c\u016f\5P)"+
		"\2\u016d\u016f\5N(\2\u016e\u016c\3\2\2\2\u016e\u016d\3\2\2\2\u016fM\3"+
		"\2\2\2\u0170\u0176\7\3\2\2\u0171\u0172\5P)\2\u0172\u0173\7\t\2\2\u0173"+
		"\u0175\3\2\2\2\u0174\u0171\3\2\2\2\u0175\u0178\3\2\2\2\u0176\u0174\3\2"+
		"\2\2\u0176\u0177\3\2\2\2\u0177\u0179\3\2\2\2\u0178\u0176\3\2\2\2\u0179"+
		"\u017a\5P)\2\u017a\u017b\7\4\2\2\u017bO\3\2\2\2\u017c\u0180\5R*\2\u017d"+
		"\u0180\5\\/\2\u017e\u0180\7P\2\2\u017f\u017c\3\2\2\2\u017f\u017d\3\2\2"+
		"\2\u017f\u017e\3\2\2\2\u0180Q\3\2\2\2\u0181\u0184\5Z.\2\u0182\u0184\5"+
		"T+\2\u0183\u0181\3\2\2\2\u0183\u0182\3\2\2\2\u0184S\3\2\2\2\u0185\u0187"+
		"\5V,\2\u0186\u0188\5X-\2\u0187\u0186\3\2\2\2\u0187\u0188\3\2\2\2\u0188"+
		"U\3\2\2\2\u0189\u018b\7\16\2\2\u018a\u0189\3\2\2\2\u018a\u018b\3\2\2\2"+
		"\u018b\u018c\3\2\2\2\u018c\u018f\7S\2\2\u018d\u018e\7\f\2\2\u018e\u0190"+
		"\7S\2\2\u018f\u018d\3\2\2\2\u018f\u0190\3\2\2\2\u0190W\3\2\2\2\u0191\u0193"+
		"\7V\2\2\u0192\u0194\7W\2\2\u0193\u0192\3\2\2\2\u0193\u0194\3\2\2\2\u0194"+
		"\u0195\3\2\2\2\u0195\u0196\7S\2\2\u0196Y\3\2\2\2\u0197\u0199\7\16\2\2"+
		"\u0198\u0197\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u019a\3\2\2\2\u019a\u019b"+
		"\t\f\2\2\u019b[\3\2\2\2\u019c\u019d\7X\2\2\u019d]\3\2\2\2%afmx\u0085\u008e"+
		"\u009a\u00b1\u00ba\u00db\u00ed\u00f0\u00f9\u00fe\u010a\u0118\u011a\u0120"+
		"\u012b\u0131\u013b\u0142\u0150\u0156\u015a\u015e\u016e\u0176\u017f\u0183"+
		"\u0187\u018a\u018f\u0193\u0198";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}