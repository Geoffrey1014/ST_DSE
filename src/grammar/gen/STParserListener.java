// Generated from /Users/heweigang/MY_project/ST_DSE/src/grammar/STParser.g4 by ANTLR 4.8
package grammar.gen;


import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link STParser}.
 */
public interface STParserListener extends ParseTreeListener {
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
	 * Enter a parse tree produced by {@link STParser#invoc_stat}.
	 * @param ctx the parse tree
	 */
	void enterInvoc_stat(STParser.Invoc_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#invoc_stat}.
	 * @param ctx the parse tree
	 */
	void exitInvoc_stat(STParser.Invoc_statContext ctx);
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
	 * Enter a parse tree produced by {@link STParser#if_else_stat}.
	 * @param ctx the parse tree
	 */
	void enterIf_else_stat(STParser.If_else_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#if_else_stat}.
	 * @param ctx the parse tree
	 */
	void exitIf_else_stat(STParser.If_else_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#if_elsif_stat}.
	 * @param ctx the parse tree
	 */
	void enterIf_elsif_stat(STParser.If_elsif_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#if_elsif_stat}.
	 * @param ctx the parse tree
	 */
	void exitIf_elsif_stat(STParser.If_elsif_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#if_elsif_else_stat}.
	 * @param ctx the parse tree
	 */
	void enterIf_elsif_else_stat(STParser.If_elsif_else_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#if_elsif_else_stat}.
	 * @param ctx the parse tree
	 */
	void exitIf_elsif_else_stat(STParser.If_elsif_else_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void enterIf_stmt(STParser.If_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#if_stmt}.
	 * @param ctx the parse tree
	 */
	void exitIf_stmt(STParser.If_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#elsif_stmt}.
	 * @param ctx the parse tree
	 */
	void enterElsif_stmt(STParser.Elsif_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#elsif_stmt}.
	 * @param ctx the parse tree
	 */
	void exitElsif_stmt(STParser.Elsif_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#else_stmt}.
	 * @param ctx the parse tree
	 */
	void enterElse_stmt(STParser.Else_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#else_stmt}.
	 * @param ctx the parse tree
	 */
	void exitElse_stmt(STParser.Else_stmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#for_stat}.
	 * @param ctx the parse tree
	 */
	void enterFor_stat(STParser.For_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#for_stat}.
	 * @param ctx the parse tree
	 */
	void exitFor_stat(STParser.For_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#for_range}.
	 * @param ctx the parse tree
	 */
	void enterFor_range(STParser.For_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#for_range}.
	 * @param ctx the parse tree
	 */
	void exitFor_range(STParser.For_rangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#while_stat}.
	 * @param ctx the parse tree
	 */
	void enterWhile_stat(STParser.While_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#while_stat}.
	 * @param ctx the parse tree
	 */
	void exitWhile_stat(STParser.While_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#invoc_expr}.
	 * @param ctx the parse tree
	 */
	void enterInvoc_expr(STParser.Invoc_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#invoc_expr}.
	 * @param ctx the parse tree
	 */
	void exitInvoc_expr(STParser.Invoc_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExternArg}
	 * labeled alternative in {@link STParser#param_assignment}.
	 * @param ctx the parse tree
	 */
	void enterExternArg(STParser.ExternArgContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExternArg}
	 * labeled alternative in {@link STParser#param_assignment}.
	 * @param ctx the parse tree
	 */
	void exitExternArg(STParser.ExternArgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignParam}
	 * labeled alternative in {@link STParser#param_assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignParam(STParser.AssignParamContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignParam}
	 * labeled alternative in {@link STParser#param_assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignParam(STParser.AssignParamContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignOutput}
	 * labeled alternative in {@link STParser#param_assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignOutput(STParser.AssignOutputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignOutput}
	 * labeled alternative in {@link STParser#param_assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignOutput(STParser.AssignOutputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithExpr}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArithExpr(STParser.ArithExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithExpr}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArithExpr(STParser.ArithExprContext ctx);
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
	 * Enter a parse tree produced by the {@code ParenExper}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExper(STParser.ParenExperContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenExper}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExper(STParser.ParenExperContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EquateExpr}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEquateExpr(STParser.EquateExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EquateExpr}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEquateExpr(STParser.EquateExprContext ctx);
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
	 * Enter a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(STParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotExpr}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(STParser.NotExprContext ctx);
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
	 * Enter a parse tree produced by the {@code NegateExpr}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNegateExpr(STParser.NegateExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NegateExpr}
	 * labeled alternative in {@link STParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNegateExpr(STParser.NegateExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#primary_expression}.
	 * @param ctx the parse tree
	 */
	void enterPrimary_expression(STParser.Primary_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#primary_expression}.
	 * @param ctx the parse tree
	 */
	void exitPrimary_expression(STParser.Primary_expressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarLocation}
	 * labeled alternative in {@link STParser#location}.
	 * @param ctx the parse tree
	 */
	void enterVarLocation(STParser.VarLocationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarLocation}
	 * labeled alternative in {@link STParser#location}.
	 * @param ctx the parse tree
	 */
	void exitVarLocation(STParser.VarLocationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayLocation}
	 * labeled alternative in {@link STParser#location}.
	 * @param ctx the parse tree
	 */
	void enterArrayLocation(STParser.ArrayLocationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayLocation}
	 * labeled alternative in {@link STParser#location}.
	 * @param ctx the parse tree
	 */
	void exitArrayLocation(STParser.ArrayLocationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FbLcation}
	 * labeled alternative in {@link STParser#location}.
	 * @param ctx the parse tree
	 */
	void enterFbLcation(STParser.FbLcationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FbLcation}
	 * labeled alternative in {@link STParser#location}.
	 * @param ctx the parse tree
	 */
	void exitFbLcation(STParser.FbLcationContext ctx);
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
	 * Enter a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link STParser#type_rule}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(STParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link STParser#type_rule}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(STParser.ArrayTypeContext ctx);
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
	 * Enter a parse tree produced by {@link STParser#string_type_name}.
	 * @param ctx the parse tree
	 */
	void enterString_type_name(STParser.String_type_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#string_type_name}.
	 * @param ctx the parse tree
	 */
	void exitString_type_name(STParser.String_type_nameContext ctx);
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
	 * Enter a parse tree produced by {@link STParser#array_initialization}.
	 * @param ctx the parse tree
	 */
	void enterArray_initialization(STParser.Array_initializationContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#array_initialization}.
	 * @param ctx the parse tree
	 */
	void exitArray_initialization(STParser.Array_initializationContext ctx);
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
	 * Enter a parse tree produced by {@link STParser#floating_point_literal}.
	 * @param ctx the parse tree
	 */
	void enterFloating_point_literal(STParser.Floating_point_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#floating_point_literal}.
	 * @param ctx the parse tree
	 */
	void exitFloating_point_literal(STParser.Floating_point_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#floating_point_fraction}.
	 * @param ctx the parse tree
	 */
	void enterFloating_point_fraction(STParser.Floating_point_fractionContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#floating_point_fraction}.
	 * @param ctx the parse tree
	 */
	void exitFloating_point_fraction(STParser.Floating_point_fractionContext ctx);
	/**
	 * Enter a parse tree produced by {@link STParser#decimal_exponent}.
	 * @param ctx the parse tree
	 */
	void enterDecimal_exponent(STParser.Decimal_exponentContext ctx);
	/**
	 * Exit a parse tree produced by {@link STParser#decimal_exponent}.
	 * @param ctx the parse tree
	 */
	void exitDecimal_exponent(STParser.Decimal_exponentContext ctx);
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