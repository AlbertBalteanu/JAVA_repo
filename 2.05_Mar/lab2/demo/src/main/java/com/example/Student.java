package com.example;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name; 
    private List<Project> proiecteDorite = new ArrayList<Project>();   

    public Student(String name){
        this.name = name;
    }

    public void addProject(Project project){
        proiecteDorite.add(project);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", proiecteDorite=" + proiecteDorite + "]";
    }

    public List<Project> getProiecteDorite() {
        return proiecteDorite;
    }

    public void setProiecteDorite(List<Project> proiecteDorite) {
        this.proiecteDorite = proiecteDorite;
    }

}
