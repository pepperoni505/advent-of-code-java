package com.jacklavigne;

import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        List<String> input = util.openInput("day2.txt");
        System.out.println("Part 1: " + partOne(input));
        System.out.println("Part 2: " + partTwo(input));
    }

    public static int partOne(List<String> input) {
        int horizontalPosition = 0;
        int depth = 0;
        for (String line : input) {
            String[] instruction = line.split(" ");
            String direction = instruction[0];
            String change = instruction[1];
            switch (direction) {
                case "forward":
                    horizontalPosition += Integer.parseInt(change);
                    break;
                case "down":
                    depth += Integer.parseInt(change);
                    break;
                case "up":
                    depth -= Integer.parseInt(change);
                    break;
            }
        }
        return horizontalPosition * depth;
    }

    public static int partTwo(List<String> input) {
        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;
        for (String line : input) {
            String[] instruction = line.split(" ");
            String direction = instruction[0];
            String change = instruction[1];
            switch (direction) {
                case "forward":
                    horizontalPosition += Integer.parseInt(change);
                    depth += aim * Integer.parseInt(change);
                    break;
                case "down":
                    aim += Integer.parseInt(change);
                    break;
                case "up":
                    aim -= Integer.parseInt(change);
                    break;
            }
        }
        return horizontalPosition * depth;
    }
}
