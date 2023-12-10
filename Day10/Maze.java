import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze 
{
    public static int startX;
    public static int startY;
    public static char map [][];
    public static void main(String[] args) throws IOException 
    {
        String path = "Day10/test1";
        map = load_map(path);
        change_start(startX, startY);
        int result = 0;
        //print_map();
        result = run_map(map, result, startX, startY);
        print_map();
        System.out.println(result/2 + 1);
    }

    public static void change_start(int x, int y)
    {
        map[y][x] = 'M';
        if (x + 1 <= map[y].length && map[y][x + 1] != '.')
        {
            startX++;
            return;
        }
        if (y + 1 <= map.length && map[y + 1][x] != '.')
        {
            startY++;
            return;
        }

        if (x - 1 >= 0 && map[y][x - 1] != '.')
        {
            startX--;
            return;
        }
        if (y - 1 >= 0 && map[y - 1][x] != '.')
        {
            startY--;
            return;
        }

        return ;
    }

    public static void print_map()
    {
        for (int i = 0; i < map.length ; i++)
        {
            for (char ch : map[i])
                System.out.print(ch);
            System.out.println();
        }
    }

    public static int run_map (char [][] map, int result, int x, int y)
    {
        boolean sigue = true;
        while (sigue)
        {
            char ch = map[y][x];
            map[y][x] = 'M';
            int aux = result;
            if (x + 1 < map[y].length)
            {
                x = x + 1;
                if ((ch == 'F') && (map[y][x] == '-' || map[y][x] == 'J' || map[y][x] == '7'))
                    result++;
                if ((ch == '-') && (map[y][x] == '-' || map[y][x] == 'J' || map[y][x] == '7'))
                    result++;
                if ((ch == 'L') && (map[y][x] == '-' || map[y][x] == 'J' || map[y][x] == '7'))
                    result++;
                if (aux != result)
                    continue;
                x--;
            }
            if (x - 1 >= 0)
            {
                x = x - 1;
                if ((ch == '7') && (map[y][x] == '-' || map[y][x] == 'F' || map[y][x] == 'L'))
                    result++;
                if ((ch == 'J') && (map[y][x] == '-' || map[y][x] == 'F' || map[y][x] == 'L'))
                    result++;
                if ((ch == '-') && (map[y][x] == '-' || map[y][x] == 'F' || map[y][x] == 'L'))
                    result++;
                if (aux != result)
                    continue;
                x++;
            }

            if (y + 1 < map.length)
            {
                y = y + 1;
                if ((ch == 'F') && (map[y][x] == '|' || map[y][x] == 'L' || map[y][x] == 'J'))
                    result++;        
                if ((ch == '7') && (map[y][x] == '|' || map[y][x] == 'L' || map[y][x] == 'J'))
                    result++;
                if ((ch == '|') && (map[y][x] == '|' || map[y][x] == 'L' || map[y][x] == 'J'))
                    result++;
                if (aux != result)
                    continue;
                y--;
            }
            if (y - 1 >= 0)
            {
                y = y - 1;
                if ((ch == 'J') && (map[y][x] == '|' || map[y][x] == '7' || map[y][x] == 'F'))
                    result++;
                if ((ch == 'L') && (map[y][x] == '|' || map[y][x] == '7' || map[y][x] == 'F'))
                    result++;
                if ((ch == '|') && (map[y][x] == '|' || map[y][x] == '7' || map[y][x] == 'F'))
                    result++;
                if (aux != result)
                    continue;
                y++;  
            }
            sigue = false;
        }
        return result;
    }

    public static char [][] load_map(String path) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = reader.readLine();
        int y = 0;
        int x = line.length();
        while (line != null)
        {
            y++;
            line = reader.readLine();
        }
        char map [][] = new char[y][x];
        reader.close();
        reader = new BufferedReader(new FileReader(path));
        line = reader.readLine();
        x = 0;
        y = 0;
        while (line != null)
        {
            x = 0;
            for (char ch : line.toCharArray())
            {
                if (ch == 'S')
                {
                    startX = x;
                    startY = y;
                }
                map[y][x++] = ch;
            }
            y++;
            line = reader.readLine();
        }
        return map;
    }
}
