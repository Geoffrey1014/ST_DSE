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


pous : pou pou* EOF;

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
    | for_stat
    | while_stat
//    | repeat_stat
//    | invoc_stat
    ;
assign_stat : location AS_OP expression SEMI_COL;

if_stat : RES_IF expression RES_THEN stat_list (RES_ELSIF expression RES_THEN stat_list)* (RES_ELSE stat_list)? RES_END_IF SEMI_COL ;
//case_stat : 'CASE' expression 'OF' integer_literal (',' integer_literal)* ':' stat_list  // TODO:   case_state可以泛化 interger_literal 成 subrange | signed_integer | enumerated_value
//        ( integer_literal (',' integer_literal)* ':' stat_list)*
//        ('ELSE' stat_list)?
//        'END_CASE' ';' ;
for_stat :  RES_FOR control_variable=ID AS_OP for_list RES_DO stat_list RES_END_FOR SEMI_COL ;
for_list : expression RES_TO expression (RES_BY expression)? ;

while_stat : RES_WHILE expression RES_DO stat_list RES_END_WHILE SEMI_COL ;
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

primary_expression : constant
//                    | enumerated_value
                    | location
//                    | function_name=ID '(' param_assignment (',' param_assignment)* ')' # FunctionCall
                    ;
//enumerated_value: ' '; // TODO
location: ID | ID L_SQUARE expression R_SQUARE;

//var_block locals[boolean input, boolean output, boolean temp]
//  : ('VAR'
//     | { $input=true; } 'VAR_INPUT'
//     | { $output=true; } 'VAR_OUTPUT'
//     | { $input=$output=true; } 'VAR_INPUT_OUTPUT'
//     | { $temp=true; } 'VAR_TEMP')
//    ( variables+=variable_declaration* 'END_VAR');

var_block
  : var_type  variable_declaration*  RES_END_VAR;

var_type:
     RES_VAR
     |  RES_VAR_INPUT
     |  RES_VAR_OUTPUT
     |  RES_VAR_INPUT_OUTPUT
//     |  'VAR_TEMP'
     ;

type_rule:
  name=elementary_type_name #simpleType
  | array=array_type #arrayType
//  | pointer=pointer_type #pointerType
  ;

array_type
  : RES_ARRAY L_SQUARE range R_SQUARE RES_OF elementary_type_name;

range
  : lbound=integer_literal FromTo ubound=integer_literal;
//
//pointer_type: 'POINTER' 'TO' type=type_rule;

//structure_type_declaration: ' ';

variable_declaration:
  names+=ID (COMMA names+=ID)* COLON type=type_rule (AS_OP variable_initializer)? SEMI_COL ;

elementary_type_name : numeric_type_name | date_type_name | bit_string_type_name;
numeric_type_name : integer_type_name | real_type_name;
integer_type_name : signed_integer_type_name | unsigned_integer_type_name ;
signed_integer_type_name : RES_SINT | RES_INT | RES_DINT | RES_LINT;
unsigned_integer_type_name : USINT | UINT | UDINT | ULINT;
real_type_name : REAL | LREAL;
date_type_name : DATE | TIME_OF_DAY | TOD | DATE_AND_TIME | DT;
bit_string_type_name : RES_BOOL | BYTE | WORD | DWORD | LWORD;

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

