package com.example;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a teacher with a name, birth date, and a list of proposed projects.
 */
public class Teacher extends Person {

    private ArrayList<Project> proposedProjects = new ArrayList<>();

    /**
     * Constructs a new Teacher with the specified name and birth date.
     *
     * @param name the name of the teacher
     * @param birthDate the birth date of the teacher
     */
    public Teacher(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    } 
    
    /**
     * Adds a project to the list of proposed projects.
     *
     * @param project the project to add
     */
    public void addProposedProject(Project project) {
        this.proposedProjects.add(project);
    }

    /**
     * Returns a string representation of the teacher.
     *
     * @return a string representation of the teacher
     */
    @Override
    public String toString() {
        return "Teacher [birthDate=" + birthDate + ", name=" + name + ", proposedProjects=" + proposedProjects + "]";
    }
    
}