package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Scratchcards 
{
    public static void main(String[] args) 
    {
            BufferedReader reader;
            try 
            {
                reader = new BufferedReader(new FileReader("Day4/test1"));
                String line = reader.readLine();
                int result = 0;
                while (line != null)
                {
                    result += read_cards(line.split(":")[1]);
                    line = reader.readLine();
                }
                System.out.println(result);
                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
    }
    public static int read_cards(String cards)
    {
        String aux_str[] = cards.split("\\|");
        int result = 0;
        HashMap<Integer, Integer> win_cards = new HashMap<>();
        for (String number : aux_str[0].split("\\ "))
        {
            if (!number.equals(" ") && !number.equals(""))
                win_cards.put(Integer.parseInt(number), 0);
        }
        for (String number : aux_str[1].split("\\ "))
        {
            if (number.equals(" ") || number.equals(""))
                continue;
            if (result == 0 && win_cards.containsKey(Integer.parseInt(number)))
                result = 1;
            else if (win_cards.containsKey(Integer.parseInt(number)))
                result = result * 2;
        }
        return result;
    }
}
