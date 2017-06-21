package com.homelearning.view;

import com.homelearning.controller.DeveloperController;
import com.homelearning.model.Developer;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class DeveloperView {
    private static final int ADD_DEVELOPER_KEY = 1;
    private static final int UPDATE_DEVELOPER_KEY = 2;
    private static final int DELETE_DEVELOPER_KEY = 3;
    private static final int GET_DEVELOPER_BY_ID_KEY = 4;
    private static final int GET_ALL_DEVELOPERS_KEY = 5;
    private static final int EXIT_KEY = 6;

    private DeveloperController developerController;
    private Scanner scanner;
    private String intIdMessage = "Enter integer id:";

    public DeveloperView() {
        developerController = new DeveloperController();
        scanner = new Scanner(System.in);
    }

    public void execute(){
        printMenu();
        selectAction();
    }

    private void printMenu(){
        System.out.printf("Press %d to add developer.\n", ADD_DEVELOPER_KEY);
        System.out.printf("Press %d to update developer by id.\n", UPDATE_DEVELOPER_KEY);
        System.out.printf("Press %d to delete developer by id.\n", DELETE_DEVELOPER_KEY);
        System.out.printf("Press %d to get developer by id.\n", GET_DEVELOPER_BY_ID_KEY);
        System.out.printf("Press %d to get all developers.\n", GET_ALL_DEVELOPERS_KEY);
        System.out.printf("Press %d to exit.\n", EXIT_KEY);
    }

    private void printById(){
        int id = readIntFromInput(intIdMessage);
        Developer developer = developerController.getById(id);
        if (developer != null) System.out.println(developer);
        else System.out.printf("There is no developer with id %d.\n", id);
    }

    private void selectAction(){
        int enteredInteger = readIntFromInput();
        switch (enteredInteger){
            case ADD_DEVELOPER_KEY: addDeveloperFromInput();
                break;
            case UPDATE_DEVELOPER_KEY: updateDeveloperById();
                break;
            case DELETE_DEVELOPER_KEY: deleteDeveloperById();
                break;
            case GET_DEVELOPER_BY_ID_KEY: printById();
                break;
            case GET_ALL_DEVELOPERS_KEY: printAllDevelopers();
                break;
            case EXIT_KEY : System.exit(0);
                break;
            default:
                System.out.printf("There is no action for %d. Please press correct action key.\n", enteredInteger);
                printMenu();
                selectAction();
                break;
        }
        printMenu();
        selectAction();
    }

    private int readIntFromInput(){
        return readIntFromInput("");
    }

    private int readIntFromInput(String msg){
        if(msg.length() > 0) System.out.println(msg);
        if (scanner.hasNextInt()) return scanner.nextInt();
        scanner.next();
        System.out.println("Incorrect input, please enter an integer.");
        return readIntFromInput();
    }

    private void addDeveloperFromInput() {
        developerController.save(readDeveloperFromInput());
    }


    @NotNull
    private Developer readDeveloperFromInput() {
        int id = readIntFromInput(intIdMessage);
        String firstName = readStringFromInput("Enter first name:");
        String lastName = readStringFromInput("Enter last name:");
        String specialty = readStringFromInput("Enter specialty:");
        int experience = readIntFromInput("Enter integer experience");
        double salary = readDoubleFromInput("Enter double salary:");
        return new Developer(id, firstName, lastName, specialty, experience, salary);
    }

    private double readDoubleFromInput(String msg) {
        System.out.println(msg);
        if (scanner.hasNextDouble()) return scanner.nextDouble();
        scanner.next();
        System.out.println("Incorrect input. Please enter double.");
        return readDoubleFromInput(msg);
    }

    private String readStringFromInput(String msg) {
        System.out.println(msg);
        if (scanner.hasNext()) return scanner.next();
        return readStringFromInput(msg);
    }

    private void updateDeveloperById() {
        int id = readIntFromInput(intIdMessage);
        Developer developer = developerController.getById(id);
        if (developer != null){
            System.out.println(developer);
            developerController.update(readDeveloperFromInput());
        } else {
            System.out.printf("Developer with id %d not found.\n", id);
        }
    }

    private void deleteDeveloperById() {
        int id = readIntFromInput(intIdMessage);
        developerController.delete(id);
    }

    private void printAllDevelopers() {
        System.out.println(developerController.getAllDevelopers());
    }
}
