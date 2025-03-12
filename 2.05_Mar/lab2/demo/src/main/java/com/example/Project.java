package com.example;

public class Project {

    private String name;
    private boolean assigned = false;
    private ProjectType projectType;

    public Project (String name, ProjectType projectType) {
        this.name = name;
        this.projectType = projectType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }


    @Override
    public String toString() {
        return "Project [name=" + name + ", assigned=" + assigned + ", projectType=" + projectType + "]";
    }


}
