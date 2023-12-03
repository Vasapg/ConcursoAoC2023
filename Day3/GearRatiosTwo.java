package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GearRatiosTwo
{
    public static void main(String[] args) 
    {

        char [][]schematic = load_schematic();
        Integer result = 0;
        for (int y = 0; y < schematic.length ; y++)
        {
            for (int x = 0; x < schematic[y].length; x++)
            {
                if (schematic[y][x] == '*')
                    result += check_cuadrants(schematic, x, y);
            }
        }
        System.out.println(result);
    }


    public static int check_cuadrants(char [][]schematic, int x, int y)
    {
        int []numbers = new int[8];
        int i = 0;
        System.out.println("x: " + x + " y: " + y + " ch: " + schematic[y][x] + " ch+1: " +schematic[y][x+1]);
        if (x+1 < schematic[y].length && Character.isDigit(schematic[y][x+1]) && !has_number(numbers, get_number(schematic, x+1, y)))
            numbers[i++]= get_number(schematic, x+1, y);
        if (y+1 < schematic.length && Character.isDigit(schematic[y+1][x]) && !has_number(numbers, get_number(schematic, x, y+1)))
            numbers[i++]= get_number(schematic, x, y+1);
        if (x-1 >= 0 && Character.isDigit(schematic[y][x-1]) && !has_number(numbers, get_number(schematic, x-1, y)))
            numbers[i++]= get_number(schematic, x-1, y);
        if (y-1 >= 0 && Character.isDigit(schematic[y-1][x]) && !has_number(numbers, get_number(schematic, x, y-1)))
            numbers[i++]= get_number(schematic, x, y-1);
        if (x-1 >= 0 && y-1 >= 0 && Character.isDigit(schematic[y-1][x-1]) && !has_number(numbers, get_number(schematic, x-1, y-1)))
            numbers[i++]= get_number(schematic, x-1, y-1);
        if (x+1 < schematic[y].length && y-1 >= 0 && Character.isDigit(schematic[y-1][x+1]) && !has_number(numbers, get_number(schematic, x+1, y-1)))
            numbers[i++]= get_number(schematic, x+1, y-1);
        if (x+1 < schematic[y].length && y+1 < schematic.length && Character.isDigit(schematic[y+1][x+1]) && !has_number(numbers, get_number(schematic, x+1, y+1)))
            numbers[i++]= get_number(schematic, x+1, y+1);
        if (x-1 >= 0 && y+1 < schematic.length && Character.isDigit(schematic[y+1][x-1]) && !has_number(numbers, get_number(schematic, x-1, y+1)))
            numbers[i++]= get_number(schematic, x-1, y+1);
        if (i == 2)
        {
            // System.out.println(numbers[0]);
            // System.out.println(numbers[1]);
            // System.out.println("next *");
            return numbers[0] * numbers[1];
        }
        return 0;
    }

    public static boolean has_number(int[] array, int numero) {
        for (int elemento : array) {
            if (elemento == numero) {
                return true; // El número está en el array
            }
        }
        return false; // El número no está en el array
    }

    public static int get_number(char [][]schematic, int x, int y)
    {
        String number = "";
        while (x >= 0 && Character.isDigit(schematic[y][x]))
            x--;
        x++;
        while (x < schematic[y].length && Character.isDigit(schematic[y][x]))
        {
            number += schematic[y][x];
            x++;
        }
        System.out.println("number: " + number);
        if (!number.equals(""))
            return Integer.parseInt(number);
        return 0;
    }

    public static char [][] load_schematic()
    {
        BufferedReader reader;
        char [][]schematic = null;
		try 
        {
            int limit_y = 0;
            int limit_x = 0;
			reader = new BufferedReader(new FileReader("Day3/test2"));
			String line = reader.readLine();
            limit_x = line.length();
            while (line != null && line.length() == limit_x)
            {
                line = reader.readLine();
                limit_y++;
            }
            schematic = new char[limit_y][limit_x];
            reader.close();
            reader = new BufferedReader(new FileReader("Day3/test2"));
            line = reader.readLine();
            int y = 0;
            while (line != null && y < limit_y)
            {
                int x = 0;
                for (char ch : line.toCharArray())
                {
                    schematic[y][x++] = ch;
                }
                line = reader.readLine();
                y++;
            }
            reader.close();
        }
        catch (IOException e) 
        {
			e.printStackTrace();
		}
        return schematic;
    }
}
