package com.jacklavigne;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 {
    public static void main(String[] args) {
        List<String> input = util.openInput("day3.txt");
        System.out.println("Part 1: " + partOne(input));
        System.out.println("Part 2: " + partTwo(input));
    }

    public static int partOne(List<String> input) {
        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();
        for (int i = 0; i < input.get(0).length(); i++) {
            int totalZero = 0;
            int totalOne = 0;
            for (String line : input) {
                if (line.charAt(i) == '0') {
                    totalZero += 1;
                } else {
                    totalOne += 1;
                }
            }
            if (totalZero > totalOne) {
                gammaRate.append("0");
                epsilonRate.append("1");
            } else{
                gammaRate.append("1");
                epsilonRate.append("0");
            }
        }
        return Integer.parseInt(gammaRate.toString(), 2) * Integer.parseInt(epsilonRate.toString(), 2);
    }

    public static List<String> test(List<String> input, int i, boolean mostCommon) {
        int totalZero = (int) input.stream().filter(line -> line.charAt(i) == '0').count();
        int totalOne = input.size() - totalZero;
        List<String> filtered = input.stream().filter(line -> ((mostCommon ? totalOne >= totalZero : totalZero < totalOne) ? line.charAt(i) == '1' : line.charAt(i) == '0')).collect(Collectors.toList()); // TODO: fix the least common issue
        if (filtered.size() == 1) {
            return filtered;
        }
        return test(filtered, i + 1, mostCommon);
    }

    public static int partTwo(List<String> input) {
        List<String> testList = test(input, 0, false);
        System.out.println(testList);

        return 0;

    }
}
