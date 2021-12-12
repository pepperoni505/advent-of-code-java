package com.jacklavigne;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class util {

    public static List<String> openInput(String fileName) {
        List<String> lst = new ArrayList<>();
        try {
            lst = Files.readAllLines(Paths.get(System.getProperty("user.dir"), "input", fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lst;
    }
}
