// Generated from /Users/heweigang/MY_project/ST_DSE/src/grammar/ST.g4 by ANTLR 4.8
package gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link STParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface STVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link STParser#pous}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPous(STParser.PousContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#pou}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPou(STParser.PouContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(STParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#function_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_block(STParser.Function_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(STParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#stat_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat_list(STParser.Stat_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(STParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#assign_stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign_stat(STParser.Assign_statContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#if_stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stat(STParser.If_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Not}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(STParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDivMod}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivMod(STParser.MulDivModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(STParser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Comparison}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(STParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryExpr}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(STParser.PrimaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Logic}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic(STParser.LogicContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Power}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPower(STParser.PowerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Const}
	 * labeled alternative in {@link STParser#primary_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConst(STParser.ConstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Varibale}
	 * labeled alternative in {@link STParser#primary_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVaribale(STParser.VaribaleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenthesisExpr}
	 * labeled alternative in {@link STParser#primary_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesisExpr(STParser.ParenthesisExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#var_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_block(STParser.Var_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#var_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_type(STParser.Var_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleType}
	 * labeled alternative in {@link STParser#type_rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleType(STParser.SimpleTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#variable_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_declaration(STParser.Variable_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#elementary_type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementary_type_name(STParser.Elementary_type_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#numeric_type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric_type_name(STParser.Numeric_type_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#integer_type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger_type_name(STParser.Integer_type_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#signed_integer_type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSigned_integer_type_name(STParser.Signed_integer_type_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#unsigned_integer_type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsigned_integer_type_name(STParser.Unsigned_integer_type_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#real_type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReal_type_name(STParser.Real_type_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#date_type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate_type_name(STParser.Date_type_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#bit_string_type_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBit_string_type_name(STParser.Bit_string_type_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#variable_initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_initializer(STParser.Variable_initializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(STParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#boolean_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean_literal(STParser.Boolean_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#numeric_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumeric_literal(STParser.Numeric_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#integer_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger_literal(STParser.Integer_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#string_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_literal(STParser.String_literalContext ctx);
}