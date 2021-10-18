/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Bumper                                          */
/*    Written by: Peter Doege                                  */
/*    Solved by: Phillip Dexheimer                             */
/***************************************************************/
import java.lang.*;
import java.io.*;
import java.util.*;

public class bumper
{
 public static void main (String []args)
  {
   int nCases, X, Y, r;
   String inLine;
   try {
     BufferedReader br = new BufferedReader(new FileReader("bumper.in"));
     inLine = br.readLine();
     nCases = Integer.parseInt(inLine);
     for (int i=0;i<nCases;i++)
      {
       inLine = br.readLine();
       StringTokenizer st = new StringTokenizer(inLine);
       X = Integer.parseInt(st.nextToken());
       Y = Integer.parseInt(st.nextToken());
       r = Integer.parseInt(st.nextToken());
//This is the only tricky part of the problem
//Since the airplane is at the origin, the distance formula
//is simplified.  And since we're checking circles, we just
//sum the radii.
       if (Math.sqrt(X*X+Y*Y) <= r+10)
         System.out.println("Danger");
       else
         System.out.println("Ok");
      }
   } catch (Exception e) {}
  }
};
