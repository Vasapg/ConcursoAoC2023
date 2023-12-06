package Day4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ScratchcardsThree
{
    public static HashMap<Integer, Integer> cards_map = new HashMap<>();
    public static HashMap<Integer, Integer> cards_number = new HashMap<>();

    public static void main(String[] args) 
    {
            BufferedReader reader;
            try 
            {
                reader = new BufferedReader(new FileReader("Day4/test2"));
                String line = reader.readLine();
                int result = 0;
                int i = 1;
                while (line != null)
                {
                    int winning_cards = read_cards(line.split(":")[1]);
                    cards_map.put(i, winning_cards);
                    cards_number.put(i, 1);
                    i++;
                    line = reader.readLine();
                }
                int k = 1;
                while (k != 0)
                {
                    if (!cards_map.containsKey(k))
                    {
                        k = 0;
                        continue ;
                    }
                    increase_cards(k, cards_map.get(k));
                    k++;
                }
                k = 1;
                while (cards_number.containsKey(k))
                {
                    System.out.println("hay " + cards_number.get(k) + " del set Card " + k);
                    result += cards_number.get(k);
                    k++;
                }            
                reader.close();
                System.out.println(result);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
    }

    public static void increase_cards(int id, int nCards)
    {
        int aux = id;
        while (nCards != 0)
        {
            aux++;
            if (cards_number.containsKey(aux))
            {
                System.out.println("Añado una card " + aux);
                cards_number.put(aux, cards_number.get(aux) + cards_number.get(id));
            }
            nCards--;
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
            else if (win_cards.containsKey(Integer.parseInt(number)))
                result +=1;
        }
        return result;
    }
}
