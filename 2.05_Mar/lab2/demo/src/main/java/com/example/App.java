package com.example;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Student S1 = new Student("Ion");
        Student S2 = new Student("Vasile");
        Student S3 = new Student("Gheorghe"); 

        List<Student> students = new ArrayList<Student>();

        students.add(S1);
        students.add(S2);
        students.add(S3);

        Project P1 = new Project("ProiectulA", ProjectType.THEORETICAL);
        Project P2 = new Project("ProiectulB", ProjectType.PRACTICAL);
        Project P3 = new Project("ProiectulC", ProjectType.THEORETICAL);

        List<Project> projects = new ArrayList<Project>();

        projects.add(P1);
        projects.add(P2);
        projects.add(P3);

        S1.addProject(P1);
        S1.addProject(P2);
        S2.addProject(P3);
        S2.addProject(P1);
        S3.addProject(P2);
        S3.addProject(P3);

        assignProjects(students, projects);
    }

    public static void assignProjects(List<Student> students, List<Project> projects) {
        for (Student student : students) {
            for (Project project : projects) {
                if (student.getProiecteDorite().contains(project) && !project.isAssigned()) {
                    project.setAssigned(true);
                    System.out.println("Studentul " + student.getName() + " a fost asignat la " + project.getName());
                    break;
                }
            }
        }
    }
}
