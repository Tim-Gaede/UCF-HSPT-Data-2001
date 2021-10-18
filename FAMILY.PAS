(***************************************************************)
(*            Fifteenth Annual UCF High School                 *)
(*                 Programming Tournament                      *)
(*                      May 4, 2001                            *)
(*                                                             *)
(*    Problem: Family                                          *)
(*    Written by: Richard Russo                                *)
(*    Solved by: Richard Urich                                 *)
(***************************************************************)
{uses strings;}
var
  name, value: array[1..1000] of String[20];
  orig,v,s : String;
  err,currval,x,y,z,namecount, currname, dispcount: integer;
  infile : text;

begin
  assign(infile,'family.in');
  reset(infile);
  repeat
    readln(infile,namecount);{read in number of names, age pairs}
    if (namecount=0) then Break;{if no names to read in, break}
    for x:=1 to namecount do
     begin
       readln(infile,s);
       y:=Pos(' ',s);{set y to position of first space in s}
       name[x]:=Copy(s,1,y-1);{copy up to space into name[x]}
       value[x]:=Copy(s,y+1,Length(s)-y);
           {copy everything after space into value[x]}
     end;
    readln(infile,dispcount);{read in number of names to display}
    for x:=1 to dispcount do
     begin
       readln(infile,s);{read in name to display age of}
       orig:=s;{copy so you can print out name}
       currval:=0;{start the age at 0}
       repeat
         if (pos('+',s)>0) then
           y:=pos('+',s){set y to position of + sign}
         else y:=pos('-',s);{set y to position of - sign}
         if (y>0) then v:=Copy(s,y,Length(s)-y+1)
	    {if +/- sign found, copy the number to v}
         else v:='0';{else set v to 0}
         val(v,z,err);{get the value of v}
         currval:= currval + z;{increment age by z}
         delete(s,y,Length(s)-y+1);{delete the number from the string}
         y:=1;
         while ((y<=namecount)AND(name[y]<>s)) do inc(y);
	     {set y to the index of the name in the name array}
         s:=value[y];{s is now the value(age) of the name}
       until((s[1]<'A')OR((s[1]>'Z')AND(s[1]<'a'))OR(s[1]>'z'));
          {repeat until s is not a name}
       val(s,z,err);{get value of s}
       currval:=currval+z;{add value to age}
       if (currval=1) then writeln(orig,' is 1 year old.')
       else writeln(orig,' is ',currval,' years old.');
     end;
     writeln;
  until(namecount=0);{repeat until no names input}
  close(infile);
end.
