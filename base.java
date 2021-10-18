/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Base                                            */
/*    Written by: Jason Daly                                   */
/*    Solved by: Glenn Martin                                  */
/***************************************************************/
import java.io.*;
import java.util.StringTokenizer;


public class base
{
   public static void main(String args[]) throws Exception
   {
      FileInputStream   file;
      BufferedReader    data;
      String            line;
      int               numBases;
      boolean           grid[][];
      int               i;
      int               j;
      StringTokenizer   st;
      String            token;
      int               x;
      int               y;
      int               numBombs;
      int               count;

      // Try to open input file
      try
      {
         file = new FileInputStream("base.in");
      }
      catch (FileNotFoundException e)
      {
         System.out.println("WARNING: Can't open input file!");
         return;
      }

      // Get a stream for the file
      data = new BufferedReader(new InputStreamReader(file));

      // Create a grid
      grid = new boolean[51][51];

      // Loop to process each data set
      line = data.readLine();
      numBases = Integer.parseInt(line);
      while (numBases >= 0)
      {
         // Initialize our representation of the grid -- false means there is 
         // no base there 
         for (i=0; i <= 50; i++)
         {
            for (j=0; j <= 50; j++)
               grid[j][i] = false;
         }

         // For each base, read in its location and mark the grid 
         for (i=0; i < numBases; i++)
         {
            line = data.readLine();

            st = new StringTokenizer(line);
            token = st.nextToken();
            x = Integer.parseInt(token);
            token = st.nextToken();
            y = Integer.parseInt(token);

            grid[y][x] = true;
         }

         // For each bomb, then mark that any base that may have been there 
         // is no longer there             
         line = data.readLine();
         numBombs = Integer.parseInt(line);
         for (i=0; i < numBombs; i++)
         {
            line = data.readLine();

            st = new StringTokenizer(line);
            token = st.nextToken();
            x = Integer.parseInt(token);
            token = st.nextToken();
            y = Integer.parseInt(token);

            grid[y][x] = false;
         }

         // Now, go through and print any bases we missed.  At the same 
         // time, count them 
         count = 0;
         for (j=0; j <= 50; j++)
         {
            for (i=0; i <= 50; i++)
            {
               if (grid[j][i] == true)
               {
                  count++;
                  System.out.println("We missed the base at (" +
                                     i + ", " + j + ")");
               }
            }
         }

         // If we didn't miss any, then say so 
         if (count == 0)
            System.out.println("All your base are belong to us!");

         // Don't forget the blank line after each set!
         System.out.println();

         // Get the next base 
         line = data.readLine();
         numBases = Integer.parseInt(line);
      }
   }
}

