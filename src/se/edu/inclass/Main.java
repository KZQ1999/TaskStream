package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

//        System.out.println("Printing deadlines");
//        printDeadlines(tasksData);
//
//        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
//        printDeadlinesUsingStream(tasksData);
        ArrayList<Task> filteredList = filterTaskByString(tasksData,"11");
        printData(filteredList);
    }

    public static ArrayList<Task> filterTaskByString(ArrayList<Task> tasks, String description) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t)-> t.getDescription().contains(description))
                .collect(Collectors.toList());
        return filteredList;
    }
    
    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }
    
    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        System.out.println("Printing deadlines using stream");
        tasks.stream()
                .filter((t) -> t instanceof Deadline) // filtering using lamda
                .sorted((a,b)-> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()))
                .forEach(System.out::println);
    }
    
}
