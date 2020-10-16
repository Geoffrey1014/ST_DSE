// Generated from /Users/heweigang/MY_project/ST_DSE/src/grammar/STParser.g4 by ANTLR 4.8
package grammar.gen;


import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link STParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface STParserVisitor<T> extends ParseTreeVisitor<T> {
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
	 * Visit a parse tree produced by {@link STParser#if_else_stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_else_stat(STParser.If_else_statContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#if_elsif_stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_elsif_stat(STParser.If_elsif_statContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#if_elsif_else_stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_elsif_else_stat(STParser.If_elsif_else_statContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#if_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stmt(STParser.If_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#elsif_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElsif_stmt(STParser.Elsif_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#else_stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse_stmt(STParser.Else_stmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#for_stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_stat(STParser.For_statContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#for_range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_range(STParser.For_rangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#while_stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stat(STParser.While_statContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#invoc_stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvoc_stat(STParser.Invoc_statContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExternArg}
	 * labeled alternative in {@link STParser#param_assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExternArg(STParser.ExternArgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignParam}
	 * labeled alternative in {@link STParser#param_assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignParam(STParser.AssignParamContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignOutput}
	 * labeled alternative in {@link STParser#param_assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignOutput(STParser.AssignOutputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArithExpr}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithExpr(STParser.ArithExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Comparison}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(STParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenExper}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExper(STParser.ParenExperContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrimaryExpr}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(STParser.PrimaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(STParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Logic}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic(STParser.LogicContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NegateExpr}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegateExpr(STParser.NegateExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#primary_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary_expression(STParser.Primary_expressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarLocation}
	 * labeled alternative in {@link STParser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarLocation(STParser.VarLocationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArrayLocation}
	 * labeled alternative in {@link STParser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLocation(STParser.ArrayLocationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FbLcation}
	 * labeled alternative in {@link STParser#location}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFbLcation(STParser.FbLcationContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#var_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_block(STParser.Var_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#var_acc_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_acc_type(STParser.Var_acc_typeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleType}
	 * labeled alternative in {@link STParser#type_rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleType(STParser.SimpleTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link STParser#type_rule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(STParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#array_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_type(STParser.Array_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRange(STParser.RangeContext ctx);
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
	 * Visit a parse tree produced by {@link STParser#array_initialization}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_initialization(STParser.Array_initializationContext ctx);
	/**
	 * Visit a parse tree produced by {@link STParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(STParser.ConstantContext ctx);
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