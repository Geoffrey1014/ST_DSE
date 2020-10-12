// Generated from /Users/heweigang/MY_project/ST_DSE/src/grammar/ST.g4 by ANTLR 4.8
package gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link STParser}.
 */
public interface STListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link STParser#pous}.
	 * @param ctx the parse tree
	 */
	void enterPous(STParser.PousContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#pous}.
	 * @param ctx the parse tree
	 */
	void exitPous(STParser.PousContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#pou}.
	 * @param ctx the parse tree
	 */
	void enterPou(STParser.PouContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#pou}.
	 * @param ctx the parse tree
	 */
	void exitPou(STParser.PouContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(STParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(STParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#function_block}.
	 * @param ctx the parse tree
	 */
	void enterFunction_block(STParser.Function_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#function_block}.
	 * @param ctx the parse tree
	 */
	void exitFunction_block(STParser.Function_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(STParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(STParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#stat_list}.
	 * @param ctx the parse tree
	 */
	void enterStat_list(STParser.Stat_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#stat_list}.
	 * @param ctx the parse tree
	 */
	void exitStat_list(STParser.Stat_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(STParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(STParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#assign_stat}.
	 * @param ctx the parse tree
	 */
	void enterAssign_stat(STParser.Assign_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#assign_stat}.
	 * @param ctx the parse tree
	 */
	void exitAssign_stat(STParser.Assign_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#if_stat}.
	 * @param ctx the parse tree
	 */
	void enterIf_stat(STParser.If_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#if_stat}.
	 * @param ctx the parse tree
	 */
	void exitIf_stat(STParser.If_statContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Not}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNot(STParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNot(STParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDivMod}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulDivMod(STParser.MulDivModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDivMod}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulDivMod(STParser.MulDivModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(STParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(STParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Comparison}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterComparison(STParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Comparison}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitComparison(STParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryExpr}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpr(STParser.PrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryExpr}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpr(STParser.PrimaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Logic}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogic(STParser.LogicContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Logic}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogic(STParser.LogicContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Power}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPower(STParser.PowerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Power}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPower(STParser.PowerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Const}
	 * labeled alternative in {@link STParser#primary_expression}.
	 * @param ctx the parse tree
	 */
	void enterConst(STParser.ConstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Const}
	 * labeled alternative in {@link STParser#primary_expression}.
	 * @param ctx the parse tree
	 */
	void exitConst(STParser.ConstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Varibale}
	 * labeled alternative in {@link STParser#primary_expression}.
	 * @param ctx the parse tree
	 */
	void enterVaribale(STParser.VaribaleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Varibale}
	 * labeled alternative in {@link STParser#primary_expression}.
	 * @param ctx the parse tree
	 */
	void exitVaribale(STParser.VaribaleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenthesisExpr}
	 * labeled alternative in {@link STParser#primary_expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesisExpr(STParser.ParenthesisExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenthesisExpr}
	 * labeled alternative in {@link STParser#primary_expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesisExpr(STParser.ParenthesisExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#var_block}.
	 * @param ctx the parse tree
	 */
	void enterVar_block(STParser.Var_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#var_block}.
	 * @param ctx the parse tree
	 */
	void exitVar_block(STParser.Var_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#var_type}.
	 * @param ctx the parse tree
	 */
	void enterVar_type(STParser.Var_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#var_type}.
	 * @param ctx the parse tree
	 */
	void exitVar_type(STParser.Var_typeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleType}
	 * labeled alternative in {@link STParser#type_rule}.
	 * @param ctx the parse tree
	 */
	void enterSimpleType(STParser.SimpleTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleType}
	 * labeled alternative in {@link STParser#type_rule}.
	 * @param ctx the parse tree
	 */
	void exitSimpleType(STParser.SimpleTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVariable_declaration(STParser.Variable_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#variable_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVariable_declaration(STParser.Variable_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#elementary_type_name}.
	 * @param ctx the parse tree
	 */
	void enterElementary_type_name(STParser.Elementary_type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#elementary_type_name}.
	 * @param ctx the parse tree
	 */
	void exitElementary_type_name(STParser.Elementary_type_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#numeric_type_name}.
	 * @param ctx the parse tree
	 */
	void enterNumeric_type_name(STParser.Numeric_type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#numeric_type_name}.
	 * @param ctx the parse tree
	 */
	void exitNumeric_type_name(STParser.Numeric_type_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#integer_type_name}.
	 * @param ctx the parse tree
	 */
	void enterInteger_type_name(STParser.Integer_type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#integer_type_name}.
	 * @param ctx the parse tree
	 */
	void exitInteger_type_name(STParser.Integer_type_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#signed_integer_type_name}.
	 * @param ctx the parse tree
	 */
	void enterSigned_integer_type_name(STParser.Signed_integer_type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#signed_integer_type_name}.
	 * @param ctx the parse tree
	 */
	void exitSigned_integer_type_name(STParser.Signed_integer_type_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#unsigned_integer_type_name}.
	 * @param ctx the parse tree
	 */
	void enterUnsigned_integer_type_name(STParser.Unsigned_integer_type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#unsigned_integer_type_name}.
	 * @param ctx the parse tree
	 */
	void exitUnsigned_integer_type_name(STParser.Unsigned_integer_type_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#real_type_name}.
	 * @param ctx the parse tree
	 */
	void enterReal_type_name(STParser.Real_type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#real_type_name}.
	 * @param ctx the parse tree
	 */
	void exitReal_type_name(STParser.Real_type_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#date_type_name}.
	 * @param ctx the parse tree
	 */
	void enterDate_type_name(STParser.Date_type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#date_type_name}.
	 * @param ctx the parse tree
	 */
	void exitDate_type_name(STParser.Date_type_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#bit_string_type_name}.
	 * @param ctx the parse tree
	 */
	void enterBit_string_type_name(STParser.Bit_string_type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#bit_string_type_name}.
	 * @param ctx the parse tree
	 */
	void exitBit_string_type_name(STParser.Bit_string_type_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#variable_initializer}.
	 * @param ctx the parse tree
	 */
	void enterVariable_initializer(STParser.Variable_initializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#variable_initializer}.
	 * @param ctx the parse tree
	 */
	void exitVariable_initializer(STParser.Variable_initializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(STParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(STParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#boolean_literal}.
	 * @param ctx the parse tree
	 */
	void enterBoolean_literal(STParser.Boolean_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#boolean_literal}.
	 * @param ctx the parse tree
	 */
	void exitBoolean_literal(STParser.Boolean_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#numeric_literal}.
	 * @param ctx the parse tree
	 */
	void enterNumeric_literal(STParser.Numeric_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#numeric_literal}.
	 * @param ctx the parse tree
	 */
	void exitNumeric_literal(STParser.Numeric_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#integer_literal}.
	 * @param ctx the parse tree
	 */
	void enterInteger_literal(STParser.Integer_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#integer_literal}.
	 * @param ctx the parse tree
	 */
	void exitInteger_literal(STParser.Integer_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#string_literal}.
	 * @param ctx the parse tree
	 */
	void enterString_literal(STParser.String_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#string_literal}.
	 * @param ctx the parse tree
	 */
	void exitString_literal(STParser.String_literalContext ctx);
}