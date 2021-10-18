(***************************************************************)
(*            Fifteenth Annual UCF High School                 *)
(*                 Programming Tournament                      *)
(*                      May 4, 2001                            *)
(*                                                             *)
(*    Problem: Genetic                                         *)
(*    Written by: Phillip Dexheimer                            *)
(*    Solved by: Carlos Rosas-Anderson                         *)
(***************************************************************)
program Genetic;

var
	InputFile: Text;
	I, N, B: Integer;
	Gene: string;

begin
	{Associate the filename with the file variable}
	Assign(InputFile, 'GENETIC.IN');

	{Open the intput file for reading}
	Reset(InputFile);

	{Read the number of cases from the file}
	Readln(InputFile, N);

	{Process each of the N cases}
	for I := 1 to N do
	begin
		{Read the bit to be mutated from the file}
		Readln(InputFile, B);

		{Read the gene from the file}
		Readln(InputFile, Gene);

		{Mutate the bit}
		if Gene[B] = '1' then
			Gene[B] := '0'
		else
			Gene[B] := '1';

		{Print the gene}
		Writeln(Gene);
		Writeln;
	end;

	{Close the input file}
	Close(InputFile)
end.
