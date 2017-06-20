package com.homelearning.dao;

import com.homelearning.model.Developer;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DeveloperDAO {
    private final String separator = ", ";
    private final String filePath = "resources/developers.txt";

    public Developer getById(int id){
        return null;
    }

    public Collection<Developer> getAllDevelopers(){
        List<Developer> developers = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            reader.lines().forEach(line -> developers.add(developerFromString(line)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return developers;
    }


    @NotNull
    private Developer developerFromString(String line) {
        String devParams[] = line.split(separator);
        return new Developer(Integer.parseInt(devParams[0]), devParams[1], devParams[2], devParams[3],
                Integer.parseInt(devParams[4]), Double.parseDouble(devParams[5]));
    }

    /**@throws IllegalArgumentException if developer is null*/
    public void save(@NotNull Developer developer){
        String developerAsString = developerToString(developer);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write(developerAsString, 0, developerAsString.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**@throws IllegalArgumentException if developer is null*/
    @NotNull
    private String developerToString(@NotNull Developer developer) {
        return String.valueOf(developer.getId()) + separator +
                developer.getFirstName() + separator +
                developer.getLastName() + separator +
                developer.getSpecialty() + separator +
                developer.getExperience() + separator +
                developer.getSalary() + "\n";
    }

    /**@throws IllegalArgumentException if developer is null*/
    public void update(@NotNull Developer developer){

    }

    public void delete(int id){

    }
}
