/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Yoda                                            */
/*    Written by: Glenn Martin                                 */
/*    Solved by: Carlos Rosas-Anderson                         */
/***************************************************************/
import java.io.*;

public class yoda
{
  public static void main(String[] args)
  {
    try
    {
      BufferedReader inputFile
        = new BufferedReader(new FileReader(new File("yoda.in")));

      int i, n;
      String sentence;

      // Read the number of sentences from the file
      n = Integer.parseInt(inputFile.readLine());

      // Process each of the n sentences
      for(i = 0; i < n; i++)
      {
        // Read a sentence from the file
        sentence = inputFile.readLine();

        // Print the sentence
        System.out.print(sentence);

        // Print "  Hmmm?" if the sentence ends with '?'
        if(sentence.endsWith("?"))
          System.out.print("  Hmmm?");

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
