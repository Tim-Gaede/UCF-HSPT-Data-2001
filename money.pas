(***************************************************************)
(*            Fifteenth Annual UCF High School                 *)
(*                 Programming Tournament                      *)
(*                      May 4, 2001                            *)
(*                                                             *)
(*    Problem: Money                                           *)
(*    Written by: Glenn Martin                                 *)
(*    Solved by: Peter Doege                                   *)
(***************************************************************)
program Money;

var
	InputFile: text;
        I, N: integer;
        Amount: real;
	Sentence: string;

begin
	{Associate the filename with the file variable}
	assign(InputFile, 'money.in');

	{Open the intput file for reading}
	reset(InputFile);

	{Read the number of sentences from the file}
	readln(InputFile, N);

	{Process each of the N sentences}
	for I := 1 to N do
	begin
		{Read a sentence from the file}
                readln(InputFile, Amount);

                {Convert to US currency}
                Amount := Amount * 0.64;

		{Print the sentence}
	        write(Amount:0:2);
                writeln;
	end;

	close(InputFile);
end.
