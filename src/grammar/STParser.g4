parser grammar STParser;

options
{
    language = 'Java';
    tokenVocab = 'STScanner';
    superClass = 'Parser'; //class DecafParser extends Parser;
    //buildAST = true;
}
@parser::header{
     package grammar;
}


pous : pou pou*;

pou: program
    | function_block
    | function
    ;

program :
    RES_PROGRAM name=ID
    var_blocks+=var_block*
    stat_list
    RES_PROGRAM_END
    ;

function_block:
    RES_FUNCTION_BLOCK name=ID
    var_blocks+=var_block*
    stat_list
    RES_END_FUNCTION_BLOCK
    ;

function: RES_FUNCTION name=ID COLON type=type_rule
    var_blocks+=var_block*
    stat_list
    RES_END_FUNCTION
    ;

stat_list : stat (stat)*;

stat
    : assign_stat
    | if_stat
//    | case_stat
//    | for_stat
//    | while_stat
//    | repeat_stat
//    | invoc_stat
    ;
assign_stat : ID AS_OP expression SEMI_COL;
if_stat : RES_IF expression RES_THEN stat_list (RES_ELSIF expression RES_THEN stat_list)* (RES_ELSE stat_list)? RES_END_IF SEMI_COL ;
//case_stat : 'CASE' expression 'OF' integer_literal (',' integer_literal)* ':' stat_list  // TODO:   case_state可以泛化 interger_literal 成 subrange | signed_integer | enumerated_value
//        ( integer_literal (',' integer_literal)* ':' stat_list)*
//        ('ELSE' stat_list)?
//        'END_CASE' ';' ;
//for_stat :  'FOR' control_variable=ID ':=' for_list 'DO' stat_list 'END_FOR' ';' ;
//for_list : expression 'TO' expression ('BY' expression)? ;
//
//while_stat : 'WHILE' expression 'DO' stat_list 'END_WHILE' ';' ;
//repeat_stat : 'REPEAT' stat_list 'UNTIL' expression 'END_REPEAT' ';' ;
//invoc_stat : fb_name=ID '(' (param_assignment (',' param_assignment)* ) ?')' ;
//param_assignment : ((variable_name=ID ':=')? expression) | ('NOT'? variable_name=ID '=>' variable=ID);
//exit_stat : 'EXIT' ;

expression
    : primary_expression # PrimaryExpr
    | op=(SUB_OP| NOT_OP ) expression # Not
    | base=expression POWER_OP exponent=expression # Power
    | left=expression op=(MUL_OP|DIV_OP| MOD_OP) right=expression #MulDivMod
    | left=expression op=(ADD_OP| SUB_OP) right=expression # AddSub
    | left=expression op=(LT_OP | GT_OP | LEQ_OP | GEQ_OP) right=expression # Comparison
    | left=expression op=(EQ_OP| NEQ_OP) right=expression # Comparison
    | left=expression AND_OP right=expression # Logic
    | left=expression XOR right=expression # Logic
    | left=expression OR right=expression # Logic
    ;

primary_expression : constant # Const
//                    | enumerated_value
                    | variable=ID # Varibale
                    | L_PAREN expression R_PAREN # ParenthesisExpr
//                    | function_name=ID '(' param_assignment (',' param_assignment)* ')' # FunctionCall
                    ;
//enumerated_value: ' '; // TODO

//var_block locals[boolean input, boolean output, boolean temp]
//  : ('VAR'
//     | { $input=true; } 'VAR_INPUT'
//     | { $output=true; } 'VAR_OUTPUT'
//     | { $input=$output=true; } 'VAR_INPUT_OUTPUT'
//     | { $temp=true; } 'VAR_TEMP')
//    ( variables+=variable_declaration* 'END_VAR');

var_block
  : var_type  ( variables+=variable_declaration* RES_END_VAR);

var_type:
     RES_VAR
     |  RES_VAR_INPUT
     |  RES_VAR_OUTPUT
     |  RES_VAR_INPUT_OUTPUT
//     |  'VAR_TEMP'
     ;

type_rule:
  name=elementary_type_name #simpleType
//  | array=array_type #arrayType
//  | pointer=pointer_type #pointerType
  ;

//array_type
//  : 'ARRAY' '[' ranges+=range (',' ranges+=range)* ']' 'OF' type=type_rule;
//
//range
//  : lbound=integer_literal '..' ubound=integer_literal;
//
//pointer_type: 'POINTER' 'TO' type=type_rule;

variable_declaration:
  names+=ID (COMMA names+=ID)* COLON type=type_rule (AS_OP initializer=variable_initializer)? SEMI_COL ;

elementary_type_name : numeric_type_name | date_type_name | bit_string_type_name;
numeric_type_name : integer_type_name | real_type_name;
integer_type_name : signed_integer_type_name | unsigned_integer_type_name ;
signed_integer_type_name : RES_SINT | RES_INT | RES_DINT | RES_LINT;
unsigned_integer_type_name : USINT | UINT | UDINT | ULINT;
real_type_name : REAL | LREAL;
date_type_name : DATE | TIME_OF_DAY | TOD | DATE_AND_TIME | DT;
bit_string_type_name : RES_BOOL | BYTE | WORD | DWORD | LWORD;

variable_initializer:
  constant;

constant:
  numeric_literal | string_literal | BOOL;



numeric_literal
  : SUB_OP? integer_literal
  | SUB_OP? Floating_point_literal
  ;


integer_literal
 : Binary_literal
 | Octal_literal
 | Decimal_literal
 | Pure_decimal_digits
 | Hexadecimal_literal
 ;


string_literal
  : Static_string_literal
  ;
