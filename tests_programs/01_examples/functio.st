FUNCTION COMPENSATION_PT : REAL

VAR_INPUT (* 输 人变 量声 明段*)
    P1, PO : REAL ;  (* 实 际 压力和设 计 压 力，单 位 kpa *)
    T1, T0 : REAL ; (* 实 际 温 度和设 计 温 度，单 位 尤 *)
END_VAR
VAR
    CP, CT : REAL ;
END_VAR

CT := DIV( ADD( T0+273.15) ,  ADD(T1 +273.15)) ;
CP := DIV( ADD( P1+103.32) , ADD(PO + 103.32)) ;
COMPENSATION_PT := MUL(CT, CP);

END_FUNCTION