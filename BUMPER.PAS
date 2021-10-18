(***************************************************************)
(*            Fifteenth Annual UCF High School                 *)
(*                 Programming Tournament                      *)
(*                      May 4, 2001                            *)
(*                                                             *)
(*    Problem: Bumper                                          *)
(*    Written by: Peter Doege                                  *)
(*    Solved by: Phillip Dexheimer                             *)
(***************************************************************)
program bumper;

var
  Infile : text;
  nCases, i, X, Y, r : integer;

begin
  assign(Infile, 'bumper.in');
  reset(Infile);
  readln(Infile, nCases);
  for i := 1 to nCases do
   begin
    readln(Infile, X, Y, r);
(*//This is the only tricky part of the problem
//Since the airplane is at the origin, the distance formula
//is simplified.  And since we're checking circles, we just
//sum the radii.*)
    if (sqrt(X*X+Y*Y) <= r+10) then
      writeln('Danger')
    else
      writeln('Ok');
   end;
  close(Infile);
end.
