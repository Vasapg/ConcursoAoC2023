package Day6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Race 
{
    public static void main(String[] args) throws IOException 
    {
        String path = "Day6/test2";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringTokenizer times = new StringTokenizer(reader.readLine());
        StringTokenizer distances = new StringTokenizer(reader.readLine());
        ArrayList<Integer> time = new ArrayList<>();
        ArrayList<Integer> distance = new ArrayList<>();
        int result = 1;
        times.nextToken();
        distances.nextToken();
        while (times.hasMoreTokens() && distances.hasMoreTokens())
        {
            time.add(Integer.parseInt(times.nextToken()));
            distance.add(Integer.parseInt(distances.nextToken()));
        }
        for (int i = 0; i < time.size(); i++)
        {
            result = result * get_records(time.get(i), distance.get(i));
        }
        System.out.println(result);
        reader.close();
    }    

    public static int get_records(int time, int distance)
    {
        int result = 0;
        for (int velocity = 0; velocity < time; velocity++)
        {
            int new_distance = velocity * (time - velocity);
            if (new_distance > distance)
                result++;
        }
        return result;
    }
}
