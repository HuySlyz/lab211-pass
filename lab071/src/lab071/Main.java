/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab071;

import java.util.ArrayList;

/**
 *
 * @author win
 */
public class Main {

    public static void main(String[] args) {
       
        GetData getdata = new GetData();
        ArrayList<Task> taskList = new ArrayList<>();
        int id = 1;
        TaskManager manager = new TaskManager();
        int choice;      
        do {
            //Display menu
            getdata.displayMenu();
            //get user choice
            choice = getdata.getInt("Your choice: ", "Choice must be in range [1-4]", 1, 4);
            switch (choice) {
                //1. Add Task
                case 1:
                    id = manager.addTask(taskList, id);
                    break;
                //2. delete task
                case 2:
                    manager.deleteTask(taskList);
                    break;
                //3. Display task
                case 3:
                    manager.displayTask(taskList);
                    break;
                //4. Exit
                case 4:
                    System.exit(0);
                    break;
            }
        }while (true);
    
    }
}
