package com.example;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a problem that involves students and teachers.
 */
public class Problem {

    private ArrayList<Person> persons = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Teacher> teachers = new ArrayList<>();

    /**
     * Adds a teacher to the problem.
     *
     * @param teacher the teacher to add
     */
    public void addTeacher(Teacher teacher) {
        if (!persons.contains(teacher)) {
            teachers.add(teacher);
            persons.add(teacher);
        }
    }

    /**
     * Adds a student to the problem.
     *
     * @param student the student to add
     */
    public void addStudent(Student student) {
        if (!persons.contains(student)) {
            students.add(student);
            persons.add(student);
        }
    }

    /**
     * Checks if this problem is equal to another object.
     *
     * @param obj the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Problem problem = (Problem) obj;
        return Objects.equals(persons, problem.persons) &&
               Objects.equals(students, problem.students) &&
               Objects.equals(teachers, problem.teachers);
    }

    /**
     * Returns the list of persons involved in the problem.
     *
     * @return the list of persons
     */
    public ArrayList<Person> getPersons() {
        return persons;
    }

    /**
     * Returns the list of students involved in the problem.
     *
     * @return the list of students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Returns the list of teachers involved in the problem.
     *
     * @return the list of teachers
     */
    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    /**
     * Returns a string representation of the problem.
     *
     * @return a string representation of the problem
     */
    @Override
    public String toString() {
        return "Problem [students=" + students + ", teachers=" + teachers + "]";
    }
}