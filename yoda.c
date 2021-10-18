/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Yoda                                            */
/*    Written by: Glenn Martin                                 */
/*    Solved by: Carlos Rosas-Anderson                         */
/***************************************************************/
#include <stdio.h>
#include <string.h>

int main()
{
	FILE *file;
	int  i, n;
	char sentence[100];

	
	// Open the input file for reading
	file = fopen("yoda.in", "r");

	// Read the number of sentences from the file
	fscanf(file, "%d\n", &n);

	// Process each of the n sentences
	for(i = 0; i < n; i++)
	{
		// Read a sentence from the file
		fgets(sentence, 100, file);

		// Erase a newline character from the sentence if it has one
		if(sentence[strlen(sentence) - 1] == '\n')
			sentence[strlen(sentence) - 1] = '\0';

		// Print the sentence
		printf("%s", sentence);
		
		// Print "  Hmmm?" if the sentence ends with '?'
		if(sentence[strlen(sentence) - 1] == '?')
			printf("  Hmmm?");

		printf("\n");
	}

	// Close the input file
	fclose(file);

	return 0;
}
