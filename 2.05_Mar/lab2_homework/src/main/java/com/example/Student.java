package com.example;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a student with a registration number, name, birth date, and a wishlist of projects.
 */
public class Student extends Person {

    private int registrationNumber;
    private ArrayList<Project> projectsWishlist = new ArrayList<>();
    private Project assignedProject = null;

    /**
     * Constructs a new Student with the specified registration number, name, and birth date.
     *
     * @param registrationNumber the registration number of the student
     * @param name the name of the student
     * @param birthDate the birth date of the student
     */
    public Student(int registrationNumber, String name, LocalDate birthDate) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.birthDate = birthDate;
    }

    /**
     * Adds a project to the student's wishlist.
     *
     * @param project the project to add
     */
    public void addProjectToWishlist(Project project) {
        this.projectsWishlist.add(project);
    }

    public String getName() {
        return name;
    }
    /**
     * Returns the registration number of the student.
     *
     * @return the registration number of the student
     */
    public int getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Returns the wishlist of projects of the student.
     *
     * @return the wishlist of projects
     */
    public ArrayList<Project> getProjectsWishlist() {
        return projectsWishlist;
    }

    /**
     * Returns the assigned project of the student.
     *
     * @return the assigned project
     */
    public Project getAssignedProject() {
        return assignedProject;
    }

    /**
     * Sets the wishlist of projects for the student.
     *
     * @param projectsWishlist the wishlist of projects to set
     */
    public void setProjectsWishlist(ArrayList<Project> projectsWishlist) {
        this.projectsWishlist = projectsWishlist;
    }

    /**
     * Sets the assigned project for the student.
     *
     * @param assignedProject the assigned project to set
     */
    public void setAssignedProject(Project assignedProject) {
        this.assignedProject = assignedProject;
    }

    /**
     * Returns a string representation of the student.
     *
     * @return a string representation of the student
     */
    @Override
    public String toString() {
        return "Student [birthDate=" + birthDate + ", name=" + name + ", registrationNumber=" + registrationNumber
                + ", projectsWishlist=" + projectsWishlist + ", assignedProject=" + assignedProject + "]";
    }

}