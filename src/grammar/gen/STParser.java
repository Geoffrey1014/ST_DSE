// Generated from /Users/heweigang/MY_project/ST_DSE/src/grammar/STParser.g4 by ANTLR 4.8
package grammar.gen;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

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
		RES_EXIT=41, RES_ARRAY=42, FromTo=43, RES_OF=44, RES_BOOL=45, RES_SINT=46, 
		RES_INT=47, RES_DINT=48, RES_LINT=49, REAL=50, LREAL=51, USINT=52, UINT=53, 
		UDINT=54, ULINT=55, DATE=56, TIME_OF_DAY=57, TOD=58, DATE_AND_TIME=59, 
		DT=60, BYTE=61, WORD=62, DWORD=63, LWORD=64, RES_PROGRAM=65, RES_END_PROGRAM=66, 
		RES_FUNCTION_BLOCK=67, RES_END_FUNCTION_BLOCK=68, RES_FUNCTION=69, RES_END_FUNCTION=70, 
		RES_VAR=71, RES_VAR_INPUT=72, RES_VAR_OUTPUT=73, RES_VAR_INPUT_OUTPUT=74, 
		RES_END_VAR=75, RES_VAR_TEMP=76, BOOL=77, Binary_literal=78, Octal_literal=79, 
		Decimal_literal=80, Pure_decimal_digits=81, Hexadecimal_literal=82, Floating_point_literal=83, 
		Static_string_literal=84, ID=85, Block_comment=86, WS=87;
	public static final int
		RULE_pous = 0, RULE_pou = 1, RULE_program = 2, RULE_function_block = 3, 
		RULE_function = 4, RULE_stat_list = 5, RULE_stat = 6, RULE_assign_stat = 7, 
		RULE_if_stat = 8, RULE_if_else_stat = 9, RULE_if_elsif_stat = 10, RULE_if_elsif_else_stat = 11, 
		RULE_if_stmt = 12, RULE_elsif_stmt = 13, RULE_else_stmt = 14, RULE_for_stat = 15, 
		RULE_for_range = 16, RULE_while_stat = 17, RULE_invoc_stat = 18, RULE_param_assignment = 19, 
		RULE_expression = 20, RULE_primary_expression = 21, RULE_location = 22, 
		RULE_var_block = 23, RULE_variable_declaration = 24, RULE_type_rule = 25, 
		RULE_array_type = 26, RULE_range = 27, RULE_elementary_type_name = 28, 
		RULE_numeric_type_name = 29, RULE_integer_type_name = 30, RULE_signed_integer_type_name = 31, 
		RULE_unsigned_integer_type_name = 32, RULE_real_type_name = 33, RULE_date_type_name = 34, 
		RULE_bit_string_type_name = 35, RULE_variable_initializer = 36, RULE_array_initialization = 37, 
		RULE_constant = 38, RULE_numeric_literal = 39, RULE_integer_literal = 40, 
		RULE_string_literal = 41;
	private static String[] makeRuleNames() {
		return new String[] {
			"pous", "pou", "program", "function_block", "function", "stat_list", 
			"stat", "assign_stat", "if_stat", "if_else_stat", "if_elsif_stat", "if_elsif_else_stat", 
			"if_stmt", "elsif_stmt", "else_stmt", "for_stat", "for_range", "while_stat", 
			"invoc_stat", "param_assignment", "expression", "primary_expression", 
			"location", "var_block", "variable_declaration", "type_rule", "array_type", 
			"range", "elementary_type_name", "numeric_type_name", "integer_type_name", 
			"signed_integer_type_name", "unsigned_integer_type_name", "real_type_name", 
			"date_type_name", "bit_string_type_name", "variable_initializer", "array_initialization", 
			"constant", "numeric_literal", "integer_literal", "string_literal"
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
			"'..'", "'OF'", "'BOOL'", "'SINT'", "'INT'", "'DINT'", "'LINT'", "'REAL'", 
			"'LREAL'", "'USINT'", "'UINT'", "'UDINT'", "'ULINT'", "'DATE'", "'TIME_OF_DAY'", 
			"'TOD'", "'DATE_AND_TIME'", "'DT'", "'BYTE'", "'WORD'", "'DWORD'", "'LWORD'", 
			"'PROGRAM'", "'END_PROGRAM'", "'FUNCTION_BLOCK'", "'END_FUNCTION_BLOCK'", 
			"'FUNCTION'", "'END_FUNCTION'", "'VAR'", "'VAR_INPUT'", "'VAR_OUTPUT'", 
			"'VAR_INPUT_OUTPUT'", "'END_VAR'", "'VAR_TEMP'"
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
			"RES_ARRAY", "FromTo", "RES_OF", "RES_BOOL", "RES_SINT", "RES_INT", "RES_DINT", 
			"RES_LINT", "REAL", "LREAL", "USINT", "UINT", "UDINT", "ULINT", "DATE", 
			"TIME_OF_DAY", "TOD", "DATE_AND_TIME", "DT", "BYTE", "WORD", "DWORD", 
			"LWORD", "RES_PROGRAM", "RES_END_PROGRAM", "RES_FUNCTION_BLOCK", "RES_END_FUNCTION_BLOCK", 
			"RES_FUNCTION", "RES_END_FUNCTION", "RES_VAR", "RES_VAR_INPUT", "RES_VAR_OUTPUT", 
			"RES_VAR_INPUT_OUTPUT", "RES_END_VAR", "RES_VAR_TEMP", "BOOL", "Binary_literal", 
			"Octal_literal", "Decimal_literal", "Pure_decimal_digits", "Hexadecimal_literal", 
			"Floating_point_literal", "Static_string_literal", "ID", "Block_comment", 
			"WS"
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
			setState(85); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(84);
				pou();
				}
				}
				setState(87); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (RES_PROGRAM - 65)) | (1L << (RES_FUNCTION_BLOCK - 65)) | (1L << (RES_FUNCTION - 65)))) != 0) );
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
			setState(92);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RES_PROGRAM:
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				program();
				}
				break;
			case RES_FUNCTION_BLOCK:
				enterOuterAlt(_localctx, 2);
				{
				setState(90);
				function_block();
				}
				break;
			case RES_FUNCTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(91);
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
			setState(94);
			match(RES_PROGRAM);
			setState(95);
			((ProgramContext)_localctx).name = match(ID);
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (RES_VAR - 71)) | (1L << (RES_VAR_INPUT - 71)) | (1L << (RES_VAR_OUTPUT - 71)) | (1L << (RES_VAR_INPUT_OUTPUT - 71)) | (1L << (RES_VAR_TEMP - 71)))) != 0)) {
				{
				{
				setState(96);
				((ProgramContext)_localctx).var_block = var_block();
				((ProgramContext)_localctx).var_blocks.add(((ProgramContext)_localctx).var_block);
				}
				}
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(102);
			stat_list();
			setState(103);
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
			setState(105);
			match(RES_FUNCTION_BLOCK);
			setState(106);
			match(ID);
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (RES_VAR - 71)) | (1L << (RES_VAR_INPUT - 71)) | (1L << (RES_VAR_OUTPUT - 71)) | (1L << (RES_VAR_INPUT_OUTPUT - 71)) | (1L << (RES_VAR_TEMP - 71)))) != 0)) {
				{
				{
				setState(107);
				((Function_blockContext)_localctx).var_block = var_block();
				((Function_blockContext)_localctx).var_blocks.add(((Function_blockContext)_localctx).var_block);
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(113);
			stat_list();
			setState(114);
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
			setState(116);
			match(RES_FUNCTION);
			setState(117);
			match(ID);
			setState(118);
			match(COLON);
			setState(119);
			type_rule();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (RES_VAR - 71)) | (1L << (RES_VAR_INPUT - 71)) | (1L << (RES_VAR_OUTPUT - 71)) | (1L << (RES_VAR_INPUT_OUTPUT - 71)) | (1L << (RES_VAR_TEMP - 71)))) != 0)) {
				{
				{
				setState(120);
				((FunctionContext)_localctx).var_block = var_block();
				((FunctionContext)_localctx).var_blocks.add(((FunctionContext)_localctx).var_block);
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(126);
			stat_list();
			setState(127);
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
			setState(129);
			stat();
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 29)) & ~0x3f) == 0 && ((1L << (_la - 29)) & ((1L << (RES_FOR - 29)) | (1L << (RES_WHILE - 29)) | (1L << (RES_IF - 29)) | (1L << (ID - 29)))) != 0)) {
				{
				{
				setState(130);
				stat();
				}
				}
				setState(135);
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
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				assign_stat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				if_stat();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(138);
				if_else_stat();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(139);
				if_elsif_stat();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(140);
				if_elsif_else_stat();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(141);
				for_stat();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(142);
				while_stat();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(143);
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
		enterRule(_localctx, 14, RULE_assign_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			location();
			setState(147);
			match(AS_OP);
			setState(148);
			expression(0);
			setState(149);
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
		enterRule(_localctx, 16, RULE_if_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			if_stmt();
			setState(152);
			match(RES_END_IF);
			setState(153);
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
		enterRule(_localctx, 18, RULE_if_else_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			if_stmt();
			setState(156);
			else_stmt();
			setState(157);
			match(RES_END_IF);
			setState(158);
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
		enterRule(_localctx, 20, RULE_if_elsif_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			if_stmt();
			setState(162); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(161);
				elsif_stmt();
				}
				}
				setState(164); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RES_ELSIF );
			setState(166);
			match(RES_END_IF);
			setState(167);
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
		enterRule(_localctx, 22, RULE_if_elsif_else_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			if_stmt();
			setState(171); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(170);
				elsif_stmt();
				}
				}
				setState(173); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RES_ELSIF );
			setState(175);
			else_stmt();
			setState(176);
			match(RES_END_IF);
			setState(177);
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
		enterRule(_localctx, 24, RULE_if_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			match(RES_IF);
			setState(180);
			expression(0);
			setState(181);
			match(RES_THEN);
			setState(182);
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
		enterRule(_localctx, 26, RULE_elsif_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(RES_ELSIF);
			setState(185);
			expression(0);
			setState(186);
			match(RES_THEN);
			setState(187);
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
		enterRule(_localctx, 28, RULE_else_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(RES_ELSE);
			setState(190);
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
		enterRule(_localctx, 30, RULE_for_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(RES_FOR);
			setState(193);
			((For_statContext)_localctx).control_variable = match(ID);
			setState(194);
			match(AS_OP);
			setState(195);
			for_range();
			setState(196);
			match(RES_DO);
			setState(197);
			stat_list();
			setState(198);
			match(RES_END_FOR);
			setState(199);
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
		enterRule(_localctx, 32, RULE_for_range);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			expression(0);
			setState(202);
			match(RES_TO);
			setState(203);
			expression(0);
			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RES_BY) {
				{
				setState(204);
				match(RES_BY);
				setState(205);
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
		enterRule(_localctx, 34, RULE_while_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(RES_WHILE);
			setState(209);
			expression(0);
			setState(210);
			match(RES_DO);
			setState(211);
			stat_list();
			setState(212);
			match(RES_END_WHILE);
			setState(213);
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

	public static class Invoc_statContext extends ParserRuleContext {
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
		enterRule(_localctx, 36, RULE_invoc_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			((Invoc_statContext)_localctx).fb_name = match(ID);
			setState(216);
			match(L_PAREN);
			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L_PAREN) | (1L << SUB_OP) | (1L << NOT_OP))) != 0) || ((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (BOOL - 77)) | (1L << (Binary_literal - 77)) | (1L << (Octal_literal - 77)) | (1L << (Decimal_literal - 77)) | (1L << (Pure_decimal_digits - 77)) | (1L << (Hexadecimal_literal - 77)) | (1L << (Floating_point_literal - 77)) | (1L << (Static_string_literal - 77)) | (1L << (ID - 77)))) != 0)) {
				{
				setState(217);
				param_assignment();
				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(218);
					match(COMMA);
					setState(219);
					param_assignment();
					}
					}
					setState(224);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(227);
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
		enterRule(_localctx, 38, RULE_param_assignment);
		int _la;
		try {
			setState(239);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new ExternArgContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(229);
				expression(0);
				}
				break;
			case 2:
				_localctx = new AssignParamContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(230);
				match(ID);
				setState(231);
				match(AS_OP);
				setState(232);
				expression(0);
				}
				break;
			case 3:
				_localctx = new AssignOutputContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT_OP) {
					{
					setState(233);
					match(NOT_OP);
					}
				}

				setState(236);
				match(ID);
				setState(237);
				match(RT_AS_OP);
				setState(238);
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
		public TerminalNode EQ_OP() { return getToken(STParser.EQ_OP, 0); }
		public TerminalNode NEQ_OP() { return getToken(STParser.NEQ_OP, 0); }
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
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				_localctx = new PrimaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(242);
				primary_expression();
				}
				break;
			case 2:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(243);
				match(NOT_OP);
				setState(244);
				expression(7);
				}
				break;
			case 3:
				{
				_localctx = new NegateExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(245);
				match(SUB_OP);
				setState(246);
				expression(6);
				}
				break;
			case 4:
				{
				_localctx = new ParenExperContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(247);
				match(L_PAREN);
				setState(248);
				expression(0);
				setState(249);
				match(R_PAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(267);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(265);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new ArithExprContext(new ExpressionContext(_parentctx, _parentState));
						((ArithExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(253);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(254);
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
						setState(255);
						((ArithExprContext)_localctx).right = expression(6);
						}
						break;
					case 2:
						{
						_localctx = new ComparisonContext(new ExpressionContext(_parentctx, _parentState));
						((ComparisonContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(256);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(257);
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
						setState(258);
						((ComparisonContext)_localctx).right = expression(5);
						}
						break;
					case 3:
						{
						_localctx = new ComparisonContext(new ExpressionContext(_parentctx, _parentState));
						((ComparisonContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(259);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(260);
						((ComparisonContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQ_OP || _la==NEQ_OP) ) {
							((ComparisonContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(261);
						((ComparisonContext)_localctx).right = expression(4);
						}
						break;
					case 4:
						{
						_localctx = new LogicContext(new ExpressionContext(_parentctx, _parentState));
						((LogicContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(262);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(263);
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
						setState(264);
						((LogicContext)_localctx).right = expression(3);
						}
						break;
					}
					} 
				}
				setState(269);
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
		public Invoc_statContext invoc_stat() {
			return getRuleContext(Invoc_statContext.class,0);
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
		enterRule(_localctx, 42, RULE_primary_expression);
		try {
			setState(273);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				constant();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(271);
				location();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(272);
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
		public List<TerminalNode> DOT() { return getTokens(STParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(STParser.DOT, i);
		}
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
		enterRule(_localctx, 44, RULE_location);
		try {
			int _alt;
			setState(288);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				_localctx = new VarLocationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(275);
				match(ID);
				}
				break;
			case 2:
				_localctx = new ArrayLocationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(276);
				match(ID);
				setState(277);
				match(L_SQUARE);
				setState(278);
				expression(0);
				setState(279);
				match(R_SQUARE);
				}
				break;
			case 3:
				_localctx = new FbLcationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(281);
				match(ID);
				setState(284); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(282);
						match(DOT);
						setState(283);
						match(ID);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(286); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
		enterRule(_localctx, 46, RULE_var_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			((Var_blockContext)_localctx).var_acc_type = _input.LT(1);
			_la = _input.LA(1);
			if ( !(((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (RES_VAR - 71)) | (1L << (RES_VAR_INPUT - 71)) | (1L << (RES_VAR_OUTPUT - 71)) | (1L << (RES_VAR_INPUT_OUTPUT - 71)) | (1L << (RES_VAR_TEMP - 71)))) != 0)) ) {
				((Var_blockContext)_localctx).var_acc_type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(294);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(291);
				variable_declaration();
				}
				}
				setState(296);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(297);
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
		public Type_ruleContext type;
		public TerminalNode COLON() { return getToken(STParser.COLON, 0); }
		public TerminalNode SEMI_COL() { return getToken(STParser.SEMI_COL, 0); }
		public List<TerminalNode> ID() { return getTokens(STParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(STParser.ID, i);
		}
		public Type_ruleContext type_rule() {
			return getRuleContext(Type_ruleContext.class,0);
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
		enterRule(_localctx, 48, RULE_variable_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			((Variable_declarationContext)_localctx).ID = match(ID);
			((Variable_declarationContext)_localctx).names.add(((Variable_declarationContext)_localctx).ID);
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(300);
				match(COMMA);
				setState(301);
				((Variable_declarationContext)_localctx).ID = match(ID);
				((Variable_declarationContext)_localctx).names.add(((Variable_declarationContext)_localctx).ID);
				}
				}
				setState(306);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(307);
			match(COLON);
			setState(308);
			((Variable_declarationContext)_localctx).type = type_rule();
			setState(311);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS_OP) {
				{
				setState(309);
				match(AS_OP);
				setState(310);
				variable_initializer();
				}
			}

			setState(313);
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
		public Array_typeContext array_type() {
			return getRuleContext(Array_typeContext.class,0);
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
		enterRule(_localctx, 50, RULE_type_rule);
		try {
			setState(317);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RES_BOOL:
			case RES_SINT:
			case RES_INT:
			case RES_DINT:
			case RES_LINT:
			case REAL:
			case LREAL:
			case USINT:
			case UINT:
			case UDINT:
			case ULINT:
			case DATE:
			case TIME_OF_DAY:
			case TOD:
			case DATE_AND_TIME:
			case DT:
			case BYTE:
			case WORD:
			case DWORD:
			case LWORD:
				_localctx = new SimpleTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(315);
				elementary_type_name();
				}
				break;
			case RES_ARRAY:
				_localctx = new ArrayTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(316);
				array_type();
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

	public static class Array_typeContext extends ParserRuleContext {
		public TerminalNode RES_ARRAY() { return getToken(STParser.RES_ARRAY, 0); }
		public TerminalNode L_SQUARE() { return getToken(STParser.L_SQUARE, 0); }
		public RangeContext range() {
			return getRuleContext(RangeContext.class,0);
		}
		public TerminalNode R_SQUARE() { return getToken(STParser.R_SQUARE, 0); }
		public TerminalNode RES_OF() { return getToken(STParser.RES_OF, 0); }
		public Elementary_type_nameContext elementary_type_name() {
			return getRuleContext(Elementary_type_nameContext.class,0);
		}
		public Array_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterArray_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitArray_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitArray_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Array_typeContext array_type() throws RecognitionException {
		Array_typeContext _localctx = new Array_typeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_array_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(319);
			match(RES_ARRAY);
			setState(320);
			match(L_SQUARE);
			setState(321);
			range();
			setState(322);
			match(R_SQUARE);
			setState(323);
			match(RES_OF);
			setState(324);
			elementary_type_name();
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

	public static class RangeContext extends ParserRuleContext {
		public Integer_literalContext lbound;
		public Integer_literalContext ubound;
		public TerminalNode FromTo() { return getToken(STParser.FromTo, 0); }
		public List<Integer_literalContext> integer_literal() {
			return getRuleContexts(Integer_literalContext.class);
		}
		public Integer_literalContext integer_literal(int i) {
			return getRuleContext(Integer_literalContext.class,i);
		}
		public RangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitRange(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangeContext range() throws RecognitionException {
		RangeContext _localctx = new RangeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			((RangeContext)_localctx).lbound = integer_literal();
			setState(327);
			match(FromTo);
			setState(328);
			((RangeContext)_localctx).ubound = integer_literal();
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
			setState(333);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RES_SINT:
			case RES_INT:
			case RES_DINT:
			case RES_LINT:
			case REAL:
			case LREAL:
			case USINT:
			case UINT:
			case UDINT:
			case ULINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(330);
				numeric_type_name();
				}
				break;
			case DATE:
			case TIME_OF_DAY:
			case TOD:
			case DATE_AND_TIME:
			case DT:
				enterOuterAlt(_localctx, 2);
				{
				setState(331);
				date_type_name();
				}
				break;
			case RES_BOOL:
			case BYTE:
			case WORD:
			case DWORD:
			case LWORD:
				enterOuterAlt(_localctx, 3);
				{
				setState(332);
				bit_string_type_name();
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
			setState(337);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RES_SINT:
			case RES_INT:
			case RES_DINT:
			case RES_LINT:
			case USINT:
			case UINT:
			case UDINT:
			case ULINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(335);
				integer_type_name();
				}
				break;
			case REAL:
			case LREAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(336);
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
			setState(341);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RES_SINT:
			case RES_INT:
			case RES_DINT:
			case RES_LINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(339);
				signed_integer_type_name();
				}
				break;
			case USINT:
			case UINT:
			case UDINT:
			case ULINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(340);
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
			setState(343);
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
		public TerminalNode USINT() { return getToken(STParser.USINT, 0); }
		public TerminalNode UINT() { return getToken(STParser.UINT, 0); }
		public TerminalNode UDINT() { return getToken(STParser.UDINT, 0); }
		public TerminalNode ULINT() { return getToken(STParser.ULINT, 0); }
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
			setState(345);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << USINT) | (1L << UINT) | (1L << UDINT) | (1L << ULINT))) != 0)) ) {
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
		public TerminalNode REAL() { return getToken(STParser.REAL, 0); }
		public TerminalNode LREAL() { return getToken(STParser.LREAL, 0); }
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
			setState(347);
			_la = _input.LA(1);
			if ( !(_la==REAL || _la==LREAL) ) {
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
		public TerminalNode DATE() { return getToken(STParser.DATE, 0); }
		public TerminalNode TIME_OF_DAY() { return getToken(STParser.TIME_OF_DAY, 0); }
		public TerminalNode TOD() { return getToken(STParser.TOD, 0); }
		public TerminalNode DATE_AND_TIME() { return getToken(STParser.DATE_AND_TIME, 0); }
		public TerminalNode DT() { return getToken(STParser.DT, 0); }
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
			setState(349);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DATE) | (1L << TIME_OF_DAY) | (1L << TOD) | (1L << DATE_AND_TIME) | (1L << DT))) != 0)) ) {
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
		public TerminalNode BYTE() { return getToken(STParser.BYTE, 0); }
		public TerminalNode WORD() { return getToken(STParser.WORD, 0); }
		public TerminalNode DWORD() { return getToken(STParser.DWORD, 0); }
		public TerminalNode LWORD() { return getToken(STParser.LWORD, 0); }
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
			setState(351);
			_la = _input.LA(1);
			if ( !(((((_la - 45)) & ~0x3f) == 0 && ((1L << (_la - 45)) & ((1L << (RES_BOOL - 45)) | (1L << (BYTE - 45)) | (1L << (WORD - 45)) | (1L << (DWORD - 45)) | (1L << (LWORD - 45)))) != 0)) ) {
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
		enterRule(_localctx, 72, RULE_variable_initializer);
		try {
			setState(355);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUB_OP:
			case BOOL:
			case Binary_literal:
			case Octal_literal:
			case Decimal_literal:
			case Pure_decimal_digits:
			case Hexadecimal_literal:
			case Floating_point_literal:
			case Static_string_literal:
				enterOuterAlt(_localctx, 1);
				{
				setState(353);
				constant();
				}
				break;
			case L_SQUARE:
				enterOuterAlt(_localctx, 2);
				{
				setState(354);
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
		enterRule(_localctx, 74, RULE_array_initialization);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			match(L_SQUARE);
			setState(363);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(358);
					constant();
					setState(359);
					match(COMMA);
					}
					} 
				}
				setState(365);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			setState(366);
			constant();
			setState(367);
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
		enterRule(_localctx, 76, RULE_constant);
		try {
			setState(372);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SUB_OP:
			case Binary_literal:
			case Octal_literal:
			case Decimal_literal:
			case Pure_decimal_digits:
			case Hexadecimal_literal:
			case Floating_point_literal:
				enterOuterAlt(_localctx, 1);
				{
				setState(369);
				numeric_literal();
				}
				break;
			case Static_string_literal:
				enterOuterAlt(_localctx, 2);
				{
				setState(370);
				string_literal();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 3);
				{
				setState(371);
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
		public TerminalNode SUB_OP() { return getToken(STParser.SUB_OP, 0); }
		public TerminalNode Floating_point_literal() { return getToken(STParser.Floating_point_literal, 0); }
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
		enterRule(_localctx, 78, RULE_numeric_literal);
		int _la;
		try {
			setState(382);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(375);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SUB_OP) {
					{
					setState(374);
					match(SUB_OP);
					}
				}

				setState(377);
				integer_literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(379);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SUB_OP) {
					{
					setState(378);
					match(SUB_OP);
					}
				}

				setState(381);
				match(Floating_point_literal);
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

	public static class Integer_literalContext extends ParserRuleContext {
		public TerminalNode Binary_literal() { return getToken(STParser.Binary_literal, 0); }
		public TerminalNode Octal_literal() { return getToken(STParser.Octal_literal, 0); }
		public TerminalNode Decimal_literal() { return getToken(STParser.Decimal_literal, 0); }
		public TerminalNode Pure_decimal_digits() { return getToken(STParser.Pure_decimal_digits, 0); }
		public TerminalNode Hexadecimal_literal() { return getToken(STParser.Hexadecimal_literal, 0); }
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
		enterRule(_localctx, 80, RULE_integer_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			_la = _input.LA(1);
			if ( !(((((_la - 78)) & ~0x3f) == 0 && ((1L << (_la - 78)) & ((1L << (Binary_literal - 78)) | (1L << (Octal_literal - 78)) | (1L << (Decimal_literal - 78)) | (1L << (Pure_decimal_digits - 78)) | (1L << (Hexadecimal_literal - 78)))) != 0)) ) {
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
		enterRule(_localctx, 82, RULE_string_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
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
		case 20:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3Y\u0187\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\6\2X\n\2\r\2\16\2Y\3\3\3\3\3\3\5\3_\n\3\3\4\3\4\3\4\7\4d\n\4\f\4\16"+
		"\4g\13\4\3\4\3\4\3\4\3\5\3\5\3\5\7\5o\n\5\f\5\16\5r\13\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\7\6|\n\6\f\6\16\6\177\13\6\3\6\3\6\3\6\3\7\3\7\7\7"+
		"\u0086\n\7\f\7\16\7\u0089\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0093"+
		"\n\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f"+
		"\3\f\6\f\u00a5\n\f\r\f\16\f\u00a6\3\f\3\f\3\f\3\r\3\r\6\r\u00ae\n\r\r"+
		"\r\16\r\u00af\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\22\5\22\u00d1\n\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\24\7\24\u00df\n\24\f\24\16\24\u00e2\13\24"+
		"\5\24\u00e4\n\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\5\25\u00ed\n\25\3"+
		"\25\3\25\3\25\5\25\u00f2\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\5\26\u00fe\n\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\7\26\u010c\n\26\f\26\16\26\u010f\13\26\3\27\3\27\3\27"+
		"\5\27\u0114\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\6\30\u011f"+
		"\n\30\r\30\16\30\u0120\5\30\u0123\n\30\3\31\3\31\7\31\u0127\n\31\f\31"+
		"\16\31\u012a\13\31\3\31\3\31\3\32\3\32\3\32\7\32\u0131\n\32\f\32\16\32"+
		"\u0134\13\32\3\32\3\32\3\32\3\32\5\32\u013a\n\32\3\32\3\32\3\33\3\33\5"+
		"\33\u0140\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35"+
		"\3\36\3\36\3\36\5\36\u0150\n\36\3\37\3\37\5\37\u0154\n\37\3 \3 \5 \u0158"+
		"\n \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\5&\u0166\n&\3\'\3\'\3\'\3\'"+
		"\7\'\u016c\n\'\f\'\16\'\u016f\13\'\3\'\3\'\3\'\3(\3(\3(\5(\u0177\n(\3"+
		")\5)\u017a\n)\3)\3)\5)\u017e\n)\3)\5)\u0181\n)\3*\3*\3+\3+\3+\2\3*,\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJL"+
		"NPRT\2\r\3\2\r\22\3\2\23\26\3\2\27\30\3\2\31\33\4\2ILNN\3\2\60\63\3\2"+
		"\669\3\2\64\65\3\2:>\4\2//?B\3\2PT\2\u018d\2W\3\2\2\2\4^\3\2\2\2\6`\3"+
		"\2\2\2\bk\3\2\2\2\nv\3\2\2\2\f\u0083\3\2\2\2\16\u0092\3\2\2\2\20\u0094"+
		"\3\2\2\2\22\u0099\3\2\2\2\24\u009d\3\2\2\2\26\u00a2\3\2\2\2\30\u00ab\3"+
		"\2\2\2\32\u00b5\3\2\2\2\34\u00ba\3\2\2\2\36\u00bf\3\2\2\2 \u00c2\3\2\2"+
		"\2\"\u00cb\3\2\2\2$\u00d2\3\2\2\2&\u00d9\3\2\2\2(\u00f1\3\2\2\2*\u00fd"+
		"\3\2\2\2,\u0113\3\2\2\2.\u0122\3\2\2\2\60\u0124\3\2\2\2\62\u012d\3\2\2"+
		"\2\64\u013f\3\2\2\2\66\u0141\3\2\2\28\u0148\3\2\2\2:\u014f\3\2\2\2<\u0153"+
		"\3\2\2\2>\u0157\3\2\2\2@\u0159\3\2\2\2B\u015b\3\2\2\2D\u015d\3\2\2\2F"+
		"\u015f\3\2\2\2H\u0161\3\2\2\2J\u0165\3\2\2\2L\u0167\3\2\2\2N\u0176\3\2"+
		"\2\2P\u0180\3\2\2\2R\u0182\3\2\2\2T\u0184\3\2\2\2VX\5\4\3\2WV\3\2\2\2"+
		"XY\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\3\3\2\2\2[_\5\6\4\2\\_\5\b\5\2]_\5\n\6"+
		"\2^[\3\2\2\2^\\\3\2\2\2^]\3\2\2\2_\5\3\2\2\2`a\7C\2\2ae\7W\2\2bd\5\60"+
		"\31\2cb\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2fh\3\2\2\2ge\3\2\2\2hi\5"+
		"\f\7\2ij\7D\2\2j\7\3\2\2\2kl\7E\2\2lp\7W\2\2mo\5\60\31\2nm\3\2\2\2or\3"+
		"\2\2\2pn\3\2\2\2pq\3\2\2\2qs\3\2\2\2rp\3\2\2\2st\5\f\7\2tu\7F\2\2u\t\3"+
		"\2\2\2vw\7G\2\2wx\7W\2\2xy\7\13\2\2y}\5\64\33\2z|\5\60\31\2{z\3\2\2\2"+
		"|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\u0080\3\2\2\2\177}\3\2\2\2\u0080\u0081"+
		"\5\f\7\2\u0081\u0082\7H\2\2\u0082\13\3\2\2\2\u0083\u0087\5\16\b\2\u0084"+
		"\u0086\5\16\b\2\u0085\u0084\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3"+
		"\2\2\2\u0087\u0088\3\2\2\2\u0088\r\3\2\2\2\u0089\u0087\3\2\2\2\u008a\u0093"+
		"\5\20\t\2\u008b\u0093\5\22\n\2\u008c\u0093\5\24\13\2\u008d\u0093\5\26"+
		"\f\2\u008e\u0093\5\30\r\2\u008f\u0093\5 \21\2\u0090\u0093\5$\23\2\u0091"+
		"\u0093\5&\24\2\u0092\u008a\3\2\2\2\u0092\u008b\3\2\2\2\u0092\u008c\3\2"+
		"\2\2\u0092\u008d\3\2\2\2\u0092\u008e\3\2\2\2\u0092\u008f\3\2\2\2\u0092"+
		"\u0090\3\2\2\2\u0092\u0091\3\2\2\2\u0093\17\3\2\2\2\u0094\u0095\5.\30"+
		"\2\u0095\u0096\7\35\2\2\u0096\u0097\5*\26\2\u0097\u0098\7\n\2\2\u0098"+
		"\21\3\2\2\2\u0099\u009a\5\32\16\2\u009a\u009b\7*\2\2\u009b\u009c\7\n\2"+
		"\2\u009c\23\3\2\2\2\u009d\u009e\5\32\16\2\u009e\u009f\5\36\20\2\u009f"+
		"\u00a0\7*\2\2\u00a0\u00a1\7\n\2\2\u00a1\25\3\2\2\2\u00a2\u00a4\5\32\16"+
		"\2\u00a3\u00a5\5\34\17\2\u00a4\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6"+
		"\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\7*"+
		"\2\2\u00a9\u00aa\7\n\2\2\u00aa\27\3\2\2\2\u00ab\u00ad\5\32\16\2\u00ac"+
		"\u00ae\5\34\17\2\u00ad\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00ad\3"+
		"\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\5\36\20\2\u00b2"+
		"\u00b3\7*\2\2\u00b3\u00b4\7\n\2\2\u00b4\31\3\2\2\2\u00b5\u00b6\7&\2\2"+
		"\u00b6\u00b7\5*\26\2\u00b7\u00b8\7\'\2\2\u00b8\u00b9\5\f\7\2\u00b9\33"+
		"\3\2\2\2\u00ba\u00bb\7)\2\2\u00bb\u00bc\5*\26\2\u00bc\u00bd\7\'\2\2\u00bd"+
		"\u00be\5\f\7\2\u00be\35\3\2\2\2\u00bf\u00c0\7(\2\2\u00c0\u00c1\5\f\7\2"+
		"\u00c1\37\3\2\2\2\u00c2\u00c3\7\37\2\2\u00c3\u00c4\7W\2\2\u00c4\u00c5"+
		"\7\35\2\2\u00c5\u00c6\5\"\22\2\u00c6\u00c7\7 \2\2\u00c7\u00c8\5\f\7\2"+
		"\u00c8\u00c9\7!\2\2\u00c9\u00ca\7\n\2\2\u00ca!\3\2\2\2\u00cb\u00cc\5*"+
		"\26\2\u00cc\u00cd\7\"\2\2\u00cd\u00d0\5*\26\2\u00ce\u00cf\7#\2\2\u00cf"+
		"\u00d1\5*\26\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1#\3\2\2\2"+
		"\u00d2\u00d3\7$\2\2\u00d3\u00d4\5*\26\2\u00d4\u00d5\7 \2\2\u00d5\u00d6"+
		"\5\f\7\2\u00d6\u00d7\7%\2\2\u00d7\u00d8\7\n\2\2\u00d8%\3\2\2\2\u00d9\u00da"+
		"\7W\2\2\u00da\u00e3\7\5\2\2\u00db\u00e0\5(\25\2\u00dc\u00dd\7\t\2\2\u00dd"+
		"\u00df\5(\25\2\u00de\u00dc\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2"+
		"\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3"+
		"\u00db\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\7\6"+
		"\2\2\u00e6\'\3\2\2\2\u00e7\u00f2\5*\26\2\u00e8\u00e9\7W\2\2\u00e9\u00ea"+
		"\7\35\2\2\u00ea\u00f2\5*\26\2\u00eb\u00ed\7\34\2\2\u00ec\u00eb\3\2\2\2"+
		"\u00ec\u00ed\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef\7W\2\2\u00ef\u00f0"+
		"\7\36\2\2\u00f0\u00f2\7W\2\2\u00f1\u00e7\3\2\2\2\u00f1\u00e8\3\2\2\2\u00f1"+
		"\u00ec\3\2\2\2\u00f2)\3\2\2\2\u00f3\u00f4\b\26\1\2\u00f4\u00fe\5,\27\2"+
		"\u00f5\u00f6\7\34\2\2\u00f6\u00fe\5*\26\t\u00f7\u00f8\7\16\2\2\u00f8\u00fe"+
		"\5*\26\b\u00f9\u00fa\7\5\2\2\u00fa\u00fb\5*\26\2\u00fb\u00fc\7\6\2\2\u00fc"+
		"\u00fe\3\2\2\2\u00fd\u00f3\3\2\2\2\u00fd\u00f5\3\2\2\2\u00fd\u00f7\3\2"+
		"\2\2\u00fd\u00f9\3\2\2\2\u00fe\u010d\3\2\2\2\u00ff\u0100\f\7\2\2\u0100"+
		"\u0101\t\2\2\2\u0101\u010c\5*\26\b\u0102\u0103\f\6\2\2\u0103\u0104\t\3"+
		"\2\2\u0104\u010c\5*\26\7\u0105\u0106\f\5\2\2\u0106\u0107\t\4\2\2\u0107"+
		"\u010c\5*\26\6\u0108\u0109\f\4\2\2\u0109\u010a\t\5\2\2\u010a\u010c\5*"+
		"\26\5\u010b\u00ff\3\2\2\2\u010b\u0102\3\2\2\2\u010b\u0105\3\2\2\2\u010b"+
		"\u0108\3\2\2\2\u010c\u010f\3\2\2\2\u010d\u010b\3\2\2\2\u010d\u010e\3\2"+
		"\2\2\u010e+\3\2\2\2\u010f\u010d\3\2\2\2\u0110\u0114\5N(\2\u0111\u0114"+
		"\5.\30\2\u0112\u0114\5&\24\2\u0113\u0110\3\2\2\2\u0113\u0111\3\2\2\2\u0113"+
		"\u0112\3\2\2\2\u0114-\3\2\2\2\u0115\u0123\7W\2\2\u0116\u0117\7W\2\2\u0117"+
		"\u0118\7\3\2\2\u0118\u0119\5*\26\2\u0119\u011a\7\4\2\2\u011a\u0123\3\2"+
		"\2\2\u011b\u011e\7W\2\2\u011c\u011d\7\f\2\2\u011d\u011f\7W\2\2\u011e\u011c"+
		"\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121"+
		"\u0123\3\2\2\2\u0122\u0115\3\2\2\2\u0122\u0116\3\2\2\2\u0122\u011b\3\2"+
		"\2\2\u0123/\3\2\2\2\u0124\u0128\t\6\2\2\u0125\u0127\5\62\32\2\u0126\u0125"+
		"\3\2\2\2\u0127\u012a\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129"+
		"\u012b\3\2\2\2\u012a\u0128\3\2\2\2\u012b\u012c\7M\2\2\u012c\61\3\2\2\2"+
		"\u012d\u0132\7W\2\2\u012e\u012f\7\t\2\2\u012f\u0131\7W\2\2\u0130\u012e"+
		"\3\2\2\2\u0131\u0134\3\2\2\2\u0132\u0130\3\2\2\2\u0132\u0133\3\2\2\2\u0133"+
		"\u0135\3\2\2\2\u0134\u0132\3\2\2\2\u0135\u0136\7\13\2\2\u0136\u0139\5"+
		"\64\33\2\u0137\u0138\7\35\2\2\u0138\u013a\5J&\2\u0139\u0137\3\2\2\2\u0139"+
		"\u013a\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013c\7\n\2\2\u013c\63\3\2\2"+
		"\2\u013d\u0140\5:\36\2\u013e\u0140\5\66\34\2\u013f\u013d\3\2\2\2\u013f"+
		"\u013e\3\2\2\2\u0140\65\3\2\2\2\u0141\u0142\7,\2\2\u0142\u0143\7\3\2\2"+
		"\u0143\u0144\58\35\2\u0144\u0145\7\4\2\2\u0145\u0146\7.\2\2\u0146\u0147"+
		"\5:\36\2\u0147\67\3\2\2\2\u0148\u0149\5R*\2\u0149\u014a\7-\2\2\u014a\u014b"+
		"\5R*\2\u014b9\3\2\2\2\u014c\u0150\5<\37\2\u014d\u0150\5F$\2\u014e\u0150"+
		"\5H%\2\u014f\u014c\3\2\2\2\u014f\u014d\3\2\2\2\u014f\u014e\3\2\2\2\u0150"+
		";\3\2\2\2\u0151\u0154\5> \2\u0152\u0154\5D#\2\u0153\u0151\3\2\2\2\u0153"+
		"\u0152\3\2\2\2\u0154=\3\2\2\2\u0155\u0158\5@!\2\u0156\u0158\5B\"\2\u0157"+
		"\u0155\3\2\2\2\u0157\u0156\3\2\2\2\u0158?\3\2\2\2\u0159\u015a\t\7\2\2"+
		"\u015aA\3\2\2\2\u015b\u015c\t\b\2\2\u015cC\3\2\2\2\u015d\u015e\t\t\2\2"+
		"\u015eE\3\2\2\2\u015f\u0160\t\n\2\2\u0160G\3\2\2\2\u0161\u0162\t\13\2"+
		"\2\u0162I\3\2\2\2\u0163\u0166\5N(\2\u0164\u0166\5L\'\2\u0165\u0163\3\2"+
		"\2\2\u0165\u0164\3\2\2\2\u0166K\3\2\2\2\u0167\u016d\7\3\2\2\u0168\u0169"+
		"\5N(\2\u0169\u016a\7\t\2\2\u016a\u016c\3\2\2\2\u016b\u0168\3\2\2\2\u016c"+
		"\u016f\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u0170\3\2"+
		"\2\2\u016f\u016d\3\2\2\2\u0170\u0171\5N(\2\u0171\u0172\7\4\2\2\u0172M"+
		"\3\2\2\2\u0173\u0177\5P)\2\u0174\u0177\5T+\2\u0175\u0177\7O\2\2\u0176"+
		"\u0173\3\2\2\2\u0176\u0174\3\2\2\2\u0176\u0175\3\2\2\2\u0177O\3\2\2\2"+
		"\u0178\u017a\7\16\2\2\u0179\u0178\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u017b"+
		"\3\2\2\2\u017b\u0181\5R*\2\u017c\u017e\7\16\2\2\u017d\u017c\3\2\2\2\u017d"+
		"\u017e\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u0181\7U\2\2\u0180\u0179\3\2"+
		"\2\2\u0180\u017d\3\2\2\2\u0181Q\3\2\2\2\u0182\u0183\t\f\2\2\u0183S\3\2"+
		"\2\2\u0184\u0185\7V\2\2\u0185U\3\2\2\2#Y^ep}\u0087\u0092\u00a6\u00af\u00d0"+
		"\u00e0\u00e3\u00ec\u00f1\u00fd\u010b\u010d\u0113\u0120\u0122\u0128\u0132"+
		"\u0139\u013f\u014f\u0153\u0157\u0165\u016d\u0176\u0179\u017d\u0180";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}