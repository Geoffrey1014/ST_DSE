TYPE MULT1VAR    (* 用类型名MULTIVAR定 义 类型变量 *)
    STRUCT
        VAR1：INT；
        VAR2 ：REAL；(* 设 置结 构 变 量 VAR1 和 VAR2 *)
    END_STRUCT
END_TYPE

VAR
    A：INT；
    B：ARRAY [1..8] OF INT；
    C: REAL: =20.2;
    D：MULTIVAR(VAR1：=5,VAR2： =2.0) ；
    E：MULTIVAR；
END_VAR

A ： =15; ( * 对 变 量 A陚 值 1 5*)
B [1]：= A * 3 ;
B [2]：=A ； (* 分别根据表达 式对数组变量B 的两 个 下标 变 2 賦 值 *)
A ：=REAL_TO_INT(C)； (* 函数 调 用后賦值 给 A *)
E ：= D ； (* 将 D 中各变 量的值 陚 值 给 E 变 量 *)
