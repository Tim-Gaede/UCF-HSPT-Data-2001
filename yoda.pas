(***************************************************************)
(*            Fifteenth Annual UCF High School                 *)
(*                 Programming Tournament                      *)
(*                      May 4, 2001                            *)
(*                                                             *)
(*    Problem: Yoda                                            *)
(*    Written by: Glenn Martin                                 *)
(*    Solved by: Carlos Rosas-Anderson                         *)
(***************************************************************)
program Yoda;

var
	InputFile: text;
	I, N: integer;
	Sentence: string;

begin
	{Associate the filename with the file variable}
	assign(InputFile, 'yoda.in');

	{Open the intput file for reading}
	reset(InputFile);

	{Read the number of sentences from the file}
	readln(InputFile, N);

	{Process each of the N sentences}
	for I := 1 to N do
	begin
		{Read a sentence from the file}
		readln(InputFile, Sentence);

		{Print the sentence}
		write(Sentence);

		{Print '  Hmmm?' if the sentence ends with '?'}
		if Sentence[length(Sentence)] = '?' then
			write('  Hmmm?');

		writeln;
	end;

	close(InputFile);
end.
