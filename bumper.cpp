/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Bumper                                          */
/*    Written by: Peter Doege                                  */
/*    Solved by: Phillip Dexheimer                             */
/***************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

void main ()
{
 int nCases;
 FILE *In = fopen("bumper.in", "r");
 int X, Y, r;
 fscanf(In, "%d", &nCases);
 for (int i=0;i<nCases;i++)
  {
   fscanf(In, "%d %d %d", &X, &Y, &r);
//This is the only tricky part of the problem
//Since the airplane is at the origin, the distance formula
//is simplified.  And since we're checking circles, we just
//sum the radii.
   if (sqrt(X*X+Y*Y) <= r+10)
     printf("Danger\n");
   else
     printf("Ok\n");
  }
 fclose(In);
}
