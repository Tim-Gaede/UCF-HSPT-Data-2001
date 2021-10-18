/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Hope                                            */
/*    Written by: Eric Heimburg / Kevin Wertman                */
/*    Solved by: Richard Russo                                 */
/***************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include <assert.h>

#define MAX 256

const char HOLE = 'o';
const char HINGE = '=';

char chest[MAX];

/**
 * indexOf returns the index (zero-based) of the first occurrence
 * of the character 'c' in the string 's' that occurs at or 
 * after the specified 'startIdx'.
 */
int indexOf (const char *s, char c, unsigned int startIdx = 0)
{
  if (startIdx > strlen (s)) return -1;

  while (s[startIdx] != '\0')
  {
    if (c == s[startIdx]) return startIdx;
    startIdx++;
  }
  return -1;
} // indexOf

/**
 * max returns the larger of the two values 'i' and 'j'.
 * If they are equal, it will return either one.
 */
int max (int i, int j)
{
  return (i > j) ? i : j;
}

/**
 * min returns the smaller of the two values 'i' and 'j'.
 * If they are equal, it will return either one.
 */
int min (int i, int j)
{
  return (i < j) ? i : j;
}

/**
 * findSolution sets the 'bestLeft' and 'bestRight' parameters to
 * the lengths of the best hinges (according to the problem specification).
 * The variable bestLeft will be set to the length of the hinge on the
 * left, and bestRight will be set to the length of the hinge on the right.
 * If there is no suitable solution, they will both be set to -1.
 */
void findSolution (int &bestLeft, int &bestRight)
{
  /*
   * bestLeft and bestRight keep track of the best solution found so
   * far.  After enumerating all possibilities for the set of two hinges,
   * these variables will hold the solution.
   */
  bestLeft = bestRight = -1;
  int leftStart, leftEnd, rightStart, rightEnd;

  /* 
   * A set of two hinges is evaluated based on the area that they cover,
   * the number of characters between the hinges, and the length of the
   * largest hinge in the set.  These variables keep track of the
   * best values found so far, and correspond to the bestRight and bestLeft
   * above.
   */
  int bestArea = -1, bestSeparation = -1, bestHinge = -1;

  /*
   * The following two 'for' loops enumerate all possibilities for the
   * set of two hinges.  leftStart and leftEnd mark the indices of the
   * start and end of the left hinge; similarly for rightStart and rightEnd.
   */

  for (leftStart = indexOf (chest, HOLE);
       leftStart >= 0;
       leftStart = leftEnd)
  {
    leftEnd = indexOf (chest, HOLE, leftStart + 1);
    if (leftEnd < 0) break;

    for (rightStart = indexOf (chest, HOLE, leftEnd + 1);
         rightStart >= 0;
         rightStart = rightEnd)
    {
      rightEnd = indexOf (chest, HOLE, rightStart + 1);
      if (rightEnd < 0) break;

      int left = leftEnd - leftStart - 1;
      int right = rightEnd - rightStart - 1;

      int area = left + right;
      int separation = rightStart - leftEnd - 1;
      int hinge = max (left, right);

      /*
       * After computing the area, separation, and largest hinge length
       * for the current set, compare them to the best found so far.
       */
      bool aCk = area > bestArea;                  // area check
      bool aEq = area == bestArea;                 // areas equal
      bool sCk = separation > bestSeparation;      // separation check
      bool sEq = separation == bestSeparation;     // separations equal
      bool hCk = hinge > bestHinge;                // hinge check

     /*
      * Determine if the current possibility is better than the best one found
      * so far.  Resolve ties according to the problem spec.
      */
     if (aCk || (aEq && sCk) || (aEq && sEq && hCk))
     {
       bestLeft = left;
       bestRight = right;
       bestArea = area;
       bestSeparation = separation;
       bestHinge = hinge;
     }
    }
  } // for (leftStart
} // findSolution

void solve (FILE *input)
{
  int numCases;

  fgets (chest, sizeof (chest), input);
  sscanf (chest, "%d", &numCases);

  for (int count = 0; count < numCases; count++)
  {
    fgets (chest, sizeof (chest), input);
    int left, right;
    findSolution (left, right);
    printf ("Hope Chest #%d: %d %d\n", count + 1,
            max (left, right), min (left, right));
  }
} // solve

int main (void)
{
  FILE *f = fopen ("hope.in", "r");
  solve (f);
  fclose (f);
  return 0;
}
