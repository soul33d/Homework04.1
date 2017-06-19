package com.homelearning.controller;

import com.homelearning.dao.DeveloperDAO;
import com.homelearning.model.Developer;

public class DeveloperController {
    private DeveloperDAO developerDAO = new DeveloperDAO();

    public DeveloperController() {
    }

    public Developer getById(int id){
        return developerDAO.getById(id);
    }

    public void save(Developer developer){
        developerDAO.save(developer);
    }
}
