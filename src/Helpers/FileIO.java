package Helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {

    public static String readData(String path) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new File(path));

        StringBuilder data = new StringBuilder();

        while(scanner.hasNextLine())
        {
            data.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return data.toString();
    }

    public static void writeData(String path, String data, boolean append) throws IOException
    {
        FileWriter writer = new FileWriter(path);

        if(append)
        {
            writer.append(data);
        }
        else
        {
            writer.write(data);
        }
        writer.close();
    }
}
