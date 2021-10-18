/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Combo                                           */
/*    Written by: Kevin Wertman                                */
/*    Solved by: Ken Hadden                                    */
/***************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

FILE *fin;      // the input file
int Digits[9];  // current settings of lock
int Combo[9];   // correct settings of lock


/**********************************
 * GetCurrentSettings             *
 *                                *
 * Reads the starting settings    *
 * of the lock from the file      *
 *********************************/
void GetCurrentSettings(int n)
{
	int i;
	char buff[256];
	char *p = buff;
	//read a line from the file
	fgets(buff, sizeof(buff), fin);

	// pull each digit from the input line
	// and put it into the Digits array
	for (i = 0; i < n; i++)
		Digits[i] = strtol(p, &p, 10);
}

/**********************************
 * GetCorrectCombo                *
 *                                *
 * Reads the correct combination  *
 * of the lock from the file      *
 *********************************/
void GetCorrectCombo(int n)
{
	int i;
	char buff[256];
	char *p = buff;
	// read a line from the file
	fgets(buff, sizeof(buff), fin);

	// pull each digit from the input line
	// and put it into the Combo array
	for (i = 0; i < n; i++)
		Combo[i] = strtol(p, &p, 10);
}

/**********************************
 * PrintCurrentSettings           *
 *                                *
 * Prints the current settings of *
 * the lock with a space between  *
 * each digit. Note that there is *
 * no new line character so that  *
 * we can write "- click" on the  *
 * same line later.               *
 *********************************/
void PrintCurrentSettings(int n)
{
	int i;

	// print each digit with a space in between
	for (i = 0; i < n; i++)
		printf("%d ", Digits[i]);
}

/**********************************
 * OpenLock                       *
 *                                *
 * Where there real work is done. *
 * Finds the correct combination  *
 * by advancing each digit        *
 * individually until the correct *
 * digit is found.  Starts with   *
 * the first number on the left   *
 * and works its way to the right.*
 *********************************/
void OpenLock(int n)
{
	int i;
	int nSteps = 0;  // keeps track of how
		     // many steps are needed
			 // to open lock

	// print the starting combination
	PrintCurrentSettings(n);
	printf("\n");

	// start with first digit on the left
	// and work our way to the right
	for (i = 0; i < n; i++)
	{
		// change the ith digit until it is correct
		while (Digits[i] != Combo[i])
		{
			// advance 3 numbers and then do modulo 10.
			// this performs the appropriate "wrap-around"
			// of the digits.
			Digits[i] = (Digits[i] + 3) % 10;
			PrintCurrentSettings(n);
			nSteps++;

			// if this digit is correct, must output
			// appropriate message
			if (Digits[i] == Combo[i])
				printf("- click");
			printf("\n");
		}
	}

	// print how many steps were required to open lock
	printf("Locker opened in %d steps\n\n", nSteps);
}

void main()
{
	int nDigits;
	int ProblemNumber = 1;

	// assign file pointer
	fin = fopen("combo.in", "r");

	// make sure the file was opened properly
	assert(fin);

	// read number of digits in first lock
	fscanf(fin, "%d\n", &nDigits);

	// continue solving until nDigits == 0
	while (nDigits != 0)
	{
		printf("Lock #%d\n", ProblemNumber++);
		GetCurrentSettings(nDigits);
		GetCorrectCombo(nDigits);
		OpenLock(nDigits);

		// read number of digits in next lock
		fscanf(fin, "%d\n", &nDigits);
	}       
}
