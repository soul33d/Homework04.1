package com.homelearning.view;

import com.homelearning.controller.DeveloperController;
import com.homelearning.model.Developer;

public class DeveloperView {
    private DeveloperController developerController = new DeveloperController();

    public void execute(){
        developerController.save(new Developer
                (1,"Pavel", "Baranowski", "Java-trainer", 5, 5000.00));
        developerController.save(new Developer
                (2,"Eugene", "Sklyarov", "Mentor", 5, 5000.00));
    }

    public void printMenu(){

    }

    public void printById(int id){

    }
}
