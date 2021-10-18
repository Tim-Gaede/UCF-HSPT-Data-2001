(***************************************************************)
(*            Fifteenth Annual UCF High School                 *)
(*                 Programming Tournament                      *)
(*                      May 4, 2001                            *)
(*                                                             *)
(*    Problem: Hope                                            *)
(*    Written by: Eric Heimburg / Kevin Wertman                *)
(*    Solved by: Richard Russo                                 *)
(***************************************************************)
program hope;
  var
    infile: TEXT;
    chest: string[255];
    caseCount, numCases, answerLeft, answerRight: integer;

  (**
   * indexOf returns the index (one-based) of the first occurrence
   * of the character 'c' in the string 's' that occurs at or 
   * after the specified 'startIdx'.
   *)

  function indexOf (s:string; c: char; start: integer): integer;
  var
    count, result: integer;
  begin
    result := -1;
    if start < 1 then start := 1;
    for count := start to length (s) do
    begin
      if ((s[count] = c) and (result < 0)) then
      begin
        result := count
      end;
    end;
    indexOf := result;
  end;

  (**
   * max returns the larger of the two values 'i' and 'j'.
   * If they are equal, it will return either one.
   *)

  function max (i: integer; j: integer): integer;
  var
    m : integer;
  begin
    if i > j then
    begin
      m := i;
    end
    else
    begin
      m := j;
    end;
    max := m;
  end;

  (**
   * min returns the smaller of the two values 'i' and 'j'.
   * If they are equal, it will return either one.
   *)

  function min (i: integer; j: integer): integer;
  var
    m : integer;
  begin
    if (i < j) then
    begin
      m := i;
    end
    else
    begin
      m := j;
    end;
    min := m;
  end;

  (**
   * findSolution sets the 'bestLeft' and 'bestRight' parameters to
   * the lengths of the best hinges (according to the problem specification).
   * The variable bestLeft will be set to the length of the hinge on the
   * left, and bestRight will be set to the length of the hinge on the right.
   * If there is no suitable solution, they will both be set to -1.
   *)

  procedure findSolution (var bestLeft: integer; var bestRight: integer);
    var leftStart, leftEnd, rightStart, rightEnd: integer;
    var left, right: integer;
    var bestArea, bestSeparation, bestHinge: integer;
    var area, separation, hinge: integer;
    var aCk, sCk, hCk: boolean;
    var aEq, sEq: boolean;
  begin

    (*
     * bestLeft and bestRight keep track of the best solution found so
     * far.  After enumerating all possibilities for the set of two hinges,
     * these variables will hold the solution.
     *)

    bestLeft := -1;
    bestRight := -1;
    leftStart := indexOf (chest, 'o', 1);

    (* 
     * A set of two hinges is evaluated based on the area that they cover,
     * the number of characters between the hinges, and the length of the
     * largest hinge in the set.  These variables keep track of the
     * best values found so far, and correspond to the bestRight and bestLeft
     * above.
     *)

    bestArea := -1;
    bestSeparation := -1;
    bestHinge := -1;

    (*
     * The following two 'while' loops enumerate all possibilities for the
     * set of two hinges.  leftStart and leftEnd mark the indices of the
     * start and end of the left hinge; similarly for rightStart and rightEnd.
     *)

    while leftStart >= 0 do
    begin
      leftEnd := indexOf (chest, 'o', leftStart + 1);
      if leftEnd >= 0 then
      begin
        rightStart := indexOf (chest, 'o', leftEnd + 1);
        while rightStart >= 0 do
        begin
          rightEnd := indexOf (chest, 'o', rightStart + 1);
          if (rightEnd >= 0) then
          begin
            left := leftEnd - leftStart - 1;
            right := rightEnd - rightStart - 1;

            (*
             * After computing the area, separation, and largest hinge length
             * for the current set, compare them to the best found so far.
             *)

            area := left + right;
            separation := rightStart - leftEnd - 1;
            hinge := max (left, right);

            aCk := area > bestArea;
            aEq := area = bestArea;
            sCk := separation > bestSeparation;
            sEq := separation = bestSeparation;
            hCk := hinge > bestHinge;


            (*
             * Determine if the current possibility is better than the best one found
             * so far.  Resolve ties according to the problem spec.
             *)

            if (aCk or (aEq and sCk) or (aEq and sEq and hCk)) then
            begin
              bestLeft := left;
              bestRight := right;
              bestArea := area;
              bestSeparation := separation;
              bestHinge := hinge;
            end;
          end;
          rightStart := rightEnd;
        end; {while rightStart >= 0 }
      end; {if leftEnd >= 0}
      leftStart := leftEnd;
    end; { while leftStart >= 0}
  end; {procedure findSolution}

begin
  assign (infile, 'hope.in');
  reset (infile);
  readln (infile, numCases);
  for caseCount := 1 to numCases do
  begin
    readln (infile, chest);
    findSolution (answerLeft, answerRight);
    writeln ('Hope Chest #', caseCount, ': ', max (answerLeft, answerRight),
             ' ', min (answerLeft, answerRight));
  end;
end.
