package com.jacklavigne;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 {
    public static void main(String[] args) {
        List<String> input = util.openInput("day4.txt");
        System.out.println("Part 1: " + partOne(input));
        System.out.println("Part 2: " + partTwo(input));
    }

    public static int partOne(List<String> input) {
        List<Integer> winningBoards = getWinningBoards(input);
        return winningBoards.get(0);
    }

    public static int partTwo(List<String> input) {
        List<Integer> winningBoards = getWinningBoards(input);
        return winningBoards.get(winningBoards.size() - 1);
    }

    public static List<Integer> getWinningBoards(List<String> input) {
        List<String> drawOrder = List.of(input.get(0).split(","));
        List<List<String>> boards = new ArrayList<>();
        for (int i = 2; i < input.size(); i+= 6) {
            List<String> board = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                board.addAll(Stream.of(input.get(i + j).split(" ")).filter(item -> !item.isEmpty()).collect(Collectors.toList()));
            }
            boards.add(board);
        }

        List<Integer> winningBoards = new ArrayList<>();
        List<Integer> winningBoardsFinalScore = new ArrayList<>();
        for (String draw : drawOrder) {
            // Mark all occurrences of drawn number with "X"
            for (List<String> board : boards) {
                for (int item = 0; item < board.size(); item++) {
                    if (board.get(item).equals(draw)) {
                        board.set(item, "X");
                    }
                }
            }

            // Check wins
            for (int board = 0; board < boards.size(); board++) { // TODO: figure out why returning the wrong value. probably something to do with the vertical check
                // Check win horizontal
                for (int row = 0; row < (boards.get(board).size() / 5); row++) {
                    if (boards.get(board).subList(row * 5, row * 5 + 5).stream().distinct().count() <= 1) {
                        addWinningBoard(boards, winningBoards, winningBoardsFinalScore, draw, board);
                    }
                }
                // Check win vertical
                for (int column = 0; column < 5; column++) {
                    List<String> currentColumn = new ArrayList<>();
                    for (int i = 0; i <= 20; i+= 5) {
                        currentColumn.add(boards.get(board).get(column + i));
                    }
                    if (currentColumn.stream().distinct().count() <= 1) {
                        addWinningBoard(boards, winningBoards, winningBoardsFinalScore, draw, board);
                    }
                }
            }
        }
        return winningBoardsFinalScore;
    }

    private static void addWinningBoard(List<List<String>> boards, List<Integer> winningBoards, List<Integer> winningBoardsFinalScore, String draw, int board) {
        if (!winningBoards.contains(board)) {
            winningBoards.add(board);

            // Get sum of all non-drawn numbers
            int sum = (int) boards.get(board).stream().filter(i -> !Objects.equals(i, "X")).mapToInt(Integer::parseInt).sum();
            winningBoardsFinalScore.add(sum * Integer.parseInt(draw));
        }
    }
}
