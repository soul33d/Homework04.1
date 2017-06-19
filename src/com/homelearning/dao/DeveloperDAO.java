package com.homelearning.dao;

import com.homelearning.model.Developer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class DeveloperDAO {
    private final String separator = ", ";
    private final String filePath = "resources/developers.txt";

    public Developer getById(int id){
        return null;
    }

    public Collection<Developer> getAllDevelopers(){
        return null;
    }

    public void save(Developer developer){
        StringBuilder sb = new StringBuilder();
        sb.append(developer.getId()).append(separator)
                .append(developer.getFirstName()).append(separator)
                .append(developer.getLastName()).append(separator)
                .append(developer.getSpecialty()).append(separator)
                .append(developer.getExperience()).append(separator)
                .append(developer.getSalary()).append("\n");
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write(sb.toString(), 0, sb.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Developer developer){

    }

    public void delete(int id){

    }
}
