package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Fertilizer 
{
    public static void main(String[] args) 
    { 
        BufferedReader reader;
        try 
        {
            reader = new BufferedReader(new FileReader("Day5/test2"));
            String line = reader.readLine();
            ArrayList<Long> seeds = new ArrayList<>();
            for (String number : line.trim().split(":")[1].split(" "))
            {
                if (!number.equals("") && !number.equals("seeds"))
                {
                    seeds.add(Long.parseLong(number));
                }
            }
            System.out.println("seeds: " + seeds.toString());
            while (line != null)
            {
                if (line.contains("map"))
                {
                    seeds= convert_to_map(reader, seeds);
                    System.out.println(seeds.toString());
                }
                line = reader.readLine();
            }
            reader.close();
            Long min = Long.MAX_VALUE;
            for (Long number : seeds)
            {
                if (number < min)
                    min = number;
            }
            System.out.println(min);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static ArrayList<Long> convert_to_map(BufferedReader reader, ArrayList<Long> seeds) throws IOException
    {
        String line = reader.readLine().trim();
        Boolean [] modified = new Boolean[seeds.size()];
        for (int i = 0; i < seeds.size(); i++)
            modified[i] = false;
        while (line != null && line.length() > 4)
        {
            //System.out.println(line);
            Long source = Long.parseLong(line.split(" ")[1]);
            Long destination = Long.parseLong(line.split(" ")[0]);
            Long length = Long.parseLong(line.split(" ")[2]);
            System.out.println("destination start: " + destination + " source start: " + source + " length: " + length);

            for (int i = 0; i < seeds.size(); i++)
            {
                if (seeds.get(i) >= source && seeds.get(i) <= source + length && !modified[i])
                {
                    System.out.println("Entro con elemento: " + seeds.get(i) + " en pos: " + i);
                    seeds.set(i, seeds.get(i) + destination - source);
                    modified[i] = true;
                    System.out.println("REMOVE SEEDS: " + seeds.toString());
                }
            }
            line = reader.readLine();
        }
        return seeds;
    }
}
