#include <stdio.h>

main()
{
   FILE   *infile;
   char   line[80];
   int    numRooms;
   int    i;
   int    roomSize[50];
   int    j;
   int    tmp;
   int    count;
   int    turpentineFeetLeft;

   /* Open the input file */
   infile = fopen("avenger.in", "r");

   /* Loop for each room */
   fgets(line, sizeof(line), infile);
   sscanf(line, "%d", &numRooms);
   while (numRooms != 0) 
   {
      /* Read in the room square footage */
      for (i=0; i < numRooms; i++)
      {
         fgets(line, sizeof(line), infile);
         sscanf(line, "%d", &roomSize[i]);
      }

      /* Sort the room sizes.  This lets us pick the first N rooms that */
      /* will fit under The Turpentine Avenger's maximum of 100 gallons */
      /* of turpentine (each gallon cleans 50 square feet of paint)     */
      for (i=0; i < numRooms-1; i++)
      {
         for (j=i+1; j < numRooms; j++)
         {
            if (roomSize[i] > roomSize[j]) 
            {
               tmp = roomSize[i];
               roomSize[i] = roomSize[j];
               roomSize[j] = tmp;
            }
         }
      }

      /* Now just use as many rooms from the front of the sorted list as */
      /* we can (based on how much turpentine we have)                   */
      count = 0;
      turpentineFeetLeft = 100 * 50;
      for (i=0; i < numRooms; i++)
      {
         if (turpentineFeetLeft >= roomSize[i])
         {
            count++;
            turpentineFeetLeft = turpentineFeetLeft - roomSize[i];
         }
      }

      /* Print the count */
      printf("%d\n\n", count);

      /* Get the next room */
      fgets(line, sizeof(line), infile);
      sscanf(line, "%d", &numRooms);
   }

   /* Close the input file */
   fclose(infile);
}
