grammar ST;

pous: pou *;

pou: program
    | function_block
    | function
    ;



program :
    'PROGRAM' name=ID
    var_blocks+=var_block*
    stat_list
    'END_PROGRAM'
    ;

function_block:
    'FUNCTION_BLOCK' name=ID
    var_blocks+=var_block*
    stat_list
    'END_FUNCTION_BLOCK'
    ;

function: 'FUNCTION' name=ID ':' type=type_rule
    var_blocks+=var_block*
    stat_list
    'END_FUNCTION'
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
assign_stat : ID ':=' expression ';';
if_stat : 'IF' expression 'THEN' stat_list ('ELSIF' expression 'THEN' stat_list)* ('ELSE' stat_list)? 'END_IF' ';' ;
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
    | op=('-'|'NOT') expression # Not
    | base=expression ('*''*') exponent=expression # Power
    | left=expression op=('*'|'/'|'MOD') right=expression #MulDivMod
    | left=expression op=('+'|'-') right=expression # AddSub
    | left=expression op=('>'|'<'|'<='|'>=') right=expression # Comparison
    | left=expression op=('='|'<>') right=expression # Comparison
    | left=expression op=('&'|'AND') right=expression # Logic
    | left=expression ('OXR') right=expression # Logic
    | left=expression ('OR') right=expression # Logic
    ;

primary_expression : constant # Const
//                    | enumerated_value
                    | variable=ID # Varibale
                    | '(' expression ')' # ParenthesisExpr
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
  : var_type  ( variables+=variable_declaration* 'END_VAR');

var_type:
     'VAR'
     |  'VAR_INPUT'
     |  'VAR_OUTPUT'
     |  'VAR_INPUT_OUTPUT'
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
  names+=ID (',' names+=ID)* ':' type=type_rule (':=' initializer=variable_initializer)? ';' ;

elementary_type_name : numeric_type_name | date_type_name | bit_string_type_name;
numeric_type_name : integer_type_name | real_type_name;
integer_type_name : signed_integer_type_name | unsigned_integer_type_name ;
signed_integer_type_name : 'SINT' | 'INT' | 'DINT' | 'LINT';
unsigned_integer_type_name : 'USINT' | 'UINT' | 'UDINT' | 'ULINT';
real_type_name : 'REAL' | 'LREAL';
date_type_name : 'DATE' | 'TIME_OF_DAY' | 'TOD' | 'DATE_AND_TIME' | 'DT';
bit_string_type_name : 'BOOL' | 'BYTE' | 'WORD' | 'DWORD' | 'LWORD';

variable_initializer:
  constant;

constant:
  numeric_literal | string_literal | boolean_literal;


boolean_literal: 'TRUE' | 'FALSE';

//constant:
//  numeric_literal | string_literal | BOOL;
//
//
//BOOL: 'TRUE' | 'FALSE';

numeric_literal
  : '-'? integer_literal
  | '-'? Floating_point_literal
  ;

LP : '(' ;
RP : '}' ;
POWER : '**' ;
MUL : '*';
DIV : '/';
MOD : 'MOD';
ADD : '+';
MIN : '_';
GT : '>';
LT : '<';
EQ : '=';
GTE : '>=';
LTE : '<=';
NE : '<>';
AND : 'AND' | '&';
XOR : 'XOR';
OR : 'OR';


integer_literal
 : Binary_literal
 | Octal_literal
 | Decimal_literal
 | Pure_decimal_digits
 | Hexadecimal_literal
 ;

Binary_literal : '2#' Binary_digit Binary_literal_characters? ;
fragment Binary_digit : [01] ;
fragment Binary_literal_character : Binary_digit | '_'  ;
fragment Binary_literal_characters : Binary_literal_character+ ;

Octal_literal : '8#' Octal_digit Octal_literal_characters? ;
fragment Octal_digit : [0-7] ;
fragment Octal_literal_character : Octal_digit | '_'  ;
fragment Octal_literal_characters : Octal_literal_character+ ;

Decimal_literal		: [0-9] [0-9_]* ;
Pure_decimal_digits : [0-9]+ ;
fragment Decimal_digit : [0-9] ;
fragment Decimal_literal_character : Decimal_digit | '_'  ;
fragment Decimal_literal_characters : Decimal_literal_character+ ;

Hexadecimal_literal : '16#' Hexadecimal_digit Hexadecimal_literal_characters? ;
fragment Hexadecimal_digit : [0-9a-fA-F] ;
fragment Hexadecimal_literal_character : Hexadecimal_digit | '_'  ;
fragment Hexadecimal_literal_characters : Hexadecimal_literal_character+ ;

Floating_point_literal
 : Decimal_literal Decimal_fraction? Decimal_exponent?
 ;

fragment Decimal_fraction : '.' Decimal_literal ;
fragment Decimal_exponent : Floating_point_e Sign? Decimal_literal ;
fragment Floating_point_e : [eE] ;
fragment Floating_point_p : [pP] ;
fragment Sign : [+\-] ;

string_literal
  : Static_string_literal
  ;
Static_string_literal : '\'' Quoted_text? '\'' ;
fragment Quoted_text : Quoted_text_item+ ;
fragment Quoted_text_item
  : Escaped_character
  | ~["\n\r\\]
  ;
fragment
Escaped_character
  : '$' [$'LNPRT]
  | '$' Hexadecimal_digit Hexadecimal_digit
  ;
ID: [A-Za-z][A-Za-z_0-9]*;
WS : [ \n\r\t]+ -> channel(HIDDEN) ;
Block_comment : '(*' (Block_comment|.)*? '*)' -> channel(HIDDEN) ; // nesting comments allowed
