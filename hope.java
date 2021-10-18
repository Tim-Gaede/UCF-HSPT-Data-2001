/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Hope                                            */
/*    Written by: Eric Heimburg / Kevin Wertman                */
/*    Solved by: Richard Russo                                 */
/***************************************************************/
import java.io.*;
import java.util.*;

class hope
{
  BufferedReader stdin;
  String         chestString;

  final static char HINGE = '=';
  final static char HOLE  = 'o';

  public hope ()
  throws Exception
  {
    stdin = new BufferedReader (new FileReader ("hope.in"));
  }

  void readChest ()
  throws Exception
  {
    chestString = stdin.readLine ();
  }

  /**
   * findSolution computes the lengths of the best set of two
   * hinges, and returns them in an array.  The first element
   * of the array is the length of the hinge on the left.  The second
   * is the length of the hinge on the right.  If there are no
   * valid solutions, {-1, -1} is the return value.
   */
  int[] findSolution ()
  {
    /*
     * bestLeft and bestRight keep track of the best solution found so
     * far.  After enumerating all possibilities for the set of two hinges,
     * these variables will hold the solution.
     */
    int bestLeft = -1, bestRight = -1;

    /* 
     * A set of two hinges is evaluated based on the area that they cover,
     * the number of characters between the hinges, and the length of the
     * largest hinge in the set.  These variables keep track of the
     * best values found so far, and correspond to the bestRight and bestLeft
     * above.
     */

    int bestArea = -1, bestSeparation = -1, bestHinge = -1;
    
    int leftStart, leftEnd, rightStart, rightEnd;

    /*
     * The following two 'for' loops enumerate all possibilities for the
     * set of two hinges.  leftStart and leftEnd mark the indices of the
     * start and end of the left hinge; similarly for rightStart and rightEnd.
     */

    for (leftStart = chestString.indexOf (HOLE); leftStart >= 0; leftStart = leftEnd)
    {
      leftEnd = chestString.indexOf (HOLE, leftStart + 1);
      if (leftEnd < 0) break;
      for (rightStart = chestString.indexOf (HOLE, leftEnd + 1); rightStart >= 0;
           rightStart = rightEnd)
      {
        rightEnd = chestString.indexOf (HOLE, rightStart + 1);
        if (rightEnd < 0) break;
        int left = leftEnd - leftStart - 1;
        int right = rightEnd - rightStart - 1;

        int area = left + right;
        int separation = rightStart - leftEnd - 1;
        int hinge = Math.max (left, right);

        /*
         * After computing the area, separation, and largest hinge length
         * for the current set, compare them to the best found so far.
         */

        boolean aCk = area > bestArea;               // area check
        boolean aEq = area == bestArea;              // areas equal
        boolean sCk = separation > bestSeparation;   // separation check
        boolean sEq = separation == bestSeparation;  // separations equal
        boolean hCk = hinge > bestHinge;             // hinge check


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
      } // right
    } // left
    return new int[] { bestLeft, bestRight };
  } // findSolution

  void solve ()
  throws Exception
  {
    int numChests = Integer.parseInt (stdin.readLine ());
    for (int count = 0; count < numChests; count++)
    {
      readChest ();
      int[] sol = findSolution ();
      int set = count + 1;
      Arrays.sort (sol);    // sort the array in ascending order for output
      System.out.println ("Hope Chest #" + set + ": " + sol[1] + " " + sol[0]);
    }
  } // solve

  public static void main (String[] args)
  throws Exception
  {
    hope h = new hope ();
    h.solve ();
  } // main

} // class hope
