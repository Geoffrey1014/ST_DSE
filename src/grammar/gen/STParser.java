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
		RES_EXIT=41, Res_RETURN=42, RES_ARRAY=43, FromTo=44, RES_OF=45, RES_BOOL=46, 
		RES_STRING=47, RES_SINT=48, RES_INT=49, RES_DINT=50, RES_LINT=51, RES_REAL=52, 
		RES_LREAL=53, RES_USINT=54, RES_UINT=55, RES_UDINT=56, RES_ULINT=57, RES_DATE=58, 
		RES_TIME_OF_DAY=59, RES_TOD=60, RES_DATE_AND_TIME=61, RES_DT=62, RES_BYTE=63, 
		RES_WORD=64, RES_DWORD=65, RES_LWORD=66, RES_PROGRAM=67, RES_END_PROGRAM=68, 
		RES_FUNCTION_BLOCK=69, RES_END_FUNCTION_BLOCK=70, RES_FUNCTION=71, RES_END_FUNCTION=72, 
		RES_VAR=73, RES_VAR_INPUT=74, RES_VAR_OUTPUT=75, RES_VAR_INPUT_OUTPUT=76, 
		RES_END_VAR=77, RES_VAR_TEMP=78, BOOL=79, Binary_literal=80, Octal_literal=81, 
		Decimal_literal=82, Pure_decimal_digits=83, Hexadecimal_literal=84, Floating_point_e=85, 
		Sign=86, Static_string_literal=87, ID=88, Block_comment=89, WS=90, RES_RETURN=91;
	public static final int
		RULE_pous = 0, RULE_pou = 1, RULE_program = 2, RULE_function_block = 3, 
		RULE_function = 4, RULE_stat_list = 5, RULE_stat = 6, RULE_invoc_stat = 7, 
		RULE_assign_stat = 8, RULE_if_stat = 9, RULE_if_else_stat = 10, RULE_if_elsif_stat = 11, 
		RULE_if_elsif_else_stat = 12, RULE_if_stmt = 13, RULE_elsif_stmt = 14, 
		RULE_else_stmt = 15, RULE_for_stat = 16, RULE_for_range = 17, RULE_while_stat = 18, 
		RULE_exit_stat = 19, RULE_return_stat = 20, RULE_invoc_expr = 21, RULE_param_assignment = 22, 
		RULE_expression = 23, RULE_primary_expression = 24, RULE_location = 25, 
		RULE_var_block = 26, RULE_variable_declaration = 27, RULE_type_rule = 28, 
		RULE_elementary_type_name = 29, RULE_numeric_type_name = 30, RULE_integer_type_name = 31, 
		RULE_signed_integer_type_name = 32, RULE_unsigned_integer_type_name = 33, 
		RULE_real_type_name = 34, RULE_date_type_name = 35, RULE_bit_string_type_name = 36, 
		RULE_string_type_name = 37, RULE_variable_initializer = 38, RULE_array_initialization = 39, 
		RULE_constant = 40, RULE_numeric_literal = 41, RULE_floating_point_literal = 42, 
		RULE_floating_point_fraction = 43, RULE_decimal_exponent = 44, RULE_integer_literal = 45, 
		RULE_string_literal = 46;
	private static String[] makeRuleNames() {
		return new String[] {
			"pous", "pou", "program", "function_block", "function", "stat_list", 
			"stat", "invoc_stat", "assign_stat", "if_stat", "if_else_stat", "if_elsif_stat", 
			"if_elsif_else_stat", "if_stmt", "elsif_stmt", "else_stmt", "for_stat", 
			"for_range", "while_stat", "exit_stat", "return_stat", "invoc_expr", 
			"param_assignment", "expression", "primary_expression", "location", "var_block", 
			"variable_declaration", "type_rule", "elementary_type_name", "numeric_type_name", 
			"integer_type_name", "signed_integer_type_name", "unsigned_integer_type_name", 
			"real_type_name", "date_type_name", "bit_string_type_name", "string_type_name", 
			"variable_initializer", "array_initialization", "constant", "numeric_literal", 
			"floating_point_literal", "floating_point_fraction", "decimal_exponent", 
			"integer_literal", "string_literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "']'", "'('", "')'", "'{'", "'}'", "','", "';'", "':'", 
			"'.'", "'+'", "'-'", "'*'", "'/'", "'MOD'", "'**'", "'<'", "'>'", "'<='", 
			"'>='", "'='", "'<>'", null, "'XOR'", "'OR'", "'NOT'", "':='", "'=>'", 
			"'FOR'", "'DO'", "'END_FOR'", "'TO'", "'BY'", "'WHILE'", "'END_WHILE'", 
			"'IF'", "'THEN'", "'ELSE'", "'ELSIF'", "'END_IF'", "'EXIT'", "'RETURN'", 
			"'ARRAY'", "'..'", "'OF'", "'BOOL'", "'STRING'", "'SINT'", "'INT'", "'DINT'", 
			"'LINT'", "'REAL'", "'LREAL'", "'USINT'", "'UINT'", "'UDINT'", "'ULINT'", 
			"'DATE'", "'TIME_OF_DAY'", "'TOD'", "'DATE_AND_TIME'", "'DT'", "'BYTE'", 
			"'WORD'", "'DWORD'", "'LWORD'", "'PROGRAM'", "'END_PROGRAM'", "'FUNCTION_BLOCK'", 
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
			"Res_RETURN", "RES_ARRAY", "FromTo", "RES_OF", "RES_BOOL", "RES_STRING", 
			"RES_SINT", "RES_INT", "RES_DINT", "RES_LINT", "RES_REAL", "RES_LREAL", 
			"RES_USINT", "RES_UINT", "RES_UDINT", "RES_ULINT", "RES_DATE", "RES_TIME_OF_DAY", 
			"RES_TOD", "RES_DATE_AND_TIME", "RES_DT", "RES_BYTE", "RES_WORD", "RES_DWORD", 
			"RES_LWORD", "RES_PROGRAM", "RES_END_PROGRAM", "RES_FUNCTION_BLOCK", 
			"RES_END_FUNCTION_BLOCK", "RES_FUNCTION", "RES_END_FUNCTION", "RES_VAR", 
			"RES_VAR_INPUT", "RES_VAR_OUTPUT", "RES_VAR_INPUT_OUTPUT", "RES_END_VAR", 
			"RES_VAR_TEMP", "BOOL", "Binary_literal", "Octal_literal", "Decimal_literal", 
			"Pure_decimal_digits", "Hexadecimal_literal", "Floating_point_e", "Sign", 
			"Static_string_literal", "ID", "Block_comment", "WS", "RES_RETURN"
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
			setState(95); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(94);
				pou();
				}
				}
				setState(97); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (RES_PROGRAM - 67)) | (1L << (RES_FUNCTION_BLOCK - 67)) | (1L << (RES_FUNCTION - 67)))) != 0) );
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
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RES_PROGRAM:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				program();
				}
				break;
			case RES_FUNCTION_BLOCK:
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				function_block();
				}
				break;
			case RES_FUNCTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(101);
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
			setState(104);
			match(RES_PROGRAM);
			setState(105);
			((ProgramContext)_localctx).name = match(ID);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & ((1L << (RES_VAR - 73)) | (1L << (RES_VAR_INPUT - 73)) | (1L << (RES_VAR_OUTPUT - 73)) | (1L << (RES_VAR_INPUT_OUTPUT - 73)) | (1L << (RES_VAR_TEMP - 73)))) != 0)) {
				{
				{
				setState(106);
				((ProgramContext)_localctx).var_block = var_block();
				((ProgramContext)_localctx).var_blocks.add(((ProgramContext)_localctx).var_block);
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(112);
			stat_list();
			setState(113);
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
			setState(115);
			match(RES_FUNCTION_BLOCK);
			setState(116);
			match(ID);
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & ((1L << (RES_VAR - 73)) | (1L << (RES_VAR_INPUT - 73)) | (1L << (RES_VAR_OUTPUT - 73)) | (1L << (RES_VAR_INPUT_OUTPUT - 73)) | (1L << (RES_VAR_TEMP - 73)))) != 0)) {
				{
				{
				setState(117);
				((Function_blockContext)_localctx).var_block = var_block();
				((Function_blockContext)_localctx).var_blocks.add(((Function_blockContext)_localctx).var_block);
				}
				}
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(123);
			stat_list();
			setState(124);
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
			setState(126);
			match(RES_FUNCTION);
			setState(127);
			match(ID);
			setState(128);
			match(COLON);
			setState(129);
			type_rule();
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & ((1L << (RES_VAR - 73)) | (1L << (RES_VAR_INPUT - 73)) | (1L << (RES_VAR_OUTPUT - 73)) | (1L << (RES_VAR_INPUT_OUTPUT - 73)) | (1L << (RES_VAR_TEMP - 73)))) != 0)) {
				{
				{
				setState(130);
				((FunctionContext)_localctx).var_block = var_block();
				((FunctionContext)_localctx).var_blocks.add(((FunctionContext)_localctx).var_block);
				}
				}
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(136);
			stat_list();
			setState(137);
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
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 29)) & ~0x3f) == 0 && ((1L << (_la - 29)) & ((1L << (RES_FOR - 29)) | (1L << (RES_WHILE - 29)) | (1L << (RES_IF - 29)) | (1L << (RES_EXIT - 29)) | (1L << (ID - 29)) | (1L << (RES_RETURN - 29)))) != 0)) {
				{
				{
				setState(139);
				stat();
				}
				}
				setState(144);
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
		public Return_statContext return_stat() {
			return getRuleContext(Return_statContext.class,0);
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
			setState(155);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				assign_stat();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(146);
				if_stat();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(147);
				if_else_stat();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(148);
				if_elsif_stat();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(149);
				if_elsif_else_stat();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(150);
				for_stat();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(151);
				while_stat();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(152);
				exit_stat();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(153);
				return_stat();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(154);
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
			setState(157);
			invoc_expr();
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
			setState(160);
			location();
			setState(161);
			match(AS_OP);
			setState(162);
			expression(0);
			setState(163);
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
			setState(165);
			if_stmt();
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
			setState(169);
			if_stmt();
			setState(170);
			else_stmt();
			setState(171);
			match(RES_END_IF);
			setState(172);
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
			setState(174);
			if_stmt();
			setState(176); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(175);
				elsif_stmt();
				}
				}
				setState(178); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RES_ELSIF );
			setState(180);
			match(RES_END_IF);
			setState(181);
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
			setState(183);
			if_stmt();
			setState(185); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(184);
				elsif_stmt();
				}
				}
				setState(187); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==RES_ELSIF );
			setState(189);
			else_stmt();
			setState(190);
			match(RES_END_IF);
			setState(191);
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
			setState(193);
			match(RES_IF);
			setState(194);
			expression(0);
			setState(195);
			match(RES_THEN);
			setState(196);
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
			setState(198);
			match(RES_ELSIF);
			setState(199);
			expression(0);
			setState(200);
			match(RES_THEN);
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
			setState(203);
			match(RES_ELSE);
			setState(204);
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
			setState(206);
			match(RES_FOR);
			setState(207);
			((For_statContext)_localctx).control_variable = match(ID);
			setState(208);
			match(AS_OP);
			setState(209);
			for_range();
			setState(210);
			match(RES_DO);
			setState(211);
			stat_list();
			setState(212);
			match(RES_END_FOR);
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
			setState(215);
			expression(0);
			setState(216);
			match(RES_TO);
			setState(217);
			expression(0);
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RES_BY) {
				{
				setState(218);
				match(RES_BY);
				setState(219);
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
			setState(222);
			match(RES_WHILE);
			setState(223);
			expression(0);
			setState(224);
			match(RES_DO);
			setState(225);
			stat_list();
			setState(226);
			match(RES_END_WHILE);
			setState(227);
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
			setState(229);
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

	public static class Return_statContext extends ParserRuleContext {
		public TerminalNode RES_RETURN() { return getToken(STParser.RES_RETURN, 0); }
		public Return_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).enterReturn_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STParserListener ) ((STParserListener)listener).exitReturn_stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STParserVisitor ) return ((STParserVisitor<? extends T>)visitor).visitReturn_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_statContext return_stat() throws RecognitionException {
		Return_statContext _localctx = new Return_statContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_return_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(RES_RETURN);
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
		enterRule(_localctx, 42, RULE_invoc_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			((Invoc_exprContext)_localctx).fb_name = match(ID);
			setState(234);
			match(L_PAREN);
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L_PAREN) | (1L << SUB_OP) | (1L << NOT_OP))) != 0) || ((((_la - 79)) & ~0x3f) == 0 && ((1L << (_la - 79)) & ((1L << (BOOL - 79)) | (1L << (Binary_literal - 79)) | (1L << (Octal_literal - 79)) | (1L << (Decimal_literal - 79)) | (1L << (Pure_decimal_digits - 79)) | (1L << (Hexadecimal_literal - 79)) | (1L << (Static_string_literal - 79)) | (1L << (ID - 79)))) != 0)) {
				{
				setState(235);
				param_assignment();
				setState(240);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(236);
					match(COMMA);
					setState(237);
					param_assignment();
					}
					}
					setState(242);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(245);
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
		enterRule(_localctx, 44, RULE_param_assignment);
		int _la;
		try {
			setState(257);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new ExternArgContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(247);
				expression(0);
				}
				break;
			case 2:
				_localctx = new AssignParamContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(248);
				match(ID);
				setState(249);
				match(AS_OP);
				setState(250);
				expression(0);
				}
				break;
			case 3:
				_localctx = new AssignOutputContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT_OP) {
					{
					setState(251);
					match(NOT_OP);
					}
				}

				setState(254);
				match(ID);
				setState(255);
				match(RT_AS_OP);
				setState(256);
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
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				_localctx = new PrimaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(260);
				primary_expression();
				}
				break;
			case 2:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(261);
				match(NOT_OP);
				setState(262);
				expression(7);
				}
				break;
			case 3:
				{
				_localctx = new NegateExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(263);
				match(SUB_OP);
				setState(264);
				expression(6);
				}
				break;
			case 4:
				{
				_localctx = new ParenExperContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(265);
				match(L_PAREN);
				setState(266);
				expression(0);
				setState(267);
				match(R_PAREN);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(285);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(283);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new ArithExprContext(new ExpressionContext(_parentctx, _parentState));
						((ArithExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(271);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(272);
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
						setState(273);
						((ArithExprContext)_localctx).right = expression(6);
						}
						break;
					case 2:
						{
						_localctx = new ComparisonContext(new ExpressionContext(_parentctx, _parentState));
						((ComparisonContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(274);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(275);
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
						setState(276);
						((ComparisonContext)_localctx).right = expression(5);
						}
						break;
					case 3:
						{
						_localctx = new EquateExprContext(new ExpressionContext(_parentctx, _parentState));
						((EquateExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(277);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(278);
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
						setState(279);
						((EquateExprContext)_localctx).right = expression(4);
						}
						break;
					case 4:
						{
						_localctx = new LogicContext(new ExpressionContext(_parentctx, _parentState));
						((LogicContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(280);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(281);
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
						setState(282);
						((LogicContext)_localctx).right = expression(3);
						}
						break;
					}
					} 
				}
				setState(287);
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
		enterRule(_localctx, 48, RULE_primary_expression);
		try {
			setState(291);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				constant();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(289);
				location();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(290);
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
		enterRule(_localctx, 50, RULE_location);
		try {
			setState(302);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				_localctx = new VarLocationContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(293);
				match(ID);
				}
				break;
			case 2:
				_localctx = new ArrayLocationContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(294);
				match(ID);
				setState(295);
				match(L_SQUARE);
				setState(296);
				expression(0);
				setState(297);
				match(R_SQUARE);
				}
				break;
			case 3:
				_localctx = new FbLcationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(299);
				match(ID);
				setState(300);
				match(DOT);
				setState(301);
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
		enterRule(_localctx, 52, RULE_var_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			((Var_blockContext)_localctx).var_acc_type = _input.LT(1);
			_la = _input.LA(1);
			if ( !(((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & ((1L << (RES_VAR - 73)) | (1L << (RES_VAR_INPUT - 73)) | (1L << (RES_VAR_OUTPUT - 73)) | (1L << (RES_VAR_INPUT_OUTPUT - 73)) | (1L << (RES_VAR_TEMP - 73)))) != 0)) ) {
				((Var_blockContext)_localctx).var_acc_type = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(308);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(305);
				variable_declaration();
				}
				}
				setState(310);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(311);
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
		enterRule(_localctx, 54, RULE_variable_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			((Variable_declarationContext)_localctx).ID = match(ID);
			((Variable_declarationContext)_localctx).names.add(((Variable_declarationContext)_localctx).ID);
			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(314);
				match(COMMA);
				setState(315);
				((Variable_declarationContext)_localctx).ID = match(ID);
				((Variable_declarationContext)_localctx).names.add(((Variable_declarationContext)_localctx).ID);
				}
				}
				setState(320);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(321);
			match(COLON);
			setState(322);
			type_rule();
			setState(325);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS_OP) {
				{
				setState(323);
				match(AS_OP);
				setState(324);
				variable_initializer();
				}
			}

			setState(327);
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
		enterRule(_localctx, 56, RULE_type_rule);
		try {
			setState(339);
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
				setState(329);
				elementary_type_name();
				}
				break;
			case RES_ARRAY:
				_localctx = new ArrayTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(330);
				match(RES_ARRAY);
				setState(331);
				match(L_SQUARE);
				setState(332);
				((ArrayTypeContext)_localctx).integer_literal = integer_literal();
				((ArrayTypeContext)_localctx).range.add(((ArrayTypeContext)_localctx).integer_literal);
				setState(333);
				match(FromTo);
				setState(334);
				((ArrayTypeContext)_localctx).integer_literal = integer_literal();
				((ArrayTypeContext)_localctx).range.add(((ArrayTypeContext)_localctx).integer_literal);
				setState(335);
				match(R_SQUARE);
				setState(336);
				match(RES_OF);
				setState(337);
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
		enterRule(_localctx, 58, RULE_elementary_type_name);
		try {
			setState(345);
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
				setState(341);
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
				setState(342);
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
				setState(343);
				bit_string_type_name();
				}
				break;
			case RES_STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(344);
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
		enterRule(_localctx, 60, RULE_numeric_type_name);
		try {
			setState(349);
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
				setState(347);
				integer_type_name();
				}
				break;
			case RES_REAL:
			case RES_LREAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(348);
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
		enterRule(_localctx, 62, RULE_integer_type_name);
		try {
			setState(353);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RES_SINT:
			case RES_INT:
			case RES_DINT:
			case RES_LINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(351);
				signed_integer_type_name();
				}
				break;
			case RES_USINT:
			case RES_UINT:
			case RES_UDINT:
			case RES_ULINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(352);
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
		enterRule(_localctx, 64, RULE_signed_integer_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
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
		enterRule(_localctx, 66, RULE_unsigned_integer_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
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
		enterRule(_localctx, 68, RULE_real_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
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
		enterRule(_localctx, 70, RULE_date_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
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
		enterRule(_localctx, 72, RULE_bit_string_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363);
			_la = _input.LA(1);
			if ( !(((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & ((1L << (RES_BOOL - 46)) | (1L << (RES_BYTE - 46)) | (1L << (RES_WORD - 46)) | (1L << (RES_DWORD - 46)) | (1L << (RES_LWORD - 46)))) != 0)) ) {
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
		enterRule(_localctx, 74, RULE_string_type_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
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
		enterRule(_localctx, 76, RULE_variable_initializer);
		try {
			setState(369);
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
				setState(367);
				constant();
				}
				break;
			case L_SQUARE:
				enterOuterAlt(_localctx, 2);
				{
				setState(368);
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
		enterRule(_localctx, 78, RULE_array_initialization);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(371);
			match(L_SQUARE);
			setState(377);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(372);
					constant();
					setState(373);
					match(COMMA);
					}
					} 
				}
				setState(379);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			setState(380);
			constant();
			setState(381);
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
		enterRule(_localctx, 80, RULE_constant);
		try {
			setState(386);
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
				setState(383);
				numeric_literal();
				}
				break;
			case Static_string_literal:
				enterOuterAlt(_localctx, 2);
				{
				setState(384);
				string_literal();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 3);
				{
				setState(385);
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
		enterRule(_localctx, 82, RULE_numeric_literal);
		try {
			setState(390);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(388);
				integer_literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(389);
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
		enterRule(_localctx, 84, RULE_floating_point_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			floating_point_fraction();
			setState(394);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(393);
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
		enterRule(_localctx, 86, RULE_floating_point_fraction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SUB_OP) {
				{
				setState(396);
				match(SUB_OP);
				}
			}

			setState(399);
			match(Decimal_literal);
			setState(402);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(400);
				match(DOT);
				setState(401);
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
		enterRule(_localctx, 88, RULE_decimal_exponent);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(404);
			match(Floating_point_e);
			setState(406);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Sign) {
				{
				setState(405);
				match(Sign);
				}
			}

			setState(408);
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
		enterRule(_localctx, 90, RULE_integer_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(411);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SUB_OP) {
				{
				setState(410);
				match(SUB_OP);
				}
			}

			setState(413);
			_la = _input.LA(1);
			if ( !(((((_la - 80)) & ~0x3f) == 0 && ((1L << (_la - 80)) & ((1L << (Binary_literal - 80)) | (1L << (Octal_literal - 80)) | (1L << (Decimal_literal - 80)) | (1L << (Pure_decimal_digits - 80)) | (1L << (Hexadecimal_literal - 80)))) != 0)) ) {
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
		enterRule(_localctx, 92, RULE_string_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
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
		case 23:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3]\u01a4\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\6\2b\n\2\r\2\16\2c\3\3\3\3\3\3\5"+
		"\3i\n\3\3\4\3\4\3\4\7\4n\n\4\f\4\16\4q\13\4\3\4\3\4\3\4\3\5\3\5\3\5\7"+
		"\5y\n\5\f\5\16\5|\13\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\7\6\u0086\n\6\f"+
		"\6\16\6\u0089\13\6\3\6\3\6\3\6\3\7\7\7\u008f\n\7\f\7\16\7\u0092\13\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u009e\n\b\3\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\6\r\u00b3"+
		"\n\r\r\r\16\r\u00b4\3\r\3\r\3\r\3\16\3\16\6\16\u00bc\n\16\r\16\16\16\u00bd"+
		"\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\23\5\23\u00df\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\7\27\u00f1\n\27\f\27\16\27\u00f4"+
		"\13\27\5\27\u00f6\n\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\5\30\u00ff\n"+
		"\30\3\30\3\30\3\30\5\30\u0104\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\5\31\u0110\n\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\7\31\u011e\n\31\f\31\16\31\u0121\13\31\3\32\3\32"+
		"\3\32\5\32\u0126\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33"+
		"\u0131\n\33\3\34\3\34\7\34\u0135\n\34\f\34\16\34\u0138\13\34\3\34\3\34"+
		"\3\35\3\35\3\35\7\35\u013f\n\35\f\35\16\35\u0142\13\35\3\35\3\35\3\35"+
		"\3\35\5\35\u0148\n\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\5\36\u0156\n\36\3\37\3\37\3\37\3\37\5\37\u015c\n\37\3 \3 \5"+
		" \u0160\n \3!\3!\5!\u0164\n!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3"+
		"(\3(\5(\u0174\n(\3)\3)\3)\3)\7)\u017a\n)\f)\16)\u017d\13)\3)\3)\3)\3*"+
		"\3*\3*\5*\u0185\n*\3+\3+\5+\u0189\n+\3,\3,\5,\u018d\n,\3-\5-\u0190\n-"+
		"\3-\3-\3-\5-\u0195\n-\3.\3.\5.\u0199\n.\3.\3.\3/\5/\u019e\n/\3/\3/\3\60"+
		"\3\60\3\60\2\3\60\61\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:<>@BDFHJLNPRTVXZ\\^\2\r\3\2\r\22\3\2\23\26\3\2\27\30\3\2\31"+
		"\33\4\2KNPP\3\2\62\65\3\28;\3\2\66\67\3\2<@\4\2\60\60AD\3\2RV\2\u01aa"+
		"\2a\3\2\2\2\4h\3\2\2\2\6j\3\2\2\2\bu\3\2\2\2\n\u0080\3\2\2\2\f\u0090\3"+
		"\2\2\2\16\u009d\3\2\2\2\20\u009f\3\2\2\2\22\u00a2\3\2\2\2\24\u00a7\3\2"+
		"\2\2\26\u00ab\3\2\2\2\30\u00b0\3\2\2\2\32\u00b9\3\2\2\2\34\u00c3\3\2\2"+
		"\2\36\u00c8\3\2\2\2 \u00cd\3\2\2\2\"\u00d0\3\2\2\2$\u00d9\3\2\2\2&\u00e0"+
		"\3\2\2\2(\u00e7\3\2\2\2*\u00e9\3\2\2\2,\u00eb\3\2\2\2.\u0103\3\2\2\2\60"+
		"\u010f\3\2\2\2\62\u0125\3\2\2\2\64\u0130\3\2\2\2\66\u0132\3\2\2\28\u013b"+
		"\3\2\2\2:\u0155\3\2\2\2<\u015b\3\2\2\2>\u015f\3\2\2\2@\u0163\3\2\2\2B"+
		"\u0165\3\2\2\2D\u0167\3\2\2\2F\u0169\3\2\2\2H\u016b\3\2\2\2J\u016d\3\2"+
		"\2\2L\u016f\3\2\2\2N\u0173\3\2\2\2P\u0175\3\2\2\2R\u0184\3\2\2\2T\u0188"+
		"\3\2\2\2V\u018a\3\2\2\2X\u018f\3\2\2\2Z\u0196\3\2\2\2\\\u019d\3\2\2\2"+
		"^\u01a1\3\2\2\2`b\5\4\3\2a`\3\2\2\2bc\3\2\2\2ca\3\2\2\2cd\3\2\2\2d\3\3"+
		"\2\2\2ei\5\6\4\2fi\5\b\5\2gi\5\n\6\2he\3\2\2\2hf\3\2\2\2hg\3\2\2\2i\5"+
		"\3\2\2\2jk\7E\2\2ko\7Z\2\2ln\5\66\34\2ml\3\2\2\2nq\3\2\2\2om\3\2\2\2o"+
		"p\3\2\2\2pr\3\2\2\2qo\3\2\2\2rs\5\f\7\2st\7F\2\2t\7\3\2\2\2uv\7G\2\2v"+
		"z\7Z\2\2wy\5\66\34\2xw\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{}\3\2\2\2"+
		"|z\3\2\2\2}~\5\f\7\2~\177\7H\2\2\177\t\3\2\2\2\u0080\u0081\7I\2\2\u0081"+
		"\u0082\7Z\2\2\u0082\u0083\7\13\2\2\u0083\u0087\5:\36\2\u0084\u0086\5\66"+
		"\34\2\u0085\u0084\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3\2\2\2\u0087"+
		"\u0088\3\2\2\2\u0088\u008a\3\2\2\2\u0089\u0087\3\2\2\2\u008a\u008b\5\f"+
		"\7\2\u008b\u008c\7J\2\2\u008c\13\3\2\2\2\u008d\u008f\5\16\b\2\u008e\u008d"+
		"\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\r\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u009e\5\22\n\2\u0094\u009e\5\24\13"+
		"\2\u0095\u009e\5\26\f\2\u0096\u009e\5\30\r\2\u0097\u009e\5\32\16\2\u0098"+
		"\u009e\5\"\22\2\u0099\u009e\5&\24\2\u009a\u009e\5(\25\2\u009b\u009e\5"+
		"*\26\2\u009c\u009e\5\20\t\2\u009d\u0093\3\2\2\2\u009d\u0094\3\2\2\2\u009d"+
		"\u0095\3\2\2\2\u009d\u0096\3\2\2\2\u009d\u0097\3\2\2\2\u009d\u0098\3\2"+
		"\2\2\u009d\u0099\3\2\2\2\u009d\u009a\3\2\2\2\u009d\u009b\3\2\2\2\u009d"+
		"\u009c\3\2\2\2\u009e\17\3\2\2\2\u009f\u00a0\5,\27\2\u00a0\u00a1\7\n\2"+
		"\2\u00a1\21\3\2\2\2\u00a2\u00a3\5\64\33\2\u00a3\u00a4\7\35\2\2\u00a4\u00a5"+
		"\5\60\31\2\u00a5\u00a6\7\n\2\2\u00a6\23\3\2\2\2\u00a7\u00a8\5\34\17\2"+
		"\u00a8\u00a9\7*\2\2\u00a9\u00aa\7\n\2\2\u00aa\25\3\2\2\2\u00ab\u00ac\5"+
		"\34\17\2\u00ac\u00ad\5 \21\2\u00ad\u00ae\7*\2\2\u00ae\u00af\7\n\2\2\u00af"+
		"\27\3\2\2\2\u00b0\u00b2\5\34\17\2\u00b1\u00b3\5\36\20\2\u00b2\u00b1\3"+
		"\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\u00b6\3\2\2\2\u00b6\u00b7\7*\2\2\u00b7\u00b8\7\n\2\2\u00b8\31\3\2\2\2"+
		"\u00b9\u00bb\5\34\17\2\u00ba\u00bc\5\36\20\2\u00bb\u00ba\3\2\2\2\u00bc"+
		"\u00bd\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\3\2"+
		"\2\2\u00bf\u00c0\5 \21\2\u00c0\u00c1\7*\2\2\u00c1\u00c2\7\n\2\2\u00c2"+
		"\33\3\2\2\2\u00c3\u00c4\7&\2\2\u00c4\u00c5\5\60\31\2\u00c5\u00c6\7\'\2"+
		"\2\u00c6\u00c7\5\f\7\2\u00c7\35\3\2\2\2\u00c8\u00c9\7)\2\2\u00c9\u00ca"+
		"\5\60\31\2\u00ca\u00cb\7\'\2\2\u00cb\u00cc\5\f\7\2\u00cc\37\3\2\2\2\u00cd"+
		"\u00ce\7(\2\2\u00ce\u00cf\5\f\7\2\u00cf!\3\2\2\2\u00d0\u00d1\7\37\2\2"+
		"\u00d1\u00d2\7Z\2\2\u00d2\u00d3\7\35\2\2\u00d3\u00d4\5$\23\2\u00d4\u00d5"+
		"\7 \2\2\u00d5\u00d6\5\f\7\2\u00d6\u00d7\7!\2\2\u00d7\u00d8\7\n\2\2\u00d8"+
		"#\3\2\2\2\u00d9\u00da\5\60\31\2\u00da\u00db\7\"\2\2\u00db\u00de\5\60\31"+
		"\2\u00dc\u00dd\7#\2\2\u00dd\u00df\5\60\31\2\u00de\u00dc\3\2\2\2\u00de"+
		"\u00df\3\2\2\2\u00df%\3\2\2\2\u00e0\u00e1\7$\2\2\u00e1\u00e2\5\60\31\2"+
		"\u00e2\u00e3\7 \2\2\u00e3\u00e4\5\f\7\2\u00e4\u00e5\7%\2\2\u00e5\u00e6"+
		"\7\n\2\2\u00e6\'\3\2\2\2\u00e7\u00e8\7+\2\2\u00e8)\3\2\2\2\u00e9\u00ea"+
		"\7]\2\2\u00ea+\3\2\2\2\u00eb\u00ec\7Z\2\2\u00ec\u00f5\7\5\2\2\u00ed\u00f2"+
		"\5.\30\2\u00ee\u00ef\7\t\2\2\u00ef\u00f1\5.\30\2\u00f0\u00ee\3\2\2\2\u00f1"+
		"\u00f4\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f6\3\2"+
		"\2\2\u00f4\u00f2\3\2\2\2\u00f5\u00ed\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6"+
		"\u00f7\3\2\2\2\u00f7\u00f8\7\6\2\2\u00f8-\3\2\2\2\u00f9\u0104\5\60\31"+
		"\2\u00fa\u00fb\7Z\2\2\u00fb\u00fc\7\35\2\2\u00fc\u0104\5\60\31\2\u00fd"+
		"\u00ff\7\34\2\2\u00fe\u00fd\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\3"+
		"\2\2\2\u0100\u0101\7Z\2\2\u0101\u0102\7\36\2\2\u0102\u0104\7Z\2\2\u0103"+
		"\u00f9\3\2\2\2\u0103\u00fa\3\2\2\2\u0103\u00fe\3\2\2\2\u0104/\3\2\2\2"+
		"\u0105\u0106\b\31\1\2\u0106\u0110\5\62\32\2\u0107\u0108\7\34\2\2\u0108"+
		"\u0110\5\60\31\t\u0109\u010a\7\16\2\2\u010a\u0110\5\60\31\b\u010b\u010c"+
		"\7\5\2\2\u010c\u010d\5\60\31\2\u010d\u010e\7\6\2\2\u010e\u0110\3\2\2\2"+
		"\u010f\u0105\3\2\2\2\u010f\u0107\3\2\2\2\u010f\u0109\3\2\2\2\u010f\u010b"+
		"\3\2\2\2\u0110\u011f\3\2\2\2\u0111\u0112\f\7\2\2\u0112\u0113\t\2\2\2\u0113"+
		"\u011e\5\60\31\b\u0114\u0115\f\6\2\2\u0115\u0116\t\3\2\2\u0116\u011e\5"+
		"\60\31\7\u0117\u0118\f\5\2\2\u0118\u0119\t\4\2\2\u0119\u011e\5\60\31\6"+
		"\u011a\u011b\f\4\2\2\u011b\u011c\t\5\2\2\u011c\u011e\5\60\31\5\u011d\u0111"+
		"\3\2\2\2\u011d\u0114\3\2\2\2\u011d\u0117\3\2\2\2\u011d\u011a\3\2\2\2\u011e"+
		"\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\61\3\2\2"+
		"\2\u0121\u011f\3\2\2\2\u0122\u0126\5R*\2\u0123\u0126\5\64\33\2\u0124\u0126"+
		"\5,\27\2\u0125\u0122\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0124\3\2\2\2\u0126"+
		"\63\3\2\2\2\u0127\u0131\7Z\2\2\u0128\u0129\7Z\2\2\u0129\u012a\7\3\2\2"+
		"\u012a\u012b\5\60\31\2\u012b\u012c\7\4\2\2\u012c\u0131\3\2\2\2\u012d\u012e"+
		"\7Z\2\2\u012e\u012f\7\f\2\2\u012f\u0131\7Z\2\2\u0130\u0127\3\2\2\2\u0130"+
		"\u0128\3\2\2\2\u0130\u012d\3\2\2\2\u0131\65\3\2\2\2\u0132\u0136\t\6\2"+
		"\2\u0133\u0135\58\35\2\u0134\u0133\3\2\2\2\u0135\u0138\3\2\2\2\u0136\u0134"+
		"\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0139\3\2\2\2\u0138\u0136\3\2\2\2\u0139"+
		"\u013a\7O\2\2\u013a\67\3\2\2\2\u013b\u0140\7Z\2\2\u013c\u013d\7\t\2\2"+
		"\u013d\u013f\7Z\2\2\u013e\u013c\3\2\2\2\u013f\u0142\3\2\2\2\u0140\u013e"+
		"\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0143\3\2\2\2\u0142\u0140\3\2\2\2\u0143"+
		"\u0144\7\13\2\2\u0144\u0147\5:\36\2\u0145\u0146\7\35\2\2\u0146\u0148\5"+
		"N(\2\u0147\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u0149\3\2\2\2\u0149"+
		"\u014a\7\n\2\2\u014a9\3\2\2\2\u014b\u0156\5<\37\2\u014c\u014d\7-\2\2\u014d"+
		"\u014e\7\3\2\2\u014e\u014f\5\\/\2\u014f\u0150\7.\2\2\u0150\u0151\5\\/"+
		"\2\u0151\u0152\7\4\2\2\u0152\u0153\7/\2\2\u0153\u0154\5<\37\2\u0154\u0156"+
		"\3\2\2\2\u0155\u014b\3\2\2\2\u0155\u014c\3\2\2\2\u0156;\3\2\2\2\u0157"+
		"\u015c\5> \2\u0158\u015c\5H%\2\u0159\u015c\5J&\2\u015a\u015c\5L\'\2\u015b"+
		"\u0157\3\2\2\2\u015b\u0158\3\2\2\2\u015b\u0159\3\2\2\2\u015b\u015a\3\2"+
		"\2\2\u015c=\3\2\2\2\u015d\u0160\5@!\2\u015e\u0160\5F$\2\u015f\u015d\3"+
		"\2\2\2\u015f\u015e\3\2\2\2\u0160?\3\2\2\2\u0161\u0164\5B\"\2\u0162\u0164"+
		"\5D#\2\u0163\u0161\3\2\2\2\u0163\u0162\3\2\2\2\u0164A\3\2\2\2\u0165\u0166"+
		"\t\7\2\2\u0166C\3\2\2\2\u0167\u0168\t\b\2\2\u0168E\3\2\2\2\u0169\u016a"+
		"\t\t\2\2\u016aG\3\2\2\2\u016b\u016c\t\n\2\2\u016cI\3\2\2\2\u016d\u016e"+
		"\t\13\2\2\u016eK\3\2\2\2\u016f\u0170\7\61\2\2\u0170M\3\2\2\2\u0171\u0174"+
		"\5R*\2\u0172\u0174\5P)\2\u0173\u0171\3\2\2\2\u0173\u0172\3\2\2\2\u0174"+
		"O\3\2\2\2\u0175\u017b\7\3\2\2\u0176\u0177\5R*\2\u0177\u0178\7\t\2\2\u0178"+
		"\u017a\3\2\2\2\u0179\u0176\3\2\2\2\u017a\u017d\3\2\2\2\u017b\u0179\3\2"+
		"\2\2\u017b\u017c\3\2\2\2\u017c\u017e\3\2\2\2\u017d\u017b\3\2\2\2\u017e"+
		"\u017f\5R*\2\u017f\u0180\7\4\2\2\u0180Q\3\2\2\2\u0181\u0185\5T+\2\u0182"+
		"\u0185\5^\60\2\u0183\u0185\7Q\2\2\u0184\u0181\3\2\2\2\u0184\u0182\3\2"+
		"\2\2\u0184\u0183\3\2\2\2\u0185S\3\2\2\2\u0186\u0189\5\\/\2\u0187\u0189"+
		"\5V,\2\u0188\u0186\3\2\2\2\u0188\u0187\3\2\2\2\u0189U\3\2\2\2\u018a\u018c"+
		"\5X-\2\u018b\u018d\5Z.\2\u018c\u018b\3\2\2\2\u018c\u018d\3\2\2\2\u018d"+
		"W\3\2\2\2\u018e\u0190\7\16\2\2\u018f\u018e\3\2\2\2\u018f\u0190\3\2\2\2"+
		"\u0190\u0191\3\2\2\2\u0191\u0194\7T\2\2\u0192\u0193\7\f\2\2\u0193\u0195"+
		"\7T\2\2\u0194\u0192\3\2\2\2\u0194\u0195\3\2\2\2\u0195Y\3\2\2\2\u0196\u0198"+
		"\7W\2\2\u0197\u0199\7X\2\2\u0198\u0197\3\2\2\2\u0198\u0199\3\2\2\2\u0199"+
		"\u019a\3\2\2\2\u019a\u019b\7T\2\2\u019b[\3\2\2\2\u019c\u019e\7\16\2\2"+
		"\u019d\u019c\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u019f\3\2\2\2\u019f\u01a0"+
		"\t\f\2\2\u01a0]\3\2\2\2\u01a1\u01a2\7Y\2\2\u01a2_\3\2\2\2%choz\u0087\u0090"+
		"\u009d\u00b4\u00bd\u00de\u00f2\u00f5\u00fe\u0103\u010f\u011d\u011f\u0125"+
		"\u0130\u0136\u0140\u0147\u0155\u015b\u015f\u0163\u0173\u017b\u0184\u0188"+
		"\u018c\u018f\u0194\u0198\u019d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}