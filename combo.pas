(***************************************************************)
(*            Fifteenth Annual UCF High School                 *)
(*                 Programming Tournament                      *)
(*                      May 4, 2001                            *)
(*                                                             *)
(*    Problem: Combo                                           *)
(*    Written by: Kevin Wertman                                *)
(*    Solved by: Ken Hadden                                    *)
(***************************************************************)
program combo;
type
  Index = 1..9;{;}
  List  = array[Index] of integer;
var
  Digits       : List;  { current settings of lock }
  Combination  : List;  { correct settings of lock }
  nDigits      : integer; { number of digits in lock }
  ProblemNum   : integer; { current problem number }
  fin          : text;   { the input file }

{************************************
 * GetCurrentSettings               *
 *                                  *
 * Reads the starting settings      *
 * of the lock from the file        *
 ***********************************}
procedure GetCurrentSettings(var n: integer);
var
  Count : integer;
begin {GetCurrentCombo}
  for Count := 1 to n do
    begin
      read(fin, Digits[Count]);
    end;
end;  {GetCurrentCombo}

{************************************
 * GetCorrectCombo                  *
 *                                  *
 * Reads the correct combination    *
 * of the lock from the file        *
 ***********************************}
procedure GetCorrectCombo(var n: integer);
var
  Count : integer;
begin {GetCorrectCombo}
  for Count := 1 to n do
    begin
      read(fin, Combination[Count]);
    end;
end;  {GetCorrectCombo}


{************************************
 * PrintCurrentSettings             *
 *                                  *
 * Prints the current settings of   *
 * the lock with a space between    *
 * each digit.  Note that it does   *
 * not advance lines so that we can *
 * write "- click" on the same line *
 * later.                           *
 ***********************************}
procedure PrintCurrentSettings(var n: integer);
var
  i : integer;
begin {PrintCurrentSettings}
  for i := 1 to n do
    begin
      write(Digits[i], ' ');
    end;
end; {PrintCurrentSettings}

{************************************
 * OpenLock                         *
 *                                  *
 * Where the real work is done.     *
 * Finds the correct combination by *
 * advancing each digit individually*
 * until the correct digit is found.*
 * Starts with the first number on  *
 * the left and works its way to    *
 * the right.                       *
 ***********************************}
procedure OpenLock(var n: integer);
var
  i      : integer;
  nSteps : integer;
begin {OpenLock}

  nSteps := 0;

  { output the starting combination }
  PrintCurrentSettings(n);
  writeln('');{''}

  { start with the first digit on the left
    and work our way to the right }
  for i := 1 to n do
    begin {for}

      { change the ith digit until it is correct }
      while Digits[i] <> Combination[i] do
        begin {while}

          { advance 3 number and then do modulo 10.
            This performs the appropriate "wrap-around"
            of the digits. }
          Digits[i] := (Digits[i] + 3) mod 10;
          PrintCurrentSettings(n);
          nSteps := nSteps + 1;

          { if this digit is correct, must print
            appropriate message }
          if (Digits[i] = Combination[i]) then
            begin {if}
              write('- click');
            end;  {if}
          writeln('');{''}

        end; {while}
    end; {for}

    { print how many steps were required to open lock }
    writeln('Locker opened in ', nSteps, ' steps');
    writeln('');{''}

end; {OpenLock}


{******************************
 * MainBody                   *
 ******************************}
begin {combo}
  ProblemNum := 1;          { current problem number }
  assign(fin, 'combo.in');  { open input file }
  reset(fin);               { go to beginning of file }
  readln(fin, nDigits);     { read number of digits in
                              first lock }

  { continue solving until nDigits = 0 }
  while nDigits <> 0 do
    begin {while}
             writeln('Lock #', ProblemNum);

             GetCurrentSettings(nDigits);
             GetCorrectCombo(nDigits);
             OpenLock(nDigits);

             { read number of digits in next lock }
             readln(fin, nDigits);
             ProblemNum := ProblemNum + 1;
    end {while}
end.  {combo}
