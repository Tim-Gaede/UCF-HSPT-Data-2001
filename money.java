/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Money                                           */
/*    Written by: Glenn Martin                                 */
/*    Solved by: Peter Doege                                   */
/***************************************************************/
import java.io.*;
import java.text.*;

public class money
{
  public static void main(String[] args)
  {
    try
    {
      BufferedReader inputFile
        = new BufferedReader(new FileReader(new File("money.in")));

	  //Create the number formatter...
	  DecimalFormat nm = new DecimalFormat ("0.00");

      int i, n;
      double Amount;
	  String OutputString;

      // Read the number of sentences from the file
      n = Integer.parseInt(inputFile.readLine());

      // Process each of the n sentences
      for(i = 0; i < n; i++)
      {
        // Read a sentence from the file
        Amount = Float.parseFloat(inputFile.readLine());

		//Convert to CDN currency
		Amount = 0.64 * Amount;

		//Convert the currency float into a printable string
		OutputString = nm.format(Amount);

        // Print the sentence
        System.out.print(OutputString);

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
