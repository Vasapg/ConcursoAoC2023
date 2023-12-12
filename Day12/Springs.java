package Day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Springs 
{
    public static void main(String[] args) throws IOException
    {
        String path = "Day12/test2";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        int result = 0;
        while (line != null)
        {
            result += get_comb(line.trim().split(" ")[0], line.trim().split(" ")[1]);
            line = reader.readLine();
        }
        System.out.println(result);
    }
    
    public static int get_comb(String springs, String numbers)
    {
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> var = new ArrayList<>();
        int result = 0;
        for (String numb : numbers.trim().split(","))
            rows.add(Integer.parseInt(numb));
        for (int i = 0; i < springs.length() ; i++)
        {
            if (springs.toCharArray()[i] == '?')
                var.add(i);
        }
        result = recursive(springs, rows, var.size(), result);
        return result;
    }

    public static int recursive(String springs, ArrayList<Integer> lengths, int var, int result)
    {
        if (var == 0)
        {
            if (check_chain(springs, lengths))
            {
                System.out.println(springs);
                return result + 1;
            }
            else
                return result;
        }
        String save = springs;
        springs = springs.replaceFirst("\\?", "#");
        result = recursive(springs, lengths, var - 1, result);
        result = recursive(save.replaceFirst("\\?", "."), lengths, var - 1, result);
        return result;
    }

    public static boolean check_chain(String line, ArrayList<Integer> numbers)
    {
        char []charArray = line.toCharArray();
        int j = 0;
        //System.out.println(line);
        int i = 0;
        while (i<line.length())
        {
            while (i<line.length() && charArray[i] == '.')
                i++;
            int limit = numbers.get(j++);
            int aux = 0;
            //System.out.println("i: " + i + " char[i]: " + charArray[i]);
            while (i < line.length() && charArray[i] != '.')
            {
                aux++;
                i++;
            }
            //System.out.println("counted: " + aux + " required: " + limit);
            if (aux != limit)
                return false;
            if (j == numbers.size())
            {
                while (i<line.length())
                {
                    if (charArray[i] == '#')
                        return false;
                    i++;
                }
                return true;
            }
        }
        if (j != numbers.size())
            return  false;
        return true;
    }
}
