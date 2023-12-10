import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class MazeTwo
{
    public static int startX;
    public static int startY;
    public static char map [][];
    public static ArrayList<Point> coords = new ArrayList<>();
    public static ArrayList<Point> points = new ArrayList<>();
    public static void main(String[] args) throws IOException 
    {
        String path = "Day10/test1";
        map = load_map(path);
        coords.add(new Point(startX, startY));
        change_start(startX, startY);
        coords.add(new Point(startX, startY));
        int result = 0;
        result = run_map(map, result, startX, startY);
        print_map();
        result = result/2 + 1;
        System.out.println(result);
        add_points();
        System.out.println("nº points: " + points.size());
        System.out.println(check_points());
        print_map();
    }

    public static void add_points()
    {
        int y = 0;
        for (char []line : map)
        {
            int x = 0;
            for (char ch : line)
            {
                if (ch != 'M')
                    points.add(new Point(x, y));
                x++;
            }
            y++;
        }
    }
    private static int check_points()
    {
        int result = 0;
        for (Point p : points)
        {
            if (PointInside(p, coords) == 1)
            {
                result++;
                map[(int)p.getY()][(int)p.getX()] = 'I';
            }
            else
                map[(int)p.getY()][(int)p.getX()] = 'O';
        }
        return result;  
    }

    private static int PointInside(Point point, ArrayList<Point> coords) {
        int intersectCount = 0;

        for (int i = 0; i < coords.size(); i++) {
            Point p1 = coords.get(i);
            Point p2 = coords.get((i + 1) % coords.size());

            if ((p1.getY() > point.getY() != p2.getY() > point.getY()) &&
                (point.getX() < (p2.getX() - p1.getX()) * (point.getY() - p1.getY()) / (p2.getY() - p1.getY()) + p1.getX())) {
                intersectCount++;
            }
        }
        return intersectCount % 2;
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
                {
                    coords.add(new Point(x, y));
                    continue;
                }
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
                {
                    coords.add(new Point(x, y));
                    continue;
                }
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
                {
                    coords.add(new Point(x, y));
                    continue;
                }
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
                {
                    coords.add(new Point(x, y));
                    continue;
                }
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
