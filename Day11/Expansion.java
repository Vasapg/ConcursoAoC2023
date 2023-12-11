package Day11;

import java.util.ArrayList;
import java.util.HashMap;

import javax.management.MalformedObjectNameException;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Expansion 
{
    public static HashMap<Integer, Point> galaxys = new HashMap<>();
    public static void main(String[] args) throws IOException 
    {
        String path = "Day11/test2";
        ArrayList<ArrayList<Character>> map = new ArrayList<ArrayList<Character>>();
        int result = 0;
        map = load_map(map, path);
        count_stars(map);
        result = get_paths();
        System.out.println(result);
        print_map(map);
    }

    public static int get_paths()
    {
        int result = 0;
        int n = 0;
        for (int i = 0; i < galaxys.size() + 1; i++)
        {
            for (int j = i + 1; j < galaxys.size(); j++)
            {
                n++;
                int distance = distance(galaxys.get(i), galaxys.get(j));
                // System.out.println("Estrellas : " + i + ", " + j);
                // System.out.println("Distancia entre: " + galaxys.get(i).getX() + ", " + galaxys.get(i).getY() + " y : " + galaxys.get(j).getX() + ", " + galaxys.get(j).getY() + " es de : " + distance);
                result += distance;
            }
        }
        System.out.println("n: " + n);
        return result;
    }

    public static int distance(Point one, Point two)
    {
        double x = Math.abs(one.getX() - two.getX());
        double y = Math.abs(one.getY() - two.getY());
        //System.out.println("calculo: " + x + " + " + y );
        return (int) (x + y);
    }

    public static void count_stars(ArrayList<ArrayList<Character>> map)
    {
        int stars = 0;
        for (int y = 0; y < map.size(); y++)
        {
            for (int x = 0; x < map.get(y).size(); x++)
            {
                if (map.get(y).get(x) == '#')
                {
                    galaxys.put(stars++, new Point(x, y));
                }    
            }
        }
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

    public static ArrayList<ArrayList<Character>> load_map(ArrayList<ArrayList<Character>> map, String path) throws IOException 
    {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
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
        map = expand(map);

        reader.close();
        return map;
    }

    public static ArrayList<ArrayList<Character>> expand (ArrayList<ArrayList<Character>> map)
    {
        ArrayList<ArrayList<Character>> new_map = new ArrayList<ArrayList<Character>>();
        int i = 0;
        int k = 0;
        for (ArrayList<Character> line : map)
        {
            i++;
            new_map.add((ArrayList<Character>)line.clone());
            if (!line.contains('#'))
            {
                k++;
                System.out.println("añado linea en i: " + i);
                add_line(new_map, i);
                i++;
            }
        }
        int size_map = map.get(0).size();
        System.out.println(size_map);
        i = 0;
        for (int j = 0; j < size_map; j++)
        {
            boolean add = true;
            for (ArrayList<Character> line : map)
            {
                if (line.get(j) == '#')
                {
                    add = false;
                }
            }
            if (add)
            {
                add_colum(new_map, j+i);
                System.out.println("añado columna en: " + (j+i));
                i++;
            }
        }
        return new_map;
    }

    public static void add_line(ArrayList<ArrayList<Character>> map, int y)
    {
        map.add(y, new ArrayList<>());
        for (int i = 0; i < map.get(y-1).size(); i++)
        {
            map.get(y).add('.');
        }
    }

    public static void add_colum(ArrayList<ArrayList<Character>> map, int x)
    {
        for (int i = 0; i < map.size(); i++)
        {
            map.get(i).add(x, '.');
        }
    }
}
 