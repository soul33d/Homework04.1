package com.homelearning.controller;

import com.homelearning.dao.DeveloperDAO;
import com.homelearning.model.Developer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class DeveloperController {
    private DeveloperDAO developerDAO = new DeveloperDAO();

    public Collection<Developer> getAllDevelopers(){
        return developerDAO.getAllDevelopers();
    }

    @Nullable
    public Developer getById(int id){
        return developerDAO.getById(id);
    }

    public void save(@NotNull Developer developer){
        developerDAO.save(developer);
    }

    public void update(@NotNull Developer developer){
        developerDAO.update(developer);
    }

    public void delete(int id){
        developerDAO.delete(id);
    }
}
