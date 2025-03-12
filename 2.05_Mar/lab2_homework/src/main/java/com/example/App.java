package com.example;

import java.time.LocalDate;

/**
 * The main class of the application.
 */
public class App {
    /**
     * The main method of the application.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        
        Problem P = new Problem();
        Student S1 = new Student(1, "Andrei", LocalDate.of(2000, 1, 4));
        Student S2 = new Student(2, "Ioana", LocalDate.of(2000, 2, 5));
        Student S3 = new Student(3, "Maria", LocalDate.of(2000, 3, 7));

        Project PR1 = new Project("Proiect1", ProjectType.THEORETICAL);
        Project PR2 = new Project("Proiect2", ProjectType.THEORETICAL);
        Project PR3 = new Project("Proiect3", ProjectType.PRACTICAL);

        Teacher T1 = new Teacher("Profesor1", LocalDate.of(1970, 1, 1));
        Teacher T2 = new Teacher("Profesor2", LocalDate.of(1970, 1, 1));
        Teacher T3 = new Teacher("Profesor3", LocalDate.of(1970, 1, 1));

        T1.addProposedProject(PR1);
        T1.addProposedProject(PR2);
        T2.addProposedProject(PR3);
        T2.addProposedProject(PR1);
        T3.addProposedProject(PR2);
        T3.addProposedProject(PR3);

        S1.addProjectToWishlist(PR3);
        S1.addProjectToWishlist(PR2);
        S2.addProjectToWishlist(PR2);
        S2.addProjectToWishlist(PR3);
        S3.addProjectToWishlist(PR1);
        S3.addProjectToWishlist(PR3);
        
        P.addStudent(S1);
        P.addStudent(S2);
        P.addStudent(S3);
        P.addTeacher(T1);
        P.addTeacher(T2);
        P.addTeacher(T3);

        Solution S = new Solution();
        S.getStudents().addAll(P.getStudents());
        S.getTeachers().addAll(P.getTeachers());
        S.allocateProjects();
        System.out.println(S.toString());
    }
}