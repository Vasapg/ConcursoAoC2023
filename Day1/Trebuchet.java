import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;

import javax.xml.stream.events.Characters;

public class Trebuchet
{
    public static void main(String[] args) 
    {
        BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("Day 1/document1.txt"));
			String line = reader.readLine();
            int sum = 0;

			while (line != null) 
            {
                String first = "";
                String last = "";
                line = parse_line(line);
                for (char ch : line.toCharArray())
                {
                    if (Character.isDigit(ch) && first == "")
                        first += ch;
                    else if (Character.isDigit(ch))
                        last = "" + ch;
                }
                String number = "0";
                if (first != "" && last != "")
                    number = first + last;
                if (last == "" && first != "")
                    number = first + first;
                System.out.println(number);
                sum += Integer.parseInt(number);
				line = reader.readLine();
			}
            System.out.println(sum);
			reader.close();
            		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static String parse_line(String line)
    {
        String new_line = "";
        new_line = line.replaceAll("twone", "21");
        new_line = new_line.replaceAll("oneight", "18");
        new_line = new_line.replaceAll("threeight", "38");
        new_line = new_line.replaceAll("fiveight", "53");
        new_line = new_line.replaceAll("nineight", "98");
        new_line = new_line.replaceAll("eighthree", "83");
        new_line = new_line.replaceAll("eightwo", "82");
        new_line = new_line.replaceAll("sevenine", "82");
        new_line = new_line.replaceAll("one", "1");
        new_line = new_line.replaceAll("two", "2");
        new_line = new_line.replaceAll("three", "3");
        new_line = new_line.replaceAll("four", "4");
        new_line = new_line.replaceAll("five", "5");
        new_line = new_line.replaceAll("six", "6");
        new_line = new_line.replaceAll("seven", "7");
        new_line = new_line.replaceAll("eight", "8");
        new_line = new_line.replaceAll("nine", "9");
        return new_line;
    }
}