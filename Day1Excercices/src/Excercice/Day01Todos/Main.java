package Excercice.Day01Todos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static ArrayList<Todo> todoList = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    static int inputInt() {
        while (true) {
            try {
                int userChoice= input.nextInt();
                input.nextLine();
                return userChoice;
            } catch (InputMismatchException e) {
                System.out.println("Invalid integer. Try again.");
            }
        }
    }

     static void mainMenu() {
        System.out.println("1. Add a todo");
        System.out.println("2. List all todos(numbered)");
        System.out.println("3. Delete a todo");
        System.out.println("4. Modify a todo");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

     static void addTodo() {
        System.out.println("Adding a todo.");
        System.out.print("Enter task: ");
        String task = input.nextLine();
        System.out.print("Enter due date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(input.nextLine());
        System.out.print("Enter hours of work: ");
        int hoursOfWork = inputInt();
        input.nextLine();
        Todo todo = new Todo(task, dueDate, hoursOfWork, Todo.TaskStatus.Pending);
        System.out.println("You've created the following todo:");
        System.out.println(todo);
        todoList.add(todo);
    }

     static void listTodos() {
        System.out.println("Listing all todos:");
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println(i + 1 + ". " + todoList.get(i));
        }
    }

     static void modifyTodos() {
        System.out.println("Modifying a todo. Which todo# would you like to modify?");
        int userChoice = inputInt();
        input.nextLine();
        System.out.print("Modifying todo #" + userChoice + ":" + todoList.get(userChoice - 1));
        System.out.print("Enter new task: ");
        todoList.get(userChoice - 1).setTask(input.nextLine());
        System.out.print("Enter new due date: ");
        todoList.get(userChoice - 1).setDueDate(LocalDate.parse(input.nextLine()));
        System.out.print("Enter new hours of work: ");
        todoList.get(userChoice - 1).setHoursOfWork(inputInt());
        input.nextLine();
        System.out.print("Enter new status (Pending/Completed): ");
        todoList.get(userChoice - 1).setStatus(Todo.TaskStatus.valueOf(input.nextLine()));
        System.out.println("You've modify todo" + userChoice + "as follow:");
        System.out.println(todoList.get(userChoice - 1));
    }

     static void deleteTodos() {
        System.out.println("Deleting a todo. Which todo# would you like to delete?");
        int userChoice = inputInt();
        input.nextLine();
        if (userChoice > 0 && userChoice <= todoList.size()) {
            todoList.remove(userChoice - 1);
            System.out.println("You've deleted todo #" + userChoice);
        } else {
            System.out.println("Invalid input. Try again.");
        }

    }

    static void loadDataFromFile(){
        try {
            File input = new File("src/Excercice/todos.txt");
            Scanner sc = new Scanner(input);
            System.out.println("Data loaded");
            while (sc.hasNextLine()) {

                String dataLine = sc.nextLine();
                Todo todo = new Todo(dataLine);
                todoList.add(todo);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    static void saveDataToFile() throws IOException {
        try {
            FileWriter myWriter = new FileWriter("todos.txt",true);
            for (Todo todo : todoList){
                myWriter.write(todo.toDataString() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            throw new IOException("An error occurred.");
        }
        mainMenu();
    }
    static void saveAndExit() throws IOException {
         saveDataToFile();
         System.out.println("Exiting. Goodbye!");
         System.exit(0);
    }



    public static void main(String[] args) {
        int userChoice = -1;
        loadDataFromFile();
        do {
            mainMenu();
            try {
                userChoice = inputInt();
                input.nextLine();
                switch (userChoice) {
                    case 1:
                        addTodo();
                        break;
                    case 2:
                        listTodos();
                        break;
                    case 3:
                        deleteTodos();
                        break;
                    case 4:
                        modifyTodos();
                        break;
                    case 0:
                        saveAndExit();
                        break;
                    default:
                        System.out.println("Invalid input. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again.");
                input.nextLine();
                userChoice = -1;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } while (userChoice != 0);

        input.close();
    }
}
