/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Base                                            */
/*    Written by: Jason Daly                                   */
/*    Solved by: Glenn Martin                                  */
/***************************************************************/
#include <stdio.h>

main()
{
   FILE   *infile;
   char   line[80];
   int    numBases;
   int    grid[51][51];
   int    i;
   int    j;
   int    numBombs;
   int    x;
   int    y;
   int    count;

   /* Open the input file */
   infile = fopen("base.in", "r");

   /* Loop to process each data set */
   fgets(line, sizeof(line), infile);
   sscanf(line, "%d", &numBases);
   while (numBases >= 0) 
   {
      /* Initialize our representation of the grid -- false means there is */
      /* no base there                                                     */
      for (i=0; i <= 50; i++)
      {
         for (j=0; j <= 50; j++)
            grid[j][i] = 0;
      }

      /* For each base, read in its location and mark the grid */
      for (i=0; i < numBases; i++)
      {
         fgets(line, sizeof(line), infile);
         sscanf(line, "%d %d", &x, &y);
         grid[y][x] = 1;
      }

      /* For each bomb, then mark that any base that may have been there */
      /* is no longer there                                              */
      fgets(line, sizeof(line), infile);
      sscanf(line, "%d", &numBombs);
      for (i=0; i < numBombs; i++)
      {
         fgets(line, sizeof(line), infile);
         sscanf(line, "%d %d", &x, &y);
         grid[y][x] = 0;
      }

      /* Now, go through and print any bases we missed.  At the same */
      /* time, count them                                            */
      count = 0;
      for (j=0; j <= 50; j++)
      {
         for (i=0; i <= 50; i++)
         {
            if (grid[j][i] == 1) 
            {
               count++;
               printf("We missed the base at (%d, %d)\n", i, j);
            }
         }
      }

      /* If we didn't miss any, then say so */
      if (count == 0) 
         printf("All your base are belong to us!\n");

      /* Don't forget the blank line after each set! */
      printf("\n");

      /* Read in the next base */
      fgets(line, sizeof(line), infile);
      sscanf(line, "%d", &numBases);
   }

   /* Close the file */
   fclose(infile);
}
