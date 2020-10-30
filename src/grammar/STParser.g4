parser grammar STParser;

options
{
    language = 'Java';
    tokenVocab = 'STScanner';
    superClass = 'Parser'; //class DecafParser extends Parser;
    //buildAST = true;
}
@parser::header{
}


pous : pou+ ;

pou: program
    | function_block
    | function
    ;

program :
    RES_PROGRAM name=ID
    var_blocks+=var_block*
    stat_list
    RES_END_PROGRAM
    ;

function_block:
    RES_FUNCTION_BLOCK ID
    var_blocks+=var_block*
    stat_list
    RES_END_FUNCTION_BLOCK
    ;

function: RES_FUNCTION ID COLON type_rule
    var_blocks+=var_block*
    stat_list
    RES_END_FUNCTION
    ;

stat_list : stat* ;

stat
    : assign_stat
    | if_stat
    | if_else_stat
    | if_elsif_stat
    | if_elsif_else_stat
//    | case_stat
    | for_stat
    | while_stat
//    | repeat_stat
    | exit_stat
    | return_stat
    | invoc_stat
    ;

invoc_stat : invoc_expr SEMI_COL ;
assign_stat : location AS_OP expression SEMI_COL;

//if_stat : RES_IF expression RES_THEN stat_list (RES_ELSIF expression RES_THEN stat_list)* (RES_ELSE stat_list)? RES_END_IF SEMI_COL ;
if_stat : if_stmt RES_END_IF SEMI_COL;
if_else_stat : if_stmt else_stmt RES_END_IF SEMI_COL;
if_elsif_stat: if_stmt elsif_stmt+ RES_END_IF SEMI_COL;
if_elsif_else_stat: if_stmt elsif_stmt+ else_stmt RES_END_IF SEMI_COL ;

if_stmt :  RES_IF expression RES_THEN stat_list;
elsif_stmt : RES_ELSIF expression RES_THEN stat_list;
else_stmt : RES_ELSE stat_list;

//case_stat : 'CASE' expression 'OF' integer_literal (',' integer_literal)* ':' stat_list  // TODO:   case_state可以泛化 interger_literal 成 subrange | signed_integer | enumerated_value
//        ( integer_literal (',' integer_literal)* ':' stat_list)*
//        ('ELSE' stat_list)?
//        'END_CASE' ';' ;
for_stat :  RES_FOR control_variable=ID AS_OP for_range RES_DO stat_list RES_END_FOR SEMI_COL ;
for_range : expression RES_TO expression (RES_BY step=expression)? ;

while_stat : RES_WHILE expression RES_DO stat_list RES_END_WHILE SEMI_COL ;
//repeat_stat : 'REPEAT' stat_list 'UNTIL' expression 'END_REPEAT' ';' ;
exit_stat : RES_EXIT ;
return_stat : RES_RETURN;


invoc_expr : fb_name=ID L_PAREN (param_assignment (COMMA param_assignment)* ) ? R_PAREN ;
param_assignment :
    expression # ExternArg
    | ID AS_OP expression # AssignParam
    | (NOT_OP ? ID RT_AS_OP ID) # AssignOutput
    ;


expression
    : primary_expression # PrimaryExpr
    | NOT_OP expression # NotExpr
    | SUB_OP expression # NegateExpr
    | left=expression op=(MUL_OP| DIV_OP| MOD_OP | ADD_OP| SUB_OP | POWER_OP) right=expression #ArithExpr
    | left=expression op=(LT_OP | GT_OP | LEQ_OP | GEQ_OP) right=expression # Comparison
    | left=expression op=(EQ_OP| NEQ_OP) right=expression # EquateExpr
    | left=expression op = (AND_OP | XOR | OR) right=expression # Logic
    | L_PAREN expression R_PAREN  # ParenExper
    ;

primary_expression : constant
//                    | enumerated_value
                    | location
                    | invoc_expr
                    ;
//enumerated_value: ' ';
location:
    ID   # VarLocation
    | ID L_SQUARE expression R_SQUARE  # ArrayLocation
    | ID DOT ID #FbLcation  //TODO: 这个比较麻烦, 不考虑多层
    ;


var_block
  : var_acc_type=(
     RES_VAR
     |  RES_VAR_INPUT
     |  RES_VAR_OUTPUT
     |  RES_VAR_INPUT_OUTPUT
     |  RES_VAR_TEMP)  variable_declaration*  RES_END_VAR;

variable_declaration:
  names+=ID (COMMA names+=ID)* COLON type_rule (AS_OP variable_initializer)? SEMI_COL ;

type_rule:
  elementary_type_name #simpleType
  | RES_ARRAY L_SQUARE range+=integer_literal FromTo range+=integer_literal R_SQUARE RES_OF elementary_type_name #arrayType
//  | pointer=pointer_type #pointerType
//   | structure_type_declaration
  ;


//
//pointer_type: 'POINTER' 'TO' type=type_rule;
//structure_type_declaration: ' ';

elementary_type_name : numeric_type_name | date_type_name | bit_string_type_name | string_type_name;
numeric_type_name : integer_type_name | real_type_name;
integer_type_name : signed_integer_type_name | unsigned_integer_type_name ;
signed_integer_type_name : RES_SINT | RES_INT | RES_DINT | RES_LINT;
unsigned_integer_type_name : RES_USINT | RES_UINT | RES_UDINT | RES_ULINT;
real_type_name : RES_REAL | RES_LREAL;
date_type_name : RES_DATE | RES_TIME_OF_DAY | RES_TOD | RES_DATE_AND_TIME | RES_DT;
bit_string_type_name : RES_BOOL | RES_BYTE | RES_WORD | RES_DWORD | RES_LWORD;
string_type_name : RES_STRING;

variable_initializer:
  constant
  | array_initialization
  ;

array_initialization : L_SQUARE (constant COMMA)*  constant R_SQUARE;


//structure_initialization: ' ';
//
//enumerated_value: ' ';

constant:
  numeric_literal | string_literal | BOOL;


numeric_literal
  : integer_literal
  | floating_point_literal
  ;
floating_point_literal
 :  floating_point_fraction decimal_exponent?
 ;
floating_point_fraction : SUB_OP? Decimal_literal ( DOT Decimal_literal )?;
decimal_exponent : Floating_point_e Sign? Decimal_literal ;

integer_literal
 : SUB_OP? (Binary_literal
 | Octal_literal
 | Decimal_literal
 | Pure_decimal_digits
 | Hexadecimal_literal)
 ;


string_literal
  : Static_string_literal
  ;

