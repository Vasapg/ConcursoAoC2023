package Day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SpringsTwo
{
    public static void main(String[] args) throws IOException
    {
        String path = "Day12/test2";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        int result = 0;
        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> second = new ArrayList<>();
        while (line != null)
        {
            int comb = get_comb(line.trim().split(" ")[0], line.trim().split(" ")[1]);
            first.add(comb);
            line = reader.readLine();
        }

        reader = new BufferedReader(new FileReader(path));
        line = reader.readLine();
        while (line != null)
        {
            int comb = get_comb_two(line.trim().split(" ")[0], line.trim().split(" ")[1]);
            second.add(comb);
            line = reader.readLine();
        }
        for (int i = 0; i < first.size(); i++)
        {
            int aux = 0;
            if (second.get(i) != 1)
            {
                aux = second.get(i) / first.get(i);
                System.out.println("aux: " + aux);
                aux = first.get(i) * (aux * aux *aux * aux);
                result += aux;
                System.out.println("first: " + first.get(i) + " second: " + second.get(i) + " aux: " + aux);
            }
            else
                result += 1;
        }
        System.out.println(result);
    }

    public static String duplicate_springs(String springs)
    {
        String result = "";
        result = springs + "?" + springs;
        return result;
    }

    public static String duplicate_numbers(String numbers)
    {
        String result = "";
        result = numbers + "," + numbers;
        return result;
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
    public static int get_comb_two(String springs, String numbers)
    {
        springs = duplicate_springs(springs);
        numbers = duplicate_numbers(numbers);
        System.out.println(springs);
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
