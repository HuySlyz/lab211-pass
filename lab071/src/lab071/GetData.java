/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab071;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author win
 */
public class GetData {

    Scanner sc = new Scanner(System.in);

    public int getInt(String msg, String outRangeMsg, int min, int max) {
        String input;
        int result;
        do {
            System.out.print(msg);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Input could not be empty!!!");
            } else {
                try {
                    result = Integer.parseInt(input);
                    //check choice in range min and max
                    if (result >= min && result <= max) {
                        break;
                    } else {
                        System.out.println(outRangeMsg);
                        continue;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Input must be integer");
                }
            }
        } while (true);
        return result;
    }

    public String getString(String msg, String formatMsg, String regex) {
        String input;
        do {
            System.out.print(msg);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Input could not be empty!!!");
                continue;
            } else {
                if (regex.isEmpty()) {
                    break;
                    //compare input with regex
                } else if (input.matches(regex)) {
                    break;
                } else {
                    System.out.println(formatMsg);
                    continue;
                }
            }
        } while (true);
        return input;
    }

    public String getDate(String msg) {
        String input;
        Date date;
        String resultDate;
        do {
            System.out.print(msg);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            dateFormat.setLenient(false);
            input = sc.nextLine();
            if (input.isEmpty()) {
                System.out.println("Input could not be empty!!!");
                continue;
            } // \d{1,2}: the number have 1 or 2 digit number
            //[-]: contain character -
            //\d{4}: the number must have 4 digit
            else if (!input.matches("\\d{1,2}[-]\\d{1,2}[-]\\d{4}")) {
                System.out.println("Input is wrong format");
                continue;
            }
            try {
                date = dateFormat.parse(input);
                Date now = new Date();
                //check date must be before now

                break;
            } catch (ParseException exception) {
                System.out.println("Date doesn't existed!!");
            }
        } while (true);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        resultDate = dateFormat.format(date);
        return resultDate;
    }

    public void displayMenu() {
        System.out.println("=====Task Program=====");
        System.out.println("\t1. Add Task");
        System.out.println("\t2. Delete Task");
        System.out.println("\t3. Display Task");
        System.out.println("\t4. exit");
    }

    public String GetTaskType(String msg) {
        int tasktypeID = getInt(msg, "Task Type must be in range[1-4]", 1, 4);
        String result = "";
        switch (tasktypeID) {
            case 1:
                result = "Code";
                break;
            case 2:
                result = "Test";
                break;
            case 3:
                result = "Design";
                break;
            case 4:
                result = "Review";
                break;
        }
        return result;
    }

    public double getDouble(String msg, String outRangeMsg, double min, double max) {
        double result;
        String input;
        do {
            input = getString(msg, "", "");
            if (input.isEmpty()) {
                System.out.println("Input could not be empty!");
            } else {
                try {
                    result = Double.parseDouble(input);
                    //check choice in range min and max
                    if (result >= min && result <= max) {
                        break;
                    } else {
                        System.out.println(outRangeMsg);
                        continue;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Input could be a real number!");
                }
            }
        } while (true);
        return result;
    }

    public void addTaskToDB(int id) {
        File file = new File("src\\Data\\task.dat");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(id + "\n");
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkID(int id) {
        File file = new File("src\\Data\\task.dat");
        try {
            Scanner sc = new Scanner(file);
            int[] number = new int[100];
            int i = 0;         
            while (sc.hasNextInt()) {
                int num = sc.nextInt();
                number[i] = num;
                i++;             
            }
            for (int j : number) {
                if(id == number[j]){
                    return true;
                }
            }
           
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean checkFileExist() {
        File file = new File("src\\Data\\task.dat");
        if (!file.exists()) {
            try {
                System.err.println("File not exist!!!");
                file.createNewFile();
                System.err.println("File created.");
                return false;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

}
