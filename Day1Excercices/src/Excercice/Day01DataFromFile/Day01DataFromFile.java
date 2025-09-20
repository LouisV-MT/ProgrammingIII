package Excercice.Day01DataFromFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day01DataFromFile {
    static ArrayList<String> namesList = new ArrayList<>();
    static ArrayList<Double> numsList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            File input = new File("src/Excercice/Day01DataFromFile/input.txt");
            Scanner sc = new Scanner(input);
            System.out.println("Data loaded");
            while (sc.hasNextLine()) {

                String line = sc.nextLine();

                if (line.contains(".") || Character.isDigit(line.charAt(1))) {
                    numsList.add(Double.valueOf(line));
                } else {
                    namesList.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        Collections.sort(namesList);
        System.out.print("Names sorted: " + String.join(", ", namesList));


        Collections.sort(numsList);
        System.out.print("\nNumbers sorted:  " + String.join(", ", numsList.toString()));
        //Length of names
        int sum = 0;
        for (String i : namesList) {
            sum += i.length();
        }
        double average = (double) sum / namesList.size();
        System.out.printf("\nAverage length of names: %.2f\n ", average);
        // Duplicate names:
        System.out.print("\nDuplicate names: ");
        var duplicateName = new ArrayList<String>();
        for (int i = 0; i < namesList.size(); i++) {
            for (int j = i + 1; j < namesList.size(); j++) {
                if (namesList.get(i).equals(namesList.get(j))) {
                    duplicateName.add(namesList.get(i));
                }
            }
        }
        for (int i = 0; i < duplicateName.size(); i++) {
            for (int j = i + 1; j < duplicateName.size(); j++){
                if (duplicateName.get(i).equals(duplicateName.get(j))){
                    duplicateName.remove(j);
                    duplicateName.remove(i);
            }
        }
    }
        System.out.println(duplicateName);



    }
}




