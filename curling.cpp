/***************************************************************/
/*            Fifteenth Annual UCF High School                 */
/*                 Programming Tournament                      */
/*                      May 4, 2001                            */
/*                                                             */
/*    Problem: Curling                                         */
/*    Written by: David Van Brackle / Ken Hadden               */
/*    Solved by: Jason Daly                                    */
/***************************************************************/
#include <stdio.h>
#include <math.h>

struct Point
{
    int x, y;
};

#define SQR(x) ( (x) * (x) )

double distance(Point p1, Point p2)
{
    return sqrt( SQR(p2.x - p1.x) + SQR(p2.y - p1.y));
}

void main(void)
{
    FILE   *fp;
    int    numEnds;
    int    set, i, j;

    // The location of the button
    Point  button;

    // The location of the current rock
    Point  rock;

    // The distances of the rocks to the button
    double whiteDist[10], blackDist[10];

    // The smallest distance from the rock seen so far in the current 
    // end from each team
    double closestWhite, closestBlack;

    // The score for this end
    int    score;

    // Compute the button location...
    // The sheet is 138 feet long, and the button is 12 feet from the
    // end line, so the X coordinate in inches is . . .
    button.y = (138 - 12) * 12;

    // Now, the sheet is 14 feet and 2 inches wide, and the button is
    // in the center, so the Y coordinate in inches is . . .
    button.x = ((14*12) + 2) / 2;

    // Open the input file
    fp = fopen("curling.in", "r");

    // Read in the number of ends
    fscanf(fp, "%d\n", &numEnds);

    for (set = 0; set < numEnds; set++)
    {
        // Initialize the closest distances to a really large number
        // (a hundred million inches should do)
        closestWhite = 1E8;
        closestBlack = 1E8;

        for (i = 0; i < 8; i++)
        {
            // Read in the location of the next white rock
            fscanf(fp, "%d %d", &(rock.x), &(rock.y));

            // Compute the distance from the button for this 
            // rock
            whiteDist[i] = distance(rock, button);

            // Check and see if this is closer than the closest
            // distance
            if (whiteDist[i] < closestWhite)
                closestWhite = whiteDist[i];
        }
        fscanf(fp, "\n");

        for (i = 0; i < 8; i++)
        {
            // Read in the location of the next black rock
            fscanf(fp, "%d %d", &(rock.x), &(rock.y));

            // Compute the distance from the button for this 
            // rock
            blackDist[i] = distance(rock, button);

            // Check and see if this is closer than the closest
            // distance
            if (blackDist[i] < closestBlack)
                closestBlack = blackDist[i];
        }
        fscanf(fp, "\n");

        // First, check to see if either team is close enough to
        // score, the maximum distance is 6 feet from the edge of
        // the rock.  Since the rock is a foot in diameter (six
        // inches in radius) this makes the maximum distance to the
        // center of the rock 6 feet, 6 inches (or 78 inches)
        if ((closestWhite > 78.0) && (closestBlack > 78.0))
        {
            // Neither team has a rock touching the house
            printf("Neither team scores\n");
        }
        else
        {
            // Initialize the score accumulator
            score = 0;

            // See who wins
            if (fabs(closestWhite - closestBlack) <= 0.01)
            {
                // It's a tie, nobody scores
                printf("Neither team scores\n");
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

                printf("White scores %d\n", score);
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

                printf("Black scores %d\n", score);
            }
        }
    }

    fclose(fp);
}
