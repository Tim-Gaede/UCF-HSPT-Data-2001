/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Nihongo                                         */
/*    Written by: Richard Russo                                */
/*    Solved by: Kevin Wertman                                 */
/***************************************************************/
import java.io.*;
import java.util.*;

/* this problem has several special cases that you have to 
 * watch out for.  The words 'desu' and 'ka' can appear multiple
 * times in the file.   The 'desu' only counts if it is by itself
 * and the second to last word in the sentence.
 *
 * Look out for words that have desu inside of them, such as 
 * hiserodesu.
 */

public class nihongo 
{

    public static void main(String args[]) throws IOException
    {

        /* open the file to be read */
        FileReader in =  new FileReader("nihongo.in");
                BufferedReader reader = new BufferedReader(in);
        
        Vector words = new Vector();
        
        String line;    
        String word = new String();
        String verb, verb_2;

        line = reader.readLine();
            
        /* terminating condition is the word yame in the sentance */
        while(line.indexOf("yame") < 0)
        {
            StringTokenizer strtok = new StringTokenizer(line, " ");
            words.removeAllElements();  
            
            /* fill the Vector with all individual words in the sentance */
            while(strtok.hasMoreTokens())
            {
                word = strtok.nextToken();
                words.addElement(word);
            }
            
            verb = (String) words.elementAt(words.size() - 2);

            /* our verb (2nd to last word) is the special case 
                         * desu, so we have to retrieve the preceding word
                         * from the words Vector
                         */
            if(verb.equals("desu"))
            {
                verb_2 = (String) words.elementAt(words.size() - 3);
                System.out.println("Hai, " + verb_2 + " " + verb);
            }
            /* our verb is not desu so we just print it out */
            else
            {
                System.out.println("Hai, " + verb);
            }
            
            line = reader.readLine();
        }
    } 
}

