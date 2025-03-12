package com.example;

/**
 * Represents a project with a name, assignment status, and type.
 */
public class Project {

    private String name;
    private boolean assigned = false;
    private ProjectType projectType;

    /**
     * Constructs a new Project with default values.
     */
    public Project() {
    }

    /**
     * Constructs a new Project with the specified name and project type.
     *
     * @param name the name of the project
     * @param projectType the type of the project
     */
    public Project(String name, ProjectType projectType) {
        this.name = name;
        this.projectType = projectType;
    }

    /**
     * Returns the name of the project.
     *
     * @return the name of the project
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the project.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the type of the project.
     *
     * @return the type of the project
     */
    public ProjectType getProjectType() {
        return projectType;
    }

    /**
     * Sets the type of the project.
     *
     * @param projectType the type to set
     */
    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    /**
     * Returns whether the project is assigned.
     *
     * @return true if the project is assigned, false otherwise
     */
    public boolean isAssigned() {
        return assigned;
    }

    /**
     * Sets the assignment status of the project.
     *
     * @param assigned the assignment status to set
     */
    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    /**
     * Returns a string representation of the project.
     *
     * @return a string representation of the project
     */
    @Override
    public String toString() {
        return "Project [name=" + name + ", assigned=" + assigned + ", projectType=" + projectType + "]";
    }
}