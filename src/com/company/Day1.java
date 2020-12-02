package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Day1 {

    public Day1() {
        File file = new File("resources/numbersDay1.txt");
        List<Integer> numbers = readIntegersFromFile(file);

        task1(numbers);
        task2(numbers);
    }

    /**
     * In this list, find the two entries that sum to 2020.
     * What do you get if you multiply them together?
     * @param numbers List of numbers
     */
    private void task1(List<Integer> numbers) {
        for (int a : numbers) {
            Optional<Integer> b = numbers.stream()
                    .filter(value -> value == (2020 - a))
                    .findFirst();
            if (b.isPresent()) {
                System.out.println(a + " * " + b.get() + " = " + a*b.get());
                break;
            }
        }
    }

    /**
     * In this list, find the three entries that sum to 2020.
     * What do you get if you multiply them together?
     * @param numbers List of numbers
     */
    private void task2(List<Integer> numbers) {
        for (int a : numbers) {
            List<Integer> bList = numbers.stream()
                    .filter(value -> (a+value) < 2020)
                    .collect(Collectors.toList());
            boolean found = false;
            for (int b : bList) {
                Optional<Integer> c = numbers.stream()
                        .filter(value -> value == (2020 - (a+b)))
                        .findFirst();
                if (c.isPresent()) {
                    System.out.println(a + " * " + b + " * " + c.get() + " = " + a*b*c.get());
                    found = true;
                    break;
                }
            }
            if (found)
                break;
        }
    }

    private List<Integer> readIntegersFromFile(File file) {
        List<Integer> list = new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null)
                list.add(Integer.parseInt(text));
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

        // System.out.println(list);
        return list;
    }

}
