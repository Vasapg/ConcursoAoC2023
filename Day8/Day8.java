package Day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day8
{
    public static HashMap<String, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException
    {
        String path = "Day8/test2";
        BufferedReader reader = new BufferedReader(new FileReader(path));
        ArrayList<String> paths = new ArrayList<>();
        String instructions = reader.readLine();
        System.out.println(instructions.length());
        String line = reader.readLine();
        while (line != null)
        {
            if (line.length() > 3)
                map.put(line.split("=")[0].trim(), line.split("=")[1].replace("(", "").replace(")", ""));
            if (line.split("=")[0].trim().endsWith("A"))
                paths.add(line.split("=")[0].trim());
            line = reader.readLine();
        }
        long []steps = new long[paths.size()];
        int i = 0;
        for (String curren : paths)
        {
            steps[i++] = get_steps(instructions, curren);
            System.out.println(get_steps(instructions, curren));
        }
        System.out.println(calcularMCM(steps));
    }

    private static long calcularMCD(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    private static long calcularMCM(long a, long b) {
        return (a * b) / calcularMCD(a, b);
    }

    private static long calcularMCM(long[] numeros)
    {
        long mcm = numeros[0];
        for (int i = 1; i < numeros.length; i++) {
            mcm = calcularMCM(mcm, numeros[i]);
        }

        return mcm;
    }

    public static int get_steps(String instructions, String current)
    {
        int steps = 0;
        int i = 0;
        while (!current.endsWith("Z"))
        {
            if (i == instructions.length())
                i = 0;
            char ins = instructions.toCharArray()[i];
            if (ins == 'L')
                current = map.get(current).split(",")[0].trim();
            else if (ins == 'R')
                current = map.get(current).split(",")[1].trim();
            i++;
            steps++;
        }
        return steps;
    }
}
