package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Conundrum 
{
    public static void main(String[] args) 
    {
        BufferedReader reader;
		try 
        {
			reader = new BufferedReader(new FileReader("Day2/test1"));
			String line = reader.readLine();
            int sum = 0;
            while (line != null) 
            {
                String [] parse_line = line.split(":");
                int id = Integer.parseInt(parse_line[0].split(" ")[1]);
                int [] colors_line = {0,0,0};                
                for (String colors : parse_line[1].split(";"))
                {
                    int [] colors_actual = {0,0,0};
                    colors_actual = count_color(colors);
                    if (colors_actual[0] > colors_line[0])
                        colors_line[0] = colors_actual[0];
                    if (colors_actual[1] > colors_line[1])
                        colors_line[1] = colors_actual[1];
                    if (colors_actual[2] > colors_line[2])
                        colors_line[2] = colors_actual[2];
                }
                if (colors_line[0] <= 12 && colors_line[1] <= 13 && colors_line[2] <= 14)
                {
                    System.out.println(id);
                    System.out.println(colors_line[0]+ " " + colors_line[1]+ " " + colors_line[2]);
                    sum += id;
                }
                line = reader.readLine();
            }
            System.out.println(sum);
        }
        catch (IOException e) 
        {
			e.printStackTrace();
		}
    }

    public static int[] count_color(String blocks)
    {
        int []result = {0,0,0};
        String []colors = blocks.split(",");
        for (String color : colors)
        {
            String []color_aux;
            color_aux = color.split(" ");
            if (color_aux[2].equals("red"))
                result[0] = Integer.parseInt(color_aux[1]);
            if (color_aux[2].equals("green"))
                result[1] = Integer.parseInt(color_aux[1]);
            if (color_aux[2].equals("blue"))
                result[2] = Integer.parseInt(color_aux[1]);
            //System.out.println(result[0] + " " +result[1] + " " + result[2]);
        }
        return result;
    }
}
