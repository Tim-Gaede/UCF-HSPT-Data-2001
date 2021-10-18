program avenge;

var infile:text;
    numRooms:integer;
    i:integer;
    roomSize:array[1..50] of integer;
    j:integer;
    tmp:integer;
    count:integer;
    turpentineFeetLeft:integer;

begin
   (* Open the input file *)
   assign(infile, 'avenger.in');
   reset(infile);

   (* Loop for each room *)
   readln(infile, numRooms);
   while (numRooms <> 0) do
   begin
      (* Read in the room square footage *)
      for i:=1 to numRooms do
         readln(infile, roomSize[i]);

      (* Sort the room sizes.  This lets us pick the first N rooms that *)
      (* will fit under The Turpentine Avenger's maximum of 100 gallons *)
      (* of turpentine (each gallon cleans 50 square feet of paint)     *)
      for i:=1 to numRooms-1 do
      begin
         for j:=i+1 to numRooms do
         begin
            if (roomSize[i] > roomSize[j]) then
            begin
               tmp:=roomSize[i];
               roomSize[i]:=roomSize[j];
               roomSize[j]:=tmp;
            end;
         end;
      end;

      (* Now just use as many rooms from the front of the sorted list as *)
      (* we can (based on how much turpentine we have)                   *)
      count:=0;
      turpentineFeetLeft:=100 * 50;
      for i:=1 to numRooms do
      begin
         if turpentineFeetLeft >= roomSize[i] then
         begin
            inc(count);
            turpentineFeetLeft:=turpentineFeetLeft - roomSize[i];
         end;
      end;

      (* Print the count *)
      writeln(count);
      writeln;

      (* Get the next room *)
      readln(infile, numRooms);
   end;

   (* Close the input file *)
   close(infile);
end.
