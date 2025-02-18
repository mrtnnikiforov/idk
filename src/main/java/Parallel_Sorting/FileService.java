package Parallel_Sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileService {

    private static String defaultFilePath = "numbers.csv";
    public static void generateNumsInFile(String filePath, int size) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                writer.write(String.valueOf(random.nextInt(400) - 200));
                if (i < size - 1) {
                    writer.write(",");
                }
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> loadNumbersFromFile(String path) {
        List<Integer> nums = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (String value : values) {
                    nums.add(Integer.parseInt(value.trim()));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return nums;
    }
}
