/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Nihongo                                         */
/*    Written by: Richard Russo                                */
/*    Solved by: Kevin Wertman                                 */
/***************************************************************/
#include <stdio.h>
#include <string.h>

/* this problem has several special cases that you have to 
 * watch out for.  The words 'desu' and 'ka' can appear multiple
 * times in the file.   The 'desu' only counts if it is by itself
 * and the second to last word in the sentence.
 *
 * Look out for words that have desu inside of them, such as 
 * hiserodesu.
 */

char * copula = "desu";

int main(void)
{

    /* line is the buffer to read each line into from the .in file */
    char line[80];
    
    /* an array of 80 pointers to words (we couldn't possibly have 
     * more than 80 words)
     */
    char * words[80];

    int indx = 0;
    int i;

    /* two temporary character pointers to help tokenize the sentence
     * into individual words 
     */
    char * ptLine;
    char * word;
    
    freopen("nihongo.in", "r", stdin);

    gets(line);
    
    /* our input ends when the word 'yame' appears in the line */
    while(strcmp(line, "yame") != 0)
    {
        /* clear out the word pointers */
        memset(words, 0, sizeof(char *) * 80);
        indx = 0;
        ptLine = line;
        
        /* reading the line one word at a time and 
         * storing each word in our words array 
         */
        while((word = strtok(ptLine, " ")) != NULL)
        {
           words[indx++] = word;
           ptLine = NULL;
        }
        
        /* If the third to last word is the copula 'desu' then
         * it's the special case of verb desu so output both  
         * of them 
         */
        if(!strcmp(words[indx - 2], copula))
        {
           printf("Hai, %s %s\n", words[indx - 3], words[indx - 2]);
        }
        /* Normal case where the second to last word is the verb
         */ 
        else
        {
           printf("Hai, %s\n", words[indx - 2]);
        }

        gets(line);
    }

    return 0;
}
