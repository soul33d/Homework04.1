package com.homelearning.dao;

import com.homelearning.model.Developer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DeveloperDAO {
    /**default separator for {@link Developer} fields to store in file,
     * if it changed file in {@link #filePath} must be cleared or replaced*/
    private final String separator = ";_";
    private final String filePath = "resources" + File.separator + "developers.txt";

    @Nullable
    public Developer getById(int id){
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null) {
                if (id == getDeveloperId(line)) return developerFromString(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getDeveloperId(String line) {
        return Integer.parseInt(line.substring(0, line.indexOf(separator)));
    }

    /**returns empty {@link Collection} if there is no developers*/
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
    private void developerFromString(@NotNull Developer developer, String line){
        String devParams[] = line.split(separator);
        developer.setId(Integer.parseInt(devParams[0]));
        developer.setFirstName(devParams[1]);
        developer.setLastName(devParams[2]);
        developer.setSpecialty(devParams[3]);
        developer.setExperience(Integer.parseInt(devParams[4]));
        developer.setSalary(Double.parseDouble(devParams[5]));
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
        StringBuilder fileText = new StringBuilder();
        int id = developer.getId();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.lines().forEach(line -> {
                if (id != getDeveloperId(line)) fileText.append(line).append("\n");
                else fileText.append(developerToString(developer));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        overwriteFile(fileText.toString());
    }

    @Nullable
    public Developer delete(int id){
        Developer developer = new Developer();
        StringBuilder fileText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.lines().forEach(line -> {
                if (id != getDeveloperId(line)) fileText.append(line).append("\n");
                else developerFromString(developer, line);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (developer.getSpecialty() != null) {
            overwriteFile(fileText.toString());
            return developer;
        }
        return null;
    }

    private void overwriteFile(String fileText) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(fileText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
