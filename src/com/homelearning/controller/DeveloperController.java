package com.homelearning.controller;

import com.homelearning.dao.DeveloperDAO;
import com.homelearning.model.Developer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class DeveloperController {
    private DeveloperDAO developerDAO = new DeveloperDAO();

    /**returns empty {@link Collection} if there is no developers*/
    public Collection<Developer> getAllDevelopers(){
        return developerDAO.getAllDevelopers();
    }

    @Nullable
    public Developer getById(int id){
        return developerDAO.getById(id);
    }

    /**@throws IllegalArgumentException if developer is null*/
    public void save(@NotNull Developer developer){
        developerDAO.save(developer);
    }

    /**@throws IllegalArgumentException if developer is null*/
    public void update(@NotNull Developer developer){
        developerDAO.update(developer);
    }

    @Nullable
    public Developer delete(int id){
        return developerDAO.delete(id);
    }
}
