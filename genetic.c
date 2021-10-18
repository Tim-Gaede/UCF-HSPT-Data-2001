/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Genetic                                         */
/*    Written by: Phillip Dexheimer                            */
/*    Solved by: Carlos Rosas-Anderson                         */
/***************************************************************/
#include <stdio.h>

int main()
{
	FILE *file;
	int  i, n, b;
	char gene[30];

	
	// Open the input file for reading
	file = fopen("genetic.in", "r");

	// Read the number of cases from the file
	fscanf(file, "%d\n", &n);

	// Process each of the n cases
	for(i = 0; i < n; i++)
	{
		// Read the bit to be mutated
		fscanf(file, "%d\n", &b);

		// Read the gene
		fscanf(file, "%s\n", gene);

		// Mutate the bit
		if(gene[b - 1] == '1')
			gene[b - 1] = '0';
		else
			gene[b - 1] = '1';

		// Print out the mutated gene
		printf("%s\n\n", gene);
	}

	// Close the input file
	fclose(file);

	return 0;
}
