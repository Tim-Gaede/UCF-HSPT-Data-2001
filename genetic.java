/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Genetic                                         */
/*    Written by: Phillip Dexheimer                            */
/*    Solved by: Carlos Rosas-Anderson                         */
/***************************************************************/
import java.util.*;
import java.io.*;

public class genetic
{
  public static void main(String[] args)
  {
    try
    {
      BufferedReader inputFile
        = new BufferedReader(new FileReader(new File("genetic.in")));

      int i, n, b;
      StringBuffer gene;

      // Read the number of cases from the file
      n = Integer.parseInt(inputFile.readLine());

      // Process each of the n cases
      for(i = 0; i < n; i++)
      {
        // Read the bit to be mutated from the file
        b = Integer.parseInt(inputFile.readLine());

        // Read a gene from the file
        gene = new StringBuffer(inputFile.readLine());

        // Mutate the bit
        if(gene.charAt(b - 1) == '1')
          gene.setCharAt(b - 1, '0');
        else
          gene.setCharAt(b - 1, '1');

        // Print the gene
        System.out.println(gene);
        System.out.println();
      }

      // Close the input file
      inputFile.close();
    }
    catch(IOException e)
    {
    }
  }
}
