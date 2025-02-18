package Parallel_Sorting;

import org.apache.commons.cli.*;

import java.util.Arrays;
import java.util.List;

public class ParallelSort {
    private static final int NUMS_COUNT = 100;
    private static String defaultFilePath = "numbers.csv";

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(Option.builder("t")
                .longOpt("thread")
                .hasArg()
                .argName("numThreads")
                .desc("Number of threads to use for sorting")
                .required()
                .build());

        options.addOption(Option.builder("a")
                .longOpt("algorithm")
                .hasArg()
                .argName("algorithm")
                .desc("Algorithm to use MERGE_SORT, QUICK_SORT, HEAP_SORT")
                .required()
                .build());

        options.addOption(Option.builder("p")
                .longOpt("path")
                .hasArg()
                .argName("filePath")
                .desc("Path to the file containing numbers")
                .build());

        options.addOption(Option.builder("h")
                .longOpt("help")
                .desc("Show this help message")
                .build());

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("ParallelSort", options);
            return;
        }

        if (cmd.hasOption("h")) {
            formatter.printHelp("ParallelSort", options);
            return;
        }

        int numThreads = Integer.parseInt(cmd.getOptionValue("t"));

        String algorithmName = cmd.getOptionValue("a").toUpperCase();

        if (cmd.hasOption("p")) {
            defaultFilePath = cmd.getOptionValue("p");
        }


        SortingAlgorithm algorithm;
        switch (algorithmName) {
            case "MERGE_SORT":
                algorithm = new MergeSort();
                break;
            case "QUICK_SORT":
                algorithm = new QuickSort();
                break;
            case "HEAP_SORT":
                algorithm = new HeapSort();
                break;
            default:
                System.out.println("Supported algorithms: MERGE_SORT, QUICK_SORT, HEAP_SORT");
                formatter.printHelp("ParallelSort", options);
                return;
        }

        FileService.generateNumsInFile(defaultFilePath, NUMS_COUNT);
        List<Integer> numbers = FileService.loadNumbersFromFile(defaultFilePath);

        if (numbers.isEmpty()) {
            System.out.println("Empty file");
            return;
        }

        int[] array = numbers.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("Before sorting: " + Arrays.toString(array));

        SortService sortService = new SortService(algorithm);

        long start = System.currentTimeMillis();
        sortService.parallelSort(array, numThreads);
        long parallelTime = System.currentTimeMillis() - start;

        printResult(array);
        System.out.printf("Time taken: %d ms%n", parallelTime);
    }

    private static void printResult(int[] array) {
        System.out.println("After sorting: " + Arrays.toString(array));
    }
}