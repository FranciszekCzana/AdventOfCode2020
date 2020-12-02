package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

    public Day2() {
        File file = new File("resources/inputDay2.txt");
        List<String> passes = readStringLinesFromFile(file);

        task1(passes);
        task2(passes);
    }

    /**
     * For example, suppose you have the following list:
     * 1-3 a: abcde
     * 1-3 b: cdefg
     * 2-9 c: ccccccccc
     *
     * Each line gives the password policy and then the password.
     * The password policy indicates the lowest and highest number
     * of times a given letter must appear for the password to be valid.
     * For example, 1-3 a means that the password must contain a at least 1 time and at most 3 times.
     * How many passwords are valid according to their policies?
     * @param passes List of configurations
     */
    private void task1(List<String> passes) {
        int count = 0;
        for( String pass : passes) {
            String[] components = pass.split(" ");
            if (components.length == 3) {
                String amounts = components[0];
                String sign = components[1].substring(0,components[1].length() - 1);
                String password = components[2];
                String[] amount = amounts.split("-");
                int matches = countMatches(password, sign);
                if (matches >= Integer.parseInt(amount[0]) && matches <= Integer.parseInt(amount[1])) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    /**
     * Given the same example list from above:
     *
     * 1-3 a: abcde is valid: position 1 contains a and position 3 does not.
     * 1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
     * 2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.
     * How many passwords are valid according to the new interpretation of the policies?
     * @param passes List of configurations
     */
    private void task2(List<String> passes) {
        int count = 0;
        for( String pass : passes) {
            String[] components = pass.split(" ");
            if (components.length == 3) {
                String amounts = components[0];
                char sign = components[1].charAt(0);
                String password = components[2];
                String[] amount = amounts.split("-");
                char a = password.charAt(Integer.parseInt(amount[0]) - 1);
                char b = password.charAt(Integer.parseInt(amount[1]) - 1);

                boolean found = false;
                if (sign == a)
                    found = true;
                if (sign != b && found)
                    count++;
                else if (sign == b && !found)
                    count++;
            }
        }
        System.out.println(count);
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

    /* Checks if a String is empty ("") or null. */
    private boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    /* Counts how many times the substring appears in the larger string. */
    private int countMatches(String text, String str) {
        if (isEmpty(text) || isEmpty(str))
            return 0;

        int index = 0, count = 0;
        while (true) {
            index = text.indexOf(str, index);
            if (index != -1) {
                count ++;
                index += str.length();
            } else
                break;
        }
        return count;
    }
}