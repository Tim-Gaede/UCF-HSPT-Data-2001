
import java.io.*;


public class avenger
{
   public static void main(String args[]) throws Exception
   {
      FileInputStream   file;
      BufferedReader    data;
      String            line;
      int               numRooms;
      int               i;
      int               roomSize[];
      int               j;
      int               tmp;
      int               count;
      int               turpentineFeetLeft;

      // Try to open input file
      try
      {
         file = new FileInputStream("avenger.in");
      }
      catch (FileNotFoundException e)
      {
         System.out.println("WARNING: Can't open input file!");
         return;
      }

      // Get a stream for the file
      data = new BufferedReader(new InputStreamReader(file));

      // Loop for each room
      line = data.readLine();
      numRooms = Integer.parseInt(line);
      while (numRooms != 0)
      {
         // Create an array for the room sizes
         roomSize = new int[numRooms];

         // Read in the room square footage
         for (i=0; i < numRooms; i++)
         {
            line = data.readLine();
            roomSize[i] = Integer.parseInt(line);
         }

         // Sort the room sizes.  This lets us pick the first N rooms that 
         // will fit under The Turpentine Avenger's maximum of 100 gallons 
         // of turpentine (each gallon cleans 50 square feet of paint)  
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

         // Now just use as many rooms from the front of the sorted list as 
         // we can (based on how much turpentine we have) 
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

         // Print the count
         System.out.println(count);
	 System.out.println();

         // Get the next room 
         line = data.readLine();
         numRooms = Integer.parseInt(line);
      }
   }
}

