package com.homelearning.view;

import com.homelearning.controller.DeveloperController;
import com.homelearning.model.Developer;

import java.util.Scanner;

public class DeveloperView {
    private static final int ADD_DEVELOPER_KEY = 1;
    private static final int UPDATE_DEVELOPER_KEY = 2;
    private static final int DELETE_DEVELOPER_KEY = 3;
    private static final int GET_DEVELOPER_BY_ID_KEY = 4;
    private static final int GET_ALL_DEVELOPERS_KEY = 5;
    private static final int EXIT_KEY = 6;

    private static final int UPDATE_FIRST_NAME_KEY = 1;
    private static final int UPDATE_LAST_NAME_KEY = 2;
    private static final int UPDATE_SPECIALTY_KEY = 3;
    private static final int UPDATE_EXPERIENCE_KEY = 4;
    private static final int UPDATE_SALARY_KEY = 5;
    private static final int SAVE_CHANGES_KEY = 6;
    private static final int CANCEL_UPDATE_KEY = 7;

    private DeveloperController developerController;
    private Scanner scanner;

    public DeveloperView() {
        developerController = new DeveloperController();
        scanner = new Scanner(System.in);
    }

    public void execute(){
        printMenu();
        selectMainMenuAction();
    }

    private void printMenu(){
        System.out.printf("Press %d to add developer.\n", ADD_DEVELOPER_KEY);
        System.out.printf("Press %d to update developer by id.\n", UPDATE_DEVELOPER_KEY);
        System.out.printf("Press %d to delete developer by id.\n", DELETE_DEVELOPER_KEY);
        System.out.printf("Press %d to get developer by id.\n", GET_DEVELOPER_BY_ID_KEY);
        System.out.printf("Press %d to get all developers.\n", GET_ALL_DEVELOPERS_KEY);
        System.out.printf("Press %d to exit.\n", EXIT_KEY);
    }

    private void selectMainMenuAction(){
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
                printNoActionMsg(enteredInteger);
                printMenu();
                selectMainMenuAction();
                break;
        }
        printMenu();
        selectMainMenuAction();
    }

    private void addDeveloperFromInput() {
        int id = readIdFromInput();
        Developer developer = developerController.getById(id);
        if (developer == null) {
            developerController.save(new Developer(id,
                    readFirstNameFromInput(),
                    readLastNameFromInput(),
                    readSpecialtyFromInput(),
                    readExperienceFromInput(),
                    readSalaryFromInput()));
        } else {
            System.out.printf("Developer with id %d already exists:\n", id);
            System.out.println(developer);
        }
    }

    private void updateDeveloperById() {
        int id = readIdFromInput();
        Developer developer = developerController.getById(id);
        if (developer != null){
            System.out.println(developer);
            printDeveloperUpdateMenu();
            selectUpdateDeveloperAction(developer);
        } else {
            printNoDeveloperMsg(id);
        }
    }

    private void printDeveloperUpdateMenu() {
        System.out.printf("Press %d to update first name.\n", UPDATE_FIRST_NAME_KEY);
        System.out.printf("Press %d to update last name.\n", UPDATE_LAST_NAME_KEY);
        System.out.printf("Press %d to update specialty.\n", UPDATE_SPECIALTY_KEY);
        System.out.printf("Press %d to update experience.\n", UPDATE_EXPERIENCE_KEY);
        System.out.printf("Press %d to update salary.\n", UPDATE_SALARY_KEY);
        System.out.printf("Press %d to save changes.\n", SAVE_CHANGES_KEY);
        System.out.printf("Press %d to cancel update.\n", CANCEL_UPDATE_KEY);
    }

    private void selectUpdateDeveloperAction(Developer developer) {
        int enteredInteger = readIntFromInput();
        switch (enteredInteger){
            case UPDATE_FIRST_NAME_KEY: developer.setFirstName(readFirstNameFromInput());
                break;
            case UPDATE_LAST_NAME_KEY: developer.setLastName(readLastNameFromInput());
                break;
            case UPDATE_SPECIALTY_KEY: developer.setSpecialty(readSpecialtyFromInput());
                break;
            case UPDATE_EXPERIENCE_KEY: developer.setExperience(readExperienceFromInput());
                break;
            case UPDATE_SALARY_KEY: developer.setSalary(readSalaryFromInput());
                break;
            case SAVE_CHANGES_KEY: developerController.update(developer);
                return;
            case CANCEL_UPDATE_KEY:
                System.out.println("Changes canceled.");
                return;
            default: printNoActionMsg(enteredInteger);
                printDeveloperUpdateMenu();
                selectUpdateDeveloperAction(developer);
                break;
        }
        System.out.println(developer);
        printDeveloperUpdateMenu();
        selectUpdateDeveloperAction(developer);
    }


    private void deleteDeveloperById() {
        int id = readIdFromInput();
        Developer developer = developerController.delete(id);
        if (developer != null) {
            System.out.println("Removed:");
            System.out.println(developer);
        } else printNoDeveloperMsg(id);
    }

    private void printById(){
        int id = readIdFromInput();
        Developer developer = developerController.getById(id);
        if (developer != null) System.out.println(developer);
        else printNoDeveloperMsg(id);
    }

    private void printAllDevelopers() {
        System.out.println(developerController.getAllDevelopers());
    }

    private void printNoActionMsg(int enteredInteger) {
        System.out.printf("There is no action for %d. Please press correct action key.\n", enteredInteger);
    }

    private void printNoDeveloperMsg(int id) {
        System.out.printf("There is no developer with id %d\n", id);
    }

    private int readIdFromInput() {
        return readIntFromInput("Enter integer id:");
    }

    private String readFirstNameFromInput() {
        return readStringFromInput("Enter first name:");
    }

    private String readLastNameFromInput() {
        return readStringFromInput("Enter last name:");
    }

    private String readSpecialtyFromInput() {
        return readStringFromInput("Enter specialty:");
    }

    private int readExperienceFromInput() {
        return readIntFromInput("Enter integer experience");
    }

    private double readSalaryFromInput() {
        return readDoubleFromInput("Enter double salary:");
    }

    private int readIntFromInput(){
        return readIntFromInput("");
    }

    private int readIntFromInput(String msg){
        if(msg.length() > 0) System.out.println(msg);
        if (scanner.hasNextInt()) return scanner.nextInt();
        scanner.next();
        System.out.println("Incorrect input, please enter an integer.");
        return readIntFromInput(msg);
    }

    private String readStringFromInput(String msg) {
        System.out.println(msg);
        if (scanner.hasNext()) return scanner.next() + scanner.nextLine();
        return readStringFromInput(msg);
    }

    private double readDoubleFromInput(String msg) {
        System.out.println(msg);
        if (scanner.hasNextDouble()) return scanner.nextDouble();
        scanner.next();
        System.out.println("Incorrect input. Please enter double.");
        return readDoubleFromInput(msg);
    }
}
