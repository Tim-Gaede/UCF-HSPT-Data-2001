/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Curling                                         */
/*    Written by: David Van Brackle / Ken Hadden               */
/*    Solved by: Jason Daly                                    */
/***************************************************************/
import java.io.*;
import java.util.StringTokenizer;

class curling
{
    class Point
    {
        public int x, y;

	public Point(int newX, int newY)
	{
	    x = newX;
	    y = newY;
	}
    }

    protected double square(double X)
    {
        return (X * X);
    }

    protected double distance(Point p1, Point p2)
    {
        return Math.sqrt( square(p2.x - p1.x) + square(p2.y - p1.y));
    }

    public void solve() throws IOException
    {
        FileReader      inputFile;
	BufferedReader  inputReader;
	String          inputLine;
	StringTokenizer lineTokens;

        int    numEnds;
        int    set, i, j;

        // The location of the button
        Point  button;

        // The location of the current rock
	int    tx, ty;
        Point  rock;

        // The distances of the rocks to the button
        double whiteDist[], blackDist[];

        // The smallest distance from the rock seen so far in the current 
        // end from each team
        double closestWhite, closestBlack;

        // The score for this end
        int    score;

        // Compute the button location...
        // The sheet is 138 feet long, and the button is 12 feet from the
        // end line, so the X coordinate in inches is . . .
        ty = (138 - 12) * 12;

        // Now, the sheet is 14 feet and 2 inches wide, and the button is
        // in the center, so the Y coordinate in inches is . . .
        tx = ((14*12) + 2) / 2;

        // Create a Point object for the button
	button = new Point(tx, ty);

        // Open the input file
        inputFile = new FileReader("curling.in");
	inputReader = new BufferedReader(inputFile);

        // Read in the number of ends
	inputLine = inputReader.readLine();
        numEnds = Integer.parseInt(inputLine);

        for (set = 0; set < numEnds; set++)
        {
            // Initialize the closest distances to a really large number
            // (a hundred million inches should do)
            closestWhite = 1E8;
            closestBlack = 1E8;

            // Read in the white rock line
	    inputLine = inputReader.readLine();
	    lineTokens = new StringTokenizer(inputLine, " ");

	    whiteDist = new double[8];
	    i = 0;

	    while (lineTokens.hasMoreTokens())
            {
                // Read in the location of the next white rock
		tx = Integer.parseInt(lineTokens.nextToken());
		ty = Integer.parseInt(lineTokens.nextToken());
                rock = new Point(tx, ty);

                // Compute the distance from the button for this 
                // rock
                whiteDist[i] = distance(rock, button);

                // Check and see if this is closer than the closest
                // distance
                if (whiteDist[i] < closestWhite)
                    closestWhite = whiteDist[i];

		i++;
            }

            // Read in the black rock line
	    inputLine = inputReader.readLine();
	    lineTokens = new StringTokenizer(inputLine, " ");

	    blackDist = new double[8];
	    i = 0;

            while (lineTokens.hasMoreTokens())
            {
                // Read in the location of the next white rock
		tx = Integer.parseInt(lineTokens.nextToken());
		ty = Integer.parseInt(lineTokens.nextToken());
                rock = new Point(tx, ty);

                // Compute the distance from the button for this 
                // rock
                blackDist[i] = distance(rock, button);

                // Check and see if this is closer than the closest
                // distance
                if (blackDist[i] < closestBlack)
                    closestBlack = blackDist[i];

		i++;
            }

            // First, check to see if either team is close enough to
            // score, the maximum distance is 6 feet from the edge of
            // the rock.  Since the rock is a foot in diameter (six
            // inches in radius) this makes the maximum distance to the
            // center of the rock 6 feet, 6 inches (or 78 inches)
            if ((closestWhite > 78.0) && (closestBlack > 78.0))
            {
                // Neither team has a rock touching the house
                System.out.println("Neither team scores");
            }
            else
            {
                // Initialize the score accumulator
                score = 0;

                // See who wins
                if (Math.abs(closestWhite - closestBlack) <= 0.01)
                {
                    // It's a tie, nobody scores
                    System.out.println("Neither team scores");
                }
                else if (closestWhite < closestBlack)
                {
                    // White wins, count up their score
                    // The score will be the number of White rocks
                    // that are closer to the button than the closest
                    // Black rock and within 150 inches of the button
                    for (i = 0; i < 8; i++)
                    {
                        if ((whiteDist[i] < closestBlack) &&
                            (whiteDist[i] <= 150.0))
                            score++;
                    }

                    System.out.println("White scores " + score);
                }
                else
                {
                    // Black wins
                    for (i = 0; i < 8; i++)
                    {
                        if ((blackDist[i] < closestWhite) &&
                            (blackDist[i] <= 150.0))
                            score++;
                    }

                    System.out.println("Black scores " + score);
                }
            }
        }

        inputReader.close();
    }

    public static void main(String args[])
    {
        curling problem;

	problem = new curling();

        try
	{
	    problem.solve();
	}
	catch (Exception e)
	{
	}
    }
}
