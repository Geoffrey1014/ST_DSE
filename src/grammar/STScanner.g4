lexer grammar STScanner;
options
{
  language = 'Java';
  superClass = 'Lexer'; //class DecafScanner extends Lexer;
}


// Miscillaneous characters
L_SQUARE : '[';
R_SQUARE : ']';
L_PAREN : '(';
R_PAREN : ')';
L_CURL : '{';
R_CURL : '}';
COMMA : ',';
SEMI_COL :  ';';
COLON :':';

// arithmatic ops
ADD_OP  : '+';
SUB_OP : '-';
MUL_OP : '*';
DIV_OP : '/';
MOD_OP : 'MOD';
POWER_OP : '**' ;


// boolean ops
LT_OP : '<';
GT_OP : '>';
LEQ_OP : '<=';
GEQ_OP : '>=';
EQ_OP : '=';
NEQ_OP : '<>';
AND_OP : 'AND' | '&';
XOR : 'XOR';
OR : 'OR';
NOT_OP : 'NOT';



// assignment op
AS_OP : ':=';
//ADD_AS_OP : '+=';
//SUB_AS_OP : '-=';
RT_AS_OP: '=>';
// reserved words

RES_FOR : 'FOR';
RES_DO : 'DO';
RES_END_FOR : 'END_FOR';
RES_TO : 'TO';
RES_BY : 'BY';

RES_WHILE : 'WHILE';
RES_END_WHILE : 'END_WHILE';

RES_IF : 'IF';
RES_THEN : 'THEN';
RES_ELSE : 'ELSE';
RES_ELSIF : 'ELSIF';
RES_END_IF : 'END_IF';
RES_EXIT : 'EXIT';

RES_ARRAY: 'ARRAY';
FromTo : '..';
RES_OF : 'OF';

RES_BOOL : 'BOOL';
//RES_TRUE:'TRUE' ;
//RES_FALSE : 'FALSE';

RES_SINT: 'SINT' ;
RES_INT: 'INT' ;
RES_DINT :'DINT' ;
RES_LINT :'LINT';

REAL :'REAL' ;
LREAL :'LREAL';

USINT : 'USINT' ;
 UINT :'UINT' ;
UDINT : 'UDINT' ;
ULINT : 'ULINT';

DATE : 'DATE';
TIME_OF_DAY: 'TIME_OF_DAY';
TOD: 'TOD';
DATE_AND_TIME:  'DATE_AND_TIME';
DT: 'DT';

BYTE: 'BYTE' ;
WORD : 'WORD';
DWORD :  'DWORD';
LWORD : 'LWORD';

RES_PROGRAM : 'PROGRAM';
RES_END_PROGRAM : 'END_PROGRAM';
RES_FUNCTION_BLOCK : 'FUNCTION_BLOCK';
RES_END_FUNCTION_BLOCK : 'END_FUNCTION_BLOCK';
RES_FUNCTION : 'FUNCTION';
RES_END_FUNCTION : 'END_FUNCTION';

RES_VAR:    'VAR';
RES_VAR_INPUT   :  'VAR_INPUT';
RES_VAR_OUTPUT  :  'VAR_OUTPUT';
RES_VAR_INPUT_OUTPUT:  'VAR_INPUT_OUTPUT';
RES_END_VAR : 'END_VAR';

// main tokens
BOOL: 'TRUE' | 'FALSE';

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
//WS : [ \n\r\t]+ -> channel(HIDDEN) ;
//Block_comment : '(*' (Block_comment|.)*? '*)' -> channel(HIDDEN) ; // nesting comments allowed

Block_comment : '(' WS* '*' (Block_comment|.)*? '*' WS* ')' -> skip ; // nesting comments allowed
WS : [ \n\r\t]+ -> skip ;
