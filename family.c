/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Family                                          */
/*    Written by: Richard Russo                                */
/*    Solved by: Richard Urich                                 */
/***************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

char **name;//array of strings (person's name)
char **value;//array of strings (value of the person's age)
int namecount,//number of names
    currname,//current name being scanned in
	dispcount;//current name being displayed

int findindex(char *s)
{//find the index of name "s" in the name array
	int index=0;//start at index 0
	while ((strcmp(name[index],s)!=0)&&//while this isn't the string
		   (index<namecount))//and we haven't passed the end of the array
		index++;//advance to next index
	return index;//return index of name
}//int findindex(char *s)

void addtolist(char *s)
{//add name, value pair to the name and value arrays (as read from input)
	char *space;//pointer to the space between name and value
	space=strchr(s,' ');
	space[0]='\0';//seperate the name from value by null-terminating
	name[currname]=strdup(s);//name is the string up to the first space
	value[currname]=strdup(space+1);//value is the string after the first space
}//void addtolist(char *s)

void dispval(char *instr)
{//display the value of the name passed in
	int tot,val;//total (the answer), and val (the current scanned value)
	char *sign,*s;//pointer to the sign of the string, and the string we'll manipulate
	s=malloc(sizeof(char)*50);
	  //50 is enough for a maxlen name (12), a '\0', a sign '+' or '-', and 36 digits
	strcpy(s,instr);//make a new copy of string passed in
	tot=val=0;
	while (isalpha(s[0]))
	{//while there is a name in the string
		sign=strpbrk(s,"+-");//assign "sign" to point to the '+' or '-'
		if (sign!=NULL)//if there was a '+' or '-'
		{
			sscanf(sign,"%d",&val);//scan the number
			tot+=val;//add the scanned value to the total
			sign[0]='\0';//leave only the name
		}
		strcpy(s,value[findindex(s)]);//switch the name with it's value
	}
	sscanf(s,"%d",&val);//scan the last value
	tot+=val;//add the scanned value to total
	if (tot==1) printf("%s is 1 year old.\n",instr);
	else printf("%s is %d years old.\n",instr,tot);
	free(s);//deallocate space
}//void dispval(char *instr)

int main()
{
	FILE *infile;//input file
	char *s;//string read from file
	s=(char*)malloc(1024*sizeof(char));//allocate space
	infile=fopen("family.in","r");//open the input file
	while (fgets(s,1020,infile)!=NULL)
	{//while reading a line of data from the input file does not fail
		if (s[strlen(s)-1]=='\n')
			s[strlen(s)-1]='\0';//if there is a newline character, delete it
		sscanf(s,"%d",&namecount);//scan number of names
		if (namecount==0) break;
		name=(char**)malloc(namecount*sizeof(char*));//allocate space for name
		value=(char**)malloc(namecount*sizeof(char*));//allocate space for value
		for (currname=0;currname<namecount;currname++)
		{
			fgets(s,1020,infile);//read a line of data from the input file
			if (s[strlen(s)-1]=='\n')
				s[strlen(s)-1]='\0';//if there is a newline character, delete it
			addtolist(s);
		}//for (currname=0;currname<namecount;currname++)
		fgets(s,1020,infile);//read a line of data from the input file
		if (s[strlen(s)-1]=='\n')
			s[strlen(s)-1]='\0';//if there is a newline character, delete it
		sscanf(s,"%d",&dispcount);//scan number of ages to display
		for (;dispcount>0;dispcount--)
		{
			fgets(s,1020,infile);//read a line of data from the input file
			if (s[strlen(s)-1]=='\n')
				s[strlen(s)-1]='\0';//if there is a newline character, delete it
			dispval(s);//display value of name in "s"
		}//for (;dispcount>0;dispcount--)
		printf("\n");//blank line between data sets
		while (namecount>0)
		{//while there are still names in "name" (and values in "value")
			free(name[--namecount]);//deallocate
			free(value[namecount]);//deallocate
		}//while (namecount>0)
		free(name);//deallocate
		free(value);//deallocate
	}//while (fgets(s,1020,infile)!=NULL)
	free(s);//deallocate
	fclose(infile);//close input file
	return 0;
}//int main()
