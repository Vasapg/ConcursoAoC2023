package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FertlizerThree 
{
    public static void main(String[] args) 
    {
        String path = "Day5/test2";
        String ranges = "";
        ArrayList<ArrayList<String>> maps = new ArrayList<>();
        maps = set_maps(path);
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            ranges = reader.readLine();
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        long number = 1000;
        long seed = get_seed(number, maps);
        while (!check_ranges(ranges, seed))
        {
//            System.out.println("pos: " + number + " seed: " + seed);
            number++;
            seed = get_seed(number, maps);
        }
        System.out.println("FIN => pos: " + number + " seed: " + seed);
    }

    public static long get_seed(long number, ArrayList<ArrayList<String>> maps)
    {
        int i = maps.size() - 1;
        while (i >= 0)
        {
            Boolean modified = false;
            for (int j = 0; j < maps.get(i).size() && !modified; j++)
            {
                //System.out.println(maps.get(i).get(j));
                Long source = Long.parseLong(maps.get(i).get(j).split(" ")[1]);
                Long destination = Long.parseLong(maps.get(i).get(j).split(" ")[0]);
                Long length = Long.parseLong(maps.get(i).get(j).split(" ")[2]);
                //System.out.println("destination start: " + destination + " source start: " + source + " length: " + length);
                if (number >= destination && number <= destination + length)
                {
                    number = number + source - destination;
                    modified = true;
                }            
            }
            i--;
        }
        return number;
    }

    public static boolean check_ranges(String ranges, long number)
    {
        StringTokenizer tokenizer = new StringTokenizer(ranges);
        tokenizer.nextToken();
        long start = Long.parseLong(tokenizer.nextToken());
        long length = Long.parseLong(tokenizer.nextToken());
        while (tokenizer.hasMoreTokens())
        {
            if (number >= start && number <= start + length)
                return true;
            start = Long.parseLong(tokenizer.nextToken());
            length = Long.parseLong(tokenizer.nextToken());
        }
        return false;
    }

    public static ArrayList<ArrayList<String>> set_maps(String path)
    {
        BufferedReader reader;
        ArrayList<ArrayList<String>> maps = new ArrayList<>();
        try
        {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            int i = -1;
            while (line != null)
            {
                if (line.contains("map"))
                {
                    //System.out.println(line);
                    i++;
                    maps.add(new ArrayList<String>());
                }
                while (line != null && line.length() > 4 && !line.contains("map") && !line.contains("seeds"))
                {
                    maps.get(i).add(line);
                    //System.out.println("i: " + i +" line: " + line);
                    line = reader.readLine();
                }
                line = reader.readLine();
            }
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return maps;
    }
}
