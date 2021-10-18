/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Monks                                           */
/*    Written by: Peter Doege                                  */
/*    Solved by: Phillip Dexheimer                             */
/***************************************************************/
import java.io.*;
import java.lang.*;

public class monks
{
 public static void main (String []args)
  {
   int nCases, firstWall, nJumps;
   String inLine;
   try {
     BufferedReader br = new BufferedReader(new FileReader("monks.in"));
     inLine = br.readLine();
     nCases = Integer.parseInt(inLine);
     for (int i=0;i<nCases;i++)
      {
       inLine = br.readLine();
       firstWall = Integer.parseInt(inLine);
       nJumps = 100 / firstWall;
       System.out.println(nJumps);
      }
   } catch (Exception e) {}
  }
};
