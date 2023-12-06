package Day4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ScratchcardsTwo
{
    public static HashMap<Integer, String> cards_map = new HashMap<>();

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
                    cards_map.put(i++, line);
                    write_line(line);
                    line = reader.readLine();
                }
                reader.close();
                reader = new BufferedReader(new FileReader("Day4/result"));
                line = reader.readLine();
                while (line != null || result < 200)
                {
                    line = line.replaceAll("\\s+", " ");
                    result += validate_cards(line.split(":")[1], Integer.parseInt(line.split(":")[0].split("\\ ")[1]));
                    line = reader.readLine();
                }
                reader.close();
                System.out.println(result);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
    }

    public static void write_line(String line)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Day4/result", true))) 
        {
            // El segundo parámetro 'true' en FileWriter indica que se abrirá en modo de apendizaje
            writer.write(line);
            writer.newLine(); // Agregar un salto de línea después de la línea escrita
            writer.close();
        } 
        catch (IOException e) 
        {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
        
    }

    public static int validate_cards(String cards, int card_number)
    {
        String aux_str[] = cards.split("\\|");
        int i = card_number;
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
            if (win_cards.containsKey(Integer.parseInt(number)))
            {
                i++;
                if (cards_map.containsKey(i))
                {
                    result++;
                    System.out.println("i: " + i);
                    write_line(cards_map.get(i));
                }
            }
        }
        return result;
    }
}
