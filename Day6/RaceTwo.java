package Day6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class RaceTwo 
{
    public static void main(String[] args) throws IOException 
    {
        String path = "Day6/test2";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringTokenizer times = new StringTokenizer(reader.readLine());
        StringTokenizer distances = new StringTokenizer(reader.readLine());
        String time = "";
        String distance = "";
        long result = 0;
        times.nextToken();
        distances.nextToken();
        while (times.hasMoreTokens() && distances.hasMoreTokens())
        {
            time += (Integer.parseInt(times.nextToken()));
            distance += (Integer.parseInt(distances.nextToken()));
        }
        result = get_records(Long.parseLong(time), Long.parseLong(distance));
        System.out.println(result);
        reader.close();
    }    

    public static int get_records(long time, long distance)
    {
        int result = 0;
        boolean stop = false;
        
        System.out.println("time: " + time + " distance: " + distance);
        for (long velocity = 0; velocity < time && !stop; velocity++)
        {
            long new_distance = velocity * (time - velocity);
            if (new_distance > distance)
            {
                result++;
            }
            else if (result != 0)
                stop = true;

        }
        return result;
    }
}
