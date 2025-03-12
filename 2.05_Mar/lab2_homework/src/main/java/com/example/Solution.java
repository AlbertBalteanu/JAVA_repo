package com.example;

import java.util.ArrayList;

/**
 * Represents a solution to the problem of assigning projects to students.
 */
public class Solution extends Problem {

    ArrayList<Pair<Student, Project>> pairs = new ArrayList<Pair<Student, Project>>();

    /**
     * Constructs a new Solution with default values.
     */
    public Solution() {
        super();
    }

    /**
     * Allocates projects to students based on their wishlist.
     * Projects are assigned to students if they are not already assigned.
     */
    public void allocateProjects() {
        for (Student student : getStudents()) {
            for (Project project : student.getProjectsWishlist()) {
                if (!project.isAssigned()) {
                    project.setAssigned(true);
                    student.setAssignedProject(project);
                    pairs.add(new Pair<>(student, project));
                    break;
                }
            }
        }
    }

    /**
     * Returns a string representation of the solution.
     *
     * @return a string representation of the solution
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Pair<Student, Project> pair : pairs) {
            result.append("Student with id ")
                  .append(pair.getStudent().getRegistrationNumber())
                  .append(" and name ")
                  .append(pair.getStudent().getName())
                  .append(" has project ")
                  .append(pair.getProject().getName())
                  .append(" assigned\n");
        }
        return result.toString();
    }

}