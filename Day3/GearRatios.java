package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GearRatios 
{
    public static void main(String[] args) 
    {

        char [][]schematic = load_schematic();
        Integer result = 0;
        for (int y = 0; y < schematic.length ; y++)
        {
            for (int x = 0; x < schematic[y].length; x++)
            {
                if (Character.isDigit(schematic[y][x]))
                    result = check_number(result, schematic, x, y);
                while (x < schematic[y].length && Character.isDigit(schematic[y][x]))
                    x++;
            }
        }
        System.out.println(result);
    }

    public static int check_number(Integer result, char [][]schematic, int x, int y)
    {
        char ch = schematic[y][x];
        String number = "";
        Boolean valid = false;
        while (Character.isDigit(ch) && x < schematic[y].length)
        {
            number += ch;
            if(!valid)
                valid = check_cuadrants(schematic, x, y);
            x++;
            if (x < schematic[y].length)
                ch = schematic[y][x];
        }
        if (valid)
            result += Integer.parseInt(number);
        return result;
    }

    public static boolean check_cuadrants(char [][]schematic, int x, int y)
    {
        //System.out.println("x: " + x + " y: " + y + " ch: " + schematic[y][x] + " ch+1+1: " +schematic[y+1][x+1]);
        if (x+1 < schematic[y].length && schematic[y][x+1] != '.' && !Character.isDigit(schematic[y][x+1]))
            return true;
        if (y+1 < schematic.length && schematic[y+1][x] != '.' && !Character.isDigit(schematic[y+1][x]))
            return true;
        if (x-1 >= 0 && schematic[y][x-1] != '.' && !Character.isDigit(schematic[y][x-1]))
            return true;
        if (y-1 >= 0 && schematic[y-1][x] != '.' && !Character.isDigit(schematic[y-1][x]))
            return true;
        if (x-1 >= 0 && y-1 >= 0 && schematic[y-1][x-1] != '.' && !Character.isDigit(schematic[y-1][x-1]))
            return true;
        if (x+1 < schematic[y].length && y-1 >= 0 && schematic[y-1][x+1] != '.' && !Character.isDigit(schematic[y-1][x+1]))
            return true;
        if (x+1 < schematic[y].length && y+1 < schematic.length && schematic[y+1][x+1] != '.' && !Character.isDigit(schematic[y+1][x+1]))
            return true;
        if (x-1 >= 0 && y+1 < schematic.length && schematic[y+1][x-1] != '.' && !Character.isDigit(schematic[y+1][x-1]))
            return true;
        return false;
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
