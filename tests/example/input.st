PROGRAM program0
  VAR_INPUT
    X1 : INT ;
  END_VAR
  VAR_OUTPUT
    O : INT := 0 ;
  END_VAR
  VAR
    V1 : INT := 0;
  END_VAR

IF V1 = 0 THEN
	IF X1 = 3 THEN
		V1 := 2;
		O := 1;
	ELSIF X1 = 4 THEN
		V1 := 0;
		O := 2;
	END_IF;
ELSIF V1 = 2 THEN
	WHILE V1 < X1 DO
		V1 := V1 + 1;
	END_WHILE;
	O := 3;
ELSIF V1 = 4 THEN
	O := 4;
END_IF;
END_PROGRAM