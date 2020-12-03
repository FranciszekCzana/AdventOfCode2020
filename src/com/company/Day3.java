package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public Day3() {
        File file = new File("resources/inputDay3.txt");
        List<String> lines = readStringLinesFromFile(file);

        task1(lines);
        task2(lines);
    }

    /**
     * Starting at the top-left corner of your map and following a slope of right 3 and down 1,
     * how many trees would you encounter?
     * @param lines - List of maps (. - open quare, # - tree)
     */
    private void task1(List<String> lines) {
        int result = determineNumberTrees(lines, 3, 1);
        System.out.println(result);
    }

    /**
     * Starting at the top-left corner of your map and following a slopes of:
     * - right 1 and down 1,
     * - right 3 and down 1,
     * - right 5 and down 1,
     * - right 7 and down 1,
     * - right 1 and down 2,
     * How many trees would you encounter? Multiply them.
     * @param lines - List of maps (. - open quare, # - tree)
     */
    private void task2(List<String> lines) {
        int a = determineNumberTrees(lines, 1, 1);
        int b = determineNumberTrees(lines, 3, 1);
        int c = determineNumberTrees(lines, 5, 1);
        int d = determineNumberTrees(lines, 7, 1);
        int e = determineNumberTrees(lines, 1, 2);
        long result = (long) a*b*c*d*e;
        System.out.println(a + " " + b + " " + c + " " + d + " " + e);
        System.out.println(result);
    }

    private int determineNumberTrees(List<String> lines, int right, int down) {
        int wskX = 0;
        int wskY= 0;
        int count = 0;
        for (String line : lines) {
            if (wskY++ % down != 0)
                continue;
            wskX = wskX % line.length();
            if (line.charAt(wskX) == '#')
                count++;
            wskX += right;
        }
        return count;
    }

    private List<String> readStringLinesFromFile(File file) {
        List<String> list = new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null)
                list.add(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
