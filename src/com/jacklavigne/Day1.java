package com.jacklavigne;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {
    public static void main(String[] args) {
        List<String> input = util.openInput("day1.txt");
        System.out.println("Part 1: " + partOne(input));
        System.out.println("Part 2: " + partTwo(input));
    }

    public static int partOne(List<String> input) {
        int largerThanPrevious = 0;
        for (int i = 1; i < input.size(); i++) { // We can skip the first line since it's not necessary for part 1
            if (Integer.parseInt(input.get(i)) > Integer.parseInt(input.get(i - 1))) {
                largerThanPrevious++;
            }
        }
        return largerThanPrevious;
    }

    public static int partTwo(List<String> input) {
        List<Integer> windowSums = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            int window = 0;
            for (int j = 0; j <= 2; j++) {
                if (i + j < input.size()) {
                    window += Integer.parseInt(input.get(i + j));
                }
            }
            windowSums.add(window);
        }
        return partOne(windowSums.stream().map(Object::toString)
                .collect(Collectors.toList()));
    }
}
