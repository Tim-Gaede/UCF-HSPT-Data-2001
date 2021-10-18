/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Money                                           */
/*    Written by: Glenn Martin                                 */
/*    Solved by: Peter Doege                                   */
/***************************************************************/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

void main()
{
	FILE *infile;
	int cases;
	double d;
	infile=fopen("money.in","r");
	fscanf(infile,"%d",&cases);
	for (;cases>0;cases--)
	{
		fscanf(infile,"%lf",&d);
		d*=.64;
		printf("%.2lf\n",d);
	}
	fclose(infile);
}
