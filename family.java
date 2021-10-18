/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Family                                          */
/*    Written by: Richard Russo                                */
/*    Solved by: Richard Urich                                 */
/***************************************************************/
import java.io.*;
import java.util.*;

class family 
{
	static BufferedReader infile,stdin=new BufferedReader(new InputStreamReader(System.in));
	static String s,v,n;
	static int namecount,dispcount,currname,x,y,z,currval;
	static Vector name=new Vector(), val=new Vector();
	public static void main(String[] args) throws IOException
	{
		infile = new BufferedReader(new FileReader("family.in"));
		namecount=Integer.parseInt(infile.readLine());
		   //get number of name, age pairs
		while (namecount!=0)
		{
			name.clear();//clear name vector
			val.clear();//clear val vector
			for (currname=0;currname<namecount;currname++)
			{
				s=infile.readLine();
				name.addElement(s.substring(0,s.indexOf(' ')));
				   //add text up to first space to name vector
				val.addElement(s.substring(s.indexOf(' ')+1));
				   //add text after space to value vector
			}
			dispcount=Integer.parseInt(infile.readLine());
			   //get number of names to display
			for (currname=0;currname<dispcount;currname++)
			{
				s=infile.readLine();
				System.out.print(s + " is ");//print name (age later)
				currval=0;//set age to 0
				while (Character.isLetter(s.charAt(0)))
				{//while there's still a name in s
					if (s.indexOf('+')>=0)
					{//if it's adding to age
						currval+=Integer.parseInt(s.substring(s.indexOf('+')+1));
						   //add the number to the age
						s=s.substring(0,s.indexOf('+'));//delete the number from s
					}
					else if (s.indexOf('-')>=0)
					{//if it's subtracting from age
						currval-=Integer.parseInt(s.substring(s.indexOf('-')+1));
						   //subtract the number from age
						s=s.substring(0,s.indexOf('-'));//delete the number from s
					}
					s=(String)val.elementAt(name.indexOf(s));
					   //set s to the value of the name in s
				}//while
				currval+=Integer.parseInt(s);//add value of s to age
				if (currval==1) System.out.println("1 year old.");
				else System.out.println(currval + " years old.");//print age
			}//for
			System.out.println();
			namecount=Integer.parseInt(infile.readLine());
			   //get number of name, age pairs
		}//while
	}//main
}//family
