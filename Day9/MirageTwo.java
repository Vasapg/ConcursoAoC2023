package Day9;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;

public class MirageTwo 
{
    public static void main(String[] args) throws IOException
    {
        String path = "Day9/test2";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        int result = 0;
        while (line != null)
        {
            result += get_prediction(line.trim().split(" "));
            line = reader.readLine();
        }
        System.out.println(result);
        reader.close();
    }

    public static int get_prediction(String[] numbers)
    {
        int result = 0;
        ArrayList<Integer> current = new ArrayList<>();
        ArrayList<Integer> first_number = new ArrayList<>();
        for (String number : numbers)
            current.add(Integer.parseInt(number));
        first_number.add(current.get(0));
        while (!all_zeros(current))
        {
            ArrayList<Integer> new_current = new ArrayList<>();
            for (int i = 0; i < current.size() - 1; i++)
            {
                new_current.add(current.get(i + 1) - current.get(i));
            }
            first_number.add(new_current.get(0));
            current = new_current;
        }
        System.out.println(first_number);
        for (int i = first_number.size() - 1 ; i >= 0 ; i--)
            result = first_number.get(i) - result;
        System.out.println(result);
        return result;
    }

    public static boolean all_zeros(ArrayList<Integer> numbers)
    {
        for (int number : numbers)
        {
            if (number != 0)
                return false;
        }
        return true;
    }
}
