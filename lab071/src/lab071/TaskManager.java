/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab071;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author win
 */
public class TaskManager extends ArrayList<Task> {

    GetData getdata = new GetData();

    public int addTask(ArrayList<Task> TaskList, int id) {
        System.out.println("----------------Add Task------------------");
        String requirementName = getdata.getString("Requirement Name: ", "", "");
        String taskType = getdata.GetTaskType("Task Type: ");
        String date = getdata.getDate("Date: ");
        double planFrom = getdata.getDouble("From: ", "Plan From must be within 8h-17h", 8.0, 17.0);
        double planTo = getdata.getDouble("To: ", "Plan To must be within From to 17h30", planFrom + 0.5, 17.5);
        String Assignee = getdata.getString("Assignee: ", "", "");
        String reviewer = getdata.getString("Reviewer: ", "", "");
        boolean isExist = checkOverlap(date, Assignee, planFrom, planTo, TaskList);
        //check value of variable isExist
        if (isExist) {
            System.err.println("Task is overlap !");
        } else {
            Task newTask = new Task(id, taskType, requirementName, date, planFrom, planTo, Assignee, reviewer);
            TaskList.add(newTask);
            getdata.addTaskToDB(id);
            id++;
            System.out.println("Add successful!!");
        }
        return id;
    }

    boolean checkOverlap(String date, String assignee, double planFrom, double planTo, ArrayList<Task> TaskList) {
        boolean isExist = false;
        //loop use to access each element of arraylist from begining to the end
        for (Task task : TaskList) {
            //compare date in list with date input and assignee in list and assignee input
            if (date.equals(task.getDate()) && assignee.equals(task.getAssignee())) {
                //compare planTo and planfrom with planFrom and planto in every task in list 
                //have date and assignee same with date, assignee input
                /*planto of object newtask must be lest than planFrom in list
                or planfrom of object newtask must be large than planto in list */
                if ((planTo < task.getPlanFrom()) || (planFrom > task.getPlanTo())) {
                    isExist = false;
                } else {
                    isExist = true;
                    break;
                }
            }
        }
        return isExist;
    }

    void deleteTask(ArrayList<Task> taskList) {
        File file = new File("src\\Data\\task.dat");
        if (taskList.isEmpty() || file.length() == 0) {
            System.out.println("List task is empty!");
            return;
        } else {
            System.out.println("-------Del Task---------");
            int indexInList = -1;
            int TaskId = getdata.getInt("ID: ", "Task id is out of range!", 1, Integer.MAX_VALUE);
            //loop use to access each element of arraylist from begining to the end
            for (Task task : taskList) {
                //compare variable taskid with every taskId has in list
                if (TaskId == task.getTaskID() && getdata.checkID(TaskId)) {
                    indexInList = taskList.indexOf(task);
                }
            }
            //check indexInList not equal - 1 or not
            if (indexInList == -1) {
                System.out.println("Id is not exist!");
            } else {
                taskList.remove(indexInList);
                System.out.println("Delete successful!");
            }

        }
    }

    public void displayTask(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("List task is empty!");
            return;
        } else {
            System.out.println("----------------------------Task-------------------------------------");
            System.out.format("%-7s%-20s%-12s%-15s%-7s%-15s%-15s\n", "Id", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
            //loop use to access each element of arraylist from begining to the end
            for (Task task : taskList) {
                System.out.println(task);
            }
        }
    }

}
