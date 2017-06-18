package com.homelearning.controller;

import com.homelearning.dao.DeveloperDAO;
import com.homelearning.model.Developer;

public class DeveloperController {
    private DeveloperDAO developerDAO;

    public DeveloperController() {
    }

    public Developer getById(int id){
        return developerDAO.getById(id);
    }
}
