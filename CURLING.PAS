(***************************************************************)
(*            Fifteenth Annual UCF High School                 *)
(*                 Programming Tournament                      *)
(*                      May 4, 2001                            *)
(*                                                             *)
(*    Problem: Curling                                         *)
(*    Written by: David Van Brackle / Ken Hadden               *)
(*    Solved by: Jason Daly                                    *)
(***************************************************************)
program curling(input, output);

type
    Point = record
      x,y:longint;
    end;

var
    fp      : text;
    temp    : string;
    numEnds : integer;
    setNum  : integer;
    i       : integer;

    { The location of the button }
    button : Point;

    { The location of the current rock }
    rock : Point;

    { The distances of the rocks to the button }
    whiteDist, blackDist : array[1..8] of real;

    { The smallest distance from the rock seen so far in the current
      end from each team }
    closestWhite, closestBlack : real;

    { The score for this end }
    score : integer;


function distance(p1, p2 : Point) : real;
begin
    distance := sqrt(sqr(p2.x - p1.x) + sqr(p2.y - p1.y));
end;

begin {main}

    { Compute the button location...
       The sheet is 138 feet long, and the button is 12 feet from the
       end line, so the X coordinate in inches is . . . }
    button.y := (138 - 12) * 12;

    { Now, the sheet is 14 feet and 2 inches wide, and the button is
      in the center, so the Y coordinate in inches is . . . }
    button.x := ((14*12) + 2) DIV 2;

    { Open the input file }
    assign(fp, 'curling.in');
    reset(fp);

    { Read in the number of ends }
    readln(fp, numEnds);

    for setNum := 1 to numEnds do
    begin
        { Initialize the closest distances to a really large number
          (a hundred million inches should do) }
        closestWhite := 1E8;
        closestBlack := 1E8;

        for i := 1 to 8 do
        begin
            { Read in the location of the next white rock }
            read(fp, rock.x, rock.y);

            { Compute the distance from the button for this
              rock }
            whiteDist[i] := distance(rock, button);

            { Check and see if this is closer than the closest
              distance }
            if (whiteDist[i] < closestWhite) then
                closestWhite := whiteDist[i];
        end;

        readln(fp);

        for i := 1 to 8 do
        begin
            { Read in the location of the next black rock }
            read(fp, rock.x, rock.y);

            { Compute the distance from the button for this
              rock }
            blackDist[i] := distance(rock, button);

            { Check and see if this is closer than the closest
              distance }
            if (blackDist[i] < closestBlack) then
                closestBlack := blackDist[i];
        end;

        readln(fp);

        { First, check to see if either team is close enough to
          score, the maximum distance is 6 feet from the edge of
          the rock.  Since the rock is a foot in diameter (six
          inches in radius) this makes the maximum distance to the
          center of the rock 6 feet, 6 inches (or 6 inches) }
        if ((closestWhite > 78.0) and (closestBlack > 78.0)) then
        begin
            { Neither team has a rock touching the house }
            writeln('Neither team scores');
        end
        else
        begin
            { Initialize the score accumulator }
            score := 0;

            { See who wins }
            if (abs(closestWhite - closestBlack) <= 0.01) then
                writeln('Neither team scores')
            else if (closestWhite < closestBlack) then
            begin
                { White wins, count up their score
                  The score will be the number of White rocks
                  that are closer to the button than the closest
                  Black rock and within 150 inches of the button }
                for i := 1 to 8 do
                begin
                    if ((whiteDist[i] < closestBlack) and
                        (whiteDist[i] <= 150.0)) then
                        inc(score);
                end;

                writeln('White scores ', score);
            end
            else
            begin
                { Black wins }
                for i := 1 to 8 do
                begin
                    if ((blackDist[i] < closestWhite) and
                        (blackDist[i] <= 150.0)) then
                        inc(score);
                end;

                writeln('Black scores ', score);
            end;
        end;
    end;

    close(fp);
end.
