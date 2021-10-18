(***************************************************************)
(*            Fifteenth Annual UCF High School                 *)
(*                 Programming Tournament                      *)
(*                      May 4, 2001                            *)
(*                                                             *)
(*    Problem: Monks                                           *)
(*    Written by: Peter Doege                                  *)
(*    Solved by: Phillip Dexheimer                             *)
(***************************************************************)
program monks;

var
  InFile : text;
  nCases, firstWall, nJumps : integer;
  i : integer;

begin
  assign(InFile, 'monks.in');
  reset(InFile);
  readln(InFile, nCases);
  for i := 1 to nCases do
   begin
    readln(InFile, firstWall);
    nJumps := 100 div firstWall;
    writeln(nJumps);
   end
end.
