PROGRAM POU_1
VAR_INPUT
    X: INT;
    Y: INT;
END_VAR
VAR_OUTPUT
    V:INT := 0;
END_VAR

IF V <= 0 THEN
    V:= 100;
ELSE
    IF X < 5 THEN
        V := V * 2;
    ELSE
        WHILE Y<V DO
            V := V - X;
        END_WHILE;
        V := Y MOD V;
    END_IF;
END_IF;
END_PROGRAM

