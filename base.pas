(***************************************************************)
(*            Fifteenth Annual UCF High School                 *)
(*                 Programming Tournament                      *)
(*                      May 4, 2001                            *)
(*                                                             *)
(*    Problem: Base                                            *)
(*    Written by: Jason Daly                                   *)
(*    Solved by: Glenn Martin                                  *)
(***************************************************************)
program base;

var infile:text;
    numBases:integer;
    grid:array[1..50, 1..50] of boolean;
    i:integer;
    j:integer;
    numBombs:integer;
    x:integer;
    y:integer;
    count:integer;

begin
   (* Open the input file *)
   assign(infile, 'base.in');
   reset(infile);

   (* Loop to process each data set *)
   readln(infile, numBases);
   while (numBases >= 0) do
   begin

      (* Initialize our representation of the grid -- false means there is *)
      (* no base there                                                     *)
      for i:=1 to 50 do
      begin
         for j:=1 to 50 do
            grid[j][i]:=false;
      end;

      (* For each base, read in its location and mark the grid *)
      for i:=1 to numBases do
      begin
         readln(infile, x, y);
         grid[y][x]:=true;
      end;

      (* For each bomb, then mark that any base that may have been there *)
      (* is no longer there                                              *)
      readln(infile, numBombs);
      for i:=1 to numBombs do
      begin
         readln(infile, x, y);
         grid[y][x]:=false;
      end;

      (* Now, go through and print any bases we missed.  At the same *)
      (* time, count them                                            *)
      count:=0;
      for j:=1 to 50 do
      begin
         for i:=1 to 50 do
         begin
            if (grid[j][i]) then
            begin
               count:=count + 1;
               writeln('We missed the base at (', i, ', ', j, ')');
            end;
         end;
      end;

      (* If we didn't miss any, then say so *)
      if (count = 0) then
         writeln('All your base are belong to us!');

      (* Don't forget the blank line after each set! *)
      writeln;

      (* Read in the next base *)
      readln(infile, numBases);
   end;

   (* Close the file *)
   close(infile);
end.
