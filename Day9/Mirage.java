package Day9;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;

public class Mirage 
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
        ArrayList<Integer> last_number = new ArrayList<>();
        for (String number : numbers)
            current.add(Integer.parseInt(number));
        last_number.add(current.get(current.size() - 1));
        while (!all_zeros(current))
        {
            ArrayList<Integer> new_current = new ArrayList<>();
            for (int i = 0; i < current.size() - 1; i++)
            {
                new_current.add(current.get(i + 1) - current.get(i));
            }
            System.out.println(new_current);
            last_number.add(new_current.get(new_current.size() - 1));
            current = new_current;
        }
        System.out.println(last_number);
        for (int number : last_number)
            result += number;
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
