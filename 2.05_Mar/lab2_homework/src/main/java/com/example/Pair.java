package com.example;

/**
 * A generic class representing a pair of a student and a project.
 *
 * @param <Student> the type of the student
 * @param <Project> the type of the project
 */
@SuppressWarnings("hiding")
class Pair<Student, Project> {
    private Student student;
    private Project project;

    /**
     * Constructs a new Pair with the specified student and project.
     *
     * @param student the student
     * @param project the project
     */
    public Pair(Student student, Project project) {
        this.student = student;
        this.project = project;
    }

    /**
     * Returns the student of this pair.
     *
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Returns the project of this pair.
     *
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * Returns a string representation of this pair.
     *
     * @return a string representation of this pair
     */
    @Override
    public String toString() {
        return "Pair [student=" + student + ", project=" + project + "]";
    }

}