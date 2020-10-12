// Generated from /Users/heweigang/MY_project/ST_DSE/src/grammar/ST.g4 by ANTLR 4.8
package gen;
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, LP=49, RP=50, POWER=51, MUL=52, DIV=53, 
		MOD=54, ADD=55, MIN=56, GT=57, LT=58, EQ=59, GTE=60, LTE=61, NE=62, AND=63, 
		XOR=64, OR=65, Binary_literal=66, Octal_literal=67, Decimal_literal=68, 
		Pure_decimal_digits=69, Hexadecimal_literal=70, Floating_point_literal=71, 
		Static_string_literal=72, ID=73, WS=74, Block_comment=75;
	public static final int
		RULE_pous = 0, RULE_pou = 1, RULE_program = 2, RULE_function_block = 3, 
		RULE_function = 4, RULE_stat_list = 5, RULE_stat = 6, RULE_assign_stat = 7, 
		RULE_if_stat = 8, RULE_expression = 9, RULE_primary_expression = 10, RULE_var_block = 11, 
		RULE_var_type = 12, RULE_type_rule = 13, RULE_variable_declaration = 14, 
		RULE_elementary_type_name = 15, RULE_numeric_type_name = 16, RULE_integer_type_name = 17, 
		RULE_signed_integer_type_name = 18, RULE_unsigned_integer_type_name = 19, 
		RULE_real_type_name = 20, RULE_date_type_name = 21, RULE_bit_string_type_name = 22, 
		RULE_variable_initializer = 23, RULE_constant = 24, RULE_boolean_literal = 25, 
		RULE_numeric_literal = 26, RULE_integer_literal = 27, RULE_string_literal = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"pous", "pou", "program", "function_block", "function", "stat_list", 
			"stat", "assign_stat", "if_stat", "expression", "primary_expression", 
			"var_block", "var_type", "type_rule", "variable_declaration", "elementary_type_name", 
			"numeric_type_name", "integer_type_name", "signed_integer_type_name", 
			"unsigned_integer_type_name", "real_type_name", "date_type_name", "bit_string_type_name", 
			"variable_initializer", "constant", "boolean_literal", "numeric_literal", 
			"integer_literal", "string_literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'PROGRAM'", "'END_PROGRAM'", "'FUNCTION_BLOCK'", "'END_FUNCTION_BLOCK'", 
			"'FUNCTION'", "':'", "'END_FUNCTION'", "':='", "';'", "'IF'", "'THEN'", 
			"'ELSIF'", "'ELSE'", "'END_IF'", "'-'", "'NOT'", "'&'", "'AND'", "'OXR'", 
			"')'", "'END_VAR'", "'VAR'", "'VAR_INPUT'", "'VAR_OUTPUT'", "'VAR_INPUT_OUTPUT'", 
			"','", "'SINT'", "'INT'", "'DINT'", "'LINT'", "'USINT'", "'UINT'", "'UDINT'", 
			"'ULINT'", "'REAL'", "'LREAL'", "'DATE'", "'TIME_OF_DAY'", "'TOD'", "'DATE_AND_TIME'", 
			"'DT'", "'BOOL'", "'BYTE'", "'WORD'", "'DWORD'", "'LWORD'", "'TRUE'", 
			"'FALSE'", "'('", "'}'", "'**'", "'*'", "'/'", "'MOD'", "'+'", "'_'", 
			"'>'", "'<'", "'='", "'>='", "'<='", "'<>'", null, "'XOR'", "'OR'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "LP", "RP", "POWER", "MUL", "DIV", "MOD", "ADD", "MIN", "GT", "LT", 
			"EQ", "GTE", "LTE", "NE", "AND", "XOR", "OR", "Binary_literal", "Octal_literal", 
			"Decimal_literal", "Pure_decimal_digits", "Hexadecimal_literal", "Floating_point_literal", 
			"Static_string_literal", "ID", "WS", "Block_comment"
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
	public String getGrammarFileName() { return "ST.g4"; }

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
			if ( listener instanceof STListener ) ((STListener)listener).enterPous(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitPous(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitPous(this);
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
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__4))) != 0)) {
				{
				{
				setState(58);
				pou();
				}
				}
				setState(63);
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
			if ( listener instanceof STListener ) ((STListener)listener).enterPou(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitPou(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitPou(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PouContext pou() throws RecognitionException {
		PouContext _localctx = new PouContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_pou);
		try {
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				program();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				function_block();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(66);
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
		public Stat_listContext stat_list() {
			return getRuleContext(Stat_listContext.class,0);
		}
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
			if ( listener instanceof STListener ) ((STListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitProgram(this);
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
			setState(69);
			match(T__0);
			setState(70);
			((ProgramContext)_localctx).name = match(ID);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) {
				{
				{
				setState(71);
				((ProgramContext)_localctx).var_block = var_block();
				((ProgramContext)_localctx).var_blocks.add(((ProgramContext)_localctx).var_block);
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
			stat_list();
			setState(78);
			match(T__1);
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
		public Token name;
		public Var_blockContext var_block;
		public List<Var_blockContext> var_blocks = new ArrayList<Var_blockContext>();
		public Stat_listContext stat_list() {
			return getRuleContext(Stat_listContext.class,0);
		}
		public TerminalNode ID() { return getToken(STParser.ID, 0); }
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
			if ( listener instanceof STListener ) ((STListener)listener).enterFunction_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitFunction_block(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitFunction_block(this);
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
			setState(80);
			match(T__2);
			setState(81);
			((Function_blockContext)_localctx).name = match(ID);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) {
				{
				{
				setState(82);
				((Function_blockContext)_localctx).var_block = var_block();
				((Function_blockContext)_localctx).var_blocks.add(((Function_blockContext)_localctx).var_block);
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(88);
			stat_list();
			setState(89);
			match(T__3);
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
		public Token name;
		public Type_ruleContext type;
		public Var_blockContext var_block;
		public List<Var_blockContext> var_blocks = new ArrayList<Var_blockContext>();
		public Stat_listContext stat_list() {
			return getRuleContext(Stat_listContext.class,0);
		}
		public TerminalNode ID() { return getToken(STParser.ID, 0); }
		public Type_ruleContext type_rule() {
			return getRuleContext(Type_ruleContext.class,0);
		}
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
			if ( listener instanceof STListener ) ((STListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitFunction(this);
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
			setState(91);
			match(T__4);
			setState(92);
			((FunctionContext)_localctx).name = match(ID);
			setState(93);
			match(T__5);
			setState(94);
			((FunctionContext)_localctx).type = type_rule();
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) {
				{
				{
				setState(95);
				((FunctionContext)_localctx).var_block = var_block();
				((FunctionContext)_localctx).var_blocks.add(((FunctionContext)_localctx).var_block);
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(101);
			stat_list();
			setState(102);
			match(T__6);
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
			if ( listener instanceof STListener ) ((STListener)listener).enterStat_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitStat_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitStat_list(this);
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
			setState(104);
			stat();
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9 || _la==ID) {
				{
				{
				setState(105);
				stat();
				}
				}
				setState(110);
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
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_stat);
		try {
			setState(113);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				assign_stat();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				if_stat();
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

	public static class Assign_statContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(STParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Assign_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterAssign_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitAssign_stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitAssign_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assign_statContext assign_stat() throws RecognitionException {
		Assign_statContext _localctx = new Assign_statContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assign_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(ID);
			setState(116);
			match(T__7);
			setState(117);
			expression(0);
			setState(118);
			match(T__8);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<Stat_listContext> stat_list() {
			return getRuleContexts(Stat_listContext.class);
		}
		public Stat_listContext stat_list(int i) {
			return getRuleContext(Stat_listContext.class,i);
		}
		public If_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterIf_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitIf_stat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitIf_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_statContext if_stat() throws RecognitionException {
		If_statContext _localctx = new If_statContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_if_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(T__9);
			setState(121);
			expression(0);
			setState(122);
			match(T__10);
			setState(123);
			stat_list();
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(124);
				match(T__11);
				setState(125);
				expression(0);
				setState(126);
				match(T__10);
				setState(127);
				stat_list();
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(134);
				match(T__12);
				setState(135);
				stat_list();
				}
			}

			setState(138);
			match(T__13);
			setState(139);
			match(T__8);
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
	public static class NotContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulDivModContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(STParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(STParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(STParser.MOD, 0); }
		public MulDivModContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterMulDivMod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitMulDivMod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitMulDivMod(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddSubContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ADD() { return getToken(STParser.ADD, 0); }
		public AddSubContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitAddSub(this);
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
		public TerminalNode GT() { return getToken(STParser.GT, 0); }
		public TerminalNode LT() { return getToken(STParser.LT, 0); }
		public TerminalNode LTE() { return getToken(STParser.LTE, 0); }
		public TerminalNode GTE() { return getToken(STParser.GTE, 0); }
		public TerminalNode EQ() { return getToken(STParser.EQ, 0); }
		public TerminalNode NE() { return getToken(STParser.NE, 0); }
		public ComparisonContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitComparison(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitComparison(this);
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
			if ( listener instanceof STListener ) ((STListener)listener).enterPrimaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitPrimaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitPrimaryExpr(this);
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
		public TerminalNode OR() { return getToken(STParser.OR, 0); }
		public LogicContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterLogic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitLogic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitLogic(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PowerContext extends ExpressionContext {
		public ExpressionContext base;
		public ExpressionContext exponent;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(STParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(STParser.MUL, i);
		}
		public PowerContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterPower(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitPower(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitPower(this);
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
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				_localctx = new PrimaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(142);
				primary_expression();
				}
				break;
			case 2:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(143);
				((NotContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__14 || _la==T__15) ) {
					((NotContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(144);
				expression(9);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(175);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(173);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new PowerContext(new ExpressionContext(_parentctx, _parentState));
						((PowerContext)_localctx).base = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(147);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						{
						setState(148);
						match(MUL);
						setState(149);
						match(MUL);
						}
						setState(151);
						((PowerContext)_localctx).exponent = expression(9);
						}
						break;
					case 2:
						{
						_localctx = new MulDivModContext(new ExpressionContext(_parentctx, _parentState));
						((MulDivModContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(152);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(153);
						((MulDivModContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
							((MulDivModContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(154);
						((MulDivModContext)_localctx).right = expression(8);
						}
						break;
					case 3:
						{
						_localctx = new AddSubContext(new ExpressionContext(_parentctx, _parentState));
						((AddSubContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(155);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(156);
						((AddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__14 || _la==ADD) ) {
							((AddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(157);
						((AddSubContext)_localctx).right = expression(7);
						}
						break;
					case 4:
						{
						_localctx = new ComparisonContext(new ExpressionContext(_parentctx, _parentState));
						((ComparisonContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(158);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(159);
						((ComparisonContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << GTE) | (1L << LTE))) != 0)) ) {
							((ComparisonContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(160);
						((ComparisonContext)_localctx).right = expression(6);
						}
						break;
					case 5:
						{
						_localctx = new ComparisonContext(new ExpressionContext(_parentctx, _parentState));
						((ComparisonContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(161);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(162);
						((ComparisonContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQ || _la==NE) ) {
							((ComparisonContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(163);
						((ComparisonContext)_localctx).right = expression(5);
						}
						break;
					case 6:
						{
						_localctx = new LogicContext(new ExpressionContext(_parentctx, _parentState));
						((LogicContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(164);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(165);
						((LogicContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__16 || _la==T__17) ) {
							((LogicContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(166);
						((LogicContext)_localctx).right = expression(4);
						}
						break;
					case 7:
						{
						_localctx = new LogicContext(new ExpressionContext(_parentctx, _parentState));
						((LogicContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(167);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						{
						setState(168);
						match(T__18);
						}
						setState(169);
						((LogicContext)_localctx).right = expression(3);
						}
						break;
					case 8:
						{
						_localctx = new LogicContext(new ExpressionContext(_parentctx, _parentState));
						((LogicContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(170);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						{
						setState(171);
						match(OR);
						}
						setState(172);
						((LogicContext)_localctx).right = expression(2);
						}
						break;
					}
					} 
				}
				setState(177);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
		public Primary_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary_expression; }
	 
		public Primary_expressionContext() { }
		public void copyFrom(Primary_expressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParenthesisExprContext extends Primary_expressionContext {
		public TerminalNode LP() { return getToken(STParser.LP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenthesisExprContext(Primary_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterParenthesisExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitParenthesisExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitParenthesisExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VaribaleContext extends Primary_expressionContext {
		public Token variable;
		public TerminalNode ID() { return getToken(STParser.ID, 0); }
		public VaribaleContext(Primary_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterVaribale(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitVaribale(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitVaribale(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConstContext extends Primary_expressionContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstContext(Primary_expressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterConst(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitConst(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitConst(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Primary_expressionContext primary_expression() throws RecognitionException {
		Primary_expressionContext _localctx = new Primary_expressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_primary_expression);
		try {
			setState(184);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__14:
			case T__46:
			case T__47:
			case Binary_literal:
			case Octal_literal:
			case Decimal_literal:
			case Pure_decimal_digits:
			case Hexadecimal_literal:
			case Floating_point_literal:
			case Static_string_literal:
				_localctx = new ConstContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(178);
				constant();
				}
				break;
			case ID:
				_localctx = new VaribaleContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(179);
				((VaribaleContext)_localctx).variable = match(ID);
				}
				break;
			case LP:
				_localctx = new ParenthesisExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(180);
				match(LP);
				setState(181);
				expression(0);
				setState(182);
				match(T__19);
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

	public static class Var_blockContext extends ParserRuleContext {
		public Variable_declarationContext variable_declaration;
		public List<Variable_declarationContext> variables = new ArrayList<Variable_declarationContext>();
		public Var_typeContext var_type() {
			return getRuleContext(Var_typeContext.class,0);
		}
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
			if ( listener instanceof STListener ) ((STListener)listener).enterVar_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitVar_block(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitVar_block(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_blockContext var_block() throws RecognitionException {
		Var_blockContext _localctx = new Var_blockContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_var_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			var_type();
			{
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(187);
				((Var_blockContext)_localctx).variable_declaration = variable_declaration();
				((Var_blockContext)_localctx).variables.add(((Var_blockContext)_localctx).variable_declaration);
				}
				}
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(193);
			match(T__20);
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

	public static class Var_typeContext extends ParserRuleContext {
		public Var_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterVar_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitVar_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitVar_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_typeContext var_type() throws RecognitionException {
		Var_typeContext _localctx = new Var_typeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_var_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) ) {
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
		public Elementary_type_nameContext name;
		public Elementary_type_nameContext elementary_type_name() {
			return getRuleContext(Elementary_type_nameContext.class,0);
		}
		public SimpleTypeContext(Type_ruleContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterSimpleType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitSimpleType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitSimpleType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_ruleContext type_rule() throws RecognitionException {
		Type_ruleContext _localctx = new Type_ruleContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_type_rule);
		try {
			_localctx = new SimpleTypeContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			((SimpleTypeContext)_localctx).name = elementary_type_name();
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
		public Variable_initializerContext initializer;
		public List<TerminalNode> ID() { return getTokens(STParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(STParser.ID, i);
		}
		public Type_ruleContext type_rule() {
			return getRuleContext(Type_ruleContext.class,0);
		}
		public Variable_initializerContext variable_initializer() {
			return getRuleContext(Variable_initializerContext.class,0);
		}
		public Variable_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterVariable_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitVariable_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitVariable_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_declarationContext variable_declaration() throws RecognitionException {
		Variable_declarationContext _localctx = new Variable_declarationContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_variable_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			((Variable_declarationContext)_localctx).ID = match(ID);
			((Variable_declarationContext)_localctx).names.add(((Variable_declarationContext)_localctx).ID);
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__25) {
				{
				{
				setState(200);
				match(T__25);
				setState(201);
				((Variable_declarationContext)_localctx).ID = match(ID);
				((Variable_declarationContext)_localctx).names.add(((Variable_declarationContext)_localctx).ID);
				}
				}
				setState(206);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(207);
			match(T__5);
			setState(208);
			((Variable_declarationContext)_localctx).type = type_rule();
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(209);
				match(T__7);
				setState(210);
				((Variable_declarationContext)_localctx).initializer = variable_initializer();
				}
			}

			setState(213);
			match(T__8);
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
			if ( listener instanceof STListener ) ((STListener)listener).enterElementary_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitElementary_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitElementary_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Elementary_type_nameContext elementary_type_name() throws RecognitionException {
		Elementary_type_nameContext _localctx = new Elementary_type_nameContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_elementary_type_name);
		try {
			setState(218);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__26:
			case T__27:
			case T__28:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
				enterOuterAlt(_localctx, 1);
				{
				setState(215);
				numeric_type_name();
				}
				break;
			case T__36:
			case T__37:
			case T__38:
			case T__39:
			case T__40:
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				date_type_name();
				}
				break;
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
				enterOuterAlt(_localctx, 3);
				{
				setState(217);
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
			if ( listener instanceof STListener ) ((STListener)listener).enterNumeric_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitNumeric_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitNumeric_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numeric_type_nameContext numeric_type_name() throws RecognitionException {
		Numeric_type_nameContext _localctx = new Numeric_type_nameContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_numeric_type_name);
		try {
			setState(222);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__26:
			case T__27:
			case T__28:
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
				enterOuterAlt(_localctx, 1);
				{
				setState(220);
				integer_type_name();
				}
				break;
			case T__34:
			case T__35:
				enterOuterAlt(_localctx, 2);
				{
				setState(221);
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
			if ( listener instanceof STListener ) ((STListener)listener).enterInteger_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitInteger_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitInteger_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Integer_type_nameContext integer_type_name() throws RecognitionException {
		Integer_type_nameContext _localctx = new Integer_type_nameContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_integer_type_name);
		try {
			setState(226);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__26:
			case T__27:
			case T__28:
			case T__29:
				enterOuterAlt(_localctx, 1);
				{
				setState(224);
				signed_integer_type_name();
				}
				break;
			case T__30:
			case T__31:
			case T__32:
			case T__33:
				enterOuterAlt(_localctx, 2);
				{
				setState(225);
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
		public Signed_integer_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signed_integer_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterSigned_integer_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitSigned_integer_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitSigned_integer_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Signed_integer_type_nameContext signed_integer_type_name() throws RecognitionException {
		Signed_integer_type_nameContext _localctx = new Signed_integer_type_nameContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_signed_integer_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29))) != 0)) ) {
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
		public Unsigned_integer_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsigned_integer_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterUnsigned_integer_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitUnsigned_integer_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitUnsigned_integer_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unsigned_integer_type_nameContext unsigned_integer_type_name() throws RecognitionException {
		Unsigned_integer_type_nameContext _localctx = new Unsigned_integer_type_nameContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_unsigned_integer_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33))) != 0)) ) {
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
		public Real_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_real_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterReal_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitReal_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitReal_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Real_type_nameContext real_type_name() throws RecognitionException {
		Real_type_nameContext _localctx = new Real_type_nameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_real_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			_la = _input.LA(1);
			if ( !(_la==T__34 || _la==T__35) ) {
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
		public Date_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterDate_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitDate_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitDate_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Date_type_nameContext date_type_name() throws RecognitionException {
		Date_type_nameContext _localctx = new Date_type_nameContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_date_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39) | (1L << T__40))) != 0)) ) {
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
		public Bit_string_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bit_string_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterBit_string_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitBit_string_type_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitBit_string_type_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bit_string_type_nameContext bit_string_type_name() throws RecognitionException {
		Bit_string_type_nameContext _localctx = new Bit_string_type_nameContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_bit_string_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45))) != 0)) ) {
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
		public Variable_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterVariable_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitVariable_initializer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitVariable_initializer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_initializerContext variable_initializer() throws RecognitionException {
		Variable_initializerContext _localctx = new Variable_initializerContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_variable_initializer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			constant();
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
		public Boolean_literalContext boolean_literal() {
			return getRuleContext(Boolean_literalContext.class,0);
		}
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_constant);
		try {
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__14:
			case Binary_literal:
			case Octal_literal:
			case Decimal_literal:
			case Pure_decimal_digits:
			case Hexadecimal_literal:
			case Floating_point_literal:
				enterOuterAlt(_localctx, 1);
				{
				setState(240);
				numeric_literal();
				}
				break;
			case Static_string_literal:
				enterOuterAlt(_localctx, 2);
				{
				setState(241);
				string_literal();
				}
				break;
			case T__46:
			case T__47:
				enterOuterAlt(_localctx, 3);
				{
				setState(242);
				boolean_literal();
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

	public static class Boolean_literalContext extends ParserRuleContext {
		public Boolean_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterBoolean_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitBoolean_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitBoolean_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Boolean_literalContext boolean_literal() throws RecognitionException {
		Boolean_literalContext _localctx = new Boolean_literalContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_boolean_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			_la = _input.LA(1);
			if ( !(_la==T__46 || _la==T__47) ) {
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

	public static class Numeric_literalContext extends ParserRuleContext {
		public Integer_literalContext integer_literal() {
			return getRuleContext(Integer_literalContext.class,0);
		}
		public TerminalNode Floating_point_literal() { return getToken(STParser.Floating_point_literal, 0); }
		public Numeric_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).enterNumeric_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitNumeric_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitNumeric_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numeric_literalContext numeric_literal() throws RecognitionException {
		Numeric_literalContext _localctx = new Numeric_literalContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_numeric_literal);
		int _la;
		try {
			setState(255);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__14) {
					{
					setState(247);
					match(T__14);
					}
				}

				setState(250);
				integer_literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__14) {
					{
					setState(251);
					match(T__14);
					}
				}

				setState(254);
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
			if ( listener instanceof STListener ) ((STListener)listener).enterInteger_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitInteger_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitInteger_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Integer_literalContext integer_literal() throws RecognitionException {
		Integer_literalContext _localctx = new Integer_literalContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_integer_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			_la = _input.LA(1);
			if ( !(((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (Binary_literal - 66)) | (1L << (Octal_literal - 66)) | (1L << (Decimal_literal - 66)) | (1L << (Pure_decimal_digits - 66)) | (1L << (Hexadecimal_literal - 66)))) != 0)) ) {
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
			if ( listener instanceof STListener ) ((STListener)listener).enterString_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof STListener ) ((STListener)listener).exitString_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof STVisitor ) return ((STVisitor<? extends T>)visitor).visitString_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final String_literalContext string_literal() throws RecognitionException {
		String_literalContext _localctx = new String_literalContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_string_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
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
		case 9:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 2);
		case 7:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3M\u0108\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\7\2>\n\2\f\2\16"+
		"\2A\13\2\3\3\3\3\3\3\5\3F\n\3\3\4\3\4\3\4\7\4K\n\4\f\4\16\4N\13\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\7\5V\n\5\f\5\16\5Y\13\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\6\7\6c\n\6\f\6\16\6f\13\6\3\6\3\6\3\6\3\7\3\7\7\7m\n\7\f\7\16\7p"+
		"\13\7\3\b\3\b\5\bt\n\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\7\n\u0084\n\n\f\n\16\n\u0087\13\n\3\n\3\n\5\n\u008b\n\n\3\n\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\5\13\u0094\n\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00b0\n\13\f\13\16\13\u00b3\13"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00bb\n\f\3\r\3\r\7\r\u00bf\n\r\f\r\16"+
		"\r\u00c2\13\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\7\20\u00cd\n"+
		"\20\f\20\16\20\u00d0\13\20\3\20\3\20\3\20\3\20\5\20\u00d6\n\20\3\20\3"+
		"\20\3\21\3\21\3\21\5\21\u00dd\n\21\3\22\3\22\5\22\u00e1\n\22\3\23\3\23"+
		"\5\23\u00e5\n\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\32\3\32\3\32\5\32\u00f6\n\32\3\33\3\33\3\34\5\34\u00fb\n\34\3"+
		"\34\3\34\5\34\u00ff\n\34\3\34\5\34\u0102\n\34\3\35\3\35\3\36\3\36\3\36"+
		"\2\3\24\37\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66"+
		"8:\2\20\3\2\21\22\3\2\668\4\2\21\2199\4\2;<>?\4\2==@@\3\2\23\24\3\2\30"+
		"\33\3\2\35 \3\2!$\3\2%&\3\2\'+\3\2,\60\3\2\61\62\3\2DH\2\u010b\2?\3\2"+
		"\2\2\4E\3\2\2\2\6G\3\2\2\2\bR\3\2\2\2\n]\3\2\2\2\fj\3\2\2\2\16s\3\2\2"+
		"\2\20u\3\2\2\2\22z\3\2\2\2\24\u0093\3\2\2\2\26\u00ba\3\2\2\2\30\u00bc"+
		"\3\2\2\2\32\u00c5\3\2\2\2\34\u00c7\3\2\2\2\36\u00c9\3\2\2\2 \u00dc\3\2"+
		"\2\2\"\u00e0\3\2\2\2$\u00e4\3\2\2\2&\u00e6\3\2\2\2(\u00e8\3\2\2\2*\u00ea"+
		"\3\2\2\2,\u00ec\3\2\2\2.\u00ee\3\2\2\2\60\u00f0\3\2\2\2\62\u00f5\3\2\2"+
		"\2\64\u00f7\3\2\2\2\66\u0101\3\2\2\28\u0103\3\2\2\2:\u0105\3\2\2\2<>\5"+
		"\4\3\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@\3\3\2\2\2A?\3\2\2\2BF"+
		"\5\6\4\2CF\5\b\5\2DF\5\n\6\2EB\3\2\2\2EC\3\2\2\2ED\3\2\2\2F\5\3\2\2\2"+
		"GH\7\3\2\2HL\7K\2\2IK\5\30\r\2JI\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2"+
		"MO\3\2\2\2NL\3\2\2\2OP\5\f\7\2PQ\7\4\2\2Q\7\3\2\2\2RS\7\5\2\2SW\7K\2\2"+
		"TV\5\30\r\2UT\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2\2\2XZ\3\2\2\2YW\3\2\2"+
		"\2Z[\5\f\7\2[\\\7\6\2\2\\\t\3\2\2\2]^\7\7\2\2^_\7K\2\2_`\7\b\2\2`d\5\34"+
		"\17\2ac\5\30\r\2ba\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2eg\3\2\2\2fd\3"+
		"\2\2\2gh\5\f\7\2hi\7\t\2\2i\13\3\2\2\2jn\5\16\b\2km\5\16\b\2lk\3\2\2\2"+
		"mp\3\2\2\2nl\3\2\2\2no\3\2\2\2o\r\3\2\2\2pn\3\2\2\2qt\5\20\t\2rt\5\22"+
		"\n\2sq\3\2\2\2sr\3\2\2\2t\17\3\2\2\2uv\7K\2\2vw\7\n\2\2wx\5\24\13\2xy"+
		"\7\13\2\2y\21\3\2\2\2z{\7\f\2\2{|\5\24\13\2|}\7\r\2\2}\u0085\5\f\7\2~"+
		"\177\7\16\2\2\177\u0080\5\24\13\2\u0080\u0081\7\r\2\2\u0081\u0082\5\f"+
		"\7\2\u0082\u0084\3\2\2\2\u0083~\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u008a\3\2\2\2\u0087\u0085\3\2\2\2\u0088"+
		"\u0089\7\17\2\2\u0089\u008b\5\f\7\2\u008a\u0088\3\2\2\2\u008a\u008b\3"+
		"\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\7\20\2\2\u008d\u008e\7\13\2\2\u008e"+
		"\23\3\2\2\2\u008f\u0090\b\13\1\2\u0090\u0094\5\26\f\2\u0091\u0092\t\2"+
		"\2\2\u0092\u0094\5\24\13\13\u0093\u008f\3\2\2\2\u0093\u0091\3\2\2\2\u0094"+
		"\u00b1\3\2\2\2\u0095\u0096\f\n\2\2\u0096\u0097\7\66\2\2\u0097\u0098\7"+
		"\66\2\2\u0098\u0099\3\2\2\2\u0099\u00b0\5\24\13\13\u009a\u009b\f\t\2\2"+
		"\u009b\u009c\t\3\2\2\u009c\u00b0\5\24\13\n\u009d\u009e\f\b\2\2\u009e\u009f"+
		"\t\4\2\2\u009f\u00b0\5\24\13\t\u00a0\u00a1\f\7\2\2\u00a1\u00a2\t\5\2\2"+
		"\u00a2\u00b0\5\24\13\b\u00a3\u00a4\f\6\2\2\u00a4\u00a5\t\6\2\2\u00a5\u00b0"+
		"\5\24\13\7\u00a6\u00a7\f\5\2\2\u00a7\u00a8\t\7\2\2\u00a8\u00b0\5\24\13"+
		"\6\u00a9\u00aa\f\4\2\2\u00aa\u00ab\7\25\2\2\u00ab\u00b0\5\24\13\5\u00ac"+
		"\u00ad\f\3\2\2\u00ad\u00ae\7C\2\2\u00ae\u00b0\5\24\13\4\u00af\u0095\3"+
		"\2\2\2\u00af\u009a\3\2\2\2\u00af\u009d\3\2\2\2\u00af\u00a0\3\2\2\2\u00af"+
		"\u00a3\3\2\2\2\u00af\u00a6\3\2\2\2\u00af\u00a9\3\2\2\2\u00af\u00ac\3\2"+
		"\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2"+
		"\25\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00bb\5\62\32\2\u00b5\u00bb\7K\2"+
		"\2\u00b6\u00b7\7\63\2\2\u00b7\u00b8\5\24\13\2\u00b8\u00b9\7\26\2\2\u00b9"+
		"\u00bb\3\2\2\2\u00ba\u00b4\3\2\2\2\u00ba\u00b5\3\2\2\2\u00ba\u00b6\3\2"+
		"\2\2\u00bb\27\3\2\2\2\u00bc\u00c0\5\32\16\2\u00bd\u00bf\5\36\20\2\u00be"+
		"\u00bd\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2"+
		"\2\2\u00c1\u00c3\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3\u00c4\7\27\2\2\u00c4"+
		"\31\3\2\2\2\u00c5\u00c6\t\b\2\2\u00c6\33\3\2\2\2\u00c7\u00c8\5 \21\2\u00c8"+
		"\35\3\2\2\2\u00c9\u00ce\7K\2\2\u00ca\u00cb\7\34\2\2\u00cb\u00cd\7K\2\2"+
		"\u00cc\u00ca\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf"+
		"\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00d2\7\b\2\2\u00d2"+
		"\u00d5\5\34\17\2\u00d3\u00d4\7\n\2\2\u00d4\u00d6\5\60\31\2\u00d5\u00d3"+
		"\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\7\13\2\2"+
		"\u00d8\37\3\2\2\2\u00d9\u00dd\5\"\22\2\u00da\u00dd\5,\27\2\u00db\u00dd"+
		"\5.\30\2\u00dc\u00d9\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00db\3\2\2\2\u00dd"+
		"!\3\2\2\2\u00de\u00e1\5$\23\2\u00df\u00e1\5*\26\2\u00e0\u00de\3\2\2\2"+
		"\u00e0\u00df\3\2\2\2\u00e1#\3\2\2\2\u00e2\u00e5\5&\24\2\u00e3\u00e5\5"+
		"(\25\2\u00e4\u00e2\3\2\2\2\u00e4\u00e3\3\2\2\2\u00e5%\3\2\2\2\u00e6\u00e7"+
		"\t\t\2\2\u00e7\'\3\2\2\2\u00e8\u00e9\t\n\2\2\u00e9)\3\2\2\2\u00ea\u00eb"+
		"\t\13\2\2\u00eb+\3\2\2\2\u00ec\u00ed\t\f\2\2\u00ed-\3\2\2\2\u00ee\u00ef"+
		"\t\r\2\2\u00ef/\3\2\2\2\u00f0\u00f1\5\62\32\2\u00f1\61\3\2\2\2\u00f2\u00f6"+
		"\5\66\34\2\u00f3\u00f6\5:\36\2\u00f4\u00f6\5\64\33\2\u00f5\u00f2\3\2\2"+
		"\2\u00f5\u00f3\3\2\2\2\u00f5\u00f4\3\2\2\2\u00f6\63\3\2\2\2\u00f7\u00f8"+
		"\t\16\2\2\u00f8\65\3\2\2\2\u00f9\u00fb\7\21\2\2\u00fa\u00f9\3\2\2\2\u00fa"+
		"\u00fb\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u0102\58\35\2\u00fd\u00ff\7\21"+
		"\2\2\u00fe\u00fd\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\3\2\2\2\u0100"+
		"\u0102\7I\2\2\u0101\u00fa\3\2\2\2\u0101\u00fe\3\2\2\2\u0102\67\3\2\2\2"+
		"\u0103\u0104\t\17\2\2\u01049\3\2\2\2\u0105\u0106\7J\2\2\u0106;\3\2\2\2"+
		"\31?ELWdns\u0085\u008a\u0093\u00af\u00b1\u00ba\u00c0\u00ce\u00d5\u00dc"+
		"\u00e0\u00e4\u00f5\u00fa\u00fe\u0101";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}