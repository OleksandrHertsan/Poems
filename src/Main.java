import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputPath = "src/resources/data.txt";
        String outputPath = "src/resources/resize_data.txt";


        Scanner scanner = new Scanner(System.in);

        System.out.print("Start: ");
        int start = scanner.nextInt();
        System.out.print("Finish: ");
        int finish = scanner.nextInt();

        if (start > finish) {
            System.out.print("Nah, that's wrong, try again: ");
            start = scanner.nextInt();
        }

        List<String> strings = addTextFromFile(inputPath);
        createResizedFileWithStream(start, finish, outputPath, strings);

    }

    public static List<String> addTextFromFile(String inputFilePath) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));

        List<String> strings = new ArrayList<>();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            strings.add(line);
        }
        bufferedReader.close();

        return strings;
    }

    public static void createResizedFile(int start, int finish, String outputFilePath, List<String> strings) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath));

        for (int i = 0; i < strings.size(); i++) {
            if (i >= start - 1 && i <= finish - 1) {
                bufferedWriter.write(strings.get(i));
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.close();
    }

    public static void createResizedFileWithStream(int start, int finish, String outputFilePath, List<String> strings) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath));

        strings.stream()
                .skip(start - 1)
                .limit(finish - start + 1)
                .forEach(e -> {
                    try {
                        bufferedWriter.write(e);
                        bufferedWriter.newLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

        bufferedWriter.close();
    }

    }

