package com.example.studybuddyfinder.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String major;
    
    @ElementCollection
    @CollectionTable(name = "student_courses", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "course")
    private List<String> courses = new ArrayList<>();
    
    private String preferredStudyTime;
    private String studyStyle;

    // Default constructor
    public Student() {}

    // Constructor with all fields
    public Student(String name, String major, List<String> courses, String preferredStudyTime, String studyStyle) {
        this.name = name;
        this.major = major;
        this.courses = courses;
        this.preferredStudyTime = preferredStudyTime;
        this.studyStyle = studyStyle;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public String getPreferredStudyTime() {
        return preferredStudyTime;
    }

    public void setPreferredStudyTime(String preferredStudyTime) {
        this.preferredStudyTime = preferredStudyTime;
    }

    public String getStudyStyle() {
        return studyStyle;
    }

    public void setStudyStyle(String studyStyle) {
        this.studyStyle = studyStyle;
    }

    // Method to set courses from a comma-separated string
    public void setCoursesFromString(String coursesString) {
        this.courses = Arrays.asList(coursesString.split(","));
    }

    // Method to get courses as a comma-separated string
    public String getCoursesAsString() {
        return String.join(",", courses);
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", courses=" + courses +
                ", preferredStudyTime='" + preferredStudyTime + '\'' +
                ", studyStyle='" + studyStyle + '\'' +
                '}';
    }
}