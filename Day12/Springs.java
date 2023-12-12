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
        String path = "Day12/test1";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        int result = 0;
        while (line != null)
        {
            result += get_comb(line.trim().split(" ")[0], line.trim().split(" ")[1]);
        }
    }
    
    public static int get_comb(String springs, String numbers)
    {
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> var = new ArrayList<>();
        int result = 0;
        for (String numb : numbers.trim().split(","))
            rows.add(Integer.parseInt(numbers));
        for (int i = 0; i < springs.length() ; i++)
        {
            if (springs.toCharArray()[i] == '?')
                var.add(i);
        }
        recursive(springs, var, rows);
        return result;
    }

    public static boolean check_chain(String line, ArrayList<Integer> numbers)
    {
        char []charArray = line.toCharArray();
        int j = 0;
        for (int i = 0; i<numbers.size(); i++)
        {
            while (charArray[i] == '.')
                i++;
            int limit = numbers.get(j++);
            int aux = 0;
            while (charArray[i] != '.')
            {
                aux++;
                i++;
            }
            if (aux != limit)
                return false;
        }
        return true;
    }
}
