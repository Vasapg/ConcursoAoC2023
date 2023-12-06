package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class FertilizerTwo
{
    private static long min = Long.MAX_VALUE; // Variable compartida

    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("Day5/test2"));
            String line = reader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(line);
            tokenizer.nextToken();

            while (tokenizer.hasMoreTokens()) {
                long start = Long.parseLong(tokenizer.nextToken());
                long length = Long.parseLong(tokenizer.nextToken());

                // Crear y lanzar un hilo para el bucle for
                Thread thread = new Thread(new ForLoopRunnable(start, length));
                thread.start();
            }

            // Esperar a que todos los hilos terminen antes de imprimir el resultado
            Thread.sleep(1000); // Ajusta según sea necesario
            System.out.println(min);

            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Clase Runnable para el bucle for
    private static class ForLoopRunnable implements Runnable {
        private long start;
        private long length;

        public ForLoopRunnable(long start, long length) {
            this.start = start;
            this.length = length;
        }

        @Override
        public void run() {
            for (long i = start; i < length + start; i++) {
                long aux = Long.MAX_VALUE;
                try {
                    aux = get_value(i);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                synchronized (FertilizerTwo.class) {
                    if (aux < min) 
                    {
                        min = aux;
                        System.out.println("new min: " + min);
                    }
                }
                System.out.println("i: " + i);
            }
        }
    }

    public static Long get_value(long seed) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader("Day5/test2"));
        String line = reader.readLine().trim();
        while (line != null)
        {
            Boolean modified = false;
            while (line != null && line.length() > 4 && !line.contains("map") && !line.contains("seeds") && !modified)
            {
                //System.out.println(line);
                Long source = Long.parseLong(line.split(" ")[1]);
                Long destination = Long.parseLong(line.split(" ")[0]);
                Long length = Long.parseLong(line.split(" ")[2]);

                if (seed >= source && seed <= source + length)
                {
                    seed = seed + destination - source;
                    modified = true;
                }
                line = reader.readLine();
            }
            line = reader.readLine();
        }
        reader.close();
        return seed;
    }
}
