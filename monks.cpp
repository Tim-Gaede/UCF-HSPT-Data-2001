/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Monks                                           */
/*    Written by: Peter Doege                                  */
/*    Solved by: Phillip Dexheimer                             */
/***************************************************************/
#include <stdio.h>
#include <stdlib.h>

void main ()
{
 FILE *In = fopen("monks.in", "r");
 int nCases, firstWall, nJumps;
 fscanf(In, "%d", &nCases);
 for (int i=0;i<nCases;i++)
  {
   fscanf(In, "%d", &firstWall);
   nJumps = 100 / firstWall;
   printf("%d\n", nJumps);
  }
 fclose(In);
}
