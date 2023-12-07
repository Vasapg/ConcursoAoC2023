package Day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CamelTwo
{
    public static ArrayList<String> cards = new ArrayList<>();
    public static ArrayList<Integer> bids = new ArrayList<>();
    public static void main(String[] args) throws IOException 
    {
        String path = "Day7/test2";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        int result = 0;
        while (line != null)
        {
            String card = line.trim().split(" ")[0];
            Integer bid = Integer.parseInt(line.trim().split(" ")[1]);
            insert_card(card, bid);
            line = reader.readLine();
        }
        reader.close();
        System.out.println(cards.toString());
        //System.out.println(bids.toString());
        for (int i = 0 ; i < bids.size(); i++)
        {
            System.out.println("bid : " + bids.get(i) + " rank: " + (bids.size() - i) + " cards: " + cards.get(i));
            result += bids.get(i) * (bids.size() - i);
        }
        System.out.println(result);
    }

    public static void insert_card(String card, int bid)
    {
        for (int i = 0; i < cards.size(); i++)
        {
            if (compare_cards(card, cards.get(i)))
            {
                cards.add(i, card);
                bids.add(i, bid);
                return;
            }
        }
        cards.add(cards.size(), card);
        bids.add(bids.size(), bid);
    }

    public static int get_card_rank(String card)
    {
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        char max_cards = 'J';
        for (char ch : card.toCharArray())
        {
            if (map.containsKey(ch))
                map.put(ch, map.get(ch) + 1);
            else
                map.put(ch, 1);
            if (map.get(ch) > max && ch != 'J')
            {
                max = map.get(ch);
                max_cards = ch;
            }
        }
        if (map.containsKey('J'))
        {
            map.put(max_cards, map.get('J') + map.get(max_cards));
            map.remove('J');
        }
        if (map.size() == 1 || map.size() == 0)
            return 7;
        if (map.size() == 5)
            return 1;
        if (map.size() == 2)
        {
            int value = 0;
            for (int values : map.values())
                value = values;
            if (value == 1 || value == 4)
                return 6;
            if (value == 2 || value == 3)
                return 5;
        }
        if (map.size() == 3)
        {
            for (int value : map.values())
            {
                if (value == 3)
                    return 4;
            }
            return 3;
        }
        if (map.size() == 2)
            return 2;
        return 2;
    }

    public static boolean compare_cards(String card1, String card2)
    {
        int rank1 = get_card_rank(card1);
        int rank2 = get_card_rank(card2);

        //System.out.println("card1: " + card1 + " rank: " + rank1);
        //System.out.println("card2: " + card2 + " rank: " + rank2);
        if (rank1 > rank2)
            return true;
        else if (rank2 > rank1)
            return false;
        else
        {
            String AKQJT = "AKQT";
            for (int i = 0; i < card1.length(); i++)
            {
                char c1 = card1.toCharArray()[i];
                char c2 = card2.toCharArray()[i];

                if (Character.isAlphabetic(c1) && Character.isAlphabetic(c2))
                {
                    for (char ch : AKQJT.toCharArray())
                    {
                        if (ch == c1 && ch != c2)
                            return true;
                        if (ch == c2 && ch != c1)
                            return false;
                    }
                }
                if (c1 == 'J' && c2 != 'J')
                    return false;
                else if (c2 == 'J' && c1 != 'J')
                    return true;
                if (c1 > c2)
                    return true;
                else if (c2 > c1)
                    return false;
            }
            return true;
        }
    }
}
