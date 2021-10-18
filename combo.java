/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Combo                                           */
/*    Written by: Kevin Wertman                                */
/*    Solved by: Ken Hadden                                    */
/***************************************************************/
import java.io.*;
import java.util.StringTokenizer;

public class combo {
	static int Digits[] = new int[9];  // current settings of lock
	static int Combo[] = new int[9];   // correct settings of lock
    static BufferedReader Input;       // the input stream

    /**********************************
     * GetCurrentSettings             *
     *                                *
     * Reads the starting settings    *
     * of the lock from the file      *
     *********************************/
    static void GetCurrentSettings(int n) throws IOException
	{
          // read a line from the file
          String line = Input.readLine();

          // assign the line to a tokenizer
          StringTokenizer tok = new StringTokenizer(line);

          // pull each digit from the input line
          // and put it into the Digits array
          for (int i = 0; i < n; i++)
          {
             Digits[i] = Integer.parseInt( tok.nextToken() );
          }
	}

    /**********************************
     * GetCorrectCombo                *
     *                                *
     * Reads the correct combination  *
     * of the lock from the file      *
     *********************************/
    static void GetCorrectCombo(int n) throws IOException
	{
          // read a line from the file
          String line = Input.readLine();

          // assign the line to a tokenizer
          StringTokenizer tok = new StringTokenizer(line);

          // pull each digit from the input line
          // and put it into the Combo array
          for (int i = 0; i < n; i++)
          {
             Combo[i] = Integer.parseInt( tok.nextToken() );
          }

	}

    /**********************************
     * PrintCurrentSettings           *
     *                                *
     * Prints the current settings of *
     * the lock with a space between  *
     * each digit. Note that there is *
     * no new line character so that  *
     * we can write "- click" on the  *
     * same line later.               *
     *********************************/
	static void PrintCurrentSettings(int n)
	{
        // print each digit with a space in between
		for (int i = 0; i < n; i++)
			System.out.print( Digits[i] + " " );
	}

    /**********************************
     * OpenLock                       *
     *                                *
     * Where there real work is done. *
     * Finds the correct combination  *
     * by advancing each digit        *
     * individually until the correct *
     * digit is found.  Starts with   *
     * the first number on the left   *
     * and works its way to the right.*
     *********************************/
    static void OpenLock(int n)
	{
            int nSteps = 0;  // keeps track of how
                             // many steps are needed
                             // to open lock

            // print the starting combination
            PrintCurrentSettings(n);
            System.out.println();

            // start with first digit on the left
            // and work our way to the right
            for (int i=0; i<n; i++)
            {
              // change the ith digit until it is correct
              while (Digits[i] != Combo[i])
              {
                  // advance 3 numbers and then perform modulo 10.
                  // this performs the appropriate "wrap-around"
                  // of the digits
                  Digits[i] = (Digits[i] + 3) % 10;
                  PrintCurrentSettings(n);
                  nSteps++;

                  // if this digit is correct, print
                  // appropriate message
                  if (Digits[i] == Combo[i])
                    System.out.print("- click");

                  System.out.println();
              }
            }

            // print how many steps were required to open the lock
            System.out.println("Locker opened in " +
                                nSteps + " steps" );
			System.out.println();//moved /n from above
	}


	public static void main( String args[] ) throws IOException
	{
            int ProblemNumber = 1;
            
            // Create input reader
            Input = new BufferedReader( new FileReader("combo.in") );

            // get first line of input file
            String line = Input.readLine();

            // get the number of digits in the first lock
            int nDigits = Integer.parseInt(line);

            // continue solving until nDigits == 0
            while (nDigits != 0)
            {
                System.out.println( "Lock #" + ProblemNumber++ );
                GetCurrentSettings(nDigits);
                GetCorrectCombo(nDigits);
                OpenLock(nDigits);

                // read in line from input file
                line = Input.readLine();

                // get the number of digits in next lock
                nDigits = Integer.parseInt(line);
            }
	}
}
