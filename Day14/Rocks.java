package Day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Rocks 
{

    public static void main(String[] args) throws IOException
    {
        String path = "Day14/test2";
        int result = 0;
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        while (line != null)
        {
            if (line.length() > 3)
            {
                map = load_map(map, reader, line);
                print_map(map);
                turn_rocks(map);
                print_map(map);
                result += get_result(map);
            }
            line = reader.readLine();
        }
        reader.close();
        System.out.println(result);
    }

    public static void print_map(ArrayList<ArrayList<Character>> map)
    {
        for (ArrayList<Character> line : map)
        {
            for (char ch : line)
                System.out.print(ch);
            System.out.println();
        }
        System.out.println();
    }

    public static int get_result(ArrayList<ArrayList<Character>> map)
    {
        int result = 0;
        for (int i = 0; i < map.size(); i++)
        {
            for (int j = 0; j < map.get(i).size(); j++)
            {
                if (map.get(i).get(j) == 'O')
                {
                    System.out.println("result: " + (map.size() - i));
                    result += map.size() - i;
                }
            }
        }
        return result;
    }

    public static void turn_rock(ArrayList<ArrayList<Character>> map, int i, int j)
    {
        if (i == 0)
            return;
        char next = map.get(i - 1).get(j);
        char actual = map.get(i).get(j);
        while (i > 0 && next != '#' && next != 'O')
        {
            next = map.get(i - 1).get(j);
            actual = map.get(i).get(j);
            map.get(i - 1).set(j, actual);
            map.get(i).set(j, next);
            i--;
            if (i == 0)
                return;
            next = map.get(i - 1).get(j);
            actual = map.get(i).get(j);
        }
    }

    
    public static void turn_rocks(ArrayList<ArrayList<Character>> map)
    {
        for (int i = 0; i < map.size(); i++)
        {
            for (int j = 0; j < map.get(i).size(); j++)
            {
                if (map.get(i).get(j) == 'O')
                    turn_rock(map, i, j);
            }
        }
    }
    
    public static ArrayList<ArrayList<Character>> load_map(ArrayList<ArrayList<Character>> map, BufferedReader reader, String line) throws IOException 
    {
        int y = 0;
        while (line != null)
        {
            char [] chars = new char[line.length()];
            map.add(new ArrayList<>());
            chars = line.toCharArray();
            for (int x = 0; x < line.length(); x++)
            {
                char ch = chars[x];
                map.get(y).add(ch);
            }
            y++;
            line = reader.readLine();
        }
        return map;
    }
}
