(***************************************************************)
(*            Fifteenth Annual UCF High School                 *)
(*                 Programming Tournament                      *)
(*                      May 4, 2001                            *)
(*                                                             *)
(*    Problem: Nihongo                                         *)
(*    Written by: Richard Russo                                *)
(*    Solved by: Kevin Wertman                                 *)
(***************************************************************)
program nihongo;

{* this problem has several special cases that you have to 
 * watch out for.  The words 'desu' and 'ka' can appear multiple
 * times in the file.   The 'desu' only counts if it is by itself
 * and the second to last word in the sentence.
 *
 * Look out for words that have desu inside of them, such as 
 * hiserodesu.
 *}

{* This function behaves the same was as the C function strtok 
 *  The first time you call it with the sentance as the first
 *  argument and the token that breaks them apart as the second
 *  argument, and it returns the first word
 *  Each time you call it from that point with '' as the first 
 *  argument it returns the next word in the string
 *}
function StrTok(Text,Token:string):string;
const
   LftTxt:string='';
var
   i:integer;
   s:string;
   result:string;
begin
    result := '';
 
    if length(Text)>0 then
       s:=Text
    else if length(LftTxt)>0 then
       s:=LftTxt
    else
       StrTok := '';

    i:=Pos(Token,s);
    if i=0 then begin
       result:=LftTxt;
       LftTxt:='';
    end {if}
    else begin
       result:=Copy(s,1,i-1);
       LftTxt:=Copy(s,i+length(Token),length(s)+1-i-length(Token));
    end;{else}
    StrTok := result;   
end;

  var
    infile: text;
    line: string[80]; {* line of input from input file *}
    word: string[80];   {* temporary variables that hold single words *} 
    word_1: string[80];
    word_2: string[80];
    word_3: string[80];
    temp: string[80];

  begin
    {* Open the input file *}
    assign(infile, 'nihongo.in');
    reset(infile);
    
    {* Read each line, checking for terminal yame *}
    readln(infile, line);
    while (pos('yame', line) = 0) do
      begin
        word_1 := '';
        word_2 := '';
        word_3 := '';
        
        {* read each word and store only the last 3 words that have 
         * been read.  The sentence is over when word length is 0 
         *}
        word := StrTok(line, ' ');      
        
        while(Length(word) > 0) do
        begin
                word_1 := word_2;
                word_2 := word_3;
                word_3 := word;
                word := StrTok('', ' ');
        end;
        
        {* if the 2nd to last word is 'desu' then you have the 
         * special copula case and you have to print out the 
         * previous word as well 
         *}
        if(word_2 = 'desu') then
        begin
                writeln('Hai, ' + word_1 + ' ' + word_2);
        end
        {* else it's the normal case and just print out the verb *}
        else
        begin
                writeln('Hai, ' + word_2);
        end;

        readln(infile, line);
      end;
    close(infile); 
  end.


